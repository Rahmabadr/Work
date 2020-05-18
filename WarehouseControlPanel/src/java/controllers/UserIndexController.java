/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entitys.Tasks;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author 
 */
@Named(value = "userIndexController")
@Dependent
public class UserIndexController {
    
    @EJB
    private facades.AuthUser user;
    
    @EJB
    private facades.TasksFacade taskFacade;
    
    private Tasks thisTask;
    
    public UserIndexController() {
    }
    
    public String onTaskGood(){
        
        return "/userindex?faces-redirect=true";
    }

    public Tasks getThisTask() {
        thisTask = taskFacade.findByEmployeer(user.getCurrentEmployee());
        return thisTask;
    }

    public void setThisTask(Tasks thisTask) {
        this.thisTask = thisTask;
    }
    
    
}
