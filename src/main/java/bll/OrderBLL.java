package bll;

import dao.AbstractDAO;
import model.Order;

import java.util.List;

/**
 * The type Order bll.
 */
public class OrderBLL {
    private AbstractDAO abstractDAO;

    /**
     * Instantiates a new Order bll.
     */
    public OrderBLL(){
        abstractDAO = new AbstractDAO(Order.class);
    }

    /**
     * Insert order int.
     *
     * @param o the o
     * @return the int
     */
    public int insertOrder(Order o){
        o.writePDF();
        return abstractDAO.insert(o);
    }

    /**
     * Find all orders list.
     *
     * @return the list
     */
    public List<Order> findAllOrders(){
        return abstractDAO.findAll();
    }
}
