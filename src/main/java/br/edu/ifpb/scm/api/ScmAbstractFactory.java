/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api;

import br.edu.ifpb.scm.git.Git;
import br.edu.ifpb.scm.hg.Mercurial;

/**
 *
 * @author Anderson Souza
 */
public abstract class ScmAbstractFactory {

    public abstract Git createGit();

    
    public abstract Mercurial createMercurial();

}
