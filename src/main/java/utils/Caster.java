package utils;

import java.util.Map;

public class Caster{
	public static String[] castKeysToStrings(Map<String, ?> map){
		String[] mas = new String[map.size()];
		int count=0;
		for(String element : map.keySet()){
			mas[count] = element;
			count+=1;
		}
		return mas;
	}
}
