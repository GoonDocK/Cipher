package Cipher.simpleTransposition;
import GUI.ComponentCreator.NewUIComp;
import Miscellaneous.Exceptions.emptyField;
import Miscellaneous.Fonts;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class transPanel extends JPanel {
    private simpleTransposition Cipher;
    private final JPanel panelIzquierdo;
    private final NewUIComp newUIComp = new NewUIComp();
    public transPanel(CardLayout cardLayout) {
        setLayout(new GridLayout(1, 2));
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridBagLayout());
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel info1 = new JLabel("Texto descifrado: ");
        info1.setHorizontalAlignment(JLabel.LEFT);
        info1.setFont(Fonts.SourceSansPro18Bold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 2;
        gbc.gridwidth=4;
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

        JLabel info2 = new JLabel("Palabra clave: ");
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

        JLabel info3 = new JLabel("Texto Cifrado: ");
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


        JButton cifrar= newUIComp.newButton("Cifrar", "/Assets/Key.png");
        cifrar.addActionListener(e -> {createMatrix(cifrado,palabraClave,cipher);});
        gbc.gridwidth=1;
        gbc.gridy = 6;
        gbc.weightx = 3;
        gbc.weighty = 1;
        panelDerecho.add(cifrar, gbc);

        JButton descifrar = newUIComp.newButton("Descifrar", "/Assets/UnKey.png");
        descifrar.addActionListener(e -> uncipheredMatrix(cifrado,palabraClave,cipher));
        gbc.gridx=1;
        panelDerecho.add(descifrar, gbc);

        JButton atras = newUIComp.newButton("Atras","/Assets/Back.png");
        atras.addActionListener(e -> cardLayout.show(this.getParent(), "instruc"));
        gbc.gridx=2;

        panelDerecho.add(atras, gbc);

        JButton clear= newUIComp.newButton("Limpiar", "/Assets/Clear.png");
        clear.addActionListener(e -> {clear(cifrado,palabraClave,cipher);});
        gbc.gridx=3;

        panelDerecho.add(clear, gbc);

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
                JPanel panel = newUIComp.matrixComponent(Cipher.getKey().charAt(i)+"",50,50);
                gbc.gridx=i;
                panelIzquierdo.add(panel, gbc);
            }
            gbc.gridy=0;
            int[] index=Cipher.getIndex();
            for(int i=0;i<index.length;i++){
                JPanel panel = newUIComp.matrixComponent(index[i]+"",50,50);
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
                    JPanel panel = newUIComp.matrixComponent(text.charAt(j)+"",50,50);
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
    public void uncipheredMatrix(JTextArea cifrado, JTextField Key, JTextArea cipher){
        panelIzquierdo.removeAll();
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
                JPanel panel = newUIComp.matrixComponent(Cipher.getKey().charAt(i)+"",50,50);
                gbc.gridx=i;
                panelIzquierdo.add(panel, gbc);
            }
            gbc.gridy=0;
            int[] index=Cipher.getIndex();
            HashMap<Integer, Integer> map=new HashMap<>();
            for(int i=0;i<index.length;i++){
                map.put(index[i]-1,i);
            }
            for(int i=0;i<index.length;i++){
                JPanel panel = newUIComp.matrixComponent(index[i]+"",50,50);
                gbc.gridx=i;
                panelIzquierdo.add(panel, gbc);
            }
            gbc.gridy=2;
            int k=0;

            int[] x= new int[Cipher.getKeySize()];
            for(int i=0;i<Cipher.getKeySize();i++){
                int j=0;
                gbc.gridx=map.get(i);
                x[i]=map.get(i);
                while(j<Cipher.getMatrixRowsCount(cipher.getText())){
                    JPanel panel = newUIComp.matrixComponent(cipher.getText().charAt(k)+"",50,50);
                    gbc.gridy=j+2;
                    panelIzquierdo.add(panel, gbc);
                    j++;
                    k++;
                }
            }
            panelIzquierdo.revalidate();
            panelIzquierdo.repaint();
            cifrado.setText(Cipher.deCrypt(cipher.getText(), Key.getText()));


        }catch (emptyField e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public void clear(JTextArea cifrado, JTextField Key, JTextArea cipher){
        panelIzquierdo.removeAll();
        cifrado.setText("");
        Key.setText("");
        cipher.setText("");
        panelIzquierdo.revalidate();
        panelIzquierdo.repaint();
    }
}
