/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entitys.CellProduct;
import entitys.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 
 */
public class ProductsGetterServlet extends HttpServlet {
 
    @EJB
    private facades.DepartmentsFacade departmentsFacade;
    
    @EJB
    private facades.CellsFacade cellsFacade;
    
    @EJB
    private facades.ProductsFacade productsFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int cellID = Integer.valueOf(request.getParameter("cellid"));
        List<Products> products = null;
        if(cellID == 0){
            products = productsFacade.findAll();
        } else {
            List<CellProduct> cellProducts = cellsFacade.find(cellID).getCellProductList();
            products = new ArrayList<>();//productsFacade.findAll();
            for (int i = 0; i < cellProducts.size(); i++) {
                products.add(cellProducts.get(i).getProductId());
            }
        }
        List<String> productNames = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productNames.add(products.get(i).getName()+ "");
        }        
        
        
        String json = "{\"requestid\": [" + toJson(productNames.toArray()) + "]}";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    private String toJson(Object[] array){
        String result = "";
        for (int i = 0; i < array.length; i++) {
            result += array[i];
            if(i < array.length-1){
                result += ", ";
            }
        }
        return result;
    }
    
}
