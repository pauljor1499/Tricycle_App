package main;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

//if class is not on the same package
import services.DB_Service;

//import passenger package
import passenger_service.passenger_DB;

//import driver package
import driver_service.driver_DB;

//import admin package
import admin_service.admin_DB;
import vendor_service.vendor_DB;

public class Main_Frame extends javax.swing.JFrame {

    public Main_Frame() {
        initComponents();

        DayTime();
    }

    DB_Service connect_services = new DB_Service();

    passenger_DB connect_passenger = new passenger_DB();

    driver_DB connect_driver = new driver_DB();

    admin_DB connect_admin = new admin_DB();

    vendor_DB connect_vendor = new vendor_DB();

    //get tricycle id from admin's tricycle table
    public String getTricycle_ID = "";
    //Admin's tricycle table
    int selectedRow = 0;

    //get vendor id from passenger's store table
    public String getStore_Name = "";
    public String getProduct_Name = "";

    Color maroon = new Color(93, 25, 50);
    Color red = new Color(255, 102, 102);

    Border unselected = BorderFactory.createLineBorder(maroon);
    Border selected = BorderFactory.createLineBorder(Color.WHITE);

    private void userLogin(String username, String password) {
        if (connect_services.loginUser(username, password).equals("passenger")) {
            System.out.println(connect_services.loginUser(username, password));
            PassengerDashboard();
            System.out.println("user id " + connect_passenger.passenger_ID);
            JOptionPane.showMessageDialog(null, "Login successful!", "LOGIN", JOptionPane.PLAIN_MESSAGE);

        } else if (connect_services.loginUser(username, password).equals("driver")) {
            System.out.println(connect_services.loginUser(username, password));
            DriverDashboard();
            System.out.println("user id " + connect_driver.driver_ID);
            JOptionPane.showMessageDialog(null, "Login successful!", "LOGIN", JOptionPane.PLAIN_MESSAGE);

        } else if (connect_services.loginUser(username, password).equals("vendor")) {
            System.out.println(connect_services.loginUser(username, password));
            VendorDashboard();
            System.out.println("user id " + connect_vendor.vendor_ID);
            JOptionPane.showMessageDialog(null, "Login successful!", "LOGIN", JOptionPane.PLAIN_MESSAGE);

        } else if (connect_services.loginUser(username, password).equals("admin")) {
            System.out.println(connect_services.loginUser(username, password));
            AdminDashboard();
            System.out.println("user id " + connect_admin.admin_ID);
            JOptionPane.showMessageDialog(null, "Login successful!", "LOGIN", JOptionPane.PLAIN_MESSAGE);

        } else {
            System.out.println(connect_services.loginUser(username, password));
            JOptionPane.showMessageDialog(null, "Incorrect username or password.", "INVALID", JOptionPane.ERROR_MESSAGE);
        }
    }

    //DRIVER DASHBOARD
    private void DriverDashboard() {
        this.setVisible(false);
        driver_dashboard.setVisible(true);
        driver_dashboard.pack();
        driver_dashboard.setLocationRelativeTo(this);

        //Activate DB Functions
        connect_driver.getDriverData();
        connect_driver.getTricycleData();
        connect_driver.getTrips();//Table
        connect_driver.getDeliveries();//Table
    }

    //Driver REGISTRATION
    private void DriverReg() {
        this.setVisible(false);
        driver_registration.setVisible(true);
        driver_registration.pack();
        driver_registration.setLocationRelativeTo(this);
    }

    //ADMIN DASHBOARD
    private void AdminDashboard() {
        this.setVisible(false);
        admin_dashboard.setVisible(true);
        admin_dashboard.pack();
        admin_dashboard.setLocationRelativeTo(this);

        //Activate DB Functions
        connect_admin.addAdminData();
        connect_admin.addTricycleData();
        connect_admin.getFranchise();
    }

    private void registerDriver() {
        connect_driver.createDriver(driver_reg_firstname.getText(),
                driver_reg_middlename.getText(),
                driver_reg_lastname.getText(),
                driver_reg_gender.getText(),
                driver_reg_address.getText(),
                driver_reg_phone.getText(),
                driver_reg_username.getText(),
                driver_reg_password.getText());
    }

    private void driver_reg_erase() {
        driver_reg_firstname.setText("");
        driver_reg_middlename.setText("");
        driver_reg_lastname.setText("");
        driver_reg_gender.setText("");
        driver_reg_address.setText("");
        driver_reg_username.setText("");
        driver_reg_username.setText("");
        driver_reg_password.setText("");
    }

    //Passenger REGISTRATION
    private void PassengerReg() {
        this.setVisible(false);
        passenger_registration.setVisible(true);
        passenger_registration.pack();
        passenger_registration.setLocationRelativeTo(this);
    }

    private void registerPassenger() {
        connect_passenger.createPassenger(passenger_reg_firstname.getText(),
                passenger_reg_middlename.getText(),
                passenger_reg_lastname.getText(),
                passenger_reg_gender.getText(),
                passenger_reg_address.getText(),
                passenger_reg_phone.getText(),
                passenger_reg_username.getText(),
                passenger_reg_password.getText());
    }

    private void passenger_reg_erase() {
        passenger_reg_firstname.setText("");
        passenger_reg_middlename.setText("");
        passenger_reg_lastname.setText("");
        passenger_reg_gender.setText("");
        passenger_reg_address.setText("");
        passenger_reg_username.setText("");
        passenger_reg_username.setText("");
        passenger_reg_password.setText("");
    }

    //PASSENGER DASHBOARD
    private void PassengerDashboard() {
        this.setVisible(false);
        passenger_dashboard.setVisible(true);
        passenger_dashboard.pack();
        passenger_dashboard.setLocationRelativeTo(this);

        connect_passenger.getPassengerData();
        connect_passenger.getTrip();
        connect_passenger.getStores();
        connect_passenger.getPassengerOrderList();
    }

    //VENDOR REGISTRATION
    private void VendorReg() {
        this.setVisible(false);
        vendor_registration.setVisible(true);
        vendor_registration.pack();
        vendor_registration.setLocationRelativeTo(this);
    }

    private void registerVendor() {
        connect_vendor.createVendor(
                vendor_reg_firstname.getText(),
                vendor_reg_middlename.getText(),
                vendor_reg_lastname.getText(),
                vendor_reg_gender.getText(),
                vendor_reg_address.getText(),
                vendor_reg_phone.getText(),
                vendor_reg_username.getText(),
                vendor_reg_password.getText()
        );
    }

    private void vendor_reg_erase() {
        vendor_reg_firstname.setText("");
        vendor_reg_middlename.setText("");
        vendor_reg_lastname.setText("");
        vendor_reg_gender.setText("");
        vendor_reg_address.setText("");
        vendor_reg_username.setText("");
        vendor_reg_username.setText("");
        vendor_reg_password.setText("");
    }

    //VENDOR DASHBOARD
    private void VendorDashboard() {
        this.setVisible(false);
        vendor_dashboard.setVisible(true);
        vendor_dashboard.pack();
        vendor_dashboard.setLocationRelativeTo(this);

        connect_vendor.getVendorData();
        connect_vendor.getStoreName();
        connect_vendor.getProducts();
    }

