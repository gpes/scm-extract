/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgit;

import java.io.File;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
/**
 *
 * @author Anderson Souza
 */
public interface IRepository {

    public Git clone(File directory, String url) throws GitAPIException;

    public IRepository checkoutBranch(String branch);

    public IRepository connectRepository(String jass2125, String string);
    
    public IRepository getRepository();

}
