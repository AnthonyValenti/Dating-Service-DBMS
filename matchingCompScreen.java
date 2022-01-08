/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasetest;

import static databasetest.newUserScreen.displaySuccess;
import java.util.stream.IntStream;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author anthonyvalenti
 */
public class matchingCompScreen {

    public static void displayComp() {
        //Setting up screen
        Stage primaryStage = new Stage();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RU Dating?");
        Text scenetitle = new Text("Matching Compatibility Points");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(scenetitle, 0, 0);
        //Warning Label
        Label invalidWarn = new Label("Invalid Information!");
        invalidWarn.setTextFill(Color.web("#FF0000"));
        invalidWarn.setMinWidth(Region.USE_PREF_SIZE);
        invalidWarn.setVisible(false);
        grid.add(invalidWarn, 0, 7);
        //Favourite Movie
        Label favMovieLabel = new Label("Favourite Movie");
        favMovieLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(favMovieLabel, 0, 4);
        TextField favMovieTextField = new TextField();
        favMovieTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(favMovieTextField, 1, 4);
        //Favourite Music Artist
        Label favArtistLabel = new Label("Favourite Music Artist");
        favArtistLabel.setMinWidth(Region.USE_PREF_SIZE);
        favArtistLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(favArtistLabel, 0, 2);
        TextField favArtistTextField = new TextField();
        favArtistTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(favArtistTextField, 1, 2);
        //Favourite Sport
        Label favSportLabel = new Label("Favourite Sport");
        favSportLabel.setMinWidth(Region.USE_PREF_SIZE);
        favSportLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(favSportLabel, 0, 3);
        TextField favSportTextField = new TextField();
        favMovieTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(favSportTextField, 1, 3);
        //Favourite Song
        Label favSongLabel = new Label("Favourite Song");
        favSongLabel.setMinWidth(Region.USE_PREF_SIZE);
        favSongLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(favSongLabel, 0, 5);
        TextField favSongTextField = new TextField();
        favMovieTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(favSongTextField, 1, 5);
        //Favourite Hobby
        Label favHobbyLabel = new Label("Favourite Hobby");
        favHobbyLabel.setMinWidth(Region.USE_PREF_SIZE);
        favHobbyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(favHobbyLabel, 0, 6);
        TextField favHobbyTextField = new TextField();
        favMovieTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(favHobbyTextField, 1, 6);
        //Next Button
        Button nextBtn = new Button(" Finish Account! ");
        grid.add(nextBtn, 1, 7);
        primaryStage.show();
        nextBtn.setOnAction((ActionEvent e) -> {
            try {
                Database db = new Database();
                db.addMatchingComp(favMovieTextField.getText(), favArtistTextField.getText(), favSportTextField.getText(), favHobbyTextField.getText(), favSongTextField.getText());
                primaryStage.close();
                displaySuccess();

            } catch (Exception ex) {
                invalidWarn.setVisible(true);
            }

        });

    }

    public static void displaySuccess() throws InterruptedException {
        //Setting up screen
        Stage primaryStage = new Stage();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RU Dating?");
        Text scenetitle = new Text("Account Created!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        grid.add(scenetitle, 0, 0);
        primaryStage.show();
        Button loginScreenBtn = new Button(" Login ");
        grid.add(loginScreenBtn, 0, 1);
        loginScreenBtn.setOnAction((ActionEvent e) -> {
            primaryStage.close();
            loginScreen.displayLogin();

        });

    }

}
