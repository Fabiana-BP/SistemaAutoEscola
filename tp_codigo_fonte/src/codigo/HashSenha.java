/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author USUARIO
 */
public class HashSenha {

    /**
     *Método para criptografar senha
     * @param senha
     * @return
     */
    public String hashSenha(String senha){
        MessageDigest algorithm;
        String hash = null;
        byte messageDigest[] = null;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
           messageDigest = algorithm.digest(senha.getBytes("UTF-8"));
           StringBuilder hexString = new StringBuilder();
           for (byte b : messageDigest) {
                     hexString.append(String.format("%02X", 0xFF & b));
                   }
                  hash = hexString.toString();
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
      
       
        return hash;
    }
     
}
