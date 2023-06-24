
import java.lang.*;


class timeChecker{

	public boolean checkCondition(int hour,int min){
		if(hour>=24 || hour<0)
			return false;

		if(min>=60 || min<0)
			return false;

		return true;
	}


	public boolean checkTime(String time){
		
		int hour=Integer.parseInt(new String(time.substring(0,2)));
		int minutes=Integer.parseInt(new String(time.substring(3,5)));
		
		return checkCondition(hour,minutes);
	}
}