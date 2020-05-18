/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import org.omnifaces.filter.HttpFilter;
import org.omnifaces.util.Servlets;

/**
 *
 * @author 
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {
    "/faces/crud/*",
    "/faces/viewcrud/*",
    "/faces/singin.xhtml",
    "/faces/userindex.xhtml",
    "/faces/adminindex.xhtml",
    "/faces/managerindex.xhtml"
})
public class AuthFilter extends HttpFilter {
    
    @EJB
    private facades.AuthUser user;
    
    private String guestURL;
    private String userURL;
    private String adminURL;
    private String managerURL;


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response
            , FilterChain chain) throws IOException, ServletException {
        getAddresses(request.getContextPath());
        
        if(user.getCurrentEmployee() == null && !request.getRequestURI().equals(guestURL)){
            Servlets.facesRedirect(request, response, guestURL);
        }
        if(user.getCurrentEmployee() != null) { 
            if(user.getCurrentEmployee().getPostionId().getIsExecutivePosition().equals("Обслуживание") 
                    && !request.getRequestURI().equals(userURL)){
                Servlets.facesRedirect(request, response, userURL);
            }
//            if(user.getCurrentEmployee().getPostionId().getIsExecutivePosition().equals("Менеджмент") 
//                    && request.getRequestURI().equals(adminURL)){
//                Servlets.facesRedirect(request, response, managerURL);
//            }
//            if(user.getCurrentEmployee().getPostionId().getIsExecutivePosition().equals("Управление") 
//                    && request.getRequestURI().equals(managerURL)){
//                Servlets.facesRedirect(request, response, adminURL );
//            }
            if(user.getCurrentEmployee().getPostionId().getIsExecutivePosition().equals("Менеджмент") 
                    && isAdminPath(request.getRequestURI())){
                Servlets.facesRedirect(request, response, managerURL );
            }
            if(user.getCurrentEmployee().getPostionId().getIsExecutivePosition().equals("Управление") 
                    && !isAdminPath(request.getRequestURI())){
                Servlets.facesRedirect(request, response, adminURL );
            }
        }
        if(user.getCurrentEmployee() != null && request.getRequestURI().equals(guestURL)){
            String redirectPath = user.getCurrentEmployee().getPostionId().getIsExecutivePosition().equals("Управление")
                    ? adminURL : user.getCurrentEmployee().getPostionId().getIsExecutivePosition().equals("Менеджмент") 
                    ? managerURL : userURL;
            Servlets.facesRedirect(request, response, redirectPath);
        } 
        chain.doFilter(request, response); 
    }
    
    private boolean isAdminPath(String path){
        if(path.equals(adminURL)) return true;
        if(path.equals(managerURL)) return false;
        if(path.split("faces/")[1].substring(0, 4).equals("crud")){
            return true;
        } else {
            return false;
        }
    }
            
    private void getAddresses(String basePath) {
        guestURL = basePath + "/faces/singin.xhtml";
        userURL = basePath + "/faces/userindex.xhtml";
        adminURL = basePath + "/faces/adminindex.xhtml";   
        managerURL = basePath + "/faces/managerindex.xhtml";
    }

}
