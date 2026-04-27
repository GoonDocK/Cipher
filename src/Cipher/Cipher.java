package Cipher;

import Miscellaneous.Exceptions.emptyField;

public abstract class Cipher {
    public abstract String getTextCorrected(String key) throws emptyField;
    public abstract String getCipheredText(String text) throws emptyField;
    public abstract String getDecryptedText(String text) throws emptyField;
}
