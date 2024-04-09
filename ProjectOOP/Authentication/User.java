package Authentication;

import java.util.Objects;

public class User {
    protected String name;
    protected String password;
    protected boolean isAdmin = false;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    public boolean getIsAdmin(){
        return isAdmin;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        User otherUser = (User) obj;

        // Compare the name and password fields
        return Objects.equals(this.name, otherUser.name)
                && Objects.equals(this.password, otherUser.password);
    }

    @Override
    public int hashCode() {
        // Generate a hash code based on the name and password fields
        return Objects.hash(name, password);
    }

    @Override
    public String toString() {
        return (name + ", " + password + ", " + isAdmin) ;
    }
}