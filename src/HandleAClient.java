import java.net.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
			
			while (true) {
				Object src=inputFromClient.readObject();
				if (src!=null && src instanceof tokens)
					handleMessage((tokens)src);
					//System.out.println(((tokens)src).getType() + " " + (((tokens)src).getContent())[0]);
				//outputToClient.writeObject(src);
			}
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void handleMessage(tokens src) throws InterruptedException, ExecutionException {
		int type=src.getType();
		String[] s=src.getContent();
		
		switch (type) {
		case 1: break;
		case 2: break;
		case 3: {
			ExecutorService executor=Executors.newFixedThreadPool(1);
			getResult r=new getResult(s[0]);
			Future<String> f=executor.submit(r);
			executor.shutdown();
			System.out.println(f.get());
			break;
		}
		case 4: break;
		}
	}
}
