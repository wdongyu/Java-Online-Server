import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class translateFromJinshan implements Callable<String>{
	private String query;
	private String path;
	private Pattern p;
	private Matcher m;
	public String result="\nJinshan:\n";
	private final int NUM_OF_INTER=5;
	private final int NUM_OF_SENTENCE=3;
	
	public translateFromJinshan(String query) {
		this.query=query;
	}
	
	//public void run() {
	public String call() {
		java.util.Scanner out=null;
		URL url=null;
		try {
			//input =new java.util.Scanner(System.in);
			//String word=input.nextLine();
			
			path=query.replaceAll("\\s", "%20");
			url=new URL("http://www.iciba.com/" + path);	
			out= new java.util.Scanner(url.openStream());
			int count=0;
			//System.out.println("Jinshan:\n");
			String r;
			/* 匹配真实单词 */
			while (out.hasNext()) {
				r=out.nextLine();				
				p=Pattern.compile("\\s+(.*?)\\s+(</h1>)");
				m=p.matcher(r);
				if (m.matches()) {
					if (!query.equals(m.group(1)))
						//System.out.println(m.group(1)); 
						result=result+m.group(1)+"\n";
					break; 
				}
			}
			
			/* 匹配单词释义 */
			count=0;
			while (out.hasNext()) {
				boolean b=false;
				r=out.nextLine();
				//匹配属性
				p=Pattern.compile("(.*)(<span class=\"prop\">)(.*?)(</span>)");
				m=p.matcher(r);
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
					b=true;
					//System.out.println();
				}
				
				//Pattern p1=Pattern.compile("(.*)(<li class=\"change clearfix\">)(.*)");
				Pattern p1=Pattern.compile("(.*)(</p>)(.*)");
				Matcher m1=p1.matcher(r);
				if (b && m1.matches())
					break;
			}
			
			/* 匹配句子 */
			count=0;
			while (out.hasNext()) {
				r=out.nextLine();
				p=Pattern.compile("(.*)(<p class='family-english'>)(.*)");
				m=p.matcher(r);
				if (m.matches()) {
					r=out.nextLine();
					p=Pattern.compile("(.*)(<span>)(.*?)(</span>)(.*)");
					m=p.matcher(r);
					if (m.matches()) {
						/*String t=m.group(3);
						p=Pattern.compile("(.*?)(<b>)(.*)(</b>)(.*?)");
						m=p.matcher(t);
						if (m.matches()) 
							result=result + "\n" + m.group(1) + query + m.group(5);
						else*/
						String t=m.group(3).replaceAll("<b>", "");
						t=t.replaceAll("</b>", "");
						result=result + "\n" + t;					
						//result=result + "\n" + m.group(3);
					}
					r=out.nextLine();
					p=Pattern.compile("(.*)(<p class='family-chinese size-chinese'>)(.*?)(</p>)(.*)");
					m=p.matcher(r);
					while (!m.matches()) {
						r=out.nextLine();
						//System.out.println(r);
						m=p.matcher(r);
					}
					result=result + "\n" + m.group(3);
					count++;
				}
				if (count>=NUM_OF_SENTENCE) break;
			}
		}
		catch (MalformedURLException ex) {
			System.out.println("URL " + url + " not found.");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			//System.out.print(result);
			if (out!=null) out.close();
			return result;
		}
	}
}
