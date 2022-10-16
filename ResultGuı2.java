package Paket16_GuıBitirme.GUI;

import Paket16_GuıBitirme.Helper.Help;
import Paket16_GuıBitirme.Helper.ITEM;
import Paket16_GuıBitirme.Model.*;

import javax.swing.*;

public class ResultGuı2 extends JFrame{
    private JPanel wrapper;
    private JTextField fld_child;
    private JTextField fld_adult;
    private JTextField fld_in;
    private JTextField fld_out;
    private JTextField fld_pay;
    private JButton btn_return;
    private JTextField fld_bad;
    private JTextField fld_television;
    private JTextField fld_bar;
    private JTextField fld_case;
    private JTextField fld_pro;
    private JButton btn_save;

    public ResultGuı2(String in, String out, int adult, int child, ITEM hotel , ITEM region, int total, int id){
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

                fld_adult.setText(String.valueOf(adult));
                fld_child.setText(String.valueOf(child));
                fld_in.setText(in);
                fld_out.setText(out);
                fld_pay.setText(String.valueOf(total));

                fld_bar.setText(String.valueOf(r.getBar()));
                fld_case.setText(String.valueOf(r.getSafe()));
                fld_television.setText(String.valueOf(r.getTelevision()));
                fld_pro.setText(String.valueOf(r.getTelevision()));
                fld_bad.setText(String.valueOf(r.getBadNumber()));
            }
        }

        btn_return.addActionListener(e -> {
            dispose();
            ResultGuı r=new ResultGuı(in,out,adult,child,hotel,region,total,id);
        });
        btn_save.addActionListener(e -> {
            dispose();
            ResultGuı3 r=new ResultGuı3(in,out,adult,child,hotel,region,total,id);
        });
    }
}
