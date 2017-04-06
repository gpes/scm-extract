/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.factories;

import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.Builder;
import br.edu.ifpb.scm.api.git.Git;
import br.edu.ifpb.scm.api.builders.GitBuilder;

/**
 *
 * @author <a href="https://github.com/jass2125" target="_blank">Anderson
 * Souza</a>
 * @since Apr 5, 2017 9:45:10 PM
 */
public class GitFactory implements AbstractFactory {

    private Git git = new Git();

    @Override
    public SCM createScm() {
        return git;
    }

    @Override
    public Builder createBuilder() {
        return new GitBuilder(git);
    }

}
