/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.svn;

import br.edu.ifpb.scm.api.svn.Svn;
import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.RepositoryTest;
import br.edu.ifpb.scm.api.ScmType;
import br.edu.ifpb.scm.api.ScmBuilder;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ricardo Job
 */
public class SvnTest {

    private static final Logger logger = Logger.getLogger(RepositoryTest.class.getName());
    private final String url = "http://svn.code.sf.net/p/xmlunit/code/trunk";
    private final File origin = new File("/Users/job/Documents/dev/data/origin");
    private final File dest = new File("/Users/job/Documents/dev/data/scm");
    private final File EJ = new File("/home/jairanderson/Área de Trabalho/svn");

    @Test
    public void testClone() {
        try {
            logger.log(Level.INFO, "iniciando teste do clone do File");
            String url = "";
            File path = null;
            Svn instance = new Svn(EJ, url);
            Repository expResult = null;
            Repository result = new ScmBuilder().url(this.url).dir(EJ.getCanonicalPath()).create(ScmType.SVN).buildClone();
            assertEquals(expResult, result);
        } catch (Exception ex) {
            ex.printStackTrace();
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
