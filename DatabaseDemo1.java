/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattempt;

/**
 *
 * @author KOUSHIKI
 */
public class DatabaseDemo1 {

    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    public DatabaseDemo1(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/awpproject","root","");
        }catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        }catch (SQLException ex) {
            ex.printStackTrace();
                   }
        
   }
    public void writeData(){
        Scanner sc= new  Scanner(System.in);
        System.out.println("Enter name of student:");
        String name= sc.nextLine();
        System.out.println("Enter age of student:");
        int age= sc.nextInt();
        System.out.println("Enter gender of student:");
        String gender= sc.next();
       String query="insert into student(name,age,gender) "+
                "values("                
                +"'"+name+"'"+","+"'"+age+"'"+","+"'"+gender+"'"+")";  
         try {
             st= con.createStatement();
             st.executeUpdate(query);
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
    }
