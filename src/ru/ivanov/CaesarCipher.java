package ru.ivanov;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class CaesarCipher {
        private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,”:-!? ";

//        public static void decryptBruteForce(String path) {
//////        String pathOfDecryptedFile = path.substring(0,path.lastIndexOf('\\') + 1) + "decrypted" +
//////                path.substring((path.lastIndexOf('\\') + 1));
//////        Path decryptedFile = Path.of(pathOfDecryptedFile);
//////        CaesarCipher.createFile(decryptedFile);
//            for (int i = 1; i < ALPHABET.length(); i++)
//            {
//                System.out.println(decrypt(readFromFile(path), i));
//            }
//
//         }

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
                input = input.toLowerCase();
                String result = "";
                for (int i = 0; i < input.length(); i++)
                {
                    if (ALPHABET.indexOf(input.charAt(i)) != -1)
                    {
                    int charIndex = ALPHABET.indexOf(input.charAt(i));
                    int newIndex = (charIndex + key) % ALPHABET.length();
                    char cipherChar = ALPHABET.charAt(newIndex);
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
            input = input.toLowerCase();
            String result = "";
            for (int i = 0; i < input.length(); i++)
            {
                if (ALPHABET.indexOf(input.charAt(i)) != -1)
                {
                    int charIndex = ALPHABET.indexOf(input.charAt(i));
                    int newIndex = (charIndex - key) % ALPHABET.length();
                    if (newIndex < 0)
                    {
                        newIndex = ALPHABET.length() + newIndex;
                    }
                    char plainChar = ALPHABET.charAt(newIndex);
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
