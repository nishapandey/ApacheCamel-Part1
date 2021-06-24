package com.example.camel.pipeAndFilter;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class PipeFilterRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		/* 
			Pipeline and multicast: Though pipeline is the default mode of operation when you specify multiple outputs in Camel. 
			The opposite to pipeline is multicast; which fires the same message into each of its outputs.
		 */
		Object mq = from("file:files/json")
				//.pipeline()
				//.multicast()
				.log("${body}")
				.to("activemq:myQueue")
				.to("log:logging stuff");
		
		
		//reusable route
		from("direct:log-file-values")
		.log("${body}")
		
		
		;
		
	}

}
