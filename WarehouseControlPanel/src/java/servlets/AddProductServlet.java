package servlets;


import entitys.Employees;
import entitys.Products;
import entitys.RequestOfficers;
import entitys.RequestProducts;
import entitys.Requests;
import entitys.Tasks;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductServlet extends HttpServlet {

    @EJB
    private facades.RequestsFacade requestsFacade;
    
    @EJB
    private facades.RequestProductsFacade requestProductsFacade;
    
    @EJB
    private facades.ProductsFacade productsFacade;
    
    @EJB
    private facades.RequestOfficersFacade requestOfficersFacade;
    
    @EJB
    private facades.EmployeesFacade employeesFacade;
    
    @EJB
    private facades.TasksFacade tasksFacade;
    
    @EJB
    private facades.CellsFacade cellsFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int requestId = Integer.valueOf(request.getParameter("requestID"));
        
        List<RequestProducts> requestProducts = requestProductsFacade.findAll();
        int id = requestProducts.size() > 0 ? requestProducts.get(requestProducts.size() - 1).getId() + 1 : 1;
        
        Products product = productsFacade.findByName(request.getParameter("product"));
        int quantity = product.getQuantity() > Integer.valueOf(request.getParameter("quantity")) ?
                Integer.valueOf(request.getParameter("quantity")) : product.getQuantity();
        
        
//        System.out.println("HELLLLLLLLO " + requestId + " RRR " + id + " " + quantity);
        
        RequestProducts requestProduct = new RequestProducts(
            id,
            quantity,
            product,
            requestsFacade.find(requestId)
        );

        
        requestProductsFacade.create(requestProduct);
        addTask(requestProduct, Integer.valueOf(request.getParameter("from")), Integer.valueOf(request.getParameter("to")));
        String json = "{\"requestid\": \"" + requestId + "\"}";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    private void addTask(RequestProducts requestProduct, int from, int to){
        List<Employees> employees = employeesFacade.findAll();
        Employees currentEmployee = null;
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getPostionId().getIsExecutivePosition().equals("Обслуживание")){
                if(!isWork(employees.get(i))){
                    currentEmployee = employees.get(i);
                    break;
                }
            }
        }
        List<Tasks> tasks = tasksFacade.findAll();
        int id = tasks.size() > 0 ? tasks.get(tasks.size() - 1).getId() + 1 : 1;
        if(currentEmployee != null){
            List<RequestOfficers> requestOfficers = requestOfficersFacade.findAll();
            int taskID = requestOfficers.size() > 0 ? requestOfficers.get(requestOfficers.size() - 1).getId() + 1 : 1;

            RequestOfficers requestOfficer = new RequestOfficers(
                taskID,  
                currentEmployee,
                requestProduct.getRequestId()
            );
            requestOfficersFacade.create(requestOfficer);
//            try{
//                sleep(1000);
//            } catch(Exception e){}
            Tasks task = new Tasks(
                id,
                "Выдан",
                cellsFacade.find(from),
                cellsFacade.find(to),
                requestOfficer,
                requestProduct
            );
            tasksFacade.create(task);
            List<Tasks> taskList = requestOfficer.getTasksList();
            taskList.add(task);
            requestOfficer.setTasksList(taskList);
            requestOfficersFacade.edit(requestOfficer);
            
            List<RequestOfficers> requestList = currentEmployee.getRequestOfficersList();
            requestList.add(requestOfficer);
            currentEmployee.setRequestOfficersList(requestList);
            employeesFacade.edit(currentEmployee);    
            
//            List<RequestProducts> requestProductList = currentEmployee.getRequestOfficersList();
//            requestList.add(requestOfficer);
//            currentEmployee.setRequestOfficersList(requestList);
//            employeesFacade.edit(currentEmployee);
            
            Requests request = requestOfficer.getRequestId();
            request.setStatus("В процессе");
            request.setRequestOfficersList(requestList);
//            request.setRequestProductsList(requestProductsList);
            requestsFacade.edit(request);
            
        } else {
            Tasks task = new Tasks(
                id,
                "В очереди",
                cellsFacade.find(from),
                cellsFacade.find(to),
                null,
                requestProduct
            );
            tasksFacade.create(task);
        }
    }
    
    private boolean isWork(Employees employee){
        List<RequestOfficers> requests = employee.getRequestOfficersList();
                System.out.println("!!!!!!!!!WWWW11 " + requests.size());
        for (int i = 0; i < requests.size(); i++) {
            List<Tasks> tasks = requests.get(i).getTasksList();
            
            for (int j = 0; j < tasks.size(); j++) {
                Tasks current = tasksFacade.find(tasks.get(j).getId());
                System.out.println("!!!!!!!!!WWWW " + current.getStatus());
                if(current.getStatus().equals("Выдан")){
                    System.out.println(employee.getFullName() + "true");
                    return true;
                }
            }
        }
                    System.out.println(employee.getFullName() + "false");
        return false;
    }
    
    private Date fromString(String text){
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = format.parse(text);
            return date;
        } catch(Exception ex){
            return null;
        }
    }
    
}
