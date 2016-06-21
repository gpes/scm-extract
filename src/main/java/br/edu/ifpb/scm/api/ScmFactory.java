/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api;

/**
 *
 * @author  Anderson Souza
 */
public class ScmFactory extends ScmAbstractFactory {

  @Override
    public Git createGit() {
        return new GitImpl();
    }

    @Override
    public Svn createSVN() {
        return new SvnImpl();
    }

    @Override
    public Mercurial createMercurial() {
        return new MercurialImpl();
    }

    
}
