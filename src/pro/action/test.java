package pro.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date  d = df.parse("2011-10-12 12:12:12");     

		Calendar cal=Calendar.getInstance();

		cal.setTime(d);

		cal.add(Calendar.DATE, 2);  

		System.out.println(df.format(cal.getTime()));
	}

}
