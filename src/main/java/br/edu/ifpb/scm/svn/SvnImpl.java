/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.svn;

import br.edu.ifpb.scm.Repository;
import br.edu.ifpb.scm.Svn;
import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class SvnImpl implements Svn {

    @Override
    public Repository clone(String url, File path) throws GitAPIException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Repository getRepository(File dir) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
