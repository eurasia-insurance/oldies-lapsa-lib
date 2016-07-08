package com.lapsa.lapsa.velocitytools;

import javax.enterprise.inject.Produces;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityEngineProducer {

    @Produces
    public VelocityEngine produceNewEngine() {
	VelocityEngine velocityEngine = new VelocityEngine();
	velocityEngine.setProperty("input.encoding", "UTF-8");

	velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
	velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

	velocityEngine.init();
	
	return velocityEngine;
    }
}
