package fall24.hsf301.slot1.fxcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import fall24.hsf301.slot1.pojo.Student;
import fall24.hsf301.slot1.service.IStudentService;
import fall24.hsf301.slot1.service.StudentService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class StudentManagementController implements Initializable {
	@FXML
	private Button btnCreate;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnUpdate;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtMark;

	@FXML
	private TableView stdTable;

	@FXML
	private TableColumn<Student, Integer> id;

	@FXML
	private TableColumn<Student, String> firstName;

	@FXML
	private TableColumn<Student, String> lastName;

	@FXML
	private TableColumn<Student, Integer> mark;

	private IStudentService studentService;

	private ObservableList<Student> studentModel;

	private String userRole;

	public StudentManagementController() {
		studentService = new StudentService("JPAs");
		studentModel = FXCollections.observableArrayList(studentService.findAll());
	}

	// Giau CRUD theo role
	public void initializeWithRole(String role) {
		this.userRole = role;
		if (!"admin".equalsIgnoreCase(role)) {
			btnCreate.setVisible(false);
			btnDelete.setVisible(false);
			btnUpdate.setVisible(false);
			txtId.setDisable(true);
			txtFirstName.setDisable(true);
			txtLastName.setDisable(true);
			txtMark.setDisable(true);
		}
	}

	// Mouse button from stackoverflow
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		mark.setCellValueFactory(new PropertyValueFactory<>("Mark"));
		this.refeshDataGrid();
		stdTable.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
				Student selectedStudent = (Student) stdTable.getSelectionModel().getSelectedItem();
				if (selectedStudent != null) {
					populateFields(selectedStudent);
				}
			}
		});
	}

	private void refeshDataGrid() {
		studentModel = FXCollections.observableArrayList(studentService.findAll());
		stdTable.setItems(studentModel);
	}

	private void ClearInput() {
		txtId.clear();
		txtFirstName.clear();
		txtLastName.clear();
		txtMark.clear();
	}

	public void btnCancelOnAction() {
		Platform.exit();
	}

	public void btnCreateOnAction() {
		Student student = new Student();
		student.setFirstName(txtFirstName.getText());
		student.setLastName(txtLastName.getText());
		student.setMark(txtMark.getText());
		studentService.save(student);
		this.refeshDataGrid();
		this.ClearInput();
	}

	public void btnDeleteOnAction() {
		studentService.delete(Integer.parseInt(txtId.getText()));
		this.refeshDataGrid();
		this.ClearInput();
	}

	public void btnUpdateOnAction() {
		Student student = new Student();
		student.setId(Integer.parseInt(txtId.getText()));
		student.setFirstName(txtFirstName.getText());
		student.setLastName(txtLastName.getText());
		student.setMark(txtMark.getText());
		studentService.update(student);
		this.refeshDataGrid();
		this.ClearInput();
	}

	// bien dung cho mouse button
	private void populateFields(Student student) {
		txtId.setText(String.valueOf(student.getId()));
		txtFirstName.setText(student.getFirstName());
		txtLastName.setText(student.getLastName());
		txtMark.setText(String.valueOf(student.getMark()));
	}
}