/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fileName, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.git;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class ChangedFiles {

    private String oldFileName;
    private String newFileName;
    private String changedType;

    public ChangedFiles() {
    }

    public ChangedFiles(String oldFileName, String newFileName, String name) {
        this.oldFileName = oldFileName;
        this.newFileName = newFileName;
        this.changedType = name;
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

}
