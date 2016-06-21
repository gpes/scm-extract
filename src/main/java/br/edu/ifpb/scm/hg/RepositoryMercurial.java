/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.hg;

import br.edu.ifpb.scm.api.AdapterRepository;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class RepositoryMercurial extends AdapterRepository{

    @Override
    public AdapterRepository checkout(String commit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdapterRepository checkoutByCommit(String hash) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
