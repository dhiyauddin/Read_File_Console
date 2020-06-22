package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
 

public class StringToDate {

	
	
	public Date getStringToDate(String stringDate){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date today = null;
	        try
	        {
	            today = df.parse(stringDate);           
	            //System.out.println("Today = " + df.format(today));
	        } catch (ParseException e)
	        {
	            e.printStackTrace();
	        }
	       
	        return today;
	}// end method
	
	
	
	//must provide date format MM/dd/yy n yyyy-MM-dd
	public static int getNumberOfDays(String sToDate) {
        
	      long days = 0;
	      int iMonth=0;
	      int iYear=0;
	      int iDay=0;

	    try {
		   java.util.Date toDate = null;
		   if ( sToDate.indexOf("/") > 0 )
		              toDate = new SimpleDateFormat("MM/dd/yy").parse(sToDate);
		   else if ( sToDate.indexOf("-") > 0 )
		          toDate = new SimpleDateFormat("yyyy-MM-dd").parse(sToDate);
	
		   java.util.Date currentDate = new java.util.Date();
		   days = ( toDate.getTime() - currentDate.getTime() );
		   days = days / 1000 / 3600 / 24 ;
	
		   //to be consistent with old system
		   days = days + 1;


	   } catch (Exception e) {

	    	//log.error( "Failed to get number of days between the current date and the given date. Error:" + e );

	   }

	    return (int) days;
	}// end method
	
	//must provide date format MM/dd/yy n yyyy-MM-dd
	public static int getNumberOfDays(String sToDate, String sFromDate) {
        
	      long days = 0;
	      int iMonth=0;
	      int iYear=0;
	      int iDay=0;

	    try {
		   java.util.Date toDate = null;
		   java.util.Date fromDate = null;
		  
		   if ( sToDate.indexOf("/") > 0 )
		          toDate = new SimpleDateFormat("MM/dd/yy").parse(sToDate);
		   else if ( sToDate.indexOf("-") > 0 )
		          toDate = new SimpleDateFormat("yyyy-MM-dd").parse(sToDate);
	
		   
		   if ( sFromDate.indexOf("/") > 0 )
		          fromDate = new SimpleDateFormat("MM/dd/yy").parse(sFromDate);
		   else if ( sFromDate.indexOf("-") > 0 )
			   fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(sFromDate);
		   
		   
		   java.util.Date currentDate = new java.util.Date();
		   days = ( toDate.getTime() - fromDate.getTime() );
		   days = days / 1000 / 3600 / 24 ;
	
		   //to be consistent with old system
		   days = days + 1;


	   } catch (Exception e) {

	    	//log.error( "Failed to get number of days between the current date and the given date. Error:" + e );

	   }

	    return (int) days;
	}
}// end class
