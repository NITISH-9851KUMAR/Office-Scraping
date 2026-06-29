package com.example.Scraping;


import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) {

        //final String url= "jdbc:sqlserver://localhost:1433;databaseName=TempDB;encrypt=false;TrustServerCertificate=true;";
        String url = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=tempdb;"
                + "user=sa;"
                + "password=123456;"
                + "encrypt=false;";
        try{
            Connection connection= DriverManager.getConnection(url);
            if(connection!=null){
                System.out.println("Connected Successfully!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
