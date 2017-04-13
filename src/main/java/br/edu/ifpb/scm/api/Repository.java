package br.edu.ifpb.scm.api;

import br.edu.ifpb.scm.api.git.Version;
import java.util.Collections;
import java.util.List;

/**
 * //job: a ideia é termos imutabilidade. Outra url, outro repositório. Talvez
 * usarmos <http://immutables.github.io/>, depois faço um teste...
 *
 * @author Anderson Souza
 */
public class Repository {

    private String remoteURL;
    private String localUrl;
    private List<Version> versions;
    private SCM scm;

    public Repository() {
    }

    public Repository(String pathLocal, String urlRemote) {
        this.remoteURL = urlRemote;
        this.localUrl = pathLocal;
    }

    public void AddAllVersions(List<Version> versions) {
        this.versions = versions;
    }

    public String getUrlRemote() {
        return remoteURL;
    }

    public String getPathLocal() {
        return localUrl;
    }

    public List<Version> getVersions() {
        return Collections.unmodifiableList(versions);
    }

    public SCM scm() {
        return scm;
    }

    public void setScm(SCM scm) {
        this.scm = scm;
    }

}
