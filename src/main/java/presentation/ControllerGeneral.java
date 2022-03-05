package presentation;

/**
 * The type Controller general.
 */
public class ControllerGeneral {
    private ViewGeneral viewGeneral;
    private ViewClient viewClient;
    private ViewProduct viewProduct;
    private ViewOrder viewOrder;

    private ControllerClient controllerClient;
    private ControllerProduct controllerProduct;
    private ControllerOrder controllerOrder;

    /**
     * Instantiates a new Controller general.
     *
     * @param v the v
     */
    public ControllerGeneral(ViewGeneral v){
        viewGeneral = v;

        viewClient = viewGeneral.getViewClient();
        viewProduct = viewGeneral.getViewProduct();
        viewOrder = viewGeneral.getViewOrder();

        controllerClient = new ControllerClient(viewClient);
        controllerProduct = new ControllerProduct(viewProduct);
        controllerOrder = new ControllerOrder(viewOrder);
    }
}
