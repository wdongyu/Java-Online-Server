import java.io.*;
import java.net.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.*;

public class getResult {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new getResult();
	}
	
	public getResult() throws InterruptedException, ExecutionException {
		java.util.Scanner input=new java.util.Scanner(System.in);
		String query=input.nextLine();
		ExecutorService executor=Executors.newFixedThreadPool(3);
		translateFromJinshan t1=new translateFromJinshan(query);
		translateFromYoudao t2=new translateFromYoudao(query);
		translateFromHaici t3=new translateFromHaici(query);
		//executor.execute(t1);
		//executor.execute(t2);
		//executor.execute(t3);
		
		//executor.execute(new translateFromJinshan(query));
		//executor.execute(new translateFromYoudao(query));
		//executor.execute(new translateFromHaici(query));
		
		Future<String> f1=executor.submit(t1);
		Future<String> f2=executor.submit(t2);
		Future<String> f3=executor.submit(t3);

		executor.shutdown();
		System.out.println(f1.get() +  f2.get() + f3.get());
		input.close();
	}
}
