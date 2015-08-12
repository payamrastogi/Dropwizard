package com.dropwizard.helloworld;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
/*
 * When this class is deserialized from the YAML file, 
 * it will pull two root-level fields from the YAML object: template, 
 * the template for our Hello World saying, and defaultName, the default name to use.
 */
public class HelloWorldConfiguration extends Configuration
{
	/*so if the YAML configuration file has blank values for either or is missing 
	 * template entirely an informative exception will be thrown 
	 * and your application won’t start.
	 * 
	 * provided by Hibernate validator
	 */
	@NotEmpty 
	private String template;
	@NotEmpty
	private String defaultName = "Stranger";
	
	/*
	 * The mapping from YAML to your application’s Configuration instance is done by Jackson. 
	 * This means your Configuration class can use all of Jackson’s object-mapping annotations.
	 */
	@JsonProperty
	public String getTemplate()
	{
		return this.template;
	}
	
	@JsonProperty
	public void setTemplate(String template)
	{
		this.template = template;
	}
	
	@JsonProperty
	public String getDefaultName()
	{
		return this.defaultName;
	}
	
	@JsonProperty
	public void setDefaultName(String defaultName)
	{
		this.defaultName = defaultName;
	}
}
