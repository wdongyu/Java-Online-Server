import java.net.*;
import java.io.*;

public class HandleAClient implements Runnable{
	private Socket socket;
	ObjectInputStream inputFromClient;
	ObjectOutputStream outputToClient;
	
	public HandleAClient(Socket socket) {
		this.socket=socket;
	}
	
	public void run() {
		try {
			inputFromClient=new ObjectInputStream(socket.getInputStream());
			outputToClient=new ObjectOutputStream(socket.getOutputStream());
			
			//while (true) {
				Object src=inputFromClient.readObject();
				if (src!=null && src instanceof tokens) 
					System.out.println(((tokens)src).getType() + " " + (((tokens)src).getContent())[0]);
				outputToClient.writeObject(src);
			//}
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public tokens handleSrc(tokens src) {
		String[] s=new String[1];
		s[0]="hello!!!";
		return new tokens(2,s);
	}
}
