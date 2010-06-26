package bingo.vasms.bizstreet.test;

import java.util.Date;
import java.util.Random;

import bingosoft.jcf.util.StringUtil;
import bingosoft.jcf.util.TimerUtil;
import bingosoft.jcf.util.uid.UUIDGenerator;

public class TestSubNember {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestSubNember t = new TestSubNember();
		System.out.println(t.getSubNember("root"));
		
		System.out.println("root = "+"root,".substring(0,"root,".length()-1));
		
		String ss = "4243,2133,3244";
		String[] s = StringUtil.split(ss,",");
		for (String string : s) {
			System.out.println(string);
		}
	}
	
	public synchronized String getSubNember(String userName) {
        String subNumber="";
        String now =TimerUtil.dateToStr(new Date());
		//String now=(String)((new Date()).toLocaleString());
        subNumber=userName+now;
		subNumber=subNumber.replace("-","");
		subNumber=subNumber.replace(" ",""); 
		subNumber=subNumber.replace(":","");
		Random r = new Random();
		subNumber=subNumber+randomNumeric(r,6);
		return subNumber;
	}
	
    public String randomNumeric(Random random,int count) {
        //return random(count, false, true);
    	StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			int val = random.nextInt(10);
			sb.append(String.valueOf(val));
		}
		return sb.toString();
    }

}
