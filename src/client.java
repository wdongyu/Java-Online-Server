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
				int type=5;
				String[] s=new String[2];
				//type=input.nextInt();
				s[0]=input.nextLine();
				s[1]=input.nextLine();
				toServer.writeObject(new Tokens(type,s));
				toServer.flush();
				//socket.close();
				//String s=(String)fromServer.readObject();
				System.out.println(s[0]);
				//Object t=fromServer.readObject();
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		} 
		//catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
	}
}
