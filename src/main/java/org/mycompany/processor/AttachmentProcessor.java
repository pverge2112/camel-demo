package org.mycompany.processor;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class AttachmentProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		try {
			exchange.getIn().setBody("Please review this report.");
            exchange.getIn().addAttachment("JiraIssueReport.docx", new DataHandler(new FileDataSource("files/outbox/JiraIssueReport.docx")));
        } catch (Exception e) {
            e.printStackTrace();
        
		
	}
	}
}
