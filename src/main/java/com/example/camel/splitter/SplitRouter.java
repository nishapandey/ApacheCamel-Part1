package com.example.camel.splitter;

import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class SplitRouter extends RouteBuilder{

	@Autowired
	SplitClassComponent SplitClassComponent;
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		/*
		Object mq = from("file:files/csv")
				.unmarshal().csv()
				.split(body())
				.log("${body}")
				.to("activemq:mySplitter");
		
		 */
		 	  
		 Object mq = from("file:files/csv")
				.convertBodyTo(String.class)
				.split(method(SplitClassComponent)) //can do it with bean or split method
				//.bean(SplitClassComponent)
				.log("${body}")
				.to("activemq:mySplitter");
		 
		
		
	}

}

@Component
 class SplitClassComponent{
	
	public List<String> splitInpuy(String body){
		return List.of("abc","efg","hij");
	}
	
}
