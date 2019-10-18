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
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;


/**
 *
 * @author imyat
 */



class PlayerProfile implements Serializable{
    
    String playerName;
    ArrayList<String> playerNameList = new ArrayList<>();
    public void setPlayerName(String name){
        playerName = name;
        playerNameList.add(name);
    }
    
    public ArrayList getPlayerNameList(){
        return playerNameList;
    }
}






public class PlantsVSZombies extends Application {
    
    PlayerProfile profile = new PlayerProfile();
    
    Stage window;
    Label menuBackground;
    Scene menuBackgroundScene;
    
    ArrayList<String> playerNameList = new ArrayList<>();
    
    public Button createButton(String btnTitle, double xCoordinate, double yCoordinate){
        Button btn = new Button(btnTitle);
        btn.setMaxSize(300, 300);
        btn.setLayoutX(xCoordinate);
        btn.setLayoutY(yCoordinate);
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
    
    
    public void addToPane(Pane pane, Parent parent){
        pane.getChildren().add(parent);
    }
    
    
    public void btn1ConfirmationButton(Scene scene)throws InterruptedException{
        Font font = new Font("Aerial", 24);
        Label label = createTextLabel("Profile Created", 150, 140);
        label.setFont(font);
        Button okBtn = createButton("OK", 215, 180);
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
                //System.out.println(btn1TextField.getText());
                profile.setPlayerName(btn1TextField.getText());
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
    
    
    public void btn2Action(int xCoordinate, int yCoordinate)throws IOException{
        Pane pane = createPane();
        addToPane(pane, menuBackground);
        Font font= new Font("Aerial", 24);
        if(playerNameList.size() == 0){
            Button btn = createButton("EMPTY", 180, 180);
            btn.setFont(font);
            addToPane(pane, btn);
            btn.setOnAction(e -> {
                try{
                    menu();
                }
                catch(IOException ex){
                    //IOException handled
                }
            });
        }
        else{
            Label[] lbl = new Label[playerNameList.size()];
            for(int i=0; i<lbl.length; i++){
                lbl[i] = createTextLabel(playerNameList.get(i), xCoordinate, yCoordinate);
                lbl[i].setFont(font);
                /*if(playerNameList.get(i).equals("")){
                    System.out.println("NULL");
                }
                else{
                    System.out.println(playerNameList.get(i));
                }*/
                pane.getChildren().add(lbl[i]);
                yCoordinate += 40;
            }
            yCoordinate += 40;
            Button loadButton = createButton("LOAD", xCoordinate, yCoordinate);
            Button backButton = createButton("BACK", xCoordinate + 80, yCoordinate);

            backButton.setOnAction(e -> {
                try{
                    menu();
                }
                catch(IOException ex){
                    //IOException Handeled
                }
            });


            loadButton.setOnAction(e -> {
               try{
                   menu();
               } 
               catch(Exception ex){
                   //IOExceptionHandeled + Class Not Found Exception Handeled
               }
            });

            pane.getChildren().addAll(loadButton, backButton);
        }
        
        Scene scene = createScene(pane, 500, 500);
        window.setScene(scene);
        
    }
    
    
    
    public void noButtonAction(){
        System.exit(0);
    }
    
    public void yesButtonAction()throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/NetBeans 8.0.2/Projects/PlantsVSZombies/src/playerNameList.txt"));
        oos.writeObject(profile);
        oos.close();
        System.exit(0);
    }
    
    
    public void btn3Action() throws IOException{
        Label lbl = createTextLabel("Do you want to save changes", 100, 180);
        Font font = new Font("Aerial", 24);
        lbl.setFont(font);
        Button yesButton = createButton("Yes", 190, 220);
        Button noButton = createButton("No", 240, 220);
        Pane pane = createPane();
        pane.getChildren().addAll(menuBackground, lbl, yesButton, noButton);
        Scene scene = new Scene(pane, 500, 500);
        window.setScene(scene);
        noButton.setOnAction(e -> noButtonAction());
        yesButton.setOnAction(e -> {
            try{
                yesButtonAction();
            }
            catch(IOException ex){
                //handeled IOException
            }
        });
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
        
        btn2.setOnAction(e -> {
            try {
                btn2Action(180, 200);
            } catch (IOException ex) {
                //IOException Handeled
            }
        });
        
        
        btn3.setOnAction(e -> {
            try {
                btn3Action();
            } catch (IOException ex) {
                //Handeled IOException
            }
        });
        window.show();
    }
    
    
    @Override
    public void start(Stage primaryStage)throws IOException, ClassNotFoundException{
        
        window = primaryStage;
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/NetBeans 8.0.2/Projects/PlantsVSZombies/src/playerNameList.txt"));
            profile = (PlayerProfile)ois.readObject();
            playerNameList = profile.getPlayerNameList();
            ois.close();
        }
        catch(Exception ex){
            
        }
        menu();
        window.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws InterruptedException {
        launch(args);
    }
    
}
