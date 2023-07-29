# Cryptanalyzer

Криптология, криптография и криптоанализ

Итоговый проект к модулю Java Syntax. Пишем криптоанализатор
Задача: написать программу, которая работает с шифром Цезаря.

За основу криптографического алфавита возьми все буквы русского алфавита и знаки пунктуации (. , ”” : - ! ? ПРОБЕЛ). 
Если попадаются символы, которые не входят в наш криптографический алфавит, просто пропусти их.

Обязательные требования
У программы должно быть 2 режима:

Шифрование / расшифровка. Программа должна зашифровывать и расшифровывать текст, используя заданный криптографический ключ.

Программа должна получать путь к текстовому файлу с исходным текстом и на его основе создавать файл с зашифрованным текстом.

Криптоанализ методом brute force

Программа должна взламывать зашифрованный текст, переданный в виде текстового файла.

Если пользователь выбирает brute force (брутфорс, поиск грубой силой), программа должна самостоятельно, путем перебора, подобрать ключ и расшифровать текст.

Подумай, какой критерий программа должна воспринимать как сигнал успешного подбора ключа. 
Возможно, нужно обратить внимание на пробелы между словами или правильность использования знаков пунктуации.

Дополнительные требования(опционально)
1. Криптоанализ методом статистического анализа

У пользователя программы должна быть возможность выбрать один из двух методов криптоанализа.
Если пользователь выбирает метод статистического анализа, ему нужно предложить загрузить еще один дополнительный файл с текстом, желательно— того же автора и той же стилистики. 
На основе загруженного файла программа должна составить статистику вхождения символов и после этого попытаться использовать полученную статистику для криптоанализа зашифрованного текста.

2. User Interface

Все диалоговые окна с пользователем делай на свое усмотрение. При желании можно использовать графические фреймворки Swing, JavaFX.

Готовое решение загрузи в публичный Git-репозиторий.


Текст для тестирования, который можно использовать:

За пару минут город темнел, будто зажмуривался, и на горизонте, нестерпимо яркая на серо-лиловом фоне, вставала, ветвясь, первая громадная молния.
Дома становились ниже, приседали на корточки, зажимали в счастливом ужасе уши.
И через секунду-другую громко, с хрустом разрывалось в небе сырое натянутое полотно.
