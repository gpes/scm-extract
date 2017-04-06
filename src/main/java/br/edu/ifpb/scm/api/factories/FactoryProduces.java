/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.factories;

import br.edu.ifpb.scm.api.enums.ScmType;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author <a href="https://github.com/jass2125" target="_blank">Anderson
 * Souza</a>
 * @since Apr 5, 2017 9:48:13 PM
 */
public class FactoryProduces {

    private static Map<ScmType, AbstractFactory> params = new HashMap<ScmType, AbstractFactory>() {
        {
            put(ScmType.GIT, new GitFactory());
            put(ScmType.SVN, new SvnFactory());
        }
    };

    public static AbstractFactory get(ScmType scm) {
        return params.get(scm);
    }

}
