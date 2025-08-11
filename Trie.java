import java.util.ArrayList;
import java.util.List;

public class Trie {
    private final TrieNode root= new TrieNode();

    public void insertNodeToTrie(String key){
        TrieNode curr = root;
        key=key.toUpperCase();
        for(char ch: key.toCharArray()){
            int index = ch - 'A';

            if(index < 0 || index >= 26) continue;

            if (curr.children[index] == null) { //
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];

            }
        curr.isEndOfWord =true;

        }
    public List<String> autoComplete(String prefix){
        List <String> results = new ArrayList<>();
        TrieNode node =root;
        prefix=prefix.toUpperCase();

        for (char ch: prefix.toCharArray()){
            int index = ch - 'A';

            if(index < 0 || index >= 26 || node.children[index]==null){
                return results;
            }
            node= node.children[index];
        }

        findWords(node, new StringBuilder(prefix),results);
        return results;

    }
    private void findWords(TrieNode node,StringBuilder current, List<String> results){
        if(node.isEndOfWord){
            results.add(current.toString());
        }

        for (int i=0; i<26 ; i++){
            TrieNode child = node.children[i];

            if (child != null){
                current.append((char)(i+'A'));
                findWords(child,current,results);

                current.deleteCharAt(current.length()-1);

            }
        }
    }

    }

