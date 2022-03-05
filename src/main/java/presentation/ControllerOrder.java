package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Controller order.
 */
public class ControllerOrder {
    private ViewOrder view;
    private OrderBLL orderBLL;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;

    /**
     * Instantiates a new Controller order.
     *
     * @param v the v
     */
    public ControllerOrder(ViewOrder v){
        view = v;
        orderBLL = new OrderBLL();
        productBLL = new ProductBLL();
        clientBLL = new ClientBLL();

        view.getButonAddOrder().addActionListener(new AddOrderListener());
    }

    private class AddOrderListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel pGeneral = view.getpGeneral();
            pGeneral.remove(view.getpError());

            int clientID = Integer.parseInt(view.getClientIDTF().getText());
            int productID = Integer.parseInt(view.getProductIDTF().getText());
            int quantity = Integer.parseInt(view.getQuantityTF().getText());
            double price;

            Product p = productBLL.findProductById(productID);

            price = p.getPrice() * ((double)quantity);

            if(quantity <= p.getQuantity()){
                Order o = new Order(clientID, productID, quantity, price);
                orderBLL.insertOrder(o);
                productBLL.updateProduct(p, "quantity", p.getQuantity()-quantity);
            }
            else{
                pGeneral.add(view.getpError(), 2);
            }
            JScrollPane scrollPane;
            //update view
            view.getpTabel().remove(view.getScrollPane());
            view.setTabel(view.populateTable());
            scrollPane = new JScrollPane(view.getTabel());
            scrollPane.setPreferredSize(new Dimension(500, 250));
            view.setScrollPane(scrollPane);
            view.getpTabel().add(view.getScrollPane());
            view.getFrame().pack();
            view.getFrame().setLocationRelativeTo(null);
        }
    }
}
