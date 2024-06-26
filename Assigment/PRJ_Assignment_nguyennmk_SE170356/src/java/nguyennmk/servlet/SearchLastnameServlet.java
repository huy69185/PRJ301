/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennmk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nguyennmk.registration.RegistrationDAO;
import nguyennmk.registration.RegistrationDTO;
import nguyennmk.util.MyApplicationConstants;

/**
 *
 * @author nguyen
 */
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {
//    private final String RESULT_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. get all client information
        String searchValue = request.getParameter("txtSearchValue");
        
//        String url = RESULT_PAGE;
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(
                MyApplicationConstants.LoginFeatures.SEARCH_PAGE);
        try  {
            if (!searchValue.trim().isEmpty()){
                //2. call Model
                //2.1 new DAO
                RegistrationDAO dao = new RegistrationDAO();
                //2.2 call method of DAO
                dao.searchLastname(searchValue);
                List<RegistrationDTO> result = dao.getAccounts();
                //3.process result
//                url = RESULT_PAGE;
                url = siteMaps.getProperty(
                        MyApplicationConstants.LoginFeatures.SEARCH_PAGE);
                request.setAttribute("SEARCH_RESULT", result);
            }// end typed valid value 
        } 
        catch (SQLException ex) {
            log("SearchLastnameServlet_SQL: " + ex.getMessage()); 
        } catch (ClassNotFoundException ex) {
            log("SearchLastnameServlet_ClassNotFound: " + ex.getMessage()); 
        } catch (NamingException ex) {
            log("SearchLastnameServlet_Naming: " + ex.getMessage()); 
        } finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
