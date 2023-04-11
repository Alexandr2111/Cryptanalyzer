package ru.ivanov;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try
        {
        System.out.println("Добро пожаловать в программу для шифрования/расшифровки данных методом шифра Цезаря!" +
                "\nВыберите режим работы программы:");
        System.out.println("1 - Шифрование/расшифровка (с помощью криптографического ключа)\n" +
                "2 - Криптоанализ методом brute force (поиск грубой силой)");
        int choice = scanner.nextInt();

        if (choice == 1)
        {
            System.out.println("Для шифрования введите - 1, для расшифровки - 2.");
            int choice1 = scanner.nextInt();
            if (choice1 == 1)
            {
                System.out.println("Введите путь к текстовому файлу:");
                Scanner scanner1 = new Scanner(System.in);
                String path = scanner1.nextLine();
                if (Files.notExists(Path.of(path)))
                {
                    System.out.println("Введенные данные не являются путем к текстовому файлу" +
                                    "или файл не найден, перезапустите программу.");
                    System.exit(0);
                }
                System.out.println("Введите ключ:");
                int key = scanner1.nextInt();
                CaesarCipher.encryptFromFile(path, key);
                System.out.println("Файл с зашифрованным текстом расположен в той же директории.");

            }
            else if (choice1 == 2)
            {
                System.out.println("Введите путь к текстовому файлу:");
                Scanner scanner2 = new Scanner(System.in);
                String path = scanner2.nextLine();
                if (Files.notExists(Path.of(path)))
                {
                    System.out.println("Введенные данные не являются путем к текстовому файлу" +
                            "или файл не найден, перезапустите программу.");
                    System.exit(0);
                }
                System.out.println("Введите ключ:");
                int key = scanner2.nextInt();
                CaesarCipher.decryptFromFile(path, key);
                System.out.println("Файл с расшифрованным текстом расположен в той же директории.");
            }
            else
            {
                System.out.println("Введите 1 или 2");
            }
        }
        else if (choice == 2)
        {
            System.out.println("Введите путь к текстовому файлу:");
            Scanner scanner3 = new Scanner(System.in);
            String path = scanner3.nextLine();
            if (Files.notExists(Path.of(path)))
            {
                System.out.println("Введенные данные не являются путем к текстовому файлу" +
                        "или файл не найден, перезапустите программу.");
                System.exit(0);
            }
            CaesarCipher.decryptBruteForce(path);
            System.out.println("Файл с расшифрованным текстом расположен в той же директории.");
        }
        else
        {
            System.out.println("Введите 1 или 2");
        }
      }
        catch (InputMismatchException e)
      {
            System.out.println("Некорректные данные. Перезапустите программу.");
      }
    }
}

