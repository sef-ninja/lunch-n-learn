package com.example.stlsouvenir;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.db.DataSourceFactory;
//import com.example.stlsouvenir.resources.HelloWorldResource;
import com.example.stlsouvenir.resources.SouvenirResource;
import com.example.stlsouvenir.health.TemplateHealthCheck;
import com.example.stlsouvenir.dao.*;
import com.example.stlsouvenir.core.*;

public class StlSouvenirApplication extends Application<StlSouvenirConfiguration> {

    public static void main(String[] args) throws Exception {
        new StlSouvenirApplication().run(args);
    }

    private final HibernateBundle<StlSouvenirConfiguration> hibernate = new HibernateBundle<StlSouvenirConfiguration>(Souvenir.class) {
    
	@Override
        public DataSourceFactory getDataSourceFactory(StlSouvenirConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }

    };

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<StlSouvenirConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(StlSouvenirConfiguration configuration, Environment environment) {
        final SouvenirDAO dao = new SouvenirDAO(hibernate.getSessionFactory());     

        //final HelloWorldResource resource =
	//       	new HelloWorldResource(configuration.getTemplate(),configuration.getDefaultName(), dao);

	final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());

	final SouvenirResource souvenirResource = new SouvenirResource(dao);

	environment.healthChecks().register("template", healthCheck);
	//environment.jersey().register(resource);
	environment.jersey().register(souvenirResource);
    }
}

