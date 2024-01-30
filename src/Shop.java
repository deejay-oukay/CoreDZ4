import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Shop {
    private static ArrayList<Buyer> buyers = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();

    // "создать (!)статический(!) метод “совершить покупку” со строковыми(!) параметрами, соответствующими полям объекта заказа"
    static Order makePurchase(String product, String quantity, String buyer){
        try {
            if (!productExists(product))
                throw new MyException("Товар ("+product+") не найден");
            if (!buyerExists(buyer))
                throw new MyException("Покупатель ("+buyer+") не найден");
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
        try {
            int q = Integer.parseInt(quantity);
        }
        catch (NumberFormatException e){
            System.out.println("Некорректное число ("+quantity+") в количестве");
            return null;
        }
        try {
            if (Integer.parseInt(quantity) < 1)
                throw new MyException("Число товаров должно быть положительным целым числом, а не "+quantity);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
        try {
            Order order = new Order(getBuyerById(Integer.parseInt(buyer)),getProductById(Integer.parseInt(product)),Integer.parseInt(quantity));
            if (order != null)
            {
                addOrder(order);
                return order;
            }
            else
                throw new MyException("При создании заказа произошла ошибка. Мы уже знаем об этом и бросили все наши ресурсы на устранение проблемы. Пока обратитесь к конкурентам");
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void addProduct(Product product){
        products.add(product);
    }

    public static void addBuyer(Buyer buyer){
        buyers.add(buyer);
    }

    public static void addOrder(Order order){
        orders.add(order);
    }

    private static Boolean buyerExists(String id){
        try {
            for (Buyer buyer: buyers)
                if (buyer.getId() == Integer.parseInt(id))
                    return true;
        } catch (NumberFormatException e) {
            System.out.println("Некорректное число ("+id+") в ID покупателя");
            return false;
        }
        return false;
    }
    private static Boolean productExists(String id){
        try {
            for (Product product : products)
                if (product.getId() == Integer.parseInt(id))
                    return true;
        } catch (NumberFormatException e) {
            System.out.println("Некорректное число ("+id+") в ID товара");
            return false;
        }
        return false;
    }

    private static Buyer getBuyerById(int id){
        for (Buyer buyer: buyers)
            if (buyer.getId() == id)
                return buyer;
        return null;
    }

    private static Product getProductById(int id){
        for (Product product: products)
            if (product.getId() == id)
                return product;
        return null;
    }

    public static ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public static void saveOrders() {
        try (FileWriter fw = new FileWriter("orders.txt",false)) {
            for (Order order: orders)
                fw.write("Заказ № "+order.getId()+". Покупатель: "+order.getBuyer().getName()+". Товар: "+order.getProduct().getName()+
                        ". Кол-во: "+order.getQuantity()+" \n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int ordersCunt() {
        return orders.size();
    }
}
