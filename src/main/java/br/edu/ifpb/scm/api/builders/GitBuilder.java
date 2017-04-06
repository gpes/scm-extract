/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.builders;

import br.edu.ifpb.scm.api.Builder;
import br.edu.ifpb.scm.api.factories.Repository;
import br.edu.ifpb.scm.api.SCM;
import java.io.IOException;
import java.text.ParseException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 * ScmFactory .url("url") .dir("") .create("git") .clone() .repository()
 * .checkout("123") Interface Fluent
 *
 * @author Anderson Souza
 */
public final class GitBuilder implements Builder {

    private SCM scm;
    private String url;
    private String dir;

    public GitBuilder(SCM scm) {
        this.scm = scm;
    }

    @Override
    public Builder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public Builder dir(String dir) {
        this.dir = dir;
        return this;
    }

    @Override
    public Builder toPath(String path) {
        this.dir = path;
        return this;
    }

    @Override
    public Repository build() throws GitAPIException, IOException, ParseException {
        return this.scm
                .setUrl(url)
                .setDir(dir)
                .buildRepository();
    }
}
