package org.mycompany.processor;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javax.activation.DataHandler;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Processor;

import com.sun.istack.ByteArrayDataSource;


public class MailAttachmentProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		Map<String, DataHandler> attachments = exchange.getIn().getAttachments();
	     if (attachments.size() > 0) {
	         for (String name : attachments.keySet()) {
	             DataHandler dh = attachments.get(name);
	             // get the file name
	             String filename = "files/inbox/"+dh.getName();
	 
	             // get the content and convert it to byte[]
	             byte[] data = exchange.getContext().getTypeConverter()
	                               .convertTo(byte[].class, dh.getInputStream());
	 
	             // write the data to a file
	             FileOutputStream out = new FileOutputStream(filename);
	             out.write(data);
	             out.flush();
	             out.close();
	         }
	     }
		
	}

}
