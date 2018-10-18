package com.geo.config.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevProfileBean implements EnvBasedConfig {

	@Override
	public void setUp() {
		System.err.println("Dev Profile Setup method called");
	}

}
