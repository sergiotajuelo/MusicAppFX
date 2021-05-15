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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.FileUtils;
import media.Album;
import media.MediaFile;
import media.Podcast;
import media.Song;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/***
 * Class that control sample.fxml, which is the main screen of the application.
 * @author Sergio Tajuelo Cabrera
 * @version 1.0
 */
public class Controller implements Initializable {

    private final ArrayList<Artist> arrayListArtist = FileUtils.loadArtists();
    private final ArrayList<MediaFile> mediaFileArrayList = FileUtils.loadStats();

    @FXML
    private Button searchButton, createButton, ok;
    @FXML
    private Label text;
    @FXML
    private ListView<String> listView;
    @FXML
    private ComboBox<String> selectBox;

    /***
     * Method that starts as soon as this scene is loaded.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> list = new ArrayList<>();

        list.add("Songs");
        list.add("Podcasts");
        list.add("Albums");
        list.add("Artists");

        ObservableList<String> items = FXCollections.observableArrayList(list);
        selectBox.setItems(items);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listView.getItems().clear();

                if(selectBox.getValue() == null)
                    text.setText("Songs");
                else
                    text.setText(selectBox.getValue());


                if(selectBox.getValue().equals("Songs") || selectBox.getValue() == null){
                    for (MediaFile m: mediaFileArrayList) {
                        if(m instanceof Song)
                            listView.getItems().add(m.toString());
                    }
                }
                else if(selectBox.getValue().equals("Albums")){
                    for (MediaFile m: mediaFileArrayList) {
                        if(m instanceof Album)
                            listView.getItems().add(m.toString());
                    }
                }
                else if(selectBox.getValue().equals("Podcasts")){
                    for (MediaFile m: mediaFileArrayList) {
                        if(m instanceof Podcast)
                            listView.getItems().add(m.toString());
                    }
                }
                else if(selectBox.getValue().equals("Artists")){
                    for (Artist a: arrayListArtist) {
                        listView.getItems().add(a.toString());
                    }
                }
            }
        });
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
    private void sendToCreationPage(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleCreationPage.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Creation page");
        stage.show();

        Stage thisStage = (Stage) createButton.getScene().getWindow();
        thisStage.close();

    }
}
