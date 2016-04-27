package com.shilpashetty.tripplan.Tripplan;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App extends Application<TripPlanConfig>
{
    public static void main( String[] args ) throws Exception
    {
        new App().run(args);
    }
    @Override
    public void run(TripPlanConfig config,Environment env){
    	final TripService tripService = new TripService();
    	env.jersey().register(tripService);
    	 
    	
    }
}
