/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.loads;

import br.edu.ifpb.scm.Repository;
import br.edu.ifpb.scm.SCM;
import br.edu.ifpb.scm.api.ScmType;
import br.edu.ifpb.scm.git.Git;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 * ScmFactory .url("url") .dir("") .create("git") .clone() .repository()
 * .checkout("123")
 *
 * @author Anderson Souza
 */
public class ScmBuilder {

    private SCM scm;
    private String url;
    private File dir;

    public ScmBuilder url(String url) {
        this.url = url;
        return this;
    }

    public ScmBuilder dir(File dir) {
        this.dir = dir;
        return this;
    }

    public Repository build() {
        return null;
    }

    public ScmBuilder create(ScmType type) {
        this.scm = type.get();
        return this;
    }

    public Repository clon() throws GitAPIException, IOException, ParseException{
        return new Git(url, dir).clon();
    }

    public Repository repository() throws GitAPIException, IOException, ParseException {
        return new Git(url, dir).clon();
    }

}
