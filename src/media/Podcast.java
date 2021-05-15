package media;
import artist.Artist;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/***
 * Class to define Podcasts and its information.
 * @author Sergio Tajuelo Cabrera
 * @version 1.0
 */
public class Podcast extends MediaFile {
    protected String audioFile;
    protected File file;
    protected Media audio;
    protected MediaPlayer mediaPlayer;

    /***
     * Constructor that will let create a Podcast object with the information needed.
     * @param creator who created this mediaFile.
     * @param name how this will be named.
     * @param audioFile the path of the audio file.
     */
    //Constructor
    public Podcast(Artist creator, String name, String audioFile) {
        super(creator, name);
        this.audioFile = audioFile;
        file = new File(this.audioFile);
        audio = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(audio);
    }

    /**
     * Returns the audio file path
     * @return Audio file path
     */
    public String getAudioFile() { return audioFile; }

    //Audio managing
    /***
     * Method that will let play the audio.
     */
    public void playPodcast(){
        mediaPlayer.play();
    }

    /**
     * Establishes the volume
     * @param volume New audio volume
     */
    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    /***
     * toString method which contains the basic information about this Podcast.
     * @return an String with all the data, in a customized format.
     */
    //toString
    @Override
    public String toString(){
        return "Podcast -> " + super.toString();
    }
}
