package Paket16_GuıBitirme.GUI;


import Paket16_GuıBitirme.Helper.Help;
import Paket16_GuıBitirme.Helper.ITEM;
import Paket16_GuıBitirme.Model.feature;
import Paket16_GuıBitirme.Model.pansiyonFeature;

import javax.swing.*;

public class AddFeature extends JFrame{
    private JPanel wrapper;
    private JComboBox cmb_type;
    private JComboBox cmb_otopark;
    private JComboBox cmb_wf;
    private JComboBox cmb_pool;
    private JComboBox cmb_fitness;
    private JComboBox cmb_concierge;
    private JComboBox cmb_spa;
    private JComboBox cmb_service;
    private JButton btn_add;
    private JButton btn_singout;
    private JComboBox cmb_pansiyon;

    public  AddFeature(){
        add(wrapper);
        setSize(400,600);
        setLocation(Help.screenCenter("x",getSize()),Help.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        loadCmb();

        btn_singout.addActionListener(e -> {
            dispose();
            OperatorGuı op=new OperatorGuı();
        });

        btn_add.addActionListener(e -> {

            ITEM pansiyon= (ITEM) cmb_pansiyon.getSelectedItem();
            int park=isNumber(cmb_otopark.getSelectedItem().toString());
            int wf=isNumber(cmb_wf.getSelectedItem().toString());
            int poll=isNumber(cmb_pool.getSelectedItem().toString());
            int fit=isNumber(cmb_fitness.getSelectedItem().toString());
            int hotel=isNumber(cmb_concierge.getSelectedItem().toString());
            int spa=isNumber(cmb_spa.getSelectedItem().toString());
            int service=isNumber(cmb_service.getSelectedItem().toString());
            String type=cmb_type.getSelectedItem().toString();
            if (feature.getFetchT(pansiyon.getKey(),type)!=null){
                Help.showMSG("Bu otele ait aynı tipteki pansiyon zaten vardır!");
            }else{
                if (feature.add(park,wf,poll,fit,hotel,spa,service,pansiyon.getKey(),type)){
                    Help.showMSG("done");
                }else{
                    Help.showMSG("error");
                }
            }


        });
    }

    private void loadCmb(){
        cmb_pansiyon.removeAllItems();
        for (pansiyonFeature obj: pansiyonFeature.getList()){
            cmb_pansiyon.addItem(new ITEM(obj.getId(),obj.getName()));
        }
    }

    private int isNumber(String str){
        if (str.equals("Evet")){
            return 1;
        }else if (str.equals("Hayır")){
            return 0;
        }
        return 1;
    }
}
