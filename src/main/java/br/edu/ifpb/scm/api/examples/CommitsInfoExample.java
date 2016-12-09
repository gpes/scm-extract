//package br.edu.ifpb.scm.api.examples;
//
//import br.edu.ifpb.scm.api.Repository;
//import br.edu.ifpb.scm.api.ScmBuilder;
//import br.edu.ifpb.scm.api.ScmType;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.ResourceBundle;
//import org.eclipse.jgit.api.errors.GitAPIException;
//
///**
// *
// * @author Anderson Souza
// */
//public class CommitsInfoExample {
//
//    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
//
//        ResourceBundle banco = ResourceBundle.getBundle("scm");
//        String dirRepo = banco.getString("dir.local.jair");
//        try {
//            Repository repository = new ScmBuilder()
//                    .dir(dirRepo)
//                    .create(ScmType.GIT).buildRepository();
//
//            repository.getVersions().forEach(version -> {
//                System.out.println("\n ---- Informações sobre os Commits ---- ");
//                System.out.println("Data do Commit: " + version.getCommitDate());
//                System.out.println("HashCode do Commit: " + version.getHashCode());
//                System.out.println("Mensagem:" + version.getMessage());
//                System.out.println("\n");
//            });
//
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
