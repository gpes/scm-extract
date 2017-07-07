/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.AbstractFactory;
import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.factories.ScmGitFactory;
import br.edu.ifpb.scm.api.util.FileHelper;
import java.util.ResourceBundle;

/**
 * @author Anderson Souza
 * @mail jair.anderson@academico.ifpb.edu.br
 */
public class CheckoutExample {

    public static void main(String[] args) {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("scm");
            String dir = System.getProperty("java.io.tmpdir") + resource.getString("dir");
            String url = resource.getString("url.repo");

            AbstractFactory factory = new ScmGitFactory();

            SCM scm = factory.createScm();
            scm.setDir(dir).setUrl(url);
            Repository repository = scm.checkout("0f6a9eeb760a892e253c7d7b3c14ea4418432d8f");

            repository.getVersions().forEach(version -> {
                System.out.println("\n ---- Commits Info ---- ");
                System.out.println("Commit Date: " + version.getCommitDate());
                System.out.println("Commit Hashcode: " + version.getHashCode());
                System.out.println("Message:" + version.getMessage());
                System.out.println("\n");
            });
            new FileHelper(dir).apagar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
