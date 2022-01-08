/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasetest;

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
public class newUserScreen {

    public static void displayCreateUserScreen() {
        //Setting up screen
        Stage primaryStage = new Stage();
        GridPane grid = new GridPane();
        grid.setId("createUserScreen");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RU Dating?");
        Text scenetitle = new Text("Create Account");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(scenetitle, 0, 0);
        //Warning Label
        Label invalidWarn = new Label("Invalid Information!");
        invalidWarn.setTextFill(Color.web("#FF0000"));
        invalidWarn.setMinWidth(Region.USE_PREF_SIZE);
        invalidWarn.setVisible(false);
        grid.add(invalidWarn, 0, 7);
        //Username
        Label userName = new Label("Full Name");
        userName.setMinWidth(Region.USE_PREF_SIZE);
        userName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField();
        userTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(userTextField, 1, 1);
        //Passweord
        Label password = new Label("Password");
        password.setMinWidth(Region.USE_PREF_SIZE);
        password.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(password, 0, 2);
        TextField passTextField = new TextField();
        passTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(passTextField, 1, 2);
        //Age
        Label age = new Label("Age");
        age.setMinWidth(Region.USE_PREF_SIZE);
        age.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(age, 0, 3);
        TextField ageTextField = new TextField();
        ageTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(ageTextField, 1, 3);
        //Gender
        Label genderLabel = new Label("Gender");
        genderLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(genderLabel, 0, 4);
        ComboBox gender = new ComboBox();
        gender.getItems().add("Male");
        gender.getItems().add("Female");
        gender.getItems().add("Other");
        grid.add(gender, 1, 4);
        //Postal Code
        Label postalCode = new Label("Postal Code");
        postalCode.setMinWidth(Region.USE_PREF_SIZE);
        postalCode.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(postalCode, 0, 5);
        TextField pcTextField = new TextField();
        pcTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(pcTextField, 1, 5);
        Button createAccBtn = new Button(" Create Account ");
        grid.add(createAccBtn, 1, 7);
        //School
        Label school = new Label("School");
        school.setMinWidth(Region.USE_PREF_SIZE);
        school.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(school, 0, 6);
        TextField schoolTextField = new TextField();
        schoolTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(schoolTextField, 1, 6);
        createAccBtn.setOnAction((ActionEvent e) -> {
            try {
                Database db = new Database();
                db.createAccount(userTextField.getText(), passTextField.getText(), Integer.parseInt(ageTextField.getText()), gender.getValue().toString(), schoolTextField.getText(), pcTextField.getText());
                primaryStage.close();
                displaySuccess();
            } catch (Exception ex) {
                invalidWarn.setVisible(true);
            }

        });

        primaryStage.show();

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
        Text scenetitle = new Text("Welcome! \nClick Continue to finish your account");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        grid.add(scenetitle, 0, 0);
        primaryStage.show();
        Button loginScreenBtn = new Button("Continue");
        grid.add(loginScreenBtn, 0, 1);
        loginScreenBtn.setOnAction((ActionEvent e) -> {
            primaryStage.close();
            matchingReqScreen.displayReq();

        });

    }

}
