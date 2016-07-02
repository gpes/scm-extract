/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.project;

import org.eclipse.jgit.diff.DiffEntry;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class ChangedFiles {

    private DiffEntry files;

    public ChangedFiles(DiffEntry entry) {
        this.files = entry;
    }

    @Override
    public String toString() {
        return "ChangedFiles{" + "files=" + files.getNewPath() + '}';
    }

    public void setFiles(DiffEntry files) {
        this.files = files;
    }

    public DiffEntry getFiles() {
        return files;
    }

}
