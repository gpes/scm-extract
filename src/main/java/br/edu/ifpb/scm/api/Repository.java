/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api;

import br.edu.ifpb.scm.SCM;
import br.edu.ifpb.scm.project.Version;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author  Anderson Souza
 */
public class Repository{

    private String remoteURL;
    private String localUrl;
    private List<Version> versions;
    private SCM scm;
    
    public Repository() {
    }
    
    public Repository(String remote_url, String local_url, List<Version> versions) {
        this.remoteURL = remote_url;
        this.localUrl = local_url;
        this.versions = versions;
    }
    
    public String getRemoteURL() {
        return remoteURL;
    }

    public void setRemoteURL(String remoteURL) {
        this.remoteURL = remoteURL;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public List<Version> getVersions() {
        return Collections.unmodifiableList(versions);
    }

    public SCM Scm() {
        return scm;
    }

}
