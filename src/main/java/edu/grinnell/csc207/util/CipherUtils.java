package edu.grinnell.csc207.util;

import java.io.PrintWriter;

/**
 * Methods related to ciphering, deciphering, printing the results of the coding, and ensuring that
 * certain parameters are included / met before encryption.
 *
 * @author
 *  Richard Lin
 */
public class CipherUtils {

  /**
   * int location in the array created by createOrder that contains informaton about the
   * sentence/collection of letters that are going to be coded.
   */
  private static final int TOBECODED = 0;

  /**
   * int location in the array created by createOrder that contains information about the
   * key used for the encryption.
   */
  private static final int KEY = 1;

  /**
   * int location in the array created by createOrder that contains information about the
   * type of cryption: Caesar or Vinegere.
   */
  private static final int CRYPTIONTYPE = 2;

  /**
   * Int location in the array created by createOrder that contains information about the
   * type of coding: Encoding or Decoding.
   */
  private static final int CODINGTYPE = 3;

  /**
   * Length of the Alphabet.
   */
  private static final int ALPHABETLENGTH = 26;

  /**
   * The length that the Command-line arguments should be.
   */
  private static final int ARGSLENGTH = 4;


  /**
   * Converts a lowercase, alphabetical letter into its position
   * equivalent - 1 in the order that it appears in the alphabet.
   *
   * @param letter
   *  to be converted into a char.
   * @return
   *  an int representing the char for the purpose of later translation.
   */
  private static int letter2int(char letter) {
    return (int) letter - (int) ('a');
  } // letter2int(char)

  /**
   * Converts an integer into its alphabetical, lowercase equivalent.
   *
   * @param i
   *  An integer that is going to be turned into a char between 'a' to 'z', inclusive.
   * @return
   *  the alphabetical, lowercase letter equivalent of the integer.
   */
  private static char int2letter(int i) {
    return (char) (i + (int) ('a'));
  } // int2letter(int)

  /**
   * Encrypts a string str based on the type of ciphering and encryption and the key.
   *
   * Fixed ERROR in update! (REVISION).
   *
   * @param str
   *  A string to be encrypted.
   * @param key
   *  A string to be used for encrypting str.
   * @param encryptOrDecrypt
   *  an int value of -1 or 1, representing to either encrypt, 1, or decrypt, - 1.
   * @param caesarOrVigenere
   *  an int value of 0 or 1, representing to either cipher using caesar, 0, or vigenere, 1.
   * @return
   *  An encrypted String.
   */
  public static String update(String str, String key, int encryptOrDecrypt, int caesarOrVigenere) {

    char[] strArr = str.toCharArray();
    char[] keyArr = key.toCharArray();

    int[] intStr = new int[strArr.length];
    int[] intKey = new int[keyArr.length];

    // Change char[] values stored to int[] stored values
    for (int i = 0; i < keyArr.length; i++) {
      intKey[i] = letter2int(keyArr[i]);
    } // for
    for (int i = 0; i < strArr.length; i++) {
      intStr[i] = letter2int(strArr[i]);
    } // for

    int[] strKey = new int[intStr.length];
    char[] newStr = new char[strKey.length];

    int alphabetLength = ALPHABETLENGTH;

    // Alter values through numeric encryption/alteration and revert back to char values.
    for (int i = 0; i < intStr.length; i++) {
      strKey[i] = letter2int(strArr[i])
          + letter2int(keyArr[0 + ((caesarOrVigenere * i) % keyArr.length)]) * encryptOrDecrypt;
      strKey[i] = (strKey[i] + alphabetLength)  % alphabetLength;
      newStr[i] = int2letter(strKey[i]);
    } // for

    return new String(newStr);
  } // update(String, String, int, int)

  // key:
  // 3 = encode / decode
  // 0 , 1 = string, 1 is key
  // 2 = cipher

