package src;

import java.sql.*;
import java.util.*;
import java.util.Collections;

public class main {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static Map<Integer, classes> classes;
    private static Map<Integer, users> users;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        initializeDatabaseConnection();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Learner's Management System");

        while (true) {
            System.out.println("Please enter username");
            String email = scanner.next();

            if (Objects.equals(email, "andile@gmail.com")) {
                showAdminMenu(scanner);
            } else {
                System.out.println("Invalid username");
            }
        }
    }

    private static void initializeDatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/learners_accademy";
        String userName = "root";
        String password = "Gawoziwami@01";
        connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement();
    }

    private static void showAdminMenu(Scanner scanner) throws SQLException {
        System.out.println("Hi Andile, select an option to interact with the system");
        while (true) {
            System.out.println("Welcome to LMS menu!\n\n" +
                    "Main Menu:\n\n" +
                    "1. Set up users list\n" +
                    "2. Set up list of classes\n" +
                    "3. Assign students to classes\n" +
                    "4. Assign teachers to classes\n" +
                    "5. View class report\n" +
                    "6. Close the system\n\nTo interact with the LMS, choose a number:");

            String choice = scanner.next();
            switch (choice) {
                case "1":
                    setupUsersList();
                    break;
                case "2":
                    setupListOfClasses();
                    break;
                case "3":
                    assignStudentsToClasses();
                    break;
                case "4":
                    assignTeachersToClasses();
                    break;
                case "5":
                    viewClassReport();
                    break;
                case "6":
                    closeSystem();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewClassReport() throws SQLException {
        System.out.println("View Class Report");

        for (var entry : classes.entrySet()) {
            System.out.println("Class ID: " + entry.getKey() + " - Class Name: " + entry.getValue().getClassName());
        }

        System.out.println("Enter the Class ID to view the report:");
        int classId = scanner.nextInt();

        if (!classes.containsKey(classId)) {
            System.out.println("Invalid Class ID.");
            return;
        }

        classes selectedClass = classes.get(classId);
        System.out.println("Class Name: " + selectedClass.getClassName());
        System.out.println("Class Description: " + selectedClass.getClassDescription());
        System.out.println("List of Students in the Class:");

        for (var user : users.entrySet()) {
            if (user.getValue().getClassID() == classId && user.getValue().getUserType() == 2) {
                System.out.println("Student ID: " + user.getKey() + " - Name: " + user.getValue().getFname());
            }
        }

        System.out.println("List of Subjects and Teachers in the Class:");

        // Retrieve and display subjects for the class
        String subjectSql = "SELECT subjectName FROM subjects WHERE classID = " + classId;
        resultSet = statement.executeQuery(subjectSql);

        while (resultSet.next()) {
            System.out.println("Subject: " + resultSet.getString("subjectName"));
        }

        // Retrieve and display teachers for the class
        String teacherSql = "SELECT fName, lName FROM users WHERE classID = " + classId + " AND userType = 3";
        ResultSet teacherResultSet = statement.executeQuery(teacherSql);

        while (teacherResultSet.next()) {
            System.out.println("Teacher: " + teacherResultSet.getString("fName") + " " + teacherResultSet.getString("lName"));
        }
    }


    private static void assignTeachersToClasses() throws SQLException {
        setupUsersList();
        setupListOfClasses();

        for (var user : users.entrySet()) {
            if (user.getValue().getUserType() == 3 && user.getValue().getClassID() < 1) {
                System.out.println("This is a list of unassigned teachers to classes.");
                List<Integer> sortedKeys = new ArrayList<>(users.keySet());
                Collections.sort(sortedKeys);
                for (int userId : sortedKeys) {
                    users teacher = users.get(userId);
                    System.out.println(userId + " " + teacher.getFname());
                }
                System.out.println("Select a teacher you want to assign:");
                int studentID = scanner.nextInt();
                if (users.containsKey(studentID)) {
                    System.out.println("Select a class to assign the teacher:");
                    int classID = scanner.nextInt();
                    if (classes.containsKey(classID)) {
                        String sql = "UPDATE users SET classID = " + classID + " WHERE userId = " + studentID;
                        statement.executeUpdate(sql);
                        System.out.println(studentID + " is assigned to class " + classID);
                    } else {
                        System.out.println("Invalid class selection.");
                    }
                } else {
                    System.out.println("Invalid student selection.");
                }
            }
        }
    }



    private static void setupUsersList() throws SQLException {
        users = new HashMap<Integer,users>();
        resultSet = statement.executeQuery("SELECT * FROM users");
        System.out.println("ID\t\tName\t\tSurname");
        while (resultSet.next()) {
            int userId = resultSet.getInt(1);
            String fname = resultSet.getString(2);
            String lname = resultSet.getString(3);
            System.out.println(userId + "\t\t" + fname + "\t\t" + lname);
            users.put(userId, new users(fname, lname, resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6)));
        }
    }

    private static void setupListOfClasses() throws SQLException {
        classes = new HashMap<>();
        resultSet = statement.executeQuery("SELECT * FROM classes");
        System.out.println("ClassID\t\tClassName\t\tClassDescription");
        while (resultSet.next()) {
            int classId = resultSet.getInt(1);
            String className = resultSet.getString(2);
            String classDescription = resultSet.getString(3);
            System.out.println(classId + "\t\t" + className + "\t\t" + classDescription);
            classes.put(classId, new classes(classId, className, classDescription));
        }
    }

    private static void assignStudentsToClasses() throws SQLException {
        setupUsersList();
        setupListOfClasses();

        for (var user : users.entrySet()) {
            if (user.getValue().getUserType() == 2 && user.getValue().getClassID() < 1) {
                System.out.println("This is a list of unassigned students to classes.");
                List<Integer> sortedKeys = new ArrayList<>(users.keySet());
                Collections.sort(sortedKeys);
                for (int userId : sortedKeys) {
                    users student = users.get(userId);
                    System.out.println(userId + " " + student.getFname());
                }
                System.out.println("Select a student you want to assign:");
                int studentID = scanner.nextInt();
                if (users.containsKey(studentID)) {
                    System.out.println("Select a class to assign the student:");
                    int classID = scanner.nextInt();
                    if (classes.containsKey(classID)) {
                        String sql = "UPDATE users SET classID = " + classID + " WHERE userId = " + studentID;
                        statement.executeUpdate(sql);
                        System.out.println(studentID + " is assigned to class " + classID);
                    } else {
                        System.out.println("Invalid class selection.");
                    }
                } else {
                    System.out.println("Invalid student selection.");
                }
            }
        }
    }

    // Add methods for other menu options here

    private static void closeSystem() {
        System.out.println("Thank you for using LMS. Goodbye!");
        System.exit(0);
    }
}

