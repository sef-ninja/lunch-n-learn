package ch.confte.api.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import ch.confte.api.core.Conference;
import io.dropwizard.hibernate.AbstractDAO;

public class ConferenceDao extends AbstractDAO<Conference> {

	public ConferenceDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

    public Conference findConference(int talkId) {
    	
    	String query = "select distinct c from Conference c " +
    	               "join c.talks t " +
    			       "where t.talkId = " + talkId;
    	
   	    List results = currentSession().createQuery(query).list();
   	    
   	    Conference conference = null;
   	    
   	    if(results != null) {
   	    	conference = (Conference) results.get(0);
   	    } 
   	    
        return conference;
    }

	public Conference findByName(String name) {
		Conference conference = null;
		
		String query = "select distinct c from Conference c " +
		               "where c.name = '" + name + "'";
		
		List results = currentSession().createQuery(query).list();
		
   	    if(results.size() > 0) {
   	    	conference = (Conference) results.get(0);
   	    }
		
		return conference;
	}
	
    public int create(Conference conference) {
    	return persist(conference).getConferenceId();
    }
}
