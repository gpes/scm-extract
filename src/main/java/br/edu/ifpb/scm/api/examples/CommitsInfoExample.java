package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.AbstractFactory;
import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.factories.ScmGitFactory;
import br.edu.ifpb.scm.api.util.FileHelper;
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

            AbstractFactory factory = new ScmGitFactory();
            SCM scm = factory.createScm();
            scm.setDir(dir).setUrl(url);
            Repository repository = scm.buildRepository();

            repository.getVersions().forEach(version -> {
                System.out.println("\n ---- Commits Info ---- ");
                System.out.println("Commit Date: " + version.getCommitDate());
                System.out.println("Commit Hashcode: " + version.getHashCode());
                System.out.println("Message:" + version.getMessage());
                System.out.println("Author: " + version.getAuthor().getName());
                System.out.println("Email: " + version.getAuthor().getEmail());
                System.out.println("\n");
            });

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        new FileHelper(dir).apagar();
    }
}
