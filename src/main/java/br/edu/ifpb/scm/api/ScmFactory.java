/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api;

import br.edu.ifpb.scm.SCM;
import br.edu.ifpb.scm.git.Git;
import br.edu.ifpb.scm.svn.Svn;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * ScmFactory
        .url("url")
        .dir("")
        .create("git")
        .clone()
        .repository()
        .checkout("123")
 * @author Anderson Souza
 */
public class ScmFactory {

    private static final Map<String, SCM> map = new HashMap() {
        {
            put("git", new Git());
            put("svn", new Svn());
            
        }
    };

    public static SCM create(String scm) {
        return map.get(scm);
    }
}
