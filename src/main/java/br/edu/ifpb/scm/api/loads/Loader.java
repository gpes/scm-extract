/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.loads;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

/**
 *
 * @author  Anderson Souza
 */
public class Loader {

    public static final File DIRECTORY_PRIS = new File("C:/Users/Pris/Desktop/scm");
    public static final File DIRECTORY_ANDERSON = new File("/home/jairanderson/Área de Trabalho/Folder");
    public static final File DIRECTORY_JOB = new File("/Users/job/Documents/dev/testes/exemplo-jcabi/dataTeste/v2");
    public static final String REMOTE_URL = "https://github.com/google/gson";
    public static final String HASH = "d4a9eb4e7bcbf0fa9e9e76c81fc86ff669f7c8ea";
//    static List<Versao> lista = new ArrayList<>();

    public static void main(String[] args) throws IOException, ParseException {
        Git repo = Git.open(DIRECTORY_PRIS);

//        ScmAbstractFactory factory = new ScmFactory();
//        try {
            /* Fazendo clone de um repositório */
//            Git gitRepository = factory.createGit().clone(REMOTE_URL,DIRECTORY_PRIS);

            
            
        
//          Git repository = factory.createGithub().clone(DIRECTORY, "/Users/job/Documents/dev/testes/exemplo-jcabi/dataTeste/v1");

        /*Fazendo checkout de uma revisão específica*/
//          Ref call = gitRepository.checkout().setName(HASH).call();

        /*Recuperando commits a partir de um intervalo de tempo - Teste*/
//            LogCommand log = gitRepository.log();
//            
//            log.call().forEach(t -> {
//                lista.add(new Versao(t.getAuthorIdent().getEmailAddress(), t.getCommitterIdent().getWhen()));
//            });
//
//          String inicio = "01/06/2016";
//          String fim = "10/06/2016";
//
//          DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//
//          Date since = df.parse(inicio);
//          Date until = df.parse(fim);
////          bw(since, until).forEach(System.out::println);
////          bw(t->t.getData().after(since)).forEach(System.out::println);
//          bw(t -> t.getEmail().startsWith("j")).forEach(System.out::println);
        /*END*/
//          DIRECTORY_JOB.delete();
        /*Mostrando o tipo e quais arquivos foram alterados*/
//            showDiffs(gitRepository);
//            
//           /*Mostrando que alterações foram feitas no(s) arquivo(s)*/ 
            showFileDiffs(repo);
//            
        
        
    }
//
//    // É um exemplo :)
//    static final class Versao {
//
//        private final String email;
//        private final Date data;
//
//        public Versao(String email, Date data) {
//            this.email = email;
//            this.data = data;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public Date getData() {
//            return data;
//        }
//
//        @Override
//        public String toString() {
//            return "Versao(" + "email=" + email + ", data=" + data + ')';
//        }
//
//    }
//
//    public static List<Versao> bw(Date a, Date b) {
//        return bw(t -> t.getData().before(b) && t.getData().after(a));
//    }
////    public static List<Versao> after(Date a) {
////        return bw(t -> t.getData().after(a));
////    }
//
//    public static List<Versao> bw(Predicate<? super Versao> predicado) {
//        return lista.stream()
//                .filter(predicado)
//                .collect(Collectors.toList());
//    }
//
//    public static void showDiffs(Git gitRepository) {
//        Repository repo = gitRepository.getRepository();
//
//        try {
//            ObjectId head = repo.resolve("HEAD^{tree}");
//            ObjectId previousHead = repo.resolve("HEAD~^{tree}");
//            ObjectReader reader = repo.newObjectReader();
//
//            CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
//            oldTreeIter.reset(reader, previousHead);
//            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
//            newTreeIter.reset(reader, head);
//
//            List<DiffEntry> listDiffs = gitRepository.diff().setOldTree(oldTreeIter).setNewTree(newTreeIter).call();
//
//            listDiffs.stream().forEach((DiffEntry diff) -> {
//                System.out.println(diff);
//            });
//        } catch (IOException | GitAPIException ex) {
//            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
    public static void showFileDiffs(Git gitRepository) {
        Repository repo = gitRepository.getRepository();

        try {
            ObjectId head = ObjectId.fromString("c24af304077e4a6d1925db7cd35d0cd1ed488d6a");
            ObjectId previousHead = ObjectId.fromString("c16be41e77bb53a4b639cb864c9a6e4d0f8df7c2");
            ObjectReader reader = repo.newObjectReader();

            CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
            oldTreeIter.reset(reader, previousHead);
            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
            newTreeIter.reset(reader, head);

            List<DiffEntry> listDiffs = gitRepository.diff().setOldTree(oldTreeIter).setNewTree(newTreeIter).call();

            listDiffs.stream().forEach((DiffEntry diff) -> {
                
                DiffFormatter formatter = new DiffFormatter(System.out);
                formatter.setRepository(repo);
                System.out.println(diff);
                try {
                    formatter.format(diff);
                } catch (IOException ex) {
                    Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (IOException | GitAPIException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}
//
//    public static void showLog(Git gitRepository) {
//        LogCommand log = gitRepository.log();
//        try {
//            for (RevCommit t : log.call()) {
////                Date commitDate = new Date(commit.getCommitTime() * 1000L);
////                System.out.println("Autor: " + commit.getAuthorIdent() + "Data: " + commitDate);
////                System.out.print(commit.getCommitterIdent().getWhen().equals(commitDate)?"":"false "+commit.getAuthorIdent().getWhen()+ " - "+commitDate+"\n");
////                System.out.println(commit.getCommitterIdent().getWhen() + " - " + commitDate);
//            }
//        } catch (GitAPIException ex) {
//            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}
