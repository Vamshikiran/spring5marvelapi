package com.marvel.spring5marvelapi.resource.v1;

import com.google.gson.Gson;
import com.marvel.spring5marvelapi.dto.CharacterIdDTO;
import com.marvel.spring5marvelapi.dto.CharacterIdListDTO;
import com.marvel.spring5marvelapi.dto.marvel.CharacterDTO;
import com.marvel.spring5marvelapi.dto.marvel.MarvelDTO;
import com.marvel.spring5marvelapi.dto.marvel.Thumbnail;
import com.marvel.spring5marvelapi.utils.MarvelUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import us.monoid.web.Resty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.marvel.spring5marvelapi.utils.MarvelUtil.addToFile;

@RestController
public class MarvelController {

    private static final String PRIVATE_KEY;
    private static final String PUBLIC_KEY;
    private static final String BASE_URL;
    private static final String FILE_NAME;
    private static final String API_URL = "characters?ts=%d&apikey=%s&hash=%s&offset=%d&limit=%d";
    private static final ResourceBundle rb = ResourceBundle.getBundle("marvel_config");
    private static final long FREQUENCY;

    static {
        PUBLIC_KEY = rb.getString("publicKey");
        PRIVATE_KEY = rb.getString("privateKey");
        BASE_URL = rb.getString("baseURL");
        FILE_NAME = rb.getString("fileName");
        FREQUENCY = Long.valueOf(rb.getString("frequency"));
        File file = new File(FILE_NAME);
        try {
            Path path = file.toPath();
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/characters")
    @ResponseBody
    public CharacterIdListDTO getCharacters() {

        CharacterIdListDTO idList = new CharacterIdListDTO();
        List<CharacterIdDTO> characterIdDTOs = new ArrayList<>();
        try {
            long fileCreatedTime = MarvelUtil.getFileCretedTime(FILE_NAME);
            long currentTime = System.currentTimeMillis();
            long fileSize = MarvelUtil.getFileSize(FILE_NAME);

            if (currentTime - fileCreatedTime > FREQUENCY) {
                MarvelUtil.clearFileContent(FILE_NAME);
            }
            if (fileSize == 0) {
                //connect MarvelClient fetch the results
                MarvelDTO listDTO;
                Set<Integer> ids;
                int limit = 100;
                int offset = 0;
                int totalCharacters;

                Gson gsonObj = new Gson();
                String url = MarvelUtil.getMarvelClientURl(limit, offset, PRIVATE_KEY, PUBLIC_KEY, BASE_URL, API_URL);
                String output;


                output = new Resty().text(url).toString();
                listDTO = gsonObj.fromJson(output, MarvelDTO.class);
                offset += limit;
                totalCharacters = listDTO.getData().getTotal();

                ids = addToFile(listDTO, FILE_NAME);
                ids.forEach(id -> characterIdDTOs.add(new CharacterIdDTO(id)));
                ids.clear();


                while (offset <= totalCharacters) {
                    url = MarvelUtil.getMarvelClientURl(limit, offset, PRIVATE_KEY, PUBLIC_KEY, BASE_URL, API_URL);
                    output = new Resty().text(url).toString();
                    MarvelDTO marvelDTO = gsonObj.fromJson(output, MarvelDTO.class);
                    ids = addToFile(marvelDTO, FILE_NAME);
                    ids.forEach(id -> characterIdDTOs.add(new CharacterIdDTO(id)));
                    ids.clear();
                    offset += limit;
                }
            } else {
                List<String> list;
                Path path = Paths.get(FILE_NAME);
                if (path != null) {
                    try (Stream<String> stream = Files.lines(path)) {

                        list = stream
                                .map(line -> line.split("%")[0])
                                .collect(Collectors.toList());
                        list = list.stream().filter(id -> id.matches("-?\\d+(\\.\\d+)?")).collect(Collectors.toList());
                        list.forEach(id -> characterIdDTOs.add(new CharacterIdDTO(Integer.valueOf(id))));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        idList.setCharacterIdList(characterIdDTOs);
        return idList;
    }

    @RequestMapping(value = "/characters/{characterId}")
    @ResponseBody
    public CharacterDTO getCharacter(@PathVariable("characterId") String characterId) {
        CharacterDTO dto = new CharacterDTO();
        List<String> list;
        Path path = Paths.get(FILE_NAME);
        if (path != null) {

            try (Stream<String> stream = Files.lines(path)) {

                list = stream
                        .filter(line -> line.startsWith(characterId))
                        .collect(Collectors.toList());
                String[] content = list.get(0).split("%");
                int index = 0;
                if (content.length > 0) {
                    Thumbnail thumbnail = new Thumbnail();
                    dto.setId(Integer.valueOf(content[index++]));
                    dto.setName(content[index++]);
                    dto.setDescription(content[index++]);
                    thumbnail.setPath(content[index++]);
                    thumbnail.setExtension(content[index++]);
                    dto.setThumbnail(thumbnail);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //connect MarvelClient fetch the results
        return dto;
    }

    //TO-DO not implemented yet
    @RequestMapping(value = "/characters/{character}/powers")
    @ResponseBody
    public CharacterDTO getCharacterPowers(@PathVariable("character") String character) {
        //connect MarvelClient fetch the results
        return new CharacterDTO();
    }


}
