package ch.confte.api.converter;

import ch.confte.api.core.Conference;
import ch.confte.api.model.ConferenceModel;

public class ConferenceConverter {
    public ConferenceModel convert(Conference conference) {
    	ConferenceModel conferenceModel = new ConferenceModel();
    	
    	conferenceModel.setName(conference.getName());
    	conferenceModel.setDescription(conference.getDescription());
    	conferenceModel.setLocation(conference.getLocation());
    	conferenceModel.setStartDate(conference.getStartDate());
    	conferenceModel.setEndDate(conference.getEndDate());
    	
    	return conferenceModel;
    }
    
    public Conference convert(ConferenceModel conferenceModel) {
    	Conference conference = new Conference();
    	conference.setName(conferenceModel.getName());
    	conference.setDescription(conferenceModel.getDescription());
    	conference.setLocation(conferenceModel.getLocation());
    	conference.setStartDate(conferenceModel.getStartDate());
    	conference.setEndDate(conferenceModel.getEndDate());
    	
    	return conference;
    }
}
