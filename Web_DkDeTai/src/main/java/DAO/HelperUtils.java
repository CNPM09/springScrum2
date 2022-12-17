package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Random;



public class HelperUtils {
	public static String randomId() {
		Random rand = new Random(); // instance of random class
		// generate random values from 0-999999999
		int int_random = rand.nextInt(999999999);
		return Integer.toString(int_random);
	}
	
	public static Date convertStringToDate(String str) throws ParseException {
		Date date = null;
		if(str.contains("-")) {

			System.out.println("Dau - : " +str);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = formatter.parse(str);
		} else if (str.contains("/")) {
			System.out.println("Dau / : " +str);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			date = formatter.parse(str);
		}
		return date;
	}
	
	public static String formatDate(Date date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = formatter.format(date);
		return strDate;
	}
}