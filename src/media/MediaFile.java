package media;

import artist.Artist;

/***
 * Class to define a Media File and its information.
 * @author Sergio Tajuelo Cabrera
 * @version 1.0
 */
public class MediaFile {
    protected Artist creator;
    protected String name;
    protected int timesPlayed;

    /***
     * Constructor that will let create a MediaFile object with the information needed.
     * @param creator who created this mediaFile.
     * @param name how this will be named.
     */
    //Constructors
    public MediaFile(Artist creator, String name) {
        this.creator = creator;
        this.name = name;
    }

    /**
     * Returns the Artist
     * @return Artist
     */
    public Artist getCreator() {
        return creator;
    }

    /**
     * Returns the artist's name
     * @return Artist's name
     */
    public String getName() {
        return name;
    }

    /**
     * Establishes the MediaFile's name
     * @param name MediaFile's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the artistic name
     * @return Artistic name
     */
    public String getArtisticName() {
        return creator.getArtisticName();
    }

    /***
     * toString method which contains the basic information about this MediaFile.
     * @return an String with all the data, in a customized format.
     */
    //toString
    public String toString(){
        return "Creator: " + creator.getArtisticName() + " ; Name: " + name;
    }
}
