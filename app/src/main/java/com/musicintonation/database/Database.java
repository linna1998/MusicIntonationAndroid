package com.musicintonation.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {


  public static void addRecord(String nickname, int score) {

  }

  public static List<ScoreRecord> readFromDatabase() {

    List<ScoreRecord> result = new ArrayList<>();

    Connection connection;
    String query = "Select ";
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:google:mysql://musicintonation", "root", "");


      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;

  }
}
