package br.edu.ifpb.scm.loads;

import br.edu.ifpb.scm.svn.Svn;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Ricardo Job
 */
public class LoaderSVN {

    private static final String dir = "/Users/job/Documents/dev/data";
    private static final String url = "http://svn.code.sf.net/p/xmlunit/code/trunk";

    public static void main(String[] args) {
        try {
            cloneRepo();
            checkout(578, 580);
        } catch (Exception ex) {
            Logger.getLogger(LoaderSVN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void cloneRepo() throws GitAPIException, IOException, CloneNotSupportedException {
        File dest = new File(dir + "/scm");
        Svn svn = new Svn(dest, url);
        svn.clone(url, dest);
    }

    private static void checkout(int inicio, int fim) {
        for (int i = inicio; i < fim; i++) {
            File dest = new File(dir + "/v" + i);
            Svn svn = new Svn(dest, url);
            svn.checkout(String.valueOf(i));
        }
    }
}
