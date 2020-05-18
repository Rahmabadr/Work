package controllers;

import entitys.Tasks;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import entitys.Cells;
import entitys.Departments;
import entitys.Employees;
import entitys.Products;
import entitys.CellProduct;
import facades.TasksFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("managedController")
@SessionScoped
public class ManagedController implements Serializable {

    private String[] types = {"Перемещение", "Отгрузка", "Поставка"};
    
    private String type;
    private String date;
    private String officerName;
    private String from;
    private String to;
    private String quantity;
    private String product;
    private String fromdep;
    
    @EJB
    private facades.EmployeesFacade ejbFacade;
    
    @EJB
    private facades.CellsFacade cellsFacade;
    
    @EJB
    private facades.ProductsFacade productsFacade;
    
    @EJB
    private facades.DepartmentsFacade departmentsFacade;
    
    public ManagedController() {
        
    }  
    
    public Object[] requestOfficers(){
        List<Employees> employees = ejbFacade.findAll();
        List<String> managers = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getPostionId().getIsExecutivePosition().equals("Менеджмент")) {
                managers.add(employees.get(i).getFullName());
            }
        }        
        return managers.toArray();
    }
    
    public Object[] cells(){
        List<Cells> cells = cellsFacade.findAll();
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            if(i != 0){
                numbers.add(cells.get(i).getId() + "");
            }
        }    
        numbers.add(cells.get(0).getId() + "");
        return numbers.toArray();
    } 
    
    public Object[] cellsFrom(){
        List<Cells> cells = cellsFacade.findAll();
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            if(cells.get(i).getCellProductList().size() > 0){
                numbers.add(cells.get(i).getId() + "");
            }
        }        
        return numbers.toArray();
    }
    
    public Object[] products(){
        List<CellProduct> cellProducts = cellsFacade.findAll().get(0).getCellProductList();
        List<Products> products = new ArrayList<>();//productsFacade.findAll();
        for (int i = 0; i < cellProducts.size(); i++) {
            products.add(cellProducts.get(i).getProductId());
        }
        List<String> productNames = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productNames.add(products.get(i).getName()+ "");
        }        
        return productNames.toArray();
    }
    
            
    public Object[] fromdeps(){
        List<Departments> departments = departmentsFacade.findAll();
        List<String> productNames = new ArrayList<>();
        for (int i = 0; i < departments.size(); i++) {
            productNames.add(departments.get(i).getName());
        }        
        return productNames.toArray();
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String getFromdep() {
        return fromdep;
    }

    public void setFromdep(String fromdep) {
        this.fromdep = fromdep;
    }
    
    
    
}
