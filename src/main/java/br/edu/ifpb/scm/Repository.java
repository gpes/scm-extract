/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm;

/**
 *
 * @author Ricardo Job
 */
public interface Repository {

    /**
     * Método para realizar o checkout do repositório
     *
     * @param commit
     * @return
     */
    public abstract Repository checkout(String commit);

    /**
     * Método para realizar o checkout de um commit específico no repositório
     *
     * @param hash código hash do commit
     * @return AdapterRepository
     */
    public abstract Repository checkoutByCommit(String hash);

}
