package Paket16_GuıBitirme.GUI;

import Paket16_GuıBitirme.Helper.*;
import Paket16_GuıBitirme.Model.feature;
import Paket16_GuıBitirme.Model.pansiyonFeature;
import Paket16_GuıBitirme.Model.room;

import javax.swing.*;

public class AddRoom extends JFrame{
    private JPanel wrapper;
    private JComboBox cmb_room_hotel;
    private JComboBox cmb_room_fearureType;
    private JTextField fld_room_roomNumber;
    private JTextField fld_room_badNumber;
    private JTextField fld_room_m2;
    private JComboBox cmb_room_type;
    private JButton btn_room_add;
    private JButton btn_room_singout;
    private JTextField fld_room_television;
    private JTextField fld_room_bar;
    private JTextField fld_room_console;
    private JTextField fld_room_safe;
    private JTextField fld_room_projection;

    public AddRoom(){
        add(wrapper);
        setSize(500,700);
        setLocation(Help.screenCenter("x",getSize()),Help.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        loadHotelCombo();

        btn_room_singout.addActionListener(e -> {
            dispose();
            OperatorGuı op=new OperatorGuı();
        });
        btn_room_add.addActionListener(e -> {

            if (Help.isEmpty(fld_room_badNumber) || Help.isEmpty(fld_room_roomNumber) || Help.isEmpty(fld_room_m2)){
                Help.showMSG("fill");
            }else{
                try {
                    ITEM hotel= (ITEM) cmb_room_hotel.getSelectedItem();
                    String fearureType= cmb_room_fearureType.getSelectedItem().toString();
                    String type=cmb_room_type.getSelectedItem().toString();
                    int television= Integer.parseInt(fld_room_television.getText());
                    int bar= Integer.parseInt(fld_room_bar.getText());
                    int console= Integer.parseInt(fld_room_console.getText());
                    int safe= Integer.parseInt(fld_room_safe.getText());
                    int pro=Integer.parseInt(fld_room_projection.getText());
                    int roomNumber= Integer.parseInt(fld_room_roomNumber.getText());
                    int badNumber= Integer.parseInt(fld_room_badNumber.getText());
                    int m2= Integer.parseInt(fld_room_m2.getText());
                    feature fea=feature.getFetchT(hotel.getKey(),fearureType);
                    if (roomNumber==0 || badNumber==0 || m2==0){
                        Help.showMSG("Oda sayısı, yatak sayısı ve odanın alanı sıfır ılamaz!");
                    }else if (bar<0 || console<0 || safe<0 || pro<0 || television<0){
                        Help.showMSG("Bar,televizyon,oyun konsolu,kasa,projeksiyon sayısı sıfırdan küçük olamaz.");
                    }else if (fea==null){
                        Help.showMSG("Bu otelin bu tipte bir pansiyonu yokyur!");
                    }else{
                        boolean a=true;
                        for (room r: room.getList()){
                            if (r.getFeature_id()==fea.getId() && r.getType().equals(type)){
                                a=false;
                            }
                        }
                        if (a){
                            if (room.add(fea.getId(),roomNumber,badNumber,television,bar,console,m2,safe,pro,type)){
                                Help.showMSG("done");
                                fld_room_m2.setText(null);
                                fld_room_roomNumber.setText(null);
                                fld_room_badNumber.setText(null);
                                fld_room_projection.setText(null);
                                fld_room_bar.setText(null);
                                fld_room_console.setText(null);
                                fld_room_safe.setText(null);
                                fld_room_television.setText(null);
                            }else {
                                Help.showMSG("error");
                            }
                        }else {
                            Help.showMSG("Bu otelin bu pansiyon tipindeki bu tipteki odası vardır!");
                        }


                    }
                }catch (Exception ex){
                    Help.showMSG("Lütfen sayı giriniz!");
                }
            }
        });
    }


    private void loadHotelCombo(){
        cmb_room_hotel.removeAllItems();
        for (pansiyonFeature obj: pansiyonFeature.getList()){
            cmb_room_hotel.addItem(new ITEM(obj.getId(),obj.getName()));
        }
    }
}
