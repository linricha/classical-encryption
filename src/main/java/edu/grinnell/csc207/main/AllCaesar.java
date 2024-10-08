package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Encryption and Decryption using Caesar Encryption.
 *
 * @author Richard Lin
 */
public class AllCaesar {

  /**
   * The int location in the array order that stores the crytion type.
   */
  private static final int CRYPTIONTYPE = 3;

  /**
   * The length that the Command-line arguments should be.
   */
  private static final int ARGSLENGTH = 2;

  /**
   * int location in the array order that stores the string to be
   * encyrpted.
   */
  private static final int TOBECODED = 0;

  /**
   * Runs Caesar Encryption / Decryption on args.
   *
   * @param args Command-line arguments
   */
  public static void main(String[] args) {
    PrintWriter deadpen = new PrintWriter(System.err, true);

    // checks for the correct length
    if (!CipherUtils.checkArgsLength(args, ARGSLENGTH, deadpen)) {
      return;
    } // if

    String[] order = CipherUtils.createOrder(args, ARGSLENGTH);

    //Makes sure checks follow and if not, then stop the program.
    if (!CipherUtils.errorCheck(args, order, true)) {
      return;
    } // if




    PrintWriter pen = new PrintWriter(System.out, true);
    String command = order[CRYPTIONTYPE];
    String str = order[TOBECODED];

    // prints out the full caesar encryption.
    if (CipherUtils.checkCodingType(command, pen, "") == 1) {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(str, ch));
      } // for
    } else {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarDecrypt(str, ch));
      } // for
    } // if/else
    pen.flush();
    pen.close();
  } // main(String[])
} // class AllCaesar
