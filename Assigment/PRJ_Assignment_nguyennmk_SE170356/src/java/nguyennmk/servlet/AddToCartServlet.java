package nguyennmk.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nguyennmk.cart.CartObject;
import nguyennmk.product.ProductDAO;
import nguyennmk.product.ProductDTO;

public class AddToCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }

            // Retrieve ProductDTO from request parameter
            String skuParam = request.getParameter("sku");
            ProductDAO productDAO = new ProductDAO();
            ProductDTO product = productDAO.getProductById(skuParam);

            // Add ProductDTO to cart
            cart.addItemToCart(product);

            session.setAttribute("CART", cart);
        } catch (SQLException | NamingException ex) {
            log("AddToCartServlet Error: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("DispatchServlet?btnAction=GetProductList");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Add Product To Cart Servlet";
    }

    // Phương thức này để lấy thông tin sản phẩm từ sku
    private ProductDTO getProductBySku(String sku) throws SQLException, NamingException {
        // Gọi DAO để lấy thông tin sản phẩm từ sku
        ProductDAO dao = new ProductDAO();
        return dao.getProductById(sku);
    }
}
