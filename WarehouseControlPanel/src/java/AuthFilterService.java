
import entitys.Employees;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class AuthFilterService {
    private Employees currentEmploier = null;
    private Employees lastEmploier = null;
    
    private AuthFilterService instanse = new AuthFilterService();
    
    public AuthFilterService getInstanse() {
        return instanse;
    }
    
    private AuthFilterService(){
       
    }
    
    public void setNewUser(Employees newEmploier){
        this.lastEmploier = this.currentEmploier;
        this.currentEmploier = newEmploier;
    }

    public Employees getCurrentEmploier() {
        return currentEmploier;
    }

    public Employees getLastEmploier() {
        return lastEmploier;
    }  
    
}
