/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.Builder;
import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.ScmBuilder;
import br.edu.ifpb.scm.api.ScmType;
import br.edu.ifpb.scm.api.git.ChangedFiles;
import br.edu.ifpb.scm.api.git.Version;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;

/**
 *
 * @author Anderson Souza
 */
public class FilesChangedExamples {

    public static void main(String[] args) throws Exception {
        ResourceBundle resource = ResourceBundle.getBundle("scm");
        String dir = resource.getString("dir.local.jair");
        String url = resource.getString("url.repo");
        Builder builder = new ScmBuilder();
        Repository repository = builder
                .dir(dir)
                .url(url)
                .create(ScmType.GIT)
                .buildClone();

        repository.getVersions().forEach((Version version) -> {
            System.out.println("Data do Commit: " + version.getCommitDate());
            System.out.println("HashCode do Commit: " + version.getHashCode());
            System.out.println("Mensagem: " + version.getMessage());
            List<DiffEntry> diffs = version.getDiffs();
            DiffFormatter format = new DiffFormatter(System.out);
            format.setRepository(builder.getScm().getScmJGit());
            for (DiffEntry diffEntry : diffs) {
                try {
                    format.format(diffEntry);
                } catch (IOException ex) {
                    Logger.getLogger(FilesChangedExamples.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            .stream().forEach(ds -> {
//                System.out.println(ds.get);
//            });

            version.getChanges().forEach((ChangedFiles changedFile) -> {
                System.out.println("Tipo de mudança: " + changedFile.getChangedType());
                System.out.println("Nome antigo do arquivo: " + changedFile.getOldFileName());
                System.out.println("Nome novo do arquivo: " + changedFile.getNewFileName());
//                List<DiffEntry> diffs = changedFile.getDiffs();
//                DiffFormatter format = new DiffFormatter(System.out);
//                format.setRepository(builder.getScm().getScmJGit());
//                for (DiffEntry diffEntry : diffs) {
//                    try {
//                        format.format(diffEntry);
//                    } catch (IOException ex) {
//                        Logger.getLogger(FilesChangedExamples.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
            });
            System.out.println("\n");
        });

    }
}
