//  @Rampage

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Viewer extends JFrame{
    JTable table;
    dbHandler db;
    int count;

    public void init(String[][] list,ResultSet rs){


        try{
        while(rs.next()){
            for(int i=0;i<count;i++){
                for(int j=0;j<3;j++){
                    list[i][j]=rs.getString(j+2);
                }
            }
        }
        }
        catch(Exception E){
            System.out.println("Error has occured in intialisation");
        }
    
    
    }



    public Viewer(){
        
        try{
        super("Viewer");
        
        db=new dbHandler();

        count=db.getCount();

        String list[][]=new String[count][3];
        ResultSet rs=db.getItems();
        String[] columns={"Task","Start","End"};
        init(list,rs);

        table=new JTable(list,columns);
        table.setEnabled(false);

        add(table);
        setVisible(true);
        setSize(600,600);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        catch(Exception E){
            System.out.println("Error has occured in object creation");
        }


    }
}



public class viewer{


    public static void main(String[] args){

        try{
        Viewer vw=new Viewer();
        }
        catch(Exception E){
            System.out.println("Error has occured in main block of viewer.Code is Broken!");
        }

    }
}