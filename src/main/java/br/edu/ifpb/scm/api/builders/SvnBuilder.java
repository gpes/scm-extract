/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.builders;

import br.edu.ifpb.scm.api.Builder;
import br.edu.ifpb.scm.api.factories.Repository;
import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.svn.Svn;
import java.io.IOException;
import java.text.ParseException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author <a href="https://github.com/jass2125" target="_blank">Anderson
 * Souza</a>
 * @since Apr 5, 2017 10:43:20 PM
 */
public class SvnBuilder implements Builder {

    private SCM scm;
    private String url;
    private String dir;

    public SvnBuilder(Svn scm) {
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
    public Repository build() throws GitAPIException, IOException, ParseException {
        return scm
                .setDir(dir)
                .setUrl(url)
                .buildRepository();
    }

    @Override
    public Builder toPath(String path) {
        this.dir = path;
        return this;
    }

}
