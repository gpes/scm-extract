package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.ScmBuilder;
import br.edu.ifpb.scm.api.ScmType;
import br.edu.ifpb.scm.api.git.Version;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/07/2016, 02:11:49
 */
public class DiffVersionsExample {

    public static void main(String[] args) throws Exception {
        ResourceBundle banco = ResourceBundle.getBundle("scm");
        String dir = banco.getString("dir.local.jair");
        Repository repository = new ScmBuilder()
                .dir(dir)
                .create(ScmType.GIT)
                .buildRepository();
        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());

        List<Version> versions = repository.getVersions();

        versions.stream().forEach(v -> {
            v.getChanges().stream().forEach(c -> {
                System.out.println(c.getChangedType());
//                System.out.println(c.getFileName());
//                c.getChanges().stream().forEach(s -> {
//                    System.out.println(s.);
                });
            });
//        });
    }
}
