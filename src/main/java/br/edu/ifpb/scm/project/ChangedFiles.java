/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fileName, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.project;

import org.eclipse.jgit.diff.DiffEntry;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class ChangedFiles {

    private DiffEntry fileName;
    private String changedType;

    public ChangedFiles() {
    }

    @Override
    public String toString() {
        return "ChangedFiles{" + "fileName=" + fileName + ", changedType=" + changedType + '}' + "\n";
    }

    public ChangedFiles(DiffEntry fileName, String changedType) {
        this.fileName = fileName;
        this.changedType = changedType;
    }

    public String getChangedType() {
        return changedType;
    }

    public void setChangedType(String changedType) {
        this.changedType = changedType;
    }

    public void setFileName(DiffEntry fileName) {
        this.fileName = fileName;
    }

    public DiffEntry getFileName() {
        return fileName;
    }

}
