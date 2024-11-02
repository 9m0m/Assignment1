package fall24.hsf301.slot1.fxgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Load the VBox layout from the LoginForm.fxml file
			VBox root = (VBox) FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
			Scene scene = new Scene(root, 640, 400); // Adjust size according to your FXML design
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login Form"); // Optionally set a title
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
