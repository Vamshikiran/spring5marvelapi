package com.marvel.spring5marvelapi.dto;

import java.util.List;

/**
 * Created by vamshikirangullapelly on 04/11/2018.
 */
public class CharacterIdListDTO {

    private List<CharacterIdDTO>  characterIdList;

    public List<CharacterIdDTO> getCharacterIdList() {
        return characterIdList;
    }

    public void setCharacterIdList(List<CharacterIdDTO> characterIdList) {
        this.characterIdList = characterIdList;
    }
}
