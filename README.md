### Домашнее задание к занятию 1.3 Потоки ввода-вывода. Работа с файлами. Сериализация

# Задача 3: Загрузка (со звездочкой *)

## Описание
В данной задаче необходимо произвести обратную операцию по разархивации архива и десериализации файла сохраненной игры в Java класс.

Таким образом, для выполнения задания потребуется проделать следующие шаги:
1. Произвести распаковку архива в папке `savegames`.
2. Произвести считывание и десериализацию одного из разархивированных файлов `save.dat`.
3. Вывести в консоль состояние сохранненой игры.

## Реализация
Реализуйте метод `openZip()`, который принимал бы два аргумента: путь к файлу типа `String` (например, "/Users/admin/Games/GunRunner/savegames/zip.zip") и путь к папке, куда стоит разархивировать файлы типа `String` (например, "/Users/admin/Games/GunRunner/savegames"). Для распаковки Вам потребуются такие стримовые классы как `FileInputStream`, `ZipInputStream` и класс объекта архива `ZipEntry`. Считывание элементов аврхива производится с помощью метода `getNextEntry()` класса `ZipInputStream`, а узнать название объекта `ZipEntry` можно с помощью метода `getName()`. Запись в файл распакованных объектов можно произвести с помощью `FileOutputStream`.

Далее реализуйте метод `openProgress()`, который бы в качестве аргумента принимал путь к файлу с сохраненной игрой типа `String` (например, "/Users/admin/Games/GunRunner/savegames/save2.dat") и возвращал объект типа `GameProgress`. В данном методе Вам потребуются классы `FileInputStream` и `ObjectInputStream`. С помощью метода класса `ObjectInputStream` `readObject()` можно десериализовать объект, а далее привести (скастить) его к `GameProgress`.

Так как в классе `GameProgress` метод `toString()` уже переопределен, поэтому достаточно вывести полученный объект в консоль.