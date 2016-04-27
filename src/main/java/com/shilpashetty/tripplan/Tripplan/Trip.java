package com.shilpashetty.tripplan.Tripplan;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Trip {
private int id;
private String name;
public Trip(){
	
}
public Trip(int id,String name){
	this.id = id;
	this.name =name;
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
