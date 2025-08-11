#  Tree–Trie Sözlük Otomatik Tamamlama (Java)

Bu proje, verilen bir **sözlük dosyasını** (satır başına bir kelime) **Trie** veri yapısına (düğümlerde karakter tutarak) yükler ve kullanıcının girdiği ön eke (prefix) uyan tüm olası kelimeleri **otomatik tamamlama** şeklinde listeler.

> **Örnek çalışma akışı**
>
> ```
> Java MyTree sozluk.txt
> Sözlük Yükleniyor. Lütfen Bekleyin...
> Sözlük Yüklendi.
> Bir Kelime Yazıp Enter Tuşuna Basınız
> BROWSA
> Olası Kelimeler
> BROWSABLE
> BROWSABLES
> Bir Kelime Yazıp Enter Tuşuna Basınız
> ...
> ```

---

##  Mimari

- **TrieNode** → 26 harf için çocuk referansı (`A`–`Z`) ve `isEndOfWord` bayrağı tutar.
- **Trie** → Kelime ekleme (`insertNodeToTrie`), otomatik tamamlama (`autoComplete`) ve DFS ile sonuç toplama (`findWords`).
- **MyTree** → Dosyayı okur, BufferedReader ile sözlüğü satır satır okuyup trie.insertNodeToTrie(...) çağırır. , kullanıcıdan prefix alır ve sonuçları listeler.

---

##  Neden Trie?

- Prefix araması **O(L)** (L: prefix uzunluğu) karmaşıklıkla çalışır.
- Ortak ön ekler bellekte paylaşılır, yer tasarrufu sağlar.
- Otomatik tamamlama için prefix düğümünden tüm kelimeleri kolayca toplayabiliriz.

---

##  Neden `List<String>` kullandım?

Otomatik tamamlama sonuçlarını **sıralı** şekilde tutmak ve kolayca yazdırmak için `List<String>` en uygun seçim.  
DFS ile ürettiğimiz sonuçlar ekleme sırasını koruyor ve tekrar üretim olmadığından `List` yeterli oluyor.  

## Alternatifler:

- ArrayDeque<String>: BFS sırasında kuyruk olarak.

- PriorityQueue<String>: Alfabetik/skorlu en iyi N öneri gerekiyorsa.

- TreeSet<String>: Benzersiz ve sürekli sıralı akış istenirse.

- LinkedList<String>: Sık baş/son eklemelerde; ama indeks erişimi yavaş.

---

##  Karmaşıklık

- **Ekleme:** O(L) — L: kelime uzunluğu
- **Tam Arama:** O(L)
- **Autocomplete:** O(P + K) — P = önek uzunluğu, K = alt ağaçta dolaşılan toplam karakter/düğüm
- **Bellek:** O(∑Lᵢ) — tüm kelime uzunluklarının toplamına orantılı

---

##  Proje Yapısı
```bash
src/
├── MyTreeClass.java # main: dosya yükleme + kullanıcı etkileşimi
├── Trie.java # ekleme, arama, DFS/backtracking
└── TrieNode.java # 26 çocuk ve kelime bitiş bayrağı
```


---

##  Gereksinimler

- Java 21+
- Sözlük dosyası (`sozluk.txt`) — her satırda bir kelime olacak şekilde

---

## Kullanım 
- Programı çalıştırın.
- İstediğiniz prefix’i yazın.
- Listeyi görün.
- Boş satır girerseniz program kapanır.








