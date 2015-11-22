package ca.willmadruga.stuffs.persistence;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wmad on 2015-11-21.
 */
@Entity
@Table(name = "MECHANICS")
public class MechanicsEntity {

    private Long id;

    private String mechanic;

    private List<CardEntity> cards;

    public MechanicsEntity() {
    }

    public MechanicsEntity(final String mechanic) {
        this.mechanic = mechanic;
    }

    @Id
    @GeneratedValue
    @Column(name = "mechanics_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "mechanics")
    public List<CardEntity> getCards() {
        return cards;
    }

    public void setCards(List<CardEntity> cards) {
        this.cards = cards;
    }

}
