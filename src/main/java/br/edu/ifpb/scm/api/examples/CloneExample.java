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
public class CloneExample {

    static String DIR = "C:/Users/Anderson Sousa/Desktop/gpes/clone-example";
    static String URL = "https://github.com/gpes/scm-extract";

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
        Builder builder = new ScmBuilder();
        
        Repository repository = builder
                .dir(DIR)
                .url(URL)
                .create(ScmType.GIT)
                .buildClone();

        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());

    }

}
