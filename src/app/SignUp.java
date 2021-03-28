package app;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.BCrypt;
import utils.DBConnection;
import utils.Language;

public class SignUp extends GridPane {
	private Label firstName;
	private Label lastName;
	private Label email;
	private Label password;
	private Text formName;
	public static TextField firstNameTextField = new TextField();
	public static TextField lastNameTextField = new TextField();
	public static TextField emailTextField = new TextField();
	public static PasswordField passwordTextField = new PasswordField();
	public static Scene gameScene;
	public static Label errorLabel  = new Label();
	
	public GridPane getSignUp() {
		firstName = Language.getLabel("label10");
		lastName = Language.getLabel("label11");
		email = Language.getLabel("label12");
		password = Language.getLabel("label13");
		//add gridpane
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(20));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setStyle("-fx-background-color: #D5D4D4");

		Button backBtn = new Button();
		backBtn = Language.getButton("Button6");
		backBtn.setOnAction(e -> {
			cleanForm();
			(Main.window).setScene(Main.indexScene);
		});
		backBtn.setStyle("-fx-text-fill: black; " + "-fx-font-family:'Arial'; "
				+ "-fx-background-color: linear-gradient(#CACCD1,#F3F4F7); "
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		formName = Language.getText("text2");
		formName.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
		//create hbox with 120 spacing
		HBox title = new HBox(120);
		title.getChildren().addAll(formName, backBtn);

		pane.add(title, 0, 0, 2, 1);
		pane.add(firstName, 0, 1);
		pane.add(firstNameTextField, 1, 1);
		pane.add(lastName, 0, 2);
		pane.add(lastNameTextField, 1, 2);
		pane.add(email, 0, 3);
		pane.add(emailTextField, 1, 3);
		pane.add(password, 0, 4);
		pane.add(passwordTextField, 1, 4);
		pane.add(errorLabel, 0, 6, 3, 3);
		Button signUpBtn = new Button();
		signUpBtn = Language.getButton("Button4");
		signUpBtn.setStyle("-fx-text-fill: black; " + "-fx-font-family:'Arial'; "
				+ "-fx-background-color: linear-gradient(#CACCD1,#F3F4F7); "
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		signUpBtn.setOnAction(e -> signUpAction());
		pane.setOnKeyPressed(e -> {
									if(e.getCode() == KeyCode.ENTER) 
									signUpAction();});
		pane.add(signUpBtn, 1, 5);
		GridPane.setHalignment(signUpBtn, HPos.RIGHT);
		//final Text actionTarget = new Text();
		//pane.add(actionTarget, 1, 9);
		return pane;
	}

	public void cleanForm() {
		firstNameTextField.setText("");
		firstNameTextField.setStyle("-fx-background:white;");
		firstNameTextField.setPromptText("");
		lastNameTextField.setText("");
		lastNameTextField.setStyle("-fx-background:white;");
		lastNameTextField.setPromptText("");
		emailTextField.setText("");
		emailTextField.setStyle("-fx-background:white;");
		emailTextField.setPromptText("");
		passwordTextField.setText("");
		passwordTextField.setStyle("-fx-background:white;");
		passwordTextField.setPromptText("");
		errorLabel.setText("");
	}

	public void removeErrors() {
		firstNameTextField.setStyle("-fx-background:white;");
		firstNameTextField.setPromptText("");
		lastNameTextField.setStyle("-fx-background:white;");
		lastNameTextField.setPromptText("");
		emailTextField.setStyle("-fx-background:white;");
		emailTextField.setPromptText("");
		passwordTextField.setStyle("-fx-background:white;");
		passwordTextField.setPromptText("");
	}
	public void signUpAction()
	{
		removeErrors();
		if(valid())
		{
			if(checkEmail(emailTextField.getText())) {
				addUser(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText().toLowerCase(), passwordTextField.getText());
				//add css
				errorLabel.setStyle("-fx-text-fill: green");
				errorLabel.setText(Language.getLabel("TextField7").getText() + "\n" + Language.getLabel("TextField8").getText());
			} else {
				errorLabel.setStyle("-fx-text-fill: red");
				errorLabel.setText(Language.getLabel("TextField6").getText());
			}
		}
		else
		{
			Validator.textFieldNotEmpty(SignUp.firstNameTextField, Language.getLabel("TextField2").getText());
			Validator.textFieldNotEmpty(SignUp.lastNameTextField, Language.getLabel("TextField3").getText());
			Validator.emailValidate(SignUp.emailTextField, Language.getLabel("TextField4").getText());
		}
	}
	public static boolean valid()
	{
		return Validator.emailValidate(SignUp.emailTextField,Language.getLabel("TextField5").getText()) && Validator.textFieldNotEmpty(SignUp.firstNameTextField) && Validator.textFieldNotEmpty(SignUp.lastNameTextField) && Validator.textFieldNotEmpty(SignUp.passwordTextField);
	}
	public static boolean addUser(String firstName, String lastName, String email, String password) {
		try 
		{
			String hash = BCrypt.hashpw(password, BCrypt.gensalt());
			String query = "INSERT INTO users(firstName, lastName, email, hash, date_created) VALUES(?, ?, ?, ?, CURDATE())";
			PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, hash);
			
			if (preparedStatement.executeUpdate() > 0) {
				ResultSet rs = preparedStatement.getGeneratedKeys();
				rs.next();
			}
			return false;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean checkEmail(String email) {
		try {
			String query = "SELECT * FROM users WHERE email = ?";
			PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, email);
			//execute query
			ResultSet rez = preparedStatement.executeQuery();
			//move the cursor forward one row
			return !rez.next();
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
}