package fall24.hsf301.slot1.repository;

import java.util.List;

import fall24.hsf301.slot1.pojo.Student;

public interface IStudentRepository {
    public List<Student> findAll();

	public void save(Student student);

	public void delete(int studentID);

	public Student findById(int studentID);

	public void update(Student student);
}
