package com.shilpashetty.Tripplan.application;

import org.skife.jdbi.v2.DBI;

import com.shilpashetty.Tripplan.appmodel.ListArgumentFactory;
import com.shilpashetty.Tripplan.config.TripPlanConfig;
import com.shilpashetty.Tripplan.databasemodel.TripDAO;
import com.shilpashetty.Tripplan.service.TripService;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;

public class App extends Application<TripPlanConfig>
{
    public static void main( String[] args ) throws Exception
    {
        new App().run(args);
    }
    @Override
    public void run(TripPlanConfig config,Environment env) throws ClassNotFoundException{
    	
    	
    	final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(env, config.getDataSourceFactory(), "mysql");
        final TripService tripService = new TripService(jdbi);
        env.jersey().register(tripService);
        jdbi.registerArgumentFactory(new ListArgumentFactory());
    	 
    	
    }
}
