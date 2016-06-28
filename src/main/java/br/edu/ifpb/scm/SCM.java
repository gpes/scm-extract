/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm;

import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public interface SCM {

    public Repository clone(String url, File path) throws GitAPIException, IOException;

    public Repository getRepository(File dir) throws IOException;
}