    private void DayTime() {

        Timer timer;
        TimerTask task;

        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {

                SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");

                Calendar c = Calendar.getInstance();

                //Driver Dashboard
                driver_date.setText(dateFormat.format(c.getTime()));
                driver_time.setText(timeFormat.format(c.getTime()));
                //Driver Register Frame
                driver_reg_date.setText(dateFormat.format(c.getTime()));
                driver_reg_time.setText(timeFormat.format(c.getTime()));

                //Passenger Dashboard
                passenger_date.setText(dateFormat.format(c.getTime()));
                passenger_time.setText(timeFormat.format(c.getTime()));
                //Passenger Register Frame
                passenger_reg_date.setText(dateFormat.format(c.getTime()));
                passenger_reg_time.setText(timeFormat.format(c.getTime()));

                //Vendor Dashboard
                vendor_date.setText(dateFormat.format(c.getTime()));
                vendor_time.setText(timeFormat.format(c.getTime()));
                //Vendor Register Frame
                vendor_reg_date.setText(dateFormat.format(c.getTime()));
                vendor_reg_time.setText(timeFormat.format(c.getTime()));

                //Admin
                admin_date.setText(dateFormat.format(c.getTime()));
                admin_time.setText(timeFormat.format(c.getTime()));
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        driver_registration = new javax.swing.JFrame();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        driver_panel1 = new javax.swing.JPanel();
        background1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        driver_reg_firstname = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        driver_reg_middlename = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        driver_reg_lastname = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        driver_reg_gender = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        driver_reg_username = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        driver_reg_address = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        driver_reg_password = new javax.swing.JTextField();
        driver_reg_bttn = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        driver_reg_phone = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        driver_reg_date = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        driver_reg_time = new javax.swing.JLabel();
        driver_reg_logout = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        driver_dashboard = new javax.swing.JFrame();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        driver_panel2 = new javax.swing.JPanel();
        background2 = new javax.swing.JPanel();
        left_panel2 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        driver_txt_id = new javax.swing.JLabel();
        std_photo_panel2 = new javax.swing.JPanel();
        driver_photo = new javax.swing.JLabel();
        driver_txt_firstname = new javax.swing.JLabel();
        driver_txt_middlename = new javax.swing.JLabel();
        driver_txt_phone = new javax.swing.JLabel();
        driver_txt_tricycle_id = new javax.swing.JLabel();
        driver_upload_photo = new javax.swing.JLabel();
        active_now_label2 = new javax.swing.JLabel();
        green_active_icon2 = new javax.swing.JLabel();
        std_course_year_label3 = new javax.swing.JLabel();
        driver_txt_lastname = new javax.swing.JLabel();
        driver_txt_address = new javax.swing.JLabel();
        right_panel2 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        pane_passengers = new javax.swing.JScrollPane();
        table_passengers = new javax.swing.JTable();
        pane_deliveries = new javax.swing.JScrollPane();
        table_deliveries = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        driver_date = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        driver_time = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        driver_logout = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        admin_dashboard = new javax.swing.JFrame();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        driver_panel3 = new javax.swing.JPanel();
        background3 = new javax.swing.JPanel();
        left_panel3 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        admin_txt_id = new javax.swing.JLabel();
        std_photo_panel3 = new javax.swing.JPanel();
        admin_photo = new javax.swing.JLabel();
        admin_txt_firstname = new javax.swing.JLabel();
        admin_txt_middlename = new javax.swing.JLabel();
        admin_txt_phone = new javax.swing.JLabel();
        admin_upload_photo = new javax.swing.JLabel();
        active_now_label3 = new javax.swing.JLabel();
        green_active_icon3 = new javax.swing.JLabel();
        admin_txt_lastname = new javax.swing.JLabel();
        admin_txt_address = new javax.swing.JLabel();
        right_panel3 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        pane_passengers1 = new javax.swing.JScrollPane();
        table_tricycles = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        franchises_table = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        admin_date = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        admin_time = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        admin_logout = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        tricycles_pop = new javax.swing.JPopupMenu();
        view_tricycle_record = new javax.swing.JMenuItem();
        viewTricycle = new javax.swing.JFrame();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        viewTricycle_panel = new javax.swing.JPanel();
        background4 = new javax.swing.JPanel();
        viewTricycle_update = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        viewTricycle_tricycle_id = new javax.swing.JLabel();
        viewTricycle_franchise_id = new javax.swing.JLabel();
        viewTricycle_driver_id = new javax.swing.JLabel();
        viewTricycle_tricycle_color = new javax.swing.JLabel();
        viewTricycle_tricycle_status = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        viewTricycle_back = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        passenger_registration = new javax.swing.JFrame();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        driver_panel4 = new javax.swing.JPanel();
        background5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        passenger_reg_firstname = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        passenger_reg_middlename = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        passenger_reg_lastname = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        passenger_reg_gender = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        passenger_reg_username = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        passenger_reg_address = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        passenger_reg_password = new javax.swing.JTextField();
        passenger_reg_bttn = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        passenger_reg_phone = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        passenger_reg_date = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        passenger_reg_time = new javax.swing.JLabel();
        passenger_reg_logout = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        passenger_dashboard = new javax.swing.JFrame();
        jLayeredPane8 = new javax.swing.JLayeredPane();
        driver_panel5 = new javax.swing.JPanel();
        background6 = new javax.swing.JPanel();
        left_panel4 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        passenger_txt_id = new javax.swing.JLabel();
        std_photo_panel4 = new javax.swing.JPanel();
        passenger_photo = new javax.swing.JLabel();
        passenger_txt_firstname = new javax.swing.JLabel();
        passenger_txt_middlename = new javax.swing.JLabel();
        passenger_txt_phone = new javax.swing.JLabel();
        passenger_upload_photo = new javax.swing.JLabel();
        active_now_label4 = new javax.swing.JLabel();
        green_active_icon4 = new javax.swing.JLabel();
        passenger_txt_lastname = new javax.swing.JLabel();
        passenger_txt_address = new javax.swing.JLabel();
        right_panel4 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        passenger_start = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        passenger_end = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        passenger_go = new javax.swing.JButton();
        passenger_cancel = new javax.swing.JButton();
        passenger_destination_details = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        passenger_weather = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        passenger_traffic = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        passenger_distance = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        passenger_est_time = new javax.swing.JLabel();
        passenger_driver_details = new javax.swing.JPanel();
        passenger_driver_name = new javax.swing.JLabel();
        passenger_tricycle_number = new javax.swing.JLabel();
        passenger_driver_name_field = new javax.swing.JLabel();
        passenger_tricycle_number_field = new javax.swing.JLabel();
        passenger_driver_loading = new javax.swing.JLabel();
        passenger_payment_details = new javax.swing.JPanel();
        passenger_payment = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        bttn_view_stores = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        passenger_orders = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        passenger_date = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        passenger_time = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        passenger_logout = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        driver_found = new javax.swing.JFrame();
        jLayeredPane9 = new javax.swing.JLayeredPane();
        driver_found_panel = new javax.swing.JPanel();
        background7 = new javax.swing.JPanel();
        driver_found_driver_photo = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        driver_found_driver_name = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        driver_found_tricycle_number = new javax.swing.JLabel();
        driver_found_bttn_ok = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        viewStores = new javax.swing.JFrame();
        jLayeredPane11 = new javax.swing.JLayeredPane();
        viewTricycle_panel2 = new javax.swing.JPanel();
        background9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        passenger_store_table = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        viewStore_back = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        store_pop = new javax.swing.JPopupMenu();
        view_products = new javax.swing.JMenuItem();
        viewProducts = new javax.swing.JFrame();
        jLayeredPane14 = new javax.swing.JLayeredPane();
        driver_panel6 = new javax.swing.JPanel();
        background12 = new javax.swing.JPanel();
        product_details = new javax.swing.JPanel();
        std_photo_panel5 = new javax.swing.JPanel();
        product_photo = new javax.swing.JLabel();
        product_name = new javax.swing.JLabel();
        product_type = new javax.swing.JLabel();
        product_price = new javax.swing.JLabel();
        right_panel5 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewProducts_table = new javax.swing.JTable();
        jLabel71 = new javax.swing.JLabel();
        add_to_cart = new javax.swing.JPanel();
        product_quantity = new javax.swing.JSpinner();
        jLabel70 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        product_total = new javax.swing.JLabel();
        add_to_cart_bttn = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        viewProducts_store_name = new javax.swing.JLabel();
        viewProducts_back = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JSeparator();
        my_cart_txt = new javax.swing.JLabel();
        myCart_count = new javax.swing.JLabel();
        viewCart = new javax.swing.JFrame();
        jLayeredPane12 = new javax.swing.JLayeredPane();
        viewTricycle_panel3 = new javax.swing.JPanel();
        background10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        viewCart_table = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        viewCart_take_out = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        viewCart_total = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        viewCart_store_name = new javax.swing.JLabel();
        myCart_back = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        remove_product = new javax.swing.JPopupMenu();
        viewCart_remove = new javax.swing.JMenuItem();
        vendor_registration = new javax.swing.JFrame();
        jLayeredPane10 = new javax.swing.JLayeredPane();
        driver_panel7 = new javax.swing.JPanel();
        background8 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        vendor_reg_firstname = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        vendor_reg_middlename = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        vendor_reg_lastname = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        vendor_reg_gender = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        vendor_reg_username = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        vendor_reg_address = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        vendor_reg_password = new javax.swing.JTextField();
        vendor_reg_bttn = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        vendor_reg_phone = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        vendor_reg_date = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        vendor_reg_time = new javax.swing.JLabel();
        vendor_reg_logout = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        vendor_dashboard = new javax.swing.JFrame();
        jLayeredPane13 = new javax.swing.JLayeredPane();
        driver_panel8 = new javax.swing.JPanel();
        background11 = new javax.swing.JPanel();
        left_panel5 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        vendor_txt_id = new javax.swing.JLabel();
        std_photo_panel6 = new javax.swing.JPanel();
        vendor_photo = new javax.swing.JLabel();
        vendor_txt_firstname = new javax.swing.JLabel();
        vendor_txt_middlename = new javax.swing.JLabel();
        vendor_txt_phone = new javax.swing.JLabel();
        vendor_upload_photo = new javax.swing.JLabel();
        active_now_label5 = new javax.swing.JLabel();
        green_active_icon5 = new javax.swing.JLabel();
        vendor_txt_lastname = new javax.swing.JLabel();
        vendor_txt_address = new javax.swing.JLabel();
        right_panel6 = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel21 = new javax.swing.JPanel();
        bttn_add_product = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        vendor_products = new javax.swing.JTable();
        my_products_txt = new javax.swing.JLabel();
        store_name_txt = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        vendor_date = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        vendor_time = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        vendor_logout = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        product_registration = new javax.swing.JFrame();
        jLayeredPane15 = new javax.swing.JLayeredPane();
        viewTricycle_panel4 = new javax.swing.JPanel();
        background13 = new javax.swing.JPanel();
        product_reg_bttn = new javax.swing.JButton();
        jLabel83 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        product_reg_name = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        product_reg_type = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        product_reg_price = new javax.swing.JTextField();
        std_photo_panel7 = new javax.swing.JPanel();
        product_reg_photo = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        viewCart_store_name1 = new javax.swing.JLabel();
        product_reg_back = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JSeparator();
        store_registration = new javax.swing.JFrame();
        jLayeredPane16 = new javax.swing.JLayeredPane();
        viewTricycle_panel5 = new javax.swing.JPanel();
        background14 = new javax.swing.JPanel();
        product_reg_bttn1 = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        store_reg_name = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        store_reg_address = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        store_reg_phone = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        viewCart_store_name2 = new javax.swing.JLabel();
        store_reg_back = new javax.swing.JButton();
        jSeparator14 = new javax.swing.JSeparator();
        addFranchise = new javax.swing.JFrame();
        jLayeredPane17 = new javax.swing.JLayeredPane();
        viewTricycle_panel6 = new javax.swing.JPanel();
        background15 = new javax.swing.JPanel();
        registerFranchise_tricycle = new javax.swing.JButton();
        addFranchise_tricycle_id = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        addFranchise_reg_start = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        addFranchise_reg_end = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        viewCart_store_name3 = new javax.swing.JLabel();
        setFranchise_back = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JSeparator();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        panel_login = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        username_panel = new javax.swing.JPanel();
        login_txt_username = new javax.swing.JTextField();
        password_panel = new javax.swing.JPanel();
        login_txt_password = new javax.swing.JPasswordField();
        login_user = new javax.swing.JButton();
        login_new_account = new javax.swing.JLabel();

        driver_registration.setUndecorated(true);
        driver_registration.setResizable(false);

        jLayeredPane3.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        driver_panel1.setBackground(new java.awt.Color(255, 255, 255));
        driver_panel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        driver_panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background1.setBackground(new java.awt.Color(93, 25, 50));
        background1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Firstname");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Middlename");

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Lastname");

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Gender");

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Username");

        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Address");

        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Password");

        driver_reg_bttn.setBackground(new java.awt.Color(255, 255, 255));
        driver_reg_bttn.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        driver_reg_bttn.setForeground(new java.awt.Color(204, 0, 51));
        driver_reg_bttn.setText("Register Account");
        driver_reg_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                driver_reg_bttnMouseReleased(evt);
            }
        });

        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Phone Number");

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(driver_reg_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(driver_reg_middlename, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(driver_reg_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(driver_reg_username, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(driver_reg_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(driver_reg_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel37)
                    .addComponent(driver_reg_password, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jLabel31)
                    .addComponent(driver_reg_address, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(driver_reg_bttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(89, 89, 89))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(driver_reg_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(driver_reg_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background1Layout.createSequentialGroup()
                            .addComponent(jLabel24)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(driver_reg_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel38)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(driver_reg_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(driver_reg_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background1Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(driver_reg_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(driver_reg_middlename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(driver_reg_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(driver_reg_bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        driver_panel1.add(background1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 960, 340));

        jPanel4.setBackground(new java.awt.Color(93, 25, 50));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Driver Registration Form");
        jPanel4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 520, 50));

        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Date :");
        jPanel4.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, -1, -1));

        driver_reg_date.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        driver_reg_date.setForeground(new java.awt.Color(255, 255, 255));
        driver_reg_date.setText("-----");
        jPanel4.add(driver_reg_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 150, -1));

        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Time :");
        jPanel4.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        driver_reg_time.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        driver_reg_time.setForeground(new java.awt.Color(255, 255, 255));
        driver_reg_time.setText("-----");
        jPanel4.add(driver_reg_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 150, -1));

        driver_panel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 70));

        driver_reg_logout.setBackground(new java.awt.Color(255, 255, 255));
        driver_reg_logout.setText("Back");
        driver_reg_logout.setFocusable(false);
        driver_reg_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                driver_reg_logoutMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                driver_reg_logoutMouseReleased(evt);
            }
        });
        driver_panel1.add(driver_reg_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 70, 30));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        driver_panel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 30, 30));

        jLayeredPane3.add(driver_panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 460));

        javax.swing.GroupLayout driver_registrationLayout = new javax.swing.GroupLayout(driver_registration.getContentPane());
        driver_registration.getContentPane().setLayout(driver_registrationLayout);
        driver_registrationLayout.setHorizontalGroup(
            driver_registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane3)
        );
        driver_registrationLayout.setVerticalGroup(
            driver_registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane3)
        );

        driver_dashboard.setUndecorated(true);
        driver_dashboard.setResizable(false);

        jLayeredPane4.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        driver_panel2.setBackground(new java.awt.Color(255, 255, 255));
        driver_panel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        driver_panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background2.setBackground(new java.awt.Color(93, 25, 50));
        background2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        left_panel2.setBackground(new java.awt.Color(93, 25, 50));
        left_panel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "DRIVER", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        left_panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("ID NUMBER :");
        left_panel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 42, -1, -1));

        driver_txt_id.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        driver_txt_id.setForeground(new java.awt.Color(255, 255, 255));
        driver_txt_id.setText("-----");
        left_panel2.add(driver_txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 40, 210, -1));

        std_photo_panel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        std_photo_panel2.setPreferredSize(new java.awt.Dimension(130, 130));
        std_photo_panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        driver_photo.setText("             NO IMAGE");
        std_photo_panel2.add(driver_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 140));

        left_panel2.add(std_photo_panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 77, -1, 140));

        driver_txt_firstname.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        driver_txt_firstname.setForeground(new java.awt.Color(255, 255, 255));
        driver_txt_firstname.setText("Name");
        left_panel2.add(driver_txt_firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 160, -1));

        driver_txt_middlename.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        driver_txt_middlename.setForeground(new java.awt.Color(255, 255, 255));
        driver_txt_middlename.setText("Middle Name");
        left_panel2.add(driver_txt_middlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 170, -1));

        driver_txt_phone.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        driver_txt_phone.setForeground(new java.awt.Color(255, 255, 255));
        driver_txt_phone.setText("Last Name");
        left_panel2.add(driver_txt_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 160, -1));

        driver_txt_tricycle_id.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        driver_txt_tricycle_id.setForeground(new java.awt.Color(255, 255, 255));
        driver_txt_tricycle_id.setText("----");
        left_panel2.add(driver_txt_tricycle_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 140, -1));

        driver_upload_photo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        driver_upload_photo.setForeground(new java.awt.Color(255, 102, 102));
        driver_upload_photo.setText("Add Photo");
        driver_upload_photo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        driver_upload_photo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                driver_upload_photoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                driver_upload_photoMouseReleased(evt);
            }
        });
        left_panel2.add(driver_upload_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 223, -1, -1));

        active_now_label2.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        active_now_label2.setForeground(new java.awt.Color(255, 255, 255));
        active_now_label2.setText("Active now");
        left_panel2.add(active_now_label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, 20));
        left_panel2.add(green_active_icon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 13, -1, 40));

        std_course_year_label3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        std_course_year_label3.setForeground(new java.awt.Color(255, 255, 255));
        std_course_year_label3.setText("Tricycle Number :");
        left_panel2.add(std_course_year_label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 140, -1));

        driver_txt_lastname.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        driver_txt_lastname.setForeground(new java.awt.Color(255, 255, 255));
        driver_txt_lastname.setText("Last Name");
        left_panel2.add(driver_txt_lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 160, -1));

        driver_txt_address.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        driver_txt_address.setForeground(new java.awt.Color(255, 255, 255));
        driver_txt_address.setText("Last Name");
        left_panel2.add(driver_txt_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 160, -1));

        right_panel2.setBackground(new java.awt.Color(93, 25, 50));

        javax.swing.GroupLayout right_panel2Layout = new javax.swing.GroupLayout(right_panel2);
        right_panel2.setLayout(right_panel2Layout);
        right_panel2Layout.setHorizontalGroup(
            right_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        right_panel2Layout.setVerticalGroup(
            right_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        jTabbedPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        pane_passengers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 25, 50), 2));

        table_passengers.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        table_passengers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Passenger ID", "Start", "End", "Charge", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_passengers.setFocusable(false);
        table_passengers.setGridColor(new java.awt.Color(255, 255, 255));
        table_passengers.setRowHeight(25);
        table_passengers.setSelectionBackground(new java.awt.Color(93, 25, 50));
        table_passengers.setSelectionForeground(new java.awt.Color(255, 255, 255));
        pane_passengers.setViewportView(table_passengers);
        if (table_passengers.getColumnModel().getColumnCount() > 0) {
            table_passengers.getColumnModel().getColumn(0).setResizable(false);
            table_passengers.getColumnModel().getColumn(1).setResizable(false);
            table_passengers.getColumnModel().getColumn(2).setResizable(false);
            table_passengers.getColumnModel().getColumn(3).setResizable(false);
            table_passengers.getColumnModel().getColumn(4).setResizable(false);
        }

        jTabbedPane3.addTab("Passengers", pane_passengers);

        pane_deliveries.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 25, 50), 2));

        table_deliveries.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        table_deliveries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Location", "Total Payment", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_deliveries.setFocusable(false);
        table_deliveries.setGridColor(new java.awt.Color(255, 255, 255));
        table_deliveries.setRowHeight(25);
        table_deliveries.setSelectionBackground(new java.awt.Color(93, 25, 50));
        table_deliveries.setSelectionForeground(new java.awt.Color(255, 255, 255));
        pane_deliveries.setViewportView(table_deliveries);
        if (table_deliveries.getColumnModel().getColumnCount() > 0) {
            table_deliveries.getColumnModel().getColumn(0).setResizable(false);
            table_deliveries.getColumnModel().getColumn(1).setResizable(false);
            table_deliveries.getColumnModel().getColumn(2).setResizable(false);
            table_deliveries.getColumnModel().getColumn(3).setResizable(false);
        }

        jTabbedPane3.addTab("Deliveries", pane_deliveries);

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(left_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(right_panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18))))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(left_panel2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(right_panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        driver_panel2.add(background2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 960, 340));

        jPanel5.setBackground(new java.awt.Color(93, 25, 50));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel47.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("TRICYCLE");
        jPanel5.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel48.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Dashboard");
        jPanel5.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 230, 50));

        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Date :");
        jPanel5.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, -1, -1));

        driver_date.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        driver_date.setForeground(new java.awt.Color(255, 255, 255));
        driver_date.setText("-----");
        jPanel5.add(driver_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 150, -1));

        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Time :");
        jPanel5.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        driver_time.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        driver_time.setForeground(new java.awt.Color(255, 255, 255));
        driver_time.setText("-----");
        jPanel5.add(driver_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 150, -1));

        jLabel51.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("App");
        jPanel5.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 90, 50));

        driver_panel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 70));

        driver_logout.setBackground(new java.awt.Color(255, 255, 255));
        driver_logout.setText("Logout");
        driver_logout.setFocusable(false);
        driver_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                driver_logoutMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                driver_logoutMouseReleased(evt);
            }
        });
        driver_panel2.add(driver_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 70, -1, 30));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        driver_panel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 30, 30));

        jLayeredPane4.add(driver_panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 460));

        javax.swing.GroupLayout driver_dashboardLayout = new javax.swing.GroupLayout(driver_dashboard.getContentPane());
        driver_dashboard.getContentPane().setLayout(driver_dashboardLayout);
        driver_dashboardLayout.setHorizontalGroup(
            driver_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane4)
        );
        driver_dashboardLayout.setVerticalGroup(
            driver_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane4)
        );

        admin_dashboard.setUndecorated(true);
        admin_dashboard.setResizable(false);

        jLayeredPane5.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        driver_panel3.setBackground(new java.awt.Color(255, 255, 255));
        driver_panel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        driver_panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background3.setBackground(new java.awt.Color(93, 25, 50));
        background3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        left_panel3.setBackground(new java.awt.Color(93, 25, 50));
        left_panel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "ADMIN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        left_panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("ID NUMBER :");
        left_panel3.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 42, -1, -1));

        admin_txt_id.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        admin_txt_id.setForeground(new java.awt.Color(255, 255, 255));
        admin_txt_id.setText("-----");
        left_panel3.add(admin_txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 40, 220, -1));

        std_photo_panel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        std_photo_panel3.setPreferredSize(new java.awt.Dimension(130, 130));
        std_photo_panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_photo.setText("             NO IMAGE");
        std_photo_panel3.add(admin_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 140));

        left_panel3.add(std_photo_panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 77, -1, 140));

        admin_txt_firstname.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        admin_txt_firstname.setForeground(new java.awt.Color(255, 255, 255));
        admin_txt_firstname.setText("Name");
        left_panel3.add(admin_txt_firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 160, -1));

        admin_txt_middlename.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        admin_txt_middlename.setForeground(new java.awt.Color(255, 255, 255));
        admin_txt_middlename.setText("Middlename");
        left_panel3.add(admin_txt_middlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 170, -1));

        admin_txt_phone.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        admin_txt_phone.setForeground(new java.awt.Color(255, 255, 255));
        admin_txt_phone.setText("Phone Number");
        left_panel3.add(admin_txt_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 160, -1));

        admin_upload_photo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        admin_upload_photo.setForeground(new java.awt.Color(255, 102, 102));
        admin_upload_photo.setText("Add Photo");
        admin_upload_photo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        admin_upload_photo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                admin_upload_photoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                admin_upload_photoMouseReleased(evt);
            }
        });
        left_panel3.add(admin_upload_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 223, -1, -1));

        active_now_label3.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        active_now_label3.setForeground(new java.awt.Color(255, 255, 255));
        active_now_label3.setText("Active now");
        left_panel3.add(active_now_label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, 20));
        left_panel3.add(green_active_icon3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 13, -1, 40));

        admin_txt_lastname.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        admin_txt_lastname.setForeground(new java.awt.Color(255, 255, 255));
        admin_txt_lastname.setText("Last Name");
        left_panel3.add(admin_txt_lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 160, -1));

        admin_txt_address.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        admin_txt_address.setForeground(new java.awt.Color(255, 255, 255));
        admin_txt_address.setText("Home address");
        left_panel3.add(admin_txt_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 160, -1));

        right_panel3.setBackground(new java.awt.Color(93, 25, 50));

        javax.swing.GroupLayout right_panel3Layout = new javax.swing.GroupLayout(right_panel3);
        right_panel3.setLayout(right_panel3Layout);
        right_panel3Layout.setHorizontalGroup(
            right_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        right_panel3Layout.setVerticalGroup(
            right_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        jTabbedPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        pane_passengers1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 25, 50), 2));

        table_tricycles.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        table_tricycles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tricycle ID", "Franchise ID", "Driver ID", "Color", "Status", "Penalties"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_tricycles.setFocusable(false);
        table_tricycles.setGridColor(new java.awt.Color(255, 255, 255));
        table_tricycles.setRowHeight(25);
        table_tricycles.setSelectionBackground(new java.awt.Color(93, 25, 50));
        table_tricycles.setSelectionForeground(new java.awt.Color(255, 255, 255));
        table_tricycles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_tricyclesMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                table_tricyclesMouseReleased(evt);
            }
        });
        pane_passengers1.setViewportView(table_tricycles);
        if (table_tricycles.getColumnModel().getColumnCount() > 0) {
            table_tricycles.getColumnModel().getColumn(0).setResizable(false);
            table_tricycles.getColumnModel().getColumn(1).setResizable(false);
            table_tricycles.getColumnModel().getColumn(2).setResizable(false);
            table_tricycles.getColumnModel().getColumn(3).setResizable(false);
            table_tricycles.getColumnModel().getColumn(4).setResizable(false);
            table_tricycles.getColumnModel().getColumn(5).setResizable(false);
        }

        jTabbedPane4.addTab("Tricycles", pane_passengers1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        jButton1.setText("Add New");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jTabbedPane4.addTab("Penalties", jPanel20);

        franchises_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tricycle ID", "Date registered", "Expiration"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(franchises_table);
        if (franchises_table.getColumnModel().getColumnCount() > 0) {
            franchises_table.getColumnModel().getColumn(0).setResizable(false);
            franchises_table.getColumnModel().getColumn(1).setResizable(false);
            franchises_table.getColumnModel().getColumn(2).setResizable(false);
        }

        jButton2.setText("Register Tricycle");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jTabbedPane4.addTab("Franchises", jPanel19);

        javax.swing.GroupLayout background3Layout = new javax.swing.GroupLayout(background3);
        background3.setLayout(background3Layout);
        background3Layout.setHorizontalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(left_panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background3Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(right_panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(background3Layout.createSequentialGroup()
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18))))
        );
        background3Layout.setVerticalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(left_panel3, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(right_panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        driver_panel3.add(background3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 960, 340));

        jPanel6.setBackground(new java.awt.Color(93, 25, 50));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel52.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("TRICYCLE");
        jPanel6.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel53.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Dashboard");
        jPanel6.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 230, 50));

        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Date :");
        jPanel6.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, -1, -1));

        admin_date.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        admin_date.setForeground(new java.awt.Color(255, 255, 255));
        admin_date.setText("-----");
        jPanel6.add(admin_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 150, -1));

        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Time :");
        jPanel6.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        admin_time.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        admin_time.setForeground(new java.awt.Color(255, 255, 255));
        admin_time.setText("-----");
        jPanel6.add(admin_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 150, -1));

        jLabel56.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("App");
        jPanel6.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 90, 50));

        driver_panel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 70));

        admin_logout.setBackground(new java.awt.Color(255, 255, 255));
        admin_logout.setText("Logout");
        admin_logout.setFocusable(false);
        admin_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                admin_logoutMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                admin_logoutMouseReleased(evt);
            }
        });
        driver_panel3.add(admin_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 70, -1, 30));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        driver_panel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 30, 30));

        jLayeredPane5.add(driver_panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 460));

        javax.swing.GroupLayout admin_dashboardLayout = new javax.swing.GroupLayout(admin_dashboard.getContentPane());
        admin_dashboard.getContentPane().setLayout(admin_dashboardLayout);
        admin_dashboardLayout.setHorizontalGroup(
            admin_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane5)
        );
        admin_dashboardLayout.setVerticalGroup(
            admin_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane5)
        );

        view_tricycle_record.setText("Edit");
        view_tricycle_record.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_tricycle_recordActionPerformed(evt);
            }
        });
        tricycles_pop.add(view_tricycle_record);

        viewTricycle.setUndecorated(true);
        viewTricycle.setResizable(false);

        jLayeredPane6.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewTricycle_panel.setBackground(new java.awt.Color(255, 255, 255));
        viewTricycle_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        viewTricycle_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background4.setBackground(new java.awt.Color(93, 25, 50));
        background4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        viewTricycle_update.setBackground(new java.awt.Color(255, 255, 255));
        viewTricycle_update.setForeground(new java.awt.Color(204, 0, 51));
        viewTricycle_update.setText("Update Record");
        viewTricycle_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                viewTricycle_updateMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                viewTricycle_updateMouseReleased(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Tricycle Number:");

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Franchise Number:");

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Tricycle Color");

        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("Tricycle Status");

        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("Driver ID:");

        viewTricycle_tricycle_id.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        viewTricycle_tricycle_id.setForeground(new java.awt.Color(255, 255, 255));
        viewTricycle_tricycle_id.setText("Tricycle number");

        viewTricycle_franchise_id.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        viewTricycle_franchise_id.setForeground(new java.awt.Color(255, 255, 255));
        viewTricycle_franchise_id.setText("Franchise number");

        viewTricycle_driver_id.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        viewTricycle_driver_id.setForeground(new java.awt.Color(255, 255, 255));
        viewTricycle_driver_id.setText("Tricycle driver ID");

        viewTricycle_tricycle_color.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        viewTricycle_tricycle_color.setForeground(new java.awt.Color(255, 255, 255));
        viewTricycle_tricycle_color.setText("Tricycle color");

        viewTricycle_tricycle_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enable", "Disable" }));

        javax.swing.GroupLayout background4Layout = new javax.swing.GroupLayout(background4);
        background4.setLayout(background4Layout);
        background4Layout.setHorizontalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background4Layout.createSequentialGroup()
                .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(background4Layout.createSequentialGroup()
                        .addGap(381, 381, 381)
                        .addComponent(viewTricycle_update))
                    .addGroup(background4Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel12)
                            .addComponent(viewTricycle_tricycle_id, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewTricycle_franchise_id, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewTricycle_driver_id, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11)
                            .addComponent(viewTricycle_tricycle_color, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(viewTricycle_tricycle_status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        background4Layout.setVerticalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background4Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(9, 9, 9)
                        .addComponent(viewTricycle_tricycle_color)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewTricycle_tricycle_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                        .addComponent(viewTricycle_update)
                        .addGap(27, 27, 27))
                    .addGroup(background4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addComponent(viewTricycle_tricycle_id)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(9, 9, 9)
                        .addComponent(viewTricycle_franchise_id)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewTricycle_driver_id)
                        .addGap(212, 212, 212))))
        );

        viewTricycle_panel.add(background4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 530, 440));

        jPanel7.setBackground(new java.awt.Color(93, 25, 50));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Tricycle Record");
        jPanel7.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 270, 50));

        viewTricycle_panel.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 70));

        viewTricycle_back.setBackground(new java.awt.Color(255, 255, 255));
        viewTricycle_back.setText("Back");
        viewTricycle_back.setFocusable(false);
        viewTricycle_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                viewTricycle_backMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                viewTricycle_backMouseReleased(evt);
            }
        });
        viewTricycle_panel.add(viewTricycle_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 70, 30));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        viewTricycle_panel.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 30, 30));

        jLayeredPane6.add(viewTricycle_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 560));

        javax.swing.GroupLayout viewTricycleLayout = new javax.swing.GroupLayout(viewTricycle.getContentPane());
        viewTricycle.getContentPane().setLayout(viewTricycleLayout);
        viewTricycleLayout.setHorizontalGroup(
            viewTricycleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane6)
        );
        viewTricycleLayout.setVerticalGroup(
            viewTricycleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane6)
        );

        jLayeredPane7.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        driver_panel4.setBackground(new java.awt.Color(255, 255, 255));
        driver_panel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        driver_panel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background5.setBackground(new java.awt.Color(93, 25, 50));
        background5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Firstname");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Middlename");

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Lastname");

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Gender");

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Username");

        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Address");

        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Password");

        passenger_reg_bttn.setBackground(new java.awt.Color(255, 255, 255));
        passenger_reg_bttn.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        passenger_reg_bttn.setForeground(new java.awt.Color(204, 0, 51));
        passenger_reg_bttn.setText("Register Account");
        passenger_reg_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                passenger_reg_bttnMouseReleased(evt);
            }
        });

        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Phone Number");

        javax.swing.GroupLayout background5Layout = new javax.swing.GroupLayout(background5);
        background5.setLayout(background5Layout);
        background5Layout.setHorizontalGroup(
            background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background5Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(passenger_reg_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(passenger_reg_middlename, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(passenger_reg_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(passenger_reg_username, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(passenger_reg_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(passenger_reg_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel41)
                    .addComponent(passenger_reg_password, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jLabel40)
                    .addComponent(passenger_reg_address, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(passenger_reg_bttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(89, 89, 89))
        );
        background5Layout.setVerticalGroup(
            background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background5Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passenger_reg_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passenger_reg_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background5Layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(passenger_reg_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel42)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(passenger_reg_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(passenger_reg_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background5Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(passenger_reg_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(passenger_reg_middlename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(passenger_reg_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(passenger_reg_bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        driver_panel4.add(background5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 960, 340));

        jPanel8.setBackground(new java.awt.Color(93, 25, 50));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Passenger Registration Form");
        jPanel8.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 550, 50));

        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Date :");
        jPanel8.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, -1, -1));

        passenger_reg_date.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        passenger_reg_date.setForeground(new java.awt.Color(255, 255, 255));
        passenger_reg_date.setText("-----");
        jPanel8.add(passenger_reg_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 150, -1));

        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Time :");
        jPanel8.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        passenger_reg_time.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        passenger_reg_time.setForeground(new java.awt.Color(255, 255, 255));
        passenger_reg_time.setText("-----");
        jPanel8.add(passenger_reg_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 150, -1));

        driver_panel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 70));

        passenger_reg_logout.setBackground(new java.awt.Color(255, 255, 255));
        passenger_reg_logout.setText("Back");
        passenger_reg_logout.setFocusable(false);
        passenger_reg_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passenger_reg_logoutMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                passenger_reg_logoutMouseReleased(evt);
            }
        });
        driver_panel4.add(passenger_reg_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 70, 30));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        driver_panel4.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 30, 30));

        jLayeredPane7.add(driver_panel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 460));

        javax.swing.GroupLayout passenger_registrationLayout = new javax.swing.GroupLayout(passenger_registration.getContentPane());
        passenger_registration.getContentPane().setLayout(passenger_registrationLayout);
        passenger_registrationLayout.setHorizontalGroup(
            passenger_registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane7)
        );
        passenger_registrationLayout.setVerticalGroup(
            passenger_registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane7)
        );

        passenger_dashboard.setUndecorated(true);
        passenger_dashboard.setResizable(false);

        jLayeredPane8.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        driver_panel5.setBackground(new java.awt.Color(255, 255, 255));
        driver_panel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        driver_panel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background6.setBackground(new java.awt.Color(93, 25, 50));
        background6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        left_panel4.setBackground(new java.awt.Color(93, 25, 50));
        left_panel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "PASSENGER", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        left_panel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("ID NUMBER :");
        left_panel4.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 42, -1, -1));

        passenger_txt_id.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        passenger_txt_id.setForeground(new java.awt.Color(255, 255, 255));
        passenger_txt_id.setText("-----");
        left_panel4.add(passenger_txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 40, 210, -1));

        std_photo_panel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        std_photo_panel4.setPreferredSize(new java.awt.Dimension(130, 130));
        std_photo_panel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        passenger_photo.setText("             NO IMAGE");
        std_photo_panel4.add(passenger_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 140));

        left_panel4.add(std_photo_panel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 77, -1, 140));

        passenger_txt_firstname.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        passenger_txt_firstname.setForeground(new java.awt.Color(255, 255, 255));
        passenger_txt_firstname.setText("Name");
        left_panel4.add(passenger_txt_firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 160, -1));

        passenger_txt_middlename.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        passenger_txt_middlename.setForeground(new java.awt.Color(255, 255, 255));
        passenger_txt_middlename.setText("Middle Name");
        left_panel4.add(passenger_txt_middlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 170, -1));

        passenger_txt_phone.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        passenger_txt_phone.setForeground(new java.awt.Color(255, 255, 255));
        passenger_txt_phone.setText("Last Name");
        left_panel4.add(passenger_txt_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 160, -1));

        passenger_upload_photo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        passenger_upload_photo.setForeground(new java.awt.Color(255, 102, 102));
        passenger_upload_photo.setText("Add Photo");
        passenger_upload_photo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        passenger_upload_photo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passenger_upload_photoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                passenger_upload_photoMouseReleased(evt);
            }
        });
        left_panel4.add(passenger_upload_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 223, -1, -1));

        active_now_label4.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        active_now_label4.setForeground(new java.awt.Color(255, 255, 255));
        active_now_label4.setText("Active now");
        left_panel4.add(active_now_label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, 20));
        left_panel4.add(green_active_icon4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 13, -1, 40));

        passenger_txt_lastname.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        passenger_txt_lastname.setForeground(new java.awt.Color(255, 255, 255));
        passenger_txt_lastname.setText("Last Name");
        left_panel4.add(passenger_txt_lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 160, -1));

        passenger_txt_address.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        passenger_txt_address.setForeground(new java.awt.Color(255, 255, 255));
        passenger_txt_address.setText("Last Name");
        left_panel4.add(passenger_txt_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 160, -1));

        right_panel4.setBackground(new java.awt.Color(93, 25, 50));

        javax.swing.GroupLayout right_panel4Layout = new javax.swing.GroupLayout(right_panel4);
        right_panel4.setLayout(right_panel4Layout);
        right_panel4Layout.setHorizontalGroup(
            right_panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        right_panel4Layout.setVerticalGroup(
            right_panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Destination", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel7.setText("Start");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("End");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/materials/location_blue.png"))); // NOI18N
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 50, 60));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/materials/location_red.png"))); // NOI18N
        jPanel10.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 50, 60));

        passenger_go.setBackground(new java.awt.Color(0, 255, 0));
        passenger_go.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        passenger_go.setForeground(new java.awt.Color(255, 255, 255));
        passenger_go.setText("Go");
        passenger_go.setFocusPainted(false);
        passenger_go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_goActionPerformed(evt);
            }
        });

        passenger_cancel.setBackground(new java.awt.Color(255, 51, 51));
        passenger_cancel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        passenger_cancel.setForeground(new java.awt.Color(255, 255, 255));
        passenger_cancel.setText("Cancel");
        passenger_cancel.setFocusPainted(false);
        passenger_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(passenger_go, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passenger_start, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passenger_end, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(passenger_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passenger_start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passenger_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passenger_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(passenger_go, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        passenger_destination_details.setBackground(new java.awt.Color(255, 255, 255));
        passenger_destination_details.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Destination Details"));

        jLabel15.setText("Weather Condition : ");

        passenger_weather.setForeground(new java.awt.Color(0, 0, 0));
        passenger_weather.setText("----");

        jLabel17.setText("Traffic Condition :");

        passenger_traffic.setForeground(new java.awt.Color(0, 0, 0));
        passenger_traffic.setText("----");

        jLabel21.setText("Distance :");

        passenger_distance.setForeground(new java.awt.Color(0, 0, 0));
        passenger_distance.setText("----");

        jLabel23.setText("Estimated time :");

        passenger_est_time.setForeground(new java.awt.Color(0, 0, 0));
        passenger_est_time.setText("----");

        javax.swing.GroupLayout passenger_destination_detailsLayout = new javax.swing.GroupLayout(passenger_destination_details);
        passenger_destination_details.setLayout(passenger_destination_detailsLayout);
        passenger_destination_detailsLayout.setHorizontalGroup(
            passenger_destination_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passenger_destination_detailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(passenger_destination_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(passenger_destination_detailsLayout.createSequentialGroup()
                        .addGroup(passenger_destination_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passenger_weather, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(passenger_destination_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passenger_distance, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(passenger_destination_detailsLayout.createSequentialGroup()
                        .addGroup(passenger_destination_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passenger_traffic, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(passenger_destination_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passenger_est_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        passenger_destination_detailsLayout.setVerticalGroup(
            passenger_destination_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passenger_destination_detailsLayout.createSequentialGroup()
                .addGroup(passenger_destination_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(passenger_destination_detailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passenger_weather))
                    .addGroup(passenger_destination_detailsLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passenger_distance)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(passenger_destination_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(passenger_destination_detailsLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passenger_traffic))
                    .addGroup(passenger_destination_detailsLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passenger_est_time)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        passenger_driver_details.setBackground(new java.awt.Color(255, 255, 255));
        passenger_driver_details.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Driver Details"));
        passenger_driver_details.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        passenger_driver_name.setText("Driver name :");
        passenger_driver_details.add(passenger_driver_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 18, 98, -1));

        passenger_tricycle_number.setText("Tricycle number :");
        passenger_driver_details.add(passenger_tricycle_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 30));

        passenger_driver_name_field.setForeground(new java.awt.Color(0, 0, 0));
        passenger_driver_name_field.setText("----");
        passenger_driver_details.add(passenger_driver_name_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 18, 136, -1));

        passenger_tricycle_number_field.setForeground(new java.awt.Color(0, 0, 0));
        passenger_tricycle_number_field.setText("----");
        passenger_driver_details.add(passenger_tricycle_number_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 31, 136, 30));

        passenger_driver_loading.setForeground(new java.awt.Color(0, 0, 0));
        passenger_driver_loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/materials/loading_icon.gif"))); // NOI18N
        passenger_driver_details.add(passenger_driver_loading, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 11, 50, 50));

        passenger_payment_details.setBackground(new java.awt.Color(255, 255, 255));
        passenger_payment_details.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Payment Details"));

        passenger_payment.setForeground(new java.awt.Color(0, 0, 0));
        passenger_payment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passenger_payment.setText("----");

        javax.swing.GroupLayout passenger_payment_detailsLayout = new javax.swing.GroupLayout(passenger_payment_details);
        passenger_payment_details.setLayout(passenger_payment_detailsLayout);
        passenger_payment_detailsLayout.setHorizontalGroup(
            passenger_payment_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passenger_payment_detailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(passenger_payment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        passenger_payment_detailsLayout.setVerticalGroup(
            passenger_payment_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(passenger_payment, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(passenger_destination_details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passenger_driver_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passenger_payment_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passenger_destination_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(passenger_payment_details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passenger_driver_details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane5.addTab("Set a trip", jPanel1);

        bttn_view_stores.setText("View Stores");
        bttn_view_stores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_view_storesActionPerformed(evt);
            }
        });

        passenger_orders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Store", "No. of Items", "Order Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(passenger_orders);
        if (passenger_orders.getColumnModel().getColumnCount() > 0) {
            passenger_orders.getColumnModel().getColumn(0).setResizable(false);
            passenger_orders.getColumnModel().getColumn(1).setResizable(false);
            passenger_orders.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel16.setText("My Orders");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bttn_view_stores)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bttn_view_stores)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Pabili", jPanel12);

        javax.swing.GroupLayout background6Layout = new javax.swing.GroupLayout(background6);
        background6.setLayout(background6Layout);
        background6Layout.setHorizontalGroup(
            background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(left_panel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background6Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(right_panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(background6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane5)))
                .addGap(15, 15, 15))
        );
        background6Layout.setVerticalGroup(
            background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(left_panel4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(right_panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        driver_panel5.add(background6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 960, 340));

        jPanel9.setBackground(new java.awt.Color(93, 25, 50));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel58.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("TRICYCLE");
        jPanel9.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel59.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Dashboard");
        jPanel9.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 230, 50));

        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("Date :");
        jPanel9.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, -1, -1));

        passenger_date.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        passenger_date.setForeground(new java.awt.Color(255, 255, 255));
        passenger_date.setText("-----");
        jPanel9.add(passenger_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 150, -1));

        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("Time :");
        jPanel9.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        passenger_time.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        passenger_time.setForeground(new java.awt.Color(255, 255, 255));
        passenger_time.setText("-----");
        jPanel9.add(passenger_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 150, -1));

        jLabel62.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("App");
        jPanel9.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 90, 50));

        driver_panel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 70));

        passenger_logout.setBackground(new java.awt.Color(255, 255, 255));
        passenger_logout.setText("Logout");
        passenger_logout.setFocusable(false);
        passenger_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passenger_logoutMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                passenger_logoutMouseReleased(evt);
            }
        });
        driver_panel5.add(passenger_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 70, -1, 30));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        driver_panel5.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 30, 30));

        jLayeredPane8.add(driver_panel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 460));

        javax.swing.GroupLayout passenger_dashboardLayout = new javax.swing.GroupLayout(passenger_dashboard.getContentPane());
        passenger_dashboard.getContentPane().setLayout(passenger_dashboardLayout);
        passenger_dashboardLayout.setHorizontalGroup(
            passenger_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane8)
        );
        passenger_dashboardLayout.setVerticalGroup(
            passenger_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane8)
        );

        driver_found.setUndecorated(true);
        driver_found.setResizable(false);

        jLayeredPane9.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        driver_found_panel.setBackground(new java.awt.Color(255, 255, 255));
        driver_found_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        driver_found_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background7.setBackground(new java.awt.Color(93, 25, 50));
        background7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        driver_found_driver_photo.setText("jLabel16");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Congratulations!");

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("We found you a driver!");

        driver_found_driver_name.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        driver_found_driver_name.setForeground(new java.awt.Color(255, 255, 255));
        driver_found_driver_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driver_found_driver_name.setText("Firstname Middlename Lastname");

        jLabel64.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("Tricycle Number: ");

        driver_found_tricycle_number.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        driver_found_tricycle_number.setForeground(new java.awt.Color(255, 255, 255));
        driver_found_tricycle_number.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        driver_found_tricycle_number.setText("-----");

        driver_found_bttn_ok.setText("OK");
        driver_found_bttn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driver_found_bttn_okActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background7Layout = new javax.swing.GroupLayout(background7);
        background7.setLayout(background7Layout);
        background7Layout.setHorizontalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background7Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(driver_found_tricycle_number, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background7Layout.createSequentialGroup()
                        .addComponent(driver_found_driver_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background7Layout.createSequentialGroup()
                        .addComponent(driver_found_bttn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
            .addGroup(background7Layout.createSequentialGroup()
                .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addGroup(background7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(driver_found_driver_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(background7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        background7Layout.setVerticalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background7Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(driver_found_driver_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(driver_found_driver_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(driver_found_tricycle_number))
                .addGap(55, 55, 55)
                .addComponent(driver_found_bttn_ok)
                .addGap(20, 20, 20))
            .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(background7Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(jLabel22)
                    .addContainerGap(344, Short.MAX_VALUE)))
        );

        driver_found_panel.add(background7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 400, 400));

        jPanel15.setBackground(new java.awt.Color(93, 25, 50));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel68.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("TRICYCLE");
        jPanel15.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        jLabel72.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("App");
        jPanel15.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 90, 50));

        driver_found_panel.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 70));

        jLayeredPane9.add(driver_found_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 480));

        javax.swing.GroupLayout driver_foundLayout = new javax.swing.GroupLayout(driver_found.getContentPane());
        driver_found.getContentPane().setLayout(driver_foundLayout);
        driver_foundLayout.setHorizontalGroup(
            driver_foundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane9)
        );
        driver_foundLayout.setVerticalGroup(
            driver_foundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane9)
        );

        viewStores.setUndecorated(true);
        viewStores.setResizable(false);

        jLayeredPane11.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewTricycle_panel2.setBackground(new java.awt.Color(255, 255, 255));
        viewTricycle_panel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        viewTricycle_panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background9.setBackground(new java.awt.Color(93, 25, 50));
        background9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        passenger_store_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Address", "Phone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        passenger_store_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passenger_store_tableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                passenger_store_tableMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(passenger_store_table);
        if (passenger_store_table.getColumnModel().getColumnCount() > 0) {
            passenger_store_table.getColumnModel().getColumn(0).setResizable(false);
            passenger_store_table.getColumnModel().getColumn(1).setResizable(false);
            passenger_store_table.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout background9Layout = new javax.swing.GroupLayout(background9);
        background9.setLayout(background9Layout);
        background9Layout.setHorizontalGroup(
            background9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        background9Layout.setVerticalGroup(
            background9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        viewTricycle_panel2.add(background9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 530, 440));

        jPanel13.setBackground(new java.awt.Color(93, 25, 50));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel69.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("Stores");
        jPanel13.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 460, 50));

        viewTricycle_panel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 70));

        viewStore_back.setBackground(new java.awt.Color(255, 255, 255));
        viewStore_back.setText("Back");
        viewStore_back.setFocusable(false);
        viewStore_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                viewStore_backMouseReleased(evt);
            }
        });
        viewTricycle_panel2.add(viewStore_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 70, 30));

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        viewTricycle_panel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 30, 30));

        jLayeredPane11.add(viewTricycle_panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 560));

        javax.swing.GroupLayout viewStoresLayout = new javax.swing.GroupLayout(viewStores.getContentPane());
        viewStores.getContentPane().setLayout(viewStoresLayout);
        viewStoresLayout.setHorizontalGroup(
            viewStoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane11)
        );
        viewStoresLayout.setVerticalGroup(
            viewStoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane11)
        );

        view_products.setText("View Products");
        view_products.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_productsActionPerformed(evt);
            }
        });
        store_pop.add(view_products);

        viewProducts.setUndecorated(true);
        viewProducts.setResizable(false);

        jLayeredPane14.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        driver_panel6.setBackground(new java.awt.Color(255, 255, 255));
        driver_panel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        driver_panel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background12.setBackground(new java.awt.Color(93, 25, 50));
        background12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        background12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        product_details.setBackground(new java.awt.Color(93, 25, 50));
        product_details.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "PRODUCT DETAILS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        product_details.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        std_photo_panel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        std_photo_panel5.setPreferredSize(new java.awt.Dimension(130, 130));
        std_photo_panel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        product_photo.setText("             NO IMAGE");
        std_photo_panel5.add(product_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 140));

        product_details.add(std_photo_panel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 140));

        product_name.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        product_name.setForeground(new java.awt.Color(255, 255, 255));
        product_name.setText("Product name");
        product_details.add(product_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 150, -1));

        product_type.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        product_type.setForeground(new java.awt.Color(255, 255, 255));
        product_type.setText("Type");
        product_details.add(product_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 150, -1));

        product_price.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        product_price.setForeground(new java.awt.Color(255, 255, 255));
        product_price.setText("Price");
        product_details.add(product_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 150, -1));

        background12.add(product_details, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 7, 333, 199));

        right_panel5.setBackground(new java.awt.Color(93, 25, 50));

        javax.swing.GroupLayout right_panel5Layout = new javax.swing.GroupLayout(right_panel5);
        right_panel5.setLayout(right_panel5Layout);
        right_panel5Layout.setHorizontalGroup(
            right_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );
        right_panel5Layout.setVerticalGroup(
            right_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        background12.add(right_panel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 334, -1, -1));

        jPanel17.setBackground(new java.awt.Color(93, 25, 50));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        viewProducts_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewProducts_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewProducts_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(viewProducts_table);
        if (viewProducts_table.getColumnModel().getColumnCount() > 0) {
            viewProducts_table.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel71.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("PRODUCTS");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        background12.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 13, 399, -1));

        add_to_cart.setBackground(new java.awt.Color(93, 25, 50));
        add_to_cart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        product_quantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 999, 1));
        product_quantity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                getValueToText(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Quantity :");
        jLabel70.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel73.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Total :");
        jLabel73.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        product_total.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        product_total.setForeground(new java.awt.Color(255, 255, 255));
        product_total.setText("----");
        product_total.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        add_to_cart_bttn.setText("Add to Cart");
        add_to_cart_bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_to_cart_bttnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout add_to_cartLayout = new javax.swing.GroupLayout(add_to_cart);
        add_to_cart.setLayout(add_to_cartLayout);
        add_to_cartLayout.setHorizontalGroup(
            add_to_cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_to_cartLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(add_to_cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70)
                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(add_to_cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(product_quantity)
                    .addComponent(product_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(add_to_cart_bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        add_to_cartLayout.setVerticalGroup(
            add_to_cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_to_cartLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(add_to_cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(add_to_cart_bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(add_to_cartLayout.createSequentialGroup()
                        .addGroup(add_to_cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(product_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(add_to_cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(product_total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );

        background12.add(add_to_cart, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 218, -1, -1));

        driver_panel6.add(background12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 780, 340));

        jPanel22.setBackground(new java.awt.Color(93, 25, 50));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewProducts_store_name.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        viewProducts_store_name.setForeground(new java.awt.Color(255, 255, 255));
        viewProducts_store_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewProducts_store_name.setText("Store Name");
        jPanel22.add(viewProducts_store_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 410, 50));

        driver_panel6.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 70));

        viewProducts_back.setBackground(new java.awt.Color(255, 255, 255));
        viewProducts_back.setText("Back");
        viewProducts_back.setFocusable(false);
        viewProducts_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                viewProducts_backMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                viewProducts_backMouseReleased(evt);
            }
        });
        driver_panel6.add(viewProducts_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 30));

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        driver_panel6.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 30, 30));

        my_cart_txt.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        my_cart_txt.setForeground(new java.awt.Color(0, 0, 0));
        my_cart_txt.setText("My Cart :");
        my_cart_txt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        my_cart_txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                my_cart_txtMouseReleased(evt);
            }
        });
        driver_panel6.add(my_cart_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, -1, 30));

        myCart_count.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        myCart_count.setForeground(new java.awt.Color(0, 0, 0));
        myCart_count.setText("---");
        driver_panel6.add(myCart_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 66, 50, 40));

        jLayeredPane14.add(driver_panel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 460));

        javax.swing.GroupLayout viewProductsLayout = new javax.swing.GroupLayout(viewProducts.getContentPane());
        viewProducts.getContentPane().setLayout(viewProductsLayout);
        viewProductsLayout.setHorizontalGroup(
            viewProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane14)
        );
        viewProductsLayout.setVerticalGroup(
            viewProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane14)
        );

        viewCart.setUndecorated(true);
        viewCart.setResizable(false);

        jLayeredPane12.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewTricycle_panel3.setBackground(new java.awt.Color(255, 255, 255));
        viewTricycle_panel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        viewTricycle_panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background10.setBackground(new java.awt.Color(93, 25, 50));
        background10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        viewCart_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type", "Price", "Quantity", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewCart_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewCart_tableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                viewCart_tableMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(viewCart_table);
        if (viewCart_table.getColumnModel().getColumnCount() > 0) {
            viewCart_table.getColumnModel().getColumn(0).setResizable(false);
            viewCart_table.getColumnModel().getColumn(1).setResizable(false);
            viewCart_table.getColumnModel().getColumn(2).setResizable(false);
            viewCart_table.getColumnModel().getColumn(3).setResizable(false);
            viewCart_table.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("MY CART");

        viewCart_take_out.setText("Take Out");
        viewCart_take_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCart_take_outActionPerformed(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Total:");

        viewCart_total.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        viewCart_total.setForeground(new java.awt.Color(255, 255, 255));
        viewCart_total.setText("0.00");

        javax.swing.GroupLayout background10Layout = new javax.swing.GroupLayout(background10);
        background10.setLayout(background10Layout);
        background10Layout.setHorizontalGroup(
            background10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background10Layout.createSequentialGroup()
                .addGroup(background10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(background10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(background10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background10Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(195, 195, 195))))
                    .addGroup(background10Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(viewCart_total, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viewCart_take_out)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        background10Layout.setVerticalGroup(
            background10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewCart_take_out)
                    .addComponent(jLabel63)
                    .addComponent(viewCart_total))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        viewTricycle_panel3.add(background10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 530, 440));

        jPanel14.setBackground(new java.awt.Color(93, 25, 50));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewCart_store_name.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        viewCart_store_name.setForeground(new java.awt.Color(255, 255, 255));
        viewCart_store_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewCart_store_name.setText("Store Name");
        jPanel14.add(viewCart_store_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 460, 50));

        viewTricycle_panel3.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 70));

        myCart_back.setBackground(new java.awt.Color(255, 255, 255));
        myCart_back.setText("Back");
        myCart_back.setFocusable(false);
        myCart_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                myCart_backMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                myCart_backMouseReleased(evt);
            }
        });
        viewTricycle_panel3.add(myCart_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 70, 30));

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        viewTricycle_panel3.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 30, 30));

        jLayeredPane12.add(viewTricycle_panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 560));

        javax.swing.GroupLayout viewCartLayout = new javax.swing.GroupLayout(viewCart.getContentPane());
        viewCart.getContentPane().setLayout(viewCartLayout);
        viewCartLayout.setHorizontalGroup(
            viewCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane12)
        );
        viewCartLayout.setVerticalGroup(
            viewCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane12)
        );

        viewCart_remove.setText("Remove");
        viewCart_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCart_removeActionPerformed(evt);
            }
        });
        remove_product.add(viewCart_remove);

        jLayeredPane10.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        driver_panel7.setBackground(new java.awt.Color(255, 255, 255));
        driver_panel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        driver_panel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background8.setBackground(new java.awt.Color(93, 25, 50));
        background8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("Firstname");

        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Middlename");

        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Lastname");

        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("Gender");

        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Username");

        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Address");

        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Password");

        vendor_reg_bttn.setBackground(new java.awt.Color(255, 255, 255));
        vendor_reg_bttn.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        vendor_reg_bttn.setForeground(new java.awt.Color(204, 0, 51));
        vendor_reg_bttn.setText("Register Account");
        vendor_reg_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vendor_reg_bttnMouseReleased(evt);
            }
        });

        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Phone Number");

        javax.swing.GroupLayout background8Layout = new javax.swing.GroupLayout(background8);
        background8.setLayout(background8Layout);
        background8Layout.setHorizontalGroup(
            background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background8Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67)
                    .addComponent(vendor_reg_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66)
                    .addComponent(vendor_reg_middlename, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65)
                    .addComponent(vendor_reg_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75)
                    .addComponent(vendor_reg_username, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74)
                    .addComponent(vendor_reg_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78)
                    .addComponent(vendor_reg_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel77)
                    .addComponent(vendor_reg_password, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jLabel76)
                    .addComponent(vendor_reg_address, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(vendor_reg_bttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(89, 89, 89))
        );
        background8Layout.setVerticalGroup(
            background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background8Layout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendor_reg_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendor_reg_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background8Layout.createSequentialGroup()
                            .addComponent(jLabel74)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(vendor_reg_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel78)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(vendor_reg_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel75)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(vendor_reg_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background8Layout.createSequentialGroup()
                            .addComponent(jLabel65)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(vendor_reg_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel66)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(vendor_reg_middlename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel67)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(vendor_reg_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(vendor_reg_bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        driver_panel7.add(background8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 960, 340));

        jPanel11.setBackground(new java.awt.Color(93, 25, 50));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel79.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Vendor Registration Form");
        jPanel11.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 550, 50));

        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Date :");
        jPanel11.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, -1, -1));

        vendor_reg_date.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        vendor_reg_date.setForeground(new java.awt.Color(255, 255, 255));
        vendor_reg_date.setText("-----");
        jPanel11.add(vendor_reg_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 150, -1));

        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Time :");
        jPanel11.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        vendor_reg_time.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        vendor_reg_time.setForeground(new java.awt.Color(255, 255, 255));
        vendor_reg_time.setText("-----");
        jPanel11.add(vendor_reg_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 150, -1));

        driver_panel7.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 70));

        vendor_reg_logout.setBackground(new java.awt.Color(255, 255, 255));
        vendor_reg_logout.setText("Back");
        vendor_reg_logout.setFocusable(false);
        vendor_reg_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                vendor_reg_logoutMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vendor_reg_logoutMouseReleased(evt);
            }
        });
        driver_panel7.add(vendor_reg_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 70, 30));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        driver_panel7.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 30, 30));

        jLayeredPane10.add(driver_panel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 460));

        javax.swing.GroupLayout vendor_registrationLayout = new javax.swing.GroupLayout(vendor_registration.getContentPane());
        vendor_registration.getContentPane().setLayout(vendor_registrationLayout);
        vendor_registrationLayout.setHorizontalGroup(
            vendor_registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane10)
        );
        vendor_registrationLayout.setVerticalGroup(
            vendor_registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane10)
        );

        vendor_dashboard.setUndecorated(true);
        vendor_dashboard.setResizable(false);

        jLayeredPane13.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        driver_panel8.setBackground(new java.awt.Color(255, 255, 255));
        driver_panel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        driver_panel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background11.setBackground(new java.awt.Color(93, 25, 50));
        background11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        left_panel5.setBackground(new java.awt.Color(93, 25, 50));
        left_panel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "VENDOR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        left_panel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("ID NUMBER :");
        left_panel5.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 42, -1, -1));

        vendor_txt_id.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        vendor_txt_id.setForeground(new java.awt.Color(255, 255, 255));
        vendor_txt_id.setText("-----");
        left_panel5.add(vendor_txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 40, 210, -1));

        std_photo_panel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        std_photo_panel6.setPreferredSize(new java.awt.Dimension(130, 130));
        std_photo_panel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        vendor_photo.setText("             NO IMAGE");
        std_photo_panel6.add(vendor_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 140));

        left_panel5.add(std_photo_panel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 77, -1, 140));

        vendor_txt_firstname.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        vendor_txt_firstname.setForeground(new java.awt.Color(255, 255, 255));
        vendor_txt_firstname.setText("Name");
        left_panel5.add(vendor_txt_firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 160, -1));

        vendor_txt_middlename.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        vendor_txt_middlename.setForeground(new java.awt.Color(255, 255, 255));
        vendor_txt_middlename.setText("Middle Name");
        left_panel5.add(vendor_txt_middlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 170, -1));

        vendor_txt_phone.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        vendor_txt_phone.setForeground(new java.awt.Color(255, 255, 255));
        vendor_txt_phone.setText("Last Name");
        left_panel5.add(vendor_txt_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 160, -1));

        vendor_upload_photo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        vendor_upload_photo.setForeground(new java.awt.Color(255, 102, 102));
        vendor_upload_photo.setText("Add Photo");
        vendor_upload_photo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vendor_upload_photo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                vendor_upload_photoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vendor_upload_photoMouseReleased(evt);
            }
        });
        left_panel5.add(vendor_upload_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 223, -1, -1));

        active_now_label5.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        active_now_label5.setForeground(new java.awt.Color(255, 255, 255));
        active_now_label5.setText("Active now");
        left_panel5.add(active_now_label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, 20));
        left_panel5.add(green_active_icon5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 13, -1, 40));

        vendor_txt_lastname.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        vendor_txt_lastname.setForeground(new java.awt.Color(255, 255, 255));
        vendor_txt_lastname.setText("Last Name");
        left_panel5.add(vendor_txt_lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 160, -1));

        vendor_txt_address.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        vendor_txt_address.setForeground(new java.awt.Color(255, 255, 255));
        vendor_txt_address.setText("Last Name");
        left_panel5.add(vendor_txt_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 160, -1));

        right_panel6.setBackground(new java.awt.Color(93, 25, 50));

        javax.swing.GroupLayout right_panel6Layout = new javax.swing.GroupLayout(right_panel6);
        right_panel6.setLayout(right_panel6Layout);
        right_panel6Layout.setHorizontalGroup(
            right_panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        right_panel6Layout.setVerticalGroup(
            right_panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        bttn_add_product.setText("Add New Product");
        bttn_add_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_add_productActionPerformed(evt);
            }
        });

        vendor_products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(vendor_products);
        if (vendor_products.getColumnModel().getColumnCount() > 0) {
            vendor_products.getColumnModel().getColumn(0).setResizable(false);
            vendor_products.getColumnModel().getColumn(1).setResizable(false);
            vendor_products.getColumnModel().getColumn(2).setResizable(false);
        }

        my_products_txt.setText("My Products");

        store_name_txt.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        store_name_txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        store_name_txt.setText("Store UNREGISTERED");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(my_products_txt)
                        .addGap(82, 82, 82)
                        .addComponent(store_name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bttn_add_product)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bttn_add_product, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(my_products_txt)
                    .addComponent(store_name_txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Set Product", jPanel21);

        javax.swing.GroupLayout background11Layout = new javax.swing.GroupLayout(background11);
        background11.setLayout(background11Layout);
        background11Layout.setHorizontalGroup(
            background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(left_panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background11Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(right_panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(background11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane6)))
                .addGap(15, 15, 15))
        );
        background11Layout.setVerticalGroup(
            background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(left_panel5, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(right_panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        driver_panel8.add(background11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 960, 340));

        jPanel23.setBackground(new java.awt.Color(93, 25, 50));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel92.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("TRICYCLE");
        jPanel23.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel93.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Dashboard");
        jPanel23.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 230, 50));

        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setText("Date :");
        jPanel23.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, -1, -1));

        vendor_date.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        vendor_date.setForeground(new java.awt.Color(255, 255, 255));
        vendor_date.setText("-----");
        jPanel23.add(vendor_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 150, -1));

        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setText("Time :");
        jPanel23.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        vendor_time.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        vendor_time.setForeground(new java.awt.Color(255, 255, 255));
        vendor_time.setText("-----");
        jPanel23.add(vendor_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 150, -1));

        jLabel96.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("App");
        jPanel23.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 90, 50));

        driver_panel8.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 70));

        vendor_logout.setBackground(new java.awt.Color(255, 255, 255));
        vendor_logout.setText("Logout");
        vendor_logout.setFocusable(false);
        vendor_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                vendor_logoutMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vendor_logoutMouseReleased(evt);
            }
        });
        driver_panel8.add(vendor_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 70, -1, 30));

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        driver_panel8.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 30, 30));

        jLayeredPane13.add(driver_panel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 460));

        javax.swing.GroupLayout vendor_dashboardLayout = new javax.swing.GroupLayout(vendor_dashboard.getContentPane());
        vendor_dashboard.getContentPane().setLayout(vendor_dashboardLayout);
        vendor_dashboardLayout.setHorizontalGroup(
            vendor_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane13)
        );
        vendor_dashboardLayout.setVerticalGroup(
            vendor_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane13)
        );

        product_registration.setUndecorated(true);
        product_registration.setResizable(false);

        jLayeredPane15.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewTricycle_panel4.setBackground(new java.awt.Color(255, 255, 255));
        viewTricycle_panel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        viewTricycle_panel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background13.setBackground(new java.awt.Color(93, 25, 50));
        background13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        background13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        product_reg_bttn.setText("Register Product");
        product_reg_bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_reg_bttnActionPerformed(evt);
            }
        });
        background13.add(product_reg_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 140, 46));

        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("Product Photo");
        background13.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setText("Product Name");
        background13.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));
        background13.add(product_reg_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 200, -1));

        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Product Type");
        background13.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));
        background13.add(product_reg_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 200, -1));

        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Price");
        background13.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));
        background13.add(product_reg_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 200, -1));

        std_photo_panel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        std_photo_panel7.setPreferredSize(new java.awt.Dimension(130, 130));
        std_photo_panel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        product_reg_photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        product_reg_photo.setText("ADD PHOTO");
        product_reg_photo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                product_reg_photoMouseReleased(evt);
            }
        });
        std_photo_panel7.add(product_reg_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 140));

        background13.add(std_photo_panel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 77, -1, 140));

        viewTricycle_panel4.add(background13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 440, 390));

        jPanel16.setBackground(new java.awt.Color(93, 25, 50));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewCart_store_name1.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        viewCart_store_name1.setForeground(new java.awt.Color(255, 255, 255));
        viewCart_store_name1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewCart_store_name1.setText("Product Registration");
        jPanel16.add(viewCart_store_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 360, 50));

        viewTricycle_panel4.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 70));

        product_reg_back.setBackground(new java.awt.Color(255, 255, 255));
        product_reg_back.setText("Back");
        product_reg_back.setFocusable(false);
        product_reg_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                product_reg_backMouseReleased(evt);
            }
        });
        viewTricycle_panel4.add(product_reg_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 70, 30));

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);
        viewTricycle_panel4.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 30, 30));

        jLayeredPane15.add(viewTricycle_panel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 510));

        javax.swing.GroupLayout product_registrationLayout = new javax.swing.GroupLayout(product_registration.getContentPane());
        product_registration.getContentPane().setLayout(product_registrationLayout);
        product_registrationLayout.setHorizontalGroup(
            product_registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane15)
        );
        product_registrationLayout.setVerticalGroup(
            product_registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane15)
        );

        store_registration.setUndecorated(true);
        store_registration.setResizable(false);

        jLayeredPane16.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewTricycle_panel5.setBackground(new java.awt.Color(255, 255, 255));
        viewTricycle_panel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        viewTricycle_panel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background14.setBackground(new java.awt.Color(93, 25, 50));
        background14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        background14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        product_reg_bttn1.setText("Register Store");
        product_reg_bttn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_reg_bttn1ActionPerformed(evt);
            }
        });
        background14.add(product_reg_bttn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 140, 46));

        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Store Name");
        background14.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));
        background14.add(store_reg_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 200, -1));

        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Store Address");
        background14.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));
        background14.add(store_reg_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 200, -1));

        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("Store Phone");
        background14.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));
        background14.add(store_reg_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 200, -1));

        viewTricycle_panel5.add(background14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 430, 390));

        jPanel18.setBackground(new java.awt.Color(93, 25, 50));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewCart_store_name2.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        viewCart_store_name2.setForeground(new java.awt.Color(255, 255, 255));
        viewCart_store_name2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewCart_store_name2.setText("Store Registration");
        jPanel18.add(viewCart_store_name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 360, 50));

        viewTricycle_panel5.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 70));

        store_reg_back.setBackground(new java.awt.Color(255, 255, 255));
        store_reg_back.setText("Back");
        store_reg_back.setFocusable(false);
        store_reg_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                store_reg_backActionPerformed(evt);
            }
        });
        viewTricycle_panel5.add(store_reg_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 70, 30));

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        viewTricycle_panel5.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 30, 30));

        jLayeredPane16.add(viewTricycle_panel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 510));

        javax.swing.GroupLayout store_registrationLayout = new javax.swing.GroupLayout(store_registration.getContentPane());
        store_registration.getContentPane().setLayout(store_registrationLayout);
        store_registrationLayout.setHorizontalGroup(
            store_registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane16)
        );
        store_registrationLayout.setVerticalGroup(
            store_registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane16)
        );

        addFranchise.setUndecorated(true);
        addFranchise.setResizable(false);

        jLayeredPane17.setBackground(new java.awt.Color(93, 25, 50));
        jLayeredPane17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewTricycle_panel6.setBackground(new java.awt.Color(255, 255, 255));
        viewTricycle_panel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        viewTricycle_panel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background15.setBackground(new java.awt.Color(93, 25, 50));
        background15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        background15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        registerFranchise_tricycle.setText("Register Tricycle");
        registerFranchise_tricycle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerFranchise_tricycleActionPerformed(evt);
            }
        });
        background15.add(registerFranchise_tricycle, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 140, 46));

        addFranchise_tricycle_id.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        addFranchise_tricycle_id.setForeground(new java.awt.Color(255, 255, 255));
        addFranchise_tricycle_id.setText("-----");
        background15.add(addFranchise_tricycle_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 180, -1));

        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("Registered Date: ");
        background15.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));
        background15.add(addFranchise_reg_start, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 200, -1));

        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setText("Expiration Date:");
        background15.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));
        background15.add(addFranchise_reg_end, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 200, -1));

        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("Tricycle ID: ");
        background15.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        viewTricycle_panel6.add(background15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 430, 390));

        jPanel24.setBackground(new java.awt.Color(93, 25, 50));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewCart_store_name3.setFont(new java.awt.Font("Brush Script MT", 1, 45)); // NOI18N
        viewCart_store_name3.setForeground(new java.awt.Color(255, 255, 255));
        viewCart_store_name3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewCart_store_name3.setText("Set Franchise");
        jPanel24.add(viewCart_store_name3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 360, 50));

        viewTricycle_panel6.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 70));

        setFranchise_back.setBackground(new java.awt.Color(255, 255, 255));
        setFranchise_back.setText("Back");
        setFranchise_back.setFocusable(false);
        setFranchise_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setFranchise_backActionPerformed(evt);
            }
        });
        viewTricycle_panel6.add(setFranchise_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 70, 30));

        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);
        viewTricycle_panel6.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 30, 30));

        jLayeredPane17.add(viewTricycle_panel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 510));

        javax.swing.GroupLayout addFranchiseLayout = new javax.swing.GroupLayout(addFranchise.getContentPane());
        addFranchise.getContentPane().setLayout(addFranchiseLayout);
        addFranchiseLayout.setHorizontalGroup(
            addFranchiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane17)
        );
        addFranchiseLayout.setVerticalGroup(
            addFranchiseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane17)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_login.setBackground(new java.awt.Color(93, 25, 50));
        panel_login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        panel_login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TRICYCLE");
        panel_login.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 80, 120, -1));

        jLabel2.setFont(new java.awt.Font("Brush Script MT", 1, 55)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("A");
        panel_login.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        jLabel3.setFont(new java.awt.Font("Forte", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("pp");
        panel_login.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 100, -1));

        jLabel25.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 30)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Login");
        panel_login.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 130, 50));

        username_panel.setBackground(new java.awt.Color(93, 25, 50));
        username_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 25, 50)));

        login_txt_username.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        login_txt_username.setForeground(new java.awt.Color(153, 153, 153));
        login_txt_username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                login_txt_usernameMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                login_txt_usernameMouseReleased(evt);
            }
        });
        login_txt_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                login_txt_usernameKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout username_panelLayout = new javax.swing.GroupLayout(username_panel);
        username_panel.setLayout(username_panelLayout);
        username_panelLayout.setHorizontalGroup(
            username_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(username_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(login_txt_username, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addContainerGap())
        );
        username_panelLayout.setVerticalGroup(
            username_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, username_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(login_txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );

        panel_login.add(username_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 310, 50));

        password_panel.setBackground(new java.awt.Color(93, 25, 50));
        password_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(93, 25, 50)));

        login_txt_password.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        login_txt_password.setForeground(new java.awt.Color(153, 153, 153));
        login_txt_password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                login_txt_passwordMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                login_txt_passwordMouseReleased(evt);
            }
        });
        login_txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                login_txt_passwordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout password_panelLayout = new javax.swing.GroupLayout(password_panel);
        password_panel.setLayout(password_panelLayout);
        password_panelLayout.setHorizontalGroup(
            password_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, password_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(login_txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        password_panelLayout.setVerticalGroup(
            password_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(password_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(login_txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_login.add(password_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 310, -1));

        login_user.setBackground(new java.awt.Color(153, 25, 50));
        login_user.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        login_user.setForeground(new java.awt.Color(255, 255, 255));
        login_user.setText("Sign In");
        login_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_userActionPerformed(evt);
            }
        });
        panel_login.add(login_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 310, 45));

        login_new_account.setForeground(new java.awt.Color(255, 255, 255));
        login_new_account.setText("Create New Account");
        login_new_account.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        login_new_account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                login_new_accountMouseReleased(evt);
            }
        });
        panel_login.add(login_new_account, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, -1, -1));

        jLayeredPane1.add(panel_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void login_txt_usernameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_txt_usernameMousePressed
        username_panel.setBorder(selected);
    }//GEN-LAST:event_login_txt_usernameMousePressed

    private void login_txt_usernameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_txt_usernameMouseReleased
        username_panel.setBorder(unselected);
    }//GEN-LAST:event_login_txt_usernameMouseReleased

    private void login_txt_usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_login_txt_usernameKeyPressed

        try {

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//Enter key presses

                userLogin(login_txt_username.getText(), login_txt_password.getText());
                login_txt_username.requestFocus(true);
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_login_txt_usernameKeyPressed

    private void login_txt_passwordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_txt_passwordMousePressed

        password_panel.setBorder(selected);
    }//GEN-LAST:event_login_txt_passwordMousePressed

    private void login_txt_passwordMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_txt_passwordMouseReleased

        password_panel.setBorder(unselected);
    }//GEN-LAST:event_login_txt_passwordMouseReleased

    private void login_txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_login_txt_passwordKeyPressed
        try {

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//Enter key presses

                userLogin(login_txt_username.getText(), login_txt_password.getText());
                login_txt_username.requestFocus(true);
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_login_txt_passwordKeyPressed

    private void driver_reg_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driver_reg_logoutMousePressed
        driver_reg_logout.setBackground(maroon);
        driver_reg_logout.setForeground(Color.WHITE);
    }//GEN-LAST:event_driver_reg_logoutMousePressed

    private void driver_reg_logoutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driver_reg_logoutMouseReleased
        driver_reg_logout.setBackground(Color.WHITE);
        driver_reg_logout.setForeground(Color.BLACK);

        int diagResult = JOptionPane.showConfirmDialog(null, "Changes you made may not be saved. \n Do you want to exit window?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (diagResult == JOptionPane.YES_OPTION) {

            login_txt_username.setText("");
            login_txt_password.setText("");

            driver_registration.setVisible(false);
            login_txt_username.requestFocus(true);
            this.setVisible(true);

        } else {

        }
    }//GEN-LAST:event_driver_reg_logoutMouseReleased

    private void login_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_userActionPerformed
        userLogin(login_txt_username.getText(), login_txt_password.getText());
    }//GEN-LAST:event_login_userActionPerformed

    private void driver_upload_photoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driver_upload_photoMousePressed
        driver_upload_photo.setForeground(Color.WHITE);
    }//GEN-LAST:event_driver_upload_photoMousePressed

    private void driver_upload_photoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driver_upload_photoMouseReleased
        driver_upload_photo.setForeground(red);
        connect_driver.driver_setPhoto(driver_txt_lastname.getText());
    }//GEN-LAST:event_driver_upload_photoMouseReleased

    private void driver_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driver_logoutMousePressed
        driver_logout.setBackground(maroon);
        driver_logout.setForeground(Color.WHITE);
    }//GEN-LAST:event_driver_logoutMousePressed

    private void driver_logoutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driver_logoutMouseReleased
        driver_logout.setBackground(Color.WHITE);
        driver_logout.setForeground(Color.BLACK);

        int diagResult = JOptionPane.showConfirmDialog(null, "Do you want to log-out?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (diagResult == JOptionPane.YES_OPTION) {

            login_txt_username.setText("");
            login_txt_password.setText("");

            driver_dashboard.setVisible(false);
            login_txt_username.requestFocus(true);
            this.setVisible(true);

        } else {

        }
    }//GEN-LAST:event_driver_logoutMouseReleased

    private void login_new_accountMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_new_accountMouseReleased
        String[] option_type = {"Passenger", "Tricycle Driver", "Product Vendor"};

        String type = (String) JOptionPane.showInputDialog(null, "I am a...", "Create Account", JOptionPane.QUESTION_MESSAGE, null, option_type, option_type[0]);

        try {
            if (type.equals("Passenger")) {
                System.out.println("Passenger");
                PassengerReg();

            } else if (type.equals("Tricycle Driver")) {
                System.out.println("Tricycle Driver");
                DriverReg();

            } else if (type.equals("Product Vendor")) {
                System.out.println("Product Vendor");
                VendorReg();

            } else {
                System.out.println("Something");
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_login_new_accountMouseReleased

    private void driver_reg_bttnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driver_reg_bttnMouseReleased
        registerDriver();
        driver_reg_erase();
        driver_registration.setVisible(false);
        login_txt_username.requestFocus(true);
        this.setVisible(true);
    }//GEN-LAST:event_driver_reg_bttnMouseReleased

    private void admin_upload_photoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_upload_photoMousePressed
        admin_upload_photo.setForeground(Color.WHITE);
    }//GEN-LAST:event_admin_upload_photoMousePressed

    private void admin_upload_photoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_upload_photoMouseReleased
        admin_upload_photo.setForeground(red);
        connect_admin.admin_setPhoto(admin_txt_lastname.getText());
    }//GEN-LAST:event_admin_upload_photoMouseReleased

    private void admin_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_logoutMousePressed
        admin_logout.setBackground(maroon);
        admin_logout.setForeground(Color.WHITE);
    }//GEN-LAST:event_admin_logoutMousePressed

    private void admin_logoutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_logoutMouseReleased
        admin_logout.setBackground(Color.WHITE);
        admin_logout.setForeground(Color.BLACK);

        int diagResult = JOptionPane.showConfirmDialog(null, "Do you want to log-out?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (diagResult == JOptionPane.YES_OPTION) {

            login_txt_username.setText("");
            login_txt_password.setText("");

            admin_dashboard.setVisible(false);
            login_txt_username.requestFocus(true);
            this.setVisible(true);

        } else {
        }
    }//GEN-LAST:event_admin_logoutMouseReleased

    private void view_tricycle_recordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_tricycle_recordActionPerformed
        connect_admin.getTricycleRecord(getTricycle_ID);

        admin_dashboard.setEnabled(false);
        viewTricycle.setVisible(true);
        viewTricycle.pack();
        viewTricycle.setLocationRelativeTo(this);
    }//GEN-LAST:event_view_tricycle_recordActionPerformed

    private void viewTricycle_backMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewTricycle_backMousePressed
        viewTricycle_back.setBackground(maroon);
        viewTricycle_back.setForeground(Color.WHITE);
    }//GEN-LAST:event_viewTricycle_backMousePressed

    private void viewTricycle_backMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewTricycle_backMouseReleased
        viewTricycle_back.setBackground(Color.WHITE);
        viewTricycle_back.setForeground(Color.BLACK);

        int diagResult = JOptionPane.showConfirmDialog(null, "Changes you made may not be saved. \n Do you want to exit window?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (diagResult == JOptionPane.YES_OPTION) {

            viewTricycle.setVisible(false);
            admin_dashboard.setVisible(true);
            admin_dashboard.setEnabled(true);

        } else {

        }
    }//GEN-LAST:event_viewTricycle_backMouseReleased

    private void viewTricycle_updateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewTricycle_updateMouseReleased
        viewTricycle_update.setBackground(Color.WHITE);
        viewTricycle_update.setForeground(Color.RED);

        int diagResult = JOptionPane.showConfirmDialog(null, "Do you want to update this tricycle record?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (diagResult == JOptionPane.YES_OPTION) {

            connect_admin.updateTricycleRecord(getTricycle_ID, viewTricycle_tricycle_status.getSelectedItem().toString());

            viewTricycle.setVisible(false);
            admin_dashboard.setVisible(true);
            admin_dashboard.setEnabled(true);

            //refresh table
            DefaultTableModel model = (DefaultTableModel) Main_Frame.table_tricycles.getModel();
            model.setRowCount(0);
            AdminDashboard();

            table_tricycles.requestFocus();
            table_tricycles.changeSelection(selectedRow, 0, false, false);

            System.out.println(getTricycle_ID);

        } else {

        }

    }//GEN-LAST:event_viewTricycle_updateMouseReleased

    private void viewTricycle_updateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewTricycle_updateMousePressed
        viewTricycle_update.setBackground(maroon);
        viewTricycle_update.setForeground(Color.WHITE);
    }//GEN-LAST:event_viewTricycle_updateMousePressed

    private void table_tricyclesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_tricyclesMouseReleased
        try {

            if (evt.isPopupTrigger()) {
                tricycles_pop.show(evt.getComponent(), evt.getX(), evt.getY());

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a row.", "INVALID", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_table_tricyclesMouseReleased

    private void table_tricyclesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_tricyclesMouseClicked
        try {
            DefaultTableModel model = (DefaultTableModel) table_tricycles.getModel(); //if jtable clicked, the value will go to jlabel.
            selectedRow = table_tricycles.getSelectedRow();
            getTricycle_ID = model.getValueAt(selectedRow, 0).toString();

            System.out.println(getTricycle_ID);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a row.", "INVALID", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_table_tricyclesMouseClicked

    private void passenger_reg_bttnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passenger_reg_bttnMouseReleased
        registerPassenger();
        passenger_reg_erase();
        passenger_registration.setVisible(false);
        login_txt_username.requestFocus(true);
        this.setVisible(true);
    }//GEN-LAST:event_passenger_reg_bttnMouseReleased

    private void passenger_reg_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passenger_reg_logoutMousePressed
        passenger_reg_logout.setBackground(maroon);
        passenger_reg_logout.setForeground(Color.WHITE);
    }//GEN-LAST:event_passenger_reg_logoutMousePressed

    private void passenger_reg_logoutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passenger_reg_logoutMouseReleased
        passenger_reg_logout.setBackground(Color.WHITE);
        passenger_reg_logout.setForeground(Color.BLACK);

        int diagResult = JOptionPane.showConfirmDialog(null, "Changes you made may not be saved. \n Do you want to exit window?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (diagResult == JOptionPane.YES_OPTION) {

            login_txt_username.setText("");
            login_txt_password.setText("");

            passenger_registration.setVisible(false);
            login_txt_username.requestFocus(true);
            this.setVisible(true);

        } else {

        }
    }//GEN-LAST:event_passenger_reg_logoutMouseReleased

    private void passenger_upload_photoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passenger_upload_photoMousePressed
        passenger_upload_photo.setForeground(Color.WHITE);

    }//GEN-LAST:event_passenger_upload_photoMousePressed

    private void passenger_upload_photoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passenger_upload_photoMouseReleased
        passenger_upload_photo.setForeground(red);
        connect_passenger.passenger_setPhoto(passenger_txt_lastname.getText());
    }//GEN-LAST:event_passenger_upload_photoMouseReleased

    private void passenger_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passenger_logoutMousePressed
        passenger_logout.setBackground(maroon);
        passenger_logout.setForeground(Color.WHITE);
    }//GEN-LAST:event_passenger_logoutMousePressed

    private void passenger_logoutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passenger_logoutMouseReleased
        passenger_logout.setBackground(Color.WHITE);
        passenger_logout.setForeground(Color.BLACK);

        int diagResult = JOptionPane.showConfirmDialog(null, "Do you want to log-out?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (diagResult == JOptionPane.YES_OPTION) {

            login_txt_username.setText("");
            login_txt_password.setText("");

            passenger_dashboard.setVisible(false);
            login_txt_username.requestFocus(true);
            this.setVisible(true);

        } else {

        }
    }//GEN-LAST:event_passenger_logoutMouseReleased

    private void passenger_goActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_goActionPerformed
        int diagResult = JOptionPane.showConfirmDialog(null, "Are you sure to set your trip?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (diagResult == JOptionPane.YES_OPTION) {

            if (passenger_start.getText().isEmpty() || passenger_end.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid input.", "INVALID", JOptionPane.ERROR_MESSAGE);
            } else {
                connect_passenger.addTrip(passenger_start.getText(), passenger_end.getText(), passenger_date.getText(), passenger_time.getText());
            }

        } else {
        }
    }//GEN-LAST:event_passenger_goActionPerformed

    private void passenger_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_cancelActionPerformed
        int diagResult = JOptionPane.showConfirmDialog(null, "Are you sure to cancel your trip?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (diagResult == JOptionPane.YES_OPTION) {
            connect_passenger.cancelTrip();
        } else {
        }
    }//GEN-LAST:event_passenger_cancelActionPerformed

    private void driver_found_bttn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driver_found_bttn_okActionPerformed
        passenger_dashboard.setEnabled(true);
        driver_found.setVisible(false);
    }//GEN-LAST:event_driver_found_bttn_okActionPerformed

    private void view_productsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_productsActionPerformed
        connect_passenger.getStoreProducts(getStore_Name);
        connect_passenger.getCartCount(getStore_Name);

        viewProducts.setVisible(true);
        viewProducts.pack();
        viewProducts.setLocationRelativeTo(this);

        //disable panel inside the products frame
        product_details.setVisible(false);
        add_to_cart.setVisible(false);

        viewStores.setVisible(false);
    }//GEN-LAST:event_view_productsActionPerformed

    private void viewStore_backMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewStore_backMouseReleased
        passenger_dashboard.setVisible(true);

        viewStores.setVisible(false);
    }//GEN-LAST:event_viewStore_backMouseReleased

    private void bttn_view_storesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_view_storesActionPerformed
        viewStores.setVisible(true);
        viewStores.pack();
        viewStores.setLocationRelativeTo(this);

        passenger_dashboard.setVisible(false);
    }//GEN-LAST:event_bttn_view_storesActionPerformed

    private void passenger_store_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passenger_store_tableMouseClicked
        try {
            DefaultTableModel model = (DefaultTableModel) passenger_store_table.getModel(); //if jtable clicked, the value will go to jlabel.
            selectedRow = passenger_store_table.getSelectedRow();
            getStore_Name = model.getValueAt(selectedRow, 0).toString();

            viewProducts_store_name.setText(getStore_Name);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a row.", "INVALID", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_passenger_store_tableMouseClicked

    private void passenger_store_tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passenger_store_tableMouseReleased
        try {
            if (evt.isPopupTrigger()) {

                if (getStore_Name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a store.", "INVALID", JOptionPane.ERROR_MESSAGE);
                } else {
                    store_pop.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a row.", "INVALID", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_passenger_store_tableMouseReleased

    private void myCart_backMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myCart_backMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_myCart_backMousePressed

    private void myCart_backMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myCart_backMouseReleased
        DefaultTableModel model = (DefaultTableModel) viewCart_table.getModel();
        model.setRowCount(0);

        connect_passenger.getCartCount(getStore_Name);

        viewProducts.setVisible(true);
        viewProducts.pack();
        viewProducts.setLocationRelativeTo(this);

        viewCart.setVisible(false);
    }//GEN-LAST:event_myCart_backMouseReleased

    private void viewCart_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewCart_tableMouseClicked
        try {
            DefaultTableModel model = (DefaultTableModel) viewCart_table.getModel(); //if jtable clicked, the value will go to jlabel.
            selectedRow = viewCart_table.getSelectedRow();
            getProduct_Name = model.getValueAt(selectedRow, 0).toString();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a row.", "INVALID", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_viewCart_tableMouseClicked

    private void viewCart_tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewCart_tableMouseReleased
        try {

            if (evt.isPopupTrigger()) {
                remove_product.show(evt.getComponent(), evt.getX(), evt.getY());

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a row.", "INVALID", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_viewCart_tableMouseReleased

    private void viewProducts_backMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewProducts_backMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewProducts_backMousePressed

    private void viewProducts_backMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewProducts_backMouseReleased
        //Reset the products on table
        DefaultTableModel model = (DefaultTableModel) viewProducts_table.getModel();
        model.setRowCount(0);

        viewStores.setVisible(true);
        viewStores.pack();
        viewStores.setLocationRelativeTo(this);

        viewProducts.setVisible(false);
    }//GEN-LAST:event_viewProducts_backMouseReleased

    private void viewProducts_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewProducts_tableMouseClicked
        try {
            DefaultTableModel model = (DefaultTableModel) viewProducts_table.getModel(); //if jtable clicked, the value will go to jlabel.
            selectedRow = viewProducts_table.getSelectedRow();
            getProduct_Name = model.getValueAt(selectedRow, 0).toString();

            connect_passenger.getProductDetails(getStore_Name, getProduct_Name);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a product.", "INVALID", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_viewProducts_tableMouseClicked

    private void add_to_cart_bttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_to_cart_bttnActionPerformed
        connect_passenger.getOrders(getProduct_Name, product_price.getText(),
                product_quantity.getValue().toString(), product_total.getText()
        );

        connect_passenger.getCartCount(getStore_Name);
    }//GEN-LAST:event_add_to_cart_bttnActionPerformed

    private void my_cart_txtMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_my_cart_txtMouseReleased
        connect_passenger.getCart(getStore_Name);
        viewCart_store_name.setText(viewProducts_store_name.getText());;

        viewCart.setVisible(true);
        viewCart.pack();
        viewCart.setLocationRelativeTo(this);

        viewProducts.setVisible(false);
    }//GEN-LAST:event_my_cart_txtMouseReleased

    private void getValueToText(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_getValueToText
        try {

            double price = Double.parseDouble(product_price.getText());
            double quantity = Double.parseDouble(product_quantity.getValue().toString());
            double total = price * quantity;

            product_total.setText(total + "");

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_getValueToText

    private void viewCart_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCart_removeActionPerformed
        DefaultTableModel model = (DefaultTableModel) Main_Frame.viewCart_table.getModel();
        model.setRowCount(0);

        connect_passenger.removeCartProduct(getProduct_Name);
        connect_passenger.getCart(getStore_Name);
    }//GEN-LAST:event_viewCart_removeActionPerformed

    private void viewCart_take_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCart_take_outActionPerformed
        connect_passenger.takeOutOrders();
        connect_passenger.getPassengerOrders(getStore_Name);
    }//GEN-LAST:event_viewCart_take_outActionPerformed

    private void vendor_reg_bttnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendor_reg_bttnMouseReleased
        registerVendor();
        vendor_reg_erase();
        vendor_registration.setVisible(false);
        login_txt_username.requestFocus(true);
        this.setVisible(true);
    }//GEN-LAST:event_vendor_reg_bttnMouseReleased

    private void vendor_reg_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendor_reg_logoutMousePressed
        passenger_reg_logout.setBackground(maroon);
        passenger_reg_logout.setForeground(Color.WHITE);
    }//GEN-LAST:event_vendor_reg_logoutMousePressed

    private void vendor_reg_logoutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendor_reg_logoutMouseReleased
        passenger_reg_logout.setBackground(Color.WHITE);
        passenger_reg_logout.setForeground(Color.BLACK);

        int diagResult = JOptionPane.showConfirmDialog(null, "Changes you made may not be saved. \n Do you want to exit window?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (diagResult == JOptionPane.YES_OPTION) {

            login_txt_username.setText("");
            login_txt_password.setText("");

            vendor_registration.setVisible(false);
            login_txt_username.requestFocus(true);
            this.setVisible(true);

        } else {

        }
    }//GEN-LAST:event_vendor_reg_logoutMouseReleased

    private void vendor_upload_photoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendor_upload_photoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_vendor_upload_photoMousePressed

    private void vendor_upload_photoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendor_upload_photoMouseReleased
        vendor_upload_photo.setForeground(red);
        connect_vendor.vendor_setPhoto(vendor_txt_lastname.getText());
    }//GEN-LAST:event_vendor_upload_photoMouseReleased

    private void vendor_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendor_logoutMousePressed
        vendor_logout.setBackground(maroon);
        vendor_logout.setForeground(Color.WHITE);
    }//GEN-LAST:event_vendor_logoutMousePressed

    private void vendor_logoutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendor_logoutMouseReleased
        vendor_logout.setBackground(Color.WHITE);
        vendor_logout.setForeground(Color.BLACK);

        int diagResult = JOptionPane.showConfirmDialog(null, "Do you want to log-out?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (diagResult == JOptionPane.YES_OPTION) {

            login_txt_username.setText("");
            login_txt_password.setText("");

            vendor_dashboard.setVisible(false);
            login_txt_username.requestFocus(true);
            this.setVisible(true);

        } else {

        }
    }//GEN-LAST:event_vendor_logoutMouseReleased

    private void bttn_add_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_add_productActionPerformed
        connect_vendor.checkStore(store_name_txt.getText());
    }//GEN-LAST:event_bttn_add_productActionPerformed

    private void product_reg_bttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_reg_bttnActionPerformed
        DefaultTableModel model = (DefaultTableModel) Main_Frame.vendor_products.getModel();
        model.setRowCount(0);

        connect_vendor.registerProduct(
                store_name_txt.getText(),
                product_reg_name.getText(),
                product_reg_type.getText(),
                product_reg_price.getText()
        );
    }//GEN-LAST:event_product_reg_bttnActionPerformed

    private void product_reg_backMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_reg_backMouseReleased
        vendor_dashboard.setVisible(true);
        vendor_dashboard.pack();
        vendor_dashboard.setLocationRelativeTo(this);

        product_registration.setVisible(false);
    }//GEN-LAST:event_product_reg_backMouseReleased

    private void product_reg_photoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_reg_photoMouseReleased

    }//GEN-LAST:event_product_reg_photoMouseReleased

    private void product_reg_bttn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_reg_bttn1ActionPerformed
        connect_vendor.registerStore(
                store_reg_name.getText(),
                store_reg_address.getText(),
                store_reg_phone.getText()
        );
    }//GEN-LAST:event_product_reg_bttn1ActionPerformed

    private void store_reg_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_store_reg_backActionPerformed
        vendor_dashboard.setVisible(true);
        vendor_dashboard.pack();
        vendor_dashboard.setLocationRelativeTo(this);

        store_reg_name.setText("");
        store_reg_address.setText("");
        store_reg_phone.setText("");

        store_registration.setVisible(false);
    }//GEN-LAST:event_store_reg_backActionPerformed

    private void registerFranchise_tricycleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerFranchise_tricycleActionPerformed
        connect_admin.setFranchise(
                addFranchise_tricycle_id.getText(),
                addFranchise_reg_start.getText(),
                addFranchise_reg_end.getText()
        );
    }//GEN-LAST:event_registerFranchise_tricycleActionPerformed

    private void setFranchise_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setFranchise_backActionPerformed
        admin_dashboard.setVisible(true);
        admin_dashboard.pack();
        admin_dashboard.setLocationRelativeTo(this);

        addFranchise.setVisible(false);
    }//GEN-LAST:event_setFranchise_backActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String tricycle_id = JOptionPane.showInputDialog(null, "Enter Tricycle ID:", "INPUT", JOptionPane.PLAIN_MESSAGE);
        DefaultTableModel model = (DefaultTableModel) Main_Frame.franchises_table.getModel();
        model.setRowCount(0);
        connect_admin.getTricycleID(tricycle_id);
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel active_now_label2;
    private javax.swing.JLabel active_now_label3;
    private javax.swing.JLabel active_now_label4;
    private javax.swing.JLabel active_now_label5;
    public static javax.swing.JFrame addFranchise;
    private javax.swing.JTextField addFranchise_reg_end;
    private javax.swing.JTextField addFranchise_reg_start;
    public static javax.swing.JLabel addFranchise_tricycle_id;
    public static javax.swing.JPanel add_to_cart;
    private javax.swing.JButton add_to_cart_bttn;
    public static javax.swing.JFrame admin_dashboard;
    private javax.swing.JLabel admin_date;
    private javax.swing.JButton admin_logout;
    public static javax.swing.JLabel admin_photo;
    private javax.swing.JLabel admin_time;
    public static javax.swing.JLabel admin_txt_address;
    public static javax.swing.JLabel admin_txt_firstname;
    public static javax.swing.JLabel admin_txt_id;
    public static javax.swing.JLabel admin_txt_lastname;
    public static javax.swing.JLabel admin_txt_middlename;
    public static javax.swing.JLabel admin_txt_phone;
    private javax.swing.JLabel admin_upload_photo;
    private javax.swing.JPanel background1;
    private javax.swing.JPanel background10;
    private javax.swing.JPanel background11;
    private javax.swing.JPanel background12;
    private javax.swing.JPanel background13;
    private javax.swing.JPanel background14;
    private javax.swing.JPanel background15;
    private javax.swing.JPanel background2;
    private javax.swing.JPanel background3;
    private javax.swing.JPanel background4;
    private javax.swing.JPanel background5;
    private javax.swing.JPanel background6;
    private javax.swing.JPanel background7;
    private javax.swing.JPanel background8;
    private javax.swing.JPanel background9;
    private javax.swing.JButton bttn_add_product;
    private javax.swing.JButton bttn_view_stores;
    public static javax.swing.JFrame driver_dashboard;
    private javax.swing.JLabel driver_date;
    public static javax.swing.JFrame driver_found;
    private javax.swing.JButton driver_found_bttn_ok;
    public static javax.swing.JLabel driver_found_driver_name;
    public static javax.swing.JLabel driver_found_driver_photo;
    private javax.swing.JPanel driver_found_panel;
    public static javax.swing.JLabel driver_found_tricycle_number;
    private javax.swing.JButton driver_logout;
    private javax.swing.JPanel driver_panel1;
    private javax.swing.JPanel driver_panel2;
    private javax.swing.JPanel driver_panel3;
    private javax.swing.JPanel driver_panel4;
    private javax.swing.JPanel driver_panel5;
    private javax.swing.JPanel driver_panel6;
    private javax.swing.JPanel driver_panel7;
    private javax.swing.JPanel driver_panel8;
    public static javax.swing.JLabel driver_photo;
    private javax.swing.JTextField driver_reg_address;
    private javax.swing.JButton driver_reg_bttn;
    private javax.swing.JLabel driver_reg_date;
    private javax.swing.JTextField driver_reg_firstname;
    private javax.swing.JTextField driver_reg_gender;
    private javax.swing.JTextField driver_reg_lastname;
    private javax.swing.JButton driver_reg_logout;
    private javax.swing.JTextField driver_reg_middlename;
    private javax.swing.JTextField driver_reg_password;
    private javax.swing.JTextField driver_reg_phone;
    private javax.swing.JLabel driver_reg_time;
    private javax.swing.JTextField driver_reg_username;
    private javax.swing.JFrame driver_registration;
    private javax.swing.JLabel driver_time;
    public static javax.swing.JLabel driver_txt_address;
    public static javax.swing.JLabel driver_txt_firstname;
    public static javax.swing.JLabel driver_txt_id;
    public static javax.swing.JLabel driver_txt_lastname;
    public static javax.swing.JLabel driver_txt_middlename;
    public static javax.swing.JLabel driver_txt_phone;
    public static javax.swing.JLabel driver_txt_tricycle_id;
    private javax.swing.JLabel driver_upload_photo;
    public static javax.swing.JTable franchises_table;
    private javax.swing.JLabel green_active_icon2;
    private javax.swing.JLabel green_active_icon3;
    private javax.swing.JLabel green_active_icon4;
    private javax.swing.JLabel green_active_icon5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane10;
    private javax.swing.JLayeredPane jLayeredPane11;
    private javax.swing.JLayeredPane jLayeredPane12;
    private javax.swing.JLayeredPane jLayeredPane13;
    private javax.swing.JLayeredPane jLayeredPane14;
    private javax.swing.JLayeredPane jLayeredPane15;
    private javax.swing.JLayeredPane jLayeredPane16;
    private javax.swing.JLayeredPane jLayeredPane17;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JLayeredPane jLayeredPane8;
    private javax.swing.JLayeredPane jLayeredPane9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel left_panel2;
    private javax.swing.JPanel left_panel3;
    private javax.swing.JPanel left_panel4;
    private javax.swing.JPanel left_panel5;
    private javax.swing.JLabel login_new_account;
    private javax.swing.JPasswordField login_txt_password;
    private javax.swing.JTextField login_txt_username;
    private javax.swing.JButton login_user;
    private javax.swing.JButton myCart_back;
    public static javax.swing.JLabel myCart_count;
    private javax.swing.JLabel my_cart_txt;
    public static javax.swing.JLabel my_products_txt;
    private javax.swing.JScrollPane pane_deliveries;
    private javax.swing.JScrollPane pane_passengers;
    private javax.swing.JScrollPane pane_passengers1;
    private javax.swing.JPanel panel_login;
    public static javax.swing.JButton passenger_cancel;
    public static javax.swing.JFrame passenger_dashboard;
    private javax.swing.JLabel passenger_date;
    public static javax.swing.JPanel passenger_destination_details;
    public static javax.swing.JLabel passenger_distance;
    public static javax.swing.JPanel passenger_driver_details;
    public static javax.swing.JLabel passenger_driver_loading;
    public static javax.swing.JLabel passenger_driver_name;
    public static javax.swing.JLabel passenger_driver_name_field;
    public static javax.swing.JTextField passenger_end;
    public static javax.swing.JLabel passenger_est_time;
    public static javax.swing.JButton passenger_go;
    private javax.swing.JButton passenger_logout;
    public static javax.swing.JTable passenger_orders;
    public static javax.swing.JLabel passenger_payment;
    public static javax.swing.JPanel passenger_payment_details;
    public static javax.swing.JLabel passenger_photo;
    private javax.swing.JTextField passenger_reg_address;
    private javax.swing.JButton passenger_reg_bttn;
    private javax.swing.JLabel passenger_reg_date;
    private javax.swing.JTextField passenger_reg_firstname;
    private javax.swing.JTextField passenger_reg_gender;
    private javax.swing.JTextField passenger_reg_lastname;
    private javax.swing.JButton passenger_reg_logout;
    private javax.swing.JTextField passenger_reg_middlename;
    private javax.swing.JTextField passenger_reg_password;
    private javax.swing.JTextField passenger_reg_phone;
    private javax.swing.JLabel passenger_reg_time;
    private javax.swing.JTextField passenger_reg_username;
    private javax.swing.JFrame passenger_registration;
    public static javax.swing.JTextField passenger_start;
    public static javax.swing.JTable passenger_store_table;
    private javax.swing.JLabel passenger_time;
    public static javax.swing.JLabel passenger_traffic;
    public static javax.swing.JLabel passenger_tricycle_number;
    public static javax.swing.JLabel passenger_tricycle_number_field;
    public static javax.swing.JLabel passenger_txt_address;
    public static javax.swing.JLabel passenger_txt_firstname;
    public static javax.swing.JLabel passenger_txt_id;
    public static javax.swing.JLabel passenger_txt_lastname;
    public static javax.swing.JLabel passenger_txt_middlename;
    public static javax.swing.JLabel passenger_txt_phone;
    private javax.swing.JLabel passenger_upload_photo;
    public static javax.swing.JLabel passenger_weather;
    private javax.swing.JPanel password_panel;
    public static javax.swing.JPanel product_details;
    public static javax.swing.JLabel product_name;
    public static javax.swing.JLabel product_photo;
    public static javax.swing.JLabel product_price;
    public static javax.swing.JSpinner product_quantity;
    private javax.swing.JButton product_reg_back;
    private javax.swing.JButton product_reg_bttn;
    private javax.swing.JButton product_reg_bttn1;
    private javax.swing.JTextField product_reg_name;
    public static javax.swing.JLabel product_reg_photo;
    private javax.swing.JTextField product_reg_price;
    private javax.swing.JTextField product_reg_type;
    public static javax.swing.JFrame product_registration;
    public static javax.swing.JLabel product_total;
    public static javax.swing.JLabel product_type;
    private javax.swing.JButton registerFranchise_tricycle;
    private javax.swing.JPopupMenu remove_product;
    private javax.swing.JPanel right_panel2;
    private javax.swing.JPanel right_panel3;
    private javax.swing.JPanel right_panel4;
    private javax.swing.JPanel right_panel5;
    private javax.swing.JPanel right_panel6;
    private javax.swing.JButton setFranchise_back;
    private javax.swing.JLabel std_course_year_label3;
    private javax.swing.JPanel std_photo_panel2;
    private javax.swing.JPanel std_photo_panel3;
    private javax.swing.JPanel std_photo_panel4;
    private javax.swing.JPanel std_photo_panel5;
    private javax.swing.JPanel std_photo_panel6;
    private javax.swing.JPanel std_photo_panel7;
    public static javax.swing.JLabel store_name_txt;
    private javax.swing.JPopupMenu store_pop;
    private javax.swing.JTextField store_reg_address;
    private javax.swing.JButton store_reg_back;
    private javax.swing.JTextField store_reg_name;
    private javax.swing.JTextField store_reg_phone;
    public static javax.swing.JFrame store_registration;
    public static javax.swing.JTable table_deliveries;
    public static javax.swing.JTable table_passengers;
    public static javax.swing.JTable table_tricycles;
    private javax.swing.JPopupMenu tricycles_pop;
    private javax.swing.JPanel username_panel;
    public static javax.swing.JFrame vendor_dashboard;
    private javax.swing.JLabel vendor_date;
    private javax.swing.JButton vendor_logout;
    public static javax.swing.JLabel vendor_photo;
    public static javax.swing.JTable vendor_products;
    private javax.swing.JTextField vendor_reg_address;
    private javax.swing.JButton vendor_reg_bttn;
    private javax.swing.JLabel vendor_reg_date;
    private javax.swing.JTextField vendor_reg_firstname;
    private javax.swing.JTextField vendor_reg_gender;
    private javax.swing.JTextField vendor_reg_lastname;
    private javax.swing.JButton vendor_reg_logout;
    private javax.swing.JTextField vendor_reg_middlename;
    private javax.swing.JTextField vendor_reg_password;
    private javax.swing.JTextField vendor_reg_phone;
    private javax.swing.JLabel vendor_reg_time;
    private javax.swing.JTextField vendor_reg_username;
    private javax.swing.JFrame vendor_registration;
    private javax.swing.JLabel vendor_time;
    public static javax.swing.JLabel vendor_txt_address;
    public static javax.swing.JLabel vendor_txt_firstname;
    public static javax.swing.JLabel vendor_txt_id;
    public static javax.swing.JLabel vendor_txt_lastname;
    public static javax.swing.JLabel vendor_txt_middlename;
    public static javax.swing.JLabel vendor_txt_phone;
    private javax.swing.JLabel vendor_upload_photo;
    public static javax.swing.JFrame viewCart;
    private javax.swing.JMenuItem viewCart_remove;
    public static javax.swing.JLabel viewCart_store_name;
    public static javax.swing.JLabel viewCart_store_name1;
    public static javax.swing.JLabel viewCart_store_name2;
    public static javax.swing.JLabel viewCart_store_name3;
    public static javax.swing.JTable viewCart_table;
    private javax.swing.JButton viewCart_take_out;
    public static javax.swing.JLabel viewCart_total;
    public static javax.swing.JFrame viewProducts;
    private javax.swing.JButton viewProducts_back;
    private javax.swing.JLabel viewProducts_store_name;
    public static javax.swing.JTable viewProducts_table;
    private javax.swing.JButton viewStore_back;
    private javax.swing.JFrame viewStores;
    private javax.swing.JFrame viewTricycle;
    private javax.swing.JButton viewTricycle_back;
    public static javax.swing.JLabel viewTricycle_driver_id;
    public static javax.swing.JLabel viewTricycle_franchise_id;
    private javax.swing.JPanel viewTricycle_panel;
    private javax.swing.JPanel viewTricycle_panel2;
    private javax.swing.JPanel viewTricycle_panel3;
    private javax.swing.JPanel viewTricycle_panel4;
    private javax.swing.JPanel viewTricycle_panel5;
    private javax.swing.JPanel viewTricycle_panel6;
    public static javax.swing.JLabel viewTricycle_tricycle_color;
    public static javax.swing.JLabel viewTricycle_tricycle_id;
    public static javax.swing.JComboBox<String> viewTricycle_tricycle_status;
    private javax.swing.JButton viewTricycle_update;
    private javax.swing.JMenuItem view_products;
    private javax.swing.JMenuItem view_tricycle_record;
    // End of variables declaration//GEN-END:variables
}
