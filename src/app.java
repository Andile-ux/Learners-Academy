package src;

import java.sql.*;
import java.util.Scanner;

public class app
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {
        //Load the driver class
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/learners_accademy";
        String userName = "root";
        String password = "Gawoziwami@01";

        //create connection
        Connection connection = DriverManager.getConnection(url,userName,password);

        //Create query, statement, prepared statement, callable statement
        Statement statement = connection.createStatement();
//
        String sql;
//        sql = "create table test(sid int, sname varchar(50),degree varchar(20))";
//        statement.execute(sql);
//        System.out.println("success");
//        sql = "insert into test values(1,'jon','computer')";
//        statement.executeUpdate(sql);
//        System.out.println("success");

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("please enter id");
//        int id = scanner.nextInt();
//        System.out.println("please enter name");
//        String name = scanner.next();
//        System.out.println("please enter degree");
//        String degree = scanner.next();
//        sql = "insert into test values("+id+",'"+name+"','"+degree+"')";
//        statement.executeUpdate(sql);
//        System.out.println("success");

        ResultSet resultSet = null;
        sql = "select *from test";
        resultSet = statement.executeQuery(sql);

        System.out.println("ID\t\tname\t\tdegree");
        while (resultSet.next())
        {
            System.out.print(resultSet.getInt(1)+
            "\t\t"+resultSet.getString(2)+
            "\t\t"+resultSet.getString(3)+"\n");
        }

        statement.close();
        connection.close();

    }




}
