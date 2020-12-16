package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Data extends  Config {
    private Scanner scanner = new Scanner(System.in);
    private Connection connection = null;
    private List<String> messages = new ArrayList<>();
    private StringBuilder string = new StringBuilder();
    public Data() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String connect(String login) {
        String result = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            PreparedStatement select =  connection.prepareStatement("SELECT pass FROM users WHERE login=? ");
            select.setString(1,login);
            ResultSet list = select.executeQuery();
            while (list.next()){
                result = list.getString(1);

                //  System.out.println(list.getInt(1) + "  " + list.getString(2) + "  " + list.getString(3));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;

    }

    public boolean exists(String login){
        try {
            PreparedStatement select = connection.prepareStatement("SELECT COUNT(login) FROM users WHERE users.login = ?");
            select.setString(1, login);
            ResultSet set = select.executeQuery();
            while (set.next()) {
                if (Integer.parseInt(set.getString(1)) == 0){
                    return true;
                }else {
                    System.out.println("Bad");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(String login, String pass, String repass){
        if (pass.equals(repass)){
            try {
                PreparedStatement insert  = connection.prepareStatement("INSERT INTO users (login, pass)" + "VALUES(?,?)");
                insert.setString(1,login);
                insert.setString(2,pass);
                insert.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }else{
            System.out.println("pass != re-pass");
            return false;
        }
    }

    public boolean auth(String username, String pass) {
        try {
            PreparedStatement select = connection.prepareStatement("SELECT pass FROM users WHERE login=?");
            select.setString(1, username);
            ResultSet set = select.executeQuery();
            while (set.next()){
                if (set.getString(1).equals(pass)){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public int getId(String login){
        int myid = 0;
        try{
            PreparedStatement select = connection.prepareStatement(
                    "SELECT id FROM users WHERE login = ?");
            select.setString(1, login);
            ResultSet set = select.executeQuery();
            while (set.next()){
                myid = set.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return myid;
    }

    public double getBalance(String login){
        double balance = 0;
        try{
            PreparedStatement select = connection.prepareStatement(
                    "SELECT balance FROM users WHERE login = ?");
            select.setString(1, login);
            ResultSet set = select.executeQuery();
            while (set.next()){
                balance = set.getDouble(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return balance;
    }

    public boolean getUserName(String username) throws SQLException {
        PreparedStatement select = connection.prepareStatement("SELECT users FROM users WHERE users.login = ?");
        select.setString(1, username);
        ResultSet set = select.executeQuery();
        return true;
    }


    public void insertNewMassage (String name, String message){
        try {
            PreparedStatement insert  = connection.prepareStatement("INSERT INTO messages (username , message)" + "VALUES(?,?)");
            insert.setString(1,name);
            insert.setString(2,message);
            insert.executeUpdate();
            System.out.println("Success Insert");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public StringBuilder firstRaws(){
        try {
            PreparedStatement select = connection.prepareStatement("SELECT * FROM messages ORDER BY id DESC LIMIT 15");
            ResultSet list = select.executeQuery();
            while (list.next()) {
                messages.add("[" + list.getString(2) + "]: " + list.getString(3) + "\n");
            }
            for (int i = messages.size() - 1; i >= 0; i--) {
                string.append(messages.get(i));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return string;
    }
    public void transfer(int myid, int id, double mybalance, double balance) {
        try {
            PreparedStatement first = connection.prepareStatement("UPDATE users SET balance=? WHERE id=?");
            PreparedStatement second = connection.prepareStatement("UPDATE users SET balance=? WHERE id=?");
            first.setString(1, String.valueOf(mybalance - balance));
            first.setInt(2, myid);
            second.setString(1, String.valueOf(getBalanceById(id) + (mybalance - (mybalance - balance))));
            second.setInt(2, id);
            first.executeUpdate();
            second.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public double getBalanceById(int id){
        double balance = 0;
        try{
            PreparedStatement select = connection.prepareStatement(
                    "SELECT balance FROM users WHERE id = ?");
            select.setString(1, Integer.toString(id));
            ResultSet set = select.executeQuery();
            while (set.next()){
                balance = set.getDouble(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return balance;
    }
}