package in.co.devicetalk.activityeventmanagement;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EventService {
	@Autowired
	EventRepository eventRepository;
	
	 public List<Event> getAllEvents()
	    {
	        List<Event> events = eventRepository.findAll();
	         
	        if(events.size() > 0) {
	            return events;
	        } else {
	            return new ArrayList<Event>();
	        }
	    }
	 
	 public Event getEventById(Long id) throws RecordNotFoundException 
	    {
	        Optional<Event> event = eventRepository.findById(id);
	         
	        if(event.isPresent()) {
	            return event.get();
	        } else {
	            throw new RecordNotFoundException("No event record exist for given id");
	        }
	    }
	     
	    public Event createOrUpdateEvent(Event entity) throws RecordNotFoundException 
	    {
	    	Event event = new Event();
	    	List<EventPhoto> photos = new ArrayList<EventPhoto>();
	    	for (int i =0 ; i<entity.getPhotos().size();i++)
	    	{
	    		EventPhoto  eventPhoto = new EventPhoto();
		    	eventPhoto.setEvent(event);
		    	eventPhoto.setPhoto(entity.getPhotos().get(i).getPhoto());
		    	eventPhoto.setRemark(entity.getPhotos().get(i).getRemark());
		    	photos.add(eventPhoto);
	    	}
	    	event.setPhotos(photos);
	    	event.setName(entity.getName());
	    	event.setDescription(entity.getDescription());
	    	event.setRemark(entity.getRemark());
	    	event.setDate(entity.getDate());
	    	
	    	return eventRepository.save(event);
	    } 
	     
	    public ResponseEntity<?> deleteEventById(Long id) throws RecordNotFoundException 
	    {
	        Optional<Event> employee = eventRepository.findById(id);
	         
	        if(employee.isPresent()) 
	        {
	           eventRepository.deleteById(id);
	           return ResponseEntity.ok().build();
	        } else {
	            throw new RecordNotFoundException("No event record exist for given id");
	        }
	    } 
	}
