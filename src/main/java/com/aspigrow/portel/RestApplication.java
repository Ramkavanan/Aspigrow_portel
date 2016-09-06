package com.aspigrow.portel;

import java.util.Set;
import java.util.EnumSet;
import java.util.HashSet;

import javax.servlet.DispatcherType;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.jaxrs.listing.ApiListingResource;

import com.aspigrow.portel.config.RestConfiguration;
import com.aspigrow.portel.exception.handler.IllegalStateExceptionHandler;

/**
 * Main application starter. That help application to load various servlet container 
 * like swagger, rest application in server handler
 *
 * @author Ramachandran.K
 */ 
@ApplicationPath(RestApplication.BASE_PATH)
@ComponentScan(RestConfiguration.BASE_PACKAGE)
public class RestApplication  extends Application  {

    protected static final String BASE_PATH = "/";

    protected static final String BASE_PACKAGE = "com.aspigrow.*";

    protected static final Integer SERVER_PORT = 8088; // we need to load this from configuration property

    public static void main(String[] args) throws Exception {
    	System.out.println("Server starting port At "+SERVER_PORT);
        Server jettyServer = new Server(SERVER_PORT);

        try {
	       // Workaround for resources from JAR files
           Resource.setDefaultUseCaches( false );

            // Holds handlers
            final HandlerList handlers = new HandlerList();

		    // Handler for Swagger UI, static handler.
            handlers.addHandler( buildSwaggerUI() );

            // Handler for Entity Browser and Swagger
            handlers.addHandler( buildContext() );
            jettyServer.setHandler(handlers);
            jettyServer.start();
            jettyServer.join();
        } catch(Exception ex) {
            // We need to log it
            ex.printStackTrace();	
	    }finally {
            jettyServer.destroy();
        }
    }

    /**
     * Servlet Container context path and servlet will be build and deploy to the jetty server
     * It will deploy the servlet path  which we mentioned in setContextPath in context handler
     */
    private static ContextHandler buildContext()  throws Exception  {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages( "com.aspigrow.portel", ApiListingResource.class.getPackage().getName() );
        ServletContainer servletContainer = new ServletContainer( resourceConfig );
        ServletHolder servletHolder = new ServletHolder( servletContainer );
        ServletContextHandler servletContext = new ServletContextHandler( ServletContextHandler.SESSIONS );
        
        // Cors filter added to access cross site server access
        FilterHolder cors = servletContext.addFilter(CrossOriginFilter.class,"/*",EnumSet.of(DispatcherType.REQUEST));
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");

        servletContext.setContextPath( "/" );
        servletContext.addServlet( servletHolder, "/*" );
	    servletContext.addEventListener( new ContextLoaderListener() );
	    servletContext.addEventListener(new RequestContextListener());
	    servletContext.setInitParameter( "contextClass", AnnotationConfigWebApplicationContext.class.getName());
        servletContext.setInitParameter( "contextConfigLocation", RestConfiguration.class.getName() );
        
        return servletContext;
    }

    /**
     * Swagger document api context hadler added for developer document purpose
     * in docs context path
     */
    private static ContextHandler buildSwaggerUI() throws Exception   {
        final ResourceHandler swaggerUIResourceHandler = new ResourceHandler();
        swaggerUIResourceHandler.setResourceBase( RestConfiguration.class.getClassLoader().getResource( "swaggerui" ).toURI().toString() );
        final ContextHandler swaggerUIContext = new ContextHandler();
        swaggerUIContext.setContextPath( "/docs/" );
        swaggerUIContext.setHandler( swaggerUIResourceHandler );

        return swaggerUIContext;
    }
    
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(IllegalStateExceptionHandler.class);
        return resources;
    }

}
