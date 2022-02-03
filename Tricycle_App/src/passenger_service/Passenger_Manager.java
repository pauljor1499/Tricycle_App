package passenger_service;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import main.Main_Frame;

public class Passenger_Manager {

    public static void addPassengerData(String id, String firstname, String middlename, String lastname, String address, String phone, String photo) {
        Main_Frame.passenger_txt_id.setText(id);
        Main_Frame.passenger_txt_firstname.setText(firstname);
        Main_Frame.passenger_txt_middlename.setText(middlename);
        Main_Frame.passenger_txt_lastname.setText(lastname);
        Main_Frame.passenger_txt_address.setText(address);
        Main_Frame.passenger_txt_phone.setText(phone);

        ImageIcon icon = new ImageIcon(photo);
        Image image = icon.getImage().getScaledInstance(130, 140, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        Main_Frame.passenger_photo.setText("");
        Main_Frame.passenger_photo.setIcon(icon);
    }

    public static void setPassengerPhoto(String PassengerPhoto_filename) {
        ImageIcon icon = new ImageIcon(PassengerPhoto_filename);
        Image image = icon.getImage().getScaledInstance(130, 140, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        Main_Frame.passenger_photo.setText("");
        Main_Frame.passenger_photo.setIcon(icon);
    }

    public static void setTripData(String start, String end, String weather, String traffic, String payment, String distance, String time) {
        Main_Frame.passenger_go.setEnabled(false);
        Main_Frame.passenger_cancel.setEnabled(true);
        Main_Frame.passenger_start.setEnabled(false);
        Main_Frame.passenger_end.setEnabled(false);

        Main_Frame.passenger_destination_details.setVisible(true);
        Main_Frame.passenger_payment_details.setVisible(true);
        Main_Frame.passenger_driver_details.setVisible(true);

        Main_Frame.passenger_start.setText(start);
        Main_Frame.passenger_end.setText(end);
        Main_Frame.passenger_weather.setText(weather);
        Main_Frame.passenger_traffic.setText(traffic);
        Main_Frame.passenger_payment.setText("₱" + payment);
        Main_Frame.passenger_distance.setText(distance);
        Main_Frame.passenger_est_time.setText(time);
    }

    public static void setTripData_Empty() {
        Main_Frame.passenger_go.setEnabled(true);
        Main_Frame.passenger_cancel.setEnabled(false);
        Main_Frame.passenger_start.setEnabled(true);
        Main_Frame.passenger_end.setEnabled(true);

        Main_Frame.passenger_start.setText("");
        Main_Frame.passenger_end.setText("");
        Main_Frame.passenger_destination_details.setVisible(false);
        Main_Frame.passenger_payment_details.setVisible(false);
        Main_Frame.passenger_driver_details.setVisible(false);
    }

    public static void driverFound(String driver_name, String driver_photo, String tricycle_number) {
        Main_Frame.driver_found.setVisible(true);
        Main_Frame.driver_found.pack();
        Main_Frame.driver_found.setLocationRelativeTo(Main_Frame.passenger_start);

        Main_Frame.passenger_driver_name.setVisible(true);
        Main_Frame.passenger_driver_name_field.setVisible(true);
        Main_Frame.passenger_tricycle_number.setVisible(true);
        Main_Frame.passenger_tricycle_number_field.setVisible(true);

        //GIF hide
        Main_Frame.passenger_driver_loading.setVisible(false);

        Main_Frame.passenger_driver_name_field.setText(driver_name);
        Main_Frame.passenger_tricycle_number_field.setText(tricycle_number);

        ImageIcon icon = new ImageIcon(driver_photo);
        Image image = icon.getImage().getScaledInstance(130, 140, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        //Frame popup
        Main_Frame.driver_found_driver_photo.setIcon(icon);
        Main_Frame.driver_found_driver_name.setText(driver_name);
        Main_Frame.driver_found_tricycle_number.setText(tricycle_number);

        Main_Frame.passenger_dashboard.setEnabled(false);
    }

    public static void searchingDriver() {
        Main_Frame.passenger_driver_name.setVisible(false);
        Main_Frame.passenger_driver_name_field.setVisible(false);
        Main_Frame.passenger_tricycle_number.setVisible(false);
        Main_Frame.passenger_tricycle_number_field.setVisible(false);

        Main_Frame.passenger_driver_loading.setVisible(true);

        Main_Frame.passenger_driver_name_field.setText("");
        Main_Frame.passenger_tricycle_number_field.setText("");
    }

    public static void setStoreData(Object[] dataRow) {
        DefaultTableModel model = (DefaultTableModel) Main_Frame.passenger_store_table.getModel();
        model.addRow(dataRow);
    }

    public static void setStoreProducts(Object[] dataRow) {
        DefaultTableModel model = (DefaultTableModel) Main_Frame.viewProducts_table.getModel();
        model.addRow(dataRow);
    }

    public static void setProductDetails(String name, String type, String price, String photo) {

        //enable panel inside the products frame
        Main_Frame.product_details.setVisible(true);
        Main_Frame.add_to_cart.setVisible(true);

        //reset the spinner value
        Main_Frame.product_quantity.setValue(1);

        Main_Frame.product_name.setText(name);
        Main_Frame.product_type.setText(type);
        Main_Frame.product_price.setText(price);
        Main_Frame.product_total.setText(price);

        //Set product photo
        ImageIcon icon = new ImageIcon(photo);
        Image image = icon.getImage().getScaledInstance(130, 140, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        Main_Frame.product_photo.setText("");
        Main_Frame.product_photo.setIcon(icon);

    }

    public static void setCartProducts(Object[] dataRow, double total) {
        DefaultTableModel model = (DefaultTableModel) Main_Frame.viewCart_table.getModel();
        model.addRow(dataRow);

        Main_Frame.viewCart_total.setText(total + "");
    }

    public static void setCartProducts(double total) {

        Main_Frame.viewCart_total.setText(total + "");
    }

    public static void setCartCount(int count) {
        Main_Frame.myCart_count.setText(count + "");
    }

    public static void setCartEmpty() {
        DefaultTableModel model = (DefaultTableModel) Main_Frame.viewCart_table.getModel();
        model.setRowCount(0);

        Main_Frame.viewCart.setVisible(false);

        Main_Frame.passenger_dashboard.setVisible(true);
        Main_Frame.passenger_dashboard.pack();
        Main_Frame.passenger_dashboard.setLocationRelativeTo(Main_Frame.passenger_start);
    }

    public static void setPassengerOrders(Object[] dataRow) {
        DefaultTableModel model = (DefaultTableModel) Main_Frame.passenger_orders.getModel();
        model.addRow(dataRow);
    }
}
