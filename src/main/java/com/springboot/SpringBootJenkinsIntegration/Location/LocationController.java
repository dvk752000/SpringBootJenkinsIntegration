package com.springboot.SpringBootJenkinsIntegration.Location;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {
	
   @Autowired
   private LocationService locationService;
   /*
   @RequestMapping(method=RequestMethod.POST, value="/locations")
   public void addLocation(@RequestBody Location location) {
   	locationService.addLocation(location);
   }
   
   @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
   public void deleteLocation(@PathVariable String id) {
       locationService.deleteLocation(id);
   }
	
   @RequestMapping(value = "/locations")
   public List<Location> getAllLocations() 
   {
	return locationService.getAllLocations();
   }		
   
   @RequestMapping(value = "/locations/{id}")
   public Location getLocation(@PathVariable String id) {
   	return locationService.getLocation(id);
   }
   */
   

   
   @GetMapping("/locations")  
   private List<Location> allLocations()   
   {  
   return locationService.allLocations();  
   }  
   
   @GetMapping("/locations/{id}")  
   private Location getLocationById(@PathVariable("id") String id)   
   {  
   return locationService.getLocationById(id);  
   }
   
   @PostMapping("/locations")
   private Location insertLocation(@RequestBody Location location)   
   {  
   return locationService.insertLocation(location);  
   }  
  
   @GetMapping("/locationsUpdate/{id}")  
	public Location updateDatabaseJenkinsInput(@PathVariable("id") String id) {
	   return locationService.update(id);
	}
   
   @GetMapping("/locationsUpdate/{id}/{name}")  
	public Location updateDatabaseJenkinsInput(@PathVariable("id") String id, @PathVariable("name") String name) {
	   return locationService.update(id, name);
	}
   
   @GetMapping("/locationsUpdate")  
	public Location updateDatabaseJenkinsInput() {
	   return locationService.update();
	}
   
   
}
