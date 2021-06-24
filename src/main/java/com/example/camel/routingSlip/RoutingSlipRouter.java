package com.example.camel.routingSlip;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoutingSlipRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		//Routing slip: is used to dynamically route the message to certain endpoints based on certain criteria
		 String routingslip1 = "direct:endpoint1,direct:endpoint2";
		 String routingslip2 = "direct:endpoint1,direct:endpoint3";
		 
		 Object mq = from("timer:mq-timer?period=10000")
				.transform().constant("testing routig slip")
				.routingSlip(simple(routingslip1))
				
				;
		from("direct:endpoint1")
			.to("log:direct1");
		 
		 from("direct:endpoint2")
			.to("log:direct2");
		 from("direct:endpoint3")
			.to("log:direct3");
		 
		
		
	}

}
