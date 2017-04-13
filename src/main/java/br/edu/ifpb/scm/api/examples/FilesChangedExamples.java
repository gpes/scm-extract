/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.AbstractFactory;
import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.factories.ScmGitFactory;
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

        AbstractFactory abs = new ScmGitFactory();
        SCM scm = abs.createScm();
        scm.setDir(dir).setUrl(url);
        Repository repository = scm.buildRepository();

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
