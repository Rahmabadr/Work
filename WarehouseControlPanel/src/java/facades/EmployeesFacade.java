/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entitys.Employees;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 
 */
@Stateless
public class EmployeesFacade extends AbstractFacade<Employees> {

    @PersistenceContext(unitName = "WarehouseControlPanelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeesFacade() {
        super(Employees.class);
    }
    
    public Employees findWhereOrder(String email, String password) { 
        List<Employees> employes = em.createNamedQuery("Employees.findByEmail", Employees.class)
                .setParameter("email", email).getResultList();
        if(employes.size() == 0) 
            return new Employees();
        if(employes.get(0).getPassword().equals(password)) {
            return employes.get(0);
        } else {
            return new Employees();
        }
    }
    
    public Employees findByName(String name){
        List<Employees> employees = em.createNamedQuery("Employees.findByFullName", Employees.class)
                .setParameter("fullName", name).setMaxResults(1).getResultList();
        return employees.get(0);
    }
}
