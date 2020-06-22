package com.quatriz.DAO;

import java.util.ArrayList;
import java.util.List;

import com.quatriz.DO.ListData;
import com.quatriz.readFile.ListFile;

public class CrudeFormProcess {

	public CrudeFormProcess(){}
	
	public ArrayList getAllDuplicateFiles(ListFile passObj) {
		ListData ul = new ListData();
		ul.fetchDuplicateFiles(passObj);
		ArrayList data = ul.getDataList();
		ul = null;
		return data;
	}
	
	public ArrayList getAllOriginalFiles(ListFile passObj) {
		ListData ul = new ListData();
		ul.fetchOriginalFiles(passObj);
		ArrayList data = ul.getDataList();
		ul = null;
		return data;
	}

}
