package fall24.hsf301.slot1.service;

import java.util.List;
import java.util.Scanner;

import fall24.hsf301.slot1.pojo.Book;
import fall24.hsf301.slot1.pojo.Student;
import fall24.hsf301.slot1.repository.IStudentRepository;
import fall24.hsf301.slot1.repository.StudentRepository;

public class StudentService implements IStudentService {
        IStudentRepository iStudentRepository = null;
        public StudentService(String fileName){
            iStudentRepository = new StudentRepository(fileName);
        }
        @Override
        public List<Student> findAll() {
            return iStudentRepository.findAll();
        }
        @Override
        public void save(Student student) {
            iStudentRepository.save(student);
        }
        @Override
        public void delete(int studentID) {
            iStudentRepository.delete(studentID);
        }
        @Override
        public Student findById(int studentID) {
            return iStudentRepository.findById(studentID);
        }
        @Override
        public void update(Student student) {
            iStudentRepository.update(student);
        }
        @Override
        public Student readStudentInformation() {
        	Scanner sc = new Scanner(System.in);
        	System.out.println("First Name : ");
        	String firstName = sc.nextLine();
        	System.out.println("Last Name : ");
        	String lastName = sc.nextLine();
        	System.out.println("Marks: ");
        	String mark = sc.next();
        	return new Student(firstName,lastName,mark);
        }
}
