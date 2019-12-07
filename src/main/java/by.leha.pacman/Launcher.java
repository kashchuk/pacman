package by.leha.pacman;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class Launcher extends Application {

    Map map;
    Referee referee;
    Duration duration = Duration.millis(200);
    List<CreatureJavaFxModel> figures = new ArrayList<>();
    int scale = 40;

    @Override
    public void start(Stage stage) throws Exception {

        //initMap
        stage.setTitle("PAC-MAN");
        int startX = 200; //Refactor
        int startY = 200; //Refactor
        int mapHeight = 19; //Refactor
        int mapWidth = 17; //Refactor
        referee = new Referee();
        map = new Map(mapHeight, mapWidth, scale, referee);
        Animator animator = new Animator(map, scale);
        Group root = new Group();
        Group board = new Group();
        Group footer = new Group();
        footer.setLayoutX(100);
        footer.setLayoutY(map.getHeight() * scale);
        Text text = new Text();
        text.setFont(new Font(20));
        text.setText("Simple text");
        footer.getChildren().add(text);
        root.getChildren().add(board);
        root.getChildren().add(footer);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Canvas canvas = new Canvas(map.getHeight() * scale, map.getWidth() * scale);
        board.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        map.settleGhosts();
        map.settlePacman();
        Drawer drawer = new Drawer(map, scale, graphicsContext, footer, referee);
        drawer.drawTheMap();
        drawer.drawTheFooter();

        figures.add(new PacmanJavaFxModel(map.getThePacman(), board, scale, duration, Color.ORANGE));
        List<Ghost> ghosts = map.getGhosts();
        figures.add(new GhostJavaFxModel(ghosts.get(0), board, scale, duration, Color.RED));
        figures.add(new GhostJavaFxModel(ghosts.get(1), board, scale, duration, Color.BLUE));
        scene.setOnKeyPressed(new KeyEventHandler(figures.get(0)));

        stage.show();
        //*********************************************************
        figures.get(0).animate();
        figures.get(1).animate();
        figures.get(2).animate();

        GameCycle transition = new GameCycle(figures, scale);
        transition.setOnFinished(new CycleFinishedEventHandler(figures, transition, drawer));
        transition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
