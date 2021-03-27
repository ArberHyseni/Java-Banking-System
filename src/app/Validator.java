package app;

import java.util.regex.Pattern;
import javafx.scene.control.TextField;

public class Validator {

	public static final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	//[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com
	public static boolean textFieldNotEmpty(TextField t) {
		if (t.getText() != null && !t.getText().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean textFieldNotEmpty(TextField t, String sValidationText) {
		String err = "";
		if (!textFieldNotEmpty(t)) {
			err = sValidationText;
			t.setStyle("-fx-background:red;");
			t.setPromptText(err);
			return false;
		}
		return true;
	}

	public static boolean emailValidate(TextField t, String sValidationText) {
		String email = t.getText();
		String err = null;
		if (!textFieldNotEmpty(t) || !emailPattern.matcher(email).matches()) {
			err = sValidationText;
			t.setStyle("-fx-background:red;");
			t.setText("");
			t.setPromptText(err);
			return false;
		}
		return true;
	}
}