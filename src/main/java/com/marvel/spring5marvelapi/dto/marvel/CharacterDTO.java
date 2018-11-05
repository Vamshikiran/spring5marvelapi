package com.marvel.spring5marvelapi.dto.marvel;

/**
 * Created by vamshikirangullapelly on 04/11/2018.
 */
public class CharacterDTO {

    private int id;
    private String name;
    private String description;
    private Thumbnail thumbnail;

    public CharacterDTO() {
    }

    public CharacterDTO(int id, String name, String description, Thumbnail thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
