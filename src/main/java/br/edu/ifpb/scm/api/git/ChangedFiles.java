/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fileName, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.git;

import java.util.List;
import org.eclipse.jgit.diff.DiffEntry;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class ChangedFiles {

    private String fileName;
    private String changedType;
    private List<DiffEntry> changes;

    public ChangedFiles() {
    }

    public ChangedFiles(String fileName, String changedType) {
        this.fileName = fileName;
        this.changedType = changedType;
    }

    @Override
    public String toString() {
        return "ChangedFiles{" + "fileName=" + fileName + ", changedType=" + changedType + '}' + "\n";
    }

    public ChangedFiles(String fileName, String changedType, List<DiffEntry> changes) {
        this.fileName = fileName;
        this.changedType = changedType;
        this.changes = changes;
    }

    public List<DiffEntry> getChanges() {
        return changes;
    }

    public void setChanges(List<DiffEntry> changes) {
        this.changes = changes;
    }

    public String getChangedType() {
        return changedType;
    }

    public void setChangedType(String changedType) {
        this.changedType = changedType;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
