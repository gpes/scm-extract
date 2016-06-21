/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm;

import br.edu.ifpb.scm.api.AdapterRepository;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public interface Mercurial {
    
    public AdapterRepository clone(String url);

    public AdapterRepository getRepository(String url);
}
