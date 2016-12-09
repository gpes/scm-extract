package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.ScmBuilder;
import br.edu.ifpb.scm.api.ScmType;
import java.io.IOException;
import java.text.ParseException;
import java.util.ResourceBundle;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Anderson Souza
 */
public class CommitsInfoExample {

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {

        ResourceBundle banco = ResourceBundle.getBundle("scm");
        String url = banco.getString("url.repo");
        String dir = banco.getString("dir.local.jair");
        try {
            Repository repository = new ScmBuilder()
                    .dir(dir)
                    .url(url)
                    .create(ScmType.GIT).build();

            repository.getVersions().forEach(version -> {
                System.out.println("\n ---- Commits Info ---- ");
                System.out.println("Commit Date: " + version.getCommitDate());
                System.out.println("Commit Hashcode: " + version.getHashCode());
                System.out.println("Message:" + version.getMessage());
                System.out.println("\n");
            });

        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }
}
