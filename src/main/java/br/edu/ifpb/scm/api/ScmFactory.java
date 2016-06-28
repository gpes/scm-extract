/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api;

import br.edu.ifpb.scm.SCM;
import br.edu.ifpb.scm.git.GitImpl;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Anderson Souza
 */
public class ScmFactory {

    private static final Map<String, SCM> map = new HashMap() {
        {
            put("git", new GitImpl());
        }
    };

    public static SCM create(String scm) {
        return map.get(scm);

    }
}
