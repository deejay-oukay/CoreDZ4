public class Buyer {
    private static int counter = 0;
    private final int id;
    private final String name;
    public Buyer(String name) {
        id = ++counter;
        this.name = name;
        Shop.addBuyer(this);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\nПокупатель: " + "id=" + id + ", name=" + name;
    }

    public String getName() {
        return name;
    }
}
