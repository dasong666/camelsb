package com.redhat.consulting.upstsis.camelsb;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CamelsbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelsbApplication.class, args);
	}

	@Autowired
	private Route1Properties route1Properties;

	@Autowired
	private CamelContext camelContext;

	@PostConstruct
	public void configureRoutes() throws Exception {
		// Configure your Camel routes based on the properties
		RouteBuilder routeBuilder = new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				//from("direct:start1")
				from("timer://foo?period=6000").routeId("catdog-routeID").setBody().constant("Sloooo Coma Mierda!").log("${body}")
						.autoStartup(route1Properties.isAutoStartup())
						.to("log:myRoute1");
			}
		};
		camelContext.addRoutes(routeBuilder);
	}
}
