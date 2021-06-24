package com.example.camel.choice;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class choiceRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		/*
		  When you need to conditionally route a message based on some fact, you can use the "choice"
		  and "when" constructs. Its is also called content based routing  ie. Choice,  It checks body of the message or filename
		  or extn...
		 */
		
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



















































































