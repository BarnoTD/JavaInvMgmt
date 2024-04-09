package Authentication;

public class Token {
    private boolean isLoggedIn;
    private String username;
    private boolean isAdmin;

    public Token(String username, boolean isLoggedIn, boolean isAdmin){
        this.isAdmin = isAdmin;
        this.username = username;
        this.isLoggedIn = isLoggedIn;
    }
    public String getUsername() {
        return username;
    }
    public boolean getLogin() {
        return isLoggedIn;
    }
    public boolean getAdmin(){
        return isAdmin;
    }
}
