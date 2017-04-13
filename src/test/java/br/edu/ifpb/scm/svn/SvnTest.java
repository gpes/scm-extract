package br.edu.ifpb.scm.svn;

import br.edu.ifpb.scm.api.AbstractFactory;
import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.RepositoryTest;
import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.factories.ScmSvnFactory;
import br.edu.ifpb.scm.api.util.FileHelper;
import java.io.File;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Ricardo Job
 */
public class SvnTest {

    private static final Logger logger = Logger.getLogger(RepositoryTest.class.getName());
    private ResourceBundle bundle = ResourceBundle.getBundle("scm");
    private final String url = bundle.getString("url.svn");
    private final String dir = bundle.getString("dir.local.jair");

    @Test
    public void testClone() {

        try {
            logger.log(Level.INFO, "iniciando teste do clone do File");
            File path = null;
            
            AbstractFactory abs = new ScmSvnFactory();
            SCM scm = abs.createScm();
            scm.setDir(dir).setUrl(url);
            Repository result = scm.buildRepository();
            Assert.assertNull(result);
            Repository expResult = null;
            Assert.assertEquals(expResult, result);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, "Problemas na execução do teste clonar SVN: ", ex);
        }
    }

    @After
    public void deleteTempFile() {
        new FileHelper(dir).apagar();
    }

}
