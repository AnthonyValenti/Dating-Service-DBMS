/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasetest;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
public class loginScreen extends Application {

    public static void displayLogin() {
        Stage primaryStage = new Stage();
        GridPane grid = new GridPane();
        grid.setId("loginScreen");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RU Dating?");

        //Title
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        grid.add(scenetitle, 0, 0);
        //Username
        Label userName = new Label("Username");
        userName.setMinWidth(Region.USE_PREF_SIZE);
        userName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(userName, 0, 2);
        TextField userTextField = new TextField();
        userTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(userTextField, 0, 3);
        ////////Invalid entry Warning
        Label invalidWarn = new Label("Username/Password Invalid");
        invalidWarn.setTextFill(Color.web("#FF0000"));
        invalidWarn.setMinWidth(Region.USE_PREF_SIZE);
        invalidWarn.setVisible(false);
        grid.add(invalidWarn, 0, 1);
        //Password
        Label pw = new Label("Password");
        pw.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        pw.setMinWidth(Region.USE_PREF_SIZE);
        grid.add(pw, 0, 4);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 0, 5);
        //LoginButton
        Button loginBtn = new Button(" Sign in ");
        grid.add(loginBtn, 1, 5);
        loginBtn.setDefaultButton(true);
        //Create User button
        Button createAccBtn = new Button(" Create Account ");
        grid.add(createAccBtn, 2, 5);
        primaryStage.show();
        //Login function
        loginBtn.setOnAction((ActionEvent e) -> {
            if (checkLoginInfo(userTextField.getText(), pwBox.getText())) {
                try {
                    primaryStage.close();
                    mainScreen main = new mainScreen(userTextField.getText(), pwBox.getText());
                    mainScreen.displayMain();
                } catch (InterruptedException ex) {
                }
            } else {
                invalidWarn.setVisible(true);
                userTextField.clear();
                pwBox.clear();
            }

        });
        createAccBtn.setOnAction((ActionEvent e) -> {
            primaryStage.close();
            newUserScreen.displayCreateUserScreen();

        });
    }

    public static boolean checkLoginInfo(String user, String password) {
        Database db = new Database();
        ArrayList<String> loginInfo = db.getLoginInfo();
        if (loginInfo.contains(user + "," + password)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        displayLogin();
    }

}
