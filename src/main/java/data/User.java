package data;

public class User extends Login {
    private String name;

    public User(String name, String email, String password) {
        super(email, password);
        this.name = name;
    }

    public User() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}