# Spring-Boot Camel Demo

This example demonstrates how you can use Apache Camel Spring XML with Spring Boot.  

In this example, a fictitious workflow starts with an email having an attached excel spreadsheet containing JiraIDs is sent to an email account.  A route using the Mail component, polls the email account for unread emails having the subject 'Jira Issues'.  The route invokes a processor (MailAttachmentProcessor) which looks for an attachment and if one exists, it's saved to a file in the files/inbox folder as whatever file name was given to the attachment.

A second route using the File component, polls the files/inbox folder for specific file extensions (*.xlxs).  If a file with this extension exists, it's picked up, processed and deleted.  Processing is done by invoking a processor bean that uses the Apache POI library to parse the excel file and pull out the list of Jira IDs and return them in an outbound exchange header. Conditional logic routes the message (containing the Jira ID header) to another route if the JiraID header is not empty.

A third route which is invoked directly from the second route, calls an external mocked API, passing the JiraIDs which returns a JSON response.  The JSON response is unmarshaled to JiraIssue pojo list, split using the Splitter EIP pattern and persisted to an in-memory H2 database table (Issues) with a status set to 'P' (processed).

A fourth route uses a timer based JPA consumer to poll the Issues table for records having a status of 'P' (processed) and using an aggregation strategy (ArrayList), combines the records into a list of JiraIssue pojos and updates the status in the database to 'D' delivered.  The list of JiraIssue pojos is passed to a processor (IssueReportProcessor) that uses the Apach POI library to build a word (.docx) document report named JiraIssueReport.docx and places a file named JiraIssueReport.docx in the files/outbox folder.

A fifth and final route polls the files/outbox folder for files having the *.docx extension, generates an email and sends it along with the attached report to a configured email account.


The demo uses several camel components (Mail, File, HTTP4, JPA) to access local and external resources along with several EIP patterns (Splitter and Aggregator.


### Building

The example can be built with

    mvn clean install

### Running the example locally
The example can be run locally with

    mvn spring-boot:run
