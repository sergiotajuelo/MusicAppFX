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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
 * Class where you can search between Artists, Songs, Podcasts or Albums
 * @author Sergio Tajuelo Cabrera
 * @version 1.0
 */
public class ControllerSearchPage implements Initializable {

    private final ArrayList<Artist> artistArrayList = FileUtils.loadArtists();
    private final ArrayList<MediaFile> mediaFileArrayList = FileUtils.loadStats();
    @FXML
    private Button homeButton, searchButton, createButton, okButton;
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField searchBox;
    @FXML
    private ListView<String> listView;

    /***
     * Default method that will load everything in sampleSearchPage.fxml as soon as this page starts.
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
        comboBox.setItems(items);

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listView.getItems().clear();
                if(!searchBox.getText().equals("")){
                    if(comboBox.getValue().equals("Artists")){
                        for (Artist a: artistArrayList) {
                            if(a.getArtisticName().toLowerCase().contains(searchBox.getText().toLowerCase())){
                                String artist = a.getArtisticName() + "  \n" + "Artist";
                                listView.getItems().add(listView.getItems().size(), artist);
                            }
                        }
                    }
                    else {
                        if(comboBox.getValue().equals("Songs")){
                            for (MediaFile m: mediaFileArrayList) {
                                if(m.getName().toLowerCase().contains(searchBox.getText().toLowerCase()) && m instanceof Song){
                                    String song = m.getName() + " - " + m.getCreator().getArtisticName() + "  \n" + "Song";
                                    listView.getItems().add(listView.getItems().size(), song);
                                }
                            }
                        }
                        else if(comboBox.getValue().equals("Podcasts")){
                            for (MediaFile m: mediaFileArrayList) {
                                if(m.getName().toLowerCase().contains(searchBox.getText().toLowerCase()) && m instanceof Podcast){
                                    String podcast = m.getName() + " - " + m.getCreator().getArtisticName() + "  \n" + "Podcast";
                                    listView.getItems().add(listView.getItems().size(), podcast);
                                }
                            }
                        }
                        else if(comboBox.getValue().equals("Albums")){
                            for (MediaFile m: mediaFileArrayList) {
                                if(m.getName().toLowerCase().contains(searchBox.getText().toLowerCase()) && m instanceof Album){
                                    String album = m.getName() + " - " + m.getCreator().getArtisticName() + "  \n" + "Album";
                                    listView.getItems().add(listView.getItems().size(), album);
                                }
                            }
                        }
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("There is nothing to search.");

                    alert.showAndWait();
                }
            }
        });
    }

    @FXML
    private void sendToCreationPage(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleCreationPage.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Search page");
        stage.show();

        Stage thisStage = (Stage) createButton.getScene().getWindow();
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

        Stage thisStage = (Stage) homeButton.getScene().getWindow();
        thisStage.close();

    }

    @FXML
    private void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 2) //Checking double click
        {
            String item = listView.getSelectionModel().getSelectedItem();
            String[] type = item.split(" {2}");

            if(type[1].equals("\nAlbum")){
                Album album = null;
                String[] name = type[0].split(" - ");
                for (MediaFile m: mediaFileArrayList) {
                    if(m.getName().equals(name[0]) && m instanceof Album){
                        album = ((Album) m);
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");

                ArrayList<Song> songs = album.getSongs();
                String songsName = "";
                for (Song s: songs) {
                    songsName = songsName + s.getName() + ", ";

                }
                songsName = songsName.substring(0, songsName.length() - 2);
                alert.setHeaderText(null);
                alert.setContentText("Album songs: \n" + songsName);
                alert.showAndWait();
            }
            else if(type[1].equals("\nSong")){
                Song song = null;
                String[] name = type[0].split(" - ");
                for (MediaFile m: mediaFileArrayList) {
                    if(m.getName().equals(name[0]) && m instanceof Song){
                        song = (Song) m;
                    }
                }

                song.setVolume(5.00);
                song.playSong();
            }
            else if(type[1].equals("\nPodcast")){
                Podcast podcast = null;
                String[] name = type[0].split(" - ");
                for (MediaFile m: mediaFileArrayList) {
                    if(m.getName().equals(name[0]) && m instanceof Podcast){
                        podcast = (Podcast) m;
                    }
                }
                podcast.setVolume(5.00);
                podcast.playPodcast();
            }
            else{
                Artist artist = null;
                for (Artist a: artistArrayList) {
                    if(a.getArtisticName().equals(type[0])){
                        artist = a;
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");

                alert.setHeaderText(null);
                alert.setContentText("Artist: " + artist.getArtisticName() + "\nReal Name: " + artist.getRealName() +
                        " " + artist.getRealLastName());
                alert.showAndWait();
            }
        }
    }
}
