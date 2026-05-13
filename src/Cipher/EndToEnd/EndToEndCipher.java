package Cipher.EndToEnd;

import Cipher.Alphabet.AlphabetBuilder;
import Cipher.Cipher;
import Miscellaneous.Exceptions.InvalidCharacter;
import Miscellaneous.Exceptions.emptyField;

public class EndToEndCipher extends Cipher {
    private String text;
    private int a;
    private int b;
    private int n;
    private String cipheredText;
    private String decipheredText;
    private int[] index;
    private int[] index2;
    private int InverseA;
    @Override
    public String getTextCorrected(String key) throws emptyField {
        if(key.isEmpty()){
            throw new emptyField("La palabra no puede ser ninguna");
        }
        key=key.toUpperCase();
        char[] keyAux=key.toCharArray();
        StringBuilder keyCorrected=new StringBuilder();
        for (char aux : keyAux) {
            if (!(aux == 32)) keyCorrected.append(aux);
        }
        for (int i = 0; i < keyCorrected.length(); i++) {
            if(keyCorrected.charAt(i)<'A' || keyCorrected.charAt(i)>'Z'){
                throw new InvalidCharacter("La palabra solo puede contener letras");
            }
        }
        key=keyCorrected.toString();
        return key;
    }
    @Override
    public String getCipheredText(String text) throws emptyField {
        text=getTextCorrected(text);
        this.cipheredText=encrypt(text);
        this.text=text;
        return this.cipheredText;
    }
    @Override
    public String getDecryptedText(String text) throws emptyField {
        text=getTextCorrected(text);
        this.decipheredText=decrypt(text);
        this.text=decipheredText;
        this.cipheredText=text;
        return this.decipheredText;
    }

    public void setABN(String a, String b, String n) throws NumberFormatException, emptyField, InvalidCharacter{
        if(a.isEmpty()||b.isEmpty()||n.isEmpty()){
            throw new emptyField("Por favor complete todos los campos");
        }
        this.a=Integer.parseInt(a);
        this.b=Integer.parseInt(b);
        this.n=Integer.parseInt(n);
        if(this.a<=0 || this.b<=0 || this.n<=0){
            throw new InvalidCharacter("Las constantes no pueden ser negativas o 0");
        }
        int big = Math.max(this.a, this.n);
        int small = Math.min(this.a, this.n);

        while (small != 0) {
            int temp = small;
            small = big % small;
            big = temp;
        }

        if (big != 1) {
            throw new InvalidCharacter(
                    "La constante de decimación debe ser coprimo con el módulo de la cifra"
            );
        }
    }
    public String getText() {
        return text;
    }
    public String getCipheredText() {
        return cipheredText;
    }
    public int[] getIndex() {
        return index;
    }
    public int[] getIndex2() {
        return index2;
    }
    private String encrypt(String text){
        char[] textAux=text.toCharArray();
        char[] alphabet= AlphabetBuilder.build();
        int[] index= new int[textAux.length];
        int[] index2= new int[textAux.length];
        int k=0;
        for (char aux : textAux) {
            for (int c = 0; c < alphabet.length; c++) {
                if (alphabet[c] == aux) {
                    index[k] = c;
                    k++;
                }
            }
        }
        this.index=index;
        for(int i=0;i<index.length;i++){
            index2[i]=((a*index[i]+b)%n);
        }
        this.index2=index2;
        StringBuilder cipheredText=new StringBuilder();
        for (int j : index2) {
            cipheredText.append(alphabet[j]);
        }
        return cipheredText.toString();
    }
    private int getInverseA(){
        int x=1;
        int k=1;
        int mod=1;
        while((x%this.a)!=0){
            x=((n*k)+1);
            k++;
        }
        x=x/this.a;
        this.InverseA=x;
        return x;
    }
    public int InverseA() {
        return InverseA;
    }
    private String decrypt(String text){
        int a=getInverseA();
        char[] textAux=text.toCharArray();
        char[] alphabet= AlphabetBuilder.build();
        int[] index= new int[textAux.length];
        int[] index2= new int[textAux.length];
        int k=0;
        for (char aux : textAux) {
            for (int c = 0; c < alphabet.length; c++) {
                if (alphabet[c] == aux) {
                    index[k] = c;
                    k++;
                }
            }
        }
        this.index=index;
        for(int i=0;i<index.length;i++){
            index2[i]=((a*(index[i]-b+n))%n);
        }
        this.index2=index2;
        StringBuilder decipheredText=new StringBuilder();
        for (int j : index2) {
            decipheredText.append(alphabet[j]);
        }
        return decipheredText.toString();
    }
}
