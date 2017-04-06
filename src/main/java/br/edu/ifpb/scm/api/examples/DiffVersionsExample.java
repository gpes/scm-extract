package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.git.FilesChangedExamples;
import br.edu.ifpb.scm.api.factories.AbstractFactory;
import br.edu.ifpb.scm.api.Builder;
import br.edu.ifpb.scm.api.factories.FactoryProduces;
import br.edu.ifpb.scm.api.factories.Repository;
import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.enums.ScmType;
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

        AbstractFactory get = FactoryProduces.get(ScmType.GIT);
        Builder builder = get.createBuilder();
        SCM scm = get.createScm();
        Repository repository = builder
                .dir(dir)
                .url(url)
                .build();
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
