package br.edu.ifpb.scm.loads;

import br.edu.ifpb.scm.Git;
import br.edu.ifpb.scm.Repository;
import br.edu.ifpb.scm.git.GitImpl;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/06/2016, 23:30:10
 */
public class LoaderTests {

    public static void main(String[] args) {
        Supplier<String> s = () -> {
            return "";
        };

        Function<String, Integer> f = t -> Integer.parseInt(t);
        Integer apply = f.apply("3");

        Function<Integer, String> a = t -> String.valueOf(t);

        System.out.println(a.apply(13));

        StringJoiner sj = new StringJoiner("/", "[", "]");

        Optional<String> scope = Optional.ofNullable(null);//of("rmi:");
        Optional<String> app = Optional.of("ejb");
        Optional<String> module = Optional.ofNullable(null);//of("core");
//        scope.filter(t -> !t.equals(Scoped.EMPTY))
//                .ifPresent(t -> builder.append(scope.get()));
//        scope.ifPresent(sj::add);
//        app.ifPresent(sj::add);
//        module.ifPresent(sj::add);
        add(scope, sj);
        add(app, sj);
        add(module, sj);

//        System.out.println(sj.toString());
        StringBuilder builder = new StringBuilder("Select a From Aluno a");
        builder.append("where ");
        builder.append("a.nome like'%job'");
        builder.append(" and");

        System.out.println("builder: " + builder.toString());
        System.out.println("tamanho: " + (builder.length()));
        builder.delete((builder.length() - 5), builder.length());
        System.out.println("builder: " + builder.toString());
        System.out.println("tamanho: " + (builder.length()));
    }

    private static void add(Optional<String> module, StringJoiner sj) {
        module.ifPresent(sj::add);

    }

}
