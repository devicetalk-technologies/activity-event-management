package in.co.devicetalk.activityeventmanagement;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/events")
public class EventController {
	@Autowired
    EventService service;
 
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> list = service.getAllEvents();
 
        return new ResponseEntity<List<Event>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id) 
                                                    throws RecordNotFoundException {
    	Event entity = service.getEventById(id);
 
        return new ResponseEntity<Event>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<Event> createOrUpdateEvent(@Valid @RequestBody Event event)
                                                    throws RecordNotFoundException {
    	Event updated = service.createOrUpdateEvent(event);
        return new ResponseEntity<Event>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEventById(@PathVariable("id") Long id) 
                                                    throws RecordNotFoundException {
    	return service.deleteEventById(id);
    }
 

}
