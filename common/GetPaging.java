package common;

import java.util.*;
import java.io.*;
import java.sql.*;

public class GetPaging 
{
	int recordCount = 0;
	int pageSize = 0;
	int startNo = 0; 
	int endNo = 0; 
	int pageNo = 0;

	int firstRecord = 0;
	int lastRecord = 0;
	int pageCount = 0;
	int pageCurrent = 0;
	int nextPage = 0;
	int currentBound = 0;
	int backBound = 0;
	int nextBound = 0;

	boolean backSet = false;
	boolean nextSet = false;
	
	public void beginPaging(int pageSize, int pageNo, int recordCount, int fmtPage){
		switch (fmtPage){
			case 1:
				if (pageNo>1 && recordCount<=pageSize*(pageNo-1)){
					pageNo=pageNo-1;
				}
				if (recordCount!=0) 
					pageCount = recordCount/pageSize; 
				
				if(recordCount%pageSize!=0) 
					pageCount++;
					
				startNo = ((pageNo-1) * pageSize) + 1; 
				endNo = (pageNo==pageCount) ? recordCount : (pageNo * pageSize);
			
			break; 
		}
	}
	
	public void PagingBound(int recordCount, int pageSize, int pageNo, int setBound, int nextPage)
	{
		pageCount = recordCount/pageSize;
		if(recordCount%pageSize!=0) pageCount++; 
		
		if (pageNo == 0 ) {
			if (recordCount == 0) {
				pageCurrent = 0; 
			} else {
				pageCurrent = 1;
				firstRecord = 1;
				lastRecord = (pageCurrent != pageCount) ? pageSize : recordCount;
			}
		} else {
			pageCurrent = pageNo;
			firstRecord = ((pageSize * pageCurrent) - (pageSize - 1));
			lastRecord = (pageCurrent != pageCount) ? firstRecord + (pageSize - 1) : recordCount;
		}
				
		backBound =  nextPage - setBound;
		nextBound = setBound + nextPage;
		currentBound = nextBound - setBound;

		if (backBound >= 0)
			backSet =  true;
			
		if (pageCount > nextBound)
			nextSet = true;
	}
		
	public int getpageSize(){
		return pageSize;
	}
	public int getpageCount(){
		return pageCount;
	}
	public int getrecordCount(){
		return recordCount;
	}
	public int getstartNo(){
		return startNo;
	}
	public int getendNo(){
		return endNo;
	}
	public int getpageNo(){
		return pageNo;
	}

	public int getfirstRecord()
	{
		return firstRecord;
	}

	public int getlastRecord()
	{
		return lastRecord;
	}

	public int getcurrentBound()
	{
		return currentBound;
	}

	public int getbackBound()
	{
		return backBound;
	}

	public int getnextBound()
	{
		return nextBound;
	}

	public boolean getbackSet()
	{
		return backSet;
	}

	public boolean getnextSet()
	{
		return nextSet;
	}

}
