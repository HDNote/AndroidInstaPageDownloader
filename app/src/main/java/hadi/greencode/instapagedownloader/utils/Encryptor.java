package hadi.greencode.instapagedownloader.utils;


import android.util.Base64;

public class Encryptor {

    public static String encode(String input) {
        return input == null ? null : Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }

    public static String decode(String encoded) {
        return encoded == null ? null : new String(Base64.decode(encoded.getBytes(), Base64.DEFAULT));
    }
}
