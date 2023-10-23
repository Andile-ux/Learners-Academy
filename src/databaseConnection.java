package src;

import java.sql.*;
import java.util.*;

public class databaseConnection {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Load the driver class
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/learners_accademy";
        String userName = "root";
        String password = "Gawoziwami@01";

        //create connection
        Connection connection = DriverManager.getConnection(url, userName, password);

        Statement statement = connection.createStatement();

        String sql;


        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Learner's Management System");
        System.out.println("please enter username");
        String email = scanner.next();

        if (Objects.equals(email, "andile@gmail.com")) {
            System.out.println("Hi Andile, select in the men to interact with system");
            ResultSet resultSet = null;
            Map<Integer, classes> classes = new HashMap<>();
            Map<Integer, users> users = new HashMap<>();

            System.out.println("Welcome to LMS menu!\n\n" +
                    "Main Menu:\n\n" +
                    "1. set up users list" +
                    " \n2. set up list classes " +
                    " \n3. assign students to classes " +
                    " \n4. assign teachers classes " +
                    " \n5. view class report " +
                    "\n6. close the system\n\n To interact with the LMS choose a number:");


            while (true)
            {

                if (scanner.equals("1")) {//                Map<Integer, users> users = new HashMap<>();

                    System.out.println("This is all users in the system.");
                    resultSet = null;
                    sql = "select *from users";
                    resultSet = statement.executeQuery(sql);

                    System.out.println("ID\t\tname\t\tsurname");
                    while (resultSet.next()) {

                        System.out.print(resultSet.getInt(1) +
                                "\t\t" + resultSet.getString(2) +
                                "\t\t" + resultSet.getString(3) + "\n");
                        users.put(resultSet.getInt(1), new users(resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5),
                                resultSet.getInt(6)));

                    }
                    if (scanner.equals("1")) {
                    } else if (scanner.equals("2")) {
                    } else if (scanner.equals("3")) {
                    } else {
                        System.out.println();
                    }
                } else if (scanner.equals("2")) {//adding classes to dictionary
//                Map<Integer, classes> classes = new HashMap<>();

                    resultSet = null;
                    sql = "select *from classes";
                    resultSet = statement.executeQuery(sql);

                    System.out.println("classID\t\tClassName\t\tClassDescription");
                    while (resultSet.next()) {
                        System.out.print(resultSet.getInt(1) +
                                "\t\t" + resultSet.getString(2) +
                                "\t\t" + resultSet.getString(3) + "\n");
                        classes.put(resultSet.getInt(1), new classes(resultSet.getInt(1),
                                resultSet.getString(2), resultSet.getString(3)));

                    }
                } else if (scanner.equals("3")) {//adding classes to dictionary
//                Map<Integer, classes> classes = new HashMap<>();

                    resultSet = null;
                    sql = "select *from classes";
                    resultSet = statement.executeQuery(sql);

                    System.out.println("classID\t\tClassName\t\tClassDescription");
                    while (resultSet.next()) {
                        System.out.print(resultSet.getInt(1) +
                                "\t\t" + resultSet.getString(2) +
                                "\t\t" + resultSet.getString(3) + "\n");
                        classes.put(resultSet.getInt(1), new classes(resultSet.getInt(1),
                                resultSet.getString(2), resultSet.getString(3)));

                    }


                    for (var user : users.entrySet()
                    ) {
                        if (user.getValue().getUserType() == 2 && user.getValue().getClassID() < 1) {
                            System.out.println("this is a list of unassigned students to classes.");
                            List sortedKeys = new ArrayList(users.keySet());
                            Collections.sort(sortedKeys);
                            System.out.println(user.getKey() + " " + user.getValue().getFname());
                        }
                        System.out.println("select a student you wanna assign");
                        int studentID = scanner.nextInt();
                        //exption for incorrect input

                        classes.forEach((integer, classes1) -> System.out.println(integer + " " + classes1.getClassName()));
                        System.out.println("select a class to assign student");
                        int classID = scanner.nextInt();
                        //exption for incorrect input
                        sql = "UPDATE users SET classID =" + classID + " WHERE userId = " + studentID + ";";
                        statement.executeUpdate(sql);
                        System.out.println(studentID + " is assigned to " + classID);
                    }
                } else if (scanner.equals("4")) {//                         //adding classes to dictionary
//                Map<Integer, classes> classes = new HashMap<>();

                    resultSet = null;
                    sql = "select *from classes";
                    resultSet = statement.executeQuery(sql);

                    System.out.println("classID\t\tClassName\t\tClassDescription");
                    while (resultSet.next()) {
                        System.out.print(resultSet.getInt(1) +
                                "\t\t" + resultSet.getString(2) +
                                "\t\t" + resultSet.getString(3) + "\n");
                        classes.put(resultSet.getInt(1), new classes(resultSet.getInt(1),
                                resultSet.getString(2), resultSet.getString(3)));

                    }
                    for (var user : users.entrySet()
                    ) {
                        if (user.getValue().getUserType() == 3 && user.getValue().getClassID() < 1) {
                            System.out.println("this is a sorted list of unassigned teachers to classes.");

                            List sortedKeys = new ArrayList(users.keySet());
                            Collections.sort(sortedKeys);
                            System.out.println(user.getKey() + " " + user.getValue().getFname());
                        }

                    }
                    System.out.println("select a teacher you wanna assign");
                    int studentID = scanner.nextInt();
                    //exption for incorrect input

                    classes.forEach((integer, classes1) -> System.out.println(integer + " " + classes1.getClassName()));
                    System.out.println("select a class to assign a teacher");
                    int classID = scanner.nextInt();
                    //exption for incorrect input
                    sql = "UPDATE users SET classID =" + classID + " WHERE userId = " + studentID + ";";
                    statement.executeUpdate(sql);
                    System.out.println(studentID + " is assigned to " + classID);
                } else if (scanner.equals("5")) {//                          //adding classes to dictionary
//                Map<Integer, classes> classes = new HashMap<>();

                    resultSet = null;
                    sql = "select *from classes";
                    resultSet = statement.executeQuery(sql);

                    System.out.println("classID\t\tClassName\t\tClassDescription");
                    while (resultSet.next()) {
                        System.out.print(resultSet.getInt(1) +
                                "\t\t" + resultSet.getString(2) +
                                "\t\t" + resultSet.getString(3) + "\n");
                        classes.put(resultSet.getInt(1), new classes(resultSet.getInt(1),
                                resultSet.getString(2), resultSet.getString(3)));

                    }

                    System.out.println("Thank you for using LMS. Goodbye!");
                    System.exit(0);
                } else if (scanner.equals("6")) {
                    System.out.println("Thank you for using LMS. Goodbye!");
                    System.exit(0);
                } else {
                    System.out.println();
                }


                statement.close();
                connection.close();

            }




//
//                System.out.println();
//                System.out.println("Here is the list of users for the system excluding yourself.");
//
//                ResultSet resultSet = null;
//                sql = "select *from userType where ID > 0";
//                resultSet = statement.executeQuery(sql);
//
//                System.out.println("ID\t\tusertype");
//                while (resultSet.next()) {
//
//                    System.out.print(resultSet.getInt(1) +
//                            "\t\t" + resultSet.getString(2) + "\n");
//
//                }
//
//                resultSet = null;
//                sql = "select *from users where usertype = 0";
//                resultSet = statement.executeQuery(sql);
//
//
//                while (resultSet.next()) {
////            if(email.equals(resultSet.getString("email"))){
////                System.out.print(resultSet.getInt(1)+
////                        "\t\t"+resultSet.getString(2)+
////                        "\t\t"+resultSet.getString(3)+"\n");
////            }
//
//                }
//
//                Map<Integer, users> users = new HashMap<>();
//
//                System.out.println("This is all users in the system.");
//                resultSet = null;
//                sql = "select *from users";
//                resultSet = statement.executeQuery(sql);
//
//                System.out.println("ID\t\tname\t\tsurname");
//                while (resultSet.next()) {
//
//                    System.out.print(resultSet.getInt(1) +
//                            "\t\t" + resultSet.getString(2) +
//                            "\t\t" + resultSet.getString(3) + "\n");
//                    users.put(resultSet.getInt(1), new users(resultSet.getString(2),
//                            resultSet.getString(3),
//                            resultSet.getString(4),
//                            resultSet.getInt(5),
//                            resultSet.getInt(6)));
//
//                }
//
//
//
//                //adding classes to dictionary
//                Map<Integer, classes> classes = new HashMap<>();
//
//                resultSet = null;
//                sql = "select *from classes";
//                resultSet = statement.executeQuery(sql);
//
//                System.out.println("classID\t\tClassName\t\tClassDescription");
//                while (resultSet.next()) {
//                    System.out.print(resultSet.getInt(1) +
//                            "\t\t" + resultSet.getString(2) +
//                            "\t\t" + resultSet.getString(3) + "\n");
//                    classes.put(resultSet.getInt(1), new classes(resultSet.getInt(1),
//                            resultSet.getString(2), resultSet.getString(3)));
//
//                }
//
//
//                for (var user : users.entrySet()
//                ) {
//                    if (user.getValue().getUserType() == 2 && user.getValue().getClassID()<1) {
//                        System.out.println("this is a list of unassigned students to classes.");
//                        List sortedKeys=new ArrayList(users.keySet());
//                        Collections.sort(sortedKeys);
//                        System.out.println(user.getKey()+" "+user.getValue().getFname());
//                    }
//
//                }
//                for (var user : users.entrySet()
//                ) {
//                    if (user.getValue().getUserType() == 3 && user.getValue().getClassID()<1) {
//                        System.out.println("this is a sorted list of unassigned teachers to classes.");
//
//                        List sortedKeys=new ArrayList(users.keySet());
//                        Collections.sort(sortedKeys);
//                        System.out.println(user.getKey()+" "+user.getValue().getFname());
//                    }
//
//                }
//                System.out.println("select a student you wanna assign");
//                int studentID = scanner.nextInt();
//                //exption for incorrect input
//
//                classes.forEach((integer, classes1) -> System.out.println(integer+" "+classes1.getClassName()));
//                System.out.println("select a class to assign student");
//                int classID = scanner.nextInt();
//                //exption for incorrect input
//                sql = "UPDATE users SET classID ="+classID+" WHERE userId = "+studentID+";";
//                statement.executeUpdate(sql);
//                System.out.println(studentID + " is assigned to " + classID);
//
//
//                //adding subjects to dictionary
//                Map<Integer, subjects> subject = new HashMap<>();
//
//                resultSet = null;
//                sql = "select *from subjects";
//                resultSet = statement.executeQuery(sql);
//
//                System.out.println("subjectID\t\tsubjectName\t\tsubjectDescription");
//                while (resultSet.next()) {
//                    System.out.print(resultSet.getInt(1) +
//                            "\t\t" + resultSet.getString(2) +
//                            "\t\t" + resultSet.getString(3) + "\n");
//                    subject.put(resultSet.getInt(1), new subjects(resultSet.getInt(1),
//                            resultSet.getString(2), resultSet.getString(3)));
//
//                }
//
//            }
//
////        int userType = scanner.nextInt();
////        System.out.println("please enter email");
////        String email = scanner.next();
////        System.out.println("please enter Fname");
////        String fname = scanner.next();
////        System.out.println("please enter lname");
////        String lname = scanner.next();
////        sql = "insert into users (Fname,Lname,email,userType) values('"+fname+",'"+lname+"','"+email+"','"+userType+"')";
////        statement.executeUpdate(sql);
////        System.out.println("success");
//
//
//
//
//


//            statement.close();
//            connection.close();
        }


    }
}
