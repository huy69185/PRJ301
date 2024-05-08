/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennmk.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nguyennmk.util.DBHelper;

/**
 *
 * @author nguyen
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> list;

    public List<ProductDTO> getProductList() {
        return this.list;
    }

    public void getAllProductList() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. get connection
            con = DBHelper.getConnection();
            //2. create SQL string
            if (con != null) {
                String sql = "select sku, name, description, unitPrice, quantity, status "
                        + "from Product "
                        + "where status = 1";
                //3. Create Statement object
                stm = con.prepareStatement(sql);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double unitPrice = rs.getDouble("unitPrice");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    ProductDTO dto = new ProductDTO(sku, name, description, unitPrice, quantity, status);
                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
                }//end product has existed
            }// end connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public ProductDTO getProductById(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. get connection
            con = DBHelper.getConnection();
            //2. create SQL string
            if (con != null) {
                String sql = "select sku,name,description,unitPrice,quantity,status "
                        + "from Product "
                        + "where sku=?";
                //3. Create Statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double unitPrice = rs.getDouble("unitPrice");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    ProductDTO productDto = new ProductDTO(sku, name, description, unitPrice, quantity, status);
                    return productDto;
                }
            }// end connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
}
