/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.hg;

import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.Repository;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class Mercurial implements SCM {

    @Override
    public Repository buildRepository() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Repository checkout(String commit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Repository checkoutByCommit(String hash) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SCM setUrl(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SCM setDir(String dir) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public org.eclipse.jgit.lib.Repository getScmJGit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
