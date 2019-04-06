/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package server1;

/**
 *
 * @author abc
 */
package myattempt;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import test1.DBTest;

/**
 *
 * @author abc
 */
public class Server2 {
    public static void main(String[] args) {
        try {
            ServerSocket server= new ServerSocket(5000);
			while(true){
				System.out.println("Server is waiting for the request");
                Socket socket= server.accept();
				System.out.println("Connection established");
				new MyThread(socket);
                                new WriteThread(socket);
			}
			
        } catch (IOException ex) {
           // Logger.getLogger(Server1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


class MyThread extends Thread{
	Socket socket;
	DataInputStream din;
	MyThread(Socket s){
		socket=s;
		start();
	}
	public void run(){
		
		try{
			din= new DataInputStream(socket.getInputStream());
                        String data="";
			  while(!(data.equals("end"))){
				  try{
				  data= din.readUTF();
				  System.out.println(data);
				  }catch(Exception e){
					  /*System.out.println("caught7");
					  try{
						  din.close();
					  }catch(Exception ex){
						  System.out.println("caught8");
					  }*/
				  }
				
  
              
			  }
			  try{
				  din.close();
			  }catch(Exception e){
				  System.out.println("caught9");
			  }
		}catch(Exception e2){
			
			e2.printStackTrace();
		}
	}
}


class WriteThread extends Thread{
    Socket socket;
    Scanner sc;
    DataOutputStream doutserv;
    public WriteThread(Socket s){
        socket=s;
        try {
            doutserv= new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Caught");
        }
        start();
    }
    public void run(){
        String msg="";
        sc= new Scanner(System.in);
        DBTest db=new DBTest();
        ArrayList<String> str;
        str=db.getData();
        for(String s:str){
        try {
            //System.out.println(msg);
            System.out.println(s);
            doutserv.writeUTF(s);
            //doutserv.flush();
        }catch (IOException ex) {
            System.out.println("Caughtewdwed");
            try {
                doutserv.close();
            } catch (IOException ex1) {
                System.out.println("caught1");
            }
        }
        }
        // Logger.getLogger(WriteThread.class.getName()).log(Level.SEVERE, null, ex);
        try {
            doutserv.writeUTF("end");
        } catch (IOException ex) {
            Logger.getLogger(WriteThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            doutserv.close();
        } catch (IOException ex) {
            System.out.println("caught2");
        }
    }
}