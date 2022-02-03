package driver_service;

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

public class driver_DB {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    String db_driver = "com.mysql.jdbc.Driver";
    String db_schema = "tricycle_app";
    String db_URL = "jdbc:mysql://localhost:3306/" + db_schema + "?useSSL=false";
    String db_username = "root";
    String db_password = "admin123";

    public static String driver_ID = "";

    public void createDriver(String firstname, String middlename, String lastname,
            String gender, String address, String phone, String username, String password) {

        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("INSERT INTO drivers (driver_firstname, driver_middlename, driver_lastname, "
                    + "driver_gender, driver_address, driver_phone, driver_username, driver_password) VALUES (?,?,?,?,?,?,?,?) ");

            ps.setString(1, firstname);
            ps.setString(2, middlename);
            ps.setString(3, lastname);
            ps.setString(4, gender);
            ps.setString(5, address);
            ps.setString(6, phone);
            ps.setString(7, username);
            ps.setString(8, password);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Driver successfully registered.");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(driver_DB.class
                    .getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Driver already exist.");
        }
    }

    //Passengers waiting for a ride.
    public void getTrips() {
        ArrayList trip_data = new ArrayList();

        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM trips WHERE trip_status='Waiting'");
            rs = ps.executeQuery();

            while (rs.next()) {
                trip_data.add(rs.getString("passenger_id"));
                trip_data.add(rs.getString("trip_start"));
                trip_data.add(rs.getString("trip_end"));
                trip_data.add(rs.getString("trip_total_payment"));
                trip_data.add(rs.getString("trip_status"));

                Driver_Manager.addTripData(trip_data.toArray());
                trip_data.clear();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(driver_DB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Products to deliver.
    public void getDeliveries() {
        ArrayList deliveries_data = new ArrayList();

        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM deliveries WHERE delivery_status='Pending'");
            rs = ps.executeQuery();

            while (rs.next()) {
                deliveries_data.add(rs.getString("order_id"));
                deliveries_data.add(rs.getString("delivery_location"));
                deliveries_data.add(rs.getString("delivery_total_payment"));
                deliveries_data.add(rs.getString("delivery_status"));

                Driver_Manager.addDeliveriesData(deliveries_data.toArray());
                deliveries_data.clear();

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(driver_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Viewing driver's profile and other data.
    public void getDriverData() {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM drivers WHERE driver_id ='+" + driver_ID + "'");
            rs = ps.executeQuery();

            if (rs.next()) {
                Driver_Manager.addDriverData(rs.getString("driver_id"), rs.getString("driver_firstname"),
                        rs.getString("driver_middlename"), rs.getString("driver_lastname"),
                        rs.getString("driver_address"), rs.getString("driver_phone"), rs.getString("driver_photo"));

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(driver_DB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Getting driver's penalties for trips.
    public Integer getPenalties(String tricycle_id) {
        int count_penalty = 0;
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT trips.penalty_id FROM trips\n"
                    + "INNER JOIN tricycles ON tricycles.tricycle_id=trips.tricycle_id\n"
                    + "WHERE trips.tricycle_id='" + tricycle_id + "' AND trips.trip_status='Delivered'");
            rs = ps.executeQuery();

            while (rs.next()) {
                count_penalty++;

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(driver_DB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return count_penalty;
    }

    //Getting tricycle's profile and other data.
    public void getTricycleData() {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM tricycles WHERE driver_id='" + driver_ID + "'");
            rs = ps.executeQuery();

            if (rs.next()) {
                Driver_Manager.addTricyclePofile(rs.getString("tricycle_id"));
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(driver_DB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Driver upload photo.
    public void driver_setPhoto(String driver_lastname) {
        String getPhoto_dir = "E:\\NetBeansFiles\\Tricycle_App\\saved_images\\driver_images";

        String newFileName = "";
        String oldFileName = "";

        try {

            //Getting the file.
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();

            if (f.getAbsolutePath().contains(".jpeg") || f.getAbsolutePath().contains(".jpg") || f.getAbsolutePath().contains(".png")) {

                //Basehan lang ni nga file kung nag exists.
                File get_dir = new File(getPhoto_dir + driver_ID + driver_lastname + ".jpeg");

                if (get_dir.exists()) {
                    get_dir.delete();
                } else {

                    //File is image.
                    //Filename for the selected image file.
                    oldFileName = f.getAbsolutePath();

                    //Creating new file name.
                    newFileName = getPhoto_dir + "\\" + driver_ID + driver_lastname + ".jpeg";

                    //Old file will move to new file.
                    //Old file will be also deleted.
                    FileUtils.copyFile(FileUtils.getFile(oldFileName), FileUtils.getFile(newFileName));

                    try {

                        Class.forName(db_driver);
                        con = DriverManager.getConnection(db_URL, db_username, db_password);
                        ps = con.prepareStatement("UPDATE drivers SET driver_photo=? WHERE driver_id=?");
                        ps.setString(1, newFileName);
                        ps.setString(2, driver_ID);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Photo successfully uploaded.");

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(driver_DB.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex);
                    }

                    //Set driver photo
                    Driver_Manager.setDriverPhoto(newFileName);
                }
            } else {
                //File is not image.
                JOptionPane.showMessageDialog(null, "Invalid file image.", "INVALID", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
    }

}
