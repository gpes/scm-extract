/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.git;

import br.edu.ifpb.scm.Repository;
import br.edu.ifpb.scm.SCM;
import br.edu.ifpb.scm.api.AdapterRepository;
import br.edu.ifpb.scm.project.Version;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class Git implements SCM {

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
            List<Version> lista = this.getVersoes(git);
            repo.setVersions(lista);
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

    public List<Version> getVersoes(org.eclipse.jgit.api.Git git) throws IOException, GitAPIException {
        try (org.eclipse.jgit.lib.Repository repository = git.getRepository()) {
            Ref head = repository.exactRef("refs/heads/master");

            // a RevWalk allows to walk over commits based on some filtering that is defined
            try (RevWalk walk = new RevWalk(repository)) {
                RevCommit commit = walk.parseCommit(head.getObjectId());
                //System.out.println("Start-Commit: " + commit);

                //System.out.println("Walking all commits starting at HEAD");
                walk.markStart(commit);
                int count = 0;
                for (RevCommit rev : walk) {
                    //System.out.println("Commit: " + rev);
                    count++;
                }
                System.out.println(count);

                walk.dispose();
            }
        }
        List<Version> lista = new ArrayList();
        LogCommand log = git.log();
        log.call().forEach(t -> {
//                Date commitDate, String hashCode, String message
            lista.add(new Version(t.getCommitterIdent().getWhen(), null, t.getShortMessage()));
        });
        return lista;
    }
}
