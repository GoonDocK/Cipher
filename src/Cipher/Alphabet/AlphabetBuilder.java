package Cipher.Alphabet;
public class AlphabetBuilder {
    public static char[] build(){
        char[] alphabet=new char[26];
        int i=0;
        for(char c='A';c<='Z';c++){
            alphabet[i]=c;
            i++;
        }
        return alphabet;
    }
}
