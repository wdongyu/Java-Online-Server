import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class translateFromYoudao implements Callable<String>{
	private String query;
	private Pattern p;
	private Matcher m;
	private final int NUM_OF_PHRASE=3;
	public String result="";
	
	public translateFromYoudao(String query) {
		this.query=query;
	}
	
	//public void run() {
	public String call() {
		java.util.Scanner out=null,tempOut=null;
		URL url=null;
		try {
			query=query.replaceAll("\\s", "%20");
			url=new URL("http://www.youdao.com/w/" + query + "/");	
			out= new java.util.Scanner(url.openStream());
			tempOut=new java.util.Scanner(url.openStream());
			boolean once=true;
			int count=0;
			//System.out.println("\nYoudao:");
			//result=result+"\nYoudao:\n";
			
			//匹配单词解释
			while (out.hasNext()) {
				String r=out.nextLine();
				p=Pattern.compile("(.*)(<li>)(.*?)(</li>)(.*)");
				m=p.matcher(r);
				if (m.matches()) {
					while (m.matches() && once) {
						String t=m.group(3);
						if (t.charAt(0)!='<')
							//System.out.println(t);
							result=result + t + "\n";
						else { System.out.println(query + " does not exists in youdao dict."); once=false; }
						r=out.nextLine();
						m=p.matcher(r);
					}
					once=false;
					break;
				}
			}
			//result=result + "\n";
			
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
						//System.out.print("\n" + m.group(3));
						result=result + "\n" + m.group(3);
						r=tempOut.nextLine();
						//System.out.print(" " + r.trim());
						result=result + " " + r.trim();
					}
					r=tempOut.nextLine();
					count++;
				}
				if (count!=0) break;
			}
			result=result + "\n";
			
			//匹配句子
			count=0;
			while (out.hasNext()) {
				String r=out.nextLine();
				p=Pattern.compile("(.*)(<p class=\"additional\">)(.*?)(</p>)(.*)");
				m=p.matcher(r);
				//System.out.println(r);
				if (m.matches() && count<2*NUM_OF_PHRASE+1) {
					if (m.group(3).charAt(0)!='<') 
						//System.out.println(m.group(3));
						result=result + "\n" + m.group(3);
					count++;
				}
				if (count==2*NUM_OF_PHRASE+1) break;
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
