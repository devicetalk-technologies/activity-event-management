package in.co.devicetalk.activityeventmanagement;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long>{

	Optional<Event> findById(Long id);

}
