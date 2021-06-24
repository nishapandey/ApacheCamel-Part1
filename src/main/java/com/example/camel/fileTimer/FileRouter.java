package com.example.camel.fileTimer;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.springframework.stereotype.Component;


//@Component
public class FileRouter extends RouteBuilder{

	//example of file router and reusable router (using direct )
	@Override
	public void configure() throws Exception {
		Object test = 
				from("file:files/input")
				.log("${body}")
				.to("file:files/output")
				.to("direct:log-file-values")
				
				
				;
		//reusable route
		from("direct:log-file-values")
		.log("${body}")
		
		
		;
		
	}

}
