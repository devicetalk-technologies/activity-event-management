package in.co.devicetalk.activityeventmanagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPhotoRepository extends JpaRepository<EventPhoto, Long>{
	List<EventPhoto> findByEventId(Long eventId);

}
