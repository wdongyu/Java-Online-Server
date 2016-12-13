import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
	public static void main(String[] args) {
		try {
			Socket socket=new Socket("localhost",9875);
			Scanner input=new Scanner(System.in);
			
			//ObjectInputStream fromServer=new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream toServer=new ObjectOutputStream(socket.getOutputStream());
			
			while (input.hasNext()) {
				int type=3;
				String[] s=new String[1];
				s[0]=input.nextLine();
				toServer.writeObject(new tokens(type,s));
				toServer.flush();
				//socket.close();
				//String s=(String)fromServer.readObject();
				System.out.println(s[0]);
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		} 
		/*catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
