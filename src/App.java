import interfaces.Remunerable;
import Q1.*;

public class App {
    public static void main(String[] args) throws Exception {
        UndergraduateProgram program = new UndergraduateProgram("Computer Science", "CS101", 4);

        Student student1 = new 
            PostgraduateStudent("Alice", 25, "PG123", "AI Research", "Dr. Smith", 20, 1500.0);
        Student student2 = 
            new ScientificInitiationStudent("Bob", 20, "UG456", "Data Science", 15, 1000.0);
        Student student3 = 
            new UndergraduateStudent("Charlie", 22, "UG789", "Computer Science");
        program.addStudent(student1);
        program.addStudent(student2);
        program.addStudent(student3);

        for (Student student : program.getStudents()) {
            System.out.println();
            System.out.println(student);
            if (student instanceof Remunerable) {
                Remunerable remunerable = (Remunerable) student;
                System.out.println("Worked Days: " + remunerable.getWorkedDays());
                System.out.println("Scholarship Amount: " + remunerable.getScholarshipAmount());
                System.out.println("Payment: " + remunerable.calculatePayment());
                System.out.println();
            }
        }
    }
}
