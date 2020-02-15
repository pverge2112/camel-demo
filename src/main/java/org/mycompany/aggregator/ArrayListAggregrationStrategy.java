package org.mycompany.aggregator;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.mycompany.model.JiraIssue;

public class ArrayListAggregrationStrategy implements AggregationStrategy{

	@SuppressWarnings("unchecked")
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange)
    {
        List<JiraIssue> list = null;
        if(oldExchange == null || oldExchange.getIn() == null)
        {
            list = new ArrayList<JiraIssue>();
            list.add(newExchange.getIn().getBody(JiraIssue.class));
        }
        else
        {
            list = oldExchange.getIn().getBody(List.class);
            list.add(newExchange.getIn().getBody(JiraIssue.class));
        }
        newExchange.getIn().setBody(list);
        return newExchange;
    }


}
