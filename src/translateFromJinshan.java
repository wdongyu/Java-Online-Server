import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class translateFromJinshan implements Runnable{
	private String query;
	private String result="Jinshan:\n";
	
	public translateFromJinshan(String query) {
		this.query=query;
	}
	
	public void run() {
		java.util.Scanner out=null;
		URL url=null;
		try {
			//input =new java.util.Scanner(System.in);
			//String word=input.nextLine();
			
			query=query.replaceAll("\\s", "%20");
			url=new URL("http://www.iciba.com/" + query);	
			out= new java.util.Scanner(url.openStream());
			//System.out.println("Jinshan:\n");
			String r;
			while (out.hasNext()) {
				r=out.nextLine();
				
				//匹配真实的单词
				Pattern p=Pattern.compile("\\s+(.*?)\\s+(</h1>)");
				Matcher m=p.matcher(r);
				if (m.matches()) {
					if (!query.equals(m.group(1)))
						//System.out.println(m.group(1)); 
						result=result+m.group(1)+"\n";
					break; 
				}
			}
			while (out.hasNext()) {
				r=out.nextLine();
				//匹配属性
				Pattern p=Pattern.compile("(.*)(<span class=\"prop\">)(.*?)(</span>)");
				Matcher m=p.matcher(r);
				if (m.matches()) {
					String attr=m.group(3);
					//System.out.print(attr + " ");
					result=result+attr+" ";
					r=out.nextLine();
					r=out.nextLine();
					p=Pattern.compile("(.*)(<span>)(.*?)(</span>)(.*)");
					m=p.matcher(r);
					
					//匹配释义
					while (m.matches()) {
						//System.out.print(m.group(3));
						result=result+m.group(3);
						r=out.nextLine();
						m=p.matcher(r);
					}
					result=result + "\n";
					//System.out.println();
				}	
			}
		}
		catch (MalformedURLException ex) {
			System.out.println("URL " + url + " not found.");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			System.out.println(result);
			if (out!=null) out.close();
		}
	}
}
