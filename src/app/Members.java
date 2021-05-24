package app;

public class Members {
    private int Member_ID;
    private int Password;
    private String Name;
    private String EMail;

    public Members() {
    }

    public Members(int member_ID, int password, String name, String EMail) {
        Member_ID = member_ID;
        Password = password;
        Name = name;
        this.EMail = EMail;
    }

    public int getMember_ID() {
        return Member_ID;
    }

    public int getPassword() {
        return Password;
    }

    public String getName() {
        return Name;
    }

    public String getEMail() {
        return EMail;
    }

    @Override
    public String toString() {
        return "Members{" +
                "Member_ID=" + Member_ID +
                ", Password=" + Password +
                ", Name='" + Name + '\'' +
                ", EMail='" + EMail + '\'' +
                '}';
    }
}
