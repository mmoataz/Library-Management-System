package app;

public class Book {
    private int ID;
    private String Subject;
    private String Title;
    private String Author;
    private String Publisher;
    private int No_of_available_books;
    private int No_of_borrowed_books;
    private int available;

    public Book() {
    }

    public Book(int ID, String subject, String title, String author, String publisher, int no_of_available_books, int no_of_borrowed_books, int available) {
        this.ID = ID;
        Subject = subject;
        Title = title;
        Author = author;
        Publisher = publisher;
        No_of_available_books = no_of_available_books;
        No_of_borrowed_books = no_of_borrowed_books;
        this.available = available;
    }

    public int getID() {
        return ID;
    }

    public String getSubject() {
        return Subject;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public int getNo_of_available_books() {
        return No_of_available_books;
    }

    public int getNo_of_borrowed_books() {
        return No_of_borrowed_books;
    }

    public int getAvailable() {
        return available;
    }

    public void setNo_of_available_books(int no_of_available_books) {
        No_of_available_books = no_of_available_books;
    }

    public void setNo_of_borrowed_books(int no_of_borrowed_books) {
        No_of_borrowed_books = no_of_borrowed_books;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", Subject='" + Subject + '\'' +
                ", Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", No_of_available_books=" + No_of_available_books +
                ", No_of_borrowed_books=" + No_of_borrowed_books +
                ", available=" + available +
                '}';
    }
}
