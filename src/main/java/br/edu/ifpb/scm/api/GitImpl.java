/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api;

import java.io.File;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
class GitImpl implements Git {

    public GitImpl() {
    }

    @Override
    public Repository clone(String url, File path) {
//      Originalmente o método retorna um Git da API JGit            
//        if (!path.exists() && !path.isDirectory()) {
//            return org.eclipse.jgit.api.Git.cloneRepository().setURI(url).setDirectory(path).call();
//        }
//        return this.getRepository(path);
        return null;
    }

    @Override
    public Repository getRepository(File path) {
//      Originalmente o método retorna um Git da API JGit
//        return org.eclipse.jgit.api.Git.open(path);
        return null;
    }

}
