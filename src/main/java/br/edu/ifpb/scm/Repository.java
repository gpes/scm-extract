package br.edu.ifpb.scm;

import br.edu.ifpb.scm.project.Version;
import java.util.Collections;
import java.util.List;

/**
 * //job: a ideia é termos imutabilidade. Outra url, outro repositório.
 * Talvez usarmos <http://immutables.github.io/>, depois faço um teste...
 * @author Anderson Souza
 */
public class Repository {

    private String remoteURL;
    private String localUrl;
    private List<Version> versions;
    private SCM scm;

    
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

    public String pathLocal() {
        return localUrl;
    }

    public List<Version> versions() {
        return Collections.unmodifiableList(versions);
    }

    public SCM scm() {
        return scm;
    }

}
