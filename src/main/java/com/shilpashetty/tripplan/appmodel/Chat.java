package com.shilpashetty.Tripplan.appmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Chat {
	private Integer tripId;
	private String creator;
	private String text;
	private long createdDate;
	
	public Chat(){
		
	}
	
	public Chat(Integer tripId,String creator,String text,long date){
		this.creator = creator;
		this.text = text;
		this.createdDate = date;
		this.tripId = tripId;
	}
	@JsonProperty
	public Integer getTripId() {
		return tripId;
	}
	@JsonProperty
	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}
	@JsonProperty
	public String getCreator() {
		return creator;
	}
	@JsonProperty
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@JsonProperty
	public String getText() {
		return text;
	}
	@JsonProperty
	public void setText(String text) {
		this.text = text;
	}
	@JsonProperty
	public long getCreatedDate() {
		return createdDate;
	}
	@JsonProperty
	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

}
