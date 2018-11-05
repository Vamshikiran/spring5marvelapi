package com.marvel.spring5marvelapi.dto;

/**
 * Created by vamshikirangullapelly on 04/11/2018.
 */
public class CharacterIdDTO {
    private int id;

    public CharacterIdDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
