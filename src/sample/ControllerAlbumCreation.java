package sample;

import artist.Artist;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.FileUtils;
import media.Album;
import media.MediaFile;
import media.Song;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/***
 * Class that control sampleAlbumCreation.fxml, where albums will be created.
 * @author Sergio Tajuelo Cabrera
 * @version 1.0
 */
public class ControllerAlbumCreation implements Initializable {
    @FXML
    private Button doneButtonSong, okButton;
    @FXML
    private TextField albumName;
    @FXML
    private ComboBox<String> artists;
    @FXML
    private ListView<String> songsListView;

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
        songsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (MediaFile mediaFile: mediaFileArrayList) {
                    if(mediaFile instanceof Song && mediaFile.getCreator().getArtisticName().equals(artists.getValue())){
                        String song = mediaFile.getName() + " => " + mediaFile.getCreator().getArtisticName();
                        songsListView.getItems().add(songsListView.getItems().size(), song);
                    }
                }
            }
        });

        doneButtonSong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!albumName.getText().equals("")) {
                    ArrayList<String> list = new ArrayList<>(songsListView.getSelectionModel().getSelectedItems());
                    ArrayList<Song> songs = new ArrayList<>();
                    for (String s: list) {
                        String[] songData = s.split(" => ");
                        for (MediaFile m: mediaFileArrayList) {
                            if(m instanceof Song && m.getName().equals(songData[0]))
                                songs.add((Song) m);
                        }
                    }
                    Artist aAlbum = null;
                    for (Artist a : artistArrayList){
                        if(a.getArtisticName().equals(artists.getValue()))
                            aAlbum = a;
                    }
                    if(aAlbum != null)
                        mediaFileArrayList.add(new Album(songs, aAlbum, albumName.getText()));
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
