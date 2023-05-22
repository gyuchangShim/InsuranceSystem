package Dao;

import java.sql.*;

public class Dao {
    // query는 여기서만 해결
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void connect() throws Exception {
        try {
            Class.forName("");
            connect = DriverManager.getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(String query) {
        try {
            statement = connect.createStatement();
            if(!statement.execute(query))
                System.out.println("insert OK!!");
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet retrieve(String query) {
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void update(String query) {
        try {
            statement = connect.createStatement();
            if(!statement.execute(query))
                System.out.println("update OK!!");
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(String query) {
        try {
            statement = connect.createStatement();
            if(!statement.execute(query))
                System.out.println("delete OK!!");
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
