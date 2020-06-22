package common;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.*;

public class ChangeDateFormat
{
	String outDate = "";
	
	public ChangeDateFormat(){}
	
	public String beginChangeDateFormat(String theDate, int fmtNo)
	{
		java.util.Date tdate = new java.util.Date();
		SimpleDateFormat sdfInput = null;
		SimpleDateFormat sdfOutput = null;
		
		switch (fmtNo)
		{
			case 1:
				sdfInput = new SimpleDateFormat("dd/MM/yyyy");
				sdfOutput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
				break;
			case 2:
				sdfInput = new SimpleDateFormat("dd/MM/yyyy");
				sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
				break;
			case 3:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
				sdfOutput = new SimpleDateFormat("dd/MM/yyyy");
				break;
			case 4:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd");
				sdfOutput = new SimpleDateFormat("dd/MM/yyyy");
				break;
			case 5:
				sdfInput = new SimpleDateFormat("MM");
				sdfOutput = new SimpleDateFormat("MMMM");
				break;
			case 6:
				sdfInput = new SimpleDateFormat("dd/MM/yyyy");
				sdfOutput = new SimpleDateFormat("MMMM");
				break;
			case 7:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
				sdfOutput = new SimpleDateFormat("MMMM");
				break;
			case 8:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd");
				sdfOutput = new SimpleDateFormat("MMMM");
				break;
			case 9:
				sdfInput = new SimpleDateFormat("yy");
				sdfOutput = new SimpleDateFormat("yyyy");
				break;
			case 10:
				sdfInput = new SimpleDateFormat("dd/MM/yyyy");
				sdfOutput = new SimpleDateFormat("yyyy");
				break;
			case 11:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
				sdfOutput = new SimpleDateFormat("yyyy");
				break;
			case 12:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd");
				sdfOutput = new SimpleDateFormat("yyyy");
				break;
			case 13:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				sdfOutput = new SimpleDateFormat("dd/MM/yyyy");
				break;
			case 15:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				sdfOutput = new SimpleDateFormat("hh:mm a");
				break;
			case 16:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				sdfOutput = new SimpleDateFormat("hh");
				break;
			case 17:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				sdfOutput = new SimpleDateFormat("mm");
				break;
			case 18:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				sdfOutput = new SimpleDateFormat("a");
				break;
			case 19:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				sdfOutput = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				break;
			case 20:
				sdfInput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				sdfOutput = new SimpleDateFormat("dd/MM/yyyy");
				break;
			case 21:
				sdfInput = new SimpleDateFormat("hh:mm:ss");
				sdfOutput = new SimpleDateFormat("hh");
				break;
			case 22:
				sdfInput = new SimpleDateFormat("hh:mm:ss");
				sdfOutput = new SimpleDateFormat("mm");
				break;
			case 23:
				sdfInput = new SimpleDateFormat("hh:mm:ss");
				sdfOutput = new SimpleDateFormat("a");
				break;		
			case 24:
				sdfInput = new SimpleDateFormat("hh:mm:ss");
				sdfOutput = new SimpleDateFormat("hh:mm:ss a");
				break;	
			case 25:
				sdfInput = new SimpleDateFormat("dd/MM/yyyy");
				sdfOutput = new SimpleDateFormat("ddMMyyyy");
				break;
			case 26:
				sdfInput = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				sdfOutput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				break;
			case 27:
				sdfInput = new SimpleDateFormat("dd/MM/yyyy");
				sdfOutput = new SimpleDateFormat("dd");
				break;	
			case 28:
				sdfInput = new SimpleDateFormat("dd/MM/yyyy");
				sdfOutput = new SimpleDateFormat("MM");
				break;				
		}
		
		try
		{ 
			if (theDate.equals("None"))
			{
				outDate = "0000-00-00";
			}
			else if (theDate.equals("0000-00-00 00:00:00"))
			{
				outDate = "None";
			}
			else if (theDate.equals("0000-00-00"))
			{
				outDate = "None";
			}
			else if (theDate.equals("00:00:00"))
			{
				outDate = "None";
			}
			else
			{
				tdate = sdfInput.parse(theDate);
				outDate = sdfOutput.format(tdate);
			}
		}
		catch (Exception e) {}
		return outDate;
	}
}
