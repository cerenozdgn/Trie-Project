import java.util.ArrayList;
import java.util.List;

public class Trie {
    private final TrieNode root= new TrieNode();

    // Kelimeyi Trie'ye ekleyen method
    public void insertNodeToTrie(String key){
        TrieNode curr = root; // en baştan (kök) başladık
        key=key.toUpperCase(); // Küçük/büyük harf farkını kaldır (case-insensitive yapmak için)
        for(char ch: key.toCharArray()){
            int index = ch - 'A'; //// 'A' harfi 0, 'B' harfi 1, ..., 'Z' harfi 25 olacak şekilde ASCII hesaplama
            if(index < 0 || index >= 26) continue;
            // Eğer karakter A–Z aralığında değilse (örneğin noktalama işareti gibi), onu atla

            if (curr.children[index] == null) { //
                curr.children[index] = new TrieNode();
                // Bu harfe ait bir alt düğüm yoksa, yeni bir düğüm oluştur
            }
            curr = curr.children[index];
            // Mevcut harfe karşılık gelen alt düğüme ilerle
            }
        curr.isEndOfWord =true;
        // Kelimenin sonuna geldik, bu düğüm artık bir kelimenin tamamlanmış hali

        }
    public List<String> autoComplete(String prefix){
        List <String> results = new ArrayList<>();// bulunan sonuçları bu arrayliste atacağız
        TrieNode node =root;//yine kökten başlayarak prefix'i içeren düğümlere ilerle
        prefix=prefix.toUpperCase();

        for (char ch: prefix.toCharArray()){
            int index = ch - 'A';

            // Geçerli karakter için geçerli bir düğüm var mı?
            if(index < 0 || index >= 26 || node.children[index]==null){
                return results;
                // Eşleşme yoksa boş liste döndür (autocomplete başarısız)
            }
            node= node.children[index];// sonraki düğüme geçer
        }
        // Artık prefix'in son harfinin olduğu düğümdeyiz.
        // Buradan sonra tüm olası kelimeleri ara.
        findWords(node, new StringBuilder(prefix),results);
        return results; // sonuçları döndür

    }
    private void findWords(TrieNode node,StringBuilder current, List<String> results){
        if(node.isEndOfWord){
            results.add(current.toString());
            // Bu düğümde kelime sonu işareti varsa, o ana kadar biriken karakterleri sonuçlara ekle
        }

        for (int i=0; i<26 ; i++){
            TrieNode child = node.children[i];
            //A'dan Z'ye kadar tüm olası çocuk düğümleri kontrol et

            if (child != null){
                current.append((char)(i+'A')); // i=0 → 'A', i=1 → 'B' vb. Harfi current StringBuilder'a ekle
                findWords(child,current,results);
                //Bu children node'dan devam ederek arama yap (recursive DFS)
                current.deleteCharAt(current.length()-1);
                // Geriye dön (backtrack), eklediğin harfi çıkar ki diğer dallara bakabilesin
            }
        }
    }

    }

