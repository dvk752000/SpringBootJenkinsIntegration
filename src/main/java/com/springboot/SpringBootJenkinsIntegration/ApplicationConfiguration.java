package com.springboot.SpringBootJenkinsIntegration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "info.build")
public class ApplicationConfiguration {
	
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "\nApplicationConfiguration [version=" + version + "]\n";	
	}
	
	

}
