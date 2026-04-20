package Cipher.playFair;
import Cipher.Alphabet.AlphabetBuilder;
import Cipher.Cipher;
import Miscellaneous.Exceptions.emptyField;
import java.util.ArrayList;
import java.util.List;
public class playFairCipher extends Cipher{
    private String key;
    private String text;
    private String cipheredText;
    private final char[][] matrix=new char[5][5];
    int x;
    int y;
    @Override
    public String getTextCorrected(String text) {
        StringBuilder keyAux=new StringBuilder();
        if(text.isEmpty()){
            throw new emptyField("Text is empty");
        }
        this.text=text.toUpperCase();
        for(int i=0;i<this.text.length();i++){
            if(text.charAt(i)!=32) keyAux.append(this.text.charAt(i));
        }
        this.text=keyAux.toString();
        return text;
    }

    @Override
    public String getCipheredText(String text) {
        return this.cipheredText;
    }

    @Override
    public String getDecryptedText(String text) {
        return "";
    }
    public void setKey(String key) throws emptyField{
        if(key.isEmpty()){
            throw new emptyField("Key is empty");
        }
        this.key=key.toUpperCase();
    }
    /* this part checks that the message that is going to be encrypted can be
    divided in 2 if isn't the case, it will add Xs, and also checks that in the division
    there aren't any characters repeated in the groups of two letters
     */
    private ArrayList<String> check() {
        ArrayList<String> keyList = new ArrayList<>();
        StringBuilder keyAux = new StringBuilder();
        int i = 0;

        while (i < text.length()) {

            char first = text.charAt(i);
            char second;

            if (i + 1 < text.length()) {
                second = text.charAt(i + 1);

                if (first != second) {
                    keyAux.append(first);
                    keyAux.append(second);
                    i += 2;
                } else {
                    keyAux.append(first);
                    keyAux.append('X');
                    i++;
                }
            } else {
                keyAux.append(first);
                keyAux.append('X');
                i++;
            }

            keyList.add(keyAux.toString());
            keyAux.setLength(0);
        }
        return keyList;
    }
    //creates the characters of the new matrix with keyword in it
    public String getMatrix(){
        char[] Alphabet= AlphabetBuilder.build();
        String keyAux=this.key;
        keyAux=keyAux.toUpperCase();
        List<Character> newKey=new ArrayList<>();
        for(int i=0;i<keyAux.length();i++){
            if(!(newKey.contains(keyAux.charAt(i)))){
                if(keyAux.charAt(i)!='I' && keyAux.charAt(i)!='J'){
                        newKey.add(keyAux.charAt(i));
                }
            }
        }
        boolean isOnKey=false;
        for (char c : Alphabet) {
            for (Character character : newKey) {
                if (c == character) {
                    isOnKey = true;
                    break;
                }
            }
            if (!isOnKey) {
                newKey.add(c);
            }
            isOnKey = false;
        }
        StringBuilder newKeyAux=new StringBuilder();
        for(Character c:newKey){
            newKeyAux.append(c);
        }
        String linearMatrix=newKeyAux.toString();
        int h=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                this.matrix[i][j]=linearMatrix.charAt(h);
                h++;
            }
        }
        return linearMatrix;
    }
    private void encrypt(ArrayList<String> division){
        StringBuilder cipheredText=new StringBuilder();
        for(String s: division){
            switch (getAnyCase(s.charAt(0),s.charAt(1))) {
                case 1:
                    cipheredText.append(sameRow(s));
                    break;
                case 2:
                    cipheredText.append(sameColumn(s));
                    break;
                case 3:
                    cipheredText.append(notInRowOrColumn(s));
                    break;
                default:
            }
        }
        this.cipheredText=cipheredText.toString();
    }
    //Methods that return the encrypted text under development
    private String sameRow(String s){
        return null;
    }
    private String sameColumn(String s){
        return null;
    }
    private String notInRowOrColumn(String s){
        return null;
    }
    private int getAnyCase(char first, char second){
         this.x=0;
         this.y=0;
        boolean found=false;
        for(y=0;y<5;y++){
            for(x=0;x<5;x++){
                if(matrix[x][y]==first){
                    found=true;
                    break;
                }
            }
            if(found){
                break;
            }
        }
        //Same Row
        for(int i=0;i<5;i++){
            if(matrix[i][y]==second){
                return 1;
            }
        }
        //Same Column
        for(int i=0;i<5;i++){
            if(matrix[x][i]==second){
                return 2;
            }
        }
        //Characters are not in the same row or column
        return 3;
    }
}
