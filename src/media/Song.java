package media;

import artist.Artist;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/***
 * Class to define Songs and its information.
 * @author Sergio Tajuelo Cabrera
 * @version 1.0
 */
public class Song extends MediaFile {
    protected String audioFile;
    protected File file;
    protected Media audio;
    protected MediaPlayer mediaPlayer;

    /***
     * Constructor that will let create a Song object with the information needed.
     * @param creator who created this mediaFile.
     * @param name how this will be named.
     * @param audioFile the path of the audio file.
     */
    //Constructor
    public Song(Artist creator, String name, String audioFile) {
        super(creator, name);
        this.audioFile = audioFile;
        file = new File(this.audioFile);
        audio = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(audio);
    }

    /**
     * Returns the name of the song
     * @return Name of the song
     */
    public String getName(){
        return super.getName();
    }

    /**
     * Returns the audio file path
     * @return Audio file path
     */
    public String getAudioFile() { return audioFile; }

    /***
     * Method to play the song.
     */
    public void playSong(){
        mediaPlayer.play();
    }

    /***
     * Method to adjust the volume of the Song.
     * @param volume that we want to set.
     */
    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    /***
     * toString method which contains the basic information about this Song.
     * @return an String with all the data, in a customized format.
     */
    //toString
    @Override
    public String toString(){
        return "Song -> " + super.toString();
    }
}
