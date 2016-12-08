import java.io.*;
import java.net.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.*;

public class getResult {
	
	public static void main(String[] args) {
		new getResult();
	}
	
	public getResult() {
		java.util.Scanner input=new java.util.Scanner(System.in);
		String query=input.nextLine();
		ExecutorService executor=Executors.newFixedThreadPool(3);
		
		//executor.execute(new translateFromJinshan(query));
		executor.execute(new translateFromYoudao(query));
		//executor.execute(new translateFromHaici(query));
		
		executor.shutdown();
		/*java.util.Scanner input=null,out1=null,out2=null,out3=null;
		URL url1=null,url2=null,url3=null;
		try {
			input =new java.util.Scanner(System.in);
			String word=input.nextLine();
			
			//String[] t=word.split(" ");
			//word=t[0];
			//for (int i=1; i<t.length; i++) word=word+"%20" + t[i];
			word=word.replaceAll("\\s", "%20");
			//System.out.println(word);
			/*url1=new URL("http://www.youdao.com/w/" + word + "/");	
			out1= new java.util.Scanner(url1.openStream());
			
			boolean once=true;
			
			while (out1.hasNext()) {
				String r=out1.nextLine();
				Pattern p=Pattern.compile("(.*)(<li>)(.*?)(</li>)(.*)");
				Matcher m=p.matcher(r);
				if (m.matches()) {
					while (m.matches() && once) {
						String t=m.group(3);
						if (t.charAt(0)!='<')
							System.out.println(t);
						else { System.out.println(word + " does not exists in youdao dict."); once=false; }
						r=out1.nextLine();
						m=p.matcher(r);
					}
					once=false;
				}
				//System.out.println(r);
			}
			
			url2=new URL("http://www.iciba.com/" + word);	
			out2= new java.util.Scanner(url2.openStream());
			String r;
			while (out2.hasNext()) {
				r=out2.nextLine();
				
				//匹配真实的单词
				Pattern p=Pattern.compile("\\s+(.*?)\\s+(</h1>)");
				Matcher m=p.matcher(r);
				if (m.matches()) { System.out.println(m.group(1)); break; }
			}
			while (out2.hasNext()) {
				r=out2.nextLine();
				//匹配属性
				Pattern p=Pattern.compile("(.*)(<span class=\"prop\">)(.*?)(</span>)");
				Matcher m=p.matcher(r);
				if (m.matches()) {
					String attr=m.group(3);
					System.out.print(attr + " ");
					r=out2.nextLine();
					r=out2.nextLine();
					p=Pattern.compile("(.*)(<span>)(.*?)(</span>)(.*)");
					m=p.matcher(r);
					
					//匹配释义
					while (m.matches()) {
						System.out.print(m.group(3));
						r=out2.nextLine();
						m=p.matcher(r);
					}
					System.out.println();
				}
				
			}
		}
		catch (MalformedURLException ex) {
			System.out.println("URL " + url2 + " not found.");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (input!=null) input.close();
		}*/
	}
}
