package sample;

import artist.Artist;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.FileUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/***
 * Class that control sampleArtistCreation.fxml, where albums will be created.
 * @author Sergio Tajuelo Cabrera
 * @version 1.0
 */
public class ControllerArtistCreation implements Initializable {
    @FXML
    private Button doneButton;
    @FXML
    private TextField realName, realLastName, artisticName, description;

    ArrayList<Artist> artistArrayList = FileUtils.loadArtists();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if((!realName.getText().equals("")) && (!realLastName.getText().equals("")) &&
                        (!artisticName.getText().equals("")) && (!description.getText().equals(""))){

                    artistArrayList.add(new Artist(realName.getText(), realLastName.getText(), artisticName.getText(),
                            description.getText()));

                    FileUtils.saveArtists(artistArrayList);
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

                    Stage thisStage = (Stage) doneButton.getScene().getWindow();
                    thisStage.close();
                }
                else {
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
