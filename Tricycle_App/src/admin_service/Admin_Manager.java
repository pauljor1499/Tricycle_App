package admin_service;

//import main package
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import main.Main_Frame;

public class Admin_Manager {

    public static void viewAdminData(String id, String firstname, String middlename, String lastname, String address, String phone, String photo) {
        Main_Frame.admin_txt_id.setText(id);
        Main_Frame.admin_txt_firstname.setText(firstname);
        Main_Frame.admin_txt_middlename.setText(middlename);
        Main_Frame.admin_txt_lastname.setText(lastname);
        Main_Frame.admin_txt_address.setText(address);
        Main_Frame.admin_txt_phone.setText(phone);

        ImageIcon icon = new ImageIcon(photo);
        Image image = icon.getImage().getScaledInstance(130, 140, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        Main_Frame.admin_photo.setText("");
        Main_Frame.admin_photo.setIcon(icon);
    }

    public static void viewTricycleData(Object[] dataRow) {
        DefaultTableModel model = (DefaultTableModel) Main_Frame.table_tricycles.getModel();
        model.addRow(dataRow);
    }

    public static void setAdminPhoto(String adminPhoto_filename) {
        ImageIcon icon = new ImageIcon(adminPhoto_filename);
        Image image = icon.getImage().getScaledInstance(130, 140, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        Main_Frame.admin_photo.setText("");
        Main_Frame.admin_photo.setIcon(icon);
    }

    public static void viewTricycleRecord(String tricycle_id, String franchise_id, String driver_id,
            String tricycle_color, String tricycle_status) {

        Main_Frame.viewTricycle_tricycle_id.setText(tricycle_id);
        Main_Frame.viewTricycle_franchise_id.setText(franchise_id);
        Main_Frame.viewTricycle_driver_id.setText(driver_id);
        Main_Frame.viewTricycle_tricycle_color.setText(tricycle_color);

        if (tricycle_status.equals("Enable")) {
            Main_Frame.viewTricycle_tricycle_status.removeAllItems();
            Main_Frame.viewTricycle_tricycle_status.addItem("Enable");
            Main_Frame.viewTricycle_tricycle_status.addItem("Disable");

        } else if (tricycle_status.equals("Disable")) {
            Main_Frame.viewTricycle_tricycle_status.removeAllItems();
            Main_Frame.viewTricycle_tricycle_status.addItem("Disable");
            Main_Frame.viewTricycle_tricycle_status.addItem("Enable");
        }
    }

    public static void viewTricycleData(String id) {
        Main_Frame.addFranchise_tricycle_id.setText(id);

        Main_Frame.admin_dashboard.setVisible(false);

        Main_Frame.addFranchise.setVisible(true);
        Main_Frame.addFranchise.pack();
        Main_Frame.addFranchise.setLocationRelativeTo(Main_Frame.passenger_start);
    }

    public static void setFranchiseData(Object[] dataRow) {
        DefaultTableModel model = (DefaultTableModel) Main_Frame.franchises_table.getModel();
        model.addRow(dataRow);
    }

}
