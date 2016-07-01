/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.git;

import br.edu.ifpb.scm.Repository;
import br.edu.ifpb.scm.SCM;
import br.edu.ifpb.scm.project.Version;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class Git implements SCM {

    private Repository repo;

    @Override
    public Repository clone(String url, File dir) throws GitAPIException, IOException, ParseException {
        if (!dir.exists() && !dir.isDirectory()) {
            org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.cloneRepository().setURI(url).setDirectory(dir).call();
            org.eclipse.jgit.lib.Repository repository = git.getRepository();
            repo = createRepository(dir, repository);
            repo.AddAllVersions(versions(git));
            return repo;
        }
        return this.getRepository(dir);
    }

    @Override
    public Repository getRepository(File dir) throws IOException, GitAPIException, ParseException {
        org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.open(dir);
//        repo = new Repository(dir.getCanonicalPath(), getUrlFromLocalRepository(git.getRepository()));
        repo = createRepository(dir, git.getRepository());
        repo.AddAllVersions(versions(git));
        return repo;
    }

    private List<Version> versions(org.eclipse.jgit.api.Git git) throws IOException, GitAPIException, ParseException {
        List<Version> lista = new ArrayList();
        LogCommand log = git.log();
//        for (RevCommit it : log.call()) {
//            lista.add(createVersion(it));
//        }
        log.call().forEach(rc -> lista.add(createVersion(rc)));
        return lista;
    }

    @Override
    public Repository checkout(String commit) {
//        Git git; - Git.class da API JGit / Originalmente o método retorna um Ref.class da mesma API 
//        return git.checkout().
//                setCreateBranch(true).
//                setName(check).
//                setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).
//                setStartPoint("origin/" + check).call();
        return null;
    }

    @Override
    public Repository checkoutByCommit(String hash) {

//        Git git; - Git.class da api JGit  / Originalmente o método retorna um Ref.class da mesma API 
//         git.checkout().setName(check).
//                setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).
//                setStartPoint("origin/" + check).
//                call();
//        return git.checkout().setName(check).setName("master").call();
//        git.checkout().setCreateBranch(true).setName("new-branch").setStartPoint(check).call();
//                git.checkout().
//                setCreateBranch(true).
//                setName("master").
//                setStartPoint(check).
//                call();
        return null;
    }

    private String getUrlFromLocalRepository(org.eclipse.jgit.lib.Repository repository) {
        Config config = repository.getConfig();
        return config.getString("remote", "origin", "url");
    }

    private static Version createVersion(RevCommit it) {
        return new Version(it.getCommitterIdent().getWhen().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), String.valueOf(it.getId()).substring(7, 47), it.getShortMessage());
    }

    private Repository createRepository(File dir, org.eclipse.jgit.lib.Repository repository) throws IOException {
        return new Repository(dir.getCanonicalPath(), getUrlFromLocalRepository(repository));
    }
}
