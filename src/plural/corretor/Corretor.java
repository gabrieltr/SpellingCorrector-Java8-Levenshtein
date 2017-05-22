package plural.corretor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Gabriel Lucas de Toledo Ribeiro.
 */
public class Corretor {

    int min = 999;
    int limitrofe = 100;

    private HashMap<String, Integer> dict = new HashMap<>();

    public Corretor(Path path) throws Exception {
        System.out.println("Carrango banco de dados: " + path.toAbsolutePath() + "!");
        if (!Files.exists(path))
            throw new Exception("Base de dados nao existe! (" + path.toAbsolutePath() + ")");
        Stream.of(new String(Files.readAllBytes(path)).toLowerCase().split("[\\r?\\n]+"))
                .forEach((word) -> dict.compute(word, (k, v) -> v == null ? 1 : v + 1));
        System.out.println("Dicionario aberto com " + dict.size() + " palavras.");
    }

    public List<String> Corrige(String word) {
        List<String> retorno = new ArrayList<String>();

        if (dict.containsKey(word)) {
            retorno.add(word);
        } else {
            //calcula a distancia entre a palavra passada e todas as palavras do dicionario

            //Stream.of(dict).map(a -> a).parallel().forEach((str) ->
            //        str
            synchronized (dict) {
                //dict.keySet().parallelStream().forEach(s -> dict.compute(s, (s1, i) -> i = min));
                dict.keySet().forEach(s ->
                        dict.compute(s,
                                (w, i) -> i = LevenshteinDistance.computeLevenshteinDistance(w, word)
                        )
                );
                //min=dict.values().parallelStream().reduce(dict.size(), (a,c)->Math.min(a,c));
                dict.values().forEach((c)->min=Math.min(min,c));
                System.out.println("heuristica minima: "+min+"\t");
                //dict.keySet().parallelStream().forEach(s -> min = Math.min(dict.get(s), min);
                
                dict.keySet().parallelStream().filter(a -> dict.get(a) <= min)
                        .forEach(k -> retorno.add(k));
            
                
            }
        }
        return retorno;
    }
}
