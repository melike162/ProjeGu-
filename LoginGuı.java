package Paket16_GuıBitirme.GUI;

import Paket16_GuıBitirme.Helper.Help;
import Paket16_GuıBitirme.Model.user;

import javax.swing.*;

public class LoginGuı extends JFrame{
    private JPanel wrapper;
    private JTextField fld_login_uname;
    private JPasswordField fld_login_pass;
    private JButton btn_login_singin;
    private JButton btn_login_regiter;

    public LoginGuı(){
        add(wrapper);
        setSize(400,400);
        setLocation(Help.screenCenter("x",getSize()), Help.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        btn_login_singin.addActionListener(e -> {
            if (Help.isEmpty(fld_login_pass) || Help.isEmpty(fld_login_uname)){
                Help.showMSG("fill");
            }else{
                user u=user.getFetch(fld_login_uname.getText(),fld_login_pass.getText());
                if (u==null){
                    Help.showMSG("Böyle bir kullanıcı bulunmamaktadır. ");
                }else{
                    dispose();
                    Help.setLayout();
                    OperatorGuı op=new OperatorGuı();

                }
            }
        });
        btn_login_regiter.addActionListener(e -> {
            dispose();
            Help.setLayout();
            CustomerGuı c=new CustomerGuı();
        });
    }

    public static void main(String[] args) {
        Help.setLayout();
        LoginGuı loginGuı=new LoginGuı();
    }
}
