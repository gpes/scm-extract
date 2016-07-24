package br.edu.ifpb.scm.api.util;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/07/2016, 01:22:22
 */
public class FileHelper {

    private static final Logger logger = Logger.getLogger(FileHelper.class.getName());
    private String file;

    public FileHelper(String file) {
        this.file = file;
    }
    
    public void apagar(){
        apagar(new File(file));
    }
    private  void apagar(File file) {
        try {
            if (file.isDirectory()) {
                File[] entries = file.listFiles();
                for (File currentFile : entries) {
                    apagar(currentFile);
                }
                file.delete();
            } else {
                file.delete();
            }
        } catch (Throwable t) {
            logger.log(Level.SEVERE, "Não foi possível deletar o arquivo: " + file.getPath(), t);
        }
    }

}
