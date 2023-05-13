package com.portal.portalforbusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication()
public class PortalForBusinessApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PortalForBusinessApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(PortalForBusinessApplication.class, args);
	}

}
