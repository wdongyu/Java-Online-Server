import java.net.*;
import java.io.*;

public class HandleAClient {
	private Socket socket;
	
	public HandleAClient(Socket socket) {
		this.socket=socket;
	}
	
	public void run() {
		try {
			ObjectInputStream inputFromClient=new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream outputToClient=new ObjectOutputStream(socket.getOutputStream());
			
			while (true) {
				Object src=inputFromClient.readObject();
				Object result=getResult(src);
				outputToClient.writeObject(result);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Object getResult(Object src) {
		return src;
	}
}
