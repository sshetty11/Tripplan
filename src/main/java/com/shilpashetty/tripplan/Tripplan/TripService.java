package com.shilpashetty.tripplan.Tripplan;
import com.shilpashetty.tripplan.Tripplan.Trip;
import com.codahale.metrics.annotation.Timed;
 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
 
@Path("/trip")
public class TripService {
	public TripService(){
		
	}
	@GET
	@Timed
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Trip getTrip(@PathParam("id") int id){
		Trip newTrip = new Trip(1,"Yosemite");
		return newTrip;
		
	}
	

}
