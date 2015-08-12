package com.dropwizard.helloworld.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.dropwizard.helloworld.core.Saying;
import com.google.common.base.Optional;
/*
 * @Path("/hello-world") tells Jersey that this resource is accessible at the URI /hello-world
 */
@Path("/hello-world")
/*
 *  @Produces(MediaType.APPLICATION_JSON) lets Jersey’s content negotiation code know 
 *  that this resource produces representations which are application/json
 */
@Produces(MediaType.APPLICATION_JSON)
/*
 * Resource classes are used by multiple threads concurrently.
 * In general, we recommend that resources be stateless/immutable, 
 * but it’s important to keep the context in mind
 * 
 * When our application starts, we create a new instance of our 
 * resource class with the parameters from the configuration file 
 * and hand it off to the Environment, which acts like a 
 * registry of all the things your application can do.
 * 
 * A Dropwizard application can contain many resource classes, 
 * each corresponding to its own URI pattern. Just add another @Path-annotated 
 * resource class and call register with an instance of the new class.
 */
public class HelloWorldResource 
{
	private final String template;
	private final String defaultName;
	private final AtomicLong counter;
	
	public HelloWorldResource(String template, String defaultName)
	{
		this.template = template;
		this.defaultName = defaultName;
		this.counter = new AtomicLong();
	}
	/*
	 * The @QueryParam("name") annotation tells Jersey to map the name parameter 
	 * from the query string to the name parameter in the method. 
	 * If the client sends a request to /hello-world?name=Dougie, 
	 * sayHello will be called with Optional.of("Dougie"); if there is no 
	 * name parameter in the query string, sayHello will be called with Optional.absent()
	 */
	@GET
	@Timed
	public Saying sayHello(@QueryParam("name") Optional<String> name)
	{
		final String value = String.format(template, name.or(defaultName));
		return new Saying(counter.incrementAndGet(), value);
	}
}
