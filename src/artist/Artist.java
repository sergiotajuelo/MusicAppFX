package artist;

/***
 * Class to define Artist and its information.
 * @author Sergio Tajuelo Cabrera
 * @version 1.0
 */
public class Artist{
    private String realName, realLastName, artisticName, description;

    /***
     * Constructor that will let create an Artist object.
     * @param realName the real name of the artist.
     * @param realLastName the real last name of the artist.
     * @param artisticName the artistic name by which the artist will be recognize.
     * @param description a brief description of the artist.
     */
    public Artist(String realName, String realLastName, String artisticName,
                  String description){
        this.realLastName = realLastName;
        this.realName = realName;
        this.artisticName = artisticName;
        this.description = description;
    }

    //Getters and setters
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getRealLastName() {
        return realLastName;
    }
    public void setRealLastName(String realLastName) {
        this.realLastName = realLastName;
    }
    public String getArtisticName() {
        return artisticName;
    }
    public void setArtisticName(String artisticName) {
        this.artisticName = artisticName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    /***
     * toString method which contains the basic information about this Artist.
     * @return an String with all the data, in a customized format.
     */
    //toString
    public String toString(){
        return "Artist -> " + artisticName + " ; Real name: " + realLastName + ", " + realName;
    }
}
