package com.quatriz.DO;

public class ListEntry {

	private String fileName;
	private String data;
	private String dateExecuted;
	
	public ListEntry (String fileName,String data, String dateExecuted) {
		this.fileName = fileName;
		this.data = data;
		this.dateExecuted = dateExecuted;
	}
	
	public ListEntry (String fileName,String data) 
	{
		this.fileName = fileName;
		this.data = data;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public String getDateExecuted() {
		return dateExecuted;
	}

	public void setDateExecuted(String dateExecuted) {
		this.dateExecuted = dateExecuted;
	}
	
}
