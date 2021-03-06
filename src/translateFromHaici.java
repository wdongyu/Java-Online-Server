import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class translateFromHaici implements Callable<String>{
	private String query;
	private String path;
	private Pattern p;
	private Matcher m;
	public String result="";
	private final int NUM_OF_PHRASE=3;
	private final int NUM_OF_SENTENCE=3;
	
	public translateFromHaici(String query) {
		this.query=query;
	}
	
	//public void run() {
	public String call() {
		java.util.Scanner out=null;
		URL url=null;
		try {
			path=query.replaceAll("\\s", "%20");
			url=new URL("http://www.dict.cn/" + path);	
			//System.out.println(url.toString());
			out= new java.util.Scanner(url.openStream());
			int count=0;
			String r;
			
			/* 匹配单词释义 */
			boolean once=true;
			while (out.hasNext()) {
				r=out.nextLine();
				//System.out.println(r);
				p=Pattern.compile("(.*)(<li>)(.*?)(</li>)(.*)");
				m=p.matcher(r);
				while (m.matches()) {	
					//once=false;
					String content=m.group(3);
					/* 匹配属性 */
					Pattern p1=Pattern.compile("(.*)(<span>)(.*?)(</span>)(.*)");
					Matcher m1=p1.matcher(content);
					if (m1.matches()) {
						result=result + "\n" + m1.group(3);
					}
					
					/* 匹配释义 */
					Pattern p2=Pattern.compile("(.*)(<strong>)(.*?)(</strong>)(.*)");
					Matcher m2=p2.matcher(content);
					if (m2.matches()) {
						//once=true;
						//b2=true;
						//System.out.println(m2.group(3));
						result=result + " " + m2.group(3);
						once=false;
						
					}
					//if (!b2) break;
					r=out.nextLine();
					m=p.matcher(r);
					//b=true;
				}
				
				//Pattern temp_p=Pattern.compile("(.*)(<li style=\"padding-top: 25px;\">)(.*)");
				//Matcher temp_m=temp_p.matcher(r);
				if (!once)	break;
			}
			
			/* 匹配例句 */
			count=0;
			String t1="";
			while (out.hasNext()) {
				r=out.nextLine();
				p=Pattern.compile("(.*)(<li>)(.*?)(</em>)(.*?)(<i)(.*)");
				m=p.matcher(r);
				if (m.matches()) {
					String content=m.group(3)+m.group(5);
					Pattern p1=Pattern.compile("(.*?)(<em class=hot>)(.*?)");
					Matcher m1=p1.matcher(content);
					if (m1.matches())
						t1=t1 + "\n" + m1.group(1) + m1.group(3);
					
					r=out.nextLine();
					Pattern p2=Pattern.compile("(.*)(<span>)(.*?)(</span>)(.*)");
					Matcher m2=p2.matcher(r);
					if (m2.matches())
						t1=t1 + "\n" + m2.group(3);
					count++;
				}
				if (count>=NUM_OF_SENTENCE) break;
			}
			
			/* 匹配短语 */
			count=0;
			once=false;
			String t2="";
			while (out.hasNext()) {
				r=out.nextLine();
				p=Pattern.compile("(.*)(<li>)(<a href=)(.*?)(</li>)(.*)");
				m=p.matcher(r);
				while (m.matches()) {
					once=true;
					/*p=Pattern.compile("(.*)(<li>)(.*?)(</li>)(.*)");
					m=p.matcher(r);
					while (!m.matches()) {
						r=out.nextLine();
						m=p.matcher(r);
					}
					while (m.matches()) {*/
						String content=m.group(4);

						Pattern p1=Pattern.compile("(.*)(>)(.*?)(</a>)(.*)");
						Matcher m1=p1.matcher(content);
						if (m1.matches()) {
							//result=result + "\n" + m1.group(3);
							t2=t2 + "\n" + m1.group(3);
						}
						
						Pattern p2=Pattern.compile("(.*)(</a>)(.*?)");
						Matcher m2=p2.matcher(content);
						if (m2.matches()) {
							//result=result + " " + m2.group(3);
							t2=t2 + " " + m2.group(3);
							count++;
						}
						
						if (count>=NUM_OF_PHRASE) break;
						r=out.nextLine();
						m=p.matcher(r);
				}
				if (once) break;
			}
			result=result + "\n" + t2 + "\n" + t1;
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
