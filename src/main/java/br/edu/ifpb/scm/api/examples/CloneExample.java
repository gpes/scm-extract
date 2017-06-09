package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.Repository;
import java.io.IOException;
import java.text.ParseException;
import java.util.ResourceBundle;
import org.eclipse.jgit.api.errors.GitAPIException;
import br.edu.ifpb.scm.api.AbstractFactory;
import br.edu.ifpb.scm.api.factories.ScmGitFactory;

/**
 *
 * @author Anderson Souza
 */
public class CloneExample {

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
        ResourceBundle resource = ResourceBundle.getBundle("scm");
//        String url = resource.getString("url.repo");
//        String dir = resource.getString("dir.local.jair");

        String url = "https://github.com/google/gson";
        String dir = "/home/jairanderson/Desktop/TCC";

        AbstractFactory abs = new ScmGitFactory();

        SCM scm = abs.createScm();
        scm.setUrl(url);
        scm.setDir(dir);
        Repository repository = scm.buildRepository();

        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());

//        new FileHelper(dir).apagar();
    }
}
