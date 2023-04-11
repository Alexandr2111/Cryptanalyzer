package ru.ivanov;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class CaesarCipher {
        private static final String ALPHABET_L_C = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,”:()-!? ";
        private static final String ALPHABET_U_C = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,”:()-!? ";

        public static void decryptBruteForce(String path) {
            String pathOfDecryptedFile = path.substring(0,path.lastIndexOf('\\') + 1) + "decrypted" +
                path.substring((path.lastIndexOf('\\') + 1));
            Path decryptedFile = Path.of(pathOfDecryptedFile);
            CaesarCipher.createFile(decryptedFile);

            for (int i = 1; i < ALPHABET_L_C.length(); i++)
            {
                String decryptText = decrypt(readFromFile(path), i);
                char[] decryptString = decryptText.toCharArray();
                if (decryptionCheck(decryptString))
                {
                    try
                    {
                        Files.writeString(decryptedFile, decryptText);
                    }
                    catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            }
//
         }

        public static void encryptFromFile(String path, int key){
            String pathOfEncryptedFile = path.substring(0,path.lastIndexOf('\\') + 1) + "encrypted" +
                    path.substring((path.lastIndexOf('\\') + 1));
            Path encryptedFile = Path.of(pathOfEncryptedFile);
            CaesarCipher.createFile(encryptedFile);
             try
             {
                 Files.writeString(encryptedFile, encrypt(readFromFile(path), key));
             }
             catch (IOException e)
             {
                 throw new RuntimeException(e);
             }
        }

        public static void decryptFromFile(String path, int key) {
            String pathOfDecryptedFile = path.substring(0,path.lastIndexOf('\\') + 1) + "decrypted" +
                    path.substring((path.lastIndexOf('\\') + 1));
            Path decryptedFile = Path.of(pathOfDecryptedFile);
            CaesarCipher.createFile(decryptedFile);
            try
            {
                Files.writeString(decryptedFile, decrypt(readFromFile(path), key));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }

            private static boolean decryptionCheck(char[] decryptString) {

                boolean checkPunctuationChar = false;
                boolean checkWrongKey = false;

                int checkBar = 5;
                int successfullChecksCounter = 0;

                int upperCaseLetterCounter = 0;
                int lowerCaseLetterCounter = 0;
                int spaceSymbolCounter = 0;

                for (int i = 0; i < decryptString.length - 1; i++)
                {
                    if ((Character.getType(decryptString[i]) == Character.OTHER_PUNCTUATION) && (decryptString[i + 1] == ' '))
                    {
                        checkPunctuationChar = true;
                    }

                    if ((Character.getType(decryptString[i]) == Character.LOWERCASE_LETTER) && (Character.getType(decryptString[i + 1]) == Character.UPPERCASE_LETTER))
                    {
                        checkWrongKey = true;
                    }

                    if (Character.isWhitespace(decryptString[i]))
                    {
                        spaceSymbolCounter++;
                    }

                    if (Character.getType(decryptString[i]) == Character.LOWERCASE_LETTER)
                    {
                        lowerCaseLetterCounter++;
                    }

                    if (Character.getType(decryptString[i]) == Character.UPPERCASE_LETTER)
                    {
                        upperCaseLetterCounter++;
                    }
                }

                    if (checkPunctuationChar)
                    {
                        successfullChecksCounter++;
                    }

                    if (!checkWrongKey)
                    {
                        successfullChecksCounter++;
                    }

                    if (spaceSymbolCounter > decryptString.length / 10)
                    {
                        successfullChecksCounter++;
                    }

                    if (upperCaseLetterCounter < decryptString.length / 10)
                    {
                        successfullChecksCounter++;
                    }

                    if (lowerCaseLetterCounter > decryptString.length / 3)
                    {
                        successfullChecksCounter++;
                    }

                    return successfullChecksCounter >= checkBar;
            }

            private static void createFile(Path path) {
                try
                {
                    if(Files.exists(path))
                    {
                        Files.delete(path);
                    }
                    Files.createFile(path);
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }

            private static String readFromFile(String path) {
                String text = "";
                try (FileReader in = new FileReader(path);
                     BufferedReader reader = new BufferedReader(in))
                {
                    while (reader.ready())
                    {
                        String line = reader.readLine();
                        text = text + line + "\n";
                    }
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                return text;
            }

        private static String encrypt(String input, int key) {
                String result = "";
                for (int i = 0; i < input.length(); i++)
                {
                    if (Character.isLetter(input.charAt(i)) && Character.isLowerCase(input.charAt(i)) && (ALPHABET_L_C.indexOf(input.charAt(i)) != -1))
                    {
                        int charIndex = ALPHABET_L_C.indexOf(input.charAt(i));
                        int newIndex = (charIndex + key) % ALPHABET_L_C.length();
                        char cipherChar = ALPHABET_L_C.charAt(newIndex);
                        result = result + cipherChar;
                    }
                    else if (Character.isLetter(input.charAt(i)) && Character.isUpperCase(input.charAt(i)) && (ALPHABET_U_C.indexOf(input.charAt(i)) != -1))
                    {
                        int charIndex = ALPHABET_U_C.indexOf(input.charAt(i));
                        int newIndex = (charIndex + key) % ALPHABET_U_C.length();
                        char cipherChar = ALPHABET_U_C.charAt(newIndex);
                        result = result + cipherChar;
                    }
                    else if ((ALPHABET_L_C.indexOf(input.charAt(i)) != -1) && (ALPHABET_U_C.indexOf(input.charAt(i)) != -1))
                    {
                        int charIndex = ALPHABET_L_C.indexOf(input.charAt(i));
                        int newIndex = (charIndex + key) % ALPHABET_L_C.length();
                        char cipherChar = ALPHABET_L_C.charAt(newIndex);
                        result = result + cipherChar;
                    }
                    else
                    {
                        result = result + input.charAt(i);
                    }
                }
                return result;
        }

        private static String decrypt(String input, int key) {
            String result = "";
            for (int i = 0; i < input.length(); i++)
            {
                if (Character.isLetter(input.charAt(i)) && Character.isLowerCase(input.charAt(i))
                        && (ALPHABET_L_C.indexOf(input.charAt(i)) != -1))
                {
                    int charIndex = ALPHABET_L_C.indexOf(input.charAt(i));
                    int newIndex = (charIndex - key) % ALPHABET_L_C.length();
                    if (newIndex < 0)
                    {
                        newIndex = ALPHABET_L_C.length() + newIndex;
                    }
                    char plainChar = ALPHABET_L_C.charAt(newIndex);
                    result = result + plainChar;
                }
                else if (Character.isLetter(input.charAt(i)) && Character.isUpperCase(input.charAt(i))
                        && (ALPHABET_U_C.indexOf(input.charAt(i)) != -1))
                {
                    int charIndex = ALPHABET_U_C.indexOf(input.charAt(i));
                    int newIndex = (charIndex - key) % ALPHABET_U_C.length();
                    if (newIndex < 0)
                    {
                        newIndex = ALPHABET_U_C.length() + newIndex;
                    }
                    char plainChar = ALPHABET_U_C.charAt(newIndex);
                    result = result + plainChar;
                }
                else if ((ALPHABET_U_C.indexOf(input.charAt(i)) != -1)
                        && (i == 0 || ((input.charAt(i - 1) == 'а') && (input.charAt(i - 2) == ','))))
                {
                    int charIndex = ALPHABET_U_C.indexOf(input.charAt(i));
                    int newIndex = (charIndex - key) % ALPHABET_U_C.length();
                    if (newIndex < 0)
                    {
                        newIndex = ALPHABET_U_C.length() + newIndex;
                    }
                    char plainChar = ALPHABET_U_C.charAt(newIndex);
                    result = result + plainChar;
                }
                else if ((ALPHABET_L_C.indexOf(input.charAt(i)) != -1))
                {
                    int charIndex = ALPHABET_L_C.indexOf(input.charAt(i));
                    int newIndex = (charIndex - key) % ALPHABET_L_C.length();
                    if (newIndex < 0)
                    {
                        newIndex = ALPHABET_L_C.length() + newIndex;
                    }
                    char plainChar = ALPHABET_L_C.charAt(newIndex);
                    result = result + plainChar;
                }
                else
                {
                    result = result + input.charAt(i);
                }
            }
            return result;
        }

}
