import java.io.*;
import java.net.*;

public class javaServer {
	public static void main(String[] args) {
		new javaServer();
	}
	
	public javaServer() {
		try {
			ServerSocket serverSocket=new ServerSocket(8000);
			System.out.println("Server started ");
			
			int clientNo=1;
			
			while (true) {
				Socket socket=serverSocket.accept();
				System.out.println("Starting thread for client " + clientNo);
				
				InetAddress inetAddress=socket.getInetAddress();
				System.out.println("Client " + clientNo + "'s host name is " + inetAddress.getHostName());
				System.out.println("Client " + clientNo + "'s Ip Address is " + inetAddress.getHostAddress());
				
				HandleAClient task=new HandleAClient(socket);
				new Thread(task).start();
				
				clientNo++;
			}
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				inputFromClient.close();
				outputToDB.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
