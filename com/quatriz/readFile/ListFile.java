package com.quatriz.readFile;

public class ListFile {

	private String fileName;
	private String data;
	private String dateExecuted;
	private String optionTable;
	
	public ListFile() {}
	
	public ListFile (String fileName,String data,String dateExecuted) {
		this.fileName = fileName;
		this.data = data;
		this.dateExecuted = dateExecuted;
	}
	
	public String getOptionTable() {
		return optionTable;
	}
	public void setOptionTable(String optionTable) {
		this.optionTable = optionTable;
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
