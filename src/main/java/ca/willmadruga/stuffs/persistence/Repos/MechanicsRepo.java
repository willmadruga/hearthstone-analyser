package ca.willmadruga.stuffs.persistence.Repos;

import ca.willmadruga.stuffs.persistence.MechanicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wmad on 2015-11-21.
 */
public interface MechanicsRepo extends JpaRepository<MechanicsEntity, Long> {

    MechanicsEntity findByMechanic(final String mechanic);

}
