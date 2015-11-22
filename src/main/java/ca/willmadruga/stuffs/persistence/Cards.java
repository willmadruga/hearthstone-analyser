package ca.willmadruga.stuffs.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmad on 2015-11-21.
 */
public class Cards {

    private Long id;

    private List<Mechanics> mechanics;

    private String name;

    private String setName;

    private String type;

    private String faction;

    private String rarity;

    private Long cost;

    private Long attack;

    private Long health;

    private Long durability;

    private String text;

    private Boolean elite;

    private String inPlayText;

    private String flavor;

    private String artist;

    private Boolean collectible;

    private String race;

    private String playerClass;

    private String howToGet;

    private String howToGetGold;

    @Id
    @GeneratedValue
    @Column(name = "card_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "card_mechanics", joinColumns = {
            @JoinColumn(name = "card_id", nullable = false, updatable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "mechanics_id", nullable = false, updatable = false)
    })
    public List<Mechanics> getMechanics() {

        if (mechanics == null) {
            mechanics = new ArrayList<>();
        }

        return mechanics;
    }

    public void setMechanics(List<Mechanics> mechanics) {
        this.mechanics = mechanics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getAttack() {
        return attack;
    }

    public void setAttack(Long attack) {
        this.attack = attack;
    }

    public Long getHealth() {
        return health;
    }

    public void setHealth(Long health) {
        this.health = health;
    }

    public Long getDurability() {
        return durability;
    }

    public void setDurability(Long durability) {
        this.durability = durability;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getElite() {
        return elite;
    }

    public void setElite(Boolean elite) {
        this.elite = elite;
    }

    public String getInPlayText() {
        return inPlayText;
    }

    public void setInPlayText(String inPlayText) {
        this.inPlayText = inPlayText;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Boolean getCollectible() {
        return collectible;
    }

    public void setCollectible(Boolean collectible) {
        this.collectible = collectible;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public String getHowToGet() {
        return howToGet;
    }

    public void setHowToGet(String howToGet) {
        this.howToGet = howToGet;
    }

    public String getHowToGetGold() {
        return howToGetGold;
    }

    public void setHowToGetGold(String howToGetGold) {
        this.howToGetGold = howToGetGold;
    }

}
