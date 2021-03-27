package app;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import utils.DBConnection;
import utils.Help;
import utils.Language;
import utils.Session;


public class Game extends BorderPane
{
	public static Scene logInScene, signInScene, gameScene;
	private Menu fileMenu;
	private Menu languageMenu;
	private Menu helpMenu;
	private MenuItem about;
	private Label hello;
	private Label amount;
	private Label deposit;
	private Label check;
	private Label total;
	private TextField tf1;
	private TextField tf2;
	public static TextField depositTf = new TextField();
	//get message
//	private Label scoreLabel1 = Language.getLabel("label5");
//	private Label scoreLabel5 = Language.getLabel("label6");
	private TextField tf22;
	
	public Pane mainPane() {
		BorderPane gp = new BorderPane();
		GridPane menupane = new GridPane();
		Label menuLabel = new Label("BKK Homepage");
		menuLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		HBox tophb = new HBox(menuLabel);
		tophb.setAlignment(Pos.CENTER);
		
		Label depositLabel = new Label("Deposit Amount:");
		menuLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		menupane.add(depositLabel, 0, 2);
		
		GridPane.setMargin(depositLabel, new Insets(5,5,5,8));
		GridPane.setMargin(depositTf, new Insets(0,0,0,-5));
		menupane.add(depositTf, 1, 2);
		menupane.setHgap(10);
		menupane.setVgap(10);
		gp.setTop(tophb);
		gp.setCenter(menupane);
		
		
		return gp;
		
	}
}