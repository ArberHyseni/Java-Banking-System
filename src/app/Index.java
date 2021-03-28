package app;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import utils.Language;

public class Index extends VBox {
	public static Scene logInScene, signInScene;
	private Button login;
	private Button signup;
	private Label welcome;

	public VBox getIndex() {
		VBox pane = new VBox(5);
		pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));

		Login form1 = new Login();
		SignUp form2 = new SignUp();
		logInScene = new Scene(form1.getLogIn(), 400, 400);
		signInScene = new Scene(form2.getSignUp(), 400, 400);
		
		HBox buttonPane = new HBox(13);
		buttonPane.setAlignment(Pos.CENTER);
		HBox buttonPane1 = new HBox(13);
        buttonPane1.setAlignment(Pos.CENTER);
        //add padding using Insets
		buttonPane1.setPadding(new Insets(5, 5, 5, 5));

		login = Language.getButton("Button3");
		signup = Language.getButton("Button4");
		login.setStyle("-fx-text-fill: black; " + "-fx-font-family:'Arial'; "
				+ "-fx-background-color: linear-gradient(#CACCD1,#F3F4F7\r\n" + 
				"); "
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		signup.setStyle("-fx-text-fill: black; " + "-fx-font-family:'Arial'; "
				+ "-fx-background-color: linear-gradient(#CACCD1,#F3F4F7); "
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		buttonPane1.getChildren().addAll(login, signup);
		//onclick action
		login.setOnAction(e -> {
			(Main.window).setScene(logInScene);
			(Main.window).setMinWidth(600);
			(Main.window).setMinHeight(400);
			(Main.window).setTitle(Language.getLabel("Button3").getText());
        });
        //onclick action
		signup.setOnAction(e -> {
			(Main.window).setScene(signInScene);
			(Main.window).setMinWidth(600);
			(Main.window).setMinHeight(400);
			(Main.window).setTitle(Language.getLabel("Button4").getText());
        });
		HBox languageButtons = new HBox(13);
		Button alBtn = new Button();
		Button enBtn = new Button();
		//get al. flag image from img folder
		Image alImg = new Image("img/al1.png");
		Image enImg = new Image("img/en1.png");
		alBtn.setGraphic(new ImageView(alImg));
		enBtn.setGraphic(new ImageView(enImg));
		HBox.setMargin(alBtn, new Insets(5, 1, 5, 4));
		HBox.setMargin(enBtn, new Insets(5, 5, 5, -3));
		alBtn.setOnAction(e -> {
			Language.setLocale(new Locale("al"));
		});
		enBtn.setOnAction(e -> {
			Language.setLocale(new Locale("en"));
		});
		languageButtons.getChildren().addAll(alBtn, enBtn);
		languageButtons.setAlignment(Pos.CENTER);
		//get label from Languages
		welcome = Language.getLabel("label14");
		welcome.setPadding(new Insets(3, 3, 3, 3));
		welcome.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
		pane.getChildren().addAll(welcome, buttonPane, buttonPane1, languageButtons);
		return pane;
	}
}