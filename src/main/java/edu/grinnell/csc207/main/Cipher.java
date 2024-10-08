package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Encryption and Decryption based on inputs to main(),
 * with the choice either Caesar Encryption or Vigenere Encryption.
 *
 * @author Richard Lin
 */
public class Cipher {

  /**
   * int location in the array order that stores the string to be
   * encyrpted.
   */
  private static final int TOBECODED = 0;

  /**
   * int location in the array order that stores the key used for
   * encryption.
   */
  private static final int KEY = 1;

  /**
   * int location in the array order that stores the
   * type of cryption: Caesar or Vinegere.
   */
  private static final int CRYPTIONTYPE = 2;

  /**
   * Int location in the array order that stores the
   * type of coding: Encoding or Decoding.
   */
  private static final int CODINGTYPE = 3;

  /**
   * The length that the Command-line arguments should be.
   */
  private static final int ARGSLENGTH = 4;

  /**
   * Runs Vinegere or Caesar Cipher.
   *
   * @param args
   *  Command-line arguments contain information on how and what
   *  to encode / encode by.
   */
  public static void main(String[] args) {

    PrintWriter deadpen = new PrintWriter(System.err, true);

    // checks for the correct length
    if (!CipherUtils.checkArgsLength(args, ARGSLENGTH, deadpen)) {
      return;
    } // if

    String[] order = CipherUtils.createOrder(args, ARGSLENGTH);

    // Checks that the inputs to main() are of the correct format
    if (!CipherUtils.errorCheck(args, order, false)) {
      return;
    } // if

    PrintWriter pen = new PrintWriter(System.out, true);

    int cipherType = CipherUtils.checkCipherType(order[CRYPTIONTYPE], deadpen);
    int codingType = CipherUtils.checkCodingType(order[CODINGTYPE], deadpen, "-");

    CipherUtils.listEncryption(order[TOBECODED], order[KEY], pen, cipherType, codingType, true);

    pen.flush();
    pen.close();
  } // main(String[])
} // class Cipher
