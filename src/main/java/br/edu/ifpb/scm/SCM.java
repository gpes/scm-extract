package br.edu.ifpb.scm;

//TODO: Usar apenas objetos de nossa API
import java.io.IOException;
import java.text.ParseException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public interface SCM {

    /**
     * Método para clonar um repositório remoto Git
     *
     * @param url
     * @param dir
     * @return AdapterRepository
     * @throws org.eclipse.jgit.api.errors.GitAPIException
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public Repository cloneRepository() throws GitAPIException, IOException, ParseException;

    /**
     * Método para recuperar a referência para um repositório Git local
     *
     * @param dir localização do repositório local
     * @return Repository
     * @throws java.io.IOException
     * @throws org.eclipse.jgit.api.errors.GitAPIException
     * @throws java.text.ParseException
     */
    public Repository getRepository() throws IOException, GitAPIException, ParseException;

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

    public SCM setUrl(String url);

    public SCM setDir(String dir);

    public Repository get() throws IOException, GitAPIException, ParseException;
}
