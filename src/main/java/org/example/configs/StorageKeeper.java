package org.example.configs;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class StorageKeeper {
    private static final List<File> fileList = Arrays.asList(new File("src/main/resources/music").listFiles());

    public static boolean isInStorage(String fileName){
        boolean res = false;
        for (int i = 0; i < fileList.size(); i++) {
            String curFileName = fileList.get(i).getName();
            if (curFileName.equals(fileName)){
                res = true;
                break;
            }
        }
        return res;
    }

    public static String showAllAvailableTracks(){
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < fileList.size(); i++) {
            String tmp = fileList.get(i).getName().split("/")[0];

            String realFileName = "\n ---> " + tmp.substring(0, tmp.length()-4);

            res.append(realFileName);
        }

        return res.toString();
    }
}
