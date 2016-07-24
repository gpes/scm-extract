package br.edu.ifpb.scm.api;

import br.edu.ifpb.scm.api.util.FileHelper;
import java.io.File;
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
//    private final String url = "https://github.com/EndenhariaDeSoftware/scm-extract";
//    private final File origin = new File("/Users/job/Documents/dev/data/origin");
//    private final File dest = new File("/Users/job/Documents/dev/data/scm");
//    private final File EJ = new File("/home/des02/Desktop/scm");

    ResourceBundle bundle = ResourceBundle.getBundle("scm");
    private Builder builder;

    @Test
    public void clonarRepositorioDaUrl() {
        String url = bundle.getString("url.repo");
        String dir = bundle.getString("dir.local");
        try {
            logger.log(Level.INFO, "iniciando teste do clone da URL");
            builder = new ScmBuilder();

            Repository repository = builder.url(url).dir(dir).create(ScmType.GIT).buildClone();
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
    public void clonarRepositorioDoFile() {
        String url = bundle.getString("url.repo");
        String dir = bundle.getString("dir.local");

        try {
            logger.log(Level.INFO, "iniciando teste do clone do File");
            Repository repository = new ScmBuilder().url(url).dir(dir).create(ScmType.GIT).buildClone();
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
//        deleteTempFile(dest);
        String dir = bundle.getString("dir.local");
        new FileHelper(dir).apagar();
    }

//    private void deleteTempFile(File file) {
//        try {
//            if (file.isDirectory()) {
//                File[] entries = file.listFiles();
//                for (File currentFile : entries) {
//                    deleteTempFile(currentFile);
//                }
//                file.delete();
//            } else {
//                file.delete();
//            }
//        } catch (Throwable t) {
//            logger.log(Level.SEVERE, "Não foi possível deletar o arquivo: " + file.getPath(), t);
//        }
//    }
}
