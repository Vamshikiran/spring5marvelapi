package com.marvel.spring5marvelapi.resource.v1;

import com.marvel.spring5marvelapi.dto.CharacterIdDTO;
import com.marvel.spring5marvelapi.dto.CharacterIdListDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vamshikirangullapelly on 04/11/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MarvelControllerTest {


    @Autowired
    private MockMvc mockMvc;
    MarvelController mockController = new MarvelController();


    @Test
    public void testTheResponseIdsNotNull() throws Exception {
        this.mockMvc.perform(get("/characters/")).andDo(print()).andExpect(status().isOk());
    }

    @org.junit.Test
    public void testMarvelCharacters1() throws Exception {
        CharacterIdListDTO dtoList = mockController.getCharacters();

        List<CharacterIdDTO> idList = dtoList.getCharacterIdList();
        for(CharacterIdDTO dto:idList) {
            System.out.println(dto.getId());
        }
    }

    @Test
    public void testGetCharacters() throws Exception {
        mockController.getCharacters();
        this.mockMvc.perform(get("/characters/1009536")).andDo(print()).andExpect(status().isOk());
    }
}