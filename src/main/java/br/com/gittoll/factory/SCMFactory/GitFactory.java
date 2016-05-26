/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gittoll.factory.SCMFactory;

/**
 *
 * @author jairanderson
 */
public class GitFactory extends Factory{

    @Override
    public Git createGitClone() {
        return new GitClone();
    }

    @Override
    public Svn createSvn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
