package app;

import com.mysql.cj.result.SqlDateValueFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Book[] books() throws SQLException {
        connectSQL estSQL = new connectSQL();
        estSQL.startConnection();
        Statement stmt = estSQL.conn.createStatement();
        String query = "SELECT * FROM library.books";
        ResultSet result = stmt.executeQuery(query);
        Book[] books = new Book[11];

        for(int i = 0; result.next(); ++i) {
            books[i] = new Book(Integer.parseInt(result.getString("ID")), result.getString("Subject"),result.getString("Title"), result.getString("Author"), result.getString("Publisher"),Integer.parseInt(result.getString("NOAB")),Integer.parseInt(result.getString("NOBB")),Integer.parseInt(result.getString("Available")) );
        }
        estSQL.closeConnection();
        return books;
    }

    private static Admins[] admins() throws SQLException {
        connectSQL estSQL = new connectSQL();
        estSQL.startConnection();
        Statement stmt = estSQL.conn.createStatement();
        String query = "SELECT * FROM library.admins";
        ResultSet result = stmt.executeQuery(query);
        Admins[] admins = new Admins[2];

        for(int i = 0; result.next(); ++i) {
            admins[i] = new Admins(result.getString("username"),Integer.parseInt(result.getString("password")));
        }
        estSQL.closeConnection();
        return admins;
    }

    private static Borrowed[] borrowed() throws SQLException {
        connectSQL estSQL = new connectSQL();
        estSQL.startConnection();
        Statement stmt = estSQL.conn.createStatement();
        String query = "SELECT * FROM library.borrowed";
        ResultSet result = stmt.executeQuery(query);
        Borrowed[] borrowed = new Borrowed[6];

        for(int i = 0; result.next(); ++i) {
            borrowed[i] = new Borrowed(Integer.parseInt(result.getString("book_id")),Integer.parseInt(result.getString("member_id")),result.getString("day_taken"),result.getString("day_return"));
        }
        estSQL.closeConnection();
        return borrowed;
    }
    private static Members[] members() throws SQLException {
        connectSQL estSQL = new connectSQL();
        estSQL.startConnection();
        Statement stmt = estSQL.conn.createStatement();
        String query = "SELECT * FROM library.members";
        ResultSet result = stmt.executeQuery(query);
        Members[] members = new Members[6];

        for(int i = 0; result.next(); ++i) {
            members[i] = new Members(Integer.parseInt(result.getString("member_id")),Integer.parseInt(result.getString("password")),result.getString("name"),result.getString("email"));
        }
        estSQL.closeConnection();
        return members;
    }

    public static boolean IsAdmin(Admins[] a, String user, int pass) throws SQLException
    {
        for (int i = 0; i < a.length; i++) {
            if (a[i].getUsername().equals(user) && a[i].getPassword() == pass) {
                return true;
            }

            }
        return false;
    }
    public static boolean IsMember(Members[] m, int id, int pass) throws SQLException
    {
        for (int i = 0; i < m.length; i++) {
            if (m[i].getMember_ID()==id && m[i].getPassword() == pass) {
                return true;
            }

        }
        return false;
    }
    public static void borrow_book(int ID, int member_id, String day, String return_day){
        connectSQL estSQL = new connectSQL();
        estSQL.startConnection();
        estSQL.insertInstance("borrowed",ID,member_id, day, return_day);
    }
    public static void add_member(int member_id, int password, String name, String email){
        connectSQL estSQL = new connectSQL();
        estSQL.startConnection();
        estSQL.insertMember("members",member_id,password, name, email);
    }
    public static void update(String table, String field, String operation, String key, int row ) throws SQLException{
        connectSQL estSQL = new connectSQL();
        estSQL.startConnection();
        Statement stmt = estSQL.conn.createStatement();
        String query = "update " +table +" set "+field + " = "+ operation + " where "+key +" in ("+Integer.toString(row)+ ")";
        stmt.executeUpdate(query);

    }
    public static void del(int ID, String table, String key) throws SQLException {
        connectSQL estSQL = new connectSQL();
        estSQL.startConnection();
        Statement stmt = estSQL.conn.createStatement();
        String query = "delete from " + table + " where "+key+" = " + Integer.toString(ID);
        stmt.executeUpdate(query);
    }
    public static void available_books() throws SQLException
    {
        Book[] books = books();
        for (int i=0; i<books.length; i++){
            if (books[i].getAvailable()==1){
                System.out.println(books[i].toString());
            }
        }
    }
    public static void main(String[] args) {
        try{
            Borrowed[] b = borrowed();
            Book[] test = books();
            String AName, login;
            int APass, m_id,MPass;
            Scanner in = new Scanner(System.in);
            System.out.println("Are you admin or member");
            login = in.next();
            if (login.equals("admin")){
                System.out.println("Please enter your username and your password");
                Admins[] ad = admins();
                AName = in.next();
                APass= in.nextInt();
                if (IsAdmin(ad,AName,APass))
                    System.out.println("Welcome Mr. Admin");
                else
                    System.out.println("Wrong user");
                int added_ID, added_PW;
                String added_name, added_email;
                System.out.println("Enter ID, PW, name, and email of the new added member");
                added_ID= in.nextInt();
                added_PW= in.nextInt();
                added_name= in.next();
                added_email= in.next();
                add_member(added_ID,added_PW, added_name,added_email);
                System.out.println("Enter the ID of the book you want to remove from the database: ");
                int del_ID= in .nextInt();
                del(del_ID,"books","ID");
                System.out.println("The book has been deleted.");
            }
            else {
                Members[] m = members();
                System.out.println("Please enter your ID and your password");
                m_id= in.nextInt();
                MPass= in.nextInt();
                if (IsMember(m,m_id,MPass))
                    System.out.println("Welcome to our library");
                else
                    System.out.println("Wrong user");
//                System.out.println("All the available books are: ");
//                available_books();
                System.out.println("Enter the ID of the book you want to borrow: ");
                int B_ID= in.nextInt();
                if (test[B_ID-1].getAvailable()==0)
                    System.out.println("The book is not available right now.");
                else{
                borrow_book(B_ID,m_id,"2020-07-01","2020-08-01");
                System.out.println("Process is done, you can take it right now.");
                    update("books", "NOBB", "NOBB+1", "ID", B_ID);
                test[0].setNo_of_borrowed_books(test[0].getNo_of_borrowed_books()+1);
                if (test[0].getNo_of_available_books()== test[0].getNo_of_borrowed_books()){
                    update("books", "Available", "0", "ID", B_ID);
                }
                System.out.println("Now, the available books are: ");
                available_books();
            }}
        }
        catch (SQLException  var21) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, (String)null, var21);
        }
        catch (NullPointerException var22) {
        }

    }
}
