package app;

public class Admins {
    private String Username;
    private int Password;

    public Admins() {
    }

    public Admins(String username, int password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public int getPassword() {
        return Password;
    }

    @Override
    public String toString() {
        return "Admins{" +
                "Username='" + Username + '\'' +
                ", Password=" + Password +
                '}';
    }
}
