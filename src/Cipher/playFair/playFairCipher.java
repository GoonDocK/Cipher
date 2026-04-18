package Cipher.playFair;
import Miscellaneous.Exceptions.emptyField;

import java.util.ArrayList;
import java.util.Arrays;

public class playFairCipher extends Cipher.Cipher{
    private String key;
    private String text;
    @Override
    public String getTextCorrected(String key) {
        StringBuilder keyAux=new StringBuilder();
        if(key.isEmpty()){
            throw new emptyField("Key is empty");
        }
        this.key=key.toUpperCase();
        for(int i=0;i<this.key.length();i++){
            if(key.charAt(i)!=32) keyAux.append(this.key.charAt(i));
        }
        this.key=keyAux.toString();
        check();
        return key;
    }

    @Override
    public String getCipheredText(String text) {
        return "";
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
    private void check() {
        ArrayList<String> keyList = new ArrayList<>();
        StringBuilder keyAux = new StringBuilder();
        int i = 0;

        while (i < key.length()) {

            char first = key.charAt(i);
            char second;

            if (i + 1 < key.length()) {
                second = key.charAt(i + 1);

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

        System.out.println(keyList);
    }
}
