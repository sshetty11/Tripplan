package com.shilpashetty.Tripplan.appmodel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trip {
	private String name;
	private Date startdate;
	private Date enddate;
	private User organizer;
	private List<String> location_name;
	private List<User> invitees;
	
	public Trip(){
		
	}

	public Trip(String name,Date startdate, Date enddate, User organizer, List<String> location_name, List<User> invitees){
		this.name =name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.organizer = organizer;
		this.location_name = location_name;
		this.invitees = invitees;
	}
	@JsonProperty
	public List<User> getInvitees() {
		return invitees;
	}
	@JsonProperty
	public void setInvitees(List<User> invitees) {
		this.invitees = invitees;
	}
	@JsonProperty
	public User getOrganizer() {
		return organizer;
	}
	@JsonProperty
	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}
	
	@JsonProperty
	public List<String> getLocation_name() {
		return location_name;
	}
	@JsonProperty
	public void setLocation_name(List<String> location_name) {
		this.location_name = location_name;
	}
	@JsonProperty
	public Date getStartdate() {
		return startdate;
	}
	@JsonProperty
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	@JsonProperty
	public Date getEnddate() {
		return enddate;
	}
	@JsonProperty
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	@JsonProperty
	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty
	public String getName(){
		return name;
	}

}
