package com.marvel.spring5marvelapi.dto.marvel;

/**
 * Created by vamshikirangullapelly on 04/11/2018.
 */
public class ThumbnailDTO {
    private String path;
    private String extension;

    public ThumbnailDTO(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
