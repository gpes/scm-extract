/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.git;

import br.edu.ifpb.scm.api.AdapterRepository;
import br.edu.ifpb.scm.api.CloneException;
import br.edu.ifpb.scm.api.ReferenceException;
import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;

/**
 *
 * @author  Anderson Souza
 */
@Deprecated
public class Github{

    
    public Ref checkoutBranch(Git repository, String branch) throws GitAPIException {
        return repository.checkout().setCreateBranch(true).setName(branch).
                setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).
                setStartPoint("origin/" + branch).call();

    }

    
    public Ref checkout(Git git, String check) throws GitAPIException {
        return git.checkout().
                setCreateBranch(true).
                setName(check).
                setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).
                setStartPoint("origin/" + check).call();
    }

    /**
     * Realiza um clone de um reposiório remoto
     *
     * @param directory Localização para o repositório local
     * @param url Localização para o repositorio remoto
     * @return Git Objeto Git
     * @throws GitAPIException Exceção do API do JGIT
     * @throws CloneException Exceção lançada quando a pasta não possui
     * permissão de escrita
     * @throws IOException Exceção lançada quando o diretório não é um
     * repositório git local
     */
    
    public Git clone(File directory, String url) throws GitAPIException, CloneException, IOException {
        try {
            if (!directory.exists() && !directory.isDirectory()) {
                return Git.cloneRepository().setURI(url).setDirectory(directory).call();
            }
            return this.getRepository(directory);
        } catch (SecurityException e) {
            throw new CloneException("fatal: permissão de pasta", e);
        } catch (ReferenceException e) {
            throw new IOException("fatal: Not a repository git.");
        }
    }

    
    public AdapterRepository connectRepository(String jass2125, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Recupera um repositorio local
     *
     * @param directory Localização para o repositório local
     * @return Git Objeto Git
     * @throws ReferenceException Exceção lançada quando o diretório local não é
     * um repositorio git
     */
    
    public Git getRepository(File directory) throws ReferenceException {
        try {
            return Git.open(directory);
        } catch (IOException e) {
            throw new ReferenceException(e);           

        }
    }

    
    public Ref checkoutByCommit(Git git, String check) throws GitAPIException {
//        git.checkout().setName(check).
//                setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).
//                setStartPoint("origin/" + check).
//                call();

        return git.checkout().setName(check).setName("master").call();
//        git.checkout().setCreateBranch(true).setName("new-branch").setStartPoint(check).call();
//                git.checkout().
//                setCreateBranch(true).
//                setName("master").
//                setStartPoint(check).
//                call();
    }

}
