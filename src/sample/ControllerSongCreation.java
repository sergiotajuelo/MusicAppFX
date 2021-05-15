package sample;

import artist.Artist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.FileUtils;
import media.MediaFile;
import media.Song;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/***
 *  Class that control sampleSongCreation.fxml, where songs will be created.
 * @author Sergio Tajuelo Cabrera
 * @version 1.0
 */
public class ControllerSongCreation implements Initializable {
    @FXML
    private Button doneButtonSong;
    @FXML
    private TextField songName, genre;
    @FXML
    private ComboBox<String> artists, songPath;

    private final ArrayList<Artist> artistArrayList = FileUtils.loadArtists();
    private final ArrayList<MediaFile> mediaFileArrayList = FileUtils.loadStats();

    /***
     * Method that starts as soon as this scene is loaded.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> list = new ArrayList<>();

        for (Artist artist : artistArrayList) {
            list.add(artist.getArtisticName());
        }
        ObservableList<String> items = FXCollections.observableArrayList(list);
        artists.setItems(items);

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("Blinding Lights");
        ObservableList<String> items2 = FXCollections.observableArrayList(list2);
        songPath.setItems(items2);

        doneButtonSong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if ((!songName.getText().equals("")) && (!genre.getText().equals(""))) {
                    Artist artist = null;
                    for (Artist artistCheck : artistArrayList) {
                        if(artistCheck.getArtisticName().equals(artists.getValue())){
                            artist = artistCheck;
                        }
                    }
                    mediaFileArrayList.add(new Song(artist, songName.getText(), "src/audio/blindingLights.mp3"));

                    FileUtils.saveStats(mediaFileArrayList);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleCreationPage.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Creation page");
                    stage.show();

                    Stage thisStage = (Stage) doneButtonSong.getScene().getWindow();
                    thisStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Not a valid artist.");
                    alert.setContentText("Check out if there is any empty field.");

                    alert.showAndWait();
                }
            }
        });
    }
}
