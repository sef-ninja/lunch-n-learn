package ch.confte.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpeakerModel {
    private String name;
    private LinkModel link;

    @JsonProperty
    public LinkModel getLink() {
		return link;
	}

	public void setLink(LinkModel link) {
		this.link = link;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
