package facades;

import entitys.Employees;
import javax.ejb.Singleton;

@Singleton
public class AuthUser {

    private Employees currentEmployee;

    public Employees getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employees currentEmployee) {
        this.currentEmployee = currentEmployee;
    }
    
}
