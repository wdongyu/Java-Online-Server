import java.io.*;

public class Tokens implements Serializable{
	/* 1：注册	用户名，密码 	
	   2：登陆 	用户名，密码
	   3：查词	单词，选择渠道(针对查询返回)
	   4：反馈	点赞对象
	   */
	private int type;
	private String[] content;
	
	public Tokens(int type, String[] content) {
		this.type=type;
		this.content=content;
	}
	
	public int getType() { return type; }
	public String[] getContent() { return content; }
}
