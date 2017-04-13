/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.AbstractFactory;
import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.factories.ScmGitFactory;
import java.io.IOException;
import java.text.ParseException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Anderson Souza
 */
public class ReferenceRepositoryExample {

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {

        AbstractFactory abs = new ScmGitFactory();
        SCM scm = abs.createScm();
        scm.setDir("/home/jairanderson/NetBeansProjects/scm-extract");
        Repository repository = scm.buildRepository();

        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());
    }
}
