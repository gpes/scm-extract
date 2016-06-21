/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.svn;

import br.edu.ifpb.scm.Svn;
import br.edu.ifpb.scm.api.AdapterRepository;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class SvnImpl implements Svn {

    public SvnImpl() {
    }

    @Override
    public AdapterRepository clone(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdapterRepository getRepository(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
