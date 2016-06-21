/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.hg;

import br.edu.ifpb.scm.Mercurial;
import br.edu.ifpb.scm.api.AdapterRepository;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class MercurialImpl implements Mercurial {

    public MercurialImpl() {
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
