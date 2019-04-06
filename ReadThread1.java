/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattempt;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static myattempt.myattempt.a;
import test1.DBTest;

/**
 *
 * @author KOUSHIKI
 */
public class ReadThread1 extends Thread{
    Socket socket;
    DataInputStream din;
    myattempt jj;
    ReadThread1(Socket s,myattempt j){
        socket=s;
        try {
            din= new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Caught4");
        }
        jj=j;
		start();
    }
    public void run(){
        String data="";
            try {
                do{
                data= din.readUTF();
                //jj.update(data);
                if(!data.equals("end")){
                String str[];
                str = data.split(":");
                int id=Integer.parseInt(str[0]);
                int age=Integer.parseInt(str[2]);
                 Student s = new Student(id,str[1],age,str[3]);
                a.add(s);
                //jj.jComboBox1.addItem(str[1]);
                jj.addToComboBox(id+" "+str[1]);
                }
                //System.out.println(data);
                
                }while(!data.equals("end"));
            } catch (IOException ex) {
                System.out.println("caught5");
                try {
                    din.close();
                } catch (IOException ex1) {
                    System.out.println("caught6");
                }
            }
            
        
        try {
                    din.close();
                } catch (IOException ex1) {
                    System.out.println("caught");
                }
    }
}

