package admin_service;

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

public class admin_DB {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    String db_driver = "com.mysql.jdbc.Driver";
    String db_schema = "tricycle_app";
    String db_URL = "jdbc:mysql://localhost:3306/" + db_schema + "?useSSL=false";
    String db_username = "root";
    String db_password = "admin123";

    public static String admin_ID = "";

    //ADMIN
    //Viewing admin's profile.
    public void addAdminData() {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM admins WHERE admin_id ='+" + admin_ID + "'");
            rs = ps.executeQuery();

            if (rs.next()) {
                Admin_Manager.viewAdminData(rs.getString("admin_id"), rs.getString("admin_firstname"),
                        rs.getString("admin_middlename"), rs.getString("admin_lastname"),
                        rs.getString("admin_address"), rs.getString("admin_phone"), rs.getString("admin_photo"));

//                System.out.println(rs.getString("admin_firstname"));
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(admin_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Viewing the list of tricycles for admin table.
    public void addTricycleData() {
        ArrayList tricycle_data = new ArrayList();
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM tricycles");
            rs = ps.executeQuery();

            while (rs.next()) {
                tricycle_data.add(rs.getString("tricycle_id"));
                tricycle_data.add(rs.getString("franchise_id"));
                tricycle_data.add(rs.getString("driver_id"));
                tricycle_data.add(rs.getString("tricycle_color"));
                tricycle_data.add(rs.getString("tricycle_status"));

                Admin_Manager.viewTricycleData(tricycle_data.toArray());
                tricycle_data.clear();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(admin_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Admin upload photo.
    public void admin_setPhoto(String admin_lastname) {
        String getPhoto_dir = "E:\\NetBeansFiles\\Tricycle_App\\saved_images\\admin_images";

        String newFileName = "";
        String oldFileName = "";

        try {

            //Getting the file.
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();

            if (f.getAbsolutePath().contains(".jpeg") || f.getAbsolutePath().contains(".jpg") || f.getAbsolutePath().contains(".png")) {

                //Basehan lang ni nga file kung nag exists.
                File get_dir = new File(getPhoto_dir + admin_ID + admin_lastname + ".jpeg");

                if (get_dir.exists()) {
                    get_dir.delete();
                } else {

                    //File is image.
                    //Filename for the selected image file.
                    oldFileName = f.getAbsolutePath();

                    //Creating new file name.
                    newFileName = getPhoto_dir + "\\" + admin_ID + admin_lastname + ".jpeg";

                    //Old file will move to new file.
                    //Old file will be also deleted.
                    FileUtils.copyFile(FileUtils.getFile(oldFileName), FileUtils.getFile(newFileName));

                    try {

                        Class.forName(db_driver);
                        con = DriverManager.getConnection(db_URL, db_username, db_password);
                        ps = con.prepareStatement("UPDATE admins SET admin_photo=? WHERE admin_id=?");
                        ps.setString(1, newFileName);
                        ps.setString(2, admin_ID);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Photo successfully uploaded.");

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(admin_DB.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex);
                    }

                    //Set admin photo
                    Admin_Manager.setAdminPhoto(newFileName);
                }
            } else {
                //File is not image.
                JOptionPane.showMessageDialog(null, "Invalid file image.", "INVALID", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
    }

    public void getTricycleRecord(String id) {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM tricycles WHERE tricycle_id='" + id + "'");
            rs = ps.executeQuery();

            if (rs.next()) {
                Admin_Manager.viewTricycleRecord(rs.getString("tricycle_id"), rs.getString("franchise_id"), rs.getString("driver_id"),
                        rs.getString("tricycle_color"), rs.getString("tricycle_status"));

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(admin_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTricycleRecord(String id, String item) {
        try {

            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("UPDATE tricycles SET tricycle_status=? WHERE tricycle_id=" + id);
            ps.setString(1, item);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Tricycle record successfully updated.");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(admin_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getTricycleID(String id) {
        try {

            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM tricycles WHERE tricycle_id=" + id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Admin_Manager.viewTricycleData(rs.getString("tricycle_id"));
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(admin_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setFranchise(String tricycle_id, String start, String expiration) {
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("INSERT INTO franchises (tricycle_id, franchise_date_start, franchise_date_end) VALUES (?,?,?) ");

            ps.setString(1, tricycle_id);
            ps.setString(2, start);
            ps.setString(3, expiration);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Tricycle franchise successfully registered.", null, JOptionPane.PLAIN_MESSAGE);

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Franchise.");
        }
    }

    public void getFranchise() {
        ArrayList franchise_data = new ArrayList();
        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);
            ps = con.prepareStatement("SELECT * FROM franchises");

            rs = ps.executeQuery();

            while (rs.next()) {
                franchise_data.add(rs.getString("tricycle_id"));
                franchise_data.add(rs.getString("franchise_date_start"));
                franchise_data.add(rs.getString("franchise_date_end"));

                Admin_Manager.setFranchiseData(franchise_data.toArray());
                franchise_data.clear();
            }

            JOptionPane.showMessageDialog(null, "Tricycle franchise successfully registered.", null, JOptionPane.PLAIN_MESSAGE);

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Franchise.");
        }
    }

}
