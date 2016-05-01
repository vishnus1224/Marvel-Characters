package com.vishnus1224.marvelcharacters.model;

/**
 * Represents a thumbnail for character resource.
 * Created by Vishnu on 5/1/2016.
 */
public class CharacterResourceThumbnail {

    /**
     * Thumbnail path.
     */
    private String path;

    /**
     * Thumbnail extension.
     */
    private String extension;

    /**
     * String builder instance for generating final image path.
     */
    private static StringBuilder stringBuilder = new StringBuilder();

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

    /**
     * Combines the path and extension of the thumbnail.
     * @return Final path of the image.
     */
    public String getFinalPath() {

        //reset the contents of the string builder.
        stringBuilder.setLength(0);

        return stringBuilder.append(path)
                .append(".")
                .append(extension)
                .toString();
    }
}
