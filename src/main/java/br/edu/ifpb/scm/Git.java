/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public interface Git extends SCM {

    /**
     * Método para clonar um repositório remoto Git
     *
     * @param url localização do repositório remoto
     * @param path localização de onde deve ficar o repositório local
     * @return AdapterRepository
     * @throws org.eclipse.jgit.api.errors.GitAPIException
     */

    /**
     * Método para recuperar a referência para um repositório Git local
     * @param path localização do repositório local
     * @return AdapterRepository
     */
}
