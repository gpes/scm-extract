package br.edu.ifpb.scm.api.examples;

import br.edu.ifpb.scm.api.AbstractFactory;
import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.factories.ScmGitFactory;
import br.edu.ifpb.scm.api.git.Version;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import static org.tmatesoft.svn.core.internal.wc2.SvnRepositoryAccess.RepositoryInfo.repository;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/07/2016, 02:11:49
 */
public class DiffVersionsExample {

    public static void main(String[] args) throws Exception {
        ResourceBundle resource = ResourceBundle.getBundle("scm");
        String dir = System.getProperty("java.io.tmpdir") + resource.getString("dir");
        String url = resource.getString("url.repo");

        AbstractFactory factory = new ScmGitFactory();
        SCM scm = factory.createScm();
        scm.setDir(dir).setUrl(url);
        Repository repository = scm.buildRepository();
        System.out.println("Local URL: " + repository.getPathLocal());
        System.out.println("Remote URL: " + repository.getUrlRemote());

        repository.getVersions().forEach((Version version) -> {
            try {
                System.out.println("Commit Date: " + version.getCommitDate());
                System.out.println("Commit Hashcode: " + version.getHashCode());
                System.out.println("Message: " + version.getMessage());
                List<DiffEntry> diffs = version.getDiffs();
                OutputStream stream = new ByteArrayOutputStream();
                DiffFormatter format = new DiffFormatter(stream);
                format.setRepository(scm.getRepository());

//                shouldComputeTheDiffOfACommit(scm.getRepository(), "9840312e2e8401853a42452432678a28c296777a");
//                for (DiffEntry diffEntry : diffs) {
//                    try {
//                        format.format(diffEntry);
//                    } catch (IOException ex) {
//                        Logger.getLogger(DiffVersionsExample.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
            } catch (Exception ex) {
                Logger.getLogger(DiffVersionsExample.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public static void shouldComputeTheDiffOfACommit(org.eclipse.jgit.lib.Repository repository, String hascodeCommit) throws Exception {
        // print = true;
        // Find the commit ...
        Ref ref = repository.getRef("master");
        ref = repository.peel(ref);
        RevWalk walker = new RevWalk(repository);
        walker.setRetainBody(true);
        try {
            RevCommit commit = walker.parseCommit(ref.getObjectId());

            // Set up the tree walk to obtain the difference between the commit and it's parent(s) ...
            TreeWalk tw = new TreeWalk(repository);
            tw.setRecursive(true);
            tw.addTree(commit.getTree());
            for (RevCommit parent : commit.getParents()) {
                RevCommit parentCommit = walker.parseCommit(parent);
                tw.addTree(parentCommit.getTree());
            }

            // Now process the diff of each file ...
            for (DiffEntry fileDiff : DiffEntry.scan(tw)) {
                ChangeType type = fileDiff.getChangeType();
                switch (type) {
                    case ADD:
                        String newPath = fileDiff.getNewPath();
//                        print("ADD   ", newPath);
                        break;
                    case COPY:
                        newPath = fileDiff.getNewPath();
                        String origPath = fileDiff.getOldPath();
//                        print("COPY   ", origPath, " -> ", newPath);
                        break;
                    case DELETE:
                        origPath = fileDiff.getOldPath();
//                        print("DELETE ", origPath);
                        break;
                    case MODIFY:
                        newPath = fileDiff.getNewPath();
//                        print("MODIFY ", newPath);
                        break;
                    case RENAME:
                        newPath = fileDiff.getNewPath();
                        origPath = fileDiff.getOldPath();
//                        print("RENAME ", origPath, " -> ", newPath);
                        break;
                    default:
                        // skip
                        break;
                }
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                DiffFormatter formatter = new DiffFormatter(output);
                formatter.setRepository(repository);
                formatter.format(fileDiff);
                String diff = output.toString("UTF-8");
                System.out.println(output);
//                print(diff);
            }
        } finally {
            walker.dispose();
        }
    }
}
