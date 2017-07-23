/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.examples;

import java.util.Collection;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jul 23, 2017 6:07:11 PM
 */
public class GitChange {

    private final RevCommit commit;
    private final Collection<DiffEntry> diffs;

    /**
     * @param commit
     * @param diffs
     */
    public GitChange(RevCommit commit, Collection<DiffEntry> diffs) {
        this.commit = commit;
        this.diffs = diffs;
    }

    /**
     * Returns commit.
     *
     * @return
     */
    public RevCommit getCommit() {
        return commit;
    }

    /**
     * Returns diffs.
     * @return 
     */
    public Collection<DiffEntry> getDiffs() {
        return diffs;
    }
}
