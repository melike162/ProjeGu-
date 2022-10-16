package Paket16_GuıBitirme.GUI;

import Paket16_GuıBitirme.Helper.Help;
import Paket16_GuıBitirme.Helper.ITEM;
import Paket16_GuıBitirme.Model.pansiyonFeature;

import javax.swing.*;
import java.util.ArrayList;

public class CustomerGuı extends JFrame {

    private JComboBox cmd_hotel;
    private JTextField fld_in;
    private JTextField fld_out;
    private JTextField fld_adult;
    private JTextField fld_chidren;
    private JButton btn_surch;
    private JPanel wrapper;
    private JButton btn_singOut;
    private JComboBox cmd_region;

    public CustomerGuı(){
        add(wrapper);
        setSize(500,510);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Help.screenCenter("x",getSize()),Help.screenCenter("y",getSize()));
        setVisible(true);

        loadCmd();

        btn_surch.addActionListener(e -> {
            if(Help.isEmpty(fld_adult) || Help.isEmpty(fld_chidren) || Help.isEmpty(fld_in) || Help.isEmpty(fld_out)){
                Help.showMSG("Yetişkin sayısı, çocuk sayısı, giriş ve çıkış saatleri boş bırakılamaz! ");
            }else{
                if (Integer.parseInt(fld_adult.getText())<1 || Integer.parseInt(fld_chidren.getText())<0){
                    Help.showMSG("Hata var lütfen kontrol ediniz1!");
                }else{
                    try {
                        String[] in=fld_in.getText().split("/");
                        String[] out=fld_out.getText().split("/");
                        int in0=Integer.parseInt(in[0]);
                        int in1=Integer.parseInt(in[1]);
                        int in2=Integer.parseInt(in[2]);
                        int out0=Integer.parseInt(out[0]);
                        int out1=Integer.parseInt(out[1]);
                        int out2=Integer.parseInt(out[2]);
                        ITEM hotel= (ITEM) cmd_hotel.getSelectedItem();
                        ITEM region= (ITEM) cmd_region.getSelectedItem();
                        if (in.length==3 && out.length==3){
                            if (isDateTrue(in0,in1,in2,out0,out1,out2)){
                                dispose();
                                Help.setLayout();
                                CaseGuı c=new CaseGuı(in0,in1,in2,out0,out1,out2,Integer.parseInt(fld_adult.getText()),Integer.parseInt(fld_chidren.getText()),hotel,region);
                            }else {
                                Help.showMSG("Hata var lütfen kontrol ediniz4!");
                            }

                        }else{
                            Help.showMSG("Hatalı tarih yazımı3");
                        }
                    }catch (Exception ex){
                        Help.showMSG("Hata var lütfen kontrol ediniz2!");
                    }
                }
            }
        });
        btn_singOut.addActionListener(e -> {
            dispose();
            LoginGuı k=new LoginGuı();
        });
    }

    private void loadCmd(){
        ArrayList<String> arr1=new ArrayList<>();
        ArrayList<String> arr2=new ArrayList<>();
        cmd_hotel.removeAllItems();

        cmd_region.removeAllItems();
        cmd_hotel.addItem(new ITEM(0,""));
        cmd_region.addItem(new ITEM(0,""));

        for (pansiyonFeature a: pansiyonFeature.getList()){
            cmd_hotel.addItem(new ITEM(a.getId(),a.getName()));
            if (!arr2.contains(a.getRegion())){
                cmd_region.addItem(new ITEM(a.getId(),a.getRegion()));
                arr2.add(a.getRegion());
            }
        }
    }


    private boolean isDateTrue(int in0,int in1,int in2,int out0,int out1,int out2){
        if (in0>0 && in0<32 && in1>0 && in1<13 && in2>2000){
            if (out0>0 && out0<32 && out1>0 && out1<13 && out2>2000){
                if (in2>out2){
                    return false;
                }else if (in2==out2 && in1>out1){
                    return false;
                }else if (in2==out2 && in1==out1 && in0>=out0){
                    return false;
                }else{
                    return true;
                }

            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
