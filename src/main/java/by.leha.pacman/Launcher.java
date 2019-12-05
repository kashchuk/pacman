package by.leha.pacman;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;


public class Launcher extends Application {

    Map map;
    CreatureJavaFxModel[] figures = new CreatureJavaFxModel[4];
    int scale = 50;

    @Override
    public void start(Stage stage) throws Exception {

        //initMap
        stage.setTitle("PAC-MAN");
        int startX = 200; //Refactor
        int startY = 200; //Refactor
        int mapHeight = 19; //Refactor
        int mapWidth = 17; //Refactor
        map = new Map(mapHeight, mapWidth, scale);
        Animator animator = new Animator(map, scale);
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Canvas canvas = new Canvas(map.getHeight() * scale, map.getWidth() * scale);
        root.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        map.settleGhosts();
        map.settlePacman();
        map.drawMap(graphicsContext);
        scene.setOnKeyPressed(new KeyEventHandler(map));

        //init pacman
        figures[0] = new PacmanJavaFxModel(map.getThePacman(), root, scale);

        //init ghosts
        List<Ghost> ghosts = map.getGhosts();
        figures[1] = new GhostJavaFxModel(ghosts.get(0), root, scale, Color.RED);

        stage.show();
        //*********************************************************
        figures[0].animate();
        figures[1].animate();
        //*********************************************************


        Transition transition = new Transition() {

            {
                setCycleDuration(Duration.millis(200));
                setInterpolator(Interpolator.LINEAR);
                setCycleCount(1);
                setAutoReverse(false);

            }

            @Override
            protected void interpolate(double fraction) {
//                double startGhostX = inhabitants[1].getPreviousX() * scale;
//                double startGhostY = inhabitants[1].getPreviousY() * scale;
//                double endGhostX = inhabitants[1].getX() * scale; //Refactor
//                double endGhostY = inhabitants[1].getY() * scale; //Refactor
//                inhabitants[1].getPane().setLayoutX(getInterpolator().interpolate(startGhostX, endGhostX, fraction));
//                inhabitants[1].getPane().setLayoutY(getInterpolator().interpolate(startGhostY, endGhostY, fraction));
                for (int i = 0; i < 2; i++) {
                    double startX = figures[i].getPreviousX() * scale;
                    double startY = figures[i].getPreviousY() * scale;
                    double endX = figures[i].getX() * scale; //Refactor
                    double endY = figures[i].getY() * scale; //Refactor
                    figures[i].getPane().setLayoutX(getInterpolator().interpolate(startX, endX, fraction));
                    figures[i].getPane().setLayoutY(getInterpolator().interpolate(startY, endY, fraction));
                }
            }
        };

        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                figures[1].move();
                figures[0].move();
                transition.play();
            }
        };

        transition.setOnFinished(onFinished);
        transition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
