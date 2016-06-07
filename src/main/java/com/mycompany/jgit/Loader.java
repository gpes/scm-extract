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

    public static final File DIRECTORY = new File("/home/jairanderson/Área de Trabalho/Folder");
    public static final String REMOTE_URL = "https://github.com/ricardojob/SwissArmyJavaGit";
    public static final String PARAM = "822f0c75641731ab61863bc540b8cfe06f909c5f";
    
    public static void main(String[] args) {
        
//        Git git = Git.cloneRepository().setURI("https://github.com/jass2125/Academia").setDirectory(new File("/home/jairanderson/Área de Trabalho/JGit")).call();
        AbstractFactory factory = new Factory();
        try {
            Git repository = factory.createGithub().clone(DIRECTORY, REMOTE_URL);
            IRepository repo = new Github();
            Ref ref = repo.checkoutByCommit(repository, PARAM);
            System.out.println(ref.getName());
        } catch (GitAPIException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
