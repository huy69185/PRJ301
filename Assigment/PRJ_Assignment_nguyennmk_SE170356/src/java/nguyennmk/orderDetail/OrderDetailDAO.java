/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennmk.orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import nguyennmk.util.DBHelper;

/**
 *
 * @author nguyen
 */
public class OrderDetailDAO implements Serializable {

    public boolean createOrderDetail(String product_id, double unitprice, int quantity, double total, String order_id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL string
                String sql = "INSERT INTO [OrderDetail] (product_id, order_id, unitprice, quantity, total) VALUES (?, ?, ?, ?, ?)";
                //3. Create Statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, product_id);
                stm.setString(2, order_id);
                stm.setDouble(3, unitprice);
                stm.setInt(4, quantity);
                stm.setDouble(5, total);
                //4. Execute query
                int rowsEffect = stm.executeUpdate();
                //5. Process result
                if (rowsEffect > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
