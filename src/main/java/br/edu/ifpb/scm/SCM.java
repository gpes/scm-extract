/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm;

import br.edu.ifpb.scm.api.Repository;
import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public interface SCM {

    /**
     * Método para clonar um repositório remoto Git
     *
     * @param url localização do repositório remoto
     * @param dir localização de onde deve ficar o repositório local
     * @return AdapterRepository
     * @throws org.eclipse.jgit.api.errors.GitAPIException
     * @throws java.io.IOException
     */
    public Repository clone(String url, File dir) throws GitAPIException, IOException;

    /**
     * Método para recuperar a referência para um repositório Git local
     *
     * @param dir localização do repositório local
     * @return Repository
     * @throws java.io.IOException
     */
    public Repository getRepository(File dir) throws IOException, GitAPIException;

    /**
     * Método para realizar o checkout do repositório
     *
     * @param commit
     * @return
     */
    public Repository checkout(String commit);

    /**
     * Método para realizar o checkout de um commit específico no repositório
     *
     * @param hash código hash do commit
     * @return AdapterRepository
     */
    public Repository checkoutByCommit(String hash);
}
