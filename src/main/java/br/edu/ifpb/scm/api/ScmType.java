/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api;

import br.edu.ifpb.scm.SCM;
import br.edu.ifpb.scm.git.Git;
import br.edu.ifpb.scm.hg.Mercurial;
import br.edu.ifpb.scm.svn.Svn;

/**
 *
 * @author Anderson Souza
 */
public enum ScmType {
    GIT {
        @Override
        public SCM getScmType() {
            return new Git();
        }
    },
    MERCURIAL {
        @Override
        public SCM getScmType() {
            return new Mercurial();
        }
    },
    SVN {
        @Override
        public SCM getScmType() {
            return new Svn();
        }
    };

    public abstract SCM getScmType();

}
