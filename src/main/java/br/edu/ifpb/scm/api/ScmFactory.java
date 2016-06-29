/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api;

import br.edu.ifpb.scm.SCM;
import br.edu.ifpb.scm.git.Git;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Anderson Souza
 */
public class ScmFactory {

//    public Git createGit() {
//        return new GitImpl();
//    }

    private static final Map<String, SCM> map = new HashMap() {
        {
            put("git", new Git());
            
        }
    };

    public static SCM create(String scm) {
        return map.get(scm);

    }
}
