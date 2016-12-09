package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.Builder;
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
public class CloneExample {

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
        ResourceBundle resource = ResourceBundle.getBundle("scm");
        String url = resource.getString("url.repo");
        String dir = resource.getString("dir.local.jair");

        Builder builder = new ScmBuilder();

        Repository repository = builder
                .dir(dir)
                .url(url)
                .create(ScmType.GIT)
                .build();

        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());
    }

}
