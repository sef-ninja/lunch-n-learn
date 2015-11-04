package ch.confte.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConferenceModel {
    private String name;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;
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
	
	@JsonProperty
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	@JsonProperty
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonProperty
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
