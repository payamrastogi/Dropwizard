package com.dropwizard.helloworld;

import com.dropwizard.helloworld.health.TemplateHealthCheck;
import com.dropwizard.helloworld.resources.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration>
{
	public static void main(String args[]) throws Exception
	{
		new HelloWorldApplication().run(args);
	}
	
	@Override
	public void run(HelloWorldConfiguration configuration, Environment environment)throws Exception 
	{
		final HelloWorldResource resource = new HelloWorldResource(
				configuration.getTemplate(),
				configuration.getDefaultName()
				);
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
	}
	
	@Override
	public String getName()
	{
		return "hello-world";
	}
	/*
	 * (non-Javadoc)
	 * @see io.dropwizard.Application#initialize(io.dropwizard.setup.Bootstrap)
	 * An initialize method is used to configure aspects of the application 
	 * required before the application is run, like bundles, configuration source providers, etc
	 */
	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap)
	{
		
	}
}
