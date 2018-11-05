
package com.marvel.spring5marvelapi.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.marvel.spring5marvelapi.dto.marvel.CharacterDTO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "status",
    "results"
})
public class CharacterListDTO implements Serializable
{

    @JsonProperty("code")
    private Integer code;
    @JsonProperty("status")
    private String status;
    @JsonProperty("results")
    private List<CharacterDTO> characterDTOs = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1518072661850438638L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CharacterListDTO() {
    }

    /**
     * 
     * @param characterDTOs
     * @param status
     * @param code
     */
    public CharacterListDTO(Integer code, String status, List<CharacterDTO> characterDTOs) {
        super();
        this.code = code;
        this.status = status;
        this.characterDTOs = characterDTOs;
    }

    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("results")
    public List<CharacterDTO> getCharacterDTOs() {
        return characterDTOs;
    }

    @JsonProperty("results")
    public void setCharacterDTOs(List<CharacterDTO> characterDTOs) {
        this.characterDTOs = characterDTOs;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
