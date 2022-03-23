package com.springboot.SpringBootJenkinsIntegration.Location;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
	
	   //database
	   @Autowired  
	   LocationRepository locationRepository;
	   	
	   @Value(value = "${dataToBeUpdatedValue}")
	   private String dataToBeUpdated;
	   

	   
	   //getting all student records  
	   public List<Location> allLocations()   
	   {  
	   List<Location> locations = new ArrayList<Location>();  
	   locationRepository.findAll().forEach(location -> locations.add(location));  
	   return locations;  
	   }  
	   
	   public Location getLocationById(String id)   
	   {  
	   return locationRepository.findById(id).get();  
	   }  
	   
	   
	   public Location insertLocation(Location location) {
		   System.out.println(location.toString());
	       return locationRepository.save(location);
	   }

	   public Location update(String id) {
			
			Location locationFromDb = locationRepository.findById(id).get();
			//System.out.println("setDbData has a value: " + jdbcUrl1);
			//System.out.println("getProperty returned a value: " + System.getProperty("${hsqlSource}"));;

		    return locationRepository.save(locationFromDb);
			
		}
	   
	   public Location update() {
		   
			
			//Location locationFromDb = locationRepository.findById(id).get();
			//locationFromDb.setName(name);
			//System.out.println("dbDataVal has a value: " + dataToBeUpdated.toString());
		   Location location = new Location();
			String[] dataToBeUpdatedArray = dataToBeUpdated.split("\\s+");
			//for(String data:newStr) {
			//	System.out.println("dbDataVal has a value: " + data);
			//}
			
			//System.out.println("getProperty returned a value: " + System.getProperty("${hsqlSource}"));;

		    //return locationRepository.save(locationFromDb);
		   if(dataToBeUpdatedArray.length == 2) {
			   location.setId(dataToBeUpdatedArray[0]);
			   location.setName(dataToBeUpdatedArray[1]);
			   
		   }
		   else {
			   location.setName("No Location Given");
		   }
		   
		   return locationRepository.save(location);
		
		}
	   
	   public Location update(String id, String name) {
		   
			
			Location locationFromDb = locationRepository.findById(id).get();
			locationFromDb.setName(name);
			
		    return locationRepository.save(locationFromDb);
		}
	
	
/*	
   Location location1 = new Location("l1", "Lagos");
   Location location2 = new Location("l2", "Asaba");
   Location location3 = new Location("l3", "Budapest");	
   
   List<Location> locations = new ArrayList<>(Arrays.asList(location1, location2, location3));
	
   public List<Location> getAllLocations() {
		
	return locations;
   }
   public Location getLocation(String id) {
	    Location location = locations.stream()
		.filter(t -> id.equals(t.getId()))
		.findFirst()
		.orElse(null);
			
	    return location;
	}
   
   public void addLocation(Location location) {
	     locations.add(location);
	}
   
   public void deleteLocation(String id) {
	    locations.removeIf(t -> t.getId().equals(id));
	}
*/



}