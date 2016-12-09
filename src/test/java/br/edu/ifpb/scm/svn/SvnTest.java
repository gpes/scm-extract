package br.edu.ifpb.scm.svn;

import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.RepositoryTest;
import br.edu.ifpb.scm.api.ScmType;
import br.edu.ifpb.scm.api.ScmBuilder;
import br.edu.ifpb.scm.api.svn.Svn;
import br.edu.ifpb.scm.api.util.FileHelper;
import java.io.File;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
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
            Svn instance = new Svn(new File(dir), url);
            Repository expResult = null;
            Repository result = new ScmBuilder().url(url).dir(dir).create(ScmType.SVN).build();
            assertEquals(expResult, result);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, "Problemas na execução do teste clonar SVN: ", ex);
        }
    }

//    @After
    public void deleteTempFile() {
        new FileHelper(dir).apagar();
    }

}
