/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.loads;

import br.edu.ifpb.scm.api.AdapterRepository;
import br.edu.ifpb.scm.api.ScmFactory;
import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Anderson Souza
 */
public class Loader3 {

    public static String URL = "https://github.com/EndenhariaDeSoftware/scm-extract";
    public static File PATH = new File("C:/Users/Anderson Sousa/Documents/scm");

    public static void main(String[] args) throws GitAPIException, IOException {
        AdapterRepository repository = (AdapterRepository) ScmFactory
                .create("git")
                .clone(URL, PATH);
        System.out.println(repository.getLocalUrl() == null ? "Local Vazio" : repository.getLocalUrl());
        System.out.println(repository.getRemoteURL() == null ? "Remoto Vazio" : repository.getRemoteURL());
        System.out.println(repository.getVersions());
    
}
}
