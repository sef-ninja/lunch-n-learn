package ch.confte.api.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ch.confte.api.converter.ConferenceConverter;
import ch.confte.api.converter.SpeakerConverter;
import ch.confte.api.converter.TalkConverter;
import ch.confte.api.core.Conference;
import ch.confte.api.core.Speaker;
import ch.confte.api.core.Talk;
import ch.confte.api.dao.ConferenceDao;
import ch.confte.api.dao.SpeakerDao;
import ch.confte.api.dao.TalkDao;
import ch.confte.api.model.ConferenceModel;
import ch.confte.api.model.SpeakerModel;
import ch.confte.api.model.TalkModel;
import io.dropwizard.hibernate.UnitOfWork;

@Path("v1/talks.json")
public class TalkCollectionResource {
    private TalkDao talkDao;
	private ConferenceDao conferenceDao;
	private SpeakerDao speakerDao;
	private TalkConverter talkConverter;
	private ConferenceConverter conferenceConverter;
	private SpeakerConverter speakerConverter;

	public TalkCollectionResource(TalkDao talkDao, ConferenceDao conferenceDao,
			SpeakerDao speakerDao, TalkConverter talkConverter,
			ConferenceConverter conferenceConverter, SpeakerConverter speakerConverter) {
		this.talkDao = talkDao;
		this.conferenceDao = conferenceDao;
		this.speakerDao = speakerDao;
		this.talkConverter = talkConverter;
		this.conferenceConverter = conferenceConverter;
		this.speakerConverter = speakerConverter;
	}

	
	@POST
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTalk(TalkModel talkModel, @Context UriInfo uriInfo) throws URISyntaxException {
		ConferenceModel confModel = talkModel.getConference();
		
		Talk newTalk = talkConverter.convert(talkModel);
        talkDao.saveOrUpdate(newTalk);

		List<SpeakerModel> speakerList = talkModel.getSpeakers();
		if(speakerList != null) {
			
			List<Speaker> newSpeakerList = new ArrayList<Speaker>();
			
			for(SpeakerModel speakerModel : speakerList) {
				String speakerName = speakerModel.getName();
				speakerName = speakerName.replace("'", "''");
				Speaker speaker = speakerDao.findByName(speakerName);
				
				if(speaker == null) {
					speaker = speakerConverter.convert(speakerModel);
					speakerDao.saveOrUpdate(speaker);
				}
				
				newSpeakerList.add(speaker);
			}
			
			newTalk.setSpeakers(newSpeakerList);
		}
        
        String confName = confModel.getName();
        confName = confName.replace("'", "''");
        Conference conference = conferenceDao.findByName(confName);
        
        if(conference == null) {
         	Conference newConference = conferenceConverter.convert(confModel);
        	conferenceDao.create(newConference);
        }
    	
        newTalk.setConference(conference);
        talkDao.saveOrUpdate(newTalk);
        
        String baseTalkUri = uriInfo.getAbsolutePathBuilder().toString();
        baseTalkUri = baseTalkUri.substring(0, baseTalkUri.length() - 5);
        
        URI talkUri = new URI(baseTalkUri + "/" + newTalk.getTalkId() + ".json");
        
        // TODO: Return the newly created representation in the response
        
        return Response.created(talkUri).build();
	}
    
}
