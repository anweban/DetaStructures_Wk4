package DataStructures_Wk4;
import java.util.*;

public class HashChains {
    public static class HashTble {
        private int m;
        private List<LinkedList<String>> hMap;

        public HashTble(int m){
            this.m = m;
            this.hMap = new ArrayList<LinkedList<String>>();
        }

        private int getHashValueOfString(String s, long p, int x) {
            long hash = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                int asciiVal = s.charAt(i);
                hash = ((hash * x) + asciiVal) % p;
            }
            return (int) hash % m;
        }

        public Boolean containsKey(String s) {
            int hash = getHashValueOfString(s,1000000007, 263);
            if(hash <= hMap.size()-1){
                for(String st: hMap.get(hash)){
                    if(st.equals(s)){
                        return true;
                    }
                }
            }
            return false;
        }

        public void add(String s){
            int hash = getHashValueOfString(s,1000000007, 263);
            //System.out.println("hash--->"+hash);
            //System.out.println("size--->"+hMap.size());
            if(hash <= hMap.size()-1){
                for(String st: hMap.get(hash)){
                    if(st.equals(s)){
                        return;
                    }
                }
                hMap.get(hash).addFirst(s);
            }else{
                int size  = hMap.size();
                for(int i = 1; i <= hash - (size - 1); i++){
                    //System.out.println("i-->"+i);
                    hMap.add(new LinkedList<String>());
                }
                //System.out.println("size2--->"+hMap.size());
                hMap.get(hash).addFirst(s);
            }
        }

        public void delete(String s) {
            int hash = getHashValueOfString(s, 1000000007, 263);
            if (hash <= hMap.size() - 1) {
                for (ListIterator<String> it = hMap.get(hash).listIterator(); it.hasNext(); ) {
                    if (it.next().equals(s)) {
                        it.remove();
                        return;
                    }
                }
            }
        }

        public void check(int n){
            //System.out.println("hMap size--->"+hMap.size());
            //System.out.println(n);
            if(n <= hMap.size()-1){
                for(String st:hMap.get(n)){
                    System.out.print(st+" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        HashTble hashTable = new HashTble(m);
        int queries = sc.nextInt();
        for(int i=0; i<queries; i++){
            String queryType = sc.next();
            if(queryType.equals("add")){
                String name = sc.next();
                hashTable.add(name);
            }else if(queryType.equals("del")){
                String name = sc.next();
                hashTable.delete(name);
            }else if(queryType.equals("find")){
                String name = sc.next();
                if(hashTable.containsKey(name)){
                    System.out.println("yes");
                }else{
                    System.out.println("no");
                }
            }else if(queryType.equals("check")){
                int n = Integer.parseInt(sc.next());
                //System.out.println(n);
                hashTable.check(n);
            }
        }
    }
}
