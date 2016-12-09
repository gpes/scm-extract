package br.edu.ifpb.scm.api;

//TODO: Usar apenas objetos de nossa API

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public interface SCM {

    /**
     * Realiza um clone de um repositório remoto de um controle de versão
     *
     * @return {@link Repository} Repositório
     */

    public Repository buildRepository();

    /**
     * Recupera a referência para um repositório local
     *
     * @return {@link Repository} Reposiório
     */

    /**
     * Realizar um checkout entre versões de um repositório
     *
     * @param commit HashCode do commit
     * @return {@link Repository} Repositório
     */
    public Repository checkout(String commit);

    /**
     * Método para realizar o checkout de um commit específico no repositório
     *
     * @param hash código hash do commit
     * @return AdapterRepository
     */
    @Deprecated
    public Repository checkoutByCommit(String hash);

    /**
     * Altera a url remota do repositório
     *
     * @param url Caminho do repositório remoto
     * @return Este objeto
     */
    public SCM setUrl(String url);

    /**
     * Altera o diretório local do repositório
     *
     * @param dir Caminho do diretório
     * @return Este objeto
     */
    public SCM setDir(String dir);

//    public SCM setScm(SCM scm);

//    public org.eclipse.jgit.lib.Repository getScmJGit();

}
