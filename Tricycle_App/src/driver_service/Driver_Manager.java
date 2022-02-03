package driver_service;

//import main class
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

//import main frame
import main.Main_Frame;

public class Driver_Manager {

    //DRIVER
    public static void addDriverData(String id, String firstname, String middlename, String lastname, String address, String phone, String photo) {
        Main_Frame.driver_txt_id.setText(id);
        Main_Frame.driver_txt_firstname.setText(firstname);
        Main_Frame.driver_txt_middlename.setText(middlename);
        Main_Frame.driver_txt_lastname.setText(lastname);
        Main_Frame.driver_txt_address.setText(address);
        Main_Frame.driver_txt_phone.setText(phone);

        ImageIcon icon = new ImageIcon(photo);
        Image image = icon.getImage().getScaledInstance(130, 140, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        Main_Frame.driver_photo.setText("");
        Main_Frame.driver_photo.setIcon(icon);
    }

    public static void addTricyclePofile(String number) {
        Main_Frame.driver_txt_tricycle_id.setText(number);
    }

    public static void addTripData(Object[] dataRow) {
        DefaultTableModel model = (DefaultTableModel) Main_Frame.table_passengers.getModel();
        model.addRow(dataRow);
    }

    public static void addDeliveriesData(Object[] dataRow) {
        DefaultTableModel model = (DefaultTableModel) Main_Frame.table_deliveries.getModel();
        model.addRow(dataRow);
    }

    public static void setDriverPhoto(String driverPhoto_filename) {
        ImageIcon icon = new ImageIcon(driverPhoto_filename);
        Image image = icon.getImage().getScaledInstance(130, 140, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        Main_Frame.driver_photo.setText("");
        Main_Frame.driver_photo.setIcon(icon);
    }

}
