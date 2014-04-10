package utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

public class CookieDescriptor{
	private Map<String,String> nameToCookie=
			new HashMap<String,String>();

	public CookieDescriptor(Cookie coo[]){
		for(int f=0;f<coo.length;f++){
			nameToCookie.put(coo[f].getName(), coo[f].getValue());
		}
	}

	public String getCookieByName(String name){
		String ans=null;
		if (nameToCookie.containsKey(name))
			ans=nameToCookie.get(name);
		return ans;
	}
}