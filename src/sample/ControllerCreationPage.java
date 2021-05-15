package sample;

import artist.Artist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.FileUtils;
import media.MediaFile;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/***
 * Class that control sampleCreationPage.fxml, where albums will be created.
 * @author Sergio Tajuelo Cabrera
 * @version 1.0
 */
public class ControllerCreationPage implements Initializable {
    @FXML
    private Button searchButton, createButton, album, artist, podcast, song;

    private final ArrayList<Artist> arrayListArtist = FileUtils.loadArtists();
    private final ArrayList<MediaFile> mediaFileArrayList = FileUtils.loadStats();

    /***
     * Method that starts as soon as this scene is loaded.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void sendToSearchPage(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleSearchPage.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Search page");
        stage.show();

        Stage thisStage = (Stage) searchButton.getScene().getWindow();
        thisStage.close();

    }

    @FXML
    private void creationArtist(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleArtistCreation.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        Stage thisStage = (Stage) artist.getScene().getWindow();
        thisStage.close();
    }

    @FXML
    private void creationSong(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleSongCreation.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        Stage thisStage = (Stage) song.getScene().getWindow();
        thisStage.close();
    }

    @FXML
    private void sendToHomePage(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Home Page");
        stage.show();

        Stage thisStage = (Stage) createButton.getScene().getWindow();
        thisStage.close();

    }

    @FXML
    private void creationPodcast(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("samplePodcastCreation.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        Stage thisStage = (Stage) podcast.getScene().getWindow();
        thisStage.close();
    }

    @FXML
    private void creationAlbum(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleAlbumCreation.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        Stage thisStage = (Stage) album.getScene().getWindow();
        thisStage.close();
    }
}
