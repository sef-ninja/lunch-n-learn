package ch.confte.api.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table( name = "talk" )
public class Talk {
    private int talkId;
    private String title;
    private String description;
    private String topic;
    private Conference conference;
    private List<Speaker> speakers = new ArrayList<Speaker>();   
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "talk_speaker",
            joinColumns = @JoinColumn(name = "talk_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    @OrderColumn(name = "order_by")
	public List<Speaker> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}

	public Talk() {}
	
	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="talk_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name="talk_id", unique=true, nullable=false)
    @JsonProperty
	public int getTalkId() {
		return talkId;
	}
	
	public void setTalkId(int talkId) {
		this.talkId = talkId;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conference_id", nullable = false)
	@JsonProperty
	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}
	
}
