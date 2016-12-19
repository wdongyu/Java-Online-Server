import java.net.*;
import java.sql.*;
import java.util.concurrent.*;
import java.io.*;
import java.util.*;
import java.util.Date;

public class HandleAClient implements Runnable{
	private Socket socket;
	//private String preWord=null;
	//private String preInter=null;		
	private String username=null;
	private String[] r;
	private String[] pCount={"0","0","0"};
	ObjectInputStream inputFromClient;
	ObjectOutputStream outputToClient;
	connectToDB db=new connectToDB();
	
	public HandleAClient(Socket socket) {
		this.socket=socket;
	}
	
	public void run() {
		try {
			while (true) {
				inputFromClient=new ObjectInputStream(socket.getInputStream());
				outputToClient=new ObjectOutputStream(socket.getOutputStream());
				Object src=inputFromClient.readObject();
				/* 处理每一条消息 */
				if (src!=null && src instanceof Tokens)
					handleMessage((Tokens)src);
				outputToClient.flush();
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
	
	public void handleMessage(Tokens src) throws InterruptedException, ExecutionException, IOException {
		int type=src.getType();
		String[] s=src.getContent();

		String count="0";
		String result="";
		
		switch (type) {
		case 1: {
			result=interactWithDB(src);
			//r[0]=result;
			System.out.println(result);
			//outputToClient.writeObject(new Tokens(1,r));
			break;
		}
		case 2: {
			result=interactWithDB(src);
			//r[0]=result;
			System.out.println(result);
			//outputToClient.writeObject(new Tokens(2,r));
			break;
		}
		case 3: {
			ExecutorService executor=Executors.newFixedThreadPool(3);
			translateFromJinshan t1=new translateFromJinshan(s[0]);
			translateFromYoudao t2=new translateFromYoudao(s[0]);
			translateFromHaici t3=new translateFromHaici(s[0]);
			
			Future<String> f1=executor.submit(t1);
			Future<String> f2=executor.submit(t2);
			Future<String> f3=executor.submit(t3);

			executor.shutdown();
			//getResult r=new getResult(s[0]);
			/*Callable<String> t;
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
			}*/
			
			System.out.println(interactWithDB(src));
			r=new String[6];
			//result= + "\n" +  + "\n" + ;
			r[0]=f1.get();
			r[1]=f2.get();
			r[2]=f3.get();
			r[3]=pCount[0];
			r[4]=pCount[1];
			r[5]=pCount[2];
			System.out.println(r[0]);
			outputToClient.writeObject(new Tokens(3,r));
			break;
		}
		case 4: {
			result=interactWithDB(src);
			//r[0]=result;
			System.out.println(result);
			//outputToClient.writeObject(new Tokens(4,r));
			break;
		}
		case 5: {
			synchronized (javaServer.map) {
				System.out.println(javaServer.map);
				/* 保存单词卡信息 */
				javaServer.imageSave.put(s, username);
			}
			break;
		}
		case 6: {
			synchronized (javaServer.imageSave) {
				/* 发送单词卡信息 */
				Set<Map.Entry<String[], String>> entryset=javaServer.imageSave.entrySet();
				for (Map.Entry<String[], String> entry: entryset) {
					if (entry.getValue().equals(username)) {
						Socket dstSocket=javaServer.map.get(username);
						outputToClient=new ObjectOutputStream(dstSocket.getOutputStream());
						outputToClient.writeObject(new Tokens(6,entry.getKey()));
					}
				}
			}
			break;
		}
		case 99: {
			result=interactWithDB(src);
			System.out.println(result);
			break;
		}
		}
	}
	
	public String interactWithDB(Tokens src) {
		int type=src.getType();
		String[] s=src.getContent();
		String option=null,str="";
		ResultSet resultSet;
		String query;
		
		if (s.length>1) {
			switch (s[1]) {
			case "0": option="jinshan"; break;
			case "1": option="youdao"; break;
			case "2": option="haici"; break;
			}
		}
		
		try {
			switch (type) {
			case 1: {
				query="select username from UserData";
				resultSet=db.getResultSet(query,1);
				while (resultSet.next()) {
					//System.out.println(resultSet.getString("username"));
					if (s[0].equals(resultSet.getString("username").trim())) {
						outputToClient.writeObject(new Tokens(-1,null));
						return "-1";
					}
				}
				username=s[0];
				javaServer.map.put(s[0],null);
				query="insert into UserData(username,password,recentTime) values ('" + s[0] + "','" + s[1] + "','" + new Date() + "')";
				db.getResultSet(query, 1);
				outputToClient.writeObject(new Tokens(1,null));
				return "1";
			}
			case 2: {
				query="select * from UserData";
				resultSet=db.getResultSet(query, 1);
				boolean m=false;
				while (resultSet.next()) {
					if (s[0].equals(resultSet.getString("username").trim())) {
						m=true;
						break;
					}
				}
				
				if (m && s[1].equals(resultSet.getString("password").trim())) {
					//System.out.println(resultSet.getString("status"));
					if (!resultSet.getString("status").equals("1")) {
						username=s[0];
						javaServer.map.put(s[0],socket);
						query="update UserData set status=1,recentTime='" + new Date() + "' where username='" + s[0] + "'";
						db.getResultSet(query, 1);
						/* 查询用户人数 */
						query="select count(username) from UserData";
						resultSet=db.getResultSet(query, 1);
						resultSet.next();
						int c=Integer.parseInt(resultSet.getString(1));
						System.out.println(c);
						r=new String[c];
						
						int i=0;
						query="select username from UserData where status=1";
						resultSet=db.getResultSet(query, 1);
						while (resultSet.next()) {
							r[i]=resultSet.getString("username") + " " + "1";
							i++;
							str=str + resultSet.getString("username") + " " + "1" + "\n";
						}
						query="select username from UserData where status=0";
						resultSet=db.getResultSet(query, 1);
						while (resultSet.next()) {
							r[i]=resultSet.getString("username") + " " + "0";
							i++;
							str=str + resultSet.getString("username") + " " + "0" + "\n";
						}
						outputToClient.writeObject(new Tokens(2,r));
					}
					else {
						str=str + "Fail to login in for this username is online\n";
						outputToClient.writeObject(new Tokens(-2,null));
					}
					return str;
				}
				else {
					outputToClient.writeObject(new Tokens(-2,null));
					return "Fail to match the username and password\n";
				}
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
					db.getResultSet(query,2);
					b=true;
				}
				//outputToClient.writeObject(new Tokens(3,null));
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
					return "Search done...";
				}
			}
			case 4: {
				query="update PraiseCount set " + option + "=" + option + s[2] + " where content='" + s[0] + "'";
				//connectToDB db=new connectToDB();
				resultSet=db.getResultSet(query,2);
				outputToClient.writeObject(new Tokens(4,null));
				return "Thanks for your commment...";
			}
			case 99: {
				username=s[0];
				javaServer.map.put(s[0],null);
				query="update UserData set status=0,recentTime='" + new Date() + "' where username='" + username + "'";
				resultSet=db.getResultSet(query,1);
				outputToClient.writeObject(new Tokens(99,null));
				return "T";
			}
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}
}