  /**
   * Creates an array where the Strings in args are stored and ordered
   * in to represent certain roles.
   *
   * Updated incrementing of i in the two if clauses after else.
   * Added a "" if case for firstStrSeen.
   *
   * @param args
   *  A String array representing the inputs to main().
   * @param argsLength
   *  Length of the String[].
   * @return an array where the elements of args is reordered to better
   *  identify the role the Strings play where [0] is the string, [1] is
   *  the key, [2] is the ciphering Type, and [3] is the coding type.
   */
  public static String[] createOrder(String[] args, int argsLength) {

    // Checks
    String[] order = new String[ARGSLENGTH];
    boolean firstStrSeen = false;
    boolean secondStrSeen = false;
    boolean thirdStrSeen = false;
    boolean fourthStrSeen = false;

    int toBeCodedStr = TOBECODED;
    int key = KEY;
    int cryptionType = CRYPTIONTYPE;
    int typeOfCoding = CODINGTYPE;

    boolean stringMatched = false;

    // Initialize if for a certain input length
    if (argsLength == 2) {
      order[toBeCodedStr] = args[1];
      order[key] = "";
      order[cryptionType] = "";
      order[typeOfCoding] = args[0];
      return order;
    } else { // Detect what certain inputs are and organize accordingly

      for (int i = 0; i < argsLength; i++) {
        stringMatched = false;

        if (args[i].length() > 2) {
          if (((args[i].substring(0, 2).compareTo("-e") == 0)
              || (args[i].substring(0, 2).compareTo("-d") == 0))
              && !fourthStrSeen) {

            order[typeOfCoding] = args[i];
            fourthStrSeen = true;

            stringMatched = true;
          } // if
          if (((args[i].substring(0, 2).compareTo("-c") == 0)
              || (args[i].substring(0, 2).compareTo("-v") == 0))
              && !thirdStrSeen && !stringMatched) {
            order[cryptionType] = args[i];
            thirdStrSeen = true;

            stringMatched = true;
          } // if
        } // if

        if (!firstStrSeen && !stringMatched) {
          order[toBeCodedStr] = args[i];
          firstStrSeen = true;
        } else if (!secondStrSeen && !stringMatched) {
          order[key] = args[i];
          secondStrSeen = true;
        } // if/else if
      } // for

      // Check to make sure no string is represented as null
      if (!fourthStrSeen) {
        order[typeOfCoding] = "";
      } // if
      if (!thirdStrSeen) {
        order[cryptionType] = "";
      } // if
      if (!secondStrSeen) {
        order[key] = "";
      } // if
      if (!firstStrSeen) { // when argslength is 0
        order[key] = "";
      } // if
      return order;
    } // if/else
  } // createOrder(String[], int)


  /**
   * Checks for errors in the inputs.
   *
   * @param args
   *  represents the inputs to main().
   * @param order
   *  An ordered array with specific representations based on where the Strings are stored.
   * @param allCaesarOrCipher
   *  represents whether check is used for allCaesar,true, or Cipher, false.
   * @return
   *  true if all checks are passed and false if not.
   */
  public static boolean errorCheck(String[] args, String[] order, boolean allCaesarOrCipher) {

    PrintWriter anError = new PrintWriter(System.err, true);
    String prefix;

    if (allCaesarOrCipher) {
      prefix = "";
    } else {
      prefix = "-";
    } // if/else


    //Check for errors
    if (!allCaesarOrCipher) {
      if (checkCipherType(order[CRYPTIONTYPE], anError) == 0) {
        return false;
      } // if
    } // if

    if (checkCodingType(order[CODINGTYPE], anError, prefix) == 0) {
      return false;
    } // if

    if (!allCaesarOrCipher) {
      if (!checkKey(order, anError)) {
        return false;
      } // if
    } // if

    if (!checkLowerCase(order[TOBECODED], anError)) {
      return false;
    } // if

    if (!allCaesarOrCipher) {
      if (!checkLowerCase(order[KEY], anError) || !checkEmptyString(order[KEY], anError, "KEY")) {
        return false;
      } // if
    } // if


    return true;
  } // errorCheck(String[], String[], boolean)


  /**
   * Checks if the array of inputs is the required length.
   *
   * @param args
   *  represents the array containing the inputs to main.
   * @param intendedLength
   *  Length that args should be.
   * @param pen
   *  Used to print.
   * @return
   *  boolean value saying whether the check has been passed.
   */
  public static boolean checkArgsLength(String[] args, int intendedLength, PrintWriter pen) {
    if (args.length != intendedLength) {
      pen.println("Error: Incorrect number of parameters.");
      pen.flush();
      return false;
    } // if
    return true;
  } // checkArgsLength(String[], int, PrintWriter)

  /**
   * Checks that the key is one character when the ciphering is that of caesar.
   *
   * @param args
   *  represents the array containing the inputs to main.
   * @param pen
   *  Used to Print.
   * @return
   *  boolean value saying whether the check has been passed.
   */
  public static boolean checkKey(String[] args, PrintWriter pen) {
    if (args[2].compareTo("-caesar") == 0) {
      if (args[1].length() > 1) {

        pen.println("Error: Invalid option. Key: \"" + args[1]
            +
            "\" is a multi-character key, which is not allowed for caesar encryption/decryption.");
        pen.flush();
        return false;
      } // if
    } // if
    return true;
  } // checkKey(String[], PrintWriter)

  /**
   * Checks that str is not an empty string.
   *
   * This is ADDED!!!
   *
   * @param str
   *  The String to be looked at.
   * @param pen
   *  Used to print error messages.
   * @param part
   *  The part that the string represents.
   * @return
   *  Boolean value indicating if string str is or is not valid to use.
   */
  public static boolean checkEmptyString(String str, PrintWriter pen, String part) {
    if (str.compareTo("") == 0) {
      pen.println("Error: Invalid option. " + part + ": \"" + str
          +
          "\" is an empty string, which is not allowed.");
      return false;
    } // if
    return true;
  } // checkNullString(String, PrintWriter, String)

  /**
   * Checks which option is chosen from.
   *
   * @param str
   *  to be compared with to see what it is
   * @param pen
   *  Used to Print.
   * @param strCompare1
   *  to see if str is equal to strCompare1
   * @param strCompare2
   *  to see if str is equal to strCompare2
   * @param errorMessage
   *  Error message to be printed if neither strCompare1 and strCompare2 are the same as str.
   * @return
   *  a int representing whether str is equal to strCompare1, 1; strCompare2, 2; or neither, 0.
   */
  public static int checkOptionChosenTwo(String str, PrintWriter pen,
      String strCompare1, String strCompare2, String errorMessage) {

    if (str.compareTo(strCompare1) == 0) {
      return 1;
    } else if (str.compareTo(strCompare2) == 0) {
      return 2;
    } else {
      pen.println(errorMessage);
      pen.flush();
      return 0;
    } // if/else-if/else
  } // checkOptionChosenTwo(String, PrintWriter, String, String, String)

  /**
   * Checks if str represents a valid cipher or not.
   *
   * @param str
   *  the string to be compared with, recorded as the string representing what cipher to do.
   * @param pen
   *  Used to print.
   * @return
   *  an int representing whether to cipher by caesar, vigenere, or neither.
   */
  public static int checkCipherType(String str, PrintWriter pen) {
    String str1 = "-caesar";
    String str2 = "-vigenere";
    String message =
        "Error: Invalid/missing option: Valid options are \"-caesar\" or \"-vigenere\"";

    return checkOptionChosenTwo(str, pen, str1, str2, message);

  } // checkCipherType(String, PrintWriter)

  /**
   * Checks if str represents a valid coding or not.
   *
   * @param str
   *  the string to be compared with, recorded as the string representing what coding to do.
   * @param pen
   *  Used to print.
   * @param prefix
   *  Used to fit the design of either AllCaesar or Cipher if needed.
   * @return
   *  an int representing whether to code by encoding, decoding, or neither.
   */
  public static int checkCodingType(String str, PrintWriter pen, String prefix) {

    String str1 = prefix.concat("encode");
    String str2 = prefix.concat("decode");
    String message =
        "Error: Invalid/missing option: Valid options are \"" + str1 + "\" or \"" + str2 + "\".";

    return checkOptionChosenTwo(str, pen, str1, str2, message);
  } // checkCodingType(String, PrintWriter, String)

  /**
   * Checks if a string str is all lowercase.
   *
   * @param str
   *  the string that's going to be checked by.
   * @param pen
   *  Used to print.
   * @return
   *  a boolean showing whether or not str is all lowercase.
   */
  public static boolean checkLowerCase(String str, PrintWriter pen) {

    for (int i = 0; i < str.length(); i++) {
      if ((str.charAt(i) < 'a') || (str.charAt(i) > 'z')) {
        pen.println("Error: String contains characters other than lowercase letters.");
        pen.flush();
        return false;
      } // if
    } // for
    return true;
  } // checkLowerCase(String, PrintWriter)


  /**
   * Encrypts a string str using the char letter.
   *
   * @param str
   *  The string to be encrypted.
   * @param letter
   *  The key dictating how the string str is going to be encrypted.
   * @return
   *  The encrypted string.
   */
  public static String caesarEncrypt(String str, char letter) {
    int encrypt = 1;
    int caesar = 0;
    String key = String.valueOf(letter);
    String alteredStr = update(str, key, encrypt, caesar);

    return alteredStr;
  } // caesarEncrypt(String, char)

  /**
   * Decrypts the string.
   *
   * @param str
   *  The string to be decrypted.
   * @param letter
   *  The key dictating how the string str is goind to be decrypted.
   * @return
   *  The decrypted string.
   */
  public static String caesarDecrypt(String str, char letter) {
    int decrypt = -1;
    int caesar = 0;
    String key = String.valueOf(letter);
    String alteredStr = update(str, key, decrypt, caesar);

    return alteredStr;
  } // caesarDecrypt(String, char)

  /**
   * Prints the encrypted message.
   *
   * @param str
   *  the string to be encrypted.
   * @param key
   *  a string that dictates how the encryption will go / how it will be encrypted.
   * @param pen
   *  Used to print.
   * @param cipherType
   *  represents what cipher to encrypt by, Caesar:1, Vigenere: 2.
   * @param codingType
   *  represents what encryption to code by, encode:1, decode: 2.
   * @param twoCipherChoices
   *  boolean that tracks how many choices of Ciphers there are.
   */
  public static void listEncryption(String str, String key,
      PrintWriter pen, int cipherType, int codingType, boolean twoCipherChoices) {

    String encryption;

    // Caesar Encryption
    if (cipherType == 1) {

      for (int i = 0; i < ALPHABETLENGTH; i++) {
        if (codingType == 1) {
          encryption =
            caesarEncrypt(str, int2letter((letter2int(key.charAt(0)) + i) % ALPHABETLENGTH));
        } else {
          encryption =
            caesarDecrypt(str, int2letter((letter2int(key.charAt(0)) + i) % ALPHABETLENGTH));
        } // if/else

        if (twoCipherChoices) {
          pen.println(encryption);
          return; // stop
        } else {
          pen.println(key + " = " + int2letter(i) + ": " + encryption);
        } // if/else
      } // for
    } else { // Vigenere Encryption
      if (codingType == 1) {
        encryption = vigenereEncrypt(str, key);
      } else {
        encryption = vigenereDecrypt(str, key);
      } // if/else


      for (int i = 0; i < str.length(); i++) {
        pen.print(encryption.charAt(i));
      } // for
      pen.print("\n");

    } // if/else
  } // listEncryption(String, String, PrintWriter, int, int)

  /**
   * Encodes str by the Vigenere Encryption based on key.
   *
   * @param str
   *  The string to be encrypted.
   * @param key
   *  a string that dictates how the encryption will go / how it will be encrypted.
   * @return
   *  A string encrypted by Vigenere encryption.
   */
  public static String vigenereEncrypt(String str, String key) {
    int encrypt = 1;
    int vigenere = 1;
    String alteredStr = update(str, key, encrypt, vigenere);
    return alteredStr;
  } // vigenereEncrypt(String, String)

  /**
   * Decodes str by the Vigenere Encryption based on key.
   *
   * @param str
   *  The string to be decrypted.
   * @param key
   *  a string that dictates how the encryption will go / how it will be decrypted.
   * @return
   *  A string decrypted using Vigenere encryption.
   */
  public static String vigenereDecrypt(String str, String key) {
    int decrypt = -1;
    int vigenere = 1;
    String alteredStr = update(str, key, decrypt, vigenere);
    return alteredStr;
  } // vigenereDecrypt(String, String)
} // class CipherUtils
