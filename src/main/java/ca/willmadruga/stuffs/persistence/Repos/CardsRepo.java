package ca.willmadruga.stuffs.persistence.Repos;

import ca.willmadruga.stuffs.persistence.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wmad on 2015-11-21.
 */
public interface CardsRepo extends JpaRepository<CardEntity, Long> {

    CardEntity findByCardIdentifier(final String cardIdentifier);

}