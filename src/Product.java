public class Product {
    private static int counter = 0;
    private final int id;
    private final String name;
    public Product(String name) {
        id = ++counter;
        this.name = name;
        Shop.addProduct(this);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\nТовар: " + "id=" + id + ", name=" + name;
    }

    public String getName() {
        return name;
    }
}
