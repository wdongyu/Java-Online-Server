import java.net.*;
import java.sql.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.io.*;

public class HandleAClient implements Runnable{
	private Socket socket;
	private String preWord=null;
	private String preInter=null;
	private String[] pCount={"0","0","0"};
	ObjectInputStream inputFromClient;
	ObjectOutputStream outputToClient;
	connectToDB db=new connectToDB();
	
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
		String count="0";
		
		switch (type) {
		case 1: {
			System.out.println(interactWithDB(src));
			break;
		}
		case 2: { 
			System.out.println(interactWithDB(src));
			break;
		}
		case 3: {
			ExecutorService executor=Executors.newFixedThreadPool(1);
			//getResult r=new getResult(s[0]);
			Callable<String> t;
			switch (s[1]) {
			case "HC": t=new translateFromHaici(s[0]); break;
			case "JS": t=new translateFromJinshan(s[0]); break;
			case "YD": t=new translateFromYoudao(s[0]); break;
			default: t=null;
			}
			Future<String> f=executor.submit(t);
			executor.shutdown();
			preInter=f.get();
			if (s[0].equals(preWord)) {
				switch (s[1]) {
				case "HC": count=pCount[2]; break;
				case "JS": count=pCount[0]; break;
				case "YD": count=pCount[1]; break;
				}
			}
			else {
				preWord=s[0];
				count=interactWithDB(src);
			}
			
			String[] back=new String[2];
			back[0]=preInter;
			back[1]=count;
			System.out.println(back[0] + "\n" + back[1]);
			break;
		}
		case 4: {
			interactWithDB(src);
			break;
		}
		}
	}
	
	public String interactWithDB(tokens src) {
		int type=src.getType();
		String[] s=src.getContent();
		String option=null,str="";
		ResultSet resultSet;
		String query;
		
		switch (s[1]) {
		case "HC": option="haici"; break;
		case "JS": option="jinshan"; break;
		case "YD": option="youdao"; break;
		}
		
		try {
			switch (type) {
			case 1: {
				query="select username from UserData";
				resultSet=db.getResultSet(query,1);
				while (resultSet.next()) {
					//System.out.println(resultSet.getString("username"));
					if (s[0].equals(resultSet.getString("username").trim())) 
						return "F";
				}
				query="insert into UserData(username,password) values ('" + s[0] + "','" + s[1] + "')";
				db.getResultSet(query, 1);
				return "T";
			}
			case 2: {
				query="select username,password from UserData";
				resultSet=db.getResultSet(query, 1);
				boolean m=false;
				while (resultSet.next()) {
					if (s[0].equals(resultSet.getString("username").trim())) {
						m=true;
						break;
					}
				}
				
				if (m && s[1].equals(resultSet.getString("password").trim())) {
					query="update UserData set status=1 where username='" + s[0] + "'";
					db.getResultSet(query, 1);
					query="select username from UserData where status=1";
					resultSet=db.getResultSet(query, 1);
					while (resultSet.next()) {
						str=str + resultSet.getString("username") + "\n";
					}
					return str;
				}
				else
					return "F";
			}
			case 3: {
				//preWord=s[0];
				
				query="select * from PraiseCount where content='" + s[0] + "'";
				//connectToDB db=new connectToDB();
				resultSet=db.getResultSet(query,2);
				boolean b=false;
				//resultSet.next();
				if (resultSet==null || !resultSet.next()) {
					query="insert into PraiseCount(content) values ('" + s[0] + "')";
					//return "0";
					//resultSet=db.getResultSet(query,2);
					b=true;
				}
				if (b) {
					for (int i=0; i<3; i++)
						pCount[i]="0";
					return "0";
				}
				else {
					//resultSet.next();
					for (int i=2; i<=4; i++) {
						//System.out.println(resultSet.getString(i));
						pCount[i-2]=resultSet.getString(i);
					}
					return resultSet.getString(option);
				}
			}
			case 4: {
				query="update PraiseCount set " + option + "=" + option + "+1 where content='" + s[0] + "'";
				//connectToDB db=new connectToDB();
				resultSet=db.getResultSet(query,2);
				return "T";
			}
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}
}
