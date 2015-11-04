package ch.confte.api.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.codahale.metrics.annotation.Timed;

import ch.confte.api.converter.ConferenceConverter;
import ch.confte.api.converter.SpeakerConverter;
import ch.confte.api.converter.TalkConverter;
import ch.confte.api.core.Conference;
import ch.confte.api.core.Speaker;
import ch.confte.api.core.Talk;
import ch.confte.api.dao.ConferenceDao;
import ch.confte.api.dao.TalkDao;
import ch.confte.api.model.ConferenceModel;
import ch.confte.api.model.LinkModel;
import ch.confte.api.model.SpeakerModel;
import ch.confte.api.model.TalkModel;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/v1/talks")
public class TalkResource {

	private TalkDao talkDao;
	private ConferenceDao conferenceDao;
	private TalkConverter talkConverter;
	private ConferenceConverter conferenceConverter;
	private SpeakerConverter speakerConverter;
	
    public TalkResource(TalkDao talkDao, ConferenceDao conferenceDao, TalkConverter talkConverter,
    		ConferenceConverter conferenceConverter, SpeakerConverter speakerConverter) {
		this.talkDao = talkDao;
		this.conferenceDao = conferenceDao;
		this.talkConverter = talkConverter;
		this.conferenceConverter = conferenceConverter;
		this.speakerConverter = speakerConverter;
	}

    @Path("{talkId}.json")
    @Produces(MediaType.APPLICATION_JSON)
	@GET
    @Timed
    @UnitOfWork
    public Response getById(@PathParam("talkId") int talkId, @Context UriInfo uriInfo) {
    	
		Talk talk = talkDao.findByIdFetchSpeakers(talkId);
		
		if(talk == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		String baseTalkUri = uriInfo.getAbsolutePathBuilder().toString();
		baseTalkUri = baseTalkUri.substring(0, baseTalkUri.length() - 5) + "/";
		
		TalkModel talkModel = talkConverter.convert(talk);
		ConferenceModel conferenceModel = conferenceConverter.convert(conferenceDao.findConference(talkId));
		conferenceModel.setLink(new LinkModel("self", baseTalkUri + "conference.json"));
		talkModel.setConference(conferenceModel);
		
		List<SpeakerModel> speakerModelList = new ArrayList<SpeakerModel>();
		int speakerNum = 1;
		for(Speaker speaker : talk.getSpeakers()) {
			SpeakerModel speakerModel = speakerConverter.convert(speaker);
			speakerModel.setLink(new LinkModel("self", baseTalkUri + "speakers/" + speakerNum + ".json"));
			speakerModelList.add(speakerModel);
			speakerNum++;
		}
		
		talkModel.setSpeakers(speakerModelList);
		
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		
		talkModel.setLink(new LinkModel("self", builder.build().toString()));
		
        return Response.ok(talkModel).build();
    }
	
    @Path("{talkId}/conference.json")
    @Produces(MediaType.APPLICATION_JSON)
	@GET
	@Timed
	@UnitOfWork
	public Response getConferenceForTalk(@PathParam("talkId") int talkId, @Context UriInfo uriInfo) {
    	
    	Conference conference = conferenceDao.findConference(talkId);
    	
		if(conference == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
    	
    	ConferenceModel conferenceModel = conferenceConverter.convert(conference);

		LinkModel linkModel = new LinkModel("self", uriInfo.getAbsolutePathBuilder().toString());
		conferenceModel.setLink(linkModel);
		
		return Response.ok(conferenceModel).build();
	}
    
    @Path("{talkId}/speakers/{speakerNum}.json")
    @Produces(MediaType.APPLICATION_JSON)
	@GET
	@Timed
	@UnitOfWork
	public Response getSpeakerForTalk(@PathParam("talkId") int talkId, @PathParam("speakerNum") int speakerNum, @Context UriInfo uriInfo) {
    	
		Talk talk = talkDao.findByIdFetchSpeakers(talkId);
		
		if(talk == null || talk.getSpeakers().size() < speakerNum) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
        Speaker speaker = talk.getSpeakers().get(speakerNum - 1);
    	SpeakerModel speakerModel = speakerConverter.convert(speaker);
        speakerModel.setLink(new LinkModel("self", uriInfo.getAbsolutePathBuilder().toString()));
        
    	return Response.ok(speakerModel).build();
    }
}
