package com.geo.config.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdProfileBean implements EnvBasedConfig {

	@Override
	public void setUp() {
       System.err.println("Prod Profile Setup method called");
	}

}
