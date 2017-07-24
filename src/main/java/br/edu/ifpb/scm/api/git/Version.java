/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.git;

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
    private List<ChangedFiles> changes = new ArrayList<>();
    private StringBuilder diffs = new StringBuilder();
    private Author author;

    public Version() {
    }

    public Version(LocalDate commitDate, String hashCode, String message, List<ChangedFiles> changedFiles, StringBuilder diffs, Author author) {
        this.commitDate = commitDate;
        this.hashCode = hashCode;
        this.message = message;
        this.changes = changedFiles;
        this.diffs = diffs;
        this.author = author;
    }

    public List<ChangedFiles> getChanges() {
        return changes;
    }
//
//    public Version setChanges(List<ChangedFiles> changes) {
//        this.changes = changes;
//        return this;
//    }

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

    public void setDiffs(StringBuilder diffs) {
        this.diffs = diffs;
    }

    public StringBuilder getDiffs() {
        return diffs;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Version{" + "commitDate=" + commitDate + ", hashCode=" + hashCode + ", message=" + message + ", diffs=" + diffs + '}';
    }
}
