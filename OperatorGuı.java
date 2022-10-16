package Paket16_GuıBitirme.GUI;


import Paket16_GuıBitirme.Helper.*;
import Paket16_GuıBitirme.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorGuı extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JTable tbl_hotel_list;
    private JTable tbl_feature_list;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_adress;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_phone;
    private JComboBox cmb_hotel_stare;
    private JButton btn_hotel_add;
    private JButton btn_hotel_delete;
    private JTextField fld_hotel_id;
    private JTextField fld_feature_id;
    private JButton btn_feature_delete;
    private JButton btn_feature_add;
    private JTable tbl_room_list;
    private JTextField fld_room_id;
    private JButton btn_room_delete;
    private JButton btn_room_add;
    private JTable tbl_date_list;
    private JComboBox cmb_date_hotelType;
    private JComboBox cmd_date_roomType;
    private JComboBox cmb_date_hotel;
    private JTextField fld_date_d1;
    private JTextField fld_date_d2;
    private JTextField fld_date_d3;
    private JTextField fld_date_d4;
    private JButton btn_date_add;
    private JTextField fld_date_id;
    private JButton btn_date_delete;
    private JButton btn_SingOut;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_region;
    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;
    private DefaultTableModel mdl_feature_list;
    private Object[] row_feature_list;
    private DefaultTableModel mdl_room_list;
    private Object[] row_room_list;
    private DefaultTableModel mdl_date_list;
    private Object[] row_date_list;

    public OperatorGuı(){
        add(wrapper);
        setSize(900,650);
        setLocation(Help.screenCenter("x",getSize()),Help.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        mdl_hotel_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) { // 0. sütunun değerlerinin değişmesini engelledik
                if (column==0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] colmn_hotel_list={"ID","İsim","Adres","Şehir","Bölge","Mail","Telefon","Yıldız"};
        mdl_hotel_list.setColumnIdentifiers(colmn_hotel_list);
        row_hotel_list=new Object[colmn_hotel_list.length];
        loadHotelModel();
        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);


        tbl_hotel_list.getModel().addTableModelListener(e -> {
            if (e.getType()==TableModelEvent.UPDATE){
                int id= Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0).toString());
                String name=tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),1).toString();
                String adres=tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),2).toString();
                String city=tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),3).toString();
                String region=tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),4).toString();
                String mail=tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),5).toString();
                String phone=tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),6).toString();
                int stare= Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),7).toString());
                if (!(stare>0 && stare<6)){
                    Help.showMSG("Otelin yıldızı 1-5 arasında olmalıdır!");
                }else{
                    if (pansiyonFeature.getFetch(name,id)==null){
                        if (pansiyonFeature.update(id,name,adres,city,region,mail,phone,stare)){
                            Help.showMSG("done");
                        }else{
                            Help.showMSG("error");
                        }
                    }else{
                        Help.showMSG("Bu isimde bir otel zaten vardır!");
                    }

                }
                loadHotelModel();
                loadFeatureModel();
                loadRoomModel();
                loadDateModel();
                cmdDateHotel();
            }
        });

        //####
        mdl_feature_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) { // 0. sütunun değerlerinin değişmesini engelledik
                if (column==0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] colmn_feature_list={"ID","Otel ID","SPA","Ücretsiz Otopark", "Ücretsiz WiFi","Yüzme Havuzu","Fitness Center","Hotel Concierge","7/24 Oda Servisi","type"};
        mdl_feature_list.setColumnIdentifiers(colmn_feature_list);
        row_feature_list=new Object[colmn_feature_list.length];
        loadFeatureModel();
        tbl_feature_list.setModel(mdl_feature_list);
        tbl_feature_list.getTableHeader().setReorderingAllowed(false);
        for (int i=0;i<3;i++){
            tbl_feature_list.getColumnModel().getColumn(i).setMaxWidth(50);
        }

        tbl_feature_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String id=tbl_feature_list.getValueAt(tbl_feature_list.getSelectedRow(),0).toString();
                fld_feature_id.setText(id);
            }catch (Exception ex){

            }
        });

        tbl_feature_list.getModel().addTableModelListener(e -> {
            if (e.getType()==TableModelEvent.UPDATE){
                try {
                    int id= Integer.parseInt(tbl_feature_list.getValueAt(tbl_feature_list.getSelectedRow(),0).toString());
                    int otel_id=Integer.parseInt(tbl_feature_list.getValueAt(tbl_feature_list.getSelectedRow(),1).toString());
                    int spa=isYesNo(tbl_feature_list.getValueAt(tbl_feature_list.getSelectedRow(),2).toString());
                    int park=isYesNo(tbl_feature_list.getValueAt(tbl_feature_list.getSelectedRow(),3).toString());
                    int wf=isYesNo(tbl_feature_list.getValueAt(tbl_feature_list.getSelectedRow(),4).toString());
                    int pool=isYesNo(tbl_feature_list.getValueAt(tbl_feature_list.getSelectedRow(),5).toString());
                    int fitness=isYesNo(tbl_feature_list.getValueAt(tbl_feature_list.getSelectedRow(),6).toString());
                    int conc=isYesNo(tbl_feature_list.getValueAt(tbl_feature_list.getSelectedRow(),7).toString());
                    int service=isYesNo(tbl_feature_list.getValueAt(tbl_feature_list.getSelectedRow(),8).toString());
                    String type=tbl_feature_list.getValueAt(tbl_feature_list.getSelectedRow(),9).toString();
                    if (spa==2 || park==2 || wf==2 || pool==2 || fitness==2 || conc==2 || service==2){
                        Help.showMSG("Evet,evet,Hayır,hayır dan başka bir şey yazılamaz");
                    }else{
                        if (type.equals("Ultra Herşey Dahil") || type.equals("Herşey Dahil")|| type.equals("Oda Kahvaltı")|| type.equals("Tam Pansiyon")|| type.equals("Yarım Pansiyon")|| type.equals("Sadece Yatak")|| type.equals("Alkol Hariç Full credit")){
                            boolean a=false;
                            for (pansiyonFeature f: pansiyonFeature.getList()){
                                if (f.getId()==otel_id){
                                    a=true;
                                }
                            }
                            if (a){
                                boolean b=true;
                                for (feature f: feature.getList()){
                                    if (f.getPansiyon_id()==otel_id && f.getType().equals(type)){
                                        b=false;
                                    }
                                }
                                if (b){
                                    if (feature.update(id,park,wf,pool,fitness,conc,spa,service,otel_id,type)){
                                        Help.showMSG("done");
                                    }else {
                                        Help.showMSG("error");
                                    }
                                }else{
                                    Help.showMSG("Bu otele ait bu tipte bir pansiyon vardır");
                                }

                            }else {
                                Help.showMSG("Bu id ye sahip bir otel yoktur");
                            }
                        }else{
                            Help.showMSG("Tip olarak Ultra Herşey Dahil,Herşey Dahil,Oda Kahvaltı,Tam Pansiyon,Yarım Pansiyon,Sadece Yatak,Alkol Hariç Full credit yazılabilir.");
                        }
                    }
                }catch (Exception ex){
                    Help.showMSG("Lütfen otel id sütununa sayı yazınız!");
                }
                loadRoomModel();
                loadFeatureModel();
                loadDateModel();
            }
        });

        //###
        mdl_room_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) { // 0. sütunun değerlerinin değişmesini engelledik
                if (column==0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] colm_room_list={"ID","Özellik ID","Adet","Yatak Sayısı","Televizton","Bar","Oyun Konsolu","Odanın Alanı[m2]","Kasa","Projeksiyon","Oda Tipi"};
        mdl_room_list.setColumnIdentifiers(colm_room_list);
        row_room_list=new Object[colm_room_list.length];
        loadRoomModel();
        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);
        tbl_room_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_room_list.getColumnModel().getColumn(2).setMaxWidth(50);
        tbl_room_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String id=tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),0).toString();
                fld_room_id.setText(id);
            }catch (Exception ex){

            }
        });

        tbl_room_list.getModel().addTableModelListener(e -> {
            if (e.getType()==TableModelEvent.UPDATE){
                try {
                    int id= Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),0).toString());
                    int fearute_id= Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),1).toString());
                    int roomNumber= Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),2).toString());
                    int badNumber= Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),3).toString());
                    int tele= Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),4).toString());
                    int bar= Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),5).toString());
                    int console= Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),6).toString());
                    int m2= Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),7).toString());
                    int safe= Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),8).toString());
                    int pro= Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),9).toString());
                    String type= tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),10).toString();
                    feature fea=null;
                    for (feature f: feature.getList()){
                        if(fearute_id==f.getId()){
                            fea=f;
                            break;
                        }
                    }
                    if (roomNumber<0 || badNumber<0 || tele<0 || bar<0 || console<0 || m2<0 || safe<0 || pro<0){
                        Help.showMSG("Negatif değer girilemez!");
                    }else{
                        if (fea==null){
                            Help.showMSG("Bu özelliklere sahip bir pansiyon yoktur!");
                        }else{
                            if (type.equals("Suit") || type.equals("Single")|| type.equals("Double") || type.equals("suit") || type.equals("single")|| type.equals("double")){
                                boolean a=true;
                                for (room r : room.getList()){
                                    if (r.getType().equals(type) && r.getFeature_id()==fearute_id){
                                        a=false;
                                    }
                                }
                                if (a){
                                    if (room.update(id,fearute_id,roomNumber,badNumber,tele,bar,console,m2,safe,pro,type)){
                                        Help.showMSG("done");
                                    }else{
                                        Help.showMSG("error");
                                    }
                                }else{
                                    Help.showMSG("Bu pansiyon tipinin bu tipte  odası vardır!");
                                }

                            }else{
                                Help.showMSG("Tip olarak Single,Double ya da Suit yazılabilir.");
                            }
                        }

                    }
                }catch (Exception ex){
                    Help.showMSG("Lütfen sayı giriniz! ");
                }
                loadRoomModel();
            }
        });

        //###
        mdl_date_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                if (column==0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] colmn_date_list={"ID","Oda ID","01.01-30.06 [TL]","01.01-30.06(Çocuk) [TL]","01.07-31.12 [TL]","01.07-31.12(Çocuk) [TL]"};
        mdl_date_list.setColumnIdentifiers(colmn_date_list);
        row_date_list=new Object[colmn_date_list.length];
        loadDateModel();
        cmdDateHotel();
        tbl_date_list.setModel(mdl_date_list);
        tbl_date_list.getTableHeader().setReorderingAllowed(false);
        tbl_date_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_date_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String id=tbl_date_list.getValueAt(tbl_date_list.getSelectedRow(),0).toString();
                fld_date_id.setText(id);
            }catch (Exception ex){

            }
        });
        tbl_date_list.getModel().addTableModelListener(e -> {
            if (e.getType()==TableModelEvent.UPDATE){
                try {
                    int id= Integer.parseInt(tbl_date_list.getValueAt(tbl_date_list.getSelectedRow(),0).toString());
                    int feature_id=Integer.parseInt(tbl_date_list.getValueAt(tbl_date_list.getSelectedRow(),1).toString());
                    int d1=Integer.parseInt(tbl_date_list.getValueAt(tbl_date_list.getSelectedRow(),2).toString());
                    int d2=Integer.parseInt(tbl_date_list.getValueAt(tbl_date_list.getSelectedRow(),3).toString());
                    int d3= Integer.parseInt(tbl_date_list.getValueAt(tbl_date_list.getSelectedRow(),4).toString());
                    int d4= Integer.parseInt(tbl_date_list.getValueAt(tbl_date_list.getSelectedRow(),5).toString());
                    room ro=null;
                    for (room f: room.getList()){
                        if (f.getId()==feature_id){
                            ro=f;
                            break;
                        }
                    }
                    if (ro==null){
                        Help.showMSG("Bu id ye sahip bir oda yoktur!");
                    }else{
                        if (d1<0 || d2<0 || d3<0 || d4<0){
                            Help.showMSG("fiyatlar sıfırdan küçük olamaz!");
                        }else{
                            if (date.getList(feature_id)==null){
                                if (date.update(id,feature_id,d1,d2,d3,d4)){
                                    Help.showMSG("done");
                                }else {
                                    Help.showMSG("error");
                                }
                            }else{
                                Help.showMSG("Bu oda tipi zaten fiyatlandırıldı!");
                            }

                        }
                    }
                }catch (Exception ex){
                    Help.showMSG("Lütfen sayı giriniz!");
                }
                loadDateModel();
            }
        });

        //###
        btn_hotel_add.addActionListener(e -> {
            if (Help.isEmpty(fld_hotel_mail) || Help.isEmpty(fld_hotel_name) || Help.isEmpty(fld_hotel_adress) || Help.isEmpty(fld_hotel_phone)|| Help.isEmpty(fld_hotel_city) || Help.isEmpty(fld_hotel_region)){
                Help.showMSG("fill");
            }else {
                String stare=cmb_hotel_stare.getSelectedItem().toString();
                boolean a=true;
                for (pansiyonFeature f: pansiyonFeature.getList()){
                    if (f.getName().equals(fld_hotel_name.getText())){
                        a=false;
                    }
                }
                if (a){
                    if (pansiyonFeature.add(fld_hotel_name.getText(),fld_hotel_adress.getText(),fld_hotel_city.getText(),fld_hotel_region.getText(),fld_hotel_mail.getText(),fld_hotel_phone.getText(),Integer.parseInt(stare))){
                        Help.showMSG("done");
                        fld_hotel_mail.setText(null);
                        fld_hotel_adress.setText(null);
                        fld_hotel_name.setText(null);
                        fld_hotel_phone.setText(null);
                    }else {
                        Help.showMSG("error");
                    }
                }else {
                    Help.showMSG("Bu isimde bir otel vardır!");
                }

            }
            loadHotelModel();
        });
        btn_hotel_delete.addActionListener(e -> {
            if (Help.isEmpty(fld_hotel_id)){
                Help.showMSG("fill");
            }else{
                if (pansiyonFeature.delete(Integer.parseInt(fld_hotel_id.getText()))){
                    Help.showMSG("done");
                    fld_hotel_id.setText(null);
                }else{
                    Help.showMSG("error");
                }
            }
            loadHotelModel();
            loadFeatureModel();
            loadRoomModel();
            loadDateModel();
            cmdDateHotel();
        });
        btn_feature_delete.addActionListener(e -> {
            if (Help.isEmpty(fld_feature_id)){
                Help.showMSG("fill");
            }else{
                if (feature.delete(Integer.parseInt(fld_feature_id.getText()))){
                    Help.showMSG("done");
                    fld_feature_id.setText(null);
                }else {
                    Help.showMSG("error");
                }
                loadFeatureModel();
                loadRoomModel();
                loadDateModel();
            }
        });
        btn_feature_add.addActionListener(e -> {
            dispose();
            AddFeature fe=new AddFeature();
        });
        btn_room_delete.addActionListener(e -> {
            if(Help.isEmpty(fld_room_id)){
                Help.showMSG("fill");
            }else{
                if (room.delete(Integer.parseInt(fld_room_id.getText()))){
                    Help.showMSG("done");
                    fld_room_id.setText(null);
                }else{
                    Help.showMSG("error");
                }
                loadRoomModel();
                loadDateModel();
            }
        });
        btn_room_add.addActionListener(e -> {
            dispose();
            AddRoom r=new AddRoom();
        });
        btn_date_add.addActionListener(e -> {
            if(Help.isEmpty(fld_date_d1) || Help.isEmpty(fld_date_d2) || Help.isEmpty(fld_date_d3) || Help.isEmpty(fld_date_d4)){
                Help.showMSG("fill");
            }else{
                try{
                    int d1= Integer.parseInt(fld_date_d1.getText());
                    int d2= Integer.parseInt(fld_date_d2.getText());
                    int d3= Integer.parseInt(fld_date_d3.getText());
                    int d4= Integer.parseInt(fld_date_d4.getText());
                    ITEM hotel= (ITEM) cmb_date_hotel.getSelectedItem();
                    String hotelType=cmb_date_hotelType.getSelectedItem().toString();
                    String roomType=cmd_date_roomType.getSelectedItem().toString();
                    feature fea=feature.getFetchT(hotel.getKey(),hotelType);
                    if (fea!=null){
                        room r=room.getFetch(fea.getId(),roomType);
                        if (r!=null){
                            boolean a=true;
                            for (date d: date.getList()){
                                if (d.getRoom_id()==r.getId()){
                                    a=false;
                                }
                            }
                            if (a){
                                if(date.add(r.getId(),d1,d2,d3,d4)){
                                    fld_date_d1.setText(null);
                                    fld_date_d2.setText(null);
                                    fld_date_d3.setText(null);
                                    fld_date_d4.setText(null);
                                }else{
                                    Help.showMSG("error");
                                }
                            }else{
                                Help.showMSG("Bu oda tipi zatan fiyatlandırıldı!");
                            }

                        }else{
                            Help.showMSG("Bu pansiyonun bu tipte bir odası yoktr! ");
                        }


                    }else{
                        Help.showMSG("Bu otelin bu tipte bir pansiyonu yoktur! ");
                    }
                }catch (Exception ex){
                    Help.showMSG("Lütfen rakam giriniz! ");
                }
                loadDateModel();
            }
        });
        btn_date_delete.addActionListener(e -> {
            if (Help.isEmpty(fld_date_id)){
                Help.showMSG("fill");
            }else{
                if (date.delete(Integer.parseInt(fld_date_id.getText()))){
                    fld_date_id.setText(null);
                }else {
                    Help.showMSG("error");
                }
            }
            loadDateModel();
        });
        btn_SingOut.addActionListener(e -> {
            dispose();
            LoginGuı lo=new LoginGuı();
        });
    }

    private void loadHotelModel() {
        DefaultTableModel model= (DefaultTableModel) tbl_hotel_list.getModel();
        model.setRowCount(0);
        int i=0;
        for (pansiyonFeature obj: pansiyonFeature.getList()){
            i=0;
            row_hotel_list[i++]=obj.getId();
            row_hotel_list[i++]=obj.getName();
            row_hotel_list[i++]=obj.getAdress();
            row_hotel_list[i++]=obj.getCity();
            row_hotel_list[i++]=obj.getRegion();
            row_hotel_list[i++]=obj.getMail();
            row_hotel_list[i++]=obj.getPhone();
            row_hotel_list[i++]=obj.getStare();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }

    private void loadFeatureModel(){
        DefaultTableModel model= (DefaultTableModel) tbl_feature_list.getModel();
        model.setRowCount(0);
        int i=0;
        for (feature fea: feature.getList()){
            i=0;
            row_feature_list[i++]=fea.getId();
            row_feature_list[i++]=fea.getPansiyon_id();
            row_feature_list[i++]=isWhat(fea.getSPA());
            row_feature_list[i++]=isWhat(fea.getFreePark());
            row_feature_list[i++]=isWhat(fea.getFreeWF());
            row_feature_list[i++]=isWhat(fea.getSwimPool());
            row_feature_list[i++]=isWhat(fea.getFitnessCenter());
            row_feature_list[i++]=isWhat(fea.getHotelConcierge());
            row_feature_list[i++]=isWhat(fea.getRoomService());
            row_feature_list[i++]=fea.getType();
            mdl_feature_list.addRow(row_feature_list);
        }
    }
    private void loadRoomModel(){
        DefaultTableModel model= (DefaultTableModel) tbl_room_list.getModel();
        model.setRowCount(0);
        int i=0;
        for(room obj: room.getList()){
            i=0;
            row_room_list[i++]=obj.getId();
            row_room_list[i++]=obj.getFeature_id();
            row_room_list[i++]=obj.getRoomNumber();
            row_room_list[i++]=obj.getBadNumber();
            row_room_list[i++]=obj.getTelevision();
            row_room_list[i++]=obj.getBar();
            row_room_list[i++]=obj.getConsole();
            row_room_list[i++]=obj.getM2();
            row_room_list[i++]=obj.getSafe();
            row_room_list[i++]=obj.getProjection();
            row_room_list[i++]=obj.getType();
            mdl_room_list.addRow(row_room_list);
        }
    }

    private void loadDateModel(){
        DefaultTableModel clear= (DefaultTableModel) tbl_date_list.getModel();
        clear.setRowCount(0);
        int i=0;
        for (date d: date.getList()){
            i=0;
            row_date_list[i++]=d.getId();
            row_date_list[i++]=d.getRoom_id();
            row_date_list[i++]=d.getD1();
            row_date_list[i++]=d.getD2();
            row_date_list[i++]=d.getD3();
            row_date_list[i++]=d.getD4();
            mdl_date_list.addRow(row_date_list);
        }
    }

    private void cmdDateHotel(){
        cmb_date_hotel.removeAllItems();
        for (pansiyonFeature p: pansiyonFeature.getList()){
            cmb_date_hotel.addItem(new ITEM(p.getId(),p.getName()));
        }
    }

    private String isWhat(int number){
        if (number==1){
            return "Evet";
        }else if(number==0){
            return "Hayır";
        }
        return "Bilinmiyor";
    }

    private int isYesNo(String str){
        if (str.equals("Evet") || str.equals("evet")){
            return 1;
        }else if (str.equals("Hayır") || str.equals("hayır")){
            return 0;
        }
        return 2;
    }


}
