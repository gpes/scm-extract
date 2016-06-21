/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public interface Mercurial {
    
    public Repository clone(String url);

    public Repository getRepository(String url);
}
