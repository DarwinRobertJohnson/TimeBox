//  @Rampage

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class myGui extends JFrame implements ActionListener{

    JLabel task_name;
    JTextField task_name_tf;
    JLabel start_time;
    JTextField start_time_field;
    JLabel end_time;
    JTextField end_time_field;
    JButton add_button;
    dbHandler db;

    public myGui(){

        try{

        db=new dbHandler();


        setLayout(new GridLayout(4,2,25,25));

        task_name=new JLabel("Task Name");
        task_name_tf=new JTextField(5);
        task_name_tf.setBounds(new Rectangle(50,100,200,30));

        start_time=new JLabel("Start time");
        start_time_field=new JTextField(15);

        end_time=new JLabel("End time");
        end_time_field=new JTextField(15);

        add_button=new JButton("Add");
        add_button.setBounds(new Rectangle(100,100,100,100));
        add_button.addActionListener(this);

        add(task_name);
        add(task_name_tf);

        add(start_time);
        add(start_time_field);

        add(end_time);
        add(end_time_field);

        add(add_button);

        setVisible(true);
        setSize(600,600);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        catch(Exception E){
            System.out.println("Error has occured in creation of gui object of writer");
        }
    }       



    //Checks if a given string is empty

    public boolean checkStringEmpty(String str){
        if(str.length()==0)
            return true;

        return false;
    }

    //Inserts data into the database

    public void insertData(){
        
        try{
        db.add(task_name_tf.getText(),start_time_field.getText(),end_time_field.getText());
        }
        catch(Exception e){
            System.out.println("101:data insertion into database not completed");
        }

    }

    //Handles the button click

    public void actionPerformed(ActionEvent e){
        boolean conditionSatisfactionEmpty=!(checkStringEmpty(task_name_tf.getText()) || checkStringEmpty(start_time_field.getText()) || checkStringEmpty(end_time_field.getText()));

        timeChecker tc=new timeChecker();

        boolean conditionSatisfactionTime   =   tc.checkTime(start_time_field.getText()) &&  tc.checkTime(end_time_field.getText());
        if(conditionSatisfactionEmpty && conditionSatisfactionTime){
            try{
                insertData();
            }catch(Exception exp){
                System.out.println("Data not inserted contact developer @Rampage");
            }
        }else{
            System.out.println("please fill all the text fields and use right time format");
        }
    }




}


public class writer{
    public static void main(String args[]){

        try{       
        myGui g=new myGui();
        }
        catch(Exception E){
            System.out.println("Error has occured in main block of writer.Code is Broken");
        }

    }
}