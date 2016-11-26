import java.io.*;
import java.net.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class getResult {
	
	public static void main(String[] args) {
		new getResult();
	}
	
	public getResult() {
		java.util.Scanner input=null,out=null;
		URL url=null;
		try {
			input =new java.util.Scanner(System.in);
			String word=input.nextLine();
			
			//String[] t=word.split(" ");
			//word=t[0];
			//for (int i=1; i<t.length; i++) word=word+"%20" + t[i];
			word=word.replaceAll("\\s", "%20");
			//System.out.println(word);
			url=new URL("http://www.youdao.com/w/" + word + "/");
			
			out= new java.util.Scanner(url.openStream());
			
			boolean once=true;
			
			while (out.hasNext()) {
				String r=out.nextLine();
				Pattern p=Pattern.compile("(.*)(<li>)(.*?)(</li>)(.*)");
				Matcher m=p.matcher(r);
				if (m.matches()) {
					while (m.matches() && once) {
						System.out.println(m.group(3));
						r=out.nextLine();
						m=p.matcher(r);
					}
					once=false;
				}
				
				//System.out.println(r);
			}
		}
		catch (MalformedURLException ex) {
			System.out.println("URL " + url + " not found.");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (input!=null) input.close();
		}
	}
}
