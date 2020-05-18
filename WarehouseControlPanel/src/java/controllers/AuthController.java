/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entitys.Employees;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import controllers.util.JsfUtil;

/**
 *
 * @author 
 */
@Named(value = "authController")
@SessionScoped
public class AuthController implements Serializable {
    
    @EJB
    private facades.EmployeesFacade employeesFacade;
    
    @EJB
    private facades.AuthUser user;

    private String login = null;
    
    private String password = null;
            
//    private Employees currentEmployee;
    
    public AuthController() {
    }
    
    public String doLogin(){
        Employees employee = employeesFacade.findWhereOrder(login, password);
        if(employee.getFullName()!= null){
            user.setCurrentEmployee(employee);
            if(employee.getPostionId().getIsExecutivePosition().equals("Управление")){
                return "/adminindex?faces-redirect=true";
            } else if(employee.getPostionId().getIsExecutivePosition().equals("Менеджмент")){
                return "/managerindex?faces-redirect=true";
            } else {
                return "/userindex?faces-redirect=true";
            }
        } else {
            JsfUtil.addErrorMessage(new Exception("Wrong Login\\Password"), "Wrong Login\\Password");
            return null;
        }
    }    
    
    public String exit(){
        user.setCurrentEmployee(null);
        return "/faces/singin.xhtml?faces-redirect=true";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Employees getCurrentEmployee() {
//        return currentEmployee;
//    }
//
//    public void setCurrentEmployee(Employees currentEmployee) {
//        this.currentEmployee = currentEmployee;
//    }
    
    
    
}
