package app;

import java.sql.*;

public class connectSQL {
    Connection conn = null;
    // create a mysql database connection
    String dbName = "library";
    String dbHost = "127.0.0.1:3306";
    String myUrl = "jdbc:mysql://" + dbHost + "/" + dbName;
    String myUsername = "root";
    String myPassword = "12345678";

    public void startConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(myUrl, myUsername, myPassword);
//            System.out.println("Database connected!");

        } catch (Exception e) {
            System.err.println("CONNECTION: Got an exception!");
            System.err.println(e.getMessage());
        }
    }
        /*
    public void createTable(String table_name) {
        String queryToCreateMainTable = "CREATE TABLE IF NOT EXISTS " + table_name
                + "  (ins_id          INTEGER AUTO_INCREMENT," + "   course_code     VARCHAR(25),"
                + "   ins_day         VARCHAR(12)," + "   slot_number     INTEGER," + "   ins_type        VARCHAR(10),"
                + "   ins_place       VARCHAR(15)," + "   PRIMARY KEY (ins_id))";
        try {
            conn = DriverManager.getConnection(myUrl, myUsername, myPassword);
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmtToCreate = conn.prepareStatement(queryToCreateMainTable);
            preparedStmtToCreate.execute();
        } catch (SQLException e) {
            System.err.println("TABLE CREATION: Got an exception in table " + table_name + "!");
            System.err.println(e.getMessage());
        }
    }

    public void truncateTable(String table_name) {
        String queryToDelete = "TRUNCATE TABLE " + table_name;
        try {
            conn = DriverManager.getConnection(myUrl, myUsername, myPassword);
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmtToDelete = conn.prepareStatement(queryToDelete);
            preparedStmtToDelete.execute();
        } catch (SQLException e) {
            System.err.println("TRUNCATE: Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    */
        public void insertInstance(String table_name, int book_id, int member_id, String day_taken, String day_return) {
            String query = " insert into " + table_name + " (book_id, member_id, day_taken, day_return) values (?, ?, ?, ?)";

            try {
                this.conn = DriverManager.getConnection(this.myUrl, this.myUsername, this.myPassword);
                PreparedStatement preparedStmt = this.conn.prepareStatement(query);
                preparedStmt.setInt(1, book_id);
                preparedStmt.setInt(2, member_id);
                preparedStmt.setString(3, day_taken);
                preparedStmt.setString(4, day_return);
                preparedStmt.execute();
                this.conn.close();
            } catch (SQLException var9) {
                System.err.println("INSERTION: Got an exception!");
                System.err.println(var9.getMessage());
            }

        }
    public void insertMember(String table_name, int member_id, int password, String name, String email) {
        String query = " insert into " + table_name + " (member_id, password, name, email) values (?, ?, ?, ?)";

        try {
            this.conn = DriverManager.getConnection(this.myUrl, this.myUsername, this.myPassword);
            PreparedStatement preparedStmt = this.conn.prepareStatement(query);
            preparedStmt.setInt(1, member_id);
            preparedStmt.setInt(2, password);
            preparedStmt.setString(3, name);
            preparedStmt.setString(4, email);
            preparedStmt.execute();
            this.conn.close();
        } catch (SQLException var9) {
            System.err.println("INSERTION: Got an exception!");
            System.err.println(var9.getMessage());
        }

    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("CLOSING: Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}
