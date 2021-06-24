package com.example.camel.choice;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class choiceRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		Object test = 
				from("file:files/input")
				.routeId("Choice-Route")
				.transform().body(String.class)
				.choice()
						.when(simple("${file:ext} == 'xml'"))
							.log("XML File")
						.otherwise()
							.log("not xml file dont processs")
				.end()
				.to("file:files/output")
				.to("direct:log-file-values")
				
				;
		//reusable route
		from("direct:log-file-values")
		.log("${body}")
		
		
		;
		
	}

}



















































































