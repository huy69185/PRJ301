/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennmk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nguyennmk.registration.RegistrationDAO;
import nguyennmk.util.MyApplicationConstants;

/**
 *
 * @author nguyen
 */
public class DeleteAccountServlet extends HttpServlet {

//    private final String ERROR_PAGE = "errors.html";

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
        //1. get all parameters
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
//        String url = ERROR_PAGE;
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(
                MyApplicationConstants.DispatchFeature.ERROR_PAGE);
        try {
            //2. call Model
            //2.1 New DAO object
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 Call methods of DAO
            boolean result = dao.deleteAccount(username);
            //3. process result
            if (result) {
                //call the search features again using URL Rewriting technique
                //Tất cả mọi thứ phải được tham chiếu từ controller
                url = "DispatchServlet?btnAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }// end delete action is success
        } catch (SQLException ex) {
            log("DeleteAccountServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteAccountServlet_Naming: " + ex.getMessage());
        } finally {
            //forward issue because duplicate name
            response.sendRedirect(url);
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
