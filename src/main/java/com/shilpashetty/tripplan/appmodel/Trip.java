package com.shilpashetty.Tripplan.appmodel;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trip {
	private Integer id;
	private String name;
	private Date startdate;
	private Date enddate;
	private String organizer;

	public Trip(int id,String name,Date startdate, Date enddate, String organizer){
		this.id = id;
		this.name =name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.organizer = organizer;
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
	public String getOrganizer() {
		return organizer;
	}
	@JsonProperty
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	@JsonProperty
	public void setId(Integer id) {
		this.id = id;
	}
	@JsonProperty
	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty
	public int getId(){
		return id;
	}
	@JsonProperty
	public String getName(){
		return name;
	}

}
