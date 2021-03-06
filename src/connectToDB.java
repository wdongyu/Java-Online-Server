import java.sql.*;
import java.util.*;

public class connectToDB {
	//private String query;
	private ResultSet resultSet=null;
	Connection dbCon;
	
	public connectToDB() {
		try {
			String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=java_db";
			String userName="java";
			String userPwd="java";
			Class.forName(driverName);
			dbCon=DriverManager.getConnection(dbURL,userName,userPwd);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("连接数据库成功");
		//this.query=query;
	}
	
	public ResultSet getResultSet(String query, int chooseDB) { 
		try {
			Statement statement=dbCon.createStatement();
			String showResult;
			if (chooseDB==1)
				showResult="select * from UserData";
			else
				showResult="select * from PraiseCount";
			//Scanner input=new Scanner(System.in);
			//String query=input.nextLine();
			
			/* 判断属于哪种语句 */
			String[] token=query.split(" ");
			if (token[0].equalsIgnoreCase("select"))
				resultSet=statement.executeQuery(query);
			else {
				statement.executeUpdate(query);
				resultSet=statement.executeQuery(showResult);
			}
			/*ResultSetMetaData rsmd=resultSet.getMetaData();
			while (resultSet.next()) {
				for (int i=1; i<=rsmd.getColumnCount();i++) {
					System.out.print(resultSet.getString(i) + "\t");
				}
				System.out.println();
			}*/
			
		}
		catch (Exception e) {
			e.printStackTrace();
			//System.out.println("连接失败");
		}
		finally {
			return resultSet;
		}
		}
}

