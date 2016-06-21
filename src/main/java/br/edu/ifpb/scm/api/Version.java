/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api;

import java.util.Date;
import java.util.List;
import org.eclipse.jgit.diff.DiffEntry;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class Version {
    private Date commitDate;
    private String hashCode;
    private String message;
    private List<ChangedFiles> changes;

    public Version() {
    }

    public Version(Date commitDate, String hashCode, String message) {
        this.commitDate = commitDate;
        this.hashCode = hashCode;
        this.message = message;
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
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
