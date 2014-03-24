package utils;

import java.util.HashMap;
import java.util.Map;

import frontend.UserDataImpl;

public class SysInfo implements Runnable{
	private Runtime runtime = Runtime.getRuntime();
	private String lastDate;
	private static Map<String, String> data =
			new HashMap<String, String>();

	public SysInfo(){
		data.put("MemoryUsage", "/statistic/memoryUsage");
		data.put("TotalMemory", "/statistic/totalMemory");
		data.put("Time", "/statistic/time");
		data.put("CCU", "/statistic/ccu");
	}

	public static String getStat(String service){
		return ("["+VFS.readFile(data.get(service))+"]");
	}

	public void run(){
		for(String service:data.keySet()){
			lastDate=TimeHelper.getTime();
			if(service.equals("MemoryUsage")){
				VFS.writeToFile(data.get(service), String.valueOf((int) (runtime.totalMemory()-runtime.freeMemory())));
			}
			else if(service.equals("TotalMemory")){
				VFS.writeToFile(data.get(service), String.valueOf((int) (runtime.totalMemory())));
			}
			else if(service.equals("Time")){
				VFS.writeToFile(data.get(service), lastDate);
			}
			else if(service.equals("CCU")){
				VFS.writeToFile(data.get(service), String.valueOf(UserDataImpl.ccu()));
			}
		}
		while (true){
			TimeHelper.sleep(10000);
			for(String service:data.keySet()){
				lastDate=TimeHelper.getTime();
				if(service.equals("MemoryUsage")){
					VFS.writeToEndOfFile(data.get(service), ","+String.valueOf((int) (runtime.totalMemory()-runtime.freeMemory())));
				}
				else if(service.equals("TotalMemory")){
					VFS.writeToEndOfFile(data.get(service), ","+String.valueOf((int) (runtime.totalMemory())));
				}
				else if(service.equals("Time")){
					VFS.writeToEndOfFile(data.get(service), ","+lastDate);
				}
				else if(service.equals("CCU")){
					VFS.writeToEndOfFile(data.get(service), ","+String.valueOf(UserDataImpl.ccu()));
				}
			}
		}
	}
}