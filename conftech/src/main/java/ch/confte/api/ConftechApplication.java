package ch.confte.api;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import ch.confte.api.converter.ConferenceConverter;
import ch.confte.api.converter.SpeakerConverter;
import ch.confte.api.converter.TalkConverter;
import ch.confte.api.core.Conference;
import ch.confte.api.core.Speaker;
import ch.confte.api.core.Talk;
import ch.confte.api.dao.ConferenceDao;
import ch.confte.api.dao.SpeakerDao;
import ch.confte.api.dao.TalkDao;
import ch.confte.api.health.TemplateHealthCheck;
import ch.confte.api.resources.TalkCollectionResource;
import ch.confte.api.resources.TalkResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ConftechApplication extends Application<ConftechConfiguration> {

    public static void main(String[] args) throws Exception {
        new ConftechApplication().run(args);
    }

    private final HibernateBundle<ConftechConfiguration> hibernate =
    		new HibernateBundle<ConftechConfiguration>(Talk.class,
    				Speaker.class, Conference.class) {
    
        @Override
        public DataSourceFactory getDataSourceFactory(ConftechConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<ConftechConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(ConftechConfiguration configuration, Environment environment) {

        final TalkDao talkDao = new TalkDao(hibernate.getSessionFactory());
        final ConferenceDao conferenceDao = new ConferenceDao(hibernate.getSessionFactory());
        final SpeakerDao speakerDao = new SpeakerDao(hibernate.getSessionFactory());
        
        final TalkConverter talkConverter = new TalkConverter();
        final ConferenceConverter conferenceConverter = new ConferenceConverter();
        final SpeakerConverter speakerConverter = new SpeakerConverter();
        
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());

        final TalkResource talkResource = new TalkResource(talkDao, conferenceDao,
        		talkConverter, conferenceConverter, speakerConverter);

        final TalkCollectionResource talkCollectionResource =
        		new TalkCollectionResource(talkDao, conferenceDao, speakerDao,
        				talkConverter, conferenceConverter, speakerConverter);
        
        
        environment.getObjectMapper().setDateFormat(ISO8601DateFormat.getInstance());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(talkResource);
        environment.jersey().register(talkCollectionResource);
    }
}

