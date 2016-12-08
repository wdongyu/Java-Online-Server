import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class translateFromYoudao implements Runnable{
	private String query;
	private Pattern p;
	private Matcher m;
	private final int NUM_OF_PHRASE=3;
	
	public translateFromYoudao(String query) {
		this.query=query;
	}
	
	public void run() {
		java.util.Scanner out=null,tempOut=null;
		URL url=null;
		try {
			//input =new java.util.Scanner(System.in);
			//String word=input.nextLine();
			//word=word.replaceAll("\\s", "%20");
			
			query=query.replaceAll("\\s", "%20");
			url=new URL("http://www.youdao.com/w/" + query + "/");	
			out= new java.util.Scanner(url.openStream());
			tempOut=new java.util.Scanner(url.openStream());
			//InputStreamReader inputStreamReader = new InputStreamReader(url.openStream(), "utf-8");  
			//BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			/*StringBuffer buffer = new StringBuffer();  
			String str = null;  
			while (out.hasNext()) {
				str=out.next();
				buffer.append(str);  
			}
			str=buffer.toString();
			//System.out.println(str);

			//匹配单词解释
			p=Pattern.compile("(.*)(</h2>)(.*?)(</ul>)(.*)");
			m=p.matcher(str);
			if (m.matches()) {
				String temp=m.group(3);
				System.out.println(m.group(3));
				p=Pattern.compile("(<li>)(.*?)(</li>)");
			    m=p.matcher(temp);
			    while(m.find()) {
			    	System.out.println(m.group(2));
			    }
			}
			
			//匹配短语解释
			p=Pattern.compile("(.*)(<divid=\"transformToggle\">)(.*?)(<divclass=\"more\">)(.*)");
			m=p.matcher(str);
			if (m.matches()) {
				String temp=m.group(3);
				p=Pattern.compile("(<.*>)(.*?)(</a></span>)(.*?)(/p)");
			    m=p.matcher(temp);
			    while(m.find()) {
			    	System.out.println(m.group(2) + " " + m.group(4));
			    }
				//System.out.println(m.group(3));
			}*/
			boolean once=true;
			System.out.println("\nYoudao:");
			
			//匹配单词解释
			while (out.hasNext()) {
				String r=out.nextLine();
				p=Pattern.compile("(.*)(<li>)(.*?)(</li>)(.*)");
				m=p.matcher(r);
				if (m.matches()) {
					while (m.matches() && once) {
						String t=m.group(3);
						if (t.charAt(0)!='<')
							System.out.println(t);
						else { System.out.println(query + " does not exists in youdao dict."); once=false; }
						r=out.nextLine();
						m=p.matcher(r);
					}
					once=false;
					break;
				}
			}
			
			//匹配句子
			int count=0;
			while (out.hasNext()) {
				String r=out.nextLine();
				p=Pattern.compile("(.*)(<p class=\"additional\">)(.*?)(</p>)(.*)");
				m=p.matcher(r);
				//System.out.println(r);
				if (m.matches() && count<2*NUM_OF_PHRASE+1) {
					if (m.group(3).charAt(0)!='<') 
						System.out.println(m.group(3));
					count++;
				}
				if (count==2*NUM_OF_PHRASE+1) break;
			}
			
			//匹配短语解释
			while (tempOut.hasNext()) {
				String r=tempOut.nextLine();
				p=Pattern.compile("(.*)(<div id=\"wordGroup\" class=\"trans-container tab-content hide more-collapse\">)(.*)");
				m=p.matcher(r);
				count=0;
				while (m.matches() && count<NUM_OF_PHRASE) {
					r=tempOut.nextLine();
					r=tempOut.nextLine();
					//System.out.println(r);
					p=Pattern.compile("(.*)(>)(.*?)(</a>)(.*)");
					m=p.matcher(r);
					if (m.matches()) {
						System.out.print("\n" + m.group(3));
						r=tempOut.nextLine();
						System.out.print(" " + r.trim());
					}
					r=tempOut.nextLine();
					count++;
				}
				if (count!=0) break;
			}
			
		}
		catch (MalformedURLException ex) {
			System.out.println("URL " + url + " not found.");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (out!=null) out.close();
		}
	}
}
