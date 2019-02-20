package com.geo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/myprofile")
public class MyProfileController {

	Logger logger = LoggerFactory.getLogger(MyProfileController.class);

	@Value(value = "${welcome.message}")
	private String value;

	// -------------------Retrieve All
	// Addresss--------------------------------------------------------

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getProfile() {
		logger.trace("A TRACE Message");
		logger.debug("A DEBUG Message");
		logger.info("An INFO Message");
		logger.warn("A WARN Message");
		logger.error("An ERROR Message");
		return value;
	}

}
