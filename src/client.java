import java.io.*;
import java.net.*;

public class client {
	public static void main(String[] args) {
		try {
			Socket socket=new Socket("114.212.130.245",9870);
			
			ObjectInputStream fromServer=new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream toServer=new ObjectOutputStream(socket.getOutputStream());
			
			toServer.writeObject("hello");
			toServer.flush();
			
			String s=(String)fromServer.readObject();
			System.out.println(s);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
