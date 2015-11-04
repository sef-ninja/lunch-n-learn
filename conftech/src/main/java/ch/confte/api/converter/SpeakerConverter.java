package ch.confte.api.converter;

import ch.confte.api.core.Speaker;
import ch.confte.api.model.SpeakerModel;

public class SpeakerConverter {

	public SpeakerModel convert(Speaker speaker) {
    	SpeakerModel speakerModel = new SpeakerModel();
    	speakerModel.setName(speaker.getName());
		return speakerModel;
    }
    
    public Speaker convert(SpeakerModel speakerModel) {
    	Speaker speaker = new Speaker();
    	speaker.setName(speakerModel.getName());
    	return speaker;
    }
}
