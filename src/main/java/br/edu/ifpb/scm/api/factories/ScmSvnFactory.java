/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.factories;

import br.edu.ifpb.scm.api.AbstractFactory;
import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.svn.Svn;

/**
 *
 * @author <a href="https://github.com/jass2125" target="_blank">Anderson
 * Souza</a>
 * @since Apr 12, 2017 11:49:40 PM
 */
public class ScmSvnFactory implements AbstractFactory {

    public ScmSvnFactory() {
    }

    @Override
    public SCM createScm() {
        return new Svn();
    }

}
