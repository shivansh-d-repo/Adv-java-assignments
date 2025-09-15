import service.UniversityService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniversityService service = new UniversityService();
        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n===== University Course Registration System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Register Student for Course");
            System.out.println("4. View All Students with Courses");
            System.out.println("5. Search Courses by Minimum Credits");
            System.out.println("6. Get Students in a Course");
            System.out.println("7. Sort Students by Year & Name");
            System.out.println("8. Calculate Total Credits Per Student");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    service.addStudent(name, year);
                    break;

                case 2:
                    System.out.print("Enter course title: ");
                    sc.nextLine();
                    String title = sc.nextLine();
                    System.out.print("Enter credits: ");
                    int credits = sc.nextInt();
                    service.addCourse(title, credits);
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Enter course ID: ");
                    int cid = sc.nextInt();
                    service.registerStudentForCourse(sid, cid);
                    break;

                case 4:
                    service.viewAllStudentsWithCourses();
                    break;

                case 5:
                    System.out.print("Enter minimum credits: ");
                    int min = sc.nextInt();
                    service.searchCoursesByCredits(min);
                    break;

                case 6:
                    System.out.print("Enter course ID: ");
                    int cId = sc.nextInt();
                    service.getStudentsInCourse(cId);
                    break;

                case 7:
                    service.sortStudentsByYearAndName();
                    break;

                case 8:
                    service.calculateTotalCreditsPerStudent();
                    break;

                case 9:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 9);

        sc.close();
    }
}