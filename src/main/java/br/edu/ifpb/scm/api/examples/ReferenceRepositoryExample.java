///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package br.edu.ifpb.scm.api.examples;
//
//import br.edu.ifpb.scm.api.Builder;
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
//public class ReferenceRepositoryExample {
//
//    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
//        ResourceBundle resource = ResourceBundle.getBundle("scm");
//        String PATH = resource.getString("dir.local.jair");
//        Builder builder = new ScmBuilder();
//        Repository repository = builder
//                .dir(PATH)
//                .create(ScmType.GIT)
//                .buildRepository();
//
//        System.out.println("Local URL: " + repository.getPathLocal());
//        System.out.println("Remote URL: " + repository.getUrlRemote());
//    }
//}
