//	@Rampage



import java.lang.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;




//the class of the entity consisting of all the data about a task

class task_data{
	String date;
	String task_name;
	String start_time;
	String end_time;
	Scanner sc;


	public task_data(){
		
		sc=new Scanner(System.in);

		this.date=sc.nextLine();
		this.task_name=sc.nextLine();
		this.start_time=sc.nextLine();
		this.end_time=sc.nextLine();
	}


	public void initData(){

	}
}




//The class that handles database interaction

public class dbHandler{

	//Class variables

	ResultSet rs;
	Statement stm;
	PreparedStatement pstm;
	Connection con;
	Scanner sc;
	LocalDate date;


	//Class constructor

	public dbHandler(){

		try{
		Class.forName("org.sqlite.JDBC");
		con=DriverManager.getConnection("jdbc:sqlite:../databse/timebox");
		stm=con.createStatement();
		date=LocalDate.now();
		rs=stm.executeQuery("select * from ttimetable");

		pstm=con.prepareStatement("insert into ttimetable values(?,?,?,?)");
		}
		catch(Exception E){
			System.out.println("Error has occured in opening of database file.");
		}
	}



	//prints the list of items for that day

	public ResultSet getItems(){

		String query="select * from "+processDate(date.toString());
		try{
		rs=stm.executeQuery(query);

		}
		catch(Exception E){
			System.out.println("error has occured in retrieving items from database");
		}
		return rs;
	}


	public int getCount(){
		ResultSet RS=getItems();
		int count=0;
		try{
	
		while(RS.next()){
			count++;
		}

		
		}
		catch(Exception E){
			System.out.println("error has occured in getting count of entries");
		}
		return count;
	}


	//checks whether the table with today's date as name exists

	public boolean isTable(){
				
				String str=processDate(date.toString());
				ResultSet result;

				try{
				PreparedStatement pstatement=con.prepareStatement("SELECT name FROM sqlite_master WHERE type=? AND name=?");
				pstatement.setString(1,"table");
				pstatement.setString(2,str);
				result=pstatement.executeQuery();
				return result.next();
				}
				catch(Exception E){
					System.out.println("error has occured in finding existence of table");
				}
				return false;

}

	//prepares the date in required foramt(_year_month_day)

	public String processDate(String arg){
				
		return "_"+arg.substring(0,4)+"_"+arg.substring(5,7)+"_"+arg.substring(8,10);

	}


	//creates a table with the day's date as tablename

	public void createTable(){
			try{
			String str=processDate(date.toString());
			String query="create table "+str+"(date text,task_name text,start_time text,end_time text)";
			Statement stm=con.createStatement();
			stm.executeUpdate(query);
			}
			catch(Exception E){
				System.out.println("error has occured in creating table");
			}

	}
	
	//Adds item to the day's list

	public void add(){
		try{
		if(!isTable())
			createTable();

		String str=processDate(date.toString());
		String sqlQuery="insert into $tablename values(?,?,?,?)";
		String query=sqlQuery.replace("$tablename",str);
		PreparedStatement pstm=con.prepareStatement(query);
		
		System.out.println("statement prepared");					//debug code

		task_data td=new task_data();
		td.initData();
		
		pstm.setString(1,td.date);
		pstm.setString(2,td.task_name);
		pstm.setString(3,td.start_time);
		pstm.setString(4,td.end_time);
		
		pstm.executeUpdate();
		}
		catch(Exception E){
			System.out.println("error has occured in adding the item to database");
		}
	}


	//Adds item to the day's list but has arguments

	public void add(String task_name,String start_time,String end_time) throws Exception{
		try{
		if(!isTable())
			createTable();

		String str=processDate(date.toString());
		String sqlQuery="insert into $tablename values(?,?,?,?)";
		String query=sqlQuery.replace("$tablename",str);
		PreparedStatement pstm=con.prepareStatement(query);

		pstm.setString(1,str);
		pstm.setString(2,task_name);
		pstm.setString(3,start_time);
		pstm.setString(4,end_time);
		
		pstm.executeUpdate();
	}
	catch(Exception E){
		System.out.println("error has occured in adding item to the database");
	}
	}

}

