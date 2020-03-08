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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/events/photos")
public class EventPhotoController {
	@Autowired
	EventPhotoService service;
	
	@GetMapping("/{eventId}")
    public ResponseEntity<List<EventPhoto>> getPhotosByEventId(@PathVariable Long eventId) {
		List<EventPhoto> list = service.getPhotosByEventId(eventId);
        return new ResponseEntity<List<EventPhoto>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	 @PostMapping("/{eventId}")
	    public ResponseEntity<EventPhoto> addPhoto(@PathVariable Long eventId, 
	    		@Valid @RequestBody EventPhoto photo) throws RecordNotFoundException 
	 {
		 EventPhoto entity = service.addPhoto(eventId, photo);
		 return new ResponseEntity<EventPhoto>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	 @PutMapping("/{eventId}/{photoId}")
	    public ResponseEntity<EventPhoto> updatePhoto(@PathVariable Long eventID,
	                               @PathVariable Long photoId,
	                               @Valid @RequestBody EventPhoto photoRequest) throws RecordNotFoundException
	 {
		 EventPhoto entity = service.updatePhoto(eventID, photoId, photoRequest);
		 return new ResponseEntity<EventPhoto>(entity, new HttpHeaders(), HttpStatus.OK);
	    }

	    @DeleteMapping("/{photoId}")
	    public ResponseEntity<?> deletePhoto(@PathVariable Long photoID) throws RecordNotFoundException 
	    {
	       return service.deletePhoto(photoID);

	    }
}
