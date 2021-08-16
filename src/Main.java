import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    private static final String SEP = File.separator;
    private static final String SAVE_EXT = ".dat";
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        System.out.println("Домашнее задание к занятию 1.3\n" +
                "Потоки ввода-вывода. Работа с файлами. Сериализация\n" +
                "Задача 3: Загрузка\n");

        sb.append("\r\n");
        String savePath = "T:" + SEP + "Program Files" + SEP + "Games" + SEP + "savegames";
        String zipPath = savePath + SEP + "zipped.zip";
        String logPath = "T:" + SEP + "Program Files" + SEP + "Games" + SEP + "temp" + SEP + "temp.txt";
        String temp = "";
        LocalDateTime ldt = null;
        openZip(zipPath, savePath);
        File[] files = new File(savePath).listFiles();
        List<File> saveList = new ArrayList<>();
        for (File file : files) {
            if (file.getName().contains(SAVE_EXT)) {
                saveList.add(file);
                file.delete();
            }
        }
        GameProgress[] gps = new GameProgress[saveList.size()];
        for (int i = 0; i < saveList.size(); i++) {
            gps[i] = openProgress(saveList.get(i).getPath());
        }
        for (GameProgress gp : gps) {
            ldt = LocalDateTime.now();
            temp = "Объект " + gp + " прочитан";
            System.out.println(temp);
            sb
                    .append(ldt)
                    .append("\t")
                    .append(temp)
                    .append("\r\n");
        }
        log(sb.toString(), logPath);
    }

    private static void openZip(String inPath, String outPath) {
        if (new File(inPath).isFile()) {
            try(ZipInputStream zis = new ZipInputStream(new FileInputStream(inPath))) {
                ZipEntry entry;
                String name;
                String temp = "";
                LocalDateTime ldt = null;
                while ((entry = zis.getNextEntry()) != null) {
                    name = outPath + SEP + entry.getName();
                    FileOutputStream fos = new FileOutputStream(name);
                    for (int c = zis.read(); c != -1; c = zis.read())
                        fos.write(c);
                    fos.flush();
                    zis.closeEntry();
                    fos.close();
                    ldt = LocalDateTime.now();
                    temp = "Объект " + entry.getName() + " распакован";
                    System.out.println(temp);
                    sb
                            .append(ldt)
                            .append("\t")
                            .append(temp)
                            .append("\r\n");
                }
                byte[] buffer = new byte[zis.available()];
                zis.read(buffer);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static GameProgress openProgress(String path) {
        GameProgress gp = null;
        if (new File(path).isFile()) {
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
                gp = (GameProgress) ois.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return gp;
    }

    private static void log(String data, String path) {
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(data);
            System.out.println("Лог обновлен");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
