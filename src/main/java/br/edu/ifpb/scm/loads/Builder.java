/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.loads;

import br.edu.ifpb.scm.SCM;

/**
 *
 * @author Anderson Souza
 */
public interface Builder {

    public Object url(String url);

    public Object dir(String url);

    public Object create(String url);

    public Object clone(String url);

    public SCM repository(String url);
}
