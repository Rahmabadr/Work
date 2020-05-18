package servlets;

import entitys.Requests;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewRequestServlet extends HttpServlet {

    
    @EJB
    private facades.DepartmentsFacade departmentsFacade;
    
    @EJB
    private facades.EmployeesFacade employeesFacade;
    
    @EJB
    private facades.RequestsFacade requestsFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Requests> requests = requestsFacade.findAll();
        int id = requests.size() > 0 ? requests.get(requests.size() - 1).getId() + 1 : 1;
        Requests requestEntity = new Requests(
            id,
            request.getParameter("name"),
            "В очереди",
            request.getParameter("type"),
            fromString(request.getParameter("date")),
            fromString(request.getParameter("deadline")),
            departmentsFacade.findByName(request.getParameter("department")),
            employeesFacade.findByName(request.getParameter("officer"))
        );
        
        requestsFacade.create(requestEntity);
        
        requests = requestsFacade.findAll();
        id = requests.get(requests.size() - 1).getId();
        
        String json = "{\"requestid\": \"" + id + "\"}";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
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
