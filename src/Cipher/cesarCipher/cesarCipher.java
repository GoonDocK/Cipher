package Cipher.cesarCipher;
import Cipher.Alphabet.AlphabetBuilder;
public class cesarCipher extends Cipher.Cipher {
    private final char[] Alpha= AlphabetBuilder.build();
    private String CorrectedText;
    @Override
    public String getTextCorrected(String key) {
        key = key.toUpperCase();
        return key;
    }
    public void setCorrectedText(String correctedText) {
        CorrectedText = correctedText.toUpperCase();
    }
    @Override
    public String getCipheredText(String text){
        StringBuilder textoCifrado=new StringBuilder();
        int[] index=new int[CorrectedText.length()];
        for(int i=0;i<CorrectedText.length();i++){
            for(int j=0;j<26;j++){
                if(CorrectedText.charAt(i)==32){
                    index[i]=32;
                    continue;
                }
                if(CorrectedText.charAt(i)==Alpha[j]){
                    index[i]=(j+3)%26;
                    break;
                }
            }
        }
        for(int i=0;i<index.length;i++){
            if(index[i]==32){
                textoCifrado.append(" ");
                continue;
            }
            textoCifrado.append(Alpha[index[i]]);
        }
        return textoCifrado.toString();
    }
    @Override
    public String getDecryptedText(String text){
        StringBuilder textoDescifrado=new StringBuilder();
        int[] index=new int[CorrectedText.length()];
        for(int i=0;i<CorrectedText.length();i++){
            for(int j=0;j<26;j++){
                if(CorrectedText.charAt(i)==32){
                    index[i]=32;
                    continue;
                }
                if(CorrectedText.charAt(i)==Alpha[j]){
                    index[i]=(j-3)%26;
                    break;
                }
            }
        }
        for(int i=0;i<index.length;i++){
            if(index[i]==32){
                textoDescifrado.append(" ");
                continue;
            }

            textoDescifrado.append(Alpha[index[i]]);
        }
        return textoDescifrado.toString();
    }
}
