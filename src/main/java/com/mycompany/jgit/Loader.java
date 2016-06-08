/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgit;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import static java.time.LocalDate.now;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 *
 * @author Anderson Souza
 */
public class Loader {

    public static final File DIRECTORY_PRIS = new File("C:/Users/Pris/Desktop/Teste1");
    public static final File DIRECTORY_JOB = new File("/Users/job/Documents/dev/testes/exemplo-jcabi/dataTeste/v2");
    public static final String REMOTE_URL = "https://github.com/google/gson";
    public static final String HASH = "d4a9eb4e7bcbf0fa9e9e76c81fc86ff669f7c8ea";

    public static void main(String[] args) throws IOException, ParseException {

        AbstractFactory factory = new Factory();
        try {
            /* Clone */
//            Git repository = factory.createGithub().clone(DIRECTORY, "/Users/job/Documents/dev/testes/exemplo-jcabi/dataTeste/v1");
            Git gitRepository = factory.createGithub().clone(DIRECTORY_PRIS, REMOTE_URL);

            /* Clone/checkout individual  */
//            Ref call = gitRepository.checkout().setName(HASH).call();
            /* Visualização de log -> Autor e Data*/
//            LogCommand log = gitRepository.log();
//            for (RevCommit commit : log.call()) {
//                Date commitDate = new Date(commit.getCommitTime() * 1000L);
//                System.out.println("Autor: " + commit.getAuthorIdent() + "Data: " + commitDate);
//
//            }
            
            /* Manipulação de Data -> Checkout entre datas */
            DateTimeFormatter fomr = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
            System.out.println(ZonedDateTime.now().format(fomr));

            //RevWalk walk = new RevWalk(gitRepository.getRepository());
//            String sinceDate = "Wed Apr 27 01:40:58 GMT-03:00 2016";
//            String untilDate = "Sat Apr 23 14:36:52 GMT-03:00 2016";
//                    
//            String pattern = "EEE MMM HH:mm:ss dd yyyy";
//            DateFormat df = new SimpleDateFormat(pattern);
//            
//            Date since = df.parse(sinceDate);
//            Date until = df.parse(untilDate);
//            
//            RevFilter bt = CommitTimeRevFilter.between(since, until);
        } catch (GitAPIException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
