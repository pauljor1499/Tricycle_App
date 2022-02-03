package passenger_service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

public class passenger_DB {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    String db_driver = "com.mysql.jdbc.Driver";
    String db_schema = "tricycle_app";
    String db_URL = "jdbc:mysql://localhost:3306/" + db_schema + "?useSSL=false";
    String db_username = "root";
    String db_password = "admin123";

    public static String passenger_ID = "";
    private double distance = 0.0;
    DecimalFormat formatter = new DecimalFormat("#.#");
    DecimalFormat formatter2 = new DecimalFormat("#.##");

    //Registering a passenger profile.
    public void createPassenger(String firstname, String middlename, String lastname,
            String gender, String address, String phone, String username, String password) {

        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("INSERT INTO passengers (passenger_firstname, passenger_middlename, passenger_lastname, "
                    + "passenger_gender, passenger_address, passenger_phone, passenger_username, passenger_password) VALUES (?,?,?,?,?,?,?,?) ");

            ps.setString(1, firstname);
            ps.setString(2, middlename);
            ps.setString(3, lastname);
            ps.setString(4, gender);
            ps.setString(5, address);
            ps.setString(6, phone);
            ps.setString(7, username);
            ps.setString(8, password);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Passenger successfully registered.", null, JOptionPane.PLAIN_MESSAGE);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Passenger already exist.");
        }
    }

    //Passenger upload photo.
    public void passenger_setPhoto(String passenger_lastname) {
        String getPhoto_dir = "E:\\NetBeansFiles\\Tricycle_App\\saved_images\\passenger_images";

        String newFileName = "";
        String oldFileName = "";

        try {

            //Getting the file.
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();

            if (f.getAbsolutePath().contains(".jpeg") || f.getAbsolutePath().contains(".jpg") || f.getAbsolutePath().contains(".png")) {

                //Basehan lang ni nga file kung nag exists.
                File get_dir = new File(getPhoto_dir + passenger_ID + passenger_lastname + ".jpeg");

                if (get_dir.exists()) {
                    get_dir.delete();
                } else {

                    //File is image.
                    //Filename for the selected image file.
                    oldFileName = f.getAbsolutePath();

                    //Creating new file name.
                    newFileName = getPhoto_dir + "\\" + passenger_ID + passenger_lastname + ".jpeg";

                    //Old file will move to new file.
                    //Old file will be also deleted.
                    FileUtils.copyFile(FileUtils.getFile(oldFileName), FileUtils.getFile(newFileName));

                    try {

                        Class.forName(db_driver);
                        con = DriverManager.getConnection(db_URL, db_username, db_password);
                        ps = con.prepareStatement("UPDATE passengers SET passenger_photo=? WHERE passenger_id=?");
                        ps.setString(1, newFileName);
                        ps.setString(2, passenger_ID);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Photo successfully uploaded.");

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex);
                    }

                    //Set passenger photo
                    Passenger_Manager.setPassengerPhoto(newFileName);
                }
            } else {
                //File is not image.
                JOptionPane.showMessageDialog(null, "Invalid file image.", "INVALID", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
    }

    public void getPassengerData() {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM passengers WHERE passenger_id ='+" + passenger_ID + "'");
            rs = ps.executeQuery();

            if (rs.next()) {
                Passenger_Manager.addPassengerData(rs.getString("passenger_id"), rs.getString("passenger_firstname"),
                        rs.getString("passenger_middlename"), rs.getString("passenger_lastname"),
                        rs.getString("passenger_address"), rs.getString("passenger_phone"), rs.getString("passenger_photo"));

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getTrip() {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM trips WHERE passenger_id='" + passenger_ID + "' AND trip_status='Waiting'");
            rs = ps.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(null,
                        "Passenger ID: " + passenger_ID + "\n"
                        + "Trip start: " + rs.getString("trip_start") + "\n"
                        + "Trip end: " + rs.getString("trip_end") + "\n"
                        + "Date: " + rs.getString("trip_date") + "\n"
                        + "Time: " + rs.getString("trip_time") + "\n"
                        + "Weather condition: " + rs.getString("trip_weather") + "\n"
                        + "Traffic condition: " + rs.getString("trip_traffic") + "\n"
                        + "Trip distance: " + rs.getString("trip_distance") + " km" + "\n"
                        + "Estimated time: " + rs.getString("trip_estimated_time") + " min" + "\n"
                        + "Total payment: ₱" + rs.getString("trip_total_payment") + "\n", "TRIP DETAILS", JOptionPane.PLAIN_MESSAGE);

                Passenger_Manager.setTripData(rs.getString("trip_start"), rs.getString("trip_end"),
                        rs.getString("trip_weather"), rs.getString("trip_traffic"), rs.getString("trip_total_payment"),
                        rs.getString("trip_distance"), rs.getString("trip_estimated_time"));

                try {

                    Class.forName(db_driver);
                    con = DriverManager.getConnection(db_URL, db_username, db_password);
                    ps = con.prepareStatement("SELECT * FROM trips WHERE passenger_id='" + passenger_ID + "' AND trip_status='Waiting'");
                    rs = ps.executeQuery();

                    if (rs.next()) {

                        if (rs.getString("tricycle_id") == null) {
                            //If tricycle_id is null on trips' table
                            Passenger_Manager.searchingDriver();

                        } else {
                            //If tricycle_id is not null on trips' table
                            searchDriver_by_Tricycle(rs.getString("tricycle_id"));
                        }
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Passenger_Manager.setTripData_Empty();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void searchDriver_by_Tricycle(String tricycle_id) {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT drivers.*, tricycles.tricycle_id FROM drivers\n"
                    + "\n"
                    + "INNER JOIN tricycles\n"
                    + "ON tricycles.driver_id=drivers.driver_id\n"
                    + "\n"
                    + "WHERE tricycles.tricycle_id='" + tricycle_id + "'");
            rs = ps.executeQuery();

            if (rs.next()) {
                Passenger_Manager.driverFound(rs.getString("driver_firstname") + " "
                        + rs.getString("driver_middlename") + " "
                        + rs.getString("driver_lastname"),
                        rs.getString("driver_photo"),
                        rs.getString("tricycle_id"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addTrip(String start, String end, String date, String time) {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("INSERT INTO trips (passenger_id, trip_start, trip_end,"
                    + " trip_date, trip_time,trip_weather, trip_traffic, trip_distance, "
                    + "trip_estimated_time, trip_total_payment, trip_status) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?) ");

            ps.setString(1, passenger_ID);
            ps.setString(2, start);
            ps.setString(3, end);
            ps.setString(4, date);
            ps.setString(5, time);
            ps.setString(6, getWeather());
            ps.setString(7, getTraffic());
            ps.setString(8, formatter.format(getDistance()) + " km");
            ps.setString(9, getEstimatedTime() + " min");
            ps.setString(10, formatter2.format(getTripPayment()));
            ps.setString(11, "Waiting");
            ps.executeUpdate();

            //Show Trip Details
            getTrip();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Invalid trip.");
        }
    }

    public void cancelTrip() {
        try {

            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("UPDATE trips SET trip_status=? WHERE passenger_id=? AND trip_status='Waiting'");
            ps.setString(1, "Cancelled");
            ps.setString(2, passenger_ID);
            ps.executeUpdate();

            Passenger_Manager.setTripData_Empty();

            JOptionPane.showMessageDialog(null, "Trip cancelled.", "TRIP", JOptionPane.PLAIN_MESSAGE);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void getStores() {
        ArrayList store_data = new ArrayList();
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM stores");
            rs = ps.executeQuery();

            while (rs.next()) {
                store_data.add(rs.getString("store_name"));
                store_data.add(rs.getString("store_address"));
                store_data.add(rs.getString("store_phone"));

                Passenger_Manager.setStoreData(store_data.toArray());
                store_data.clear();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getStoreProducts(String store) {
        ArrayList product_data = new ArrayList();
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM stores WHERE store_name='" + store + "'");
            rs = ps.executeQuery();

            if (rs.next()) {

                try {
                    Class.forName(db_driver);
                    con = DriverManager.getConnection(db_URL, db_username, db_password);
                    ps = con.prepareStatement("SELECT * FROM products WHERE store_id='" + rs.getString("store_id") + "'");
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        product_data.add(rs.getString("product_name"));

                        Passenger_Manager.setStoreProducts(product_data.toArray());
                        product_data.clear();
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getProductDetails(String store, String product) {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM stores WHERE store_name='" + store + "'");
            rs = ps.executeQuery();

            if (rs.next()) {

                try {
                    Class.forName(db_driver);
                    con = DriverManager.getConnection(db_URL, db_username, db_password);
                    ps = con.prepareStatement("SELECT * FROM products WHERE store_id='" + rs.getString("store_id") + "' AND product_name='" + product + "'");
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        Passenger_Manager.setProductDetails(rs.getString("product_name"),
                                rs.getString("product_type"),
                                rs.getString("product_price"),
                                rs.getString("product_photo"));
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getOrders(String product_name,
            String product_price, String product_quantity,
            String product_total) {

        String product_id;

        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT product_id FROM products WHERE product_name='" + product_name + "'");
            rs = ps.executeQuery();

            if (rs.next()) {

                product_id = rs.getString("product_id");

                //user have a product exists.
                try {
                    Class.forName(db_driver);
                    con = DriverManager.getConnection(db_URL, db_username, db_password);
                    ps = con.prepareStatement("SELECT * FROM orders WHERE order_status='ONCART' AND product_id='" + product_id + "'");
                    rs = ps.executeQuery();

                    if (rs.next()) {

                        try {
                            Class.forName(db_driver);
                            con = DriverManager.getConnection(db_URL, db_username, db_password);
                            ps = con.prepareStatement("UPDATE orders SET order_quantity=?, order_total_payment=? WHERE order_id='" + rs.getString("order_id") + "'");
                            ps.setString(1, (Integer.parseInt(product_quantity) + Integer.parseInt(rs.getString("order_quantity"))) + "");
                            ps.setString(2, (Double.parseDouble(product_total) + Double.parseDouble(rs.getString("order_total_payment"))) + "");
                            ps.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Product successfully added.");
                            return null;

                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex);
                }

                //user doesnt have a product.
                try {
                    Class.forName(db_driver);
                    con = DriverManager.getConnection(db_URL, db_username, db_password);
                    ps = con.prepareStatement("INSERT INTO orders (product_id, passenger_id, order_quantity, "
                            + "order_total_payment, order_status) VALUES (?,?,?,?,?) ");

                    ps.setString(1, product_id);
                    ps.setString(2, passenger_ID);
                    ps.setString(3, product_quantity);
                    ps.setString(4, product_total);
                    ps.setString(5, "ONCART");
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Product successfully added.");
                    return null;

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex);
                }

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void getCartCount(String store) {

        int count_order = 0;

        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT orders.*, products.*, stores.* FROM orders\n"
                    + "INNER JOIN products\n"
                    + "ON orders.product_id=products.product_id\n"
                    + "INNER JOIN stores\n"
                    + "ON products.store_id=stores.store_id");
            rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getString("passenger_id").equals(passenger_ID)
                        && rs.getString("store_name").equals(store)
                        && rs.getString("order_status").equals("ONCART")) {
                    count_order++;
                }
            }

            Passenger_Manager.setCartCount(count_order);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getCart(String store) {
        ArrayList product_data = new ArrayList();
        double product_total = 0;

        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT orders.*, products.*, stores.* FROM orders\n"
                    + "INNER JOIN products\n"
                    + "ON orders.product_id=products.product_id\n"
                    + "INNER JOIN stores\n"
                    + "ON products.store_id=stores.store_id");
            rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getString("passenger_id").equals(passenger_ID)
                        && rs.getString("order_status").equals("ONCART")
                        && rs.getString("store_name").equals(store)) {

                    product_data.add(rs.getString("product_name"));
                    product_data.add(rs.getString("product_type"));
                    product_data.add(rs.getString("product_price"));
                    product_data.add(rs.getString("order_quantity"));
                    product_data.add(rs.getString("order_total_payment"));

                    product_total += Double.parseDouble(rs.getString("order_total_payment"));

                    Passenger_Manager.setCartProducts(product_data.toArray(), product_total);
                    product_data.clear();

                }
            }

            Passenger_Manager.setCartProducts(product_total);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeCartProduct(String product) {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT orders.*, products.*, stores.* FROM orders\n"
                    + "INNER JOIN products\n"
                    + "ON orders.product_id=products.product_id\n"
                    + "INNER JOIN stores\n"
                    + "ON products.store_id=stores.store_id");
            rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getString("passenger_id").equals(passenger_ID)
                        && rs.getString("order_status").equals("ONCART")
                        && rs.getString("product_name").equals(product)) {

                    try {
                        Class.forName(db_driver);
                        con = DriverManager.getConnection(db_URL, db_username, db_password);
                        ps = con.prepareStatement("DELETE FROM orders WHERE order_id='" + rs.getString("order_id") + "' ");
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Product removed.", "MESSAGE", JOptionPane.PLAIN_MESSAGE);

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void takeOutOrders() {
        try {

            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("UPDATE orders SET order_status=? WHERE passenger_id='" + passenger_ID + "' AND order_status='ONCART'");
            ps.setString(1, "TAKE OUT");
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }

        JOptionPane.showMessageDialog(null, "TAKE OUT.", null, JOptionPane.PLAIN_MESSAGE);
        Passenger_Manager.setCartEmpty();
    }

    public void getPassengerOrders(String store) {
        ArrayList order_data = new ArrayList();
        double products = 0.0;
        double total_payment = 0.0;

        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT orders.*, products.*, stores.* FROM orders\n"
                    + "INNER JOIN products\n"
                    + "ON orders.product_id=products.product_id\n"
                    + "INNER JOIN stores\n"
                    + "ON products.store_id=stores.store_id");
            rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getString("passenger_id").equals(passenger_ID)
                        && rs.getString("order_status").equals("TAKE OUT")
                        && rs.getString("store_name").equals(store)) {

                    products += Double.parseDouble(rs.getString("order_quantity"));
                    total_payment += Double.parseDouble(rs.getString("order_total_payment"));

                }

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        order_data.add(store);
        order_data.add(products + "");
        order_data.add(total_payment + "");
        Passenger_Manager.setPassengerOrders(order_data.toArray());
        order_data.clear();
    }

    public void getPassengerOrderList() {
        ArrayList order_data = new ArrayList();
        String store = "";
        double products = 0.0;
        double total_payment = 0.0;

        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT orders.*, products.*, stores.* FROM orders\n"
                    + "INNER JOIN products\n"
                    + "ON orders.product_id=products.product_id\n"
                    + "INNER JOIN stores\n"
                    + "ON products.store_id=stores.store_id");
            rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getString("passenger_id").equals(passenger_ID)
                        && rs.getString("order_status").equals("TAKE OUT")) {

                    store = rs.getString("store_name");
                    products += Double.parseDouble(rs.getString("order_quantity"));
                    total_payment += Double.parseDouble(rs.getString("order_total_payment"));
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(passenger_DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (store.equals("")) {

        } else {
            order_data.add(store);
            order_data.add(products + "");
            order_data.add(total_payment + "");
            Passenger_Manager.setPassengerOrders(order_data.toArray());
            order_data.clear();
        }

    }

    private Double getDistance() {

        double min = 0.5;
        double max = 3.0;

        double random = new Random().nextDouble();
        double random_distance = min + (random * (max - min));
        double distance_num = random_distance;

        distance = distance_num;

        return distance_num;
    }

    private Integer getEstimatedTime() {

        if (distance >= 3.0) {
            return 15;

        } else if (distance >= 2.8) {
            return 14;

        } else if (distance >= 2.6) {
            return 13;

        } else if (distance >= 2.4) {
            return 12;

        } else if (distance >= 2.2) {
            return 11;

        } else if (distance >= 2.0) {
            return 10;

        } else if (distance >= 1.8) {
            return 9;

        } else if (distance >= 1.6) {
            return 8;

        } else if (distance >= 1.4) {
            return 7;

        } else if (distance >= 1.2) {
            return 6;

        } else if (distance >= 1.0) {
            return 5;
        } else {
            return 2;
        }
    }

    private Double getTripPayment() {

        if (distance >= 3.0) {
            return 20.50;

        } else if (distance >= 2.8) {
            return 19.0;

        } else if (distance >= 2.6) {
            return 18.50;

        } else if (distance >= 2.4) {
            return 17.0;

        } else if (distance >= 2.2) {
            return 16.50;

        } else if (distance >= 2.0) {
            return 15.0;

        } else if (distance >= 1.8) {
            return 14.50;

        } else if (distance >= 1.6) {
            return 13.0;

        } else if (distance >= 1.4) {
            return 12.50;

        } else if (distance >= 1.2) {
            return 11.0;

        } else if (distance >= 1.0) {
            return 10.50;
        } else {
            return 8.0;
        }

    }

    private String getWeather() {
        int min = 0;
        int max = 4;

        Random random = new Random();
        int random_wheather = random.nextInt(max - min + 1) + min;

        switch (random_wheather) {
            case 4:
                return "Sunny";
            case 3:
                return "Cloudy";
            case 2:
                return "Windy";
            case 1:
                return "Rainy";
            case 0:
                return "Stormy";
            default:
                return null;
        }
    }

    private String getTraffic() {
        int min = 0;
        int max = 2;

        Random random = new Random();
        int random_traffic = random.nextInt(max - min + 1) + min;

        switch (random_traffic) {

            case 2:
                return "Light";
            case 1:
                return "Moderate";
            case 0:
                return "Heavy";
            default:
                return null;
        }
    }

}
