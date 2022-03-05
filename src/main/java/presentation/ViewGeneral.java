package presentation;

/**
 * The type View general.
 */
public class ViewGeneral {
    private ViewClient viewClient;
    private ViewProduct viewProduct;
    private ViewOrder viewOrder;

    /**
     * Instantiates a new View general.
     */
    public ViewGeneral(){
        viewClient = new ViewClient();
        viewProduct = new ViewProduct();
        viewOrder = new ViewOrder();
    }

    /**
     * Gets view client.
     *
     * @return the view client
     */
    public ViewClient getViewClient() {
        return viewClient;
    }

    /**
     * Gets view product.
     *
     * @return the view product
     */
    public ViewProduct getViewProduct() {
        return viewProduct;
    }

    /**
     * Gets view order.
     *
     * @return the view order
     */
    public ViewOrder getViewOrder() {
        return viewOrder;
    }
}
