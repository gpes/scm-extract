package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.Builder;
import br.edu.ifpb.scm.api.factories.FactoryProduces;
import br.edu.ifpb.scm.api.factories.Repository;
import br.edu.ifpb.scm.api.enums.ScmType;
import java.io.IOException;
import java.text.ParseException;
import java.util.ResourceBundle;
import org.eclipse.jgit.api.errors.GitAPIException;
import br.edu.ifpb.scm.api.factories.AbstractFactory;

/**
 *
 * @author Anderson Souza
 */
public class CloneExample {

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
        ResourceBundle resource = ResourceBundle.getBundle("scm");
        String url = resource.getString("url.repo");
        String dir = resource.getString("dir.local.jair");
        
        AbstractFactory get = FactoryProduces.get(ScmType.GIT);
        Builder builder = get.createBuilder();
        Repository repository = builder
                .dir(dir)
                .url(url)
                .build();

        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());
    }

}
