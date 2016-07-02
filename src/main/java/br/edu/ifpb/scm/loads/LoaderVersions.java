/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.loads;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.AnyObjectId;
import static org.eclipse.jgit.lib.ObjectChecker.tree;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

/**
 *
 * @author Anderson Souza
 */
public class LoaderVersions {

    public static String URL = "https://github.com/jass2125/LocaGames";
    public static File PATH = new File("C:/Users/Amanda/Documents/scm");

    public static String teste3() throws IOException {
        try (org.eclipse.jgit.lib.Repository repository = org.eclipse.jgit.api.Git.open(PATH).getRepository()) {
            // See e.g. GetRevCommitFromObjectId for how to use a SHA-1 directly
            Ref head = repository.findRef("HEAD");
            System.out.println("Ref of HEAD: " + head + ": " + head.getName() + " - " + head.getObjectId().getName() + "\n");
            System.out.println("Ref of HEAD getName: " + head.getName() + "\n");
            System.out.println("Ref of HEAD getId().getName: " + head.getObjectId().getName() + "\n");

            // a RevWalk allows to walk over commits based on some filtering that is defined
            try (RevWalk walk = new RevWalk(repository)) {
                RevCommit commit = walk.parseCommit(head.getObjectId());
                System.out.println("Commit: " + commit);

                // a commit points to a tree
                RevTree tree = walk.parseTree(commit.getTree().getId());
                System.out.println("FOUND TREE: " + tree.getName());

                walk.dispose();
                return tree.getName();
            }
        }
    }

    public static String teste4() throws IOException {
        try (org.eclipse.jgit.lib.Repository repository = org.eclipse.jgit.api.Git.open(PATH).getRepository()) {
            // See e.g. GetRevCommitFromObjectId for how to use a SHA-1 directly
            Ref head = repository.findRef("HEAD");
            System.out.println("Ref of HEAD: " + head + ": " + head.getName() + " - " + head.getObjectId().getName() + "\n");
            System.out.println("Ref of HEAD getName: " + head.getName() + "\n");
            System.out.println("Ref of HEAD getId().getName: " + head.getObjectId().getName() + "\n");

            // a RevWalk allows to walk over commits based on some filtering that is defined
            try (RevWalk walk = new RevWalk(repository)) {
                RevCommit commit = walk.parseCommit(head.getObjectId());
                System.out.println("Commit: " + commit);

                // a commit points to a tree
                RevTree tree = walk.parseTree(commit.getTree().getId());
                System.out.println("FOUND TREE: " + tree.getName());

                walk.dispose();
                return tree.getName();
            }
        }
    }

    public static void teste2() throws IOException, GitAPIException {
        org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.open(PATH);
        org.eclipse.jgit.lib.Repository repository = git.getRepository();

        repository.findRef(URL);

        ObjectId oldHead = repository.resolve("HEAD~^{tree}");
        ObjectId newHead = repository.resolve("HEAD^{tree}");

        ObjectReader reader = repository.newObjectReader();
        CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
//        ObjectId oldTree = git.getRepository().resolve("SHA-1{64c852a8fe9e3673aa381f95c4b0420986d1f925}");

        CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
//        ObjectId newTree = git.getRepository().resolve("SHA-1{12ae7a9960c49cfe68bdd5f7b0a58e1b3b0c6e56}");

        oldTreeIter.reset(reader, oldHead);
        newTreeIter.reset(reader, newHead);

        try (org.eclipse.jgit.api.Git g = new org.eclipse.jgit.api.Git(repository)) {
            List<DiffEntry> diffs = g.diff()
                    .setNewTree(newTreeIter)
                    .setOldTree(oldTreeIter)
                    .call();
            for (DiffEntry entry : diffs) {
                System.out.println("Entry: " + entry);
            }
        }

//        DiffFormatter diffFormatter = new DiffFormatter(DisabledOutputStream.INSTANCE);
//        diffFormatter.setRepository(git.getRepository());
//        List<DiffEntry> entries = diffFormatter.scan(oldTreeIter, newTreeIter);
//
//        entries.stream().forEach((entry) -> {
//            System.out.println(entry.getChangeType());
//        });
    }

