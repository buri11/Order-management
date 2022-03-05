package bll;

import dao.AbstractDAO;
import model.Product;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Product bll.
 */
public class ProductBLL {
    private AbstractDAO abstractDAO;

    /**
     * Instantiates a new Product bll.
     */
    public ProductBLL() {
        abstractDAO = new AbstractDAO(Product.class);
    }

    /**
     * Delete product.
     *
     * @param p the p
     */
    public void deleteProduct(Product p){
        abstractDAO.deleteByID(p.getId());
    }

    /**
     * Update product.
     *
     * @param p      the p
     * @param field  the field
     * @param newVal the new val
     */
    public void updateProduct(Product p, String field, Object newVal){
        abstractDAO.update(p, field, newVal);
    }

    /**
     * Find all products list.
     *
     * @return the list
     */
    public List<Product> findAllProducts(){
        return abstractDAO.findAll();
    }

    /**
     * Find product by id product.
     *
     * @param id the id
     * @return the product
     */
    public Product findProductById(int id) {
        Product p = (Product)abstractDAO.findById(id);
        if (p == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return p;
    }

    /**
     * Insert product int.
     *
     * @param p the p
     * @return the int
     */
    public int insertProduct(Product p) {
        return abstractDAO.insert(p);
    }
}
