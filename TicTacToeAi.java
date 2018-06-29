import javax.swing.*;
import java.util.*;
import javafx.scene.input.MouseEvent ;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.JPanel ;
import javafx.scene.layout.Pane; 
import javafx.scene.Scene;
import javafx.scene.shape.*; 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color ;
import javafx.scene.text.*; 

public class TicTacToeAi extends Application{

    @Override
    public void start(Stage primaryStage) {
      Pane pane = new Pane();
      Line line1 = new Line(250,100,250,550);
      Line line2 = new Line(400, 100, 400, 550);
      Line line3 = new Line(100, 250, 550, 250);
      Line line4 = new Line(100, 400, 550, 400);
      pane.getChildren().addAll(line1, line2, line3, line4);
      Text title = new Text (153,90, "Tic-Tac-Toe");
      title.setFont(new Font(70));
      pane.getChildren().add(title);
      Scene scene = new Scene(pane, 650, 650);
      primaryStage.setScene(scene); 
       primaryStage.setTitle("Tic-Tac-Toe");
      primaryStage.show();
      scene.setOnMousePressed(new EventHandler<MouseEvent>() {
          int numClicks = 0;
          int lastTurn;
          String[] placement = new String [9];
          boolean win = false;
          boolean xwin = false;
          boolean owin = false;
      
         @Override
         public void handle(MouseEvent e) {
            int x = (int)e.getSceneX();
            int y = (int)e.getSceneY();
            String turn = "";
            if(numClicks%2==0){
               turn = "x";            
               //else  turn = "o";
               Text text = new Text(turn);
               text.setFont(new Font(200));
               int column =  (x-100)/150;
               int row = (y-100)/150;
               if(row<3 && column <3 && !win){
                  if(placement[row*3+column] != "x" && placement[row*3+column] != "o"){
                     numClicks++;
                     placement[row*3+column] = turn;
                     lastTurn = row*3+column;
                     if(column !=3 && row !=3){   
                        text.relocate(column*150+125, row*150+5); 
                        pane.getChildren().add(text);
                     }  
                     for(int i = 0; i<7; i= i+3){
                        if (placement[i] == "x" && placement[i+1] == "x" && placement[i+2] == "x"){
                           xwin = true;
                         }
                    }
                    for(int i = 0; i<3; i++){
                        if (placement[i] == "x" && placement[i+3] == "x" && placement[i+6] == "x"){
                       xwin = true;
                       }
                     }
                     
                     if (placement[0] == "x" && placement[4] == "x" && placement[8] == "x"){
                        xwin = true;
                     }
                    
                     if (placement[2] == "x" && placement[4] == "x" && placement[6] == "x"){
                        xwin = true;
                     }
                      
                     if(numClicks == 9 && !xwin && !owin){
                        Text draw = new Text ("IT'S A DRAW");
                        draw.setFont(new Font(100));
                        draw.relocate(60,500);
                        pane.getChildren().add(draw);   
                    }                  
                     if(xwin == true){
                        win = true;    
                        System.out.println("You win"); 
                        Text winT = new Text ('"' +turn.toUpperCase() + '"' + " WINS!!");
                        winT.setFont(new Font(120));
                        winT.relocate(60,500);
                        pane.getChildren().add(winT);   
                     }
                  }
                }
              }
               if(!xwin){
               boolean oplayed = false;
                turn = "o";
                //if first turn
                if(numClicks == 1){
                  if(placement[4] == "x"){
                     placement[0] = turn;
                     Text text = new Text(turn);
                     text.setFont(new Font(200));
                     text.relocate(0*150+125, 0*150+5);  
                     pane.getChildren().add(text); 
                     numClicks++;
                     oplayed = true;                  
                  }
                  else{
                     placement [4] = turn;
                     Text text = new Text(turn);
                     text.setFont(new Font(200));
                     text.relocate(1*150+125, 1*150+5);  
                     pane.getChildren().add(text);
                     numClicks++;
                     oplayed = true;                     
                  }
               }
                           
               else if (numClicks != 9){
                     //check for two in a row
                     for (int i = 0; i<3; i++){
                        int doubleNum = 0;
                        String value ="";
                        int empty = -1;
                        for (int j = 0; j<3; j++){     
                           if(placement[i*3+j] != null && value == ""){
                              value = placement[i*3+j];
                            }
                            if(placement[i*3+j] == null){
                             empty = i*3+j; 
                            }
                             if(value != "" && placement[i*3+j] != null && placement[i*3+j].equals(value)){
                            doubleNum++;
                            }
                            
                            if(doubleNum == 2 && empty != -1){
                              placement[empty] = "o";
                              int col = empty%3;
                              int row = empty/3;
                              Text t = new Text(turn);
                              t.setFont(new Font(200));
                              t.relocate(col*150+125, row*150+5);  
                              pane.getChildren().add(t);
                              numClicks++;
                              oplayed = true;  
                              }
                        }
                     }
                     if (!oplayed){
                        //check for two in a col
                        for (int i = 0; i<3; i++){
                           int doubleNum = 0;
                           String value ="";
                           int empty = -1;
                           for (int j = 0; j<3; j++){     
                              if(placement[j*3+i] != null && value == ""){
                                 value = placement[j*3+i];
                               }
                               if(placement[j*3+i] == null){
                                empty = j*3+i; 
                               }
                                if(value != "" && placement[j*3+i] != null && placement[j*3+i].equals(value)){
                               doubleNum++;
                               }
                               
                               if(doubleNum == 2 && empty != -1){
                                 placement[empty] = "o";
                                 int col = empty%3;
                                 int row = empty/3;
                                 Text t = new Text(turn);
                                 t.setFont(new Font(200));
                                 t.relocate(col*150+125, row*150+5);  
                                 pane.getChildren().add(t);
                                 numClicks++;
                                 oplayed = true;  
                                 }
                           }
                        } 
                     } 
                     if (!oplayed){
                        //check for two in a diagnol down right
                        for (int i = 0; i<3; i++){
                           int doubleNum = 0;
                           String value ="";
                           int empty = -1;
                              if(placement[i*3+i] != null && value == ""){
                                 value = placement[i*3+i];
                               }
                               if(placement[i*3+i] == null){
                                empty = i*3+i; 
                               }
                                if(value != "" && placement[i*3+i] != null && placement[i*3+i].equals(value)){
                               doubleNum++;
                               }
                               
                               if(doubleNum == 2 && empty != -1){
                                 placement[empty] = "o";
                                 int col = empty%3;
                                 int row = empty/3;
                                 Text t = new Text(turn);
                                 t.setFont(new Font(200));
                                 t.relocate(col*150+125, row*150+5);  
                                 pane.getChildren().add(t);
                                 numClicks++;
                                 oplayed = true;  
                                 }
                        } 
                     } 
                      if (!oplayed){
                        //check for two in a diagnol left
                        for (int i = 2; i>-1; i--){
                           int doubleNum = 0;
                           String value ="";
                           int empty = -1;
                           for (int j = 0; j<3; j++){     
                              if(placement[j*3+i] != null && value == ""){
                                 value = placement[j*3+i];
                               }
                               if(placement[j*3+i] == null){
                                empty = j*3+i; 
                               }
                                if(value != "" && placement[j*3+i] != null && placement[j*3+i].equals(value)){
                               doubleNum++;
                               }
                               
                               if(doubleNum == 2 && empty != -1){
                                 placement[empty] = "o";
                                 int col = empty%3;
                                 int row = empty/3;
                                 Text t = new Text(turn);
                                 t.setFont(new Font(200));
                                 t.relocate(col*150+125, row*150+5);  
                                 pane.getChildren().add(t);
                                 numClicks++;
                                 oplayed = true;  
                                 }
                           }
                        } 
                     } 
                     if(lastTurn == 7 && !oplayed ){
                        if(placement [lastTurn-1] != "x" && placement [lastTurn-1] != "o"){
                           placement [lastTurn-1] = "o";
                           int col = lastTurn%3 - 1;
                           int row = lastTurn/3;
                           Text text = new Text(turn);
                           text.setFont(new Font(200));
                           text.relocate(col*150+125, row*150+5);  
                           pane.getChildren().add(text);
                           numClicks++;
                           oplayed = true;                        
                         }
                         else if(placement [lastTurn+1] != "x" && placement [lastTurn+1] != "o"){
                           placement [lastTurn+1] = "o";
                           int col = lastTurn%3 + 1;
                           int row = lastTurn/3;
                           Text text = new Text(turn);
                           text.setFont(new Font(200));
                           text.relocate(col*150+125, row*150+5);  
                           pane.getChildren().add(text);
                           numClicks++;  
                           oplayed = true;                      
                         }                           
                     } 
                     if(lastTurn == 1 && !oplayed){
                        if(placement [lastTurn-1] != "x" && placement [lastTurn-1] != "o"){
                           placement [lastTurn-1] = "o";
                           int col = lastTurn%3 - 1;
                           int row = lastTurn/3;
                           Text text = new Text(turn);
                           text.setFont(new Font(200));
                           text.relocate(col*150+125, row*150+5);  
                           pane.getChildren().add(text);
                           numClicks++;  
                           oplayed = true;                     
                         }
                         else if(placement [lastTurn+1] != "x" && placement [lastTurn+1] != "o"){
                           placement [lastTurn+1] = "o";
                           int col = lastTurn%3 + 1;
                           int row = lastTurn/3;
                           Text text = new Text(turn);
                           text.setFont(new Font(200));
                           text.relocate(col*150+125, row*150+5);  
                           pane.getChildren().add(text);
                           numClicks++;  
                           oplayed = true; 
                                                
                         }                           
                     } 
                     if(lastTurn == 3 && !oplayed){
                        if(placement [lastTurn-3] != "x" && placement [lastTurn-3] != "o"){
                           placement [lastTurn-3] = "o";
                           int col = lastTurn%3;
                           int row = lastTurn/3-1;
                           Text text = new Text(turn);
                           text.setFont(new Font(200));
                           text.relocate(col*150+125, row*150+5);  
                           pane.getChildren().add(text);
                           numClicks++;   
                           oplayed = true;                     
                         }
                         else if(placement [lastTurn+3] != "x" && placement [lastTurn+3] != "o"){
                           placement [lastTurn+3] = "o";
                           int col = lastTurn%3;
                           int row = lastTurn/3+1;
                           Text text = new Text(turn);
                           text.setFont(new Font(200));
                           text.relocate(col*150+125, row*150+5);  
                           pane.getChildren().add(text);
                           numClicks++;  
                           oplayed = true;                      
                         }                           
                     }
                     if(lastTurn == 5 && !oplayed){
                        if(placement [lastTurn-3] != "x" && placement [lastTurn-3] != "o"){
                           placement [lastTurn-3] = "o";
                           int col = lastTurn%3;
                           int row = lastTurn/3-1;
                           Text text = new Text(turn);
                           text.setFont(new Font(200));
                           text.relocate(col*150+125, row*150+5);  
                           pane.getChildren().add(text);
                           numClicks++;        
                           oplayed = true;                
                         }
                         else if(placement [lastTurn+3] != "x" && placement [lastTurn+3] != "o"){
                           placement [lastTurn+3] = "o";
                           int col = lastTurn%3;
                           int row = lastTurn/3+1;
                           Text text = new Text(turn);
                           text.setFont(new Font(200));
                           text.relocate(col*150+125, row*150+5);  
                           pane.getChildren().add(text);
                           numClicks++; 
                           oplayed = true;                       
                         }                           
                     }
                     if(!oplayed){
                        while (!oplayed){
                           int r = (int) (Math.random()*8);
                           if (placement [r] == null){
                              placement [r] = "o";
                              int col = r%3;
                              int row = r/3;
                              Text oTurn = new Text("o");
                              oTurn.setFont(new Font(200));
                              oTurn.relocate(col*150+125, row*150+5);  
                              pane.getChildren().add(oTurn);
                              numClicks++; 
                              oplayed = true; 
                           }
                        } 
                     }          
                     for(int i = 0; i<7; i= i+3){
                        if (placement[i] == "o" && placement[i+1] == "o" && placement[i+2] == "o"){
                           owin = true;
                        }
                     }
                     for(int i = 0; i<3; i++){
                        if (placement[i] == "o" && placement[i+3] == "o" && placement[i+6] == "o"){
                           owin = true;
                        }
                     }
                     
                     if (placement[0] == "o" && placement[4] == "o" && placement[8] == "o"){
                        owin = true;
                     }
                     
                     if (placement[2] == "o" && placement[4] == "o" && placement[6] == "o"){
                        owin = true;
                     }
                     if(numClicks == 9 && !owin && !xwin){
                        Text draw = new Text ("IT'S A DRAW");
                        draw.setFont(new Font(100));
                        draw.relocate(60,500);
                        pane.getChildren().add(draw);   
                    }                  
                     if(owin == true){
                        win = true;    
                        System.out.println("You win"); 
                        Text winT = new Text ("O" + " WINS!!");
                        winT.setFont(new Font(120));
                        winT.relocate(60,500);
                        pane.getChildren().add(winT);   
                     }            
                  }                  
                }
                              System.out.println(numClicks);             
 
              } 
         });
}
   
   public static void main(String[] args){
     launch(args);
   }
}

      