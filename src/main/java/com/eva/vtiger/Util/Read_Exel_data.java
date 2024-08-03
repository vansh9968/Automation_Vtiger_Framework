package com.eva.vtiger.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_Exel_data {
	public static void main(String[] args) {
		
//		Map<String,String> map=new HashMap<String,String>();
//		map.put("dataID", "Login_003");
//		map.put("address", "xyz");
//		map.put("username", "56456");
//		map.put("password", "klnkdslv");
//		String u=map.get("username");
//		
//		List<String> list=new ArrayList<String>();
//		list.add("Login_003");
//		list.add("xyz");
//		list.add("56456");
//		list.add("klnkdslv");
//		String ui=list.get(2);
		
		
		// Exel file extension types
		// TestData.xlsx - when we save in exel 2007 after version
		// TestData.xls - we we save in exel 97-2003 work book
		// TestData.xlsx TestData.xls
		// XSSFWorkBook HSSFWorkbook
		// Sheet - Interface
		// InputStream- file read or take data from file
		// OutputStream- create new file or update any file data
		// InputStream- Interface child-FileInputStream
		// Workbook- Interface child- XSSFWorkbook, HSSFWorkbook

		InputStream is;
		try {
			// creating object of FileInputStream class to read the file
			// Storing object to its parent interface
			is = new FileInputStream("data\\Vtiger Data.xlsx");

			// creating object of XSSFWorkbook class to read .xlsx file
			// Storing object to its parent Workbook interface 
			// So that it can manage XSSFWorkbook & HSSFWorkbook both
			// for .xls -- use HSSFWorkbook
			Workbook wbook = new XSSFWorkbook(is);
			
            // getSheet method will return of Sheet object on the basis of name
			Sheet sheetObj = wbook.getSheet("Login");
			
			// getLastRowNum method will give last activated row number means row count
			// it returns -1 means if there is 10 row it returns 9
			int rowCount = sheetObj.getLastRowNum();
			
			// loop from 0 to rowcount which is already -1
 			for (int i = 0; i <= rowCount; i++) {
				// calling getRow Method of Sheet Interface 
				Row row = sheetObj.getRow(i);
				
				// getLastCellNum will give last active cell number of that row 
				int cellCount = row.getLastCellNum();
				// loop through from 0 to cellcount-1
				// because it gives actual cell count
				for (int j = 0; j <= cellCount - 1; j++) {
					// getcell method will return object of cell on the basis of cell index number
					// index number starts from 0
					// Cell cell = row.getCell(j); use when there is no blank cell
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					// MissingCellPolicy.CREATE_NULL_AS_BLANK will make null to blank string
					String data = cell.getStringCellValue();
					System.out.println(data);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
