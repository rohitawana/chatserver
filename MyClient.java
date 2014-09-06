import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MyClient extends JFrame implements ActionListener
{
 public static void main(String...s)
 {new MyClient();}
 
 
 JLabel user;
 JTextField tf;
 JButton login;
 String userName;
 
 public MyClient()
 {
  super("Client");
  this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  this.setBounds(200,100,400,400);
  this.getContentPane().setBackground(new Color(102,50,120));
  this.setLayout(null);
  user=new JLabel("ENTER THE USER NAME :-");
  user.setForeground(Color.PINK);
  user.setBounds(30,50,150,40);
    
  tf=new JTextField();
  tf.setForeground(Color.RED);
  tf.setBounds(200,57,150,30);
  tf.addActionListener(this);
  tf.addKeyListener(new MyKeyAdapter(this));
  login=new JButton("LOGIN");
  login.setForeground(Color.BLUE);
  login.setBounds(140,170,90,40);
  login.addActionListener(this);
  
  add(user);add(tf);add(login);
  this.setVisible(true);
 }
 
 public void actionPerformed(ActionEvent ae)
 {
  //userName=tf.getText();
  //System.out.println(userName);
  //new ClientLoggedOn(userName);
  //this.setVisible(false);
  if(ae.getSource()==tf||ae.getActionCommand().equals("LOGIN"))
  {
   userName=tf.getText();
   System.out.println(userName);
   new ClientLoggedOn(userName);
   this.setVisible(false);
  }
 }
}

class MyKeyAdapter extends KeyAdapter
{
 MyClient mc;
 
 MyKeyAdapter(MyClient mc)
 {this.mc=mc;}
 
 public void keyPressed(KeyEvent ke)
 {
  this.mc.tf.setForeground(Color.red);
  int l=this.mc.tf.getText().length();
  if(l>=4&&l<10)
  {this.mc.tf.setForeground(Color.BLUE);}
  else if(l>=10)
  {this.mc.tf.setForeground(Color.GREEN);}
 }
}