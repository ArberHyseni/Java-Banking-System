package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import utils.DBConnection;
import utils.Help;
import utils.Language;
import utils.Session;
import utils.TransactionModel;


public class Game extends BorderPane
{
	public static Scene logInScene, signInScene, gameScene;
	private Button depositButton;
	private Button withdrawButton;
	private Button showPreviousButton;
	private Button showBalanceButton;
//	private Button accinformationButton = new Button("Show acc information");
	private static TextField depositTf = new TextField();
	private static TextField withdrawTf = new TextField();
	private static Label errorLabel = new Label();
	private Label menuLabel;
	private Label depositLabel;
	private Label withdraw;

	public Pane mainPane() {
		BorderPane gp = new BorderPane();
		GridPane menupane = new GridPane();
		StackPane sp = new StackPane();
		menuLabel = Language.getLabel("label8");
		menuLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		HBox tophb = new HBox(menuLabel);
		tophb.setAlignment(Pos.CENTER);

		depositLabel = Language.getLabel("label19");
		depositLabel.setMinWidth(USE_PREF_SIZE);
		depositButton = Language.getButton("Button1");
		depositButton.setMinWidth(USE_PREF_SIZE);
		depositLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		HBox depositBox = new HBox(depositLabel, depositTf, depositButton);
		depositBox.setSpacing(15);
		depositBox.setMargin(depositButton, new Insets(0,0,0,-1));
		depositBox.setMargin(depositTf, new Insets(0,0,0,16));
		menupane.add(depositBox, 0, 1);
		withdrawButton = Language.getButton("Button1");
		showPreviousButton = Language.getButton("Button7");
		showBalanceButton = Language.getButton("Button8");
		depositButton.setStyle("-fx-text-fill: black; " + "-fx-font-family:'Arial'; "
				+ "-fx-background-color: linear-gradient(#CACCD1,#F3F4F7); "
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		withdrawButton.setStyle("-fx-text-fill: black; " + "-fx-font-family:'Arial'; "
				+ "-fx-background-color: linear-gradient(#CACCD1,#F3F4F7); "
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		showBalanceButton.setStyle("-fx-text-fill: black; " + "-fx-font-family:'Arial'; "
				+ "-fx-background-color: linear-gradient(#CACCD1,#F3F4F7); "
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
//		accinformationButton.setStyle("-fx-text-fill: black; " + "-fx-font-family:'Arial'; "
//				+ "-fx-background-color: linear-gradient(#CACCD1,#F3F4F7); "
//				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		showPreviousButton.setStyle("-fx-text-fill: black; " + "-fx-font-family:'Arial'; "
				+ "-fx-background-color: linear-gradient(#CACCD1,#F3F4F7); "
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");

		GridPane.setMargin(depositBox, new Insets(10, 0, 0, 20));
		depositButton.setOnAction(e -> {
			String amount = depositTf.getText().toString();
			if (TransactionModel.checkDepositInfo(amount) && Validator.textFieldNotEmpty(depositTf)) {

				TransactionModel.deposit(Session.getId(), Double.parseDouble(amount));

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Deposit successful");
				alert.setHeaderText("Deposit Successful");
				alert.setContentText(null);
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Deposit unsuccessful");
				alert.setHeaderText("Not double");
				alert.setContentText(null);
			}
		});
		
		withdraw = Language.getLabel("label20");
		withdraw.setMinWidth(USE_PREF_SIZE);
		withdrawButton.setMinWidth(USE_PREF_SIZE);
		withdraw.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		HBox withdrawBox = new HBox(withdraw, withdrawTf, withdrawButton);
		withdrawBox.setSpacing(15);
		withdrawButton.setOnAction(e-> {
			String withdrawAmount = withdrawTf.getText().toString();
			if ((TransactionModel.getAccountBalance(Session.getId()) > Double.parseDouble(withdrawAmount))
					&& Validator.textFieldNotEmpty(withdrawTf)) {
				TransactionModel.performTransaction(withdrawAmount, Session.getBalance(), Session.getId());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Withdraw successful");
				alert.setHeaderText("Withdraw Successful");
				alert.setContentText(null);
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("E");
				alert.setHeaderText("Withdraw unsuccessful");
				alert.setContentText(null);
				alert.showAndWait();
			}
		});
		menupane.add(withdrawBox, 0,3);
		GridPane.setMargin(withdrawBox, new Insets(10,0,0,20));
		
		HBox buttonBox = new HBox(20);
		buttonBox.getChildren().addAll(showPreviousButton, showBalanceButton);
		sp.setAlignment(Pos.CENTER);
		sp.getChildren().addAll(buttonBox);
		StackPane.setAlignment(buttonBox, Pos.CENTER);
		StackPane.setMargin(buttonBox, new Insets(10,0,0, 150));
		menupane.add(sp, 0, 5);
		
		showBalanceButton.setOnAction(e -> {
			String balance = Double.toString(Session.getBalance());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Withdraw successful");
			alert.setHeaderText(null);
			alert.setContentText("Your balance is: " + balance);
			alert.showAndWait();
		});
		showPreviousButton.setOnAction(e -> new Transactions().getStage());
		HBox errorBox = new HBox(errorLabel);
		errorBox.setAlignment(Pos.CENTER);
		
		menupane.setHgap(10);
		menupane.setVgap(10);
		//use methods of borderpane
		gp.setTop(tophb);
		gp.setCenter(menupane);
		gp.setBottom(errorBox);
		gp.setStyle("-fx-background-color: #D5D4D4");
		return gp;
	}
}