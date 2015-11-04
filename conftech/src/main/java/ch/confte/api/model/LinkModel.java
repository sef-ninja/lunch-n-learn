package ch.confte.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkModel {
    private String rel;
    private String href;
    
    public LinkModel(String rel, String href) {
		this.rel = rel;
		this.href = href;
	}
    
	@JsonProperty
	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}
	
	@JsonProperty
	public String getHref() {
		return href;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
}
