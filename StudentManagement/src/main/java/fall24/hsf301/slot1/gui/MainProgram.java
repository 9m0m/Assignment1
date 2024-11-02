package fall24.hsf301.slot1.gui;

import fall24.hsf301.slot1.dao.StudentDAO;
import fall24.hsf301.slot1.pojo.Student;
import fall24.hsf301.slot1.repository.IStudentRepository;
import fall24.hsf301.slot1.repository.StudentRepository;
import fall24.hsf301.slot1.service.IStudentService;
import fall24.hsf301.slot1.service.StudentService;
import java.util.List;
import java.util.Scanner;
public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String fileName = "hibernate.cfg.xml";
		String fileName = "JPAs";
		IStudentService student = new StudentService(fileName);

		Scanner sc = new Scanner(System.in);
		System.out.println("========================Menu=========================");
		System.out.println("                1.Add student                ");
		System.out.println("                2.Delete student                ");
		System.out.println("                3.Update student                ");
		System.out.println("                4.Find all student                ");
		System.out.println("                5.Find student by ID                ");
		System.out.println("                6.Exit                ");
		System.out.println("========================Menu=========================");
		//Each case will save student information to file
		int choice = 0;
		do {
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter student firstname: ");
				String firstName = sc.next();
				System.out.println("Enter student lastname: ");
				String lastName = sc.next();
				System.out.println("Enter student mark: ");
				String mark = sc.next();
				student.save(new Student(firstName, lastName, mark));
				break;
			case 2:
				System.out.println("Enter student ID to delete: ");
				int studentIDDelete = sc.nextInt();
				student.delete(studentIDDelete);
				break;
			case 3:
				System.out.println("Enter student ID to update: ");
				int idUpdate = sc.nextInt();
				System.out.println("Enter student firstname: ");
				String firstName1 =sc.next();
				System.out.println("Enter student lastname: ");
				String lastName1 = sc.next();
				System.out.println("Enter student mark: ");
				String mark1 = sc.next();
				student.update(new Student(firstName1,lastName1,mark1));
				student.save(new Student(firstName1,lastName1,mark1));
				break;
			case 4:
				List<Student> students = student.findAll();
				for(Student s: students) {
					System.out.println(s);
				}
				break;
			case 5:
				System.out.println("Enter student ID to find: ");
				int studentIDFind = sc.nextInt();
				Student s = student.findById(studentIDFind);
				System.out.println(s);
				break;
			default:
				System.out.println("Exit program");
				break;
			}
	}while(choice>=1 && choice<=5);
		sc.close();
	}
}
