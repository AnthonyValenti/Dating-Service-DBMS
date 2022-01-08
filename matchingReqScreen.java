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
public class matchingReqScreen {

    public static void displayReq() {
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
        Text scenetitle = new Text("Matching Requirements");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(scenetitle, 0, 0);
        //Warning Label
        Label invalidWarn = new Label("Invalid Information!");
        invalidWarn.setTextFill(Color.web("#FF0000"));
        invalidWarn.setMinWidth(Region.USE_PREF_SIZE);
        invalidWarn.setVisible(false);
        grid.add(invalidWarn, 0, 7);
        //Gender Dating Preference
        Label genderLabel = new Label("Gender Preference");
        genderLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(genderLabel, 0, 3);
        ComboBox gender = new ComboBox();
        gender.getItems().add("Male");
        gender.getItems().add("Female");
        gender.getItems().add("Both");
        grid.add(gender, 1, 3);
        //maxKM
        Label maxKM = new Label("Max Range (Km)");
        maxKM.setMinWidth(Region.USE_PREF_SIZE);
        maxKM.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(maxKM, 0, 2);
        TextField maxKMTextField = new TextField();
        maxKMTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(maxKMTextField, 1, 2);
        //Min Age
        Label age = new Label("Minimum Age");
        age.setMinWidth(Region.USE_PREF_SIZE);
        age.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(age, 0, 4);
        ComboBox minAge = new ComboBox();
        minAge.getItems().addAll(generateNumbers());
        grid.add(minAge, 1, 4);
        //Max Age
        Label maxAgeLabel = new Label("Maximum Age");
        maxAgeLabel.setMinWidth(Region.USE_PREF_SIZE);
        maxAgeLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(maxAgeLabel, 0, 5);
        ComboBox maxAge = new ComboBox();
        maxAge.getItems().addAll(generateNumbers());
        grid.add(maxAge, 1, 5);
        //Next Button
        Button nextBtn = new Button(" Next Step! ");
        nextBtn.setMinWidth(Region.USE_PREF_SIZE);
        grid.add(nextBtn, 1, 7);
        primaryStage.show();
        nextBtn.setOnAction((ActionEvent e) -> {
            try {
                //Check for errors
                if (Integer.parseInt(minAge.getValue().toString()) > Integer.parseInt(maxAge.getValue().toString())) {
                    invalidWarn.setVisible(true);
                } else {
                    //add entered values to desired database table
                    Database db = new Database();
                    db.addMatchingReq(gender.getValue().toString(), Integer.parseInt(maxKMTextField.getText()), Integer.parseInt(minAge.getValue().toString()), Integer.parseInt(maxAge.getValue().toString()));
                    //move onto mathcing compatability
                    primaryStage.close();
                    matchingCompScreen.displayComp();
                }

            } catch (Exception ex) {
                invalidWarn.setVisible(true);
            }

        });

    }

    private static Integer[] generateNumbers() {
        Integer[] nums = new Integer[82];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 18;
        }
        return nums;
    }

}
