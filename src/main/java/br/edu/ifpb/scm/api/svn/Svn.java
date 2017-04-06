/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.svn;

import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.factories.Repository;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc2.SvnCheckout;
import org.tmatesoft.svn.core.wc2.SvnLog;
import org.tmatesoft.svn.core.wc2.SvnOperationFactory;
import org.tmatesoft.svn.core.wc2.SvnRevisionRange;
import org.tmatesoft.svn.core.wc2.SvnTarget;

/**
 *
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 */
public class Svn implements SCM {

    private Repository repository;
    private SvnOperationFactory operationFactory;
    private SVNURL location;
    private File dir;
    private String url;
    private File path;
    private SCM scm;

    public Svn() {
    }

    /**
     * TODO: Conseguir configurar isso na interface SCM
     *
     * @param dir
     * @param url
     */
    public Svn(File dir, String url) {
        try {
            this.dir = dir;
            location = SVNURL.parseURIEncoded(url);
            operationFactory = new SvnOperationFactory();
        } catch (SVNException ex) {
            Logger.getLogger(Svn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    //TODO
    @Override
    public Repository buildRepository() {
        return cloneRepository();
    }

    public Repository cloneRepository() {
        try {
            location = SVNURL.parseURIEncoded(url);
            operationFactory = new SvnOperationFactory();
            dir = path;
            Logger.getLogger(Svn.class.getName()).log(Level.INFO, "Iniciando o checkout da vers\u00e3o: {0} no dir {1}", new Object[]{"HEAD", dir});
            return checkout("-1");
        } catch (SVNException ex) {
            ex.printStackTrace();
            Logger.getLogger(Svn.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Repository getRepositorySVN() {

        try {
            location = SVNURL.fromFile(dir);//parseURIEncoded(url);
            operationFactory = new SvnOperationFactory();
            this.dir = dir;
            return checkout("-1");
        } catch (SVNException ex) {
            Logger.getLogger(Svn.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override //TODO: Criar o Repository
    public Repository checkout(String commit) {
        try {
            final SvnCheckout checkout = operationFactory.createCheckout();
            checkout.setSingleTarget(SvnTarget.fromFile(dir));
            checkout.setSource(SvnTarget.fromURL(location));
            checkout.setRevision(SVNRevision.create(Long.parseLong(commit)));
            checkout.run();
            Logger.getLogger(Svn.class.getName()).log(Level.INFO, "Concluido o checkout da vers\u00e3o: {0} no dir {1}", new Object[]{commit, dir});
        } catch (SVNException ex) {
            Logger.getLogger(Svn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void log() {
        try {
//            ReceiverSVN receiver = new ReceiverSVN(null, null);
            SvnLog log = operationFactory.createLog();
            log.setSingleTarget(SvnTarget.fromURL(location));
            log.addRange(SvnRevisionRange.create(SVNRevision.create(1), SVNRevision.create(2)));
            log.setDiscoverChangedPaths(true);
//            log.setReceiver(receiver);
            log.setStopOnCopy(true);
            log.run();
        } catch (SVNException ex) {
            Logger.getLogger(Svn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public SCM setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public SCM setDir(String dir) {
        this.path = new File(dir);
        return this;
    }

    public SCM setScm(SCM scm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public org.eclipse.jgit.lib.Repository getRepository() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

//package info.sample;
//
//import java.util.List;
//import java.util.Set;
//import org.immutables.value.Value;
//
//@Value.Immutable
//public abstract class FoobarValue {
//  public abstract int foo();
//  public abstract String bar();
//  public abstract List<Integer> buz();
//  public abstract Set<Long> crux();
//}
//package info.sample;
//
//import java.util.List;
//
//public class FoobarValueMain {
//  public static void main(String... args) {
//    FoobarValue value = ImmutableFoobarValue.builder()
//        .foo(2)
//        .bar("Bar")
//        .addBuz(1, 3, 4)
//        .build(); // FoobarValue{foo=2, bar=Bar, buz=[1, 3, 4], crux={}}
//
//    int foo = value.foo(); // 2
//
//    List<Integer> buz = value.buz(); // ImmutableList.of(1, 3, 4)
//  }
//}
