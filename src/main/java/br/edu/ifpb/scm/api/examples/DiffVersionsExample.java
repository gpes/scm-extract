package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.ScmBuilder;
import br.edu.ifpb.scm.api.ScmType;
import br.edu.ifpb.scm.api.git.ChangedFiles;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.util.io.DisabledOutputStream;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/07/2016, 02:11:49
 */
public class DiffVersionsExample {

    public static void main(String[] args) {
        try {
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
                        DiffEntry diff = file.getFileName();

//                        System.out.println("old = " + diff.getOldPath());
//                        System.out.println("new = " + diff.getNewPath());
//                        System.out.println("id = " + diff.getNewId());
//                        System.out.println("old mode = " + diff.getOldMode());
//                        System.out.println("new mode = " + diff.getNewMode());
//                        System.out.println("tostring = " + diff.toString());

                    }
                });
            });

        } catch (GitAPIException ex) {
            Logger.getLogger(DiffVersionsExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DiffVersionsExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DiffVersionsExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
