package controllers;

import entitys.Employees;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

@Named(value = "adminInfoController")
@SessionScoped
public class AdminInfoController implements Serializable {

    
    @EJB
    private facades.EmployeesFacade employeesFacade;
    
    @EJB
    private facades.AuthUser user;
    
    private Employees currentUser;
    
    public AdminInfoController() {
        
    }
    
    private String update() {
        user.setCurrentEmployee(currentUser);
        employeesFacade.edit(currentUser);
        return null;
    }

    public Employees getCurrentUser() {
        currentUser = user.getCurrentEmployee();
        return currentUser;
    }

    public void setCurrentUser(Employees currentUser) {
        this.currentUser = currentUser;
    }
    
        
}
