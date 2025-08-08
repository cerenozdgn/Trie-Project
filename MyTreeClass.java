import java.io.*;
import java.util.List;
import java.util.Scanner;

public class MyTreeClass {
    public static void main(String[] args) {
        if(args.length!=1){
            System.out.println(" Java MyTree sozluk.txt");
            return;

        }
        Trie trie = new Trie();
        String filePath = args[0];
        System.out.println("Sözlük Yükleniyor. Lütfen Bekleyin...");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line=reader.readLine())!=null){
               trie.insertNodeToTrie(line.trim());
            }

        }catch (IOException e){
            System.out.println("Hata: " +e.getMessage());
            return;
        }
        System.out.println("Sözlük Yüklendi.");

        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println("Bir Kelime Yazıp Enter Tuşuna Basınız");
            String input = scan.nextLine().trim();

            if(input.isEmpty()) break;

            List<String> suggestions = trie.autoComplete(input);
            if (suggestions.isEmpty()){
                System.out.println("Uygun kelime bulunamadı.");
            }else {
                System.out.println("Olası Kelimeler");
                for (String word : suggestions){
                    System.out.println(word);
                }
            }
        }

    }
}
