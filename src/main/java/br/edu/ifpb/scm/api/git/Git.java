/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.api.git;

import br.edu.ifpb.scm.api.exception.DiffException;
import br.edu.ifpb.scm.api.exception.SCMException;
import br.edu.ifpb.scm.api.exception.ConvertionException;
import br.edu.ifpb.scm.api.exception.AuthorizationException;
import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.SCM;
import br.edu.ifpb.scm.api.exception.ReferenceException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

/**
 * @author Priscila Gouveia <priscilaggouveia@gmail.com>
 * @author Anderson Souza
 */
public class Git implements SCM {

    private Repository repository;
    private String url;
    private File dir;
    private org.eclipse.jgit.api.Git git;
    private org.eclipse.jgit.lib.Repository repoJGit;
    private static final String HEAD = "^{tree}";
    private static final String HEAD_NEIGHBOR = "~1^{tree}";

    public Git() {
        this.repository = new Repository();
        this.git = null;
    }

    @Override
    public Repository buildRepository() {
        if (!dir.exists() && !dir.isDirectory()) {
            try {
                repoJGit = cloneRepository();
            } catch (CloneNotSupportedException | GitAPIException ex) {
                Logger.getLogger(Git.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            repoJGit = this.getReferenceRepository();
        }
        repository = createRepository();
        repository.AddAllVersions(versions());
        repository.setScm(new Git());
        return repository;
    }

    private org.eclipse.jgit.lib.Repository getReferenceRepository() throws SCMException {
        try {
            git = org.eclipse.jgit.api.Git.open(dir);
            repoJGit = git.getRepository();
            this.url = this.getUrlFromLocalRepository();
            return git.getRepository();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SCMException("Erro ao recuperar referencia do repositório.", e);
        }
    }

    private org.eclipse.jgit.lib.Repository cloneRepository() throws CloneNotSupportedException, GitAPIException {
        try {
            this.git = org.eclipse.jgit.api.Git.cloneRepository().setURI(url).setDirectory(dir).call();
        } catch (Exception e) {
            this.git = org.eclipse.jgit.api.Git.cloneRepository().setDirectory(dir).call();
        }
        return this.git.getRepository();
    }

    /**
     * Recupera a lista de versões
     *
     * @param git Git JGit
     * @return List {@link List} de {@link Version}
     */
    private List<Version> versions() {
        List<Version> listOfVersions = new ArrayList();
        LogCommand log = git.log();
        try {
            log.call().forEach(rc -> {
                try {
                    listOfVersions.add(createVersion(rc));
                } catch (IOException ex) {
                    Logger.getLogger(Git.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (GitAPIException | NullPointerException e) {
            e.printStackTrace();
            throw new SCMException("Não foi ppossível recuperar as versões", e);
        }
        return listOfVersions;
    }

    /**
     * Cria um objeto versão
     *
     * @param repo {@link org.eclipse.jgit.lib.Repository} JGit
     * @param it RevCommit JGit
     * @return {@link Version} Versão
     */
    private Version createVersion(RevCommit it) throws IOException {
        Author author = new Author();
        author.setName(it.getAuthorIdent().getName());
        author.setEmail(it.getAuthorIdent().getEmailAddress());
        List<ChangedFiles> listOfChangedFiles = getChangedFilesFromSpecifiedVersion(extractHashFromCommit(it));
        List<DiffEntry> listOfDiffs = Collections.EMPTY_LIST;
        boolean flag = false;
        if (it.getParentCount() <= 0) {
            flag = true;
            listOfDiffs = getDiff(extractHashFromCommit(it), flag);
        }
        listOfDiffs = getDiff(extractHashFromCommit(it), flag);
        return new Version(extractLocalDateFromCommit(it),
                extractHashFromCommit(it),
                it.getShortMessage(), listOfDiffs, author);

//                .setChanges(listOfChangedFiles);
    }

    /**
     * Recupera a referencia do RevCommit
     *
     * @param repoJGit {@link org.eclipse.jgit.lib.Repository} Repositorio JGit
     * @param commit String
     * @return List {@link List} de de {@link ChangedFiles}
     */
    private List<ChangedFiles> getChangedFilesFromSpecifiedVersion(String commit) {
        RevCommit revCommit1 = convertStringToRevCommit(commit);
        //Fluxo alternativo quando chegar no primeiro commit do repositório
        if (revCommit1.getParentCount() <= 0) {
            return searchDiff(revCommit1, revCommit1);
        }
        //trocado pela chamada de metodos
        RevCommit revCommit2 = convertStringToRevCommit(revCommit1.getParents()[0].getName());
        return searchDiff(revCommit1, revCommit2);
    }

    private String extractHashFromCommit(RevCommit commit) {
        return String.valueOf(commit.getId()).substring(7, 47);
    }

    private LocalDate extractLocalDateFromCommit(RevCommit commit) {
        return commit.getCommitterIdent().getWhen().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     *
     * @param repoJGit {@link org.eclipse.jgit.lib.Repository} Repositorio JGit
     * @param commit String hashCode do commit
     * @return {@link RevCommit} RevCommit
     */
    private RevCommit convertStringToRevCommit(String commit) {
        RevWalk rev = new RevWalk(repoJGit);
        rev.reset();
        //Transformando o hash commit em um Objeto RevCommit
        try {
            ObjectId obj = repoJGit.resolve(commit);
            return rev.parseCommit(obj);
        } catch (IncorrectObjectTypeException e) {
            throw new ConvertionException("Erro de Conversão do tipo String em RevCommit", e);
        } catch (RevisionSyntaxException | IOException e) {
            throw new ConvertionException("Erro de conversão do tipo String em RevCommit", e);
        }
    }

    private List<DiffEntry> getDiff(String commit, boolean flag) throws IOException {
        ObjectReader reader = git.getRepository().newObjectReader();
        CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
        ObjectId oldTree = git.getRepository().resolve(commit + "^{tree}"); // equals newCommit.getTree()
        oldTreeIter.reset(reader, oldTree);
        ObjectId newTree = null;
        if (flag) {
            newTree = git.getRepository().resolve(commit + HEAD); // equals oldCommit.getTree()
        } else {
            newTree = git.getRepository().resolve(commit + HEAD_NEIGHBOR); // equals oldCommit.getTree()
        }

        DiffFormatter formatter = new DiffFormatter(System.out);
        formatter.setRepository(git.getRepository());
        List<DiffEntry> scan = formatter.scan(oldTree, newTree);
        
        return scan;
    }

    /**
     * Cria um objeto Repository
     *
     * @param dir Path local do repositório
     * @param repository Repositório JGit
     * @return {@link Repository} Repositorio
     * @throws AuthorizationException
     * @throws DirectoryException
     */
    private Repository createRepository() {
        try {
            return new Repository(dir.getCanonicalPath(), getUrlFromLocalRepository());
        } catch (SecurityException e) {
            throw new AuthorizationException("Verifique as permissões de acesso a pastas de seu computador.", e);
        } catch (IOException e) {
            throw new ReferenceException("Ocorreu um erro, tente novamente mais tarde", e);
        }
    }

    /**
     * Recupera a url do repositorio remoto
     *
     * @param repository {@link org.eclipse.jgit.lib.Repository} JGit
     * @return {@link String} Url do repositorio remoto
     */
    private String getUrlFromLocalRepository() {
        Config config = repoJGit.getConfig();
        return config.getString("remote", "origin", "url");
    }

    /**
     * Recupera a difereça entre as versões
     *
     * @param repoJGit {@link org.eclipse.jgit.lib.Repository} Repositorio JGit
     * JGit
     * @param rev1 {@link RevCommit} RevCommit
     * @param rev2 {@link RevCommit} RevCommit
     * @return {@link List} List de {@link Version}
     */
    private List<ChangedFiles> searchDiff(RevCommit rev1, RevCommit rev2) {
        List<ChangedFiles> listaOfChangedFiles = new ArrayList<>();
        try {
            ObjectId oldHead = repoJGit.resolve(rev2.getTree().getName());
            ObjectId head = repoJGit.resolve(rev1.getTree().getName());
            ObjectReader reader = repoJGit.newObjectReader();
            CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
            oldTreeIter.reset(reader, oldHead);
            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
            newTreeIter.reset(reader, head);
            org.eclipse.jgit.api.Git g = new org.eclipse.jgit.api.Git(repoJGit);

            List<DiffEntry> diffs = g.diff()
                    .setNewTree(newTreeIter)
                    .setOldTree(oldTreeIter)
                    .call();

            diffs.iterator().forEachRemaining(entry -> {
                String oldPath = null;
                if (entry.getChangeType().equals(DiffEntry.ChangeType.MODIFY) || entry.getChangeType().equals(DiffEntry.ChangeType.COPY)) {
                    oldPath = entry.getOldPath();
                }
                List<DiffEntry> call = Collections.EMPTY_LIST;
                ChangedFiles changed = new ChangedFiles(oldPath, entry.getNewPath(), entry.getChangeType().name(), call);
                listaOfChangedFiles.add(changed);
            });
        } catch (RevisionSyntaxException | IOException | GitAPIException e) {
            throw new DiffException(e);
        }

        return listaOfChangedFiles;
    }

    @Override
    public Repository checkout(String commit) {
        try {
            buildRepository();
            git.checkout().setName(commit).call();

            //        Git git; - Git.class da API JGit / Originalmente o método retorna um Ref.class da mesma API
//            cloneRepository();
        } catch (GitAPIException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            Logger.getLogger(Git.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return git.checkout()
//                .setCreateBranch(true)
//                .setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK)
//                .setStartPoint("origin/" + commit)
//                .call();
        return repository;
    }

    /**
     * Imprime a diferença entre as versões em forma de texto
     *
     * @param repository {@link org.eclipse.jgit.lib.Repository} Repositorio
     * JGit
     * @param diff {@link DiffEntry} Objeto Diff
     */
//    private String showFileDiffs(org.eclipse.jgit.lib.Repository repository, DiffEntry diff) {
//        try {
//            DiffFormatter formatter = new DiffFormatter(System.out);
//            formatter.setRepository(repository);
//            formatter.setDiffComparator(RawTextComparator.DEFAULT);
//            formatter.setDetectRenames(true);
    //Filtrando apenas as modificações para efeito de testes
//            if (DiffEntry.ChangeType.MODIFY.equals(diff.getChangeType())) {
//                System.out.println(MessageFormat.format("[ {0} {1} {2} ]", diff.getChangeType().name(), diff.getNewMode().getBits(), diff.getNewPath()));
////                Listando as informações do arquivo
////                changesInFiles = formatter.toFileHeader(diff).getBuffer();
////                System.out.println(new String(changesInFiles));
//                EditList toEditList = formatter.toFileHeader(diff).toEditList();
//                toEditList.stream().forEach(new Consumer<Edit>() {
//                    @Override
//                    public void accept(Edit t) {
//                        System.out.println(MessageFormat.format("\tinfo sobre: {0}", t));
//                    }
//                });
//                formatter.format(diff);
//                System.out.println(diff);
//                System.out.println("\n\n\r");
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Git.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
////        String toString = new String(changesInFiles);
////        return toString;
//        return null;
//    }
//    public void showFileDiffs2(String file, AbstractTreeIterator oldTreeParser, AbstractTreeIterator newTreeParser) throws GitAPIException, IOException {
//        List<DiffEntry> diff = git.diff().
//                setOldTree(oldTreeParser).
//                setNewTree(newTreeParser).
//                setPathFilter(PathFilter.create(file)).
//                call();
//        for (DiffEntry entry : diff) {
////            System.out.println("Entry: " + entry + ", from: " + entry.getOldId() + ", to: " + entry.getNewId());
////            try (DiffFormatter formatter = new DiffFormatter(System.out)) {
////                formatter.setRepository(git.getRepository());
////                formatter.format(entry);
////                System.out.println("New Prefix: " + formatter.getNewPrefix());
////                System.out.println("Old Prefix: " + formatter.getOldPrefix());
////            }
//        }
//    }
    @Override
    public SCM setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public SCM setDir(String dir) {
        this.dir = new File(dir);
        return this;
    }

//    @Override
//    public SCM setScm(SCM scm) {
//        return scm.getReferenceRepository().scm();
//    }
//
    @Override
    public org.eclipse.jgit.lib.Repository getRepository() {
        return this.git.getRepository();
    }
}
