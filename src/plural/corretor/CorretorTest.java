package plural.corretor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by gabri on 21/05/2017.
 */
public class CorretorTest {
    private static Corretor corretor;

    private static void setup() throws Exception {
        //Path path = Paths.get("C:/tfs_plural/corretor/res/wordlist-big-latest.txt");
        String sPath = ClassLoader.getSystemResource("wordlist-big-latest.txt").getPath();
        sPath = sPath.substring(0,1).equals("/")?sPath.substring(1):sPath;
        Path path = Paths.get(sPath);
        corretor = new Corretor(path);
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        setup();
        System.out.println("Words loaded in " + (System.currentTimeMillis() - start) / 1000. + "s ");
        start = System.currentTimeMillis();

        if (args.length == 0)
            args = new String[]{"ouvindo", "ovindo", "oto", "arm", "sorbet", "sebola", "aufasse", "aufaçe", "aumoço"};
        Stream.of(args).forEach((word) -> {
            long startword = System.currentTimeMillis();
            System.out.print("Corrigindo palavra '" + word + "': ");
            corretor.Corrige(word).parallelStream().forEach((a) -> System.out.print(a + ", "));
            System.out.println("\nDone in " + (System.currentTimeMillis() - startword) / 1000. + "s ");
        });
        System.out.println("Done in " + (System.currentTimeMillis() - start) / 1000. + "s ");
    }
}
