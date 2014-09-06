import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class ClientLoggedOn extends JFrame implements ActionListener
{
 String userName;
 JScrollPane jp1,jp2,jp3;
 JTextArea jt1,jt2,jt3;
 JButton send,logout;
  
 Socket s;
 DataInputStream din;
 DataOutputStream dout;
 
 public ClientLoggedOn(String userName)
 {
  super("USER CHATING.");
  this.userName=userName;
  this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  this.setLayout(null);
  this.setBounds(200,100,700,540);
  
  jt1=new JTextArea();
  jt1.setEditable(false);
  jp1=new JScrollPane(jt1);
  jp1.setBorder(BorderFactory.createTitledBorder("SENDED TEXT"));
  jp1.setBounds(20,20,450,300);
  
  jt2=new JTextArea();
  jt2.setEditable(false);
  jp2=new JScrollPane(jt2);
  jp2.setBorder(BorderFactory.createTitledBorder("USER LIST"));
  jp2.setBounds(500,20,150,300);
  
  jt3=new JTextArea();
  jp3=new JScrollPane(jt3);
  jp3.setBorder(BorderFactory.createTitledBorder("ENTER THE TEXT TO SEND"));
  jp3.setBounds(20,350,450,120);
  
  
  send=new JButton("SEND");
  send.setForeground(Color.BLUE);
  send.setBounds(480,390,70,35);
  send.addActionListener(this);
  
  logout=new JButton("LOGOUT");
  logout.setForeground(Color.BLUE);  
  logout.setBounds(565,390,90,35);
  logout.addActionListener(this);
  
  makeConnection();
  
  try{clientChat();}
  catch(Exception e){System.out.println(e+"4");}
  
  add(jp1);add(jp2);add(jp3);add(send);add(logout);
  this.setVisible(true);
 }
 
 public void makeConnection()
 {
  try
  {
   s=new Socket("localhost",20);
   din=new DataInputStream(s.getInputStream());
   dout=new DataOutputStream(s.getOutputStream());
  }
  catch(Exception e){System.out.println(e+"1");}
 }
 
 public void clientChat()throws IOException
 {
  My m=new My(din,this);
  Thread t=new Thread(m);
  t.start();  
  try{dout.writeUTF(this.userName+" Logs On.");}
  catch(Exception e){System.out.println();}
 }
 
 public void actionPerformed(ActionEvent ae)
 {
  String str=ae.getActionCommand();
  if(str.equals("SEND"))
  {
   try
   {
   	dout.writeUTF(userName+" says :- "+jt3.getText());
    System.out.println(jt3.getText());
    dout.flush();
   	jt3.setText("");	
   }
   catch(Exception e){System.out.println(e+"2");}
  }
  
  if(str.equals("LOGOUT"))
  {
   try
   {
   	dout.writeUTF(userName+" LOGS OUT.");
    System.out.println(jt3.getText());
    dout.flush();
    System.exit(0);
   }
   catch(Exception e){System.out.println(e+"2");}
  }
 }
 
 public static void main(String...s)
 {new ClientLoggedOn("ROHIT");}
}

class My implements Runnable
{
 DataInputStream din;
 ClientLoggedOn clo;
 My(DataInputStream din,ClientLoggedOn clo)
 {this.din=din;this.clo=clo;}
 
 public void run()
 {
  String s2="";
  try
  {
   while(!s2.equals("stop"))
   {
    s2=din.readUTF();
    this.clo.jt1.setText(this.clo.jt1.getText()+s2+"\n");
   }
  }
  catch(Exception e){System.out.println(e+"3");}
 }
}