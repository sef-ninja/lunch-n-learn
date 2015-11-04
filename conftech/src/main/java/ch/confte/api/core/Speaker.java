package ch.confte.api.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table( name = "speaker" )
public class Speaker {
    private int speakerId;
    private String name;
    
    
	public Speaker() {}
	
	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="speaker_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name="speaker_id", unique=true, nullable=false)
    @JsonProperty
	public int getSpeakerId() {
		return speakerId;
	}
	
	public void setSpeakerId(int speakerId) {
		this.speakerId = speakerId;
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
