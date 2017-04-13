package br.edu.ifpb.scm.api;

import br.edu.ifpb.scm.api.factories.ScmGitFactory;
import br.edu.ifpb.scm.api.util.FileHelper;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ricardo Job
 */
public class RepositoryTest {

    private static final Logger logger = Logger.getLogger(RepositoryTest.class.getName());

    private ResourceBundle bundle = ResourceBundle.getBundle("scm");
    private final String dir = bundle.getString("dir.local.jair");
    private final String url = bundle.getString("url.repo");
    private AbstractFactory abs;

    @Test
    public void cloneRepositoryTest() {

        try {
            logger.log(Level.INFO, "iniciando teste do clone da URL");
            abs = new ScmGitFactory();
            SCM scm = abs.createScm();
            scm.setUrl(url).setDir(dir);
            Repository repository = scm.buildRepository();
            assertNotNull(repository);
            assertNotNull(repository.getPathLocal());
            assertNotNull(repository.getUrlRemote());
            assertNotNull(repository.getVersions());
            logger.log(Level.INFO, "local {0}, remote {1} e versions {2}",
                    new Object[]{repository.getPathLocal(), repository.getUrlRemote(), repository.getVersions().size()});
        } catch (Exception ex) {
            logger.log(Level.FINE, "Problemas na execução do teste clonarRepositorioDaURL", ex);
        }
    }

    @Test
    public void getReferenceRepositoryTest() {

        try {
            logger.log(Level.INFO, "iniciando teste do clone do File");
            abs = new ScmGitFactory();
            SCM scm = abs.createScm();
            scm.setUrl(url).setDir(dir);
            Repository repository = scm.buildRepository();
            assertNotNull(repository);
            assertNotNull(repository.getPathLocal());
            assertNotNull(repository.getUrlRemote());
            assertNotNull(repository.getVersions());
            logger.log(Level.INFO, "local {0}, remote {1} e versions {2}",
                    new Object[]{repository.getPathLocal(), repository.getUrlRemote(), repository.getVersions().size()});
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Problemas na execução do teste clonarRepositorioDoFile", ex);
        }
    }

    @After
    public void deleteTempFile() {
        new FileHelper(dir).apagar();
    }
}
