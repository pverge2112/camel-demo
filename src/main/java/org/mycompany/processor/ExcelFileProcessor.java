package org.mycompany.processor;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ExcelFileProcessor implements Processor {

	
	@Override
	public void process(Exchange exchange) throws Exception {
		//JiraID column
		int columnWanted = 2;
		ArrayList<String> jiraIDs = new ArrayList<String>();
		Workbook wb = null;
		
		byte[] bytes = exchange.getIn().getBody(byte[].class);
		
		try { 
		InputStream myInputStream = new ByteArrayInputStream(bytes);
		
		wb = new XSSFWorkbook(myInputStream);
		
		Sheet sheet = wb.getSheetAt(0);
		
        sheet.forEach(row -> {
            if(isRowEmpty(row) || row.getRowNum() == 8) {
           	 return;
           }
            
            row.forEach(cell -> {
            	if(cell.getColumnIndex() == columnWanted) {
            		String cellvalue = getCellValue(cell);
            		if (cellvalue != "") jiraIDs.add(cellvalue);
            	}
            });
        });

		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        exchange.getOut().setHeader("jiraIDs", String.join(",", jiraIDs));
        
		
	}
	
	private static String getCellValue(Cell cell) {
		String val = "";

		switch (cell.getCellType()) {
		case NUMERIC:
			break;
		case STRING:
			val = cell.getStringCellValue();
			break;
		case BLANK:
			break;
		case BOOLEAN:
			break;
		case ERROR:
			break;
		case FORMULA:
			break;
		case _NONE:
			break;
		default:
			break;
		}

		return val;
	}
	
	private static boolean isRowEmpty(Row row) {
		boolean isEmpty = true;
		DataFormatter dataFormatter = new DataFormatter();

		if (row != null) {
			for (Cell cell : row) {
				if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
					isEmpty = false;
					break;
				}
			}
		}

		return isEmpty;
	}

}
