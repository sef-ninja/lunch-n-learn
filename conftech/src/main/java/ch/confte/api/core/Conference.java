package ch.confte.api.core;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table( name = "conference" )
public class Conference {
    private int conferenceId;
    private String name;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;
    private Set<Talk> talks = new HashSet<Talk>();
    
	public Conference() {}

	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="conference_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name="conference_id", unique=true, nullable=false)
    @JsonProperty
	public int getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(int conferenceId) {
		this.conferenceId = conferenceId;
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

	@Column(name="start_date")
	@JsonProperty
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name="end_date")
	@JsonProperty
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "conference")
	public Set<Talk> getTalks() {
		return talks;
	}

	public void setTalks(Set<Talk> talks) {
		this.talks = talks;
	}
	
	
}
