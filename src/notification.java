import java.lang.*;
import javax.swing.*;
import java.awt.FlowLayout;


public class notification extends JFrame{

    JLabel task_name;


    //Default Constructor
    public notification(){

    }


    //Overloaded parametrised constructor
    public notification(String task_n){
        
        
        task_name=new JLabel(task_n);
        
        add(task_name);

        setVisible(true);
        setSize(720,144);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Notify(String task_n){
        //task_name=new JLabel("It Works!!");
        task_name=new JLabel(task_n);
        JLabel start_time=new JLabel("18:00");
        add(task_name);
        add(start_time);
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(720,144);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

