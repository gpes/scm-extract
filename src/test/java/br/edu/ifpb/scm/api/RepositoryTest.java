package br.edu.ifpb.scm.api;

import br.edu.ifpb.scm.Repository;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;

/**
 *
 * @author Ricardo Job
 */
public class RepositoryTest {

    private static final Logger logger = Logger.getLogger(RepositoryTest.class.getName());
    private final String url = "https://github.com/EndenhariaDeSoftware/scm-extract";
    private final File origin = new File("/Users/job/Documents/dev/data/origin");
    private final File dest = new File("/Users/job/Documents/dev/data/scm");

    //@Test
    public void clonarRepositorioDaUrl() {

//        try {
            logger.log(Level.INFO, "iniciando teste do clone da URL");
            
//            Repository repository = new br.edu.ifpb.scm.loads.ScmBuilder().url(url).dir(dest.getCanonicalPath()).create(ScmType.GIT).cloneRepository(); 
//            assertNotNull(repository);
//            assertNotNull(repository.pathLocal());
//            assertNotNull(repository.urlRemote());
//            assertNotNull(repository.versions());
//            logger.log(Level.INFO, "local {0}, remote {1} e versions {2}",
//                    new Object[]{repository.pathLocal(), repository.urlRemote(), repository.versions().size()});
//        } catch (Exception ex) {
//            logger.log(Level.FINE, "Problemas na execução do teste clonarRepositorioDaURL", ex);
//        }
    }

    //@Test
    public void clonarRepositorioDoFile() {
//        try {
//            logger.log(Level.INFO, "iniciando teste do clone do File");
//            Repository repository = new br.edu.ifpb.scm.loads.ScmBuilder().url(url).dir(dest.getCanonicalPath()).create(ScmType.GIT).cloneRepository(); 
//            assertNotNull(repository);
//            assertNotNull(repository.pathLocal());
//            assertNotNull(repository.urlRemote());
//            assertNotNull(repository.versions());
//            logger.log(Level.INFO, "local {0}, remote {1} e versions {2}",
//                    new Object[]{repository.pathLocal(), repository.urlRemote(), repository.versions().size()});
//        } catch (Exception ex) {
//            logger.log(Level.SEVERE, "Problemas na execução do teste clonarRepositorioDoFile", ex);
//        }
    }

    //@After
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
