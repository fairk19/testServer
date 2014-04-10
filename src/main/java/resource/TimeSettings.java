package resource;

public class TimeSettings implements Resource{
	private static int exitTime = 1000;

	public static int getExitTime(){
		return exitTime;
	}
	
}
