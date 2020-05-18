package facades;

import entitys.Departments;
import entitys.Employees;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DepartmentsFacade extends AbstractFacade<Departments> {

    @PersistenceContext(unitName = "WarehouseControlPanelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartmentsFacade() {
        super(Departments.class);
    }
    
    public Departments findByName(String name){
        List<Departments> departments = em.createNamedQuery("Departments.findByName", Departments.class)
                .setParameter("name", name).setMaxResults(1).getResultList();
        return departments.get(0);
    }
    
}
