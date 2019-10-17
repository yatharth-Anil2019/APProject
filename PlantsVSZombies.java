/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantsvszombies;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


/**
 *
 * @author imyat
 */
public class PlantsVSZombies extends Application {
    
    private static final double minHeight = 30;
    private static final double minWidth = 30;
    Stage window;
    Label menuBackground;
    Scene menuBackgroundScene;
    
    public Button createButton(String btnTitle, double xCoordinate, double yCoordinate){
        Button btn = new Button(btnTitle);
        btn.setMaxSize(300, 300);
        btn.setLayoutX(xCoordinate);
        btn.setLayoutY(yCoordinate);
        //btn.setMinHeight(minHeight);
        //btn.setMinWidth(minWidth);
        return btn;
    }
    
    
    public Button createImageButton(String btnTitle, String imageLocation, double xCoordinate, double yCoordinate)throws IOException{
        ImageView imageView = importImage(imageLocation);
        Button btn = new Button(btnTitle, imageView);
        btn.setMaxSize(300, 300);
        btn.setLayoutX(xCoordinate);
        btn.setLayoutY(yCoordinate);
        return btn;
    }
    
    
    public Pane createPane(){
        Pane pane = new Pane();
        return pane;
    }
    
    public Label createTextLabel(String text, double xCoordinate, double yCoordinate){
        Label label = new Label(text);
        label.setLayoutX(xCoordinate);
        label.setLayoutY(yCoordinate);
        return label;
    }
    
    public Label createImageLabel(String text, String imageLocation, int width, int height)throws IOException{
        ImageView imageView = importImage(imageLocation);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        Label label = new Label(text, imageView);
        return label;
    }
    
    public ImageView importImage(String imageLocation)throws IOException{
        FileInputStream fis = null;
        fis = new FileInputStream(imageLocation);
        Image image = new Image(fis);
        ImageView imageView = new ImageView(image);
        fis.close();
        return imageView;
    }
    
    public TextField createTextField(double xCoordinate, double yCoordinate){
        TextField textField = new TextField();
        textField.setLayoutX(xCoordinate);
        textField.setLayoutY(yCoordinate);
        return textField;
    }
    
    public Scene createScene(Pane pane, int length, int width){
        Scene scene = new Scene(pane, length, width);
        return scene;
    }
    
    public Stage createStage(Stage stage, Scene scene, String title){
        stage.setTitle(title);
        stage.setScene(scene);
        return stage;
    }
    
    
    /*public void addButtonToPane(Pane pane, Button btn){
        pane.getChildren().add(btn);
    }
    
    
    public void addLabelToPane(Pane pane, Label label){
        pane.getChildren().add(label);
    }*/
    
    public void addToPane(Pane pane, Parent parent){
        pane.getChildren().add(parent);
    }
    
    
    public void btn1ConfirmationButton(Scene scene)throws InterruptedException{
        Label label = createTextLabel("Profile Created", 200, 200);
        Button okBtn = createButton("OK", 225, 240);
        Pane pane = createPane();
        pane.getChildren().addAll(menuBackground, label, okBtn);
        Scene scene1 = createScene(pane, 500, 500);
        window.setScene(scene1);
        okBtn.setOnAction(e -> {
            try {
                menu();
            } catch (IOException ex) {
                //Handling IOException
            }
        });
    }
    
    
    public void btn1BackButton(Scene scene)throws IOException{
        menu();
    }
    
    
    public void btn1Action(Scene scene){
        Pane btn1Pane = createPane();
        Button btn1ConfirmButton = createButton("Confirm", 180, 150);
        Button btn1BackButton = createButton("Back", 280, 150);
        Label btn1Label = createTextLabel("Username: ", 70, 120);
        TextField btn1TextField = createTextField(150, 115);
        btn1Pane.getChildren().addAll(menuBackground, btn1ConfirmButton, btn1BackButton, btn1Label, btn1TextField);
        Scene btn1Scene = createScene(btn1Pane, 500, 500);
        window.setScene(btn1Scene);
        
        btn1ConfirmButton.setOnAction(e -> {
            try{
                btn1ConfirmationButton(scene);
            }
            catch(Exception ex){
                //Handeled InterruptedException
            }
        });
        btn1BackButton.setOnAction(e -> {
            try {
                btn1BackButton(scene);
            } catch (IOException ex) {
                //Handeling IOEXception
            }
        });
    }
    
    
    public void btn3Action(){
        System.exit(0);
    }
    
    
    public void menu()throws IOException{
        Button btn1 = createButton("New Profile", 0, 0);
        Button btn2 = createButton("Existing Profile", 0, 35);
        Button btn3 = createButton("Exit", 0, 70);
        menuBackground = createImageLabel("MENU", "D:/NetBeans 8.0.2/Projects/PlantsVSZombies/src/Plants VS Zombies Images/MenuImage.PNG", 500, 500);
        Pane pane = createPane();
        pane.getChildren().addAll(menuBackground, btn1, btn2, btn3);
        menuBackgroundScene = createScene(pane, 500, 500);
        window.setTitle("PLANTS VS ZOMBIES");
        window.setScene(menuBackgroundScene);
        btn1.setOnAction(e -> btn1Action(menuBackgroundScene));
        btn3.setOnAction(e -> btn3Action());
        window.show();
    }
    
    
    @Override
    public void start(Stage primaryStage)throws IOException{
        
        window = primaryStage;
        menu();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws InterruptedException {
        launch(args);
    }
    
}
