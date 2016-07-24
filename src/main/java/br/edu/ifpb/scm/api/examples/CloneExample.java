package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.Builder;
import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.ScmBuilder;
import br.edu.ifpb.scm.api.ScmType;
import br.edu.ifpb.scm.api.git.Version;
import br.edu.ifpb.scm.api.util.FileHelper;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Anderson Souza
 */
public class CloneExample {

    private static final Logger logger = Logger.getLogger(CloneExample.class.getName());

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
        ResourceBundle banco = ResourceBundle.getBundle("scm");
        String url = banco.getString("url.repo");
        String dir = banco.getString("dir.local");
        
        Builder builder = new ScmBuilder();

        Repository repository = builder
                .dir(dir)
                .url(url)
                .create(ScmType.GIT)
                .buildClone();

        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());
        repository.getVersions().forEach(new Consumer<Version>() {
            @Override
            public void accept(Version v) {
                System.out.println("has: " + v.getHashCode());
            }
        });

        new FileHelper(dir).apagar();
//        deleteTempFile(new File(dir));
    }

//    private static void deleteTempFile(File file) {
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
