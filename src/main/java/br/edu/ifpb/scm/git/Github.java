/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.git;

import br.edu.ifpb.scm.Repository;
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

    
    public Repository connectRepository(String jass2125, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
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
