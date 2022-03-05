package presentation;

import bll.ProductBLL;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Controller product.
 */
public class ControllerProduct {
    private ViewProduct view;
    private ProductBLL productBLL;

    /**
     * Instantiates a new Controller product.
     *
     * @param v the v
     */
    public ControllerProduct(ViewProduct v){
        view = v;
        productBLL = new ProductBLL();

        view.getAddProduct().addActionListener(new ControllerProduct.AddProductListener());
        view.getEditProduct().addActionListener(new ControllerProduct.EditProductListener());
        view.getDeleteProduct().addActionListener(new ControllerProduct.DeleteProductListener());

        view.getAddProductButton().addActionListener(new ControllerProduct.AddProductToDBListener());
        view.getEditProductButton().addActionListener(new ControllerProduct.EditProductDBListener());
        view.getDelProductButon().addActionListener(new ControllerProduct.DelProductDBListener());
    }

    private class DelProductDBListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int ProductID = Integer.parseInt(view.getProductDelIdTF().getText());

            Product p = productBLL.findProductById(ProductID);
            productBLL.deleteProduct(p);

            JScrollPane scrollPane;
            //update view
            view.getpTabel().remove(view.getScrollPane());
            view.setTabel(view.populateJTable());
            scrollPane = new JScrollPane(view.getTabel());
            scrollPane.setPreferredSize(new Dimension(500, 250));
            view.setScrollPane(scrollPane);
            view.getpTabel().add(view.getScrollPane());
            view.getFrame().pack();
            view.getFrame().setLocationRelativeTo(null);
        }
    }

    private class EditProductDBListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int ProductID = Integer.parseInt(view.getProductEditIdTF().getText());
            Product c = productBLL.findProductById(ProductID);

            String fieldToUpdate = (String)view.getCb().getSelectedItem();
            Object newVal = view.getNewValTF().getText();
            if(fieldToUpdate.equals("age")){
                newVal = Integer.parseInt((String)newVal);
            }
            if(fieldToUpdate.equals("price")){
                newVal = Double.parseDouble((String)newVal);
            }

            productBLL.updateProduct(c, fieldToUpdate, newVal);

            JScrollPane scrollPane;
            //update view
            view.getpTabel().remove(view.getScrollPane());
            view.setTabel(view.populateJTable());
            scrollPane = new JScrollPane(view.getTabel());
            scrollPane.setPreferredSize(new Dimension(500, 250));
            view.setScrollPane(scrollPane);
            view.getpTabel().add(view.getScrollPane());
            view.getFrame().pack();
            view.getFrame().setLocationRelativeTo(null);
        }
    }

    private class AddProductToDBListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameTF().getText();
            int quantity = Integer.parseInt(view.getQuantityTF().getText());
            double price = Double.parseDouble(view.getPriceTF().getText());
            Product Product = new Product(name, quantity, price);
            productBLL.insertProduct(Product);

            JScrollPane scrollPane;
            //update view
            view.getpTabel().remove(view.getScrollPane());
            view.setTabel(view.populateJTable());
            scrollPane = new JScrollPane(view.getTabel());
            scrollPane.setPreferredSize(new Dimension(500, 250));
            view.setScrollPane(scrollPane);
            view.getpTabel().add(view.getScrollPane());
            view.getFrame().pack();
            view.getFrame().setLocationRelativeTo(null);
        }
    }

    private class AddProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton buton = view.getAddProduct();
            JPanel pGeneral = view.getpGeneral();
            if(buton.isSelected()){
                pGeneral.remove(view.getpEditProduct());
                pGeneral.remove(view.getpDeleteProduct());

                pGeneral.add(view.getpAddProduct(), 1);
            }
            JScrollPane scrollPane;
            //update view
            view.getpTabel().remove(view.getScrollPane());
            view.setTabel(view.populateJTable());
            scrollPane = new JScrollPane(view.getTabel());
            scrollPane.setPreferredSize(new Dimension(500, 250));
            view.setScrollPane(scrollPane);
            view.getpTabel().add(view.getScrollPane());

            view.getFrame().pack();
            view.getFrame().setLocationRelativeTo(null);
        }
    }

    private class EditProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton buton = view.getEditProduct();
            JPanel pGeneral = view.getpGeneral();
            if(buton.isSelected()){
                pGeneral.remove(view.getpAddProduct());
                pGeneral.remove(view.getpDeleteProduct());

                pGeneral.add(view.getpEditProduct(), 1);
            }
            JScrollPane scrollPane;
            //update view
            view.getpTabel().remove(view.getScrollPane());
            view.setTabel(view.populateJTable());
            scrollPane = new JScrollPane(view.getTabel());
            scrollPane.setPreferredSize(new Dimension(500, 250));
            view.setScrollPane(scrollPane);
            view.getpTabel().add(view.getScrollPane());

            view.getFrame().pack();
            view.getFrame().setLocationRelativeTo(null);
        }
    }

    private class DeleteProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton buton = view.getDeleteProduct();
            JPanel pGeneral = view.getpGeneral();
            if(buton.isSelected()){
                pGeneral.remove(view.getpAddProduct());
                pGeneral.remove(view.getpEditProduct());

                pGeneral.add(view.getpDeleteProduct(), 1);
            }
            JScrollPane scrollPane;
            //update view
            view.getpTabel().remove(view.getScrollPane());
            view.setTabel(view.populateJTable());
            scrollPane = new JScrollPane(view.getTabel());
            scrollPane.setPreferredSize(new Dimension(500, 250));
            view.setScrollPane(scrollPane);
            view.getpTabel().add(view.getScrollPane());

            view.getFrame().pack();
            view.getFrame().setLocationRelativeTo(null);
        }
    }
}
