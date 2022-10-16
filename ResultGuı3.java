package Paket16_GuıBitirme.GUI;

import Paket16_GuıBitirme.Helper.Help;
import Paket16_GuıBitirme.Helper.ITEM;
import Paket16_GuıBitirme.Model.visitor;

import javax.swing.*;

public class ResultGuı3 extends JFrame{
    private JPanel wrapper;
    private JTextField fld_visitor_type;
    private JTextField fld_name;
    private JComboBox cmb;
    private JTextField fld_tc;
    private JButton btn_save_visitor;
    private JButton btn_return;
    private JButton btn_save;
    private int i=0;
    private int number=1;

    public ResultGuı3(String in, String out, int adult, int child, ITEM hotel , ITEM region, int total, int id){
        add(wrapper);
        setSize(500,500);
        setLocation(Help.screenCenter("x",getSize()),Help.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        fld_visitor_type.setText("1. Yetişkin ");
        for (visitor v: visitor.getList()){
            i=v.getId_quene();
        }
        i=i+1;
        btn_save_visitor.addActionListener(e -> {
            if (Help.isEmpty(fld_name) || Help.isEmpty(fld_tc)){
                Help.showMSG("fill");
            }else{
                String country=cmb.getSelectedItem().toString();
                if (visitor.add(i,fld_name.getText(),country,fld_tc.getText())){
                    number++;
                    if (number<=adult){
                        Help.showMSG("done");
                        fld_name.setText(null);
                        fld_tc.setText(null);
                        fld_visitor_type.setText(number+". Yetişkin ");
                    }else if (child!=0 && number<=child+adult){
                        Help.showMSG("done");
                        fld_name.setText(null);
                        fld_tc.setText(null);
                        fld_visitor_type.setText(number-adult+". Çocuk ");
                    }
                    if (number> adult+child){
                        fld_visitor_type.setText(null);
                        fld_name.setText(null);
                        fld_tc.setText(null);
                        Help.showMSG("Kişi sayısını doldurdunuz!");
                    }
                }else {
                    Help.showMSG("error");
                }
            }

        });
        btn_return.addActionListener(e -> {
            for (visitor v: visitor.getList()){
                if (v.getId_quene()==i){
                    visitor.delete(v.getId());
                }
            }
            dispose();
            Help.setLayout();
            ResultGuı2 r=new ResultGuı2(in,out,adult,child,hotel,region,total,id);
        });
        btn_save.addActionListener(e -> {
            if (!Help.isEmpty(fld_visitor_type)){
                Help.showMSG("Lütfen işlemleri tamamlayınız!");
            }else {
                Help.showMSG("done");
                dispose();
                Help.setLayout();
                LoginGuı l=new LoginGuı();
            }
        });
    }
}
