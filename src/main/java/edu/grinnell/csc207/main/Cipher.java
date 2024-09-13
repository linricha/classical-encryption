package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Encryption and Decryption based on inputs to main(), with the choice either Caesar Encryption or Vigenere Encryption
 * 
 * @author Richard Lin
 */
public class Cipher {
  public static void main(String[] args) {

    PrintWriter deadpen = new PrintWriter(System.err, true);

    // checks for the correct length
    if (CipherUtils.checkArgsLength(args, 4, deadpen) == false){
       return;
    } // if

    String[] order = CipherUtils.createOrder(args, 4);

    // Checks that the inputs to main() are of the correct format
    if (CipherUtils.errorCheck(args, order, false) == false){
      return;
    } // if

    PrintWriter pen = new PrintWriter(System.out, true);

    int cipherType = CipherUtils.checkCipherType(order[2], deadpen);
    int codingType = CipherUtils.checkCodingType(order[3], deadpen, "-");

    
    CipherUtils.listEncryption(order[0], order[1], pen, cipherType, codingType);

    pen.flush();
    pen.close();
  } // main(String[])
} // class Cipher
