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
import java.time.ZoneId;
import java.util.ArrayList;
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

    private Repository repo;
    private String url;
    private File dir;

    public Git() {
    }

    @Override
    public Repository cloneRepository() {
        if (!dir.exists() && !dir.isDirectory()) {
            org.eclipse.jgit.api.Git git = null;
            try {
                git = org.eclipse.jgit.api.Git.cloneRepository().setURI(url).setDirectory(dir).call();
                repo.AddAllVersions(versions(git));
            } catch (GitAPIException e) {
                throw new SCMException("Não foi possível realizar um clone do repositório.", e);
            }
            org.eclipse.jgit.lib.Repository repository = git.getRepository();
            repo = createRepository(dir, repository);

            return repo;
        }
        return this.getRepository();
    }
//Esse metodo não está mais sendo usado mais, mas deixei, caso voltemos a precisar dele
//    private void findChangedFiles(org.eclipse.jgit.api.Git git) throws IOException, GitAPIException {
//        for (Version v : repo.getVersions()) {
//            List<ChangedFiles> changedFiles = getChangedFilesFromSpecifiedVersion(git.getRepository(), v.getHashCode());
//            v.setChanges(changedFiles);
//        }
//    }

    @Override
    public Repository getRepository() throws SCMException {
        try {
            org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.open(dir);
//        repo = new Repository(dir.getCanonicalPath(), getUrlFromLocalRepository(git.getRepository()));
            repo = createRepository(dir, git.getRepository());
            repo.AddAllVersions(versions(git));
            return repo;
        } catch (IOException e) {
            throw new SCMException("Erro ao recuperar referencia do repositório.", e);
        }
    }

    /**
     * Recupera a lista de versões
     *
     * @param git Git JGit
     * @return List {@link List} de {@link Version}
     */
    private List<Version> versions(org.eclipse.jgit.api.Git git) {
        List<Version> list = new ArrayList();
        LogCommand log = git.log();
        try {
            log.call().forEach(rc -> {
                list.add(createVersion(git.getRepository(), rc));
            });
        } catch (GitAPIException e) {
            throw new SCMException("Não foi ppossível recuperar as versões", e);
        }

        return list;
    }

    @Override
    public Repository checkout(String commit) {
//        Git git; - Git.class da API JGit / Originalmente o método retorna um Ref.class da mesma API 
//        return git.checkout().
//                setCreateBranch(true).
//                setName(check).
//                setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).
//                setStartPoint("origin/" + check).call();
        return null;
    }

    @Override
    public Repository checkoutByCommit(String hash) {

//        Git git; - Git.class da api JGit  / Originalmente o método retorna um Ref.class da mesma API 
//         git.checkout().setName(check).
//                setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).
//                setStartPoint("origin/" + check).
//                call();
//        return git.checkout().setName(check).setName("master").call();
//        git.checkout().setCreateBranch(true).setName("new-branch").setStartPoint(check).call();
//                git.checkout().
//                setCreateBranch(true).
//                setName("master").
//                setStartPoint(check).
//                call();
        return null;
    }

    /**
     * Recupera a url do repositorio remoto
     *
     * @param repository {@link org.eclipse.jgit.lib.Repository} JGit
     * @return {@link String} Url do repositorio remoto
     */
    private String getUrlFromLocalRepository(org.eclipse.jgit.lib.Repository repository) {
        Config config = repository.getConfig();
        return config.getString("remote", "origin", "url");
    }

    /**
     * Cria um objeto versão
     *
     * @param repo {@link org.eclipse.jgit.lib.Repository} JGit
     * @param it RevCommit JGit
     * @return {@link Version} Versão
     */
    private Version createVersion(org.eclipse.jgit.lib.Repository repo, RevCommit it) {
        List<ChangedFiles> changedFiles = getChangedFilesFromSpecifiedVersion(repo, String.valueOf(it.getId()).substring(7, 47));
        return new Version(it.getCommitterIdent().getWhen().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                String.valueOf(it.getId()).substring(7, 47),
                it.getShortMessage())
                .setChanges(changedFiles);
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
    private Repository createRepository(File dir, org.eclipse.jgit.lib.Repository repository) {
        try {
            return new Repository(dir.getCanonicalPath(), getUrlFromLocalRepository(repository));
        } catch (SecurityException e) {
            throw new AuthorizationException("Verifique as permissões de acesso a pastas de seu computador.", e);
        } catch (IOException e) {
            throw new ReferenceException("Ocorreu um erro, tente novamente mais tarde", e);
        }
    }

    /**
     *
     * @param repository {@link org.eclipse.jgit.lib.Repository} Repositorio
     * JGit
     * @param commit String hashCode do commit
     * @return {@link RevCommit} RevCommit
     */
    private RevCommit convertStringToRevCommit(org.eclipse.jgit.lib.Repository repository, String commit) {
        RevWalk rev = new RevWalk(repository);
        rev.reset();
        //Transformando o hash commit em um Objeto RevCommit
        try {
            ObjectId obj = repository.resolve(commit);
            return rev.parseCommit(obj);
        } catch (IncorrectObjectTypeException e) {
            throw new ConvertionException("Erro de Conversão do tipo String em RevCommit", e);
        } catch (RevisionSyntaxException | IOException e) {
            throw new ConvertionException("Erro de conversão do tipo String em RevCommit", e);
        }
    }

    /**
     * Recupera a difereça entre as versões
     *
     * @param repository {@link org.eclipse.jgit.lib.Repository} Repositorio
     * JGit JGit
     * @param rev1 {@link RevCommit} RevCommit
     * @param rev2 {@link RevCommit} RevCommit
     * @return {@link List} List de {@link Version}
     */
    private List<ChangedFiles> searchDiff(org.eclipse.jgit.lib.Repository repository, RevCommit rev1, RevCommit rev2) {
        List<ChangedFiles> listaOfChangedFiles = new ArrayList<>();
        try {
            ObjectId oldHead = repository.resolve(rev2.getTree().getName());
            ObjectId head = repository.resolve(rev1.getTree().getName());

            ObjectReader reader = repository.newObjectReader();
            CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
            oldTreeIter.reset(reader, oldHead);
            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
            newTreeIter.reset(reader, head);

            org.eclipse.jgit.api.Git g = new org.eclipse.jgit.api.Git(repository);
            List<DiffEntry> diffs = g.diff()
                    .setNewTree(newTreeIter)
                    .setOldTree(oldTreeIter)
                    .call();

            diffs.iterator().forEachRemaining(entry -> {
                ChangedFiles changed = new ChangedFiles(entry, entry.getChangeType().name());
                listaOfChangedFiles.add(changed);
                showFileDiffs(repository, entry);
            });
        } catch (RevisionSyntaxException | IOException | GitAPIException e) {
            throw new DiffException(e);
        }
        return listaOfChangedFiles;
    }

    /**
     * Imprime a diferença entre as versões em forma de texto
     *
     * @param repository {@link org.eclipse.jgit.lib.Repository} Repositorio
     * JGit
     * @param diff {@link DiffEntry} Objeto Diff
     */
    private void showFileDiffs(org.eclipse.jgit.lib.Repository repository, DiffEntry diff) {
        System.out.println(diff);
        DiffFormatter formatter = new DiffFormatter(System.out);
        formatter.setRepository(repository);
        try {
            formatter.format(diff);

        } catch (IOException ex) {
            throw new DiffException("Não foi possível mostrar a diferença entre os arquivos alterados.", ex);
        }
    }

    /**
     * Recupera a referencia do RevCommit
     *
     * @param repository {@link org.eclipse.jgit.lib.Repository} Repositorio
     * JGit
     * @param commit String
     * @return List {@link List} de de {@link ChangedFiles}
     */
    private List<ChangedFiles> getChangedFilesFromSpecifiedVersion(org.eclipse.jgit.lib.Repository repository, String commit) {
        RevCommit revCommit1 = convertStringToRevCommit(repository, commit);
        //Fluxo alternativo quando chegar no primeiro commit
        if (revCommit1.getParentCount() <= 0) {
            //return searchDiff(repository, revCommit1, revCommit1);
            return searchDiff(repository, revCommit1, revCommit1);
        }
        //trocado pela chamada de metodos
        RevCommit revCommit2 = convertStringToRevCommit(repository, revCommit1.getParents()[0].getName());
        return searchDiff(repository, revCommit1, revCommit2);
    }

    @Override
    public SCM setUrl(String url
    ) {
        this.url = url;
        return this;
    }

    @Override
    public SCM setDir(String dir
    ) {
        this.dir = new File(dir);
        return this;
    }

}