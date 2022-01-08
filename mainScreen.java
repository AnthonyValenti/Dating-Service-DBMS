/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasetest;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author anthonyvalenti
 */
public class mainScreen {

    private static String name;
    private static String pass;
    private static ArrayList<Person> items;

    public mainScreen(String name, String pass) {
        this.name = name;
        this.pass = pass;

    }

    public static void displayMain() throws InterruptedException {
        //Setting up screen
        items = new ArrayList();
        Database db = new Database();
        db.setCurrUserId(name, pass);
        String pName = name;
        db.findMatches();
        Stage primaryStage = new Stage();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RU Dating?");
        Text scenetitle = new Text("Welcome " + name);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(scenetitle, 0, 0);
        Button logoutBtn = new Button("Logout");
        logoutBtn.setMinWidth(70);
        grid.add(logoutBtn, 0, 4);
        Button editAcc = new Button("Edit Account");
        grid.add(editAcc, 1, 4);
        TableView table = new TableView();
        TableColumn<Person, String> name = new TableColumn("Name");
        TableColumn<Person, Integer> age = new TableColumn("Age");
        table.setMinWidth(300);
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().add(name);
        table.getColumns().add(age);
        table.getItems().addAll(items);
        grid.add(table, 0, 1, 3, 1);
        Button dltAcc = new Button(" Delete Account ");
        grid.add(dltAcc, 2, 4);
        primaryStage.show();
        logoutBtn.setOnAction((ActionEvent e) -> {
            //logout and return to login screen
            primaryStage.close();
            loginScreen.displayLogin();

        });
        editAcc.setOnAction((ActionEvent e) -> {
            //edit matching requirements for logged in user
            primaryStage.close();
            editMatchingReqScreen.displayReq(pName, pass);

        });
        dltAcc.setOnAction((ActionEvent e) -> {
            //Deletes the account thats currently logged in and returns to loginScreen
            primaryStage.close();
            db.deleteAcc();
            loginScreen.displayLogin();

        });

    }

    public static void addItem(Object item) {
        items.add((Person) item);
    }

}
