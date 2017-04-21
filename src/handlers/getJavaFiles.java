package handlers;

import java.io.File;
import java.util.List;

/**
 * Created by swthrhs on 24/10/2016.
 */
public class getJavaFiles {
    public List<String> getAllJavaFiles(String path, List<String> fileList) {
        try {
            File filesFromPath = new File(path);
            File[] filesToSearch = filesFromPath.listFiles();
            if (path.isEmpty()) {//an to path einai empty
                return fileList;
            } else {//an to path den einai empty
                for (int i = 0; i < filesToSearch.length; i++) {
                    if (filesToSearch[i].isDirectory()) {//elenxo an einai fakelos
                        getAllJavaFiles(filesToSearch[i].getPath(), fileList);
                    } else if (filesToSearch[i].isFile()) {//elenxo an einai arxeio
                        String filename = filesToSearch[i].getName();//an einai arxeio pernw to olo to name
                        String[] names = filename.split("\\.");//kanw split gia na dw ti katallhksh exei to arxeio
                        if (names.length > 1) {//elenxo an exei katalhksh
                            if (names[1].endsWith("class")) {//an einai class arxeio to bazw sth lista
                                fileList.add(filesToSearch[i].getAbsolutePath());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        if (fileList.isEmpty()) {
            return fileList;
        } else {
            return fileList;
        }
    }
}