/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.git;

import br.edu.ifpb.scm.Git;
import br.edu.ifpb.scm.Repository;
import br.edu.ifpb.scm.api.AdapterRepository;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Config;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class GitImpl implements Git {

    private AdapterRepository repo;

    @Override
    public Repository clone(String url, File dir) throws GitAPIException, IOException {
        if (!dir.exists() && !dir.isDirectory()) {
            org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.cloneRepository().setURI(url).setDirectory(dir).call();
            //pegar os dados
            org.eclipse.jgit.lib.Repository repository = git.getRepository();
            Config config = repository.getConfig();
            Set<String> rems = config.getSubsections("remote");
            String remote = null;
            if (rems.size() <= 1) {
                for (String name : rems) {
                    String rem = config.getString("remote", name, "url");
                    remote = rem;
                }
            }
            repo = new RepositoryGit();
            repo.setRemoteURL(remote);
            return repo;
        }
        return this.getRepository(dir);
    }

    @Override
    public Repository getRepository(File dir) throws IOException {
        org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.open(dir);
        repo = new RepositoryGit();
        repo.setLocalUrl(dir.getCanonicalPath());
        return repo;
    }

}
