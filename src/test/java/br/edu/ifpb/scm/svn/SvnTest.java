/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.svn;

import br.edu.ifpb.scm.Repository;
import br.edu.ifpb.scm.api.RepositoryTest;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricardo Job
 */
public class SvnTest {

    private static final Logger logger = Logger.getLogger(RepositoryTest.class.getName());
    private final String url = "https://github.com/EndenhariaDeSoftware/scm-extract";
    private final File origin = new File("/Users/job/Documents/dev/data/origin");
    private final File dest = new File("/Users/job/Documents/dev/data/scm");

    //@Test
    public void testClone() {
        try {
            logger.log(Level.INFO, "iniciando teste do clone do File");
            String url = "";
            File path = null;
            Svn instance = new Svn(dest, url);
            Repository expResult = null;
            Repository result = instance.clone(url, path);
            assertEquals(expResult, result);
        } catch (Exception ex) {
             logger.log(Level.SEVERE, "Problemas na execução do teste clonar SVN: ", ex);
        }
    }

    @After
    public void deleteTempFile() {
        deleteTempFile(dest);
    }

    private void deleteTempFile(File file) {
        try {
            if (file.isDirectory()) {
                File[] entries = file.listFiles();
                for (File currentFile : entries) {
                    deleteTempFile(currentFile);
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
