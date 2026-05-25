package Cipher.RSA;
import GUI.ComponentCreator.NewUIComp;
import Miscellaneous.Fonts;

import javax.swing.*;
import java.awt.*;

public class RSAPanel extends JPanel {
    private final RSACipher Cipher = new RSACipher();
    private final NewUIComp newUIComp = new NewUIComp();
    private long phi=0;
    private long phi2=0;
    public RSAPanel(CardLayout cardLayout){
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 2;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel label = new JLabel("Primo 1:");
        label.setFont(Fonts.SourceSansPro18Bold);
        add(label, gbc);

        JTextField Prime1 = new JTextField();
        Prime1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(Prime1, gbc);

        JLabel label2 = new JLabel("Primo 2:");
        label2.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(label2, gbc);

        JTextField Prime2 = new JTextField();
        Prime2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(Prime2, gbc);

        JLabel label3= new JLabel("N(Clave Pública): ");
        label3.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(label3, gbc);

        JTextField N = new JTextField();
        N.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(N, gbc);

        JLabel label4= new JLabel("Numero a cifrar: ");
        label4.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(label4, gbc);

        JTextField Message = new JTextField();
        Message.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(Message, gbc);

        JLabel Z= new JLabel("Z(Clave pública): ");
        Z.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(Z, gbc);

        JTextField Z2 = new JTextField();
        Z2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(Z2, gbc);

        JLabel label5= new JLabel("S (Clave privada): ");
        label5.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(label5, gbc);

        JTextField S = new JTextField();
        S.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(S, gbc);

        JLabel Decrypt= new JLabel("Numero a descrifrar: ");
        Decrypt.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(Decrypt, gbc);


        JTextField DecryptMessage = new JTextField();
        DecryptMessage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(DecryptMessage, gbc);

        JLabel phiLabel = new JLabel("Phi(Z): " +this.phi);
        phiLabel.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(phiLabel, gbc);

        JLabel phiLabel2 = new JLabel(("Exponente (Phi(" +this.phi +"))-1: " +this.phi2));
        phiLabel2.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridy = 8;
        add(phiLabel2, gbc);



        JButton button = newUIComp.newButton("Cifrar","/Assets/Key.png");
        button.addActionListener(e -> {Encrypt(Prime1,Prime2,N,Message,S,DecryptMessage, Z2, phiLabel, phiLabel2);});
        gbc.gridy = 9;
        add(button, gbc);

        JButton button2 = newUIComp.newButton("Descifrar","/Assets/UnKey.png");
        button2.addActionListener(e -> {Decrypt(Prime1,Prime2,N,Message,S,DecryptMessage, Z2, phiLabel, phiLabel2, button);});
        gbc.gridx=1;
        add(button2, gbc);

        JButton button3 = newUIComp.newButton("Atras","/Assets/Back.png");
        button3.addActionListener(e -> cardLayout.show(this.getParent(), "instruc"));
        gbc.gridx=0;
        gbc.gridy=10;
        add(button3, gbc);

        JButton button4 = newUIComp.newButton("Limpiar","/Assets/Clear.png");
        button4.addActionListener(e -> {clear(Prime1,Prime2,N,Message,S,DecryptMessage, Z2, phiLabel, phiLabel2);});
        gbc.gridx=1;
        add(button4, gbc);

        //Developing Default fields remove when finished
    }
    private void Encrypt(JTextField Prime1, JTextField Prime2, JTextField N, JTextField Message, JTextField S, JTextField DecryptMessage, JTextField Z2, JLabel phiLabel, JLabel phiLabel2){
        Cipher.clear();
        try{
            if(Prime1.getText().isEmpty() || Prime2.getText().isEmpty() || N.getText().isEmpty() || Message.getText().isEmpty()){
                throw new Exception("Por favor complete todos los campos");
            }
            Cipher.setPrimes(Long.parseLong(Prime1.getText()),Long.parseLong(Prime2.getText()));
            Cipher.setN(Long.parseLong(N.getText()));
            Cipher.setMessage(Long.parseLong(Message.getText()));
            DecryptMessage.setText(String.valueOf(Cipher.getCipher()));
            S.setText(String.valueOf(Cipher.getS()));
            Z2.setText(String.valueOf(Cipher.getZ()));
            this.phi=Cipher.getPhi();
            this.phi2=Cipher.getExponent();
            phiLabel.setText("Phi(Z): " +this.phi);
            phiLabel2.setText("Exponente (Phi(" +this.phi +"))-1: " +phi2);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private void Decrypt(JTextField Prime1, JTextField Prime2, JTextField N, JTextField Message, JTextField S, JTextField DecryptMessage, JTextField Z2, JLabel phiLabel, JLabel phiLabel2, JButton button){
       Thread thread = new Thread(new Runnable() {
           @Override
           public void run() {
               button.setEnabled(false);
               Cipher.clear();
               boolean isPrivateKey=true;
               try{
                   if( N.getText().isEmpty() || Z2.getText().isEmpty()|| DecryptMessage.getText().isEmpty()){
                       throw new Exception("Por favor complete todos los campos");
                   }
                   Cipher.setCipher(Long.parseLong(DecryptMessage.getText()));
                   Cipher.setZ(Long.parseLong(Z2.getText()));
                   if(S.getText().isEmpty()){
                       JOptionPane.showMessageDialog(null, "Advertencia, sin una clave privada se intentara factorizar Z, por método de fuerza bruta, lo cual puede tardar desde unos segundos hasta varios millones de eones si Z es muy grande; si el universo no ha llegado a su etapa de máxima entropia para entonces, puede que esta maquina encuentre los números que factorizan la clave pública ");
                       Cipher.setS(0);
                       isPrivateKey=false;
                   }else{
                       Cipher.setS(Long.parseLong(S.getText()));
                   }
                   Cipher.setN(Long.parseLong(N.getText()));
                   if(!isPrivateKey){
                       Prime1.setText(String.valueOf(Cipher.getPrime1()));
                       Prime2.setText(String.valueOf(Cipher.getPrime2()));
                       Message.setText(String.valueOf(Cipher.getMessage()));
                       S.setText(String.valueOf(Cipher.getS()));
                   }else{
                       Message.setText(String.valueOf(Cipher.getMessage()));
                   }
               }catch (Exception e){
                   JOptionPane.showMessageDialog(null, e.getMessage());
               }
               button.setEnabled(true);
           }
       });
       thread.start();
    }
    private void clear(JTextField Prime1, JTextField Prime2, JTextField N, JTextField Message, JTextField S, JTextField DecryptMessage, JTextField Z2, JLabel phiLabel, JLabel phiLabel2){
        Prime1.setText("");
        Prime2.setText("");
        N.setText("");
        Message.setText("");
        S.setText("");
        DecryptMessage.setText("");
        Z2.setText("");
        phiLabel.setText("Phi(Z): " +this.phi);
        phiLabel2.setText("Exponente (Phi(" +this.phi +"))-1: " +phi2);
    }
}
