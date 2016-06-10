/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgit;

import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;

/**
 *
 * @author Anderson Souza
 */
public interface IRepository {

    public Git clone(File directory, String url) throws GitAPIException, CloneException, IOException;

    public Ref checkoutBranch(Git repository, String branch) throws GitAPIException;

    public IRepository connectRepository(String jass2125, String string);

    public Git getRepository(File directory) throws ReferenceException;

    public Ref checkout(Git git, String check) throws GitAPIException;

    public Ref checkoutByCommit(Git git, String check) throws GitAPIException;
}
