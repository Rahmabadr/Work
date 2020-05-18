package controllers;

import entitys.Employees;
import entitys.RequestOfficers;
import entitys.Tasks;
import facades.TasksFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;


@Named(value = "employeeTaskController")
@SessionScoped
public class EmployeeTaskController implements Serializable {
    
    @EJB
    private facades.TasksFacade tasksFacade;
    
    @EJB
    private facades.RequestOfficersFacade requestOfficersFacade;
    
    @EJB
    private facades.AuthUser user;
    
    private Tasks currentTask;
    
    private Employees currentUser;
    
    private boolean isHaveTask;
    
    public EmployeeTaskController() {
    }
    
    public String onTaskComplete(){
        currentTask.setStatus("Выполнен");
        tasksFacade.edit(currentTask);
        
        currentTask = tasksFacade.findEmployeeTask(currentTask.getRequestProductId().getRequestId().getId());
        List<RequestOfficers> requestOfficers = requestOfficersFacade.findAll();
        int id = requestOfficers.size() > 0 ? requestOfficers.get(requestOfficers.size() - 1).getId() + 1 : 1;
//        System.out.println("************ " + currentTask.getId());
        if(currentTask != null){
            RequestOfficers officer = new RequestOfficers(
                id,
                user.getCurrentEmployee(),
                currentTask.getRequestProductId().getRequestId()
            );
            requestOfficersFacade.create(officer);

            currentTask.setStatus("Выдан");
            currentTask.setRequestOfficerId(officer);
            tasksFacade.edit(currentTask);
        }
        return "/userindex?faces-redirect=true";
    }

    public Tasks getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Tasks currentTask) {
        this.currentTask = currentTask;
    }

    public boolean isIsHaveTask() {
        currentUser = user.getCurrentEmployee();
        currentTask = tasksFacade.findEmployeeTaskJoin(currentUser);
        isHaveTask = currentTask == null;
        return !isHaveTask;
    }
    
    
}
