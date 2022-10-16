package Paket16_GuıBitirme.GUI;

import Paket16_GuıBitirme.Helper.*;
import Paket16_GuıBitirme.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CaseGuı extends JFrame{
    private JPanel wrapper;
    private JTable tbl_case;
    private JTextField fld_pay;
    private JTextField fld_adult_d1;
    private JTextField fld_child_d2;
    private JTextField fld_id;
    private JButton btn_save;
    private JButton btn_singout;
    private JTextField fld_adult_d3;
    private JTextField fld_child_d4;
    private DefaultTableModel mdl_case;
    private Object[] row_case;
    private int total;

    public CaseGuı(int in0, int in1, int in2, int out0, int out1, int out2,int adult,int child, ITEM hotel, ITEM region){
        add(wrapper);
        setSize(700,500);
        setLocation(Help.screenCenter("x",getSize()),Help.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        mdl_case=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        Object[] colm_list={"ID","Otel İsmi","Pansiyon Tipi","Oda Tipi","Oda Sayısı","Yatak Sayısı"};
        mdl_case.setColumnIdentifiers(colm_list);
        row_case=new Object[colm_list.length];

        if(hotel.getKey()==0 && region.getKey()==0){
            loadModel1();
        }else if (hotel.getKey()==0 && region.getKey()!=0){
            loadModel2(region);
        }else if (hotel.getKey()!=0 && region.getKey()==0){
            loadModel3(hotel);
        }else if (hotel.getKey()!=0 && region.getKey()!=0){
            loadModel4(hotel,region);
        }

        tbl_case.setModel(mdl_case);
        tbl_case.getTableHeader().setReorderingAllowed(false);
        if (mdl_case.getRowCount()==0){
            Help.showMSG("İstenilen özelliklere uygun sonuç bulunamamıştır!");
        }

        tbl_case.getSelectionModel().addListSelectionListener(e -> {
            try {
                String id=tbl_case.getValueAt(tbl_case.getSelectedRow(),0).toString();
                fld_id.setText(id);
                for (date d: date.getList()){
                    if (d.getId()==Integer.parseInt(id)){
                        fld_adult_d1.setText(String.valueOf(d.getD1()));
                        fld_child_d2.setText(String.valueOf(d.getD2()));
                        fld_adult_d3.setText(String.valueOf(d.getD3()));
                        fld_child_d4.setText(String.valueOf(d.getD4()));
                        int ic1=0;
                        int id1=0;
                        if(in1<6 && out1<6){
                            id1=(out1-in1-1)*30*d.getD1()+(30-in0+out0)*d.getD1();
                            ic1=(out1-in1-1)*30*d.getD2()+(30-in0+out0)*d.getD2();
                        }else if (in1<6 && out1>=6){
                            id1=(6-in1-1)*30*d.getD1()+(30-in0)*d.getD1()+(out1-6)*30*d.getD3()+out0*d.getD3();
                            ic1=(6-in1-1)*30*d.getD2()+(30-in0)*d.getD2()+(out1-6)*30*d.getD4()+out0*d.getD4();
                        }else if (in1>=6 && out1>=6){
                            id1=(out1-in1-1)*30*d.getD3()+(30-in0+out0)*d.getD3();
                            ic1=(out1-in1-1)*30*d.getD4()+(30-in0+out0)*d.getD4();
                        }else{
                            id1=(12-in1-1)*30*d.getD3()+(30-in0)*d.getD3()+(out1-1)*30*d.getD1()+out0*d.getD1();
                            ic1=(12-in1-1)*30*d.getD4()+(30-in0)*d.getD4()+(out1-1)*30*d.getD2()+out0*d.getD2();
                            if (in1==12){
                                id1=(30-in0)*d.getD3()+(out1-1)*30*d.getD1()+out0*d.getD1();
                                ic1=(30-in0)*d.getD4()+(out1-1)*30*d.getD2()+out0*d.getD2();
                            }

                        }
                        int a=d.getD1()*adult+d.getD3()*adult+d.getD2()*child+d.getD4()*child;
                        int total_a=((out2-in2)*6*30)*a;
                        if(in1>=6 && out1<6){
                            total_a=((out2-in2-1)*6*30)*a;
                        }
                        this.total=total_a+id1*adult+ic1*child;
                        /*
                        System.out.println(in2);
                        System.out.println(out2);
                        System.out.println(out2-in2);
                        System.out.println(id1);
                        System.out.println(ic1);
                        System.out.println(total_a);
                        System.out.println(total);
                        */
                        fld_pay.setText(String.valueOf(total));

                    }
                }
            }catch (Exception ex){

            }
        });


        btn_singout.addActionListener(e -> {
            dispose();
            CustomerGuı c=new CustomerGuı();
        });
        btn_save.addActionListener(e -> {
            if (Help.isEmpty(fld_pay)){
                Help.showMSG("Tablodan bir satıra basarak boşlukları doldrunuz!");
            }else{
                String in=in0+"/"+in1+"/"+in2;
                String out=out0+"/"+out1+"/"+out2;
                dispose();
                Help.setLayout();
                ResultGuı c=new ResultGuı(in,out,adult,child,hotel,region,total,Integer.parseInt(fld_id.getText()));
            }
        });
    }

    private void loadModel1() {
        //System.out.println(1);
        DefaultTableModel clear= (DefaultTableModel) tbl_case.getModel();
        clear.setRowCount(0);
        int i=0;
        for(date d: date.getList()){
            i=0;
            room r=room.getFetch2(d.getRoom_id());
            feature f=feature.getFetch2(r.getFeature_id());
            pansiyonFeature p=pansiyonFeature.getFetch(f.getPansiyon_id());
            row_case[i++]=d.getId();
            row_case[i++]=p.getName();
            row_case[i++]=f.getType();
            row_case[i++]=r.getType();
            row_case[i++]=r.getRoomNumber();
            row_case[i++]=r.getBadNumber();
            mdl_case.addRow(row_case);
        }
    }

    private void loadModel2(ITEM region) {
        //System.out.println(2);
        DefaultTableModel clear= (DefaultTableModel) tbl_case.getModel();
        clear.setRowCount(0);
        int i=0;
        for(date d: date.getList()){
            i=0;
            room r=room.getFetch2(d.getRoom_id());
            feature f=feature.getFetch2(r.getFeature_id());
            pansiyonFeature p=pansiyonFeature.getFetch(f.getPansiyon_id());
            if (p.getRegion().equals(region.getValue())){
                row_case[i++]=d.getId();
                row_case[i++]=p.getName();
                row_case[i++]=f.getType();
                row_case[i++]=r.getType();
                row_case[i++]=r.getRoomNumber();
                row_case[i++]=r.getBadNumber();
                mdl_case.addRow(row_case);
            }
        }
    }

    private void loadModel3(ITEM hotel) {
        //System.out.println(3);
        DefaultTableModel clear= (DefaultTableModel) tbl_case.getModel();
        clear.setRowCount(0);
        int i=0;
        for(date d: date.getList()){
            i=0;
            room r=room.getFetch2(d.getRoom_id());
            feature f=feature.getFetch2(r.getFeature_id());
            pansiyonFeature p=pansiyonFeature.getFetch(f.getPansiyon_id());
            if (p.getName().equals(hotel.getValue())){
                row_case[i++]=d.getId();
                row_case[i++]=p.getName();
                row_case[i++]=f.getType();
                row_case[i++]=r.getType();
                row_case[i++]=r.getRoomNumber();
                row_case[i++]=r.getBadNumber();
                mdl_case.addRow(row_case);
            }
        }
    }

    private void loadModel4(ITEM hotel, ITEM region) {
        //System.out.println(4);
        DefaultTableModel clear= (DefaultTableModel) tbl_case.getModel();
        clear.setRowCount(0);
        int i=0;
        for(date d: date.getList()){
            i=0;
            room r=room.getFetch2(d.getRoom_id());
            feature f=feature.getFetch2(r.getFeature_id());
            pansiyonFeature p=pansiyonFeature.getFetch(f.getPansiyon_id());
            if (p.getName().equals(hotel.getValue()) && p.getRegion().equals(region.getValue())){
                row_case[i++]=d.getId();
                row_case[i++]=p.getName();
                row_case[i++]=f.getType();
                row_case[i++]=r.getType();
                row_case[i++]=r.getRoomNumber();
                row_case[i++]=r.getBadNumber();
                mdl_case.addRow(row_case);
            }
        }
    }






}
