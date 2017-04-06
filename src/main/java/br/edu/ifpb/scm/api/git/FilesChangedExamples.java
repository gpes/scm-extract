/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.git;

import br.edu.ifpb.scm.api.factories.AbstractFactory;
import br.edu.ifpb.scm.api.Builder;
import br.edu.ifpb.scm.api.factories.FactoryProduces;
import br.edu.ifpb.scm.api.factories.Repository;
import br.edu.ifpb.scm.api.enums.ScmType;
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
        
        AbstractFactory abs = FactoryProduces.get(ScmType.GIT);
        Builder builder = abs.createBuilder();
        Repository repository = builder
                .dir(dir)
                .url(url)
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
