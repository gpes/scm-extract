/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgit;

import java.io.File;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;

/**
 *
 * @author Anderson Souza
 */
public class Github implements IRepository {

    @Override
    public Ref checkoutBranch(Git repository, String branch) throws GitAPIException {
        return repository.checkout().setCreateBranch(true).setName(branch).
                setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).
                setStartPoint("origin/"+branch).call();
 
    }

    @Override
    public Ref checkout(Git git, String check) throws GitAPIException {
        return git.checkout().
                setCreateBranch(true).
                setName(check).
                setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).
                setStartPoint("origin/" + check).call();
    }

    @Override
    public Git clone(File directory, String url) throws GitAPIException {
        return Git.cloneRepository().setURI(url).setDirectory(directory).call();
    }
    
    @Override
    public IRepository connectRepository(String jass2125, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IRepository getRepository() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
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
