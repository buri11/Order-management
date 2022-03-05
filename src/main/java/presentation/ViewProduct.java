package presentation;

import bll.ProductBLL;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The type View product.
 */
public class ViewProduct {
    private JFrame frame = new JFrame("Product operations");
    private JPanel pGeneral = new JPanel();
    private JPanel pSelectOp = new JPanel();

    private JLabel selectOp = new JLabel("Select operation:");
    private JRadioButton addProduct = new JRadioButton("Add new product");
    private JRadioButton editProduct = new JRadioButton("Edit a product");
    private JRadioButton deleteProduct = new JRadioButton("Delete a product");
    private ButtonGroup radioProduct = new ButtonGroup();
    private JPanel bttnGroup = new JPanel();
    private String columnsProduct[] = {"id", "name", "quantity", "price"};
    private JTable tabel;
    private JScrollPane scrollPane;
    private JPanel pTabel = new JPanel();
    private ProductBLL productBLL = new ProductBLL();

    private JPanel pAddProduct = new JPanel();
    //add product panel
    private JLabel nameLabel = new JLabel("Name:");
    private JTextField nameTF = new JTextField(15);
    private JLabel quantityLabel = new JLabel("Quantity:");
    private JTextField quantityTF = new JTextField(15);
    private JLabel priceLabel = new JLabel("Price:");
    private JTextField priceTF = new JTextField(10);
    private JButton addProductButton = new JButton("ADD");

    private JPanel pEditProduct = new JPanel();
    //edit product panel
    private JLabel productToEditLabel = new JLabel("Type the product's id you want to edit");
    private JTextField productEditIdTF = new JTextField(5);
    private JLabel selectLabel = new JLabel("Select field to edit");
    private String []cbOptions = {"name", "quantity", "price"};
    private JComboBox cb = new JComboBox(cbOptions);
    private JLabel newValLabel = new JLabel("New value:");
    private JTextField newValTF = new JTextField(15);
    private JButton editProductButton = new JButton("EDIT");

    private JPanel pDeleteProduct = new JPanel();
    //delete product panel
    private JLabel productToDelLabel = new JLabel("Type the product's id you want to delete");
    private JTextField productDelIdTF = new JTextField(5);
    private JButton delProductButon = new JButton("DELETE");

    /**
     * Instantiates a new View product.
     */
    public ViewProduct(){
        frame.getContentPane().setLayout(new BorderLayout());
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(200,200));
        frame.setLocationRelativeTo(null);

        pSelectOp.setLayout(new GridLayout(0,1));
        selectOp.setHorizontalAlignment(SwingConstants.CENTER);
        pSelectOp.add(selectOp);

        radioProduct.add(addProduct);
        radioProduct.add(editProduct);
        radioProduct.add(deleteProduct);

        bttnGroup.setLayout(new GridLayout(0, 3));
        bttnGroup.add(addProduct);
        bttnGroup.add(editProduct);
        bttnGroup.add(deleteProduct);
        pSelectOp.add(bttnGroup);

        pGeneral.add(pSelectOp);

        tabel = this.populateJTable();
        scrollPane = new JScrollPane(tabel);
        scrollPane.setPreferredSize(new Dimension(500, 250));
        pTabel.add(scrollPane);
        pGeneral.add(pTabel);
        pGeneral.setLayout(new BoxLayout(pGeneral, BoxLayout.Y_AXIS));

        frame.setContentPane(pGeneral);
        frame.setVisible(true);
        frame.pack();

        //set-up add product panel
        pAddProduct.setLayout(new GridLayout(1,0));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddProduct.add(nameLabel);
        pAddProduct.add(nameTF);
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddProduct.add(quantityLabel);
        pAddProduct.add(quantityTF);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddProduct.add(priceLabel);
        pAddProduct.add(priceTF);
        pAddProduct.add(addProductButton);

        //set-up edit product panel
        pEditProduct.setLayout(new GridLayout(1,0));
        pEditProduct.add(productToEditLabel);
        pEditProduct.add(productEditIdTF);
        selectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pEditProduct.add(selectLabel);
        pEditProduct.add(cb);
        newValLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pEditProduct.add(newValLabel);
        pEditProduct.add(newValTF);
        pEditProduct.add(editProductButton);

        //set-up del product panel
        pDeleteProduct.setLayout(new GridLayout(1,0));
        productToDelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pDeleteProduct.add(productToDelLabel);
        pDeleteProduct.add(productDelIdTF);
        pDeleteProduct.add(delProductButon);
    }

    /**
     * Populate j table j table.
     *
     * @return the j table
     */
    protected JTable populateJTable(){
        List<Product> list = productBLL.findAllProducts();
        String [][]data = new String[list.size()][4];
        int index = 0;
        for(Product p : list){
            data[index][0] = String.valueOf(p.getId());
            data[index][1] = p.getName();
            data[index][2] = String.valueOf(p.getQuantity());
            data[index][3] = String.valueOf(p.getPrice());
            index++;
        }
        JTable table = new JTable(data,columnsProduct);
        return table;
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
     * Gets select op.
     *
     * @return the select op
     */
    public JPanel getpSelectOp() {
        return pSelectOp;
    }

    /**
     * Gets add product.
     *
     * @return the add product
     */
    public JRadioButton getAddProduct() {
        return addProduct;
    }

    /**
     * Gets edit product.
     *
     * @return the edit product
     */
    public JRadioButton getEditProduct() {
        return editProduct;
    }

    /**
     * Gets delete product.
     *
     * @return the delete product
     */
    public JRadioButton getDeleteProduct() {
        return deleteProduct;
    }

    /**
     * Gets bttn group.
     *
     * @return the bttn group
     */
    public JPanel getBttnGroup() {
        return bttnGroup;
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
     * Gets add product.
     *
     * @return the add product
     */
    public JPanel getpAddProduct() {
        return pAddProduct;
    }

    /**
     * Gets name tf.
     *
     * @return the name tf
     */
    public JTextField getNameTF() {
        return nameTF;
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
     * Gets price tf.
     *
     * @return the price tf
     */
    public JTextField getPriceTF() {
        return priceTF;
    }

    /**
     * Gets add product button.
     *
     * @return the add product button
     */
    public JButton getAddProductButton() {
        return addProductButton;
    }

    /**
     * Gets edit product.
     *
     * @return the edit product
     */
    public JPanel getpEditProduct() {
        return pEditProduct;
    }

    /**
     * Gets product edit id tf.
     *
     * @return the product edit id tf
     */
    public JTextField getProductEditIdTF() {
        return productEditIdTF;
    }

    /**
     * Gets cb.
     *
     * @return the cb
     */
    public JComboBox getCb() {
        return cb;
    }

    /**
     * Gets new val tf.
     *
     * @return the new val tf
     */
    public JTextField getNewValTF() {
        return newValTF;
    }

    /**
     * Gets edit product button.
     *
     * @return the edit product button
     */
    public JButton getEditProductButton() {
        return editProductButton;
    }

    /**
     * Gets delete product.
     *
     * @return the delete product
     */
    public JPanel getpDeleteProduct() {
        return pDeleteProduct;
    }

    /**
     * Gets product del id tf.
     *
     * @return the product del id tf
     */
    public JTextField getProductDelIdTF() {
        return productDelIdTF;
    }

    /**
     * Gets del product buton.
     *
     * @return the del product buton
     */
    public JButton getDelProductButon() {
        return delProductButon;
    }
}
