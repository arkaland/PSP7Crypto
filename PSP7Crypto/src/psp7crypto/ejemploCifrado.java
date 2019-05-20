/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp7crypto;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author Vicente
 */
public class ejemploCifrado {

    private Cipher cifrado;
    private byte[] iv;
    private Cipher descifrado;
    private ByteArrayOutputStream outputStream;

    public ejemploCifrado() {

        KeyGenerator kgen;

        try {

            // Se genera la key
            kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            SecretKey aesKey = kgen.generateKey();

            // Cifrado
            cifrado = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cifrado.init(Cipher.ENCRYPT_MODE, aesKey);

            // Descifrado
            descifrado = Cipher.getInstance("AES/CBC/PKCS5Padding");
            iv = cifrado.getIV();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            descifrado.init(Cipher.DECRYPT_MODE, aesKey, ivParameterSpec);

        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(ejemploCifrado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public byte[] cifrarMensaje(String mensaje) {

        String mensajeCifrado = null;
        String mensajeDescifrado = null;
        byte[] BytesEncriptados = null;

        try {
            outputStream = new ByteArrayOutputStream();
            try (CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cifrado)) {
                cipherOutputStream.write(mensaje.getBytes());
                cipherOutputStream.flush();
            }
            BytesEncriptados = outputStream.toByteArray();
            PrintWriter writer = new PrintWriter("fichero.cifrado", "UTF-8");
            writer.println(BytesEncriptados);
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(ejemploCifrado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return BytesEncriptados;

    }

    /*
     public String descifrarMensaje(byte[] mensaje){
        
        outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inStream = new ByteArrayInputStream(mensaje);
        CipherInputStream cipherInputStream = new CipherInputStream(inStream, descifrado);
        byte[] buf = new byte[1024];
        int bytesRead;
        try {
            while ((bytesRead = cipherInputStream.read(buf)) >= 0) {
                outputStream.write(buf, 0, bytesRead);
            }
        } catch (IOException ex) {
            Logger.getLogger(ejemploCifrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new String(outputStream.toByteArray());
        
}
     */
    public String descifrarMensaje(byte[] mensaje) throws FileNotFoundException, IOException{
        
     
        outputStream = new ByteArrayOutputStream();
       
        ByteArrayInputStream inStream = new ByteArrayInputStream(mensaje);
        CipherInputStream cipherInputStream = new CipherInputStream(inStream, descifrado);
        byte[] buf = new byte[1024];
        int bytesRead;
        try {
            Scanner in = new Scanner(new FileReader("fichero.cifrado"));
            String contenido = in.nextLine();
            
            System.out.println(contenido);
            byte[] contenidobytes = contenido.getBytes();
            
            while ((bytesRead = cipherInputStream.read(contenidobytes)) >= 0) {
                outputStream.write(contenidobytes, 0, bytesRead);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ejemploCifrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        File file = new File("fichero.cifrado");
        return new String(outputStream.toByteArray());
        
}

}
