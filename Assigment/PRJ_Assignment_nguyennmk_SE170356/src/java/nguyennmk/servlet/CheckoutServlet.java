 /* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package nguyennmk.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyennmk.cart.CartObject;
import nguyennmk.order.OrderDAO;
import nguyennmk.util.MyApplicationConstants;

/**
 *
 *
 *
 * @author nguyen
 *
 */


public class CheckoutServlet extends HttpServlet {

//    private final String GO_BACK_TO_PRODUCT_LIST = "DispatchServlet?btnAction=GetProductList";
    /**
     *
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     *
     * methods.
     *
     *
     *
     * @param request servlet request
     *
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     *
     * @throws IOException if an I/O error occurs
     *
     */
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
//    String url = GO_BACK_TO_PRODUCT_LIST;
    ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(
                MyApplicationConstants.ProductFeature.GO_BACK_TO_PRODUCT_LIST);
    HttpSession session = request.getSession(false);
    if (session != null) {
        CartObject cart = (CartObject) session.getAttribute("CART");
        if (cart != null) {
            try {
                OrderDAO orderDao = new OrderDAO();
                boolean result = orderDao.createOrder(cart);
            } catch (SQLException ex) {
                 log("CheckoutServlet_SQL: " + ex.getMessage()); 
            } catch (NamingException ex) {
                 log("CheckoutServlet_Naming: " + ex.getMessage()); 
            } finally {
                session.removeAttribute("CART");
                response.sendRedirect(url);
            }
        }
    }
}

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     *
     * Handles the HTTP <code>GET</code> method.
     *
     *
     *
     * @param request servlet request
     *
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     *
     * @throws IOException if an I/O error occurs
     *
     */
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    /**
     *
     * Handles the HTTP <code>POST</code> method.
     *
     *
     *
     * @param request servlet request
     *
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     *
     * @throws IOException if an I/O error occurs
     *
     */
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    /**
     *
     * Returns a short description of the servlet.
     *
     *
     *
     * @return a String containing servlet description
     *
     */
    @Override

    public String getServletInfo() {

        return "Short description";

    }// </editor-fold>

}
