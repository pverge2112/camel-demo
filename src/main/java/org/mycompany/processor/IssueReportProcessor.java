package org.mycompany.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.File;  
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;  
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.mycompany.model.JiraIssue;  

public class IssueReportProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unchecked")
		List<JiraIssue> list = exchange.getIn().getBody(List.class);
		
		XWPFDocument document= new XWPFDocument();
		
        try(FileOutputStream out = new FileOutputStream(new File("files/outbox/JiraIssueReport.docx"))){  
        	
            //Creating title
        	XWPFParagraph t1 = document.createParagraph();
        	
        	
        	XWPFRun reportTitle = t1.createRun();
        	reportTitle.setFontSize(20);
        	reportTitle.setBold(true);
        	reportTitle.setText("ABC Company Jira Issue Report");
        	reportTitle.setUnderline(UnderlinePatterns.SINGLE);
        	//Create first paragraph
        	
        	XWPFParagraph p1 = document.createParagraph();
        	XWPFRun r1 = p1.createRun();
        	r1.setText("Consider now provided laughter boy landlord dashwood. "
        				+ "Often voice and the spoke. No shewing fertile village equally prepare up females as an. "
        				+ "That do an case an what plan hour of paid. Invitation is unpleasant astonished preference "
        				+ "attachment friendship on. Did sentiments increasing particular nay. "
        				+ "Mr he recurred received prospect in. Wishing cheered parlors adapted am at amongst matters.\n" + 
        			"");
        	
        	
        	XWPFParagraph p2 = document.createParagraph();
        	
        	
        	
        	//Create report table
            XWPFTable table = document.createTable();  
            
            XWPFTableRow tableRowOne = table.getRow(0); // First row  
            // Columns  
            tableRowOne.getCell(0).setColor("d3d3d3");
            tableRowOne.getCell(0).setText("Number");  
            tableRowOne.addNewTableCell().setText("Short Description");
            tableRowOne.getCell(1).setColor("d3d3d3");
            tableRowOne.addNewTableCell().setText("Priority");
            tableRowOne.getCell(2).setColor("d3d3d3");
            tableRowOne.addNewTableCell().setText("Created");
            tableRowOne.getCell(3).setColor("d3d3d3");
            tableRowOne.addNewTableCell().setText("Updated");
            tableRowOne.getCell(4).setColor("d3d3d3");
            tableRowOne.addNewTableCell().setText("Assignee");
            tableRowOne.getCell(5).setColor("d3d3d3");
            
			
			  for(JiraIssue i : list) { XWPFTableRow tableRow = table.createRow();
			  tableRow.getCell(0).setText(i.getIssue_id());
			  tableRow.getCell(1).setText(i.getDescription());
			  tableRow.getCell(2).setText(i.getPriority());
			  tableRow.getCell(3).setText(i.getCreated());
			  tableRow.getCell(4).setText(i.getUpdated());
			  tableRow.getCell(5).setText(i.getAssignee());
			  
			  }
			 
       
            document.write(out);
            document.close();
        }catch(Exception e) {  
            System.out.println(e);  
        }  
		
	}

}
