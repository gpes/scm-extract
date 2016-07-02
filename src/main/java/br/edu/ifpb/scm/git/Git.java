/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.git;

import br.edu.ifpb.scm.Repository;
import br.edu.ifpb.scm.SCM;
import br.edu.ifpb.scm.project.ChangedFiles;
import br.edu.ifpb.scm.project.Version;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

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
            findChangedFiles(git);
            return repo;
        }
        return this.getRepository(dir);
    }

    private void findChangedFiles(org.eclipse.jgit.api.Git git) throws IOException, GitAPIException {
        for (Version v : repo.getVersions()) {
            List<ChangedFiles> changedFiles = getChangedFilesFromSpecifiedVersion(git.getRepository(), v.getHashCode());
            v.setChanges(changedFiles);
        }
    }

    @Override
    public Repository getRepository(File dir) throws IOException, GitAPIException, ParseException {
        org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.open(dir);
//        repo = new Repository(dir.getCanonicalPath(), getUrlFromLocalRepository(git.getRepository()));
        repo = createRepository(dir, git.getRepository());
        repo.AddAllVersions(versions(git));
        findChangedFiles(git);
        return repo;
    }

    private List<Version> versions(org.eclipse.jgit.api.Git git) throws IOException, GitAPIException, ParseException {
        List<Version> lista = new ArrayList();
        LogCommand log = git.log();

        log.call().forEach(rc -> {
            try {
                lista.add(createVersion(rc));
            } catch (IOException | GitAPIException ex) {
                Logger.getLogger(Git.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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

    private static Version createVersion(RevCommit it) throws IOException, GitAPIException {
        return new Version(it.getCommitterIdent().getWhen().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                String.valueOf(it.getId()).substring(7, 47),
                it.getShortMessage());
    }

    private Repository createRepository(File dir, org.eclipse.jgit.lib.Repository repository) throws IOException {
        return new Repository(dir.getCanonicalPath(), getUrlFromLocalRepository(repository));
    }

    private static RevCommit convertCommitToRevCommit(org.eclipse.jgit.lib.Repository repository, String commit) throws IncorrectObjectTypeException, RevisionSyntaxException, IOException {
        RevWalk rev = new RevWalk(repository);
        rev.reset();

        //Transformando o hash commit em um Objeto RevCommit
        ObjectId obj = repository.resolve(commit);
        return rev.parseCommit(obj);
    }

    private static List<ChangedFiles> searchDiff(org.eclipse.jgit.lib.Repository repository, RevCommit rev1, RevCommit rev2) throws IncorrectObjectTypeException, RevisionSyntaxException, IOException, GitAPIException {

        List<ChangedFiles> lista = new ArrayList<>();
        ObjectId oldHead = repository.resolve(rev2.getTree().getName());
        ObjectId head = repository.resolve(rev1.getTree().getName());
        try (ObjectReader reader = repository.newObjectReader()) {
            CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
            oldTreeIter.reset(reader, oldHead);
            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
            newTreeIter.reset(reader, head);

            try (org.eclipse.jgit.api.Git g = new org.eclipse.jgit.api.Git(repository)) {
                List<DiffEntry> diffs = g.diff()
                        .setNewTree(newTreeIter)
                        .setOldTree(oldTreeIter)
                        .call();

                diffs.iterator().forEachRemaining(entry -> {
                    ChangedFiles changed = new ChangedFiles(entry, entry.getChangeType().name());
                    lista.add(changed);
//                    /System.out.println(entry.getNewPath());
                });
//                stream().forEach((entry) -> {
//                    ChangedFiles changed = new ChangedFiles(entry, entry.getChangeType());
//                    lista.add(changed);
//                });
            }
        }
        return lista;
    }

    private static List<ChangedFiles> getChangedFilesFromSpecifiedVersion(org.eclipse.jgit.lib.Repository repository, String commit) throws IOException, GitAPIException {
        RevCommit revCommit1 = convertCommitToRevCommit(repository, commit);
        //Fluxo alternativo quando chegar no primeiro commit
        if (revCommit1.getParentCount() <= 0) {
            return searchDiff(repository, revCommit1, revCommit1);
        }
        //trocado pela chamada de metodos
        RevCommit revCommit2 = convertCommitToRevCommit(repository, revCommit1.getParents()[0].getName());
        return searchDiff(repository, revCommit1, revCommit2);
    }

}
