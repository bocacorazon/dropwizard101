package com.tr.mf.origin_dw;

import com.tr.mf.origin_dw.health.TemplateHealthCheck;
import com.tr.mf.origin_dw.resources.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class OriginDWApplication extends Application<OriginDWConfiguration>
{
    public static void main( String[] args ) throws Exception
    {
        new OriginDWApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<OriginDWConfiguration> bootstrap) {
        // nothing to do yet
    }
	@Override
	public void run(OriginDWConfiguration configuration, Environment environment) throws Exception {
	    final HelloWorldResource resource = new HelloWorldResource(
	            configuration.getTemplate(),
	            configuration.getDefaultName()
	        );
	        environment.jersey().register(resource);
	        
	        final TemplateHealthCheck healthCheck =
	                new TemplateHealthCheck(configuration.getTemplate());
	            environment.healthChecks().register("template", healthCheck);
	            environment.jersey().register(resource);
		
	}
}
