package com.redhat.consulting.upstsis.camelsb;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CamelsbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelsbApplication.class, args);
	}

	@Autowired
	private CamelContext camelContext;

	@Value("${ROUTE1_AUTO_STARTUP:true}")
	private boolean route1AutoStartup;

	@Value("${ROUTE2_AUTO_STARTUP}")
	private boolean route2AutoStartup;

	@Value("${ROUTE3_AUTO_STARTUP}")
	private boolean route3AutoStartup;

	@PostConstruct
	public void configureRoutes() throws Exception {
		// Configure your Camel routes based on the properties
		RouteBuilder routeBuilder = new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("timer://foo?period=30000")
						.routeId("BreakfastRoute")
						.setBody()
						.constant("Starting Breakfast...")
						.log("${body}")
						.autoStartup(route1AutoStartup)
						.to("log:myRoute1");

				from("timer://foo?period=30000")
						.routeId("LunchRoute")
						.setBody()
						.constant("Skipping Lunch...")
						.log("${body}")
						.autoStartup(route2AutoStartup)
						.to("log:myRoute2");

				from("timer://foo?period=30000")
						.routeId("DinnerRoute")
						.setBody()
						.constant("Hungry for Dinner...")
						.log("Route ID: ${routeId} ++++++++ Your Feeling: ${body}")
						.autoStartup(route3AutoStartup)
						.to("log:myRoute3");
			}
		};
		camelContext.addRoutes(routeBuilder);
	}
}
