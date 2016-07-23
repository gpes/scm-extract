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
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Anderson Souza
 */
public class VersionsExample {

    static String DIR = "C:/Users/Anderson Sousa/Desktop/gpes/clone-example";

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
        Builder builder = new ScmBuilder();
        
        Repository repository = builder
                .dir(DIR)
                .create(ScmType.GIT)
                .buildRepository();

        repository.getVersions().forEach(version -> {
            System.out.println("\n ---- Informações sobre os Commits ---- ");
            System.out.println("Data do Commit: " + version.getCommitDate());
            System.out.println("HashCode do Commit: " + version.getHashCode());
            System.out.println("Mensagem:" + version.getMessage());
            System.out.println("\n");
        });

    }
}
