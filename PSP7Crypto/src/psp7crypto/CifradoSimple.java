package psp7crypto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class CifradoSimple {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        
        System.out.println(".----------------.");
        System.out.println("|ENCRIPTAR STRING|");
        System.out.println("`----------------´");
        System.out.println("Indica un string: ");
        Scanner variScan = new Scanner(System.in);
        String mensaje = variScan.nextLine();        
        
        byte[] mensajeCifrado;
        String mensajeDescifrado;
        
        ejemploCifrado ejemplo = new ejemploCifrado();
        
        //CIFRAR MENSAJE
        mensajeCifrado = ejemplo.cifrarMensaje(mensaje);
              
       //MENSAJE NORMAL
        System.out.println("el string que has dado es "+mensaje);
        
        //MENSAJE CIFRADO
        System.out.println("El string cifrado es " + mensajeCifrado);
        System.out.println("Se ha generado el archivo en  raiz de proyecto : archivo.cifrado\n\n");
        
        System.out.println(".--------------------.");
        System.out.println("|DESENCRIPTAR ARCHIVO|");
        System.out.println("`--------------------´");        
        System.out.println("Leemos archivo...");
          
               
        //DESCIFRAR MENSAJE
        mensajeDescifrado = ejemplo.descifrarMensaje(mensajeCifrado);
        
        //MENSAJE DESCIFRADO
        System.out.println("Desciframos contenido del archivo: \n"+mensajeDescifrado);
 
        
    }
}
