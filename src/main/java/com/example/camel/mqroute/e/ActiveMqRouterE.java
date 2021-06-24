package com.example.camel.mqroute.e;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


//@Component
public class ActiveMqRouterE extends RouteBuilder{

	public static int counter=0;
	
	@Override
	public void configure() throws Exception {
		counter++;
		// TODO Auto-generated method stub
		//creating a sender route which sends constant message  to activemq queues every10seconds	
		/*Object mq = from("timer:mq-timer?period=10000")
				.transform().constant("my first mq"+counter)
				.log("${body}")
				.to("activemq:myQueue");
		*/
		
		// creating a sender route which reads file from local disk and put it  to activemq queues
		Object mq = from("activemq:e")
				.log("moviing from e to f")
				.to("activemq:f");
		
	}

}
