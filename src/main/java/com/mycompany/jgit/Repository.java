/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgit;

import java.util.List;

/**
 *
 * @author Anderson Souza
 */
public class Repository {
    private String remote_url;
    private String local_url;
    List<Version> versions;

    public Repository() {
    }

    public Repository(String remote_url, String local_url, List<Version> versions) {
        this.remote_url = remote_url;
        this.local_url = local_url;
        this.versions = versions;
    }

    public String getRemote_url() {
        return remote_url;
    }

    public void setRemote_url(String remote_url) {
        this.remote_url = remote_url;
    }

    public String getLocal_url() {
        return local_url;
    }

    public void setLocal_url(String local_url) {
        this.local_url = local_url;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }
}
