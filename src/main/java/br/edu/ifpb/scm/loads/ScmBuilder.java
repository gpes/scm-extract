/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.loads;

import br.edu.ifpb.scm.Repository;
import br.edu.ifpb.scm.SCM;
import br.edu.ifpb.scm.api.ScmType;
import java.io.IOException;
import java.text.ParseException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 * ScmFactory .url("url") .dir("") .create("git") .clone() .repository()
 * .checkout("123") Interface Fluent
 *
 * @author Anderson Souza
 */
public class ScmBuilder {

    private SCM scm;
    private String url;
    private String dir;

    public ScmBuilder url(String url) {
        this.url = url;
        return this;
    }

    public ScmBuilder dir(String dir) {
        this.dir = dir;
        return this;
    }

    public ScmBuilder create(ScmType type) {
        this.scm = type.get();
        return this;
    }
    
    public Repository buildClone() throws GitAPIException, IOException, ParseException {
        return this.scm
                .setUrl(url)
                .setDir(dir)
                .get();
    }
    
    //dá pra usar apenas um metodo na classe Git, que caso seja apenas pra pegar a referencia e ele passa a url null
    public Repository buildRepository() throws GitAPIException, IOException, ParseException {
        return this.scm
                .setDir(dir)
                .get();
    }
}
