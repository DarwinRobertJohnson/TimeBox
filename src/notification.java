import java.lang.*;
import javax.swing.*;


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

    public void Notify(){
        task_name=new JLabel("It Works!!");

        add(task_name);

        setVisible(true);
        setSize(720,144);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

