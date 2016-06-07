/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgit;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
/**
 *
 * @author Anderson Souza
 */
public class Loader {

    public static final File DIRECTORY = new File("/Users/job/Documents/dev/testes/exemplo-jcabi/dataTeste/v2");
    public static final String REMOTE_URL = "https://github.com/prisgouveia/prisgouveia.github.io";
    public static final String HASH = "5f465908eb48fa83c9b9aa15db1ced7f7d1d09c2";
    
    public static void main(String[] args) {
        
        AbstractFactory factory = new Factory();
        try {
//            Git repository = factory.createGithub().clone(DIRECTORY, "/Users/job/Documents/dev/testes/exemplo-jcabi/dataTeste/v1");
            Git repository = factory.createGithub().clone(DIRECTORY, REMOTE_URL);
            Ref call = repository.checkout().setName(HASH).call();
            
            
            
        } catch (GitAPIException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
