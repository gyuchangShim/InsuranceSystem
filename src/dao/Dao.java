package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.function.Supplier;

public class Dao {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    // 우리가 exception 만들면 더 좋음
    public void connect() throws Exception{
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance?useSSL=false", "root", "lunar2lim@");
        } catch (Exception e) {
            throw e;
        }
    }

    public void create(String query) {
        try {
            statement = connect.createStatement();
            if (!statement.execute(query)) {
                System.out.println("insert OK!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet retrieve(String query) {
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void update(String query) {
        try {
            statement = connect.createStatement();
            if (!statement.execute(query)) {
                System.out.println("update OK!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String query) {
        try {
            statement = connect.createStatement();
            if (!statement.execute(query)) {
                System.out.println("delete OK!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public <T> T enumNullCheck(String value, Supplier<T> supplier) {
        return value.contains("null") ? null : supplier.get();
    }
    public LocalDate dateNullCheck(Date value) {
        return value == null ? null : value.toLocalDate();
    }
}
