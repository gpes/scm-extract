/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.git;

import br.edu.ifpb.scm.api.Repository;
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
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class Git implements SCM {

    Repository repo;

    @Override
    public Repository clone(String url, File dir) throws GitAPIException, IOException, ParseException {
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
//            repo = new Repository();
            repo = new Repository(dir.getCanonicalPath(), remote);
//            repo.setRemoteURL(remote);
//            repo.setLocalUrl(dir.getCanonicalPath());
//            List<Version> lista = this.getVersoes(git);
            repo.AddAllVersions(getVersoes(git));
            return repo;
        }
        return this.getRepository(dir);
    }

    @Override
    public Repository getRepository(File dir) throws IOException, GitAPIException, ParseException {
        org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.open(dir);
//        repo = new Repository();
        repo = new Repository(dir.getCanonicalPath(), "TODO: Recuperar url remote");
//        repo.setLocalUrl(dir.getCanonicalPath());
        repo.AddAllVersions(getVersoes(git));
        return repo;
    }

    public List<Version> getVersoes(org.eclipse.jgit.api.Git git) throws IOException, GitAPIException, ParseException {
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
                //System.out.println(count);

                walk.dispose();
            }
        }
        List<Version> lista = new ArrayList();
        LogCommand log = git.log();
        for (RevCommit it : log.call()) {
            String day = it.getCommitterIdent().getWhen().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dat = LocalDate.parse(day, formatter);

            lista.add(new Version(it.getCommitterIdent().getWhen().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), String.valueOf(it.getId()).substring(7, 47), it.getShortMessage()));
        }
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
}
