package ch.confte.api.converter;

import ch.confte.api.core.Talk;
import ch.confte.api.model.TalkModel;

public class TalkConverter {
	
    public TalkModel convert(Talk talk) {
    	TalkModel talkModel = new TalkModel();
    	talkModel.setTitle(talk.getTitle());
    	talkModel.setDescription(talk.getDescription());
    	talkModel.setTopic(talk.getTopic());
    	
    	return talkModel;
    }
    
    public Talk convert(TalkModel talkModel) {
    	Talk talk = new Talk();
    	talk.setTitle(talkModel.getTitle());
    	talk.setDescription(talkModel.getDescription());
    	talk.setTopic(talkModel.getTopic());
    	
		return talk;
    }
}
