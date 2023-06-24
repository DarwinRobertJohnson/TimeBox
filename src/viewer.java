//  @Rampage

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Viewer extends JFrame{
    JTable table;
    dbHandler db;
    int count;

    public void init(String[][] list,ResultSet rs) throws Exception{
        while(rs.next()){
            for(int i=0;i<count;i++){
                for(int j=0;j<3;j++){
                    list[i][j]=rs.getString(j+2);
                }
            }
        }
    }



    public Viewer() throws Exception{
        
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
}



public class viewer{


    public static void main(String[] args) throws Exception{

        Viewer vw=new Viewer();


    }
}