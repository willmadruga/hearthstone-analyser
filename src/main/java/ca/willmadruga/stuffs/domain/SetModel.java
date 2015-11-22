package ca.willmadruga.stuffs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by wmad on 2015-11-21.
 */
public class SetModel {

    @JsonProperty(value = "Set")
    private String setName;

    @JsonProperty(value = "Cards")
    private List<CardModel> list;

    public List<CardModel> getList() {
        return list;
    }

    public void setList(List<CardModel> list) {
        this.list = list;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }
}
