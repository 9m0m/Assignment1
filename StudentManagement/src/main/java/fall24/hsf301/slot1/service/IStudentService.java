package fall24.hsf301.slot1.service;

import java.util.List;

import fall24.hsf301.slot1.pojo.Book;
import fall24.hsf301.slot1.pojo.Student;

public interface IStudentService {
	public List<Student> findAll();

	public void save(Student student);

	public void delete(int studentID);

	public Student findById(int studentID);

	public void update(Student student);
	
	public Student readStudentInformation();

}
