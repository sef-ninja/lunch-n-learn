package ch.confte.api.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import ch.confte.api.core.Speaker;
import io.dropwizard.hibernate.AbstractDAO;

public class SpeakerDao extends AbstractDAO<Speaker> {

    public SpeakerDao(SessionFactory factory) {
		super(factory);
	}
    
	public int saveOrUpdate(Speaker speaker) {
    	return persist(speaker).getSpeakerId();
    }

	public Speaker findByName(String name) {
        Speaker speaker = null;
		String query = "select distinct s from Speaker s " +
	               "where s.name = '" + name + "'";
        
		List results = currentSession().createQuery(query).list();
		
   	    if(results.size() > 0) {
   	    	speaker = (Speaker) results.get(0);
   	    }
		
		return speaker;
	}
}
