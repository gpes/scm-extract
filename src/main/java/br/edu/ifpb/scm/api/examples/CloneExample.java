package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.Builder;
import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.ScmBuilder;
import br.edu.ifpb.scm.api.ScmType;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;

/**
 *
 * @author Anderson Souza
 */
public class CloneExample {

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
        ResourceBundle banco = ResourceBundle.getBundle("scm");
        String url = banco.getString("url.repo");
        String dir = banco.getString("dir.local.jair");

        Builder builder = new ScmBuilder();

        Repository repository = builder
                .dir(dir)
                .url(url)
                .create(ScmType.GIT)
                .build();

        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());
        repository.getVersions().stream().forEach(v -> {
            System.out.println(v.getCommitDate());
            System.out.println(v.getCommitDate());
            System.out.println(v.getHashCode());
            System.out.println(v.getMessage());

            
//            v.getDiffs().stream().forEach(c -> {
//                System.out.println(c.getChangedType());
//                System.out.println(c.getOldFileName());
//                System.out.println(c.getNewFileName());
////                System.out.println(c.get);
//            });
            
            List<DiffEntry> diffs = v.getDiffs();
            DiffFormatter format = new DiffFormatter(System.out);
            format.setRepository(builder.getScm().getScmJGit());
            for (DiffEntry diff : diffs) {
                try {
                    format.format(diff);
                } catch (IOException ex) {
                    Logger.getLogger(CloneExample.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
//        new FileHelper(dir).apagar(); 
    }

}
