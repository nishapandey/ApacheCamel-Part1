package com.example.camel.mqroute.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


//@Component
public class ActiveMqRouterC extends RouteBuilder{

	
	@Override
	public void configure() throws Exception {
		
		// TODO Auto-generated method stub
		//creating a sender route which sends constant message  to activemq queues every10seconds	
		/*Object mq = from("timer:mq-timer?period=10000")
				.transform().constant("my first mq")
				.log("${body}")
				.to("activemq:myQueue");
		*/
		
		/* creating a sender route which reads file from local disk and puts it on activemq queues 
			to be received and processed/ transformed by another queue
		*/
		Object mq = from("file:files/json")
				//from("timer:first-timer")
				//.multicast()
				.pipeline()
				.transform().constant("I am in c")
				.to("activemq:d")
				.log("c ${body}")
				.transform().constant("I am in d")
				.log("d ${body}")
				.to("activemq:e")
				.transform().constant("I am in e")
				.log("e ${body}")
				.to("activemq:f")
				.to("direct:log-file-values");
		
		
		//reusable route: 
				from("direct:log-file-values")
				.log("direct ${body}")
				
				
				;
		
	}

}
