package Authentication;

public class Admin extends User{
    
    public Admin(String name, String password, boolean isAdmin ) {
        
        super(name, password);
        this.isAdmin = true;

        
    }
}