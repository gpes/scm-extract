package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.AbstractFactory;
import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.factories.ScmGitFactory;
import br.edu.ifpb.scm.api.git.Version;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/07/2016, 02:11:49
 */
public class DiffVersionsExample {

    public static void main(String[] args) throws Exception {
        ResourceBundle banco = ResourceBundle.getBundle("scm");
        String dir = banco.getString("dir.local.jair");
        String url = banco.getString("url.repo");

        AbstractFactory factory = new ScmGitFactory();
        SCM scm = factory.createScm();
        scm.setDir(dir).setUrl(url);
        Repository repository = scm.buildRepository();
        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());

        repository.getVersions().forEach((Version version) -> {
            System.out.println("Commit Date: " + version.getCommitDate());
            System.out.println("Commit Hashcode: " + version.getHashCode());
            System.out.println("Message: " + version.getMessage());
            List<DiffEntry> diffs = version.getDiffs();
            DiffFormatter format = new DiffFormatter(System.out);
            format.setRepository(scm.getRepository());
            for (DiffEntry diffEntry : diffs) {
                try {
                    format.format(diffEntry);
                } catch (IOException ex) {
                    Logger.getLogger(FilesChangedExamples.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
