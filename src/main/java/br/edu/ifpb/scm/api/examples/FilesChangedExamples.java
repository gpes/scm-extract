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
import java.io.IOException;
import java.text.ParseException;
import java.util.ResourceBundle;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Anderson Souza
 */
public class FilesChangedExamples {

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
        ResourceBundle resource = ResourceBundle.getBundle("scm");
        String dir = resource.getString("dir.local.jair");
        Builder builder = new ScmBuilder();
        Repository repository = builder
                .dir(dir)
                .create(ScmType.GIT)
                .buildRepository();

        repository.getVersions().forEach(version -> {
            System.out.println("Data do Commit: " + version.getCommitDate());
            System.out.println("HashCode do Commit: " + version.getHashCode());
            System.out.println("Mensagem: " + version.getMessage());
            version.getChanges().forEach(changedFile -> {
                System.out.println("Tipo de mudança: " + changedFile.getChangedType());
                System.out.println("Nome do arquivo: " + changedFile.getFileName());
            });
            System.out.println("\n");
        });

    }
}
