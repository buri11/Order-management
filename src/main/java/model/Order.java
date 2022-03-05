package model;

import com.itextpdf.text.Font;
import dao.AbstractDAO;

import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.FontFactory;

/**
 * The type Order.
 */
public class Order {
    /**
     * The Id.
     */
    public int id;
    /**
     * The Client id.
     */
    public int clientID;
    /**
     * The Product id.
     */
    public int productID;
    /**
     * The Quantity.
     */
    public int quantity;
    /**
     * The Total price.
     */
    public double totalPrice;

    /**
     * Instantiates a new Order.
     */
    public Order(){

    }

    /**
     * Instantiates a new Order.
     *
     * @param id        the id
     * @param clientID  the client id
     * @param productID the product id
     * @param quantity  the quantity
     * @param price     the price
     */
    public Order(int id, int clientID, int productID, int quantity, double price){
        this.id = id;
        this.clientID = clientID;
        this.productID = productID;
        this.quantity = quantity;
        this.totalPrice = price;
    }

    /**
     * Instantiates a new Order.
     *
     * @param clientID  the client id
     * @param productID the product id
     * @param quantity  the quantity
     * @param price     the price
     */
    public Order(int clientID, int productID, int quantity, double price){
        this.clientID = clientID;
        this.productID = productID;
        this.quantity = quantity;
        this.totalPrice = price;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Sets client id.
     *
     * @param clientID the client id
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Sets product id.
     *
     * @param productID the product id
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets total price.
     *
     * @return the total price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets total price.
     *
     * @param totalPrice the total price
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String toString(){
        return "Order: [id: " + id + ", clientID: " + clientID + ", productID: " + productID + ", quantity: " + quantity + ", total price" + totalPrice + "]";
    }

    /**
     * Write pdf.
     */
    public void writePDF() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("order_details.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
        String client, product;
        int quantity;
        client = ((Client)new AbstractDAO(Client.class).findById(this.getClientID())).getName();
        product = ((Product)new AbstractDAO(Product.class).findById(this.getProductID())).getName();
        quantity = this.quantity;
        double price = ((Product)new AbstractDAO(Product.class).findById(this.getProductID())).getPrice();
        Chunk chunk1, chunk2, chunk3, chunk4, chunk5;
        chunk1 = new Chunk("Order with details: ", font);
        chunk2 = new Chunk("Client: " + client, font);
        chunk3 = new Chunk("Product: " + product, font);
        chunk4 = new Chunk("Quantity: " + quantity, font);

        chunk5 = new Chunk("Price: " + price, font);

        try {
            document.add(new Paragraph(chunk1));
            document.add(new Paragraph(chunk2));
            document.add(new Paragraph(chunk3));
            document.add(new Paragraph(chunk4));
            document.add(new Paragraph(chunk5));

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();

    }
}
