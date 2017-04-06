/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.factories;

import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.Builder;

/**
 *
 * @author <a href="https://github.com/jass2125" target="_blank">Anderson
 * Souza</a>
 * @since Apr 5, 2017 9:43:58 PM
 */
public interface AbstractFactory {

    public SCM createScm();

    public Builder createBuilder();
}
