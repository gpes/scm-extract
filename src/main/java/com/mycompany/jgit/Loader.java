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
/**
 *
 * @author Anderson Souza
 */
public class Loader {

    public static final File DIRECTORY = new File("C:/Users/Pris/Desktop/Teste");
    public static final String REMOTE_URL = "https://github.com/prisgouveia/prisgouveia.github.io";
    
    public static void main(String[] args) {
        
//        Git git = Git.cloneRepository().setURI("https://github.com/jass2125/Academia").setDirectory(new File("/home/jairanderson/Área de Trabalho/JGit")).call();
        AbstractFactory factory = new Factory();
        try {
            Git repository = factory.createGithub().clone(DIRECTORY, REMOTE_URL);
        } catch (GitAPIException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
