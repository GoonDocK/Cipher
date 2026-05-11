package Cipher.Vernam;
import Cipher.Cipher;
import Miscellaneous.Exceptions.InvalidCharacter;
import Miscellaneous.Exceptions.TooShortKey;
import Miscellaneous.Exceptions.emptyField;
import java.util.HashMap;
import java.util.Map;
public class VernamCipher extends Cipher {
    private String key;
    private String text;
    private String cipheredText;
    private String decipheredText;
    private String[] textBinary;
    private String[] keyBinary;
    private String[] cipheredTextBinary;
    private String[] decipheredTextBinary;
    public static final Map<Character, String> baudotToChar = new HashMap<>();
    public static final Map<String, Character> charToBaudot = new HashMap<>();
    public VernamCipher(){
        startBaudot();
    }
    public void clear(){
        this.key=null;
        this.text=null;
        this.cipheredText=null;
        this.decipheredText=null;
        this.textBinary=null;
        this.keyBinary=null;
        this.cipheredTextBinary=null;
        this.decipheredTextBinary=null;
    }
    @Override
    public String getTextCorrected(String key) throws emptyField, InvalidCharacter {
        StringBuilder keyAux=new StringBuilder();
        if(key.isEmpty()){
            throw new emptyField("Por favor complete todos los campos");
        }
        key=key.toUpperCase();
        for(int i=0;i<key.length();i++){
            if(key.charAt(i)<'A' || key.charAt(i)>'Z') throw new InvalidCharacter("Caracteres inválidos detectados");
            if(!(key.charAt(i)==32)) keyAux.append(key.charAt(i));
        }
        return keyAux.toString();
    }

    @Override
    public String getCipheredText(String text) throws emptyField {
        this.cipheredTextBinary=new String[textBinary.length];
        for(int i=0;i<textBinary.length;i++){
            this.cipheredTextBinary[i]=Xor(textBinary[i], keyBinary[i]);
        }
        StringBuilder cipheredTextAux=new StringBuilder();
        for (int i = 0; i < cipheredTextBinary.length; i++) {
            if(i==cipheredTextBinary.length-1) cipheredTextAux.append(cipheredTextBinary[i]);
            else cipheredTextAux.append(cipheredTextBinary[i]).append(", ");
        }
        return cipheredTextAux.toString();
    }

    @Override
    public String getDecryptedText(String text) throws emptyField {
        this.decipheredTextBinary=new String[cipheredTextBinary.length];
        for(int i=0;i<cipheredTextBinary.length;i++){
            decipheredTextBinary[i]=Xor(cipheredTextBinary[i], keyBinary[i]);
        }
        this.decipheredText=convertBinaryToText(this.decipheredTextBinary);
        return this.decipheredText;
    }
    public String[] getCipheredTextBinary(){
        return this.cipheredTextBinary;
    }
    public String getKey(){
        return this.key;
    }
    public void setKey(String key) throws emptyField, InvalidCharacter, TooShortKey{
        this.key=getTextCorrected(key);
        StringBuilder keyAux=new StringBuilder();
        try{
            if(this.key.length()<this.text.length()){
                throw new TooShortKey("La clave debe ser mayor que el texto");
            }
            for(int i=0;i<this.text.length();i++){
                keyAux.append(this.key.charAt(i));
            }
        }catch(NullPointerException e){
            if(this.key.length()<this.cipheredTextBinary.length){
                throw new TooShortKey("La clave debe ser mayor que el texto cifrado");
            }
            for(int i=0;i<this.cipheredTextBinary.length;i++){
                keyAux.append(this.key.charAt(i));
            }
        }
        this.key=keyAux.toString();
        this.keyBinary=convertTextToBinary(this.key);
    }
    public void setText(String text) throws emptyField, InvalidCharacter{
        this.text=getTextCorrected(text);
        this.textBinary=convertTextToBinary(this.text);
    }
    public String getText(){
        return this.text;
    }
    public void setCipheredText(String cipheredText){
        this.cipheredText=cipheredText;
        StringBuilder cipheredTextAux=new StringBuilder();
        for(Character c: this.cipheredText.toCharArray()){
            if(!(c==32)){
                cipheredTextAux.append(c);
            }
            if(c!='0' && c!='1' && c!=',' && c!=32){
                throw new InvalidCharacter("El texto cifrado solo puede contener 0 y 1");
            }
        }
        this.cipheredTextBinary=cipheredTextAux.toString().split(",");
    }
    private String[] convertTextToBinary(String text){
        String[] binary=new String[text.length()];
        for(int i=0;i<text.length();i++){
            binary[i]= baudotToChar.get(text.charAt(i));
        }
        return binary;
    }
    public void startBaudot(){
        baudotToChar.put('A', "00011");
        baudotToChar.put('B', "11001");
        baudotToChar.put('C', "01110");
        baudotToChar.put('D', "01001");
        baudotToChar.put('E', "00001");
        baudotToChar.put('F', "01101");
        baudotToChar.put('G', "11010");
        baudotToChar.put('H', "10100");
        baudotToChar.put('I', "00110");
        baudotToChar.put('J', "01011");
        baudotToChar.put('K', "01111");
        baudotToChar.put('L', "10010");
        baudotToChar.put('M', "11100");
        baudotToChar.put('N', "01100");
        baudotToChar.put('O', "11000");
        baudotToChar.put('P', "10110");
        baudotToChar.put('Q', "10111");
        baudotToChar.put('R', "01010");
        baudotToChar.put('S', "00101");
        baudotToChar.put('T', "10000");
        baudotToChar.put('U', "00111");
        baudotToChar.put('V', "11110");
        baudotToChar.put('W', "10011");
        baudotToChar.put('X', "11101");
        baudotToChar.put('Y', "10101");
        baudotToChar.put('Z', "10001");
        for (Map.Entry<Character, String> entry : baudotToChar.entrySet()) {
            charToBaudot.put(entry.getValue(), entry.getKey());
        }
    }
    private String convertBinaryToText(String[] binary){
        StringBuilder text= new StringBuilder();
        for(int i=0;i<binary.length;i++){
            if(charToBaudot.containsKey(binary[i]))text.append(charToBaudot.get(binary[i]));
            else throw new InvalidCharacter("No existe el character "+this.cipheredTextBinary[i]);
        }
        return text.toString();
    }
    // own XOR methods since java only works with binary, this XOR will use Strings
    private String Xor(String textBinary, String keyBinary){
        StringBuilder xor=new StringBuilder();
        for(int i=0;i<textBinary.length();i++){
            if(textBinary.charAt(i)==keyBinary.charAt(i))xor.append("0") ;
            else xor.append("1") ;
        }
        return xor.toString();
    }

}
