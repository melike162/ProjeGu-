package Paket16_GuıBitirme.GUI;

import Paket16_GuıBitirme.Helper.Help;
import Paket16_GuıBitirme.Helper.ITEM;
import Paket16_GuıBitirme.Model.*;

import javax.swing.*;

public class ResultGuı extends JFrame {

    private JPanel wrapper;
    private JTextField fld_hotelName;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_region;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_stare;
    private JTextField fld_wf;
    private JTextField fld_park;
    private JTextField fld_pool;
    private JTextField fld_spa;
    private JTextField fld_service;
    private JTextField fld_center;
    private JTextField fld_corcierge;
    private JButton btn_save;
    private JButton btn_return;

    public ResultGuı(String ina, String outa, int adult, int child, ITEM hotel , ITEM region, int total, int id){
        add(wrapper);
        setSize(500,500);
        setLocation(Help.screenCenter("x",getSize()),Help.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        for (date d: date.getList()){
            if (d.getId()==id){
                room r=room.getFetch2(d.getRoom_id());
                feature f=feature.getFetch2(r.getFeature_id());
                pansiyonFeature p=pansiyonFeature.getFetch(f.getPansiyon_id());
                fld_hotelName.setText(p.getName());
                fld_hotel_address.setText(p.getAdress());
                fld_hotel_city.setText(p.getCity());
                fld_hotel_mail.setText(p.getMail());
                fld_hotel_phone.setText(p.getPhone());
                fld_hotel_region.setText(p.getRegion());
                fld_hotel_stare.setText(String.valueOf(p.getStare()));

                fld_center.setText(isWhat(f.getFitnessCenter()));
                fld_corcierge.setText(isWhat(f.getHotelConcierge()));
                fld_park.setText(isWhat(f.getFreePark()));
                fld_service.setText(isWhat(f.getRoomService()));
                fld_pool.setText(isWhat(f.getSwimPool()));
                fld_spa.setText(isWhat(f.getSPA()));
                fld_wf.setText(isWhat(f.getFreeWF()));

            }
        }
        btn_return.addActionListener(e -> {
            dispose();
            String[] in=ina.split("/");
            String[] out=outa.split("/");
            int in0=Integer.parseInt(in[0]);
            int in1=Integer.parseInt(in[1]);
            int in2=Integer.parseInt(in[2]);
            int out0=Integer.parseInt(out[0]);
            int out1=Integer.parseInt(out[1]);
            int out2=Integer.parseInt(out[2]);
            Help.setLayout();
            CaseGuı c=new CaseGuı(in0,in1,in2,out0,out1,out2,adult,child,hotel,region);
        });
        btn_save.addActionListener(e -> {
            dispose();
            ResultGuı2 r=new ResultGuı2(ina,outa,adult,child,hotel,region,total,id);
        });
    }
    private String isWhat(int i){
        if (i==1){
            return "Evet";
        }else if (i==0){
            return "Hayır";
        }
        return "Hayır";
    }

}
