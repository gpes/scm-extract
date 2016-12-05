package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.ScmBuilder;
import br.edu.ifpb.scm.api.ScmType;
import br.edu.ifpb.scm.api.git.ChangedFiles;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/07/2016, 02:11:49
 */
public class DiffVersionsExample {

    public static void main(String[] args) throws Exception {
        ResourceBundle banco = ResourceBundle.getBundle("scm");
        String dir = banco.getString("dir.local");
        Repository repository = new ScmBuilder()
                .dir(dir)
                .create(ScmType.GIT)
                .buildRepository();
        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());
        repository.getVersions().forEach(v -> {
            v.getChanges().forEach(new Consumer<ChangedFiles>() {
                @Override
                public void accept(ChangedFiles file) {
//                        DiffEntry diff = file.getFileName();

//                        System.out.println("old = " + diff.getOldPath());
//                        System.out.println("new = " + diff.getNewPath());
//                        System.out.println("id = " + diff.getNewId());
//                        System.out.println("old mode = " + diff.getOldMode());
//                        System.out.println("new mode = " + diff.getNewMode());
//                        System.out.println("tostring = " + diff.toString());
                }
            });
        });
    }
}
