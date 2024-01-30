public class Order {
    private static int counter = 0;
    private final int id;
    private final Buyer buyer;
    private final Product product;
    private final int quantity;

    public Order(Buyer buyer, Product product, int quantity) {
        id = ++counter;
        this.buyer = buyer;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "\n\nЗаказ: {\n" + "id=" + id + ", buyer=" + buyer + ", product=" + product + ", quantity=" + quantity + "\n}";
    }

    public int getId() {
        return id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
