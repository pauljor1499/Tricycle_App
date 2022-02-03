package vendor_service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

public class vendor_DB {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    String db_driver = "com.mysql.jdbc.Driver";
    String db_schema = "tricycle_app";
    String db_URL = "jdbc:mysql://localhost:3306/" + db_schema + "?useSSL=false";
    String db_username = "root";
    String db_password = "admin123";

    public static String vendor_ID = "";

    public void getVendorData() {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM vendors WHERE vendor_id ='+" + vendor_ID + "'");
            rs = ps.executeQuery();

            if (rs.next()) {
                Vendor_Manager.addVendorData(rs.getString("vendor_id"), rs.getString("vendor_firstname"),
                        rs.getString("vendor_middlename"), rs.getString("vendor_lastname"),
                        rs.getString("vendor_address"), rs.getString("vendor_phone"), rs.getString("vendor_photo"));

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(vendor_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createVendor(String firstname, String middlename, String lastname,
            String gender, String address, String phone, String username, String password) {

        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("INSERT INTO vendors (vendor_firstname, vendor_middlename, vendor_lastname, "
                    + "vendor_gender, vendor_address, vendor_phone, vendor_username, vendor_password) VALUES (?,?,?,?,?,?,?,?) ");

            ps.setString(1, firstname);
            ps.setString(2, middlename);
            ps.setString(3, lastname);
            ps.setString(4, gender);
            ps.setString(5, address);
            ps.setString(6, phone);
            ps.setString(7, username);
            ps.setString(8, password);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Vendor successfully registered.");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(vendor_DB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Vendor already exist.");
        }
    }

    public void checkStore(String store) {
        if (store.equals("Store UNREGISTERED")) {
            //if store doesnt exists
            JOptionPane.showMessageDialog(null, "You need to register your store first.", null, JOptionPane.PLAIN_MESSAGE);
            Vendor_Manager.viewStoreReg();

        } else {
            //if store exitst
            try {
                Class.forName(db_driver);
                con = DriverManager.getConnection(db_URL, db_username, db_password);
                ps = con.prepareStatement("SELECT * FROM stores WHERE vendor_id='" + vendor_ID + "' ");
                rs = ps.executeQuery();

                if (rs.next()) {
                    Vendor_Manager.viewProductReg();
                }

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(vendor_DB.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Vendor already exist.");
            }
        }
    }

    public void registerStore(String name, String address, String phone) {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("INSERT INTO stores (vendor_id, store_name, store_address, store_phone) VALUES (?,?,?,?) ");

            ps.setString(1, vendor_ID);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setString(4, phone);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Store successfully registered.", null, JOptionPane.PLAIN_MESSAGE);
            Vendor_Manager.viewProductReg();

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Store already exist.", "INVALID", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getStoreName() {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM stores WHERE vendor_id='" + vendor_ID + "'");

            rs = ps.executeQuery();

            if (rs.next()) {
                Vendor_Manager.setStoreName(rs.getString("store_name"));
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(vendor_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registerProduct(String store_name, String product_name, String type, String price) {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM stores\n"
                    + "INNER JOIN vendors\n"
                    + "ON vendors.vendor_id = stores.vendor_id");

            rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getString("vendor_id").equals(vendor_ID)) {
                    try {
                        Class.forName(db_driver);
                        con = DriverManager.getConnection(db_URL, db_username, db_password);
                        ps = con.prepareStatement("INSERT INTO products (store_id, product_name, product_type, product_price) VALUES (?,?,?,?) ");

                        ps.setString(1, rs.getString("store_id"));
                        ps.setString(2, product_name);
                        ps.setString(3, type);
                        ps.setString(4, price);

                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Product successfully registered.", null, JOptionPane.PLAIN_MESSAGE);
                        getProducts();
                        Vendor_Manager.hideProductReg();

                    } catch (ClassNotFoundException | SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Product already exist.", "INVALID", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Product already exist.", "INVALID", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getProducts() {
        ArrayList products = new ArrayList();
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT products.*, vendors.vendor_id FROM stores\n"
                    + "INNER JOIN vendors\n"
                    + "ON vendors.vendor_id = stores.vendor_id\n"
                    + "INNER JOIN products\n"
                    + "ON products.store_id = stores.store_id");

            rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getString("vendor_id").equals(vendor_ID)) {

                    products.add(rs.getString("products.product_name"));
                    products.add(rs.getString("products.product_type"));
                    products.add(rs.getString("products.product_price"));

                    Vendor_Manager.setVendorProducts(products.toArray());
                    products.clear();

                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Product already exist.", "INVALID", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Vendor upload photo.
    public void vendor_setPhoto(String vendor_lastname) {
        String getPhoto_dir = "E:\\NetBeansFiles\\Tricycle_App\\saved_images\\vendor_images";

        String newFileName = "";
        String oldFileName = "";

        try {

            //Getting the file.
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();

            if (f.getAbsolutePath().contains(".jpeg") || f.getAbsolutePath().contains(".jpg") || f.getAbsolutePath().contains(".png")) {

                //Basehan lang ni nga file kung nag exists.
                File get_dir = new File(getPhoto_dir + vendor_ID + vendor_lastname + ".jpeg");

                if (get_dir.exists()) {
                    get_dir.delete();
                } else {

                    //File is image.
                    //Filename for the selected image file.
                    oldFileName = f.getAbsolutePath();

                    //Creating new file name.
                    newFileName = getPhoto_dir + "\\" + vendor_ID + vendor_lastname + ".jpeg";

                    //Old file will move to new file.
                    //Old file will be also deleted.
                    FileUtils.copyFile(FileUtils.getFile(oldFileName), FileUtils.getFile(newFileName));

                    try {

                        Class.forName(db_driver);
                        con = DriverManager.getConnection(db_URL, db_username, db_password);
                        ps = con.prepareStatement("UPDATE passengers SET passenger_photo=? WHERE passenger_id=?");
                        ps.setString(1, newFileName);
                        ps.setString(2, vendor_ID);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Photo successfully uploaded.");

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(vendor_DB.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex);
                    }

                    //Set admin photo
                    Vendor_Manager.setVendorPhoto(newFileName);
                }
            } else {
                //File is not image.
                JOptionPane.showMessageDialog(null, "Invalid file image.", "INVALID", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
    }

}
