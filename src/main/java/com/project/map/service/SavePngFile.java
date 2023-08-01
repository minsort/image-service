package com.project.map.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class SavePngFile {

    public static String saveFinalFile(byte[] byteArray, String outputDirectory) {
        String finalPath = "";
        try {
            // Путь к файлу, в который нужно сохранить массив байт
            String outputFilePath = outputDirectory + "/file.png";
            finalPath = outputFilePath;

            // Создаем директорию, если она не существует
            File directory = new File(outputDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Создаем объект File для файла назначения
            File outputFile = new File(outputFilePath);

            // Записываем данные массива байт в файл
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                fos.write(byteArray);
            }

            System.out.println("Файл успешно сохранен в " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalPath;
    }

}
