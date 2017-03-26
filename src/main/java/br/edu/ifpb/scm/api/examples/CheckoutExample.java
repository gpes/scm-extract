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
 * @author Anderson Souza
 * @mail jair.anderson@academico.ifpb.edu.br
 */
public class CheckoutExample {

    public static void main(String[] args) {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("scm");
            String url = resource.getString("url.repo");
            String dir = resource.getString("dir.local.jair");

            Builder builder = new ScmBuilder();

            Repository repository = builder
                    .url(url)
                    .toPath("/home/jairanderson/Desktop/Untitled Folder/teste")
                    .create(ScmType.GIT)
                    .build();

            repository = builder.checkout("2dc0f7ff60b007e3cbd033b0ba65978b85518812");
            
            repository.getVersions().forEach(version -> {
                System.out.println("\n ---- Commits Info ---- ");
                System.out.println("Commit Date: " + version.getCommitDate());
                System.out.println("Commit Hashcode: " + version.getHashCode());
                System.out.println("Message:" + version.getMessage());
                System.out.println("\n");
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
