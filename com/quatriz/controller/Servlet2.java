package com.quatriz.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quatriz.DAO.CrudeFormProcess;
import com.quatriz.readFile.ListFile;

import common.CurrentDateTimeStingWithFormat;

public class Servlet2 extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String request_param_optionTableExecuted = "";
		request_param_optionTableExecuted = request.getParameter("monitor_input_optionTableExecuted");
		String request_param_dateExecuted = "";
		request_param_dateExecuted = request.getParameter("monitor_input_dateExecuted");
		String request_param_tagIdExecuted = "";
		request_param_tagIdExecuted = request.getParameter("monitor_input_tagIdExecuted");
		String request_param_fileNameExecuted = "";
		request_param_fileNameExecuted = request.getParameter("monitor_input_fileNameExecuted");


		ListFile passObj = new ListFile();
		if (request_param_dateExecuted!=null)
			passObj.setDateExecuted(request_param_dateExecuted);
		if (request_param_tagIdExecuted!=null)
			 passObj.setData(request_param_tagIdExecuted);
		if (request_param_fileNameExecuted!=null)
			 passObj.setFileName(request_param_fileNameExecuted);
		if (request_param_optionTableExecuted!=null)
			 passObj.setOptionTable(request_param_optionTableExecuted);
		
		CrudeFormProcess process = new CrudeFormProcess();
		ArrayList<ListFile> dataArrayList = new ArrayList<ListFile>();
		
		if ("Duplicate".equals(passObj.getOptionTable()))
			{
				dataArrayList = process.getAllDuplicateFiles(passObj);
			}
			else
				dataArrayList = process.getAllOriginalFiles(passObj);
		
		CurrentDateTimeStingWithFormat obj = new CurrentDateTimeStingWithFormat();
		request.setAttribute("msgDisplay", obj.processDate());
		RequestDispatcher rd = null; 
		
		
		if ("Duplicate".equals(passObj.getOptionTable()))
		{
			request.setAttribute("listFile", dataArrayList);
			rd = request.getRequestDispatcher("Monitor_DuplicateFile.jsp");
		}
		else
		{
			request.setAttribute("listFile", dataArrayList);
			rd = request.getRequestDispatcher("Monitor_OriginalFile.jsp");
		}

		rd.forward(request, response);

	}
}
