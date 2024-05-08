/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennmk.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import nguyennmk.cart.CartObject;
import nguyennmk.orderDetail.OrderDetailDAO;
import nguyennmk.product.ProductDTO;
import nguyennmk.util.DBHelper;

/**
 *
 * @author nguyen
 */
public class OrderDAO implements Serializable {

    private List<String> orderIdList;

    public boolean createOrder(CartObject cart) throws SQLException, NamingException {
    Connection con = null;
    PreparedStatement stm = null;
    boolean result = false;
    int numberOfLastId = 0;

    getOrderIdList();

    if (this.orderIdList.size() > 0) {
        numberOfLastId = Integer.parseInt(this.orderIdList.get(this.orderIdList.size() - 1).substring(2));
    }
    String newOrderId = "HD".concat(String.format("%03d", numberOfLastId + 1));

    try {
        //1. get connection
        con = DBHelper.getConnection();
        if (con != null) {
            if (cart != null) {
                Map<ProductDTO, Integer> items = cart.getItems();
                if (items != null) {
                    double total = 0;
                    OrderDetailDAO orderDetailDao = new OrderDetailDAO();
                    for (ProductDTO key : items.keySet()) {
                        double totalEachItem = items.get(key) * key.getUnitPrice();
                        total += totalEachItem;
                    }
                    //2. create SQL string
                    String sql = "insert into [Order](id, date, total) "
                            + "values (?,?,?)";
                    //3. Create Statement object
                    stm = con.prepareStatement(sql);
                    stm.setString(1, newOrderId);
                    stm.setDate(2, Date.valueOf(LocalDate.now()));
                    stm.setDouble(3, total);
                    //4. Execute query
                    int effectRows = stm.executeUpdate();
                    //5. Process result
                    if (effectRows > 0) {
                        for (ProductDTO key : items.keySet()) {
                            double totalEachItem = items.get(key) * key.getUnitPrice();
                            orderDetailDao.createOrderDetail(key.getSku(), key.getUnitPrice(), items.get(key), totalEachItem, newOrderId);
                            // Update product quantity
                            String updateProductSql = "UPDATE Product SET quantity = quantity - ? WHERE sku = ?";
                            stm = con.prepareStatement(updateProductSql);
                            stm.setInt(1, items.get(key));
                            stm.setString(2, key.getSku());
                            stm.executeUpdate();
                        }
                        result = true;
                    }
                }
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

    private void getOrderIdList() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        orderIdList = new ArrayList<>();
        try {
            //1. get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL string
                String sql = "select id from [Order]";
                //3. Create Statement object
                stm = con.prepareStatement(sql);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    this.orderIdList.add(rs.getString("id"));
                }
            }
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
}
