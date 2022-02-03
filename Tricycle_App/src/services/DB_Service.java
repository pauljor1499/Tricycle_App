package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//import driver database
import passenger_service.passenger_DB;

//import driver database
import driver_service.driver_DB;

//admin driver database
import vendor_service.vendor_DB;

//admin driver database
import admin_service.admin_DB;

public class DB_Service {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    String db_driver = "com.mysql.jdbc.Driver";
    String db_schema = "tricycle_app";
    String db_URL = "jdbc:mysql://localhost:3306/" + db_schema + "?useSSL=false";
    String db_username = "root";
    String db_password = "admin123";

    passenger_DB passenger_db = new passenger_DB();
    driver_DB driver_db = new driver_DB();
    vendor_DB vendor_db = new vendor_DB();
    admin_DB admin_db = new admin_DB();

    public String loginUser(String username, String password) {
        String user_type = "NO DATA";

        try {
            Class.forName(db_driver);
            con = DriverManager.getConnection(db_URL, db_username, db_password);

            //QUERY TO FETCH PASSENGER'S DATA
            ps = con.prepareStatement("SELECT * FROM passengers\n"
                    + "WHERE passenger_username=? AND passenger_password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                //Passenger Data
                if (rs.getString("passenger_username").equals(username) && rs.getString("passenger_password").equals(password)) {
                    user_type = "passenger";
                    passenger_db.passenger_ID = rs.getString("passenger_id");
                }
            }

            //CHANGE QUERY TO FETCH DRIVERS' DATA
            ps = con.prepareStatement("SELECT * FROM drivers\n"
                    + "WHERE driver_username=? AND driver_password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("driver_username").equals(username) && rs.getString("driver_password").equals(password)) {
                    user_type = "driver";
                    driver_db.driver_ID = rs.getString("driver_id");
                }
            }

            //CHANGE QUERY TO FETCH VENDOR'S DATA
            ps = con.prepareStatement("SELECT * FROM vendors\n"
                    + "WHERE vendor_username=? AND vendor_password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("vendor_username").equals(username) && rs.getString("vendor_password").equals(password)) {
                    user_type = "vendor";
                    vendor_db.vendor_ID = rs.getString("vendor_id");
                }
            }

            //CHANGE QUERY TO FETCH ADMIN'S DATA
            ps = con.prepareStatement("SELECT * FROM admins\n"
                    + "WHERE admin_username=? AND admin_password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("admin_username").equals(username) && rs.getString("admin_password").equals(password)) {
                    user_type = "admin";
                    admin_db.admin_ID = rs.getString("admin_id");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(DB_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user_type;
    }

}
