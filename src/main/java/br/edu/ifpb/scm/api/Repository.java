package br.edu.ifpb.scm.api;

import br.edu.ifpb.scm.SCM;
import br.edu.ifpb.scm.project.Version;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Anderson Souza
 */
public class Repository {

    private String remoteURL;
    private String localUrl;
    private List<Version> versions;
    private SCM scm;

//    public Repository() {
//    }

//    public Repository(String remote_url, String local_url, List<Version> versions) {
    public Repository(String pathLocal, String urlRemote) {
        this.remoteURL = urlRemote;
        this.localUrl = pathLocal;
    }

    public void AddAllVersions(List<Version> versions) {
        this.versions = versions;
    }

    public String urlRemote() {
        return remoteURL;
    }

//    public void setRemoteURL(String remoteURL) {
//        this.remoteURL = remoteURL;
//    }
    public String pathLocal() {
        return localUrl;
    }

//    public void setLocalUrl(String localUrl) {
//        this.localUrl = localUrl;
//    }
    public List<Version> getVersions() {
        return Collections.unmodifiableList(versions);
    }

    public SCM scm() {
        return scm;
    }

}
