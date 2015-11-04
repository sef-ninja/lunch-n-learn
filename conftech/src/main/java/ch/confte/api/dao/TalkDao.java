package ch.confte.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import ch.confte.api.core.Talk;
import io.dropwizard.hibernate.AbstractDAO;

public class TalkDao extends AbstractDAO<Talk> {
    public TalkDao(SessionFactory factory) {
        super(factory);
    }

    public Talk findById(int id) {
        return get(id);
    }
    
    public Talk findByIdFetchSpeakers(int id) {
    	
    	Talk talk = null;
    	
        List<Talk> talkList = criteria().add(Restrictions.eq("talkId", id))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setFetchMode("speakers", FetchMode.JOIN).list();
        
    	if(talkList.size() > 0) {
    		talk = talkList.get(0);
    	}
    	
    	return talk;
    }
    
    public int saveOrUpdate(Talk talk) {
    	return persist(talk).getTalkId();
    }
}
