/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgit;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
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
    static List<Versao> lista = new ArrayList<>();

    public static void main(String[] args) throws IOException, ParseException {

        AbstractFactory factory = new Factory();
        try {

//            Git repository = factory.createGithub().clone(DIRECTORY, "/Users/job/Documents/dev/testes/exemplo-jcabi/dataTeste/v1");
            Git gitRepository = factory.createGithub().clone(DIRECTORY_JOB, REMOTE_URL);
//            Ref call = gitRepository.checkout().setName(HASH).call();

            LogCommand log = gitRepository.log();

//            for (RevCommit t : log.call()) {
////                Date commitDate = new Date(commit.getCommitTime() * 1000L);
////                System.out.println("Autor: " + commit.getAuthorIdent() + "Data: " + commitDate);
////                System.out.print(commit.getCommitterIdent().getWhen().equals(commitDate)?"":"false "+commit.getAuthorIdent().getWhen()+ " - "+commitDate+"\n");
////                System.out.println(commit.getCommitterIdent().getWhen() + " - " + commitDate);
//                lista.add(new Versao(t.getAuthorIdent().getEmailAddress(),
//                        t.getCommitterIdent().getWhen()));
//            }
            log.call().forEach( t -> {
                lista.add(new Versao(t.getAuthorIdent().getEmailAddress(), t.getCommitterIdent().getWhen()));
            });
            
            String inicio = "01/06/2016";
            String fim = "10/06/2016";

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            Date since = df.parse(inicio);
            Date until = df.parse(fim);
            //            bw(since, until).forEach(System.out::println);
            //            bw(t->t.getData().after(since)).forEach(System.out::println);
            bw(t -> t.getEmail().startsWith("j")).forEach(System.out::println);

//            DateTimeFormatter fomr = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
//            System.out.println(ZonedDateTime.now().format(fomr));
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
            DIRECTORY_JOB.delete();
        } catch (GitAPIException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // É um exemplo :)
    static final class Versao {

        private final String email;
        private final Date data;

        public Versao(String email, Date data) {
            this.email = email;
            this.data = data;
        }

        public String getEmail() {
            return email;
        }

        public Date getData() {
            return data;
        }

        @Override
        public String toString() {
            return "Versao(" + "email=" + email + ", data=" + data + ')';
        }

    }

    public static List<Versao> bw(Date a, Date b) {
        return bw(t -> t.getData().before(b) && t.getData().after(a));
    }
//    public static List<Versao> after(Date a) {
//        return bw(t -> t.getData().after(a));
//    }

    public static List<Versao> bw(Predicate<? super Versao> predicado) {
        return lista.stream()
                .filter(predicado)
                .collect(Collectors.toList());
    }
}
