package com.example.camel.pipeAndFilter;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class PipeFilterRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		Object mq = from("file:files/json")
				.log("${body}")
				.to("activemq:myQueue");
		
		
		//reusable route
		from("direct:log-file-values")
		.log("${body}")
		
		
		;
		
	}

}
