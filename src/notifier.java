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

    public taskList(){
        
        try{
        db=new dbHandler();
        list=db.getItems();
        tl=new timeHandler();
        nf=new notification();

        }
        catch(Exception E){
            System.out.println("Error has occured in taskList formation");
        }
    }

    public void checkTasks(){
        String task_name;
        try{
        while(list.next()){
            if(tl.checkEqualTime(list.getString("start_time"),tl.getCurrentTime())){
                task_name=list.getString("task_name");
                System.out.println(task_name);
                nf.Notify(task_name);
            }
        }
        }
        catch(Exception E){
            System.out.println("Error has occured in task checking.Possible cause no schedule for today");
        }

    }

}


public class notifier{
    public static void main(String[] args){ 

        taskList l=new taskList();

        try{
        while(true){
            l.checkTasks();
            TimeUnit.MINUTES.sleep(1);
        }
        }
        catch(Exception E){
            System.out.println("Error has occured in main block of Notifier.Code is broken!");
        }

    }
}