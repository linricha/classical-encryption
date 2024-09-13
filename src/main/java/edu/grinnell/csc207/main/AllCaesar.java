package edu.grinnell.csc207.main;

import java.lang.String; 
import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Encryption and Decryption using Caesar Encryption.
 * 
 * @author Richard Lin
 */
public class AllCaesar {
  public static void main(String[] args) {
    PrintWriter deadpen = new PrintWriter(System.err, true);

    // checks for the correct length
    if (CipherUtils.checkArgsLength(args, 2, deadpen) == false){
      return;
    } // if

    String[] order = CipherUtils.createOrder(args, 2);

    //Makes sure checks follow and if not, then stop the program.
    if (CipherUtils.errorCheck(args, order, true) == false){
      return;
    } // if




    PrintWriter pen = new PrintWriter(System.out, true);
    String command = order[3];
    String str = order[0];

    // prints out the full caesar encryption.
    if (CipherUtils.checkCodingType(command, pen, "") == 1){
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(str, ch));
      } // for
    } // if
    else{
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarDecrypt(str, ch));
      } // for
    } // if/else
    pen.flush();
    pen.close();
  } // main(String[])
} // class AllCaesar
