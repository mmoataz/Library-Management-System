package app;

public class Borrowed {
    private int Book_ID;
    private int Member_ID;
    private String Day_Taken;
    private String Day_Return;

    public Borrowed() {
    }

    public Borrowed(int book_ID, int member_ID, String day_Taken, String day_Return) {
        Book_ID = book_ID;
        Member_ID = member_ID;
        Day_Taken = day_Taken;
        Day_Return = day_Return;
    }

    public int getBook_ID() {
        return Book_ID;
    }

    public int getMember_ID() {
        return Member_ID;
    }

    public String getDay_Taken() {
        return Day_Taken;
    }

    public String getDay_Return() {
        return Day_Return;
    }

    @Override
    public String toString() {
        return "Borrowed{" +
                "Book_ID=" + Book_ID +
                ", Member_ID=" + Member_ID +
                ", Day_Taken='" + Day_Taken + '\'' +
                ", Day_Return='" + Day_Return + '\'' +
                '}';
    }
}
