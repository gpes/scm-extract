/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.factories;

import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.builders.SvnBuilder;
import br.edu.ifpb.scm.api.Builder;
import br.edu.ifpb.scm.api.svn.Svn;
import java.io.File;
import java.util.ResourceBundle;

/**
 *
 * @author <a href="https://github.com/jass2125" target="_blank">Anderson
 * Souza</a>
 * @since Apr 5, 2017 10:40:37 PM
 */
public class SvnFactory implements AbstractFactory {

    private ResourceBundle bundle = ResourceBundle.getBundle("scm");
    private final String url = bundle.getString("url.svn");
    private final String dir = bundle.getString("dir.local.jair");
    private Svn svn = new Svn(new File(dir), url);
    
    @Override
    public SCM createScm() {
//        return new Svn(svn);
        return null;
    }

    @Override
    public Builder createBuilder() {
        return new SvnBuilder(svn);
    }

}
