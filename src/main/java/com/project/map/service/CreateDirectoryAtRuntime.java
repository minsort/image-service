package com.project.map.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CreateDirectoryAtRuntime {

    public static String createDirectoryForResult(byte[] result) {
        String folderName = "result";

        // Получение абсолютного пути к текущей директории (корневой папке проекта)
        String currentDir = new File("").getAbsolutePath();

        // Создание новой папки в текущей директории

        File newFolder = new File(currentDir);
        String newFolderPath = newFolder.getParent();
        String finalPath = newFolderPath + File.separator + folderName;

        File finalFolder = new File(finalPath);

        if (!finalFolder.exists()) {
            if (finalFolder.mkdir()) {
                System.out.println("Папка успешно создана: " + finalPath);
            } else {
                System.out.println("Не удалось создать папку: " + finalPath);
            }
        } else {
            System.out.println("Папка уже существует: " + finalPath);
        }
        return "Файл сохранен:  " + SavePngFile.saveFinalFile(result, finalPath);
    }

}
