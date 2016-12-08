/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api;

import java.io.IOException;
import java.text.ParseException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Anderson Souza
 */
public interface Builder {

    /**
     * Altera a url remota do repositório
     *
     * @param url Url local
     * @return ScmBuilder Este objeto
     */
    public Builder url(String url);

    /**
     * Altera o diretório local do repositório
     *
     * @param dir Caminho do diretório
     * @return Este objeto
     */
    public Builder dir(String dir);

    /**
     * Cria a instância de um sistema de controle de versão
     *
     * @param scm Tipo de controle de versão
     * @return Este objeto
     */
    public Builder create(ScmType scm);

    /**
     * Contrói uma instância de um objeto {@link Repository}
     * @return {@link Repository} Repositório
     * @throws GitAPIException
     * @throws IOException
     * @throws ParseException
     */
    public Repository buildClone() throws GitAPIException, IOException, ParseException;

    /**
     * Recupera a instância de um repositório já existente
     * @return {@link Repository} Repositório
     * @throws org.eclipse.jgit.api.errors.GitAPIException
     * @throws IOException
     * @throws ParseException
     */
    public Repository buildRepository() throws GitAPIException, IOException, ParseException;

    public SCM getScm();
}
