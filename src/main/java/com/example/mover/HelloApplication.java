package com.example.mover;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    int x = 0;
    ImageView gov = new ImageView();

    Button button = new Button("Play Again");
    TranslateTransition tt0 = new TranslateTransition(Duration.millis(800));
    TranslateTransition tt1 = new TranslateTransition(Duration.millis(10000));
    TranslateTransition tt2 = new TranslateTransition(Duration.millis(15000));
    TranslateTransition tt3 = new TranslateTransition(Duration.millis(14000));
    TranslateTransition tt4 = new TranslateTransition(Duration.millis(9000));
    TranslateTransition coint1 = new TranslateTransition(Duration.millis(10000));
    TranslateTransition coint2 = new TranslateTransition(Duration.millis(20000));
    Label score = new Label();
    BorderPane borderPane = new BorderPane();
    HBox t = new HBox();
    String src = getClass().getResource("Helicopter_Sound_Effect_-_Flying_5_minutes(256k).mp3").toExternalForm();

    Media media = new Media(src);
    MediaPlayer player = new MediaPlayer(media);
    @Override
    public void start(Stage stage) throws IOException {

        score.setFont(Font.font(50));
        HBox Score = new HBox();
        Score.getChildren().add(score);

        t.getChildren().add(button);
        button.setVisible(false);


        MediaView mediaView = new MediaView();
        mediaView.setMediaPlayer(player);
        player.setAutoPlay(true);

        Image img =  new Image("bcknd.jpg");
        BackgroundImage Bimg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(Bimg);
        borderPane.setBackground(background);

        Image plane = new Image("plane.png");
        ImageView view = new ImageView(plane);
        view.setY(200);
        view.setX(200);
        view.setFitHeight(90);
        view.setPreserveRatio(true);

        tt0.setNode(view);
        tt0.setByY(60);
        tt0.setFromY(0);
        tt0.setCycleCount(10000);
        tt0.setAutoReverse(true);
        tt0.play();


        //coins

        Image coin1 = new Image("coin.png");
        ImageView coinv1 = new ImageView(coin1);
        coinv1.setY(400);
        coinv1.setX(100);
        coinv1.setFitHeight(100);
        coinv1.setPreserveRatio(true);


        coint1.setNode(coinv1);
        coint1.setByX(-1000);
        coint1.setFromX(900);
        coint1.setCycleCount(6000);
        coint1.play();

        Image coin2 = new Image("coin.png");
        ImageView coinv2 = new ImageView(coin2);
        coinv2.setY(100);
        coinv2.setX(0);
        coinv2.setFitHeight(100);
        coinv2.setPreserveRatio(true);


        coint2.setNode(coinv2);
        coint2.setByX(-4000);
        coint2.setFromX(3500);
        coint2.setCycleCount(6000);
        coint2.play();


        //clouds

        Image cloud1 = new Image("cloud2.png");
        ImageView view1 = new ImageView(cloud1);
        view1.setFitHeight(120);
        view1.setX(100);
        view1.setY(0);
        view1.setPreserveRatio(true);


        tt1.setNode(view1);
        tt1.setByX(-1000);
        tt1.setFromX(900);
        tt1.setCycleCount(80);
        tt1.play();

        Image cloud2 = new Image("cloud2.png");
        ImageView view2 = new ImageView(cloud2);
        view2.setFitHeight(120);
        view2.setX(1000);
        view2.setY(200);
        view2.setPreserveRatio(true);


        tt2.setNode(view2);
        tt2.setByX(-2500);
        tt2.setFromX(900);
        tt2.setCycleCount(80);
        tt2.play();

        Image cloud3 = new Image("cloud2.png");
        ImageView view3 = new ImageView(cloud3);
        view3.setFitHeight(120);
        view3.setX(2020);
        view3.setY(400);
        view3.setPreserveRatio(true);


        tt3.setNode(view3);
        tt3.setByX(-3000);
        tt3.setFromX(900);
        tt3.setCycleCount(80);
        tt3.play();

        Image cloud4 = new Image("cloud2.png");
        ImageView view4 = new ImageView(cloud4);
        view4.setFitHeight(120);
        view4.setX(1000);
        view4.setY(350);
        view4.setPreserveRatio(true);


        tt4.setNode(view4);
        tt4.setByX(-2000);
        tt4.setFromX(900);
        tt4.setCycleCount(80);
        tt4.play();

        //game Over
        Scene scene = new Scene(borderPane, 1190, 710);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event ->{
            double y = view.getY();
            double x = view.getX();


            switch (event.getCode())
            {
                case RIGHT -> view.setX(x+10);
                case UP -> view.setY(y-10);
                case DOWN -> view.setY(y+10);
            }


        });
        gov.setFitHeight(400);
        gov.setPreserveRatio(true);

        AnimationTimer collision = new AnimationTimer() {
            @Override
            public void handle(long now) {
                CheckCollision(view,view1,view2,view3,view4,coinv1,coinv2);

            }
        };
        borderPane.setCenter(gov);
        borderPane.setTop(score);
        borderPane.setBottom(t);
        t.setAlignment(Pos.BOTTOM_CENTER);
        button.setPrefSize(100, 40);
        borderPane.getChildren().addAll(view,view1,view2,view3,view4,coinv1,coinv2,mediaView);
        collision.start();
        stage.setTitle("An Aeroplane");
        stage.setScene(scene);
        stage.show();
    }

    private void CheckCollision(ImageView view, ImageView view1,ImageView view2, ImageView view3,
                                ImageView view4,ImageView coinv1,ImageView coinv2) {
        if(view.getBoundsInParent().intersects(view1.getBoundsInParent()) ||
                view.getBoundsInParent().intersects(view2.getBoundsInParent()) ||
                view.getBoundsInParent().intersects(view3.getBoundsInParent()) ||
                view.getBoundsInParent().intersects(view4.getBoundsInParent()))
        {
            button.setVisible(true);
            gov.setImage(new Image("go.png"));
            tt0.stop();
            tt1.stop();
            tt2.stop();
            tt3.stop();
            tt4.stop();
            coint1.stop();
            coint2.stop();
            player.stop();
            button.setText("Play Again");
            button.setOnMouseClicked((event)->{
                Stage st = new Stage();
                try {
                    HelloApplication hello = new HelloApplication();
                    hello.start(st);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });


        } else if (view.getBoundsInParent().intersects(coinv1.getBoundsInParent()))
                 {
            x++;
            score.setText("Score: "+ x);
            //coinv1.setVisible(false);
                     coinv1.setImage(null);

        } else if (view.getBoundsInParent().intersects(coinv2.getBoundsInParent())) {
            x++;
            score.setText("Score: "+ x);
           // coinv2.setVisible(false);
            coinv2.setImage(null);
        }
        
    }

    public static void main(String[] args) {
        launch();
    }
}