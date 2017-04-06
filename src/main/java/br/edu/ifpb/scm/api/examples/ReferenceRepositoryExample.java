/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.factories.AbstractFactory;
import br.edu.ifpb.scm.api.Builder;
import br.edu.ifpb.scm.api.factories.FactoryProduces;
import br.edu.ifpb.scm.api.factories.Repository;
import br.edu.ifpb.scm.api.enums.ScmType;
import java.io.IOException;
import java.text.ParseException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Anderson Souza
 */
public class ReferenceRepositoryExample {

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
        
        AbstractFactory abs = FactoryProduces.get(ScmType.GIT);
        Builder builder = abs.createBuilder();
        Repository repository = builder
                .dir("/home/jairanderson/NetBeansProjects/scm-extract")
                .build();

        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());
    }
}
