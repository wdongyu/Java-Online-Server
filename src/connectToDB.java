import java.sql.*;
import java.util.*;

public class connectToDB {
	public static void main(String[] args) {
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=java_db";
		String userName="java";
		String userPwd="java";
		
		try {
			Class.forName(driverName);
			Connection dbCon=DriverManager.getConnection(dbURL,userName,userPwd);
			System.out.println("连接数据库成功");
			//dbCon.close();
			
			Statement statement=dbCon.createStatement();
			String showResult="select * from UserData";
			ResultSet resultSet;
			Scanner input=new Scanner(System.in);
			String query=input.nextLine();
			
			/* 判断属于哪种语句 */
			String[] tokens=query.split(" ");
			if (tokens[0].equalsIgnoreCase("select"))
				resultSet=statement.executeQuery(query);
			else {
				statement.executeUpdate(query);
				resultSet=statement.executeQuery(showResult);
			}
			ResultSetMetaData rsmd=resultSet.getMetaData();
			while (resultSet.next()) {
				for (int i=1; i<=rsmd.getColumnCount();i++) {
					System.out.print(resultSet.getString(i) + "\t");
				}
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("连接失败");
		}
		}
}

