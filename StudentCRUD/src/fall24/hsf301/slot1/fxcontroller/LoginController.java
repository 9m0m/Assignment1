package fall24.hsf301.slot1.fxcontroller;

import java.io.IOException;

import fall24.hsf301.slot1.dao.AccountDAO;
import fall24.hsf301.slot1.pojo.Account;
import fall24.hsf301.slot1.repository.AccountRepository;
import fall24.hsf301.slot1.service.AccountService;
import fall24.hsf301.slot1.service.IAccountService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;
	@FXML
	private Button btLogin;
	@FXML
	private Button btCancel;

	private IAccountService accountService;

	private AccountDAO accountDAO;

	public LoginController() {
		AccountDAO accountDAO = new AccountDAO("JPAs");
		AccountRepository accountRepository = new AccountRepository(accountDAO);
		this.accountService = new AccountService(accountRepository);
	}

	@FXML
	public void closeLoginForm(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	public void btLoginAction(ActionEvent e) throws IOException {
		String username = txtUsername.getText();
		String password = txtPassword.getText();

		Account account = accountService.login(username, password);

		if (account != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxgui/StudentManagement.fxml"));
				Parent root = loader.load();

				StudentManagementController controller = loader.getController();
				controller.initializeWithRole(account.getRole());

				Stage primaryStage = new Stage();
				primaryStage.setScene(new Scene(root));
				primaryStage.show();

				((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
			} catch (IOException ex) {
				ex.printStackTrace();
				showErrorAlert("Error loading student management window");
			}
		} else {
			showErrorAlert("Invalid username or password!");
		}
	}

	private void showErrorAlert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}