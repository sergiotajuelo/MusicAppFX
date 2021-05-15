package media;

import artist.Artist;

import java.util.ArrayList;

/***
 * Class to define Albums and its information.
 * @author Sergio Tajuelo Cabrera
 * @version 1.0
 */
public class Album extends MediaFile{
    private ArrayList<Song> songs;

    /***
     * Constructor that will let create an Album with the information needed.
     * @param songs an arrayList with all the songs that will be added to this album.
     * @param artist the artist who made this album.
     * @param name how the album will be named.
     */
    //Creating the album
    public Album(ArrayList<Song> songs, Artist artist, String name){
        super(artist, name);
        this.songs = songs;
    }

    /**
     * Returns an arrayList with all the songs.
     * @return Songs
     */
    public ArrayList<Song> getSongs(){
        return songs;
    }

    /***
     * toString method which contains the basic information about this Album.
     * @return an String with all the data, in a customized format.
     */
    @Override
    //toString - Basic information
    public String toString() {
        return "Album -> " + "Name of the album: " + super.name + " ; Artist: " +
                super.getArtisticName();
    }

}
