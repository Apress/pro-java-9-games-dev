package javafxgame;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.ParallelCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
public class JavaFXGame extends Application {
    Scene scene;
    VBox uiContainer;
    Insets uiPadding;
    ImageView boardGameBackPlate, logoLayer;
    TextFlow infoOverlay;
    BackgroundImage uiBackgroundImage;
    Background uiBackground;
    DropShadow dropShadow;
    ColorAdjust colorAdjust;
    Cylinder pole;
    PointLight light;
    ParallelCamera camera;
    Box Q1S1, Q1S2, Q1S3, Q1S4, Q1S5, Q2S1, Q2S2, Q2S3, Q2S4, Q2S5, Q3S1, Q3S2, Q3S3, Q3S4, Q3S5,
            Q4S1, Q4S2, Q4S3, Q4S4, Q4S5, q1, q2, q3, q4;
    Group root, gameBoard, Q1, Q2, Q3, Q4;
    Image diffuse1, diffuse2, diffuse3, diffuse4, diffuse5, diffuse6, diffuse7, diffuse8, diffuse9,
            diffuse10, diffuse11, diffuse12, diffuse13, diffuse14, diffuse15, diffuse16, diffuse17,
            diffuse18, diffuse19, diffuse20, diffuse21, diffuse22, diffuse23, diffuse24, diffuse25,
            diffuse26, diffuse27, diffuse28, diffuse29, diffuse30, splashScreen, helpLayer, legalLayer,
            creditLayer, scoreLayer, backPlate, alphaLogo;
    PhongMaterial Shader1, Shader2, Shader3, Shader4, Shader5, Shader6, Shader7, Shader8, Shader9,
            Shader10, Shader11, Shader12, Shader13, Shader14, Shader15, Shader16, Shader17, Shader18,
            Shader19, Shader20, Shader21, Shader22, Shader23, Shader24, Shader25;
    Sphere spinner;
    TranslateTransition moveSpinnerOn, moveCameraIn, moveSpinnerOff, moveCameraOut;
    ParallelTransition spinnerAnim, cameraAnimIn, cameraAnimOut;
    RotateTransition rotGameBoard, rotSpinner, rotSpinnerIn, rotCameraDown, rotCameraBack;
    Random random;
    int spinDeg = 45;
    AudioClip spinnerAudio, cameraAudio;
    StackPane uiLayout, qaLayout, scoreLayout;
    Node picked;
    int quadrantLanding, pickS1, pickS2, pickS3, pickS4, pickS5, pickS6, pickS7, pickS8, pickS9,
        pickS10, pickS11, pickS12, pickS13, pickS14, pickS15, pickS16, pickS17, pickS18, pickS19,
        pickS20, rightAnswer, wrongAnswer;
    Text playText, moreText, helpText, cardText, copyText, riteText, credText, codeText,
         scoreTitle, scoreRight, scoreWrong, scoreCheer, rightAnswers, wrongAnswers;
    boolean spinnerClick, buttonClick, squareClick1, squareClick2, squareClick3, squareClick4;
    Button gameButton, helpButton, legalButton, creditButton, scoreButton, a1Button, a2Button, a3Button, a4Button, againButton;
    @Override
    public void start(Stage primaryStage) {
        loadImageAssets();
        loadAudioAssets();
        createSpecialEffects();
        createTextAssets();
        createMaterials();
        createBoardGameNodes();
        againButton.setVisible(false);
        createUInodes();
        createQAnodes();
        qaLayout.setVisible(false);
        createScoreNodes();
        scoreLayout.setVisible(false);
        createQAprocessing();
        createSceneProcessing();
        createGameBoardNodes();
        addNodesToSceneGraph();
        createAnimationAssets();
        primaryStage.setTitle("iTVBoardGame (JavaFX 9 Game)");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
        random = new Random();
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                uiLayout.setVisible(false);
                camera.setTranslateZ(500);
                camera.setTranslateY(-300);
                camera.setTranslateX(-260);
                camera.setRotationAxis(Rotate.X_AXIS);
                camera.setRotate(-30);
                spinnerAnim.play();
            }
        });
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                infoOverlay.getChildren().clear();
                infoOverlay.getChildren().addAll(helpText, cardText);
                infoOverlay.setTranslateX(130);
                infoOverlay.setTranslateY(360);
                uiLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                boardGameBackPlate.setImage(helpLayer);
                logoLayer.setEffect(colorAdjust);
                colorAdjust.setHue(0.4);
            }
        });
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Game High Scores");
            }
        });
        legalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                infoOverlay.getChildren().clear();
                infoOverlay.getChildren().addAll(copyText, riteText);
                infoOverlay.setTranslateX(200);
                infoOverlay.setTranslateY(370);
                uiLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                boardGameBackPlate.setImage(legalLayer);
                logoLayer.setEffect(colorAdjust);
                colorAdjust.setHue(-0.4);
            }
        });
        creditButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                infoOverlay.getChildren().clear();
                infoOverlay.getChildren().addAll(credText, codeText);
                infoOverlay.setTranslateX(240);
                infoOverlay.setTranslateY(370);
                uiLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                boardGameBackPlate.setImage(creditLayer);
                logoLayer.setEffect(colorAdjust);
                colorAdjust.setHue(-0.9);
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
    private void createBoardGameNodes() {
        root = new Group();
        againButton = new Button();
        againButton.setText("Let's Play Again!");
        againButton.setFont(Font.font("Arial Black", 35));
        againButton.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        againButton.setMinSize(300, 200);
        againButton.setTranslateX(190);
        againButton.setTranslateY(-580);
        againButton.setTranslateZ(100);
        againButton.setOnAction((ActionEvent event) -> {
                qaLayout.setVisible(false);
                scoreLayout.setVisible(false);
                againButton.setVisible(false);
                buttonClick = false;
                spinnerClick = false;
                resetTextureMaps();
                cameraAnimOut.play();
                cameraAudio.play();
        });
        spinner = new Sphere(64);
        spinner.setMaterial(Shader25);
        spinner.setTranslateX(-350);
        spinner.setTranslateY(-512);
        spinner.setRotationAxis(Rotate.Y_AXIS);
        spinner.setRotate(30);
        gameBoard = new Group();
        gameBoard.setRotationAxis(Rotate.Y_AXIS);
        gameBoard.setRotate(45);
        Q1 = new Group();
        Q2 = new Group();
        Q3 = new Group();
        Q4 = new Group();
        camera = new ParallelCamera();
        camera.setTranslateZ(0);
        camera.setNearClip(0.1);
        camera.setFarClip(5000.0);
        light = new PointLight(Color.WHITE);
        light.setTranslateY(-25);
        light.getScope().addAll(Q1S1, Q1S2, Q1S3, Q1S4, Q1S5, q1, Q2S1, Q2S2, Q2S3, Q2S4, Q2S5, q2,
                Q3S1, Q3S2, Q3S3, Q3S4, Q3S5, q3, Q4S1, Q4S2, Q4S3, Q4S4, Q4S5, q4, spinner);
    }
    private void createUInodes(){
        uiLayout = new StackPane();
        uiLayout.setTranslateX(0);
        uiLayout.setTranslateY(0);
        uiLayout.setBackground(uiBackground);
        boardGameBackPlate = new ImageView();
        boardGameBackPlate.setImage(splashScreen);
        logoLayer = new ImageView();
        logoLayer.setImage(alphaLogo);
        logoLayer.setScaleX(0.8);
        logoLayer.setScaleY(0.8);
        logoLayer.setTranslateX(-75);
        logoLayer.setTranslateY(-225);
        infoOverlay = new TextFlow();
        infoOverlay.setTranslateX(240);
        infoOverlay.setTranslateY(420);
        uiContainer = new VBox(10);
        uiContainer.setAlignment(Pos.TOP_RIGHT);
        uiPadding = new Insets(16);
        uiContainer.setPadding(uiPadding);
        gameButton = new Button();
        gameButton.setText("Start Game");
        gameButton.setMaxWidth(125);
        helpButton = new Button();
        helpButton.setText("Game Rules");
        helpButton.setMaxWidth(125);
        scoreButton = new Button();
        scoreButton.setText("High Scores");
        scoreButton.setMaxWidth(125);
        legalButton = new Button();
        legalButton.setText("Disclaimers");
        legalButton.setMaxWidth(125);
        creditButton = new Button();
        creditButton.setText("Game Credits");
        creditButton.setMaxWidth(125);
    }
    private void addNodesToSceneGraph() {
        root.getChildren().addAll(gameBoard, uiLayout, qaLayout, scoreLayout, spinner, againButton);
        gameBoard.getChildren().addAll(Q1, Q2, Q3, Q4);
        Q1.getChildren().addAll(q1, Q1S1, Q1S2, Q1S3, Q1S4, Q1S5);
        Q2.getChildren().addAll(q2, Q2S1, Q2S2, Q2S3, Q2S4, Q2S5);
        Q3.getChildren().addAll(q3, Q3S1, Q3S2, Q3S3, Q3S4, Q3S5);
        Q4.getChildren().addAll(q4, Q4S1, Q4S2, Q4S3, Q4S4, Q4S5);
        qaLayout.getChildren().addAll(a1Button, a2Button, a3Button, a4Button);
        scoreLayout.getChildren().addAll(scoreTitle, scoreRight, scoreWrong, scoreCheer, rightAnswers, wrongAnswers);
        uiLayout.getChildren().addAll(boardGameBackPlate, logoLayer, infoOverlay, uiContainer);
        uiContainer.getChildren().addAll(gameButton, helpButton, legalButton, creditButton, scoreButton);
        infoOverlay.getChildren().addAll(playText, moreText);
    }
    private void loadAudioAssets() {
        spinnerAudio = new AudioClip(JavaFXGame.class.getResource("/spinner.wav").toExternalForm());
        spinnerAudio.setCycleCount(AudioClip.INDEFINITE);
        cameraAudio = new AudioClip(JavaFXGame.class.getResource("/camera.wav").toExternalForm());
    }
    private void loadImageAssets() {
        backPlate = new Image("/backplate8.png", 1280, 640, true, false, true);
        alphaLogo = new Image("/alphalogo.png", 1200, 132, true, false, true);
        splashScreen = new Image("/welcome.png", 1280, 640, true, false, true);
        helpLayer = new Image("/instructions.png", 1280, 640, true, false, true);
        legalLayer = new Image("/copyrights.png", 1280, 640, true, false, true);
        creditLayer = new Image("/credits.png", 1280, 640, true, false, true);
        scoreLayer = new Image("/highscores.png", 1280, 640, true, false, true);
        diffuse1 = new Image("/gameboardsquare.png", 256, 256, true, true, true);
        diffuse2 = new Image("/gameboardsquare2.png", 256, 256, true, true, true);
        diffuse3 = new Image("/gameboardsquare3.png", 256, 256, true, true, true);
        diffuse4 = new Image("/gameboardsquare4.png", 256, 256, true, true, true);
        diffuse5 = new Image("/gameboardsquare5.png", 256, 256, true, true, true);
        diffuse6 = new Image("/gameboardsquare6.png", 256, 256, true, true, true);
        diffuse7 = new Image("/gameboardsquare7.png", 256, 256, true, true, true);
        diffuse8 = new Image("/gameboardsquare8.png", 256, 256, true, true, true);
        diffuse9 = new Image("/gameboardsquare9.png", 256, 256, true, true, true);
        diffuse10 = new Image("/gameboardsquare10.png", 256, 256, true, true, true);
        diffuse11 = new Image("/gameboardsquare11.png", 256, 256, true, true, true);
        diffuse12 = new Image("/gameboardsquare12.png", 256, 256, true, true, true);
        diffuse13 = new Image("/gameboardsquare13.png", 256, 256, true, true, true);
        diffuse14 = new Image("/gameboardsquare14.png", 256, 256, true, true, true);
        diffuse15 = new Image("/gameboardsquare15.png", 256, 256, true, true, true);
        diffuse16 = new Image("/gameboardsquare16.png", 256, 256, true, true, true);
        diffuse17 = new Image("/gameboardsquare17.png", 256, 256, true, true, true);
        diffuse18 = new Image("/gameboardsquare18.png", 256, 256, true, true, true);
        diffuse19 = new Image("/gameboardsquare19.png", 256, 256, true, true, true);
        diffuse20 = new Image("/gameboardsquare20.png", 256, 256, true, true, true);
        diffuse21 = new Image("/gameboardquad1.png", 512, 512, true, true, true);
        diffuse22 = new Image("/gameboardquad2.png", 512, 512, true, true, true);
        diffuse23 = new Image("/gameboardquad3.png", 512, 512, true, true, true);
        diffuse24 = new Image("/gameboardquad4.png", 512, 512, true, true, true);
        diffuse25 = new Image("/gameboardspin.png", 512, 512, true, true, true);
        uiBackgroundImage = new BackgroundImage( backPlate,
                                                 BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                                                 BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        uiBackground = new Background(uiBackgroundImage);
    }
    private void createTextAssets() {
        playText = new Text("Press the Start Game Button to Start! \n");
        playText.setFill(Color.WHITE);
        playText.setFont(Font.font("Helvetica", FontPosture.REGULAR, 50));
        playText.setEffect(dropShadow);
        moreText = new Text("Use other buttons for instructions, \ncopyrights, credits and high scores.");
        moreText.setFill(Color.WHITE);
        moreText.setFont(Font.font("Helvetica", FontPosture.REGULAR, 50));
        moreText.setEffect(dropShadow);
        helpText = new Text("To play game, roll the dice, advance game \npiece, and follow game board instructions. \n");
        helpText.setFill(Color.GREEN);
        helpText.setFont(Font.font("Helvetica", FontPosture.REGULAR, 50));
        helpText.setEffect(dropShadow);
        cardText = new Text("If you land on a square that requires you draw \na card it will appear in the floating UI text area.");
        cardText.setFill(Color.GREEN);
        cardText.setFont(Font.font("Helvetica", FontPosture.REGULAR, 50));
        cardText.setEffect(dropShadow);
        copyText = new Text("Copyright 2017 Wallace Jackson. \nAll Rights Reserved. \n");
        copyText.setFill(Color.PURPLE);
        copyText.setFont(Font.font("Helvetica", FontPosture.REGULAR, 50));
        copyText.setEffect(dropShadow);
        riteText = new Text("Visit the iTVboardgame.com website on \nthe Internet at www.iTVboardgame.com");
        riteText.setFill(Color.PURPLE);
        riteText.setFont(Font.font("Helvetica", FontPosture.REGULAR, 50));
        riteText.setEffect(dropShadow);
        credText = new Text("Digital Imaging, 3D Modeling, 3D \nTexture Mapping by Wallace Jackson. \n");
        credText.setFill(Color.BLUE);
        credText.setFont(Font.font("Helvetica", FontPosture.REGULAR, 50));
        credText.setEffect(dropShadow);
        codeText = new Text("Game Design, User Interface Design, \nJava Programming by Wallace Jackson.");
        codeText.setFill(Color.BLUE);
        codeText.setFont(Font.font("Helvetica", FontPosture.REGULAR, 50));
        codeText.setEffect(dropShadow);
    }
    private void createSpecialEffects() {
        dropShadow = new DropShadow();
        dropShadow.setRadius(4.0);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        dropShadow.setColor(Color.DARKGRAY);
        colorAdjust = new ColorAdjust();
        colorAdjust.setHue(0.4);
    }
    private void createGameBoardNodes() {
        q1 = new Box(300, 5, 300);
        q1.setMaterial(Shader21);
        q1.setTranslateX(225);
        q1.setTranslateZ(225);
        q2 = new Box(300, 5, 300);
        q2.setMaterial(Shader22);
        q2.setTranslateX(225);
        q2.setTranslateZ(525);
        q3 = new Box(300, 5, 300);
        q3.setMaterial(Shader23);
        q3.setTranslateX(525);
        q3.setTranslateZ(525);
        q4 = new Box(300, 5, 300);
        q4.setMaterial(Shader24);
        q4.setTranslateX(525);
        q4.setTranslateZ(225);
        Q1S1 = new Box(150, 5, 150);
        Q1S1.setTranslateX(300);
        Q1S1.setMaterial(Shader1);
        Q1S2 = new Box(150, 5, 150);
        Q1S2.setTranslateX(150);
        Q1S2.setMaterial(Shader2);
        Q1S3 = new Box(150, 5, 150);
        Q1S3.setMaterial(Shader3);
        Q1S4 = new Box(150, 5, 150);
        Q1S4.setTranslateZ(150);
        Q1S4.setMaterial(Shader4);
        Q1S5 = new Box(150, 5, 150);
        Q1S5.setTranslateZ(300);
        Q1S5.setMaterial(Shader5);
        Q2S1 = new Box(150, 5, 150);
        Q2S1.setTranslateZ(450);
        Q2S1.setMaterial(Shader6);
        Q2S2 = new Box(150, 5, 150);
        Q2S2.setTranslateZ(600);
        Q2S2.setMaterial(Shader7);
        Q2S3 = new Box(150, 5, 150);
        Q2S3.setTranslateZ(750);
        Q2S3.setMaterial(Shader8);
        Q2S4 = new Box(150, 5, 150);
        Q2S4.setTranslateZ(750);
        Q2S4.setTranslateX(150);
        Q2S4.setMaterial(Shader9);
        Q2S5 = new Box(150, 5, 150);
        Q2S5.setTranslateZ(750);
        Q2S5.setTranslateX(300);
        Q2S5.setMaterial(Shader10);
        Q3S1 = new Box(150, 5, 150);
        Q3S1.setTranslateZ(750);
        Q3S1.setTranslateX(450);
        Q3S1.setMaterial(Shader11);
        Q3S2 = new Box(150, 5, 150);
        Q3S2.setTranslateZ(750);
        Q3S2.setTranslateX(600);
        Q3S2.setMaterial(Shader12);
        Q3S3 = new Box(150, 5, 150);
        Q3S3.setTranslateZ(750);
        Q3S3.setTranslateX(750);
        Q3S3.setMaterial(Shader13);
        Q3S4 = new Box(150, 5, 150);
        Q3S4.setTranslateZ(600);
        Q3S4.setTranslateX(750);
        Q3S4.setMaterial(Shader14);
        Q3S5 = new Box(150, 5, 150);
        Q3S5.setTranslateZ(450);
        Q3S5.setTranslateX(750);
        Q3S5.setMaterial(Shader15);
        Q4S1 = new Box(150, 5, 150);
        Q4S1.setTranslateZ(300);
        Q4S1.setTranslateX(750);
        Q4S1.setMaterial(Shader16);
        Q4S2 = new Box(150, 5, 150);
        Q4S2.setTranslateZ(150);
        Q4S2.setTranslateX(750);
        Q4S2.setMaterial(Shader17);
        Q4S3 = new Box(150, 5, 150);
        Q4S3.setTranslateX(750);
        Q4S3.setMaterial(Shader18);
        Q4S4 = new Box(150, 5, 150);
        Q4S4.setTranslateX(600);
        Q4S4.setMaterial(Shader19);
        Q4S5 = new Box(150, 5, 150);
        Q4S5.setTranslateX(450);
        Q4S5.setMaterial(Shader20);
    }
    private void createMaterials() {
        Shader1 = new PhongMaterial(Color.WHITE);
        Shader1.setDiffuseMap(diffuse1);
        Shader2 = new PhongMaterial(Color.WHITE);
        Shader2.setDiffuseMap(diffuse2);
        Shader3 = new PhongMaterial(Color.WHITE);
        Shader3.setDiffuseMap(diffuse3);
        Shader4 = new PhongMaterial(Color.WHITE);
        Shader4.setDiffuseMap(diffuse4);
        Shader5 = new PhongMaterial(Color.WHITE);
        Shader5.setDiffuseMap(diffuse5);
        Shader6 = new PhongMaterial(Color.WHITE);
        Shader6.setDiffuseMap(diffuse6);
        Shader7 = new PhongMaterial(Color.WHITE);
        Shader7.setDiffuseMap(diffuse7);
        Shader8 = new PhongMaterial(Color.WHITE);
        Shader8.setDiffuseMap(diffuse8);
        Shader9 = new PhongMaterial(Color.WHITE);
        Shader9.setDiffuseMap(diffuse9);
        Shader10 = new PhongMaterial(Color.WHITE);
        Shader10.setDiffuseMap(diffuse10);
        Shader11 = new PhongMaterial(Color.WHITE);
        Shader11.setDiffuseMap(diffuse11);
        Shader12 = new PhongMaterial(Color.WHITE);
        Shader12.setDiffuseMap(diffuse12);
        Shader13 = new PhongMaterial(Color.WHITE);
        Shader13.setDiffuseMap(diffuse13);
        Shader14 = new PhongMaterial(Color.WHITE);
        Shader14.setDiffuseMap(diffuse14);
        Shader15 = new PhongMaterial(Color.WHITE);
        Shader15.setDiffuseMap(diffuse15);
        Shader16 = new PhongMaterial(Color.WHITE);
        Shader16.setDiffuseMap(diffuse16);
        Shader17 = new PhongMaterial(Color.WHITE);
        Shader17.setDiffuseMap(diffuse17);
        Shader18 = new PhongMaterial(Color.WHITE);
        Shader18.setDiffuseMap(diffuse18);
        Shader19 = new PhongMaterial(Color.WHITE);
        Shader19.setDiffuseMap(diffuse19);
        Shader20 = new PhongMaterial(Color.WHITE);
        Shader20.setDiffuseMap(diffuse20);
        Shader21 = new PhongMaterial(Color.WHITE);
        Shader21.setDiffuseMap(diffuse21);
        Shader22 = new PhongMaterial(Color.WHITE);
        Shader22.setDiffuseMap(diffuse22);
        Shader23 = new PhongMaterial(Color.WHITE);
        Shader23.setDiffuseMap(diffuse23);
        Shader24 = new PhongMaterial(Color.WHITE);
        Shader24.setDiffuseMap(diffuse24);
        Shader25 = new PhongMaterial(Color.WHITE);
        Shader25.setDiffuseMap(diffuse25);
    }
    private void createAnimationAssets() {
        rotGameBoard = new RotateTransition(Duration.seconds(5), gameBoard);
        rotGameBoard.setAxis(Rotate.Y_AXIS);
        rotGameBoard.setCycleCount(1);
        rotGameBoard.setRate(0.5);
        rotGameBoard.setInterpolator(Interpolator.LINEAR);
        rotGameBoard.setOnFinished(event->{
            if (quadrantLanding == 315)  { populateQuadrantOne();   }
            if (quadrantLanding == 225) { populateQuadrantTwo();   }
            if (quadrantLanding == 135) { populateQuadrantThree(); }
            if (quadrantLanding == 45) { populateQuadrantFour();  }
            spinnerAudio.stop();
        });
        rotSpinner = new RotateTransition(Duration.seconds(5), spinner);
        rotSpinner.setAxis(Rotate.Y_AXIS);
        rotSpinner.setCycleCount(1);
        rotSpinner.setRate(0.5);
        rotSpinner.setInterpolator(Interpolator.LINEAR);
        rotSpinner.setOnFinished(event->{ spinnerClick = false; });
        rotSpinnerIn = new RotateTransition(Duration.seconds(5), spinner);
        rotSpinnerIn.setAxis(Rotate.Y_AXIS);
        rotSpinnerIn.setCycleCount(1);
        rotSpinnerIn.setRate(0.5);
        rotSpinnerIn.setInterpolator(Interpolator.LINEAR);
        rotSpinnerIn.setFromAngle(30);
        rotSpinnerIn.setToAngle(-1050);
        rotSpinnerIn.setOnFinished(event->{ spinnerClick = true; });
        moveSpinnerOn = new TranslateTransition(Duration.seconds(5), spinner);
        moveSpinnerOn.setByX(150);
        moveSpinnerOn.setCycleCount(1);
        spinnerAnim = new ParallelTransition(moveSpinnerOn, rotSpinnerIn);
        rotCameraDown = new RotateTransition(Duration.seconds(5), camera);
        rotCameraDown.setAxis(Rotate.X_AXIS);
        rotCameraDown.setCycleCount(1);
        rotCameraDown.setRate(0.75);
        rotCameraDown.setDelay(Duration.ONE);
        rotCameraDown.setInterpolator(Interpolator.LINEAR);
        rotCameraDown.setFromAngle(-30);
        rotCameraDown.setToAngle(-60);
        moveCameraIn = new TranslateTransition(Duration.seconds(2), camera);
        moveCameraIn.setByZ(-175);
        moveCameraIn.setCycleCount(1);
        moveSpinnerOff = new TranslateTransition(Duration.seconds(3), spinner);
        moveSpinnerOff.setByX(-150);
        moveSpinnerOff.setCycleCount(1);
        cameraAnimIn = new ParallelTransition(moveCameraIn, rotCameraDown, moveSpinnerOff);
        cameraAnimIn.setOnFinished(event-> {
            qaLayout.setVisible(true);
            scoreLayout.setVisible(true);
            buttonClick = true;
        });
        rotCameraBack = new RotateTransition(Duration.seconds(5), camera);
        rotCameraBack.setAxis(Rotate.X_AXIS);
        rotCameraBack.setCycleCount(1);
        rotCameraBack.setRate(0.75);
        rotCameraBack.setDelay(Duration.ONE);
        rotCameraBack.setInterpolator(Interpolator.LINEAR);
        rotCameraBack.setFromAngle(-60);
        rotCameraBack.setToAngle(-30);
        moveCameraOut = new TranslateTransition(Duration.seconds(2), camera);
        moveCameraOut.setByZ(175);
        moveCameraOut.setCycleCount(1);
        cameraAnimOut = new ParallelTransition(moveCameraOut, rotCameraBack, moveSpinnerOn);
        cameraAnimOut.setOnFinished(event-> {
            spinnerClick = true;
        });
    }
    private void createQAnodes() {
        qaLayout = new StackPane();
        qaLayout.setTranslateX(-250);
        qaLayout.setTranslateY(-385);
        qaLayout.setTranslateZ(-75);
        qaLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        qaLayout.setPrefSize(360, 654);
        a1Button = new Button();
        a1Button.setText("Answer Choice 1");
        a1Button.setFont(Font.font("Arial Black", 33));
        a1Button.setMaxSize(350, 140);
        a1Button.setTranslateY(-240);
        a2Button = new Button();
        a2Button.setText("Answer Choice 2");
        a2Button.setFont(Font.font("Arial Black", 33));
        a2Button.setMaxSize(350, 140);
        a2Button.setTranslateY(-80);
        a3Button = new Button();
        a3Button.setText("Answer Choice 3");
        a3Button.setFont(Font.font("Arial Black", 33));
        a3Button.setMaxSize(350, 140);
        a3Button.setTranslateY(80);
        a4Button = new Button();
        a4Button.setText("Answer Choice 4");
        a4Button.setFont(Font.font("Arial Black", 33));
        a4Button.setMaxSize(350, 140);
        a4Button.setTranslateY(240);
    }
    private void createScoreNodes() {
        scoreLayout = new StackPane();
        scoreLayout.setTranslateX(650);
        scoreLayout.setTranslateY(-385);
        scoreLayout.setTranslateZ(-75);
        scoreLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        scoreLayout.setPrefSize(360, 654);
        scoreLayout.setAlignment(Pos.TOP_CENTER);
        scoreTitle = new Text("SCORE");
        scoreTitle.setFont(Font.font("Arial Black", 72));
        scoreTitle.setFill(Color.DARKRED);
        scoreRight = new Text("Right:");
        scoreRight.setFont(Font.font("Arial Black", 64));
        scoreRight.setFill(Color.DARKBLUE);
        scoreRight.setTranslateX(-56);
        scoreRight.setTranslateY(160);
        rightAnswers = new Text("0");
        rightAnswers.setFont(Font.font("Arial Black", 64));
        rightAnswers.setFill(Color.BLACK);
        rightAnswers.setTranslateX(120);
        rightAnswers.setTranslateY(160);
        scoreWrong = new Text("Wrong:");
        scoreWrong.setFont(Font.font("Arial Black", 60));
        scoreWrong.setFill(Color.RED);
        scoreWrong.setTranslateX(-44);
        scoreWrong.setTranslateY(310);
        wrongAnswers = new Text("0");
        wrongAnswers.setFont(Font.font("Arial Black", 60));
        wrongAnswers.setFill(Color.BLACK);
        wrongAnswers.setTranslateX(120);
        wrongAnswers.setTranslateY(310);
        scoreCheer = new Text("Great Job!");
        scoreCheer.setFont(Font.font("Arial Black", 56));
        scoreCheer.setFill(Color.DARKGREEN);
        scoreCheer.setTranslateX(-2);
        scoreCheer.setTranslateY(460);
    }
    private void createQAprocessing() {
        a1Button.setOnAction((ActionEvent event) -> {
          if(buttonClick == true) {
            if (picked == Q1S1 && pickS1 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S1 && pickS1 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S1 && pickS1 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S2 && pickS2 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S2 && pickS2 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S2 && pickS2 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S3 && pickS3 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S3 && pickS3 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S3 && pickS3 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S4 && pickS4 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S4 && pickS4 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S4 && pickS4 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S5 && pickS5 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S5 && pickS5 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S5 && pickS5 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S1 && pickS6 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S1 && pickS6 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S1 && pickS6 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S2 && pickS7 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S2 && pickS7 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S2 && pickS7 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S3 && pickS8 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S3 && pickS8 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S3 && pickS8 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S4 && pickS9 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S4 && pickS9 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S4 && pickS9 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S5 && pickS10 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S5 && pickS10 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S5 && pickS10 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S1 && pickS11 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S1 && pickS11 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S1 && pickS11 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S2 && pickS12 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S2 && pickS12 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S2 && pickS12 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S3 && pickS13 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S3 && pickS13 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S3 && pickS13 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S4 && pickS14 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S4 && pickS14 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S4 && pickS14 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S5 && pickS15 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S5 && pickS15 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S5 && pickS15 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S1 && pickS16 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S1 && pickS16 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S1 && pickS16 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S2 && pickS17 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S2 && pickS17 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S2 && pickS17 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S3 && pickS18 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S3 && pickS18 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S3 && pickS18 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S4 && pickS19 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S4 && pickS19 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S4 && pickS19 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S5 && pickS20 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S5 && pickS20 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S5 && pickS20 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
          buttonClick = false;
          againButton.setVisible(true);
          }
        });
        a2Button.setOnAction((ActionEvent event) -> {
          if(buttonClick == true) {
            if (picked == Q1S1 && pickS1 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S1 && pickS1 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S1 && pickS1 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S2 && pickS2 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S2 && pickS2 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S2 && pickS2 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S3 && pickS3 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S3 && pickS3 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S3 && pickS3 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S4 && pickS4 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S4 && pickS4 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S4 && pickS4 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S5 && pickS5 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S5 && pickS5 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S5 && pickS5 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S1 && pickS6 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S1 && pickS6 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S1 && pickS6 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S2 && pickS7 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S2 && pickS7 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S2 && pickS7 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S3 && pickS8 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S3 && pickS8 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S3 && pickS8 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S4 && pickS9 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S4 && pickS9 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S4 && pickS9 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S5 && pickS10 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S5 && pickS10 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S5 && pickS10 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S1 && pickS11 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S1 && pickS11 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S1 && pickS11 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S2 && pickS12 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S2 && pickS12 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S2 && pickS12 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S3 && pickS13 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S3 && pickS13 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S3 && pickS13 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S4 && pickS14 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S4 && pickS14 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S4 && pickS14 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S5 && pickS15 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S5 && pickS15 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S5 && pickS15 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S1 && pickS16 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S1 && pickS16 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S1 && pickS16 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S2 && pickS17 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S2 && pickS17 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S2 && pickS17 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S3 && pickS18 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S3 && pickS18 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S3 && pickS18 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S4 && pickS19 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S4 && pickS19 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S4 && pickS19 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S5 && pickS20 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S5 && pickS20 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S5 && pickS20 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
          buttonClick = false;
          againButton.setVisible(true);
          }
        });
        a3Button.setOnAction((ActionEvent event) -> {
          if(buttonClick == true) {
            if (picked == Q1S1 && pickS1 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S1 && pickS1 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S1 && pickS1 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S2 && pickS2 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S2 && pickS2 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S2 && pickS2 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S3 && pickS3 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S3 && pickS3 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S3 && pickS3 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S4 && pickS4 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S4 && pickS4 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S4 && pickS4 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S5 && pickS5 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S5 && pickS5 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S5 && pickS5 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S1 && pickS6 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S1 && pickS6 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S1 && pickS6 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S2 && pickS7 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S2 && pickS7 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S2 && pickS7 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S3 && pickS8 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S3 && pickS8 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S3 && pickS8 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S4 && pickS9 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S4 && pickS9 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S4 && pickS9 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S5 && pickS10 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S5 && pickS10 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S5 && pickS10 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S1 && pickS11 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S1 && pickS11 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S1 && pickS11 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S2 && pickS12 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S2 && pickS12 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S2 && pickS12 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S3 && pickS13 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S3 && pickS13 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S3 && pickS13 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S4 && pickS14 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S4 && pickS14 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S4 && pickS14 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S5 && pickS15 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S5 && pickS15 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S5 && pickS15 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S1 && pickS16 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S1 && pickS16 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S1 && pickS16 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S2 && pickS17 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S2 && pickS17 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S2 && pickS17 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S3 && pickS18 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S3 && pickS18 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S3 && pickS18 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S4 && pickS19 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S4 && pickS19 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S4 && pickS19 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S5 && pickS20 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S5 && pickS20 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S5 && pickS20 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
          buttonClick = false;
          againButton.setVisible(true);
          }
        });
        a4Button.setOnAction((ActionEvent event) -> {
          if(buttonClick == true) {
            if (picked == Q1S1 && pickS1 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S1 && pickS1 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S1 && pickS1 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S2 && pickS2 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S2 && pickS2 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S2 && pickS2 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S3 && pickS3 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S3 && pickS3 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S3 && pickS3 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S4 && pickS4 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S4 && pickS4 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S4 && pickS4 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S5 && pickS5 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q1S5 && pickS5 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q1S5 && pickS5 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S1 && pickS6 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S1 && pickS6 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S1 && pickS6 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S2 && pickS7 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S2 && pickS7 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S2 && pickS7 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S3 && pickS8 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S3 && pickS8 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S3 && pickS8 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S4 && pickS9 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S4 && pickS9 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S4 && pickS9 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S5 && pickS10 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q2S5 && pickS10 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q2S5 && pickS10 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S1 && pickS11 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S1 && pickS11 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S1 && pickS11 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S2 && pickS12 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S2 && pickS12 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S2 && pickS12 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S3 && pickS13 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S3 && pickS13 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S3 && pickS13 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S4 && pickS14 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S4 && pickS14 == 1)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q3S4 && pickS14 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S5 && pickS15 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S5 && pickS15 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q3S5 && pickS15 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S1 && pickS16 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S1 && pickS16 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S1 && pickS16 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S2 && pickS17 == 0)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S2 && pickS17 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S2 && pickS17 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S3 && pickS18 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S3 && pickS18 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S3 && pickS18 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S4 && pickS19 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S4 && pickS19 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S4 && pickS19 == 2)
            {rightAnswer = rightAnswer + 1; rightAnswers.setText(String.valueOf(rightAnswer)); scoreCheer.setText("Great Job!");}
            if (picked == Q4S5 && pickS20 == 0)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S5 && pickS20 == 1)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
            if (picked == Q4S5 && pickS20 == 2)
            {wrongAnswer = wrongAnswer + 1; wrongAnswers.setText(String.valueOf(wrongAnswer)); scoreCheer.setText("Spin Again");}
          buttonClick = false;
          againButton.setVisible(true);
          }
        });
    }
    private void createSceneProcessing() {
        scene = new Scene(root, 1280, 640, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);
        scene.setOnMouseClicked(event-> {
            picked = event.getPickResult().getIntersectedNode();
            if(picked != null) {
                if (picked == Q1S1 && squareClick1 == true) { setupQ1S1gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick1 = false; }
                if (picked == Q1S2 && squareClick1 == true) { setupQ1S2gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick1 = false; }
                if (picked == Q1S3 && squareClick1 == true) { setupQ1S3gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick1 = false; }
                if (picked == Q1S4 && squareClick1 == true) { setupQ1S4gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick1 = false; }
                if (picked == Q1S5 && squareClick1 == true) { setupQ1S5gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick1 = false; }
                if (picked == Q2S1 && squareClick2 == true) { setupQ2S1gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick2 = false; }
                if (picked == Q2S2 && squareClick2 == true) { setupQ2S2gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick2 = false; }
                if (picked == Q2S3 && squareClick2 == true) { setupQ2S3gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick2 = false; }
                if (picked == Q2S4 && squareClick2 == true) { setupQ2S4gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick2 = false; }
                if (picked == Q2S5 && squareClick2 == true) { setupQ2S5gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick2 = false; }
                if (picked == Q3S1 && squareClick3 == true) { setupQ3S1gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick3 = false; }
                if (picked == Q3S2 && squareClick3 == true) { setupQ3S2gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick3 = false; }
                if (picked == Q3S3 && squareClick3 == true) { setupQ3S3gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick3 = false; }
                if (picked == Q3S4 && squareClick3 == true) { setupQ3S4gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick3 = false; }
                if (picked == Q3S5 && squareClick3 == true) { setupQ3S5gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick3 = false; }
                if (picked == Q4S1 && squareClick4 == true) { setupQ4S1gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick4 = false; }
                if (picked == Q4S2 && squareClick4 == true) { setupQ4S2gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick4 = false; }
                if (picked == Q4S3 && squareClick4 == true) { setupQ4S3gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick4 = false; }
                if (picked == Q4S4 && squareClick4 == true) { setupQ4S4gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick4 = false; }
                if (picked == Q4S5 && squareClick4 == true) { setupQ4S5gameplay(); cameraAnimIn.play(); cameraAudio.play(); squareClick4 = false; }
                if (picked == spinner && spinnerClick == true) {
                    int spin = random.nextInt(4);
                    if (spin == 0){ rotGameBoard.setByAngle(1080); rotSpinner.setByAngle(-1080); spinDeg += 1080; }
                    if (spin == 1){ rotGameBoard.setByAngle(1170); rotSpinner.setByAngle(-1170); spinDeg += 1170; }
                    if (spin == 2){ rotGameBoard.setByAngle(1260); rotSpinner.setByAngle(-1260); spinDeg += 1260; }
                    if (spin == 3){ rotGameBoard.setByAngle(1350); rotSpinner.setByAngle(-1350); spinDeg += 1350; }
                    rotGameBoard.play();
                    rotSpinner.play();
                    spinnerAudio.play();
                    calculateQuadrantLanding();
                    spinnerClick = false;
                }
            }
        });
    }
    private void calculateQuadrantLanding(){
        quadrantLanding = spinDeg%360;
        System.out.println(quadrantLanding);
    }
    private void populateQuadrantOne(){
        pickS1 = random.nextInt(3);
        if (pickS1 == 0){diffuse1 = new Image("/8bit/gamesquare1bird0.png", 256, 256, true, true, true);}
        if (pickS1 == 1){diffuse1 = new Image("/8bit/gamesquare1bird1.png", 256, 256, true, true, true);}
        if (pickS1 == 2){diffuse1 = new Image("/8bit/gamesquare1bird2.png", 256, 256, true, true, true);}
        Shader1.setDiffuseMap(diffuse1);
        pickS2 = random.nextInt(3);
        if (pickS2 == 0){diffuse2 = new Image("/8bit/gamesquare2bird0.png", 256, 256, true, true, true);}
        if (pickS2 == 1){diffuse2 = new Image("/8bit/gamesquare2bird1.png", 256, 256, true, true, true);}
        if (pickS2 == 2){diffuse2 = new Image("/8bit/gamesquare2bird2.png", 256, 256, true, true, true);}
        Shader2.setDiffuseMap(diffuse2);
        pickS3 = random.nextInt(3);
        if (pickS3 == 0){diffuse3 = new Image("/8bit/gamesquare3bird0.png", 256, 256, true, true, true);}
        if (pickS3 == 1){diffuse3 = new Image("/8bit/gamesquare3bird1.png", 256, 256, true, true, true);}
        if (pickS3 == 2){diffuse3 = new Image("/8bit/gamesquare3bird2.png", 256, 256, true, true, true);}
        Shader3.setDiffuseMap(diffuse3);
        pickS4 = random.nextInt(3);
        if (pickS4 == 0){diffuse4 = new Image("/8bit/gamesquare4bird0.png", 256, 256, true, true, true);}
        if (pickS4 == 1){diffuse4 = new Image("/8bit/gamesquare4bird1.png", 256, 256, true, true, true);}
        if (pickS4 == 2){diffuse4 = new Image("/8bit/gamesquare4bird2.png", 256, 256, true, true, true);}
        Shader4.setDiffuseMap(diffuse4);
        pickS5 = random.nextInt(3);
        if (pickS5 == 0){diffuse5 = new Image("/8bit/gamesquare5bird0.png", 256, 256, true, true, true);}
        if (pickS5 == 1){diffuse5 = new Image("/8bit/gamesquare5bird1.png", 256, 256, true, true, true);}
        if (pickS5 == 2){diffuse5 = new Image("/8bit/gamesquare5bird2.png", 256, 256, true, true, true);}
        Shader5.setDiffuseMap(diffuse5);
        squareClick1 = true;
    }
    private void populateQuadrantTwo(){
        pickS6 = random.nextInt(3);
        if (pickS6 == 0){diffuse6 = new Image("/8bit/gamesquare6vegi0.png", 256, 256, true, true, true);}
        if (pickS6 == 1){diffuse6 = new Image("/8bit/gamesquare6vegi1.png", 256, 256, true, true, true);}
        if (pickS6 == 2){diffuse6 = new Image("/8bit/gamesquare6vegi2.png", 256, 256, true, true, true);}
        Shader6.setDiffuseMap(diffuse6);
        pickS7 = random.nextInt(3);
        if (pickS7 == 0){diffuse7 = new Image("/8bit/gamesquare7vegi0.png", 256, 256, true, true, true);}
        if (pickS7 == 1){diffuse7 = new Image("/8bit/gamesquare7vegi1.png", 256, 256, true, true, true);}
        if (pickS7 == 2){diffuse7 = new Image("/8bit/gamesquare7vegi2.png", 256, 256, true, true, true);}
        Shader7.setDiffuseMap(diffuse7);
        pickS8 = random.nextInt(3);
        if (pickS8 == 0){diffuse8 = new Image("/8bit/gamesquare8vegi0.png", 256, 256, true, true, true);}
        if (pickS8 == 1){diffuse8 = new Image("/8bit/gamesquare8vegi1.png", 256, 256, true, true, true);}
        if (pickS8 == 2){diffuse8 = new Image("/8bit/gamesquare8vegi2.png", 256, 256, true, true, true);}
        Shader8.setDiffuseMap(diffuse8);
        pickS9 = random.nextInt(3);
        if (pickS9 == 0){diffuse9 = new Image("/8bit/gamesquare9vegi0.png", 256, 256, true, true, true);}
        if (pickS9 == 1){diffuse9 = new Image("/8bit/gamesquare9vegi1.png", 256, 256, true, true, true);}
        if (pickS9 == 2){diffuse9 = new Image("/8bit/gamesquare9vegi2.png", 256, 256, true, true, true);}
        Shader9.setDiffuseMap(diffuse9);
        pickS10 = random.nextInt(3);
        if (pickS10 == 0){diffuse10 = new Image("/8bit/gamesquare10vegi0.png", 256, 256, true, true, true);}
        if (pickS10 == 1){diffuse10 = new Image("/8bit/gamesquare10vegi1.png", 256, 256, true, true, true);}
        if (pickS10 == 2){diffuse10 = new Image("/8bit/gamesquare10vegi2.png", 256, 256, true, true, true);}
        Shader10.setDiffuseMap(diffuse10);
        squareClick2 = true;
            }
    private void populateQuadrantThree(){
        pickS11 = random.nextInt(3);
        if (pickS11 == 0){diffuse11 = new Image("/8bit/gamesquare11rock0.png", 256, 256, true, true, true);}
        if (pickS11 == 1){diffuse11 = new Image("/8bit/gamesquare11rock1.png", 256, 256, true, true, true);}
        if (pickS11 == 2){diffuse11 = new Image("/8bit/gamesquare11rock2.png", 256, 256, true, true, true);}
        Shader11.setDiffuseMap(diffuse11);
        pickS12 = random.nextInt(3);
        if (pickS12 == 0){diffuse12 = new Image("/8bit/gamesquare12rock0.png", 256, 256, true, true, true);}
        if (pickS12 == 1){diffuse12 = new Image("/8bit/gamesquare12rock1.png", 256, 256, true, true, true);}
        if (pickS12 == 2){diffuse12 = new Image("/8bit/gamesquare12rock2.png", 256, 256, true, true, true);}
        Shader12.setDiffuseMap(diffuse12);
        pickS13 = random.nextInt(3);
        if (pickS13 == 0){diffuse13 = new Image("/8bit/gamesquare13rock0.png", 256, 256, true, true, true);}
        if (pickS13 == 1){diffuse13 = new Image("/8bit/gamesquare13rock1.png", 256, 256, true, true, true);}
        if (pickS13 == 2){diffuse13 = new Image("/8bit/gamesquare13rock2.png", 256, 256, true, true, true);}
        Shader13.setDiffuseMap(diffuse13);
        pickS14 = random.nextInt(3);
        if (pickS14 == 0){diffuse14 = new Image("/8bit/gamesquare14rock0.png", 256, 256, true, true, true);}
        if (pickS14 == 1){diffuse14 = new Image("/8bit/gamesquare14rock1.png", 256, 256, true, true, true);}
        if (pickS14 == 2){diffuse14 = new Image("/8bit/gamesquare14rock2.png", 256, 256, true, true, true);}
        Shader14.setDiffuseMap(diffuse14);
        pickS15 = random.nextInt(3);
        if (pickS15 == 0){diffuse15 = new Image("/8bit/gamesquare15rock0.png", 256, 256, true, true, true);}
        if (pickS15 == 1){diffuse15 = new Image("/8bit/gamesquare15rock1.png", 256, 256, true, true, true);}
        if (pickS15 == 2){diffuse15 = new Image("/8bit/gamesquare15rock2.png", 256, 256, true, true, true);}
        Shader15.setDiffuseMap(diffuse15);
        squareClick3 = true;
    }
    private void populateQuadrantFour(){
        pickS16 = random.nextInt(3);
        if (pickS16 == 0){diffuse16 = new Image("/8bit/gamesquare16fame0.png", 256, 256, true, true, true);}
        if (pickS16 == 1){diffuse16 = new Image("/8bit/gamesquare16fame1.png", 256, 256, true, true, true);}
        if (pickS16 == 2){diffuse16 = new Image("/8bit/gamesquare16fame2.png", 256, 256, true, true, true);}
        Shader16.setDiffuseMap(diffuse16);
        pickS17 = random.nextInt(3);
        if (pickS17 == 0){diffuse17 = new Image("/8bit/gamesquare17fame0.png", 256, 256, true, true, true);}
        if (pickS17 == 1){diffuse17 = new Image("/8bit/gamesquare17fame1.png", 256, 256, true, true, true);}
        if (pickS17 == 2){diffuse17 = new Image("/8bit/gamesquare17fame2.png", 256, 256, true, true, true);}
        Shader17.setDiffuseMap(diffuse17);
        pickS18 = random.nextInt(3);
        if (pickS18 == 0){diffuse18 = new Image("/8bit/gamesquare18fame0.png", 256, 256, true, true, true);}
        if (pickS18 == 1){diffuse18 = new Image("/8bit/gamesquare18fame1.png", 256, 256, true, true, true);}
        if (pickS18 == 2){diffuse18 = new Image("/8bit/gamesquare18fame2.png", 256, 256, true, true, true);}
        Shader18.setDiffuseMap(diffuse18);
        pickS19 = random.nextInt(3);
        if (pickS19 == 0){diffuse19 = new Image("/8bit/gamesquare19fame0.png", 256, 256, true, true, true);}
        if (pickS19 == 1){diffuse19 = new Image("/8bit/gamesquare19fame1.png", 256, 256, true, true, true);}
        if (pickS19 == 2){diffuse19 = new Image("/8bit/gamesquare19fame2.png", 256, 256, true, true, true);}
        Shader19.setDiffuseMap(diffuse19);
        pickS20 = random.nextInt(3);
        if (pickS20 == 0){diffuse20 = new Image("/8bit/gamesquare20fame0.png", 256, 256, true, true, true);}
        if (pickS20 == 1){diffuse20 = new Image("/8bit/gamesquare20fame1.png", 256, 256, true, true, true);}
        if (pickS20 == 2){diffuse20 = new Image("/8bit/gamesquare20fame2.png", 256, 256, true, true, true);}
        Shader20.setDiffuseMap(diffuse20);
        squareClick4 = true;
    }
    private void resetTextureMaps(){
        diffuse1 = new Image("/gameboardsquare.png", 256, 256, true, true, true);
        Shader1.setDiffuseMap(diffuse1);
        diffuse2 = new Image("/gameboardsquare2.png", 256, 256, true, true, true);
        Shader2.setDiffuseMap(diffuse2);
        diffuse3 = new Image("/gameboardsquare3.png", 256, 256, true, true, true);
        Shader3.setDiffuseMap(diffuse3);
        diffuse4 = new Image("/gameboardsquare4.png", 256, 256, true, true, true);
        Shader4.setDiffuseMap(diffuse4);
        diffuse5 = new Image("/gameboardsquare5.png", 256, 256, true, true, true);
        Shader5.setDiffuseMap(diffuse5);
        diffuse6 = new Image("/gameboardsquare6.png", 256, 256, true, true, true);
        Shader6.setDiffuseMap(diffuse6);
        diffuse7 = new Image("/gameboardsquare7.png", 256, 256, true, true, true);
        Shader7.setDiffuseMap(diffuse7);
        diffuse8 = new Image("/gameboardsquare8.png", 256, 256, true, true, true);
        Shader8.setDiffuseMap(diffuse8);
        diffuse9 = new Image("/gameboardsquare9.png", 256, 256, true, true, true);
        Shader9.setDiffuseMap(diffuse9);
        diffuse10 = new Image("/gameboardsquare10.png", 256, 256, true, true, true);
        Shader10.setDiffuseMap(diffuse10);
        diffuse11 = new Image("/gameboardsquare11.png", 256, 256, true, true, true);
        Shader11.setDiffuseMap(diffuse11);
        diffuse12 = new Image("/gameboardsquare12.png", 256, 256, true, true, true);
        Shader12.setDiffuseMap(diffuse12);
        diffuse13 = new Image("/gameboardsquare13.png", 256, 256, true, true, true);
        Shader13.setDiffuseMap(diffuse13);
        diffuse14 = new Image("/gameboardsquare14.png", 256, 256, true, true, true);
        Shader14.setDiffuseMap(diffuse14);
        diffuse15 = new Image("/gameboardsquare15.png", 256, 256, true, true, true);
        Shader15.setDiffuseMap(diffuse15);
        diffuse16 = new Image("/gameboardsquare16.png", 256, 256, true, true, true);
        Shader16.setDiffuseMap(diffuse16);
        diffuse17 = new Image("/gameboardsquare17.png", 256, 256, true, true, true);
        Shader17.setDiffuseMap(diffuse17);
        diffuse18 = new Image("/gameboardsquare18.png", 256, 256, true, true, true);
        Shader18.setDiffuseMap(diffuse18);
        diffuse19 = new Image("/gameboardsquare19.png", 256, 256, true, true, true);
        Shader19.setDiffuseMap(diffuse19);
        diffuse20 = new Image("/gameboardsquare20.png", 256, 256, true, true, true);
        Shader20.setDiffuseMap(diffuse20);
        diffuse21 = new Image("/gameboardquad1.png", 512, 512, true, true, true);
        Shader21.setDiffuseMap(diffuse21);
        diffuse22 = new Image("/gameboardquad2.png", 512, 512, true, true, true);
        Shader22.setDiffuseMap(diffuse22);
        diffuse23 = new Image("/gameboardquad3.png", 512, 512, true, true, true);
        Shader23.setDiffuseMap(diffuse23);
        diffuse24 = new Image("/gameboardquad4.png", 512, 512, true, true, true);
        Shader24.setDiffuseMap(diffuse24);
    }
    private void setupQ1S1gameplay() {
        if (pickS1 == 0){ diffuse21 = new Image("/8bit/gamequad1bird0.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Falcon Hawk");
            a2Button.setText("Seagull");
            a3Button.setText("Peacock");
            a4Button.setText("Flamingo"); }
        if (pickS1 == 1){ diffuse21 = new Image("/8bit/gamequad1bird1.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Toucan");
            a2Button.setText("Goldfinch");
            a3Button.setText("Parrot");
            a4Button.setText("Heron"); }
        if (pickS1 == 2){ diffuse21 = new Image("/8bit/gamequad1bird2.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Hummingbird");
            a2Button.setText("Eagle");
            a3Button.setText("Robin");
            a4Button.setText("Goose"); }
    }
    private void setupQ1S2gameplay() {
        if (pickS2 == 0){ diffuse21 = new Image("/8bit/gamequad1s2bird0.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Egret");
            a2Button.setText("Rooster");
            a3Button.setText("Falcon");
            a4Button.setText("Ostrich"); }
        if (pickS2 == 1){ diffuse21 = new Image("/8bit/gamequad1s2bird1.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Duckling");
            a2Button.setText("Peacock");
            a3Button.setText("Bluebird");
            a4Button.setText("Crow"); }
        if (pickS2 == 2){ diffuse21 = new Image("/8bit/gamequad1s2bird2.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Parrot");
            a2Button.setText("Finch");
            a3Button.setText("Owl");
            a4Button.setText("Toucan"); }
    }
    private void setupQ1S3gameplay() {
        if (pickS3 == 0){ diffuse21 = new Image("/8bit/gamequad1s3bird0.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Cardinal");
            a2Button.setText("Peacock");
            a3Button.setText("Indigo");
            a4Button.setText("Hummingbird"); }
        if (pickS3 == 1){ diffuse21 = new Image("/8bit/gamequad1s3bird1.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Flamingo");
            a2Button.setText("Emu");
            a3Button.setText("Owl");
            a4Button.setText("Finch"); }
        if (pickS3 == 2){ diffuse21 = new Image("/8bit/gamequad1s3bird2.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Parrot");
            a2Button.setText("Rooster");
            a3Button.setText("Puffin");
            a4Button.setText("Goose"); }
    }
    private void setupQ1S4gameplay() {
        if (pickS4 == 0){ diffuse21 = new Image("/8bit/gamequad1s4bird0.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Duck");
            a2Button.setText("Rooster");
            a3Button.setText("Falcon");
            a4Button.setText("Ostrich"); }
        if (pickS4 == 1){ diffuse21 = new Image("/8bit/gamequad1s4bird1.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Egret");
            a2Button.setText("Rooster");
            a3Button.setText("Cockatiel");
            a4Button.setText("Ostrich"); }
        if (pickS4 == 2){ diffuse21 = new Image("/8bit/gamequad1s4bird2.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Egret");
            a2Button.setText("Rooster");
            a3Button.setText("Falcon");
            a4Button.setText("Seagull"); }
    }
    private void setupQ1S5gameplay() {
        if (pickS5 == 0){ diffuse21 = new Image("/8bit/gamequad1s5bird0.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Oriole");
            a2Button.setText("Chicken");
            a3Button.setText("Cardinal");
            a4Button.setText("Crow"); }
        if (pickS5 == 1){ diffuse21 = new Image("/8bit/gamequad1s5bird1.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Egret");
            a2Button.setText("Turkey");
            a3Button.setText("Heron");
            a4Button.setText("Ostrich"); }
        if (pickS5 == 2){ diffuse21 = new Image("/8bit/gamequad1s5bird2.png", 512, 512, true, true, true);
            Shader21.setDiffuseMap(diffuse21);
            a1Button.setText("Galapagos Finch");
            a2Button.setText("Eagle");
            a3Button.setText("Mockingbird");
            a4Button.setText("Falcon"); }
    }
    private void setupQ2S1gameplay() {
        if (pickS6 == 0){ diffuse22 = new Image("/8bit/gamequad2s1vegi0.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Orange");
            a2Button.setText("Pepper");
            a3Button.setText("Tomato");
            a4Button.setText("Onion"); }
        if (pickS6 == 1){ diffuse22 = new Image("/8bit/gamequad2s1vegi1.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Chard");
            a2Button.setText("Lettuce");
            a3Button.setText("Broccoli");
            a4Button.setText("Spinach"); }
        if (pickS6 == 2){ diffuse22 = new Image("/8bit/gamequad2s1vegi2.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Wheat");
            a2Button.setText("Corn");
            a3Button.setText("Celery");
            a4Button.setText("Mushroom"); }
    }
    private void setupQ2S2gameplay() {
        if (pickS7 == 0){ diffuse22 = new Image("/8bit/gamequad2s2vegi0.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Brussel Sprout");
            a2Button.setText("Spinach");
            a3Button.setText("Kale");
            a4Button.setText("Green Onion"); }
        if (pickS7 == 1){ diffuse22 = new Image("/8bit/gamequad2s2vegi1.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Mango");
            a2Button.setText("Apricot");
            a3Button.setText("Avocado");
            a4Button.setText("Persimmon"); }
        if (pickS7 == 2){ diffuse22 = new Image("/8bit/gamequad2s2vegi2.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Carrot");
            a2Button.setText("Mustard Green");
            a3Button.setText("Beet");
            a4Button.setText("Cherry Tomato"); }
    }
    private void setupQ2S3gameplay() {
        if (pickS8 == 0){ diffuse22 = new Image("/8bit/gamequad2s3vegi0.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Radish");
            a2Button.setText("Turnip");
            a3Button.setText("Carrot");
            a4Button.setText("Beet"); }
        if (pickS8 == 1){ diffuse22 = new Image("/8bit/gamequad2s3vegi1.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Cabbage");
            a2Button.setText("Pomegranate");
            a3Button.setText("Pepper");
            a4Button.setText("Tomato"); }
        if (pickS8 == 2){ diffuse22 = new Image("/8bit/gamequad2s3vegi2.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Iceburg Lettuce");
            a2Button.setText("Cauliflower");
            a3Button.setText("Broccoli");
            a4Button.setText("Squash"); }
    }
    private void setupQ2S4gameplay() {
        if (pickS9 == 0){ diffuse22 = new Image("/8bit/gamequad2s4vegi0.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Chili");
            a2Button.setText("Onion");
            a3Button.setText("Cilantro");
            a4Button.setText("Tomatillo"); }
        if (pickS9 == 1){ diffuse22 = new Image("/8bit/gamequad2s4vegi1.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Radish");
            a2Button.setText("Cabbage");
            a3Button.setText("Carrot");
            a4Button.setText("Potato"); }
        if (pickS9 == 2){ diffuse22 = new Image("/8bit/gamequad2s4vegi2.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Corn");
            a2Button.setText("Pumpkin");
            a3Button.setText("Artichoke");
            a4Button.setText("Squash"); }
    }
    private void setupQ2S5gameplay() {
        if (pickS10 == 0){ diffuse22 = new Image("/8bit/gamequad2s5vegi0.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Eggplant");
            a2Button.setText("Mustard Green");
            a3Button.setText("Cilantro");
            a4Button.setText("Purple Onion"); }
        if (pickS10 == 1){ diffuse22 = new Image("/8bit/gamequad2s5vegi1.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Purple Cabbage");
            a2Button.setText("Lettuce");
            a3Button.setText("Kale");
            a4Button.setText("Green Onion"); }
        if (pickS10 == 2){ diffuse22 = new Image("/8bit/gamequad2s5vegi2.png", 512, 512, true, true, true);
            Shader22.setDiffuseMap(diffuse22);
            a1Button.setText("Carrot");
            a2Button.setText("Brussel Sprout");
            a3Button.setText("Pumpkin");
            a4Button.setText("Broccoli"); }
    }
    private void setupQ3S1gameplay() {
        if (pickS11 == 0){ diffuse23 = new Image("/8bit/gamequad3s1rock0.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Sapphire");
            a2Button.setText("Diamond");
            a3Button.setText("Ruby");
            a4Button.setText("Emerald"); }
        if (pickS11 == 1){ diffuse23 = new Image("/8bit/gamequad3s1rock1.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Opal");
            a2Button.setText("Rose Quartz");
            a3Button.setText("Amethyst");
            a4Button.setText("Garnet"); }
        if (pickS11 == 2){ diffuse23 = new Image("/8bit/gamequad3s1rock2.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Sandstone");
            a2Button.setText("Jasper");
            a3Button.setText("Agate");
            a4Button.setText("Petrified Wood"); }
    }
    private void setupQ3S2gameplay() {
        if (pickS12 == 0){ diffuse23 = new Image("/8bit/gamequad3s2rock0.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Silver");
            a2Button.setText("Fool's Gold");
            a3Button.setText("Copper Ore");
            a4Button.setText("Tourmaline"); }
        if (pickS12 == 1){ diffuse23 = new Image("/8bit/gamequad3s2rock1.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Quartz Crystal");
            a2Button.setText("Feldspar");
            a3Button.setText("Granite");
            a4Button.setText("Wulfenite"); }
        if (pickS12 == 2){ diffuse23 = new Image("/8bit/gamequad3s2rock2.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Geode");
            a2Button.setText("Malachite");
            a3Button.setText("Basalt");
            a4Button.setText("Desert Rose"); }
    }
    private void setupQ3S3gameplay() {
        if (pickS13 == 0){ diffuse23 = new Image("/8bit/gamequad3s3rock0.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Platinum");
            a2Button.setText("Coal");
            a3Button.setText("Geode");
            a4Button.setText("Fluorite"); }
        if (pickS13 == 1){ diffuse23 = new Image("/8bit/gamequad3s3rock1.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Rose Quartz");
            a2Button.setText("Feldspar");
            a3Button.setText("Granite");
            a4Button.setText("Sandstone"); }
        if (pickS13 == 2){ diffuse23 = new Image("/8bit/gamequad3s3rock2.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Lead");
            a2Button.setText("Citrine");
            a3Button.setText("Obsidian");
            a4Button.setText("Jade"); }
    }
    private void setupQ3S4gameplay() {
        if (pickS14 == 0){ diffuse23 = new Image("/8bit/gamequad3s4rock0.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Cat's Eye");
            a2Button.setText("Turquoise");
            a3Button.setText("Garnet");
            a4Button.setText("Obsidian"); }
        if (pickS14 == 1){ diffuse23 = new Image("/8bit/gamequad3s4rock1.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Granite");
            a2Button.setText("Wulfenite");
            a3Button.setText("Quartz");
            a4Button.setText("Agate"); }
        if (pickS14 == 2){ diffuse23 = new Image("/8bit/gamequad3s4rock2.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Celestine");
            a2Button.setText("Opal");
            a3Button.setText("Afghanite");
            a4Button.setText("Jasper"); }
    }
    private void setupQ3S5gameplay() {
        if (pickS15 == 0){ diffuse23 = new Image("/8bit/gamequad3s5rock0.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Turquoise");
            a2Button.setText("Wulfenite");
            a3Button.setText("Emerald");
            a4Button.setText("Ruby"); }
        if (pickS15 == 1){ diffuse23 = new Image("/8bit/gamequad3s5rock1.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Tourmaline");
            a2Button.setText("Diamond");
            a3Button.setText("Coal");
            a4Button.setText("Zirconium"); }
        if (pickS15 == 2){ diffuse23 = new Image("/8bit/gamequad3s5rock2.png", 512, 512, true, true, true);
            Shader23.setDiffuseMap(diffuse23);
            a1Button.setText("Sapphire");
            a2Button.setText("Pyrrite");
            a3Button.setText("Marble");
            a4Button.setText("Opal"); }
    }
    private void setupQ4S1gameplay() {
        if (pickS16 == 0){ diffuse24 = new Image("/8bit/gamequad4s1fame0.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Mount Everest");
            a2Button.setText("Mount Rushmore");
            a3Button.setText("Sun Valley Idaho");
            a4Button.setText("Bay of Pigs"); }
        if (pickS16 == 1){ diffuse24 = new Image("/8bit/gamequad4s1fame1.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Times Square");
            a2Button.setText("Paris");
            a3Button.setText("Big Ben");
            a4Button.setText("Los Angeles"); }
        if (pickS16 == 2){ diffuse24 = new Image("/8bit/gamequad4s1fame2.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Kaiser Wilhelm");
            a2Button.setText("Abraham Lincoln");
            a3Button.setText("Ponce De Leon");
            a4Button.setText("Ferdinand Magellan"); }
    }
    private void setupQ4S2gameplay() {
        if (pickS17 == 0){ diffuse24 = new Image("/8bit/gamequad4s2fame0.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Empire State");
            a2Button.setText("Eiffel Tower");
            a3Button.setText("White House");
            a4Button.setText("Tower of Pizza"); }
        if (pickS17 == 1){ diffuse24 = new Image("/8bit/gamequad4s2fame1.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Sphynx");
            a2Button.setText("Mount Rushmore");
            a3Button.setText("Pyramid");
            a4Button.setText("White House"); }
        if (pickS17 == 2){ diffuse24 = new Image("/8bit/gamequad4s2fame2.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Mount Whitney");
            a2Button.setText("Egyptian Pyramid");
            a3Button.setText("White House");
            a4Button.setText("Mount Rushmore"); }
    }
    private void setupQ4S3gameplay() {
        if (pickS18 == 0){ diffuse24 = new Image("/8bit/gamequad4s3fame0.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Joan of Arc");
            a2Button.setText("Hercules");
            a3Button.setText("Statue of Liberty");
            a4Button.setText("Ben Franklin"); }
        if (pickS18 == 1){ diffuse24 = new Image("/8bit/gamequad4s3fame1.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Abe Lincoln");
            a2Button.setText("Thomas Jefferson");
            a3Button.setText("George Washington");
            a4Button.setText("Theodore Roosevelt"); }
        if (pickS18 == 2){ diffuse24 = new Image("/8bit/gamequad4s3fame2.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Amsterdam");
            a2Button.setText("Descobrimentos");
            a3Button.setText("Venice, Italy");
            a4Button.setText("Athens, Greece"); }
    }
    private void setupQ4S4gameplay() {
        if (pickS19 == 0){ diffuse24 = new Image("/8bit/gamequad4s4fame0.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Saint Louis Arch");
            a2Button.setText("Arc De Triomphe");
            a3Button.setText("Magic Mountain");
            a4Button.setText("Disneyland"); }
        if (pickS19 == 1){ diffuse24 = new Image("/8bit/gamequad4s4fame1.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Eiffel Tower");
            a2Button.setText("Times Square");
            a3Button.setText("Miami Beach");
            a4Button.setText("Colisseum"); }
        if (pickS19 == 2){ diffuse24 = new Image("/8bit/gamequad4s4fame2.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Lake Michigan");
            a2Button.setText("Gulf of Mexico");
            a3Button.setText("Port San Pedro");
            a4Button.setText("Bay Bridge"); }
    }
    private void setupQ4S5gameplay() {
        if (pickS20 == 0){ diffuse24 = new Image("/8bit/gamequad4s5fame0.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Easter Island");
            a2Button.setText("Hawaii");
            a3Button.setText("Stonehenge");
            a4Button.setText("Scotland"); }
        if (pickS20 == 1){ diffuse24 = new Image("/8bit/gamequad4s5fame1.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("WDC Monument");
            a2Button.setText("New York City");
            a3Button.setText("Miami");
            a4Button.setText("Los Angeles"); }
        if (pickS20 == 2){ diffuse24 = new Image("/8bit/gamequad4s5fame2.png", 512, 512, true, true, true);
            Shader24.setDiffuseMap(diffuse24);
            a1Button.setText("Stirling Castle");
            a2Button.setText("Warwick Castle");
            a3Button.setText("Saxony Castle");
            a4Button.setText("Hohenzollern"); }
    }
}