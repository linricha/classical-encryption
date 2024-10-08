Name: Richard Lin.


Description:
A group of files containing methods and information that allows a person to run either .java file from the main branch and enter inputs to each file, which will therein encrypt and decrypt using Caesar or Vigenere Encryption if provided an acceptable input. It will say otherwise when inputs fail to follow their designed format.  

List of Resources:
Reading on this Mini Project: https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Fa/mps/mp01.html

Link to Github Repository:
https://github.com/linricha/classical-encryption

Redo Notes: (Fixed checkstyle errors, fixed testing errors, and fixed project formatting issues)
- Updated Cipher.java: (fixed checkstyle errors)
      Added private static final int variables and replaced the numbers/ints used with these variables.

- Updated AllCaesar.java: (fixed checkstyle errors)
      Added private static final int variables and replaced the numbers/ints used with these variables.

- Removed extra files from before: (fixed formatting project issue from before)
      target folder, the .class folders in target, reports for checks, etc.

- Updated pom.xml: 
      Not a big change. Just added -SNAPSHOT to <version>1.0<version> just in case it matters/changes something.

- Updated CipherUtils.java: (fixed bug/testing errors and checkstyle errors)
      - Added private static final int variables and replaced the numbers/ints used with these variables where checkstyle errors come up.
        (Did this to adhere to checkstyle rules)

      - Changed spacings and format (with addition of newlines) (fixed checkstyle errors).
  
      - Updated method update(String, String, int, int): (fixed bug/testing errors)
          - changed
             strKey[i] = keyArr[0 + (caesarOrVigenere * i) % keyArr.length] + strArr[i] * encryptOrDecrypt;
            to
             strKey[i] = letter2int(strArr[i])
            + letter2int(keyArr[0 + ((caesarOrVigenere * i) % keyArr.length)]) * encryptOrDecrypt;

      - Updated createOrder(String[], int): (fixed bug/testing errors)
          - Fixed ELSE part of the first IF by removing i++ from the first two IF's of ELSE.
          - Added additional clause to IF using && for both IF's of the first two IF's after the ELSE of the first IF.
          - Added boolean stringMatched and added clauses to each if in FOR loop to check (!stringMatched)
          - Added IF check for (!firstStrSeen)
     
      - Created method checkEmptyString(String[], PrintWriter, String) and relevant comments: (Checks for errors / used to fix bug/testing errors)
 
      - Updated errorCheck(String[], String[], boolean): (Fixed bug/testing errors)
          - Added !checkEmptyString in the IF clause with order[KEY].
     
      - Updated listEncryption(String, String, PrintWriter, int, int, boolean): (fixed bugs/testing errors)
          - Added boolean parameter to be used in IF statement
          - Changed println line of FOR loop
          - Changed parameters to caesarEncrypt and caesarDecrypt




