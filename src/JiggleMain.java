
import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael
 */
public class JiggleMain extends Application
{
    int menuSize = 375;
    int menuSizeTwo = 60;
    int delayAmount = 1000;//how many ms between iterations?
    @Override
    public void start(Stage primaryStage)
    {
        VBox holder = new VBox();
        HBox inVBox = new HBox();
        Text message = new Text("Chrome has to be full screen and on the upload page");
        Button btJiggle = new Button("Jiggle the mouse pls");//button to jiggle the mouse
        btJiggle.setOnAction(new jigglehandler());//wire the button to jiggle the mouse
        inVBox.getChildren().add(btJiggle);//put the button in the stage
        holder.getChildren().add(message);
        holder.getChildren().add(inVBox);//then put the text and the group from earlier in the window
        Scene primscene = new Scene(holder,menuSize,menuSizeTwo);
        primaryStage.setScene(primscene);//make the scene with all the stuff in it and set it to the main window
        primaryStage.show();//show the main window
    }
    class jigglehandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            try
            {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) b.getX();
                int y = (int) b.getY();
                Robot bot = new Robot();
                bot.delay(10000);//wait ten seconds to let me alt tab over to chrome
                Color check = bot.getPixelColor(635,234);
                Color spaceone = bot.getPixelColor(645,240); 
                Color spacetwo = bot.getPixelColor(1080,230); 
                Color spacethree = bot.getPixelColor(1260,215); 
                Color spacefour = bot.getPixelColor(1392,243);
                boolean keepgoing = true;
                while(keepgoing)
                {
                    check = bot.getPixelColor(635,234);
                    spaceone = bot.getPixelColor(645,240); 
                    spacetwo = bot.getPixelColor(1080,230); 
                    spacethree = bot.getPixelColor(1260,215); 
                    spacefour = bot.getPixelColor(1392,243);
                    keepgoing = !(check.getRGB() == spaceone.getRGB() && spaceone.getRGB() == spacetwo.getRGB() && spacetwo.getRGB() == spacethree.getRGB() && spacethree.getRGB() == spacefour.getRGB());
                    //i.e. it's false (and therefore ends the loop) if all the points we check are the same color (the bar has switched to processing and has filled to at least 95%)
                    bot.mouseMove(0,0);
                    bot.delay(delayAmount);
                    bot.mouseMove(5, 5);
                }
            }
            catch (AWTException ex) {
                Stage ErrorStage = new Stage();
                HBox inside = new HBox();
                Scene ErrorScene = new Scene(inside,menuSize,menuSizeTwo);
                Text error = new Text("The Bot Is A N G E R");
                inside.getChildren().add(error);
                ErrorStage.setScene(ErrorScene);
                ErrorStage.show();
            }
        }
    
    }
    public static void main(String[] args) {
        launch(args);
    }
}
