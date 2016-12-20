/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package dict;
import java.io.*;
/**
 *
 * @author answer
 */

public class Tokens implements Serializable{
    private int type;
    private String[] content;
    
    public Tokens(int type, String[] content){
        this.type = type;
        this.content = content;
    }
    public int getType() { return type;}
    public String[] getContent() {return content;}
}
