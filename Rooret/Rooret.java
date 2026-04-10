import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class Rooret extends Application{
    Image img1 = new Image("ダウンロード.png");
    Image img2 = new Image("ダウンロード2.png");
    Image img3 = new Image("ダウンロード3.png");
    Image img4 = new Image("ダウンロード4.png");
    Image img5 = new Image("ダウンロード５.jpeg");
    Image atari = new Image("当たり.png");
    Image hazure = new Image("ハズレ.png");
    ImageView imgv1,imgv2,imgv3,kekka;
    Timeline timeline;
    Button btn,btn1,btn2,btn3,btnkekka;
    ComboBox<String> combo = new ComboBox<>();
    int kekkaatai = 0;
    int stp1 = 0;
    int stp2 = 0;
    int stp3 = 0;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    String iii;
    int ii = 0;
    double i = 5;
    AnchorPane root = new AnchorPane();
    Group group = new Group();


    public void start(Stage stage)throws Exception{
        stage.setTitle("Rooret");
        stage.setWidth(600);
        stage.setHeight(400);
        kekka = new ImageView();
        imgv1 = new ImageView(img1);
        imgv2 = new ImageView(img1);
        imgv3 = new ImageView(img1);
        imgv1.setX(0);
        imgv1.setY(60);
        imgv2.setX(200);
        imgv2.setY(60);
        imgv3.setX(400);
        imgv3.setY(60);

        combo.getItems().addAll(
            "1","2","3","4","5","6","7","8","9","10"
        );
        combo.setEditable(true);
        combo.setOnAction(e -> sokudo());


        btn1 = new Button();
        btn1.setPrefWidth(100);
        btn1.setPrefHeight(100);
        btn1.setBackground(new Background(new BackgroundFill( Color.RED , new CornerRadii(10) , new Insets(5) )));
        btn1.setDisable(true);

        btn2 = new Button();
        btn2.setPrefWidth(100);
        btn2.setPrefHeight(100);
        btn2.setBackground(new Background(new BackgroundFill( Color.RED , new CornerRadii(10) , new Insets(5) )));
        btn2.setDisable(true);

        btn3 = new Button();
        btn3.setPrefWidth(100);
        btn3.setPrefHeight(100);
        btn3.setBackground(new Background(new BackgroundFill( Color.RED , new CornerRadii(10) , new Insets(5) )));
        btn3.setDisable(true);

        AnchorPane.setTopAnchor(btn1,275.0);
        AnchorPane.setLeftAnchor(btn1,50.0);
        AnchorPane.setTopAnchor(btn2,275.0);
        AnchorPane.setLeftAnchor(btn2,250.0);
        AnchorPane.setTopAnchor(btn3,275.0);
        AnchorPane.setLeftAnchor(btn3,450.0);

        btn = new Button("回す");
        btn.setPrefWidth(50);
        btn.setPrefHeight(50);
        AnchorPane.setTopAnchor(btn,0.0);
        AnchorPane.setLeftAnchor(btn,275.0);
        btn.setOnAction(event -> kaiten(btn,btn1,btn2,btn3));

        btnkekka = new Button("結果表示");
        btnkekka.setPrefWidth(200);
        btnkekka.setPrefHeight(50);
        AnchorPane.setTopAnchor(btnkekka,0.0);
        AnchorPane.setLeftAnchor(btnkekka,400.0);
        btnkekka.setOnAction(e -> actionkekka());


        root.getChildren().addAll(btn,btn1,btn2,btn3,btnkekka);
        group.getChildren().addAll(root,imgv1,imgv2,imgv3,combo,kekka);
        stage.setScene(new Scene(group));
        stage.show();

    }

    void kaiten(Button btn,Button btn1,Button btn2,Button btn3){
        btn.setDisable(true);
        btn1.setDisable(false);
        btn2.setDisable(false);
        btn3.setDisable(false);
        Timeline timeline1 = new Timeline(
            new KeyFrame(Duration.millis(0.0),e->action1a(e)),
            new KeyFrame(Duration.millis(i*10),e->action2a(e)),
            new KeyFrame(Duration.millis(i*20),e->action3a(e)),
            new KeyFrame(Duration.millis(i*30),e->action4a(e)),
            new KeyFrame(Duration.millis(i*40),e->action5a(e)),
            new KeyFrame(Duration.millis(i*50)));
        timeline1.setCycleCount(Timeline.INDEFINITE);

        Timeline timeline2 = new Timeline(
            new KeyFrame(Duration.millis(0.0),e->action1b(e)),
            new KeyFrame(Duration.millis(i*10),e->action2b(e)),
            new KeyFrame(Duration.millis(i*20),e->action3b(e)),
            new KeyFrame(Duration.millis(i*30),e->action4b(e)),
            new KeyFrame(Duration.millis(i*40),e->action5b(e)),
            new KeyFrame(Duration.millis(i*50)));
        timeline2.setCycleCount(Timeline.INDEFINITE);

        Timeline timeline3 = new Timeline(
            new KeyFrame(Duration.millis(0.0),e->action1c(e)),
            new KeyFrame(Duration.millis(i*10),e->action2c(e)),
            new KeyFrame(Duration.millis(i*20),e->action3c(e)),
            new KeyFrame(Duration.millis(i*30),e->action4c(e)),
            new KeyFrame(Duration.millis(i*40),e->action5c(e)),
            new KeyFrame(Duration.millis(i*50)));
        timeline3.setCycleCount(Timeline.INDEFINITE);
        btn1.setOnAction(e -> stop1(e,timeline1,btn,btn1));
        btn2.setOnAction(e -> stop2(e,timeline2,btn,btn2));
        btn3.setOnAction(e -> stop3(e,timeline3,btn,btn3));
        timeline1.play();
        timeline2.play();
        timeline3.play();
    }

    void action1a(ActionEvent e){
        imgv1.setImage(img1);
        i1 = 1;
    }

    void action2a(ActionEvent e){
        imgv1.setImage(img2);
        i1 = 2;
    }

    void action3a(ActionEvent e){
        imgv1.setImage(img3);
        i1 = 3;
    }

    void action4a(ActionEvent e){
        imgv1.setImage(img4);
        i1 = 4;
    }

    void action5a(ActionEvent e){
        imgv1.setImage(img5);
        i1 = 5;
    }

    void action1b(ActionEvent e){
        imgv2.setImage(img2);
        i2 = 2;
    }

    void action2b(ActionEvent e){
        imgv2.setImage(img4);
        i2 = 4;
    }

    void action3b(ActionEvent e){
        imgv2.setImage(img3);
        i2 = 3;
    }

    void action4b(ActionEvent e){
        imgv2.setImage(img5);
        i2 = 5;
    }

    void action5b(ActionEvent e){
        imgv2.setImage(img1);
        i2 = 1;
    }

    void action1c(ActionEvent e){
        imgv3.setImage(img5);
        i3 = 5;
    }

    void action2c(ActionEvent e){
        imgv3.setImage(img3);
        i3 = 3;
    }

    void action3c(ActionEvent e){
        imgv3.setImage(img4);
        i3 = 4;
    }

    void action4c(ActionEvent e){
        imgv3.setImage(img2);
        i3 = 2;
    }

    void action5c(ActionEvent e){
        imgv3.setImage(img1);
        i3 = 1;
    }

    void stop1(ActionEvent e,Timeline timeline1,Button btn,Button btn1){
        timeline1.pause();
        stp1 = 1;
        btn1.setDisable(true);
        if(stp1 == 1 && stp2 == 1 && stp3 == 1){
            setbtn(btn);
        }
    }

    void stop2(ActionEvent e,Timeline timeline2,Button btn,Button btn2){
        timeline2.pause();
        stp2 = 1;
        btn2.setDisable(true);
        if(stp1 == 1 && stp2 == 1 && stp3 == 1){
            setbtn(btn);
        }
    }

    void stop3(ActionEvent e,Timeline timeline3,Button btn,Button btn3){
        timeline3.pause();
        stp3 = 1;
        btn3.setDisable(true);
        if(stp1 == 1 && stp2 == 1 && stp3 == 1){
            setbtn(btn);
        }
    }

    void setbtn(Button btn){

        stp1 = stp2 = stp3 = 0;
        kekka.setX(-25);
        kekka.setY(-25);

        if(kekkaatai == 0){
            kekkahyouji();
        }
        if(kekkaatai == 1){
            btn.setDisable(false);
        }


    }

    void kekkahyouji(){
        timeline = new Timeline(
            new KeyFrame(Duration.millis(250.0),e->hyouji()),
            new KeyFrame(Duration.millis(750.0),e->nashi()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    void hyouji(){
        if((i1 == i2 && i2 == i3)){
            kekka.setImage(atari);
        }
        else{
            kekka.setImage(hazure);
        }
    }

    void nashi(){
        kekka.setImage(null);
        timeline.pause();
        btn.setDisable(false);
    }

    void actionkekka(){
        kekkaatai++;
        if(kekkaatai == 2){
            kekkaatai = 0;
        }
        if(kekkaatai == 0){
            btnkekka.setText("結果表示");
        }
        if(kekkaatai == 1){
            btnkekka.setText("結果非表示");
        }
    }

    void sokudo(){
        ii = Integer.parseInt(combo.getValue());
        i = 11 - ii;
    }
}
