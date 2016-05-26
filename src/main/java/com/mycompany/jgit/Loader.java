/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgit;

/**
 *
 * @author Anderson Souza
 */
public class Loader {

    public static String directory = "/home/jairanderson/Área de Trabalho/JGit";

    public static void main(String[] args) {
//        Git git = Git.cloneRepository().setURI("https://github.com/jass2125/Academia").setDirectory(new File("/home/jairanderson/Área de Trabalho/JGit")).call();
        AbstractFactory factory = new Factory();
        IRepository repository = factory.createGithub().clone("url");
        
        
    }
}
