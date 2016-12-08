/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fileName, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.git;

import java.util.Collections;
import java.util.List;
import org.eclipse.jgit.diff.DiffEntry;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class ChangedFiles {

    private String oldFileName;
    private String newFileName;
    private String changedType;
    private List<DiffEntry> changes;
    private List<DiffEntry> diffs = Collections.EMPTY_LIST;

    public ChangedFiles() {
    }

    public ChangedFiles(String oldFileName, String newFileName, String name, List<DiffEntry> diffs) {
        this.oldFileName = oldFileName;
        this.newFileName = newFileName;
        this.changedType = name;
        this.diffs = diffs;
    }
    public ChangedFiles(String oldFileName, String newFileName, String name) {
        this.oldFileName = oldFileName;
        this.newFileName = newFileName;
        this.changedType = name;
        this.diffs = diffs;
    }

    public ChangedFiles(String oldFileName, String newFileName, String changedType, List<DiffEntry> changes, List<DiffEntry> diffs) {
        this.oldFileName = oldFileName;
//        , StringBuffer fileDiffs
        this.newFileName = newFileName;
        this.changedType = changedType;
        this.changes = changes;
        this.diffs = diffs;
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

    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public List<DiffEntry> getDiffs() {
        return diffs;
    }

    public void setDiffs(List<DiffEntry> diffs) {
        this.diffs = diffs;
    }

}
