/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class Version {

    private LocalDate commitDate;
    private String hashCode;
    private String message;
    private List<ChangedFiles> changes;

    public Version() {
        changes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Version{" + "commitDate=" + commitDate + ", hashCode=" + hashCode + ", message=" + message + '}' + "\n";
    }

    public Version(LocalDate commitDate, String hashCode, String message) {
        this.commitDate = commitDate;
        this.hashCode = hashCode;
        this.message = message;
    }

    public Version setChanges(List<ChangedFiles> changes) {
        this.changes = changes;
        return this;
    }

    public LocalDate getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(LocalDate commitDate) {
        this.commitDate = commitDate;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ChangedFiles> getChanges() {
        return changes;
    }

}
