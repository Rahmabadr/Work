
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entitys.Employees;
import entitys.Tasks;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 
 */
@Stateless
public class TasksFacade extends AbstractFacade<Tasks> {

    @PersistenceContext(unitName = "WarehouseControlPanelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TasksFacade() {
        super(Tasks.class);
    }
    
    public Tasks findByEmployeer(Employees employer){
        List <Tasks> tasks = em.createNamedQuery("Tasks.findById", Tasks.class)
                .setParameter("email", employer.getId()).getResultList();
        return tasks.get(0);
    }
    
    public Tasks findEmployeeTask(int requestId){
        System.out.println("111111111111111 " + requestId);
        List<Tasks> tasks = em.createNamedQuery("Tasks.findByStatus", Tasks.class)
                .setParameter("status", "В очереди").getResultList();
        System.out.println("222222222222 " + tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getRequestProductId().getRequestId().getId() == requestId){
                return tasks.get(i);
            }
        }
        return tasks.size() > 0 ? tasks.get(0) : null;
    }
    
    public Tasks findEmployeeTaskJoin(Employees employee){
        List<Tasks> tasks = em.createNamedQuery("Tasks.findForEmployee", Tasks.class)
                .setParameter("status", "Выдан").getResultList();
        if(tasks != null ){
            for (int i = 0; i < tasks.size(); i++) {
                if(tasks.get(i).getRequestOfficerId().getEmployerId().equals(employee))
                    return tasks.get(i);
            }
        }
        return null;
    }
}
