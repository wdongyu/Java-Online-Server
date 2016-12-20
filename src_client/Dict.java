/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

/**
 *
 * @author zhouyichen
 */


public class Dict {

    /**
     * @param args the command line arguments
     */
    class Entry{
        String word;
        String explanation;
    }
    static Map dict = new TreeMap();
    static List<String> allWord = new ArrayList<>();
    private static final Integer NUMofLIST = 5;
    public static String account, password;
    public int[] likes;
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Dict d = new Dict();
        d.loadDoc("minidict.txt");
        //d.loadDoc("dictionarynew.txt");
        //BuildFrame();
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
        while (!login.succeed){
            Thread.sleep(20);
        }
        socket = login.getSocket();
        login.dispose();
        //d.likes = new int[3];
        DictJFrame.userList = login.getUserList();
        DictJFrame DictGUI = new DictJFrame();
        //System.out.println("user: " + DictGUI.userList.length);
        DictGUI.BuildFrame();
        DictGUI.setTitle("Dictionary");
    }
    
    public boolean loadDoc(String docName) throws FileNotFoundException{
        /*
        java.io.File file = new java.io.File(docName);
        if (file.exists()){
            System.out.println("File already exits");
        }
        */
        long tstart = System.currentTimeMillis();
        java.io.File file = new java.io.File(docName);
        if (!file.exists()){
            System.out.println("file not open");
            return false;
        }
        Scanner input = new Scanner(file);
        
        while (input.hasNext()){
            String line = input.nextLine();
            String[] tokens = line.split("\t", 2);
            Entry e = new Entry();
            allWord.add(tokens[0]);
            e.word = tokens[0];
            e.explanation = tokens[1];
            dict.put(tokens[0], e);
        }
        /*
        //Test
        Scanner cin = new Scanner(System.in);
        String test = cin.nextLine();
        if (dict.containsKey(test)){
             Entry e = (Entry)dict.get(test);
            System.out.println(e.explanation);
        }else {
            System.out.println("Word No Found");
        }
        */               
        long tend = System.currentTimeMillis();
        System.out.print(allWord.size() + " words");
        System.out.println("(Init Time: " + (tend-tstart) + "ms)");
        return true;
    }
    public static String association(String sourStr){
        String result = "";
        if (!dict.containsKey(sourStr)){
            int p = 0;
            while(p < allWord.size()){
                if (ld(allWord.get(p),sourStr)<=sourStr.length()/4){
                    result += "Do you mean '" + allWord.get(p) + "'?\n";
                    System.out.println("ld: " + allWord.get(p));
                    break;
                }
                p++;
            }
        }
        
        for(int i =0; i<allWord.size(); i++){
            if (allWord.get(i).startsWith(sourStr)){
                for (int j = 0; j<NUMofLIST; j++){
                    result = result + allWord.get(i+j) + "\n";
                }
                break;
            }else if (allWord.get(i).compareTo(sourStr)>0){
                break;
            }
        }
        return result;
    }
  
    /*
    1. 注册 (成功)
    2. 登录 (在线的人)
    3. 查词 (解释;发送多个请求,不同来源分开)
    4. 反馈 (点赞对象)
    */
    public static Socket socket;
    public static void Send(Tokens tokens){
        ObjectOutputStream toServer;
        try{
            toServer = new ObjectOutputStream(socket.getOutputStream());
            toServer.writeObject(tokens);
            toServer.flush();
        }catch(Exception e){
            //toServer = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Failed to Send!");
            e.printStackTrace();
        }
    }
    
    public static Tokens Receive() {
        ObjectInputStream fromServer;
        try {
            fromServer = new ObjectInputStream(socket.getInputStream());
            Object so = fromServer.readObject();
            if (so!=null && so instanceof Tokens)
                System.out.println(so.toString());
            //System.out.println("tokens size:" + ((Tokens)so).getContent().length);
            return (Tokens)so;
        } catch (Exception e) {
            //toServer = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Failed to Receive!");
            e.printStackTrace();
            return null;
        }
    }
    public static void Client(int type, String content) {
        ObjectInputStream fromServer;
        ObjectOutputStream toServer;
        InetAddress address = null;// = InetAddress.getByName(args[i]);
        try {
            Socket socket = new Socket("114.212.130.245", 9877);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
            String sout;
            System.out.println("to Server: " + content);
            String[] s = new String[1];
            s[0] = content;
            Tokens t = new Tokens(1, s);
            toServer.writeObject(t);
            toServer.flush();
       
            ///sout = fromServer.readLine();
            Object so = fromServer.readObject();
            if (so!=null && so instanceof Tokens)
                System.out.println(so.toString());
            
        } catch (Exception ex) {
            System.out.println("failed");
            ex.printStackTrace();
        }
    }
    public static void logout(){
        String[] logout = {account, password};
        Tokens logoutTokens = new Tokens(99, logout);
        Dict.Send(logoutTokens);
        System.out.println("Logout.");
        try{
            socket.close();
        }catch(Exception e){
            e.printStackTrace();;
        }
    }

    public static String Search(String sourStr){
        System.out.println("searching...");
        
        if (dict.containsKey(sourStr)){
            Entry e = (Entry)dict.get(sourStr);
            //Client(sourStr);
            return e.explanation;
        }else{
            return "Waiting";
        }
    }
    private static int min(int one, int two, int three) {
        int min = one;
        if(two < min) {
            min = two;
        }
        if(three < min) {
            min = three;
        }
        return min;
    }

    public static int ld(String str1, String str2) {
        int d[][];
        int n = str1.length();
        int m = str2.length();
        int i;
        int j; 
        char ch1;
        char ch2;
        int temp;    //记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if(n == 0) {
            return m;
        }
        if(m == 0) {
            return n;
        }
        d = new int[n+1][m+1];
        for(i=0; i<=n; i++) {
            d[i][0] = i;
        }
        for(j=0; j<=m; j++) {
            d[0][j] = j;
        }
        for(i=1; i<=n; i++) {
            ch1 = str1.charAt(i-1);
            for(j=1; j<=m; j++) {
                ch2 = str2.charAt(j-1);
                if(ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                d[i][j] = min(d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1]+temp);
            }
        }
        return d[n][m];
    }
}

