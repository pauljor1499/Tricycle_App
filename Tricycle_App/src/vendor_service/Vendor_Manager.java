package vendor_service;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import main.Main_Frame;

public class Vendor_Manager {

    public static void addVendorData(String id, String firstname, String middlename, String lastname, String address, String phone, String photo) {
        Main_Frame.vendor_txt_id.setText(id);
        Main_Frame.vendor_txt_firstname.setText(firstname);
        Main_Frame.vendor_txt_middlename.setText(middlename);
        Main_Frame.vendor_txt_lastname.setText(lastname);
        Main_Frame.vendor_txt_address.setText(address);
        Main_Frame.vendor_txt_phone.setText(phone);

        ImageIcon icon = new ImageIcon(photo);
        Image image = icon.getImage().getScaledInstance(130, 140, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        Main_Frame.vendor_photo.setText("");
        Main_Frame.vendor_photo.setIcon(icon);
    }

    public static void setStoreName(String store_name) {
        Main_Frame.store_name_txt.setText(store_name);
    }

    public static void viewStoreReg() {
        Main_Frame.store_registration.setVisible(true);
        Main_Frame.store_registration.pack();
        Main_Frame.store_registration.setLocationRelativeTo(Main_Frame.my_products_txt);

        Main_Frame.vendor_dashboard.setVisible(false);
    }

    public static void viewProductReg() {
        Main_Frame.product_registration.setVisible(true);
        Main_Frame.product_registration.pack();
        Main_Frame.product_registration.setLocationRelativeTo(Main_Frame.my_products_txt);

        Main_Frame.vendor_dashboard.setVisible(false);
    }

    public static void hideProductReg() {
        Main_Frame.vendor_dashboard.setVisible(true);
        Main_Frame.product_registration.setVisible(false);
    }

    public static void setVendorProducts(Object[] dataRow) {
        DefaultTableModel model = (DefaultTableModel) Main_Frame.vendor_products.getModel();
        model.addRow(dataRow);
    }

    public static void setVendorPhoto(String PassengerPhoto_filename) {
        ImageIcon icon = new ImageIcon(PassengerPhoto_filename);
        Image image = icon.getImage().getScaledInstance(130, 140, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        Main_Frame.vendor_photo.setText("");
        Main_Frame.vendor_photo.setIcon(icon);
    }

}
