package Cipher.simpleTransposition;
import Cipher.Alphabet.AlphabetBuilder;
import Miscellaneous.Exceptions.emptyField;
import java.util.*;
public class simpleTransposition {
    private final char[] Alpha= AlphabetBuilder.build();
    private String texto;
    private String key;
    private int keySize;
    private int textSize;
    public simpleTransposition(String key) {
        this.key = reformatKey(key);
        this.keySize=setKeySize();
    }
    public simpleTransposition(String texto, String key) {
        this.texto = texto.toUpperCase();
        this.key = key.toUpperCase();
    }
    public int setKeySize() throws emptyField {
        int size=0;
        for(int i=0;i<key.length();i++) if(!(key.charAt(i)==(char) 32))size++;
        if(size!=0) return size;
        throw new emptyField("Debe escribir una palabra clave");
    }
    public int getTextSize() throws emptyField{
        int size=0;
        for(int i=0;i<texto.length();i++) if(!(texto.charAt(i)==(char) 32))size++;
        if (size!=0) return size;
        throw new emptyField("Debe escribir un texto");
    }
    public int getKeySize(){
        return this.keySize;
    }
    public int getMatrixColumnsCount() throws emptyField{
        this.textSize=getTextSize();
       if(keySize<textSize) return (int) Math.ceil(texto.length()/keySize);
       throw new emptyField("La palabra clave debe ser mayor que el texto");
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public String getTexto() {
        return texto;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
    private String reformatKey(String key){
        StringBuilder keyAux=new StringBuilder();
        key=key.toUpperCase();
        for(int i=0;i<key.length();i++){
            if(key.charAt(i)!=32) keyAux.append(key.charAt(i));
        }
        return keyAux.toString();
    }
    public int getMatrixRowsCount(String cipheredText){
        int row= cipheredText.length()/keySize;
        return row;
    }
    public String getTextCorrected(){
        String upperCase=this.texto.toUpperCase();
        StringBuilder textoCorrecto=new StringBuilder();
        for(int i=0;i<upperCase.length();i++){
            if(upperCase.charAt(i)!=32){
                textoCorrecto.append(upperCase.charAt(i));
            }
        }
        while(textoCorrecto.length()%keySize!=0){
             textoCorrecto.append("X");
        }
        return textoCorrecto.toString();
    }
    public int[] getIndex(){
        int[] pos=new int[keySize];
        for(int i=0;i<keySize;i++){
            for(char j: Alpha){
                if(j==key.charAt(i)){
                    pos[i]=j-64;
                }
            }
        }
        //I ain't idea how this part works, but it does
        int[] sorted=pos.clone();
        Arrays.sort(sorted);
        Map<Integer, Queue<Integer>> map = new HashMap<>();

        for (int i = 0; i < sorted.length; i++) {
            map.putIfAbsent(sorted[i], new LinkedList<>());
            map.get(sorted[i]).add(i + 1);
        }

        int[] resultado = new int[pos.length];

        for (int i = 0; i < pos.length; i++) {
            resultado[i] = map.get(pos[i]).poll();
        }
        return resultado;
    }
    public String getCipheredText(StringBuilder[] columns, int[] index){
        String[] columnasFinal = new String[index.length];
        for(int i=0;i<index.length;i++){
            columnasFinal[index[i]-1] = columns[i].toString();
        }
        StringBuilder cifrado = new StringBuilder();

        for(String col : columnasFinal){
            cifrado.append(col);
        }

        return cifrado.toString();
    }
    // Again, WTH is this?
    public String deCrypt(String cipherText, String key){

        int columnas = key.length();
        int filas = cipherText.length() / columnas;

        char[][] matriz = new char[filas][columnas];


        Integer[] orden = new Integer[columnas];
        for(int i=0;i<columnas;i++){
            orden[i] = i;
        }

        Arrays.sort(orden, (a,b) -> Character.compare(key.charAt(a), key.charAt(b)));

        int pos = 0;


        for(int k=0;k<columnas;k++){

            int col = orden[k];

            for(int f=0;f<filas;f++){
                matriz[f][col] = cipherText.charAt(pos++);
            }
        }

        StringBuilder resultado = new StringBuilder();

        for(int f=0;f<filas;f++){
            for(int c=0;c<columnas;c++){
                resultado.append(matriz[f][c]);
            }
        }

        return resultado.toString();
    }

}
