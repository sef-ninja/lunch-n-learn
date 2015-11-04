package ch.confte.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TalkModel {
    private String title;
    private String description;
    private String topic;
    private LinkModel link;
    private ConferenceModel conference;
    private List<SpeakerModel> speakers;

    
    @JsonProperty
    public LinkModel getLink() {
		return link;
	}

	public void setLink(LinkModel link) {
		this.link = link;
	}

	@JsonProperty
    public List<SpeakerModel> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<SpeakerModel> speakers) {
		this.speakers = speakers;
	}

	@JsonProperty
    public ConferenceModel getConference() {
		return conference;
	}

	public void setConference(ConferenceModel conference) {
		this.conference = conference;
	}

	@JsonProperty
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@JsonProperty
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
}
