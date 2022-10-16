package Paket16_GuıBitirme.Helper;

import javax.swing.*;
import java.awt.*;

public class Help {
    public static int screenCenter(String eksen, Dimension size){
        int point;
        switch (eksen){
            case "x": point=(Toolkit.getDefaultToolkit().getScreenSize().width-size.width )/2;
                 break;
            case "y": point=(Toolkit.getDefaultToolkit().getScreenSize().height-size.height)/2;
                break;
            default: point=0;
        }
        return point;
    }

    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
            if (info.getName().equals("Nimbus")){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                }catch (Exception ex){

                }
                break;
            }
        }
    }

    public static boolean isEmpty(JTextField field){
        return field.getText().trim().isEmpty(); // trim boşluk varsa siler
    }

    public static void showMSG(String str){
        String msg;
        String title;
        switch (str){
            case "fill":
                msg="Lütfen tüm alanları doldurunuz! ";
                title="Hata";
                break;
            case "done":
                msg="İşlem başarılı! ";
                title="Sonuç";
                break;
            case "error":
                msg="Bir hata oluştu";
                title="Hata";
            default:
                msg=str;
                title="Mesaj";
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
}
