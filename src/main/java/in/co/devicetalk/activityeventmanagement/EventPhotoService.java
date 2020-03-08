package in.co.devicetalk.activityeventmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventPhotoService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EventPhotoRepository photoRepository;
	
	public List<EventPhoto> getPhotosByEventId(Long eventId) {
        return photoRepository.findByEventId(eventId);
    }
	
	public EventPhoto addPhoto(Long eventId,EventPhoto photo) throws RecordNotFoundException 
	{
        return eventRepository.findById(eventId)
                .map(event -> {
                    photo.setEvent(event);
                    return photoRepository.save(photo);
                }).orElseThrow(() -> new RecordNotFoundException("Photos not found with id " + eventId));
    }
	
	public EventPhoto updatePhoto(Long eventID,Long photoId,EventPhoto photoRequest) throws RecordNotFoundException 
	{
		if(!eventRepository.existsById(eventID)) 
		{
			throw new RecordNotFoundException("Photo not found with id " + eventID);
		}
		
		return photoRepository.findById(photoId)
		.map(photo -> {
		 photo.setPhoto(photoRequest.getPhoto());
		 return photoRepository.save(photo);
		}).orElseThrow(() -> new RecordNotFoundException("Photo not found with id " + photoId));
	}
	
	public ResponseEntity<?> deletePhoto(Long photoID) throws RecordNotFoundException 
	{
		return photoRepository.findById(photoID)
		.map(photo -> {
		photoRepository.delete(photo);
		return ResponseEntity.ok().build();
		}).orElseThrow(() -> new RecordNotFoundException("Photo not found with id " + photoID));

	}
}
