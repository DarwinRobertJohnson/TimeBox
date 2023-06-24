import java.lang.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;  
import java.time.format.DateTimeFormatter; 
import java.sql.ResultSet;


class timeHandler{
    public String getCurrentTime(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime=LocalTime.now();
        return dtf.format(localTime);
    }

    public boolean checkEqualTime(String time1,String time2){
        return time1.equals(time2);
    }
}

class taskList{
    dbHandler db;
    ResultSet list;
    timeHandler tl;
    notification nf;

    public taskList() throws Exception{
        db=new dbHandler();
        list=db.getItems();
        tl=new timeHandler();
        nf=new notification();

        while(list.next()){
            if(tl.checkEqualTime(list.getString("start_time"),tl.getCurrentTime()))
                nf.Notify();
        }
    }

    public void checkTasks() throws Exception{
        while(list.next()){
            if(tl.checkEqualTime(list.getString("start_time"),tl.getCurrentTime()))
                nf.Notify();
        }
    }

}


public class notifier{
    public static void main(String[] args) throws Exception{ 

        taskList l=new taskList();

        while(true){
            System.out.println("Waiting done");
            l.checkTasks();
            System.out.println("Waiting started");
            TimeUnit.MINUTES.sleep(1);
        }

    }
}