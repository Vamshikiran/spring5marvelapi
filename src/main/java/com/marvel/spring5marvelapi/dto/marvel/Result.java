
package com.marvel.spring5marvelapi.dto.marvel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "comics",
    "description",
    "events",
    "id",
    "modified",
    "name",
    "resourceURI",
    "series",
    "stories",
    "thumbnail",
    "urls"
})
public class Result implements Serializable
{

    @JsonProperty("comics")
    private Comics comics;
    @JsonProperty("description")
    private String description;
    @JsonProperty("events")
    private Events events;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("name")
    private String name;
    @JsonProperty("resourceURI")
    private String resourceURI;
    @JsonProperty("series")
    private Series series;
    @JsonProperty("stories")
    private Stories stories;
    @JsonProperty("thumbnail")
    private Thumbnail thumbnail;
    @JsonProperty("urls")
    private List<Url> urls = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8182205615331503526L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param id
     * @param series
     * @param thumbnail
     * @param stories
     * @param resourceURI
     * @param urls
     * @param events
     * @param description
     * @param name
     * @param comics
     * @param modified
     */
    public Result(Comics comics, String description, Events events, Integer id, String modified, String name, String resourceURI, Series series, Stories stories, Thumbnail thumbnail, List<Url> urls) {
        super();
        this.comics = comics;
        this.description = description;
        this.events = events;
        this.id = id;
        this.modified = modified;
        this.name = name;
        this.resourceURI = resourceURI;
        this.series = series;
        this.stories = stories;
        this.thumbnail = thumbnail;
        this.urls = urls;
    }

    @JsonProperty("comics")
    public Comics getComics() {
        return comics;
    }

    @JsonProperty("comics")
    public void setComics(Comics comics) {
        this.comics = comics;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("events")
    public Events getEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setEvents(Events events) {
        this.events = events;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("modified")
    public String getModified() {
        return modified;
    }

    @JsonProperty("modified")
    public void setModified(String modified) {
        this.modified = modified;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("resourceURI")
    public String getResourceURI() {
        return resourceURI;
    }

    @JsonProperty("resourceURI")
    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    @JsonProperty("series")
    public Series getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(Series series) {
        this.series = series;
    }

    @JsonProperty("stories")
    public Stories getStories() {
        return stories;
    }

    @JsonProperty("stories")
    public void setStories(Stories stories) {
        this.stories = stories;
    }

    @JsonProperty("thumbnail")
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("urls")
    public List<Url> getUrls() {
        return urls;
    }

    @JsonProperty("urls")
    public void setUrls(List<Url> urls) {
        this.urls = urls;
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
