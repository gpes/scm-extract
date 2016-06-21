/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api;

import br.edu.ifpb.scm.project.Version;
import br.edu.ifpb.scm.Repository;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author  Anderson Souza
 */
public abstract class AdapterRepository implements Repository {

    private String remoteURL;
    private String localUrl;
    private List<Version> versions;

    public AdapterRepository() {
    }
    
    public AdapterRepository(String remote_url, String local_url, List<Version> versions) {
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
    
}
