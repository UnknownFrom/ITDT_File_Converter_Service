# ITDT File Converter
## Описание
Программа обладает функциями перевода файла с расширением .json в файл с расширением .xml (и обратно), при этом изменяя структуру
содержимого
## Использование
На вход подаются пути к файлам (откуда считывать, куда записывать) с расширением json, xml, со строгой структурой, как в файлах com/pavel/data/data.json и com/pavel/data/data.xml
На выходе получается файл указаного в пути расширения.
## ПО для сборки и запуска
1) Maven
2) IntelliJ Idea
## Запуск
### Maven
1) Должен быть установлен Maven, чтобы была возможность пользоваться командами (для проверки введите в командной строке mvn -version). Если не установлен, то можно скачать с https://maven.apache.org/download.cgi и указать в переменных окружения (описание установки https://javarush.ru/groups/posts/2523-chastjh-4osnovih-maven)
2) Находясь в папке проекта использовать "mvn install"
3) Находясь в папке проекта использовать "java -jar .\target\ITDT_File_Converter_Service-1.0-SNAPSHOT.jar
### IntelliJ Idea
1) Зайти по пути src/main/java/com/pavel
2) Запустить Main.java
