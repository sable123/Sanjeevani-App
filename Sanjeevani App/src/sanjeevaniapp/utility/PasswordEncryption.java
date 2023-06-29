/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.utility;

import java.util.Base64;

/**
 *
 * @author ABC
 */
public class PasswordEncryption {
    public static String getEncryptedPassword(String Password){
        Base64.Encoder encoder=Base64.getEncoder();
        return encoder.encodeToString(Password.getBytes());
    }
     
}
class Test{
    public static void main(String[] args) {
        String s="admin";
        System.out.println(PasswordEncryption.getEncryptedPassword(s));
    }
}