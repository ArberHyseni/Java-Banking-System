package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import utils.DBConnection;
import utils.Language;
import javafx.scene.Scene;

public class Main extends Application {
	public static Stage window;
	public static Scene indexScene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		DBConnection.getConnection();
		window = primaryStage;
		window.setOnCloseRequest(e->{
			Platform.exit();
			System.exit(0);
		});
		Index index = new Index();
		indexScene = new Scene(index.getIndex(), 400, 400);
		window.setTitle(Language.getLabel("label8").getText());
		window.setScene(indexScene);
		window.show();
	}
}