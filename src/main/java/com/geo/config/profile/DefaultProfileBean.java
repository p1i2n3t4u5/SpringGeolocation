package com.geo.config.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("default")
public class DefaultProfileBean implements EnvBasedConfig {

	@Override
	public void setUp() {
		System.err.println("Default Profile Setup method called");
	}

}
