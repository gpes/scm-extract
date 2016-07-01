/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.loads;

import br.edu.ifpb.scm.Repository;
import br.edu.ifpb.scm.api.ScmFactory;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Anderson Souza
 */
public class Loader3 {

    public static String URL = "https://github.com/EndenhariaDeSoftware/scm-extract";
    public static File PATH = new File("C:/Users/Anderson Sousa/Documents/scm");

    public static File PATH3 = new File("C:/Users/Amanda/Documents/scm2");
    public static File PATH2 = new File("C:/Users/Pris/Desktop/scm");
//    public static File PATH3 = new File("/Users/job/Documents/dev/data/scm");
    public static File origin = new File("/Users/job/Documents/dev/data/scm");
    public static File dest = new File("/Users/job/Documents/dev/data/scm2");

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
        Repository repository = ScmFactory.create("git").clone(URL, PATH);
        print(repository);
    }

    public static void print(Repository repository) {
        System.out.println("URL LOCAL: " + repository.pathLocal());
        System.out.println("URL REMOTE: " + repository.urlRemote());
//        System.out.println(repository.getVersions());
    }

}
