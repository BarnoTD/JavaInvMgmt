package Authentication;

public class NormalUser extends User{
    
    public NormalUser(String name, String password, boolean isAdmin ) {
        
        super(name, password);
        this.isAdmin = false;

        
    }
}