/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.scm.api.examples;

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
    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
        Repository repository = new ScmBuilder()
                .dir("C:/Users/Anderson Sousa/Desktop/Apostilas/scm")
                .url("https://github.com/EndenhariaDeSoftware/scm-extract")
                .create(ScmType.GIT).buildRepository();

        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());

    }

}
