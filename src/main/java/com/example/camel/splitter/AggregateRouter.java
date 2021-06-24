package com.example.camel.splitter;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.camel.splitter.AggregateRouter.AggragateSpliterStrategy;

@Component
public class AggregateRouter extends RouteBuilder{

	//@Autowired
	//CurrencyExchange CurrencyExchange;
	
	 class AggragateSpliterStrategy implements AggregationStrategy {

		@Override
		public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
	        Object newBody = newExchange.getIn().getBody();
	        ArrayList<Object> list = null;
	        if (oldExchange == null) {
	            list = new ArrayList<Object>();
	            list.add(newBody);
	            newExchange.getIn().setBody(list);
	            return newExchange;
	        } else {
	            list = oldExchange.getIn().getBody(ArrayList.class);
	            list.add(newBody);
	            return oldExchange;
	        }
	    }

	}

	@Override
	public void configure() throws Exception {
		 	  
		 Object mq = from("file:files/json")
				.unmarshal().json(JsonLibrary.Jackson, CurrencyConverter.class) // add jackson dependency inroder to unmarshall
				.log("${body}")
				.aggregate(simple("${body.to}") , new AggragateSpliterStrategy()) //you can pick one attribute of the message like "to" in json file body and group them 
				.completionSize(3) // i want to look at 3 messages with "to" in body
				//.completionTimeout(HIGHEST) // use completionsize or timeout  , in timeout you can choose to read eg :every 10 sec 
				.to("log:aggreate-json");
		 
		
		
	}

}


@Component
class Split{
	
	public List<String> splitInpuy(String body){
		return List.of("abc","efg","hij");
	}
	
}