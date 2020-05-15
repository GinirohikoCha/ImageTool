package com.github.ginirohikocha;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String path = null;

    public static void main(String[] args) {
        path = new File("").getAbsolutePath();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input DirName:");
        String dirName = scanner.next();
        System.out.println("Input ImagePrefix:");
        String imagePrefix = scanner.next();

        File newDir = new File(path+"/"+dirName);
        int index = 0;
        if (!newDir.exists()) {
            newDir.mkdir();
        } else {
            String[] fileNames = newDir.list();
            if (fileNames != null) {
                index = fileNames.length;
            }
        }

        List<File> images = getAllImageFile();
        for (int i=0; i<images.size(); i++) {
            try {
                Thumbnails.of(images.get(i))
                        .size(640, 480)
                        .outputFormat("jpg")
                        .toFile(new File(path+"/"+dirName+"/"+imagePrefix+"."+(i+index)+".jpg"));
                System.out.println("Success:"+imagePrefix+"."+(i+index)+".jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<File> getAllImageFile() {
        System.out.println("CurrentPath:"+path);

        List<File> images = new ArrayList<>();

        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files != null) {
            System.out.println("TotalFiles:"+files.length);
            for (File file:files) {
                if (file.getName().endsWith(".jpg")
                        || file.getName().endsWith(".png")
                        || file.getName().endsWith(".jpeg")) {
                    images.add(file);
                }
            }
        }
        System.out.println("TotalImages:"+images.size());
        return images;
    }
}
