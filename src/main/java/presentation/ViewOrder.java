package presentation;

import bll.OrderBLL;
import bll.ProductBLL;
import model.Order;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The type View order.
 */
public class ViewOrder {
    private JFrame frame = new JFrame("Place order");
    private JPanel pGeneral = new JPanel();

    private JPanel pBegin = new JPanel();
    private JLabel beginLabel = new JLabel("Add a new order:");

    private String columnsProduct[] = {"id", "clientID", "productID", "quantity", "price"};
    private JTable tabel;
    private JScrollPane scrollPane;
    private JPanel pTabel = new JPanel();

    private OrderBLL productBLL = new OrderBLL();

    private JPanel pAdd = new JPanel();
    private JLabel clientIDLabel = new JLabel("ClientID:");
    private JTextField clientIDTF = new JTextField(5);
    private JLabel productIDLabel = new JLabel("ProductID:");
    private JTextField productIDTF = new JTextField(5);
    private JLabel quantityLabel = new JLabel("Quantity:");
    private JTextField quantityTF = new JTextField(5);
    private JLabel priceLabel = new JLabel("Price:");
    private JLabel priceResLabel = new JLabel("0");
    private JButton butonAddOrder = new JButton("ADD");

    private JLabel errorLabel = new JLabel("Cantitate insuficienta in stoc!");
    private JPanel pError = new JPanel();

    /**
     * Instantiates a new View order.
     */
    public ViewOrder(){
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(200,200));
        frame.setLocationRelativeTo(null);

        pBegin.add(beginLabel);

        pAdd.setLayout(new GridLayout(0,7));
        pAdd.add(clientIDLabel);
        pAdd.add(clientIDTF);
        pAdd.add(productIDLabel);
        pAdd.add(productIDTF);
        pAdd.add(quantityLabel);
        pAdd.add(quantityTF);
        pAdd.add(butonAddOrder);

        tabel = this.populateTable();
        scrollPane = new JScrollPane(tabel);
        scrollPane.setPreferredSize(new Dimension(500, 250));
        pTabel.add(scrollPane);

        pGeneral.setLayout(new BoxLayout(pGeneral, BoxLayout.Y_AXIS));
        pGeneral.add(pBegin);
        pGeneral.add(pAdd);
        pGeneral.add(pTabel);

        pError.add(errorLabel);

        frame.setContentPane(pGeneral);
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * Populate table j table.
     *
     * @return the j table
     */
    protected JTable populateTable(){
        List<Order> list = productBLL.findAllOrders();
        String [][]data = new String[list.size()][5];
        int index = 0;
        for(Order o : list){
            data[index][0] = String.valueOf(o.getId());
            data[index][1] = String.valueOf(o.getClientID());
            data[index][2] = String.valueOf(o.getProductID());
            data[index][3] = String.valueOf(o.getQuantity());
            data[index][4] = String.valueOf(o.getTotalPrice());
            index++;
        }
        JTable table = new JTable(data,columnsProduct);
        return table;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    public JPanel getpError() {
        return pError;
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Gets general.
     *
     * @return the general
     */
    public JPanel getpGeneral() {
        return pGeneral;
    }

    /**
     * Gets tabel.
     *
     * @return the tabel
     */
    public JTable getTabel() {
        return tabel;
    }

    /**
     * Sets tabel.
     *
     * @param tabel the tabel
     */
    public void setTabel(JTable tabel) {
        this.tabel = tabel;
    }

    /**
     * Gets scroll pane.
     *
     * @return the scroll pane
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Sets scroll pane.
     *
     * @param scrollPane the scroll pane
     */
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    /**
     * Gets tabel.
     *
     * @return the tabel
     */
    public JPanel getpTabel() {
        return pTabel;
    }

    /**
     * Gets client idtf.
     *
     * @return the client idtf
     */
    public JTextField getClientIDTF() {
        return clientIDTF;
    }

    /**
     * Gets product idtf.
     *
     * @return the product idtf
     */
    public JTextField getProductIDTF() {
        return productIDTF;
    }

    /**
     * Gets quantity tf.
     *
     * @return the quantity tf
     */
    public JTextField getQuantityTF() {
        return quantityTF;
    }

    /**
     * Gets price res label.
     *
     * @return the price res label
     */
    public JLabel getPriceResLabel() {
        return priceResLabel;
    }

    /**
     * Gets buton add order.
     *
     * @return the buton add order
     */
    public JButton getButonAddOrder() {
        return butonAddOrder;
    }
}
