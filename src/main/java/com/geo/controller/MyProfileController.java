package com.geo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/myprofile")
public class MyProfileController {

	@Value(value = "${welcome.message}")
	private String value;

	// -------------------Retrieve All
	// Addresss--------------------------------------------------------

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String getProfile() {
		return value;
	}

}
