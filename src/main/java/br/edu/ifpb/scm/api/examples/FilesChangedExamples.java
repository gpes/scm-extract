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
import java.util.ResourceBundle;

/**
 *
 * @author Anderson Souza
 */
public class FilesChangedExamples {

    public static void main(String[] args) throws Exception {
        ResourceBundle banco = ResourceBundle.getBundle("scm");
        String dir = banco.getString("dir.local.jair");
        String url = banco.getString("url.repo");
        Builder builder = new ScmBuilder();
        Repository repository = builder
                .dir(dir)
                .url(url)
                .create(ScmType.GIT)
                .build();

        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());

        repository.getVersions().forEach(v -> {
            System.out.println("Commit Date: " + v.getCommitDate());
            System.out.println("Commit HashCode: " + v.getHashCode());
            System.out.println("Message: " + v.getMessage());
            v.getDiffs().stream().forEach(d -> {
                System.out.println("Change Type: " + d.getChangeType());
                System.out.println("Old File Name: " + d.getOldPath());
                System.out.println("New File Name: " + d.getNewPath());
            });

        });
    }
}
