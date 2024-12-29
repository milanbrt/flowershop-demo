package domain;

public class Customer {
    private final String login;
    private final String password;

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}
