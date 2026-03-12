package Cipher.simpleTransposition;
import AssetsHandler.iconScaller;
import Miscellaneous.Exceptions.emptyField;
import Miscellaneous.Fonts;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class transPanel extends JPanel {
    private simpleTransposition Cipher;
    private JPanel panelIzquierdo;
    public transPanel(CardLayout cardLayout) {
        setLayout(new GridLayout(1, 2));
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridBagLayout());
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel info1 = new JLabel("Texto a cifrar");
        info1.setHorizontalAlignment(JLabel.LEFT);
        info1.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 2;
        gbc.gridwidth=3;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelDerecho.add(info1, gbc);

        JTextArea cifrado = new JTextArea("Escribe aquí el cifrado");
        cifrado.setLineWrap(true);
        cifrado.setWrapStyleWord(true);
        cifrado.setEditable(true);
        cifrado.setFont(Fonts.SourceSansPro18);
        cifrado.setColumns(20);
        cifrado.setRows(5);
        cifrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridy = 1;
        gbc.weightx = 3;
        gbc.weighty = 3;
        panelDerecho.add(cifrado, gbc);

        JLabel info2 = new JLabel("Palabra clave");
        info2.setHorizontalAlignment(JLabel.LEFT);
        info2.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridy = 2;
        gbc.weightx = 2;
        gbc.weighty = 1;
        panelDerecho.add(info2, gbc);

        JTextField palabraClave = new JTextField("Escribe aquí la palabra clave");
        palabraClave.setFont(Fonts.SourceSansPro18);
        palabraClave.setColumns(20);
        gbc.gridy = 3;
        gbc.weightx = 6;
        gbc.weighty = 1;
        panelDerecho.add(palabraClave, gbc);

        JLabel info3 = new JLabel("Texto Cifrado");
        info3.setHorizontalAlignment(JLabel.LEFT);
        info3.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridy = 4;
        gbc.weightx = 2;
        gbc.weighty = 1;
        panelDerecho.add(info3, gbc);

        JTextArea cipher=new JTextArea();
        cipher.setLineWrap(true);
        cipher.setWrapStyleWord(true);
        cipher.setEditable(true);
        cipher.setFont(Fonts.SourceSansPro18);
        cipher.setColumns(20);
        cipher.setRows(5);
        cipher.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridy = 5;
        gbc.weightx = 6;
        gbc.weighty = 3;
        panelDerecho.add(cipher, gbc);

        JButton cifrar = new JButton("Cifrar");
        cifrar.setFont(Fonts.SourceSansPro18Bold);
        cifrar.setBackground(Color.BLACK);
        cifrar.setForeground(Color.WHITE);
        cifrar.setIcon(iconScaller.scale("/Assets/Key.png", 32, 32));
        cifrar.addActionListener(e -> {createMatrix(cifrado,palabraClave,cipher);});
        gbc.gridwidth=1;
        gbc.gridy = 6;
        gbc.weightx = 3;
        gbc.weighty = 1;
        panelDerecho.add(cifrar, gbc);

        JButton descifrar = new JButton("Descifrar");
        descifrar.setFont(Fonts.SourceSansPro18Bold);
        descifrar.setBackground(Color.BLACK);
        descifrar.setForeground(Color.WHITE);
        descifrar.setIcon(iconScaller.scale("/Assets/UnKey.png", 32, 32));
        descifrar.addActionListener(new  ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                uncipheredMatrix();
            }
        });
        gbc.gridx=1;
        panelDerecho.add(descifrar, gbc);

        JButton atras = new JButton("Atras");
        atras.setFont(Fonts.SourceSansPro18Bold);
        atras.setBackground(Color.BLACK);
        atras.setForeground(Color.WHITE);
        atras.setIcon(iconScaller.scale("/Assets/Back.png", 32, 32));
        atras.addActionListener(e -> cardLayout.show(this.getParent(), "instruc"));
        gbc.gridx=2;

        panelDerecho.add(atras, gbc);

        panelIzquierdo = new JPanel();
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        add(panelIzquierdo);
        add(panelDerecho);
    }
    private void createMatrix(JTextArea cifrado, JTextField Key, JTextArea cipher){
        Cipher = new simpleTransposition(Key.getText());
        Cipher.setTexto(cifrado.getText());
        panelIzquierdo.removeAll();
        GridBagLayout matrixGrid=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.CENTER;
        gbc.gridy=1;
        try{
            panelIzquierdo.setLayout(matrixGrid);
            for(int i=0;i<Cipher.getKeySize();i++){
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
                JLabel label = new JLabel(Cipher.getKey().charAt(i)+"");
                panel.add(label);
                panel.setPreferredSize(new Dimension(50, 50));
                panel.setMinimumSize(new Dimension(50, 50));
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setFont(Fonts.SourceSansPro18Bold);
                gbc.gridx=i;
                panelIzquierdo.add(panel, gbc);
            }
            gbc.gridy=0;
            int[] index=Cipher.getIndex();
            for(int i=0;i<index.length;i++){
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
                JLabel label = new JLabel(index[i]+"");
                panel.add(label);
                panel.setPreferredSize(new Dimension(50, 50));
                panel.setMinimumSize(new Dimension(50, 50));
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setFont(Fonts.SourceSansPro18Bold);
                gbc.gridx=i;
                panelIzquierdo.add(panel, gbc);
            }
            gbc.gridy=2;
            int j = 0;
            int textLenght = Cipher.getTextCorrected().length();
            String text = Cipher.getTextCorrected();
            int columnas = Cipher.getKeySize();
            StringBuilder[] columns= new StringBuilder[columnas];
            for(int i = 0; i < columnas; i++){
                columns[i] = new StringBuilder();
            }
            while(j < textLenght){


                for(int i = 0; i < columnas && j < textLenght; i++){

                    char c = text.charAt(j);
                    JPanel panel = new JPanel();
                    panel.setBackground(Color.WHITE);

                    JLabel label = new JLabel(text.charAt(j) + "");
                    panel.add(label);

                    panel.setPreferredSize(new Dimension(50, 50));
                    panel.setMinimumSize(new Dimension(50, 50));
                    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    label.setFont(Fonts.SourceSansPro18Bold);

                    gbc.gridx = i;
                    panelIzquierdo.add(panel, gbc);
                    columns[i].append(c);
                    j++;
                }
                gbc.gridy++;
            }
            cipher.setText(Cipher.getCipheredText(columns, index));
            panelIzquierdo.revalidate();
            panelIzquierdo.repaint();
        }catch (emptyField e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public void uncipheredMatrix(){
        panelIzquierdo.removeAll();
    }
}
