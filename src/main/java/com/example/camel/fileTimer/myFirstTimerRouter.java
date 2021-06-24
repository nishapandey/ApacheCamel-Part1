package com.example.camel.fileTimer;
import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;






//@Component
public class myFirstTimerRouter extends RouteBuilder {

	@Autowired
	GetCurrentTime GetCurrentTime;
	
	@Autowired
	processingComponent processingComponent;
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		//read from queue-> transforn -> save in db
		Object test = from("timer:frsttimer?period=10000")
			//	.transform().constant("my constant message")
			//	.bean("getCurrentTime") //when you dont autowire
			//.bean(GetCurrentTime,"getTime") 
	    	// if more than one  method use methodname	 .get()
			.log("${body}")
		    .bean(GetCurrentTime)
		    .log("${body}")
			.bean(processingComponent)
			.log("${body}")
			.process(new processComponent())
		.to("log:first-timer");
		
		
	}
	
	

}

@Component
class GetCurrentTime{
	
	public String getTime() {
		return "time now "+LocalDateTime.now();
	}
}

@Component
class processingComponent{
	
	private Logger  log = LoggerFactory.getLogger(processingComponent.class);
	
	public void testProcess() {
		log.info("logger time now {}",LocalDateTime.now());
	}
}

@Component
class processComponent implements Processor{
	
	private Logger  log = LoggerFactory.getLogger(processingComponent.class);


	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("logger time now {}",exchange.getMessage().getBody());
		
	}
}

