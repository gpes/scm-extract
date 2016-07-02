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
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
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

            for (Version v : repo.getVersions()) {
                List<ChangedFiles> changedFiles = getChangedFiles(repository, v.getHashCode());
                v.setChanges(changedFiles);
            }
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

        log.call().forEach(rc -> {
            try {
                lista.add(createVersion(rc));
            } catch (IOException ex) {
                Logger.getLogger(Git.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GitAPIException ex) {
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
//        List<ChangedFiles> lista = createChangedFiles(version);
//        version.setChanges(lista);
//        return version;
    }

    private Repository createRepository(File dir, org.eclipse.jgit.lib.Repository repository) throws IOException {
        return new Repository(dir.getCanonicalPath(), getUrlFromLocalRepository(repository));
    }

    private static List<ChangedFiles> getChangedFiles(org.eclipse.jgit.lib.Repository repository, String commit) throws IOException, GitAPIException {
        
        
        RevWalk revWalk1 = new RevWalk(repository);
        revWalk1.reset();
        
        //Transformando o hash commit em um Objeto RevCommit
        ObjectId obj = repository.resolve(commit);
        RevCommit revCommit1 = revWalk1.parseCommit(obj);

        List<ChangedFiles> lista = new ArrayList<>();
        
        //Fluxo alternativo quando chegar no primeiro commit
        if (revCommit1.getParentCount() <= 0) {

            ObjectId oldHead = repository.resolve(revCommit1.getTree().getName());
            ObjectId head = repository.resolve(revCommit1.getTree().getName());

            try (ObjectReader reader = repository.newObjectReader()) {
                CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
                oldTreeIter.reset(reader, oldHead);
                CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
                newTreeIter.reset(reader, head);

                // finally get the list of changed files
                try (org.eclipse.jgit.api.Git g = new org.eclipse.jgit.api.Git(repository)) {
                    List<DiffEntry> diffs = g.diff()
                            .setNewTree(newTreeIter)
                            .setOldTree(oldTreeIter)
                            .call();
                    diffs.stream().forEach((entry) -> {
                        //System.out.println("Entry: " + entry);
                        ChangedFiles changed = new ChangedFiles(entry, entry.getChangeType());
                        lista.add(changed);
                    });
                }
            }
            //System.out.println("Done");
            return lista;
        }
//
        RevCommit[] array = revCommit1.getParents();
        ObjectId obj2 = ObjectId.fromString(array[0].getName());
        RevWalk walk2 = new RevWalk(repository);
        RevCommit revCommit2 = walk2.parseCommit(obj2);

        ObjectId oldHead = repository.resolve(revCommit2.getTree().getName());
        ObjectId head = repository.resolve(revCommit1.getTree().getName());

        //Pegando o codigo de cada commit pra arvore
        //System.out.println("Printing diff between tree: " + oldHead.getName() + " and " + head.getName());
        // prepare the two iterators to compute the diff between
        try (ObjectReader reader = repository.newObjectReader()) {
            CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
            oldTreeIter.reset(reader, oldHead);
            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
            newTreeIter.reset(reader, head);

            // finally get the list of changed files
            try (org.eclipse.jgit.api.Git g = new org.eclipse.jgit.api.Git(repository)) {
                List<DiffEntry> diffs = g.diff()
                        .setNewTree(newTreeIter)
                        .setOldTree(oldTreeIter)
                        .call();
                diffs.stream().forEach((entry) -> {
                    //System.out.println("Entry: " + entry);
                    ChangedFiles changed = new ChangedFiles(entry, entry.getChangeType());
                    lista.add(changed);
                });
            }
        }
        //System.out.println("Done");
        return lista;
    }

}