    public static void teste() throws IOException {
        try (org.eclipse.jgit.lib.Repository repository = org.eclipse.jgit.api.Git.open(PATH).getRepository()) {
            Ref head = repository.exactRef("HEAD");
            System.out.println("Found head: " + head);

            // a RevWalk allows to walk over commits based on some filtering that is defined
            try (RevWalk walk = new RevWalk(repository)) {
                RevCommit commit = walk.parseCommit(head.getObjectId());
                System.out.println("Found Commit: " + commit);

                // You can also get the commit for an (abbreviated) SHA
                walk.reset();
                ObjectId id = repository.resolve("38d51408bd");
                RevCommit commitAgain = walk.parseCommit(id);
                System.out.println("Found Commit again: " + commitAgain);

                walk.dispose();
            }
        }
        //teste3();
    }

    public static void capturaOCodigoDeCadaHashDaArvoreDeArquivos() throws IOException {
        org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.open(PATH);
        try (org.eclipse.jgit.lib.Repository repository = git.getRepository()) {
            // The {tree} will return the underlying tree-id instead of the commit-id itself!
            // For a description of what the carets do see e.g. http://www.paulboxley.com/blog/2011/06/git-caret-and-tilde
            // This means we are selecting the parent of the parent of the parent of the parent of current HEAD and
            // take the tree-ish of it
            //id da tree

            ObjectId oldHead = repository.resolve("HEAD{61c0f8c738b2ae20a91621337bffbfb164f7cc77}");
            ObjectId head = repository.resolve("HEAD^^{tree}");

            //Pegando o codigo de cada commit pra arvore
            System.out.println("Printing diff between tree: " + oldHead.getName() + " and " + head.getName());

        }
    }

    public static void funcionando(final String commit) throws IOException, GitAPIException {
        org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.open(PATH);
        try (org.eclipse.jgit.lib.Repository repository = git.getRepository()) {
            // The {tree} will return the underlying tree-id instead of the commit-id itself!
            // For a description of what the carets do see e.g. http://www.paulboxley.com/blog/2011/06/git-caret-and-tilde
            // This means we are selecting the parent of the parent of the parent of the parent of current HEAD and
            // take the tree-ish of it
            //id da tree
            // a RevWalk allows to walk over commits based on some filtering that is defined

            ObjectId obj = ObjectId.fromString(commit);
            RevWalk walk = new RevWalk(repository);
            RevCommit revCommit = walk.parseCommit(obj);
            
            
            RevCommit[] arra = revCommit.getParents();
            
            ObjectId obj2 = ObjectId.fromString(arra[0].getName());
            RevWalk walk2 = new RevWalk(repository);
            RevCommit revCommit2 = walk2.parseCommit(obj2);
            
            ObjectId oldHead = repository.resolve(revCommit2.getTree().getName());
            ObjectId head = repository.resolve(revCommit.getTree().getName());

            //Pegando o codigo de cada commit pra arvore
            System.out.println("Printing diff between tree: " + oldHead.getName() + " and " + head.getName());

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
                        System.out.println("Entry: " + entry);
                    });
                }
            }
        }

        System.out.println("Done");

    }

    public static void main(String[] args) throws IOException, GitAPIException {
//        funcionando();
        //teste3();
        //teste2();
        //teste();
//        capturaOCodigoDeCadaHashDaArvoreDeArquivos();

        //LoaderVersions.capturaOCodigoDeCadaHashDaArvoreDeArquivos();
        LoaderVersions.funcionando("7f5f53fc049d8ade0bb95f72cdbe5f7c0d8d5cef");
    }
}
