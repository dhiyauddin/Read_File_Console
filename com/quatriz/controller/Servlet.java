package com.quatriz.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quatriz.readFile.Execute;
import com.quatriz.readFile.MoveFiles;

import common.Constant;
import common.CurrentDateTimeStingWithFormat;

public class Servlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Execute process_1 = new Execute();
		String situation_execute = "";
		Constant obj = new Constant();
		situation_execute = process_1.execute();

		MoveFiles process_2 = new MoveFiles();
		String situation = "";
		situation = process_2.execute();
				
		CurrentDateTimeStingWithFormat objDateTime = new CurrentDateTimeStingWithFormat();
		request.setAttribute("msgDisplay", objDateTime.processDate());
		RequestDispatcher rd = null;

		if (obj.ReadFileConsole_SuccessInsertRecord.equals(situation_execute)) {
			request.setAttribute("message", "Files Executed Successfully");
			rd = request.getRequestDispatcher("Success.jsp");
		} else if (obj.ReadFileConsole_UnsuccesfulInsertRecord.equals(situation_execute)) {
			request.setAttribute("message",
					"Files Failed Executed due to duplicate file or some erros. See log for the details.");
			rd = request.getRequestDispatcher("Error.jsp");
		}
		rd.forward(request, response);
	}
}
