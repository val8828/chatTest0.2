/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFXTests.music;

import javaFXTests.App;
import javaFXTests.chat.User;
import javaFXTests.chat.UserDataModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Narayan
 */
public class MusicTable extends Application{

    /**
     * This function gives the full configured TableView 
     * @return TableView
     */
    public void getTableView(TableView<User> table){
//        TableView<User> table = new TableView<>();
        table.setTableMenuButtonVisible(true);
        
        /*
         * Creating the TableColumn for the TableView
         * The property value Factory name must match with the 
         * Generic Class's(Music's) property
         */
        TableColumn<User,Image> icon = new TableColumn<>("Icon");
        icon.setCellValueFactory(new PropertyValueFactory("icon"));
        icon.setPrefWidth(60);
        
        TableColumn<User,String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory("name"));
        name.setPrefWidth(50);
         
        TableColumn<User,Integer> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory("id"));
        id.setPrefWidth(5);

        TableColumn<User,Boolean> online = new TableColumn<>("Online");
        online.setCellValueFactory(new PropertyValueFactory("online"));
        online.setPrefWidth(15);

        icon.setCellFactory(param -> {
            TableCell<User, Image> cell = new TableCell<User, Image>(){
                @Override
                public void updateItem(Image item, boolean empty) {
                    if(item!=null){
                        HBox box= new HBox();
                        box.setSpacing(10) ;

                        ImageView imageview = new ImageView();
                        imageview.setFitHeight(50);
                        imageview.setFitWidth(50);
                        imageview.setImage(item);

                        box.getChildren().addAll(imageview);

                        setGraphic(box);
                    }
                }
            };
            return cell;
        });

        table.getColumns().clear();
        table.getColumns().addAll(icon,name,online);

//        ObservableList<User> users = FXCollections.observableArrayList();

        table.setItems(App.getUserListModel().getUserList());

    }
    
    
    /**
     * This function gives the fancy Background for the application
     * @return 
     */
    public Group getBackground(){
        Group group = new Group(); 
        group.setLayoutX(40); 
        group.setLayoutY(40); 
        
        Rectangle rect = new Rectangle();
        rect.setWidth(600); 
        rect.setHeight(460); 
        rect.setFill(Color.web("#f5f5f5"));
        //Some OuterGlow Effect
        rect.setEffect(DropShadowBuilder.create().                   
                color(Color.web("#969696")).
                offsetX(0).offsetY(0).radius(50).spread(0.2)
                .build());
        
        group.getChildren().add(rect); 
        
        return group;
    }
    
    
    /**
     * Main start function for configuring the GUI Components
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {      
        
        //Main Group of the Application
        Group group = getBackground();
        //VBox for the Text and Table
        VBox box = new VBox();
        box.setLayoutX(20);
        box.setLayoutY(5);
        box.setSpacing(15); 
        
        //Text
        Text text = new Text("Music Library");
        text.setFont(new Font(20)); 
        
        //Table
//        TableView table = initializeUserList();
//        table.setLayoutX(20); table.setLayoutY(20);
//
//        adding all components
//        box.getChildren().addAll(text,table);
        group.getChildren().add(box); 
        
        
        Scene scene = new Scene(group,680,530,Color.web("#666666"));
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
