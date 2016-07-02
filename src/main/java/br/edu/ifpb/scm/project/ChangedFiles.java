/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.project;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class ChangedFiles {

    private DiffEntry files;
    private ChangeType name;

    public ChangedFiles() {
    }

    public ChangedFiles(DiffEntry entry, ChangeType name) {
        this.files = entry;
        this.name = name;
    }

    public ChangeType getName() {
        return name;
    }

    public void setName(ChangeType name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ChangedFiles{" + "files=" + files + ", name=" + name.name() + '}' + "\n";
    }

    

    public void setFiles(DiffEntry files) {
        this.files = files;
    }

    public DiffEntry getFiles() {
        return files;
    }

}
