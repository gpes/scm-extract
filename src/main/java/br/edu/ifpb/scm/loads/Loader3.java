/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.loads;

import br.edu.ifpb.scm.Repository;
import br.edu.ifpb.scm.api.ScmType;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Anderson Souza
 */
public class Loader3 {

    public static String URL = "https://github.com/jass2125/LocaGames";
    public static File PATH3 = new File("/home/des02/Desktop/scm");

    public static File PATH = new File("C:/Users/Amanda/Documents/scm");
    public static File PATH2 = new File("C:/Users/Pris/Desktop/scm");
//    public static File PATH3 = new File("/Users/job/Documents/dev/data/scm");
    public static File origin = new File("/Users/job/Documents/dev/data/scm");
    public static File dest = new File("/Users/job/Documents/dev/data/scm2");

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
//        Repository repository = ScmFactory.create("git").clon();
//       print(repository);

//        Repository repository = 
        Builder builder = new ScmBuilder();
        //Repository repository = builder.url(URL).dir(PATH3.getCanonicalPath()).create(ScmType.GIT).buildClone();
        Repository repository = builder.url(URL).dir(PATH3.getCanonicalPath()).create(ScmType.GIT).buildClone();
        print(repository);
    }

    public static void print(Repository repository) {
//        System.out.println(repository.);
//        System.out.println(repository);
        System.out.println("URL LOCAL: " + repository.getPathLocal());
        System.out.println("URL REMOTE: " + repository.getUrlRemote());
        System.out.println(repository.getVersions());

        repository.getVersions().forEach(t -> {
            System.out.println(t.getChanges());
        });

    }

}
