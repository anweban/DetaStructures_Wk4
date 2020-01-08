package DataStructures_Wk4;

import java.util.*;

public class Phone_Book {
    public static class HTable{
        List<List<NumberNameMapper>> hMap;
        Integer a,b;

        public HTable(){
            hMap = new ArrayList<List<NumberNameMapper>>();
        }

        public String find(int number) {
            Integer hashVal = containsChain(number);
            if (hashVal == null) {
                return null;
            } else {
                if (hMap.get(hashVal).isEmpty()) {
                    return null;
                } else {
                    for (NumberNameMapper ob : hMap.get((int) hashVal)) {
                        if (ob.phoneNumber == number) {
                            return ob.name;
                        }
                    }
                    return null;
                }
            }
        }

        public void add(int number, String name){
            //System.out.println("number--->"+number);
            //System.out.println("name--->"+name);
            Integer hashVal = containsChain(number);
            //System.out.print("hashVal-->"+hashVal);
            NumberNameMapper obj = new NumberNameMapper(number,name);
            if(hashVal == null){
                int size = hMap.size();
                int hash = getHashValueOfInteger(number,1000003, 1000);
                //System.out.println("hash-->"+hash);
                for(int i = 1; i <= hash-(size-1); i++){
                    List<NumberNameMapper> emptyList= new ArrayList<>();
                    hMap.add(emptyList);
                }
                hMap.get(hash).add(obj);
            }else{
                for (NumberNameMapper ob : hMap.get(hashVal)) {
                    if (ob.phoneNumber == number) {
                        ob.name = obj.name;
                        return;
                    }
                }
                    hMap.get(hashVal).add(obj);
            }
        }

        public void delete(int number) {
            Integer hashVal = containsChain(number);
            if(hashVal == null){
                return;
            }else{
                for(ListIterator<NumberNameMapper> iterator = hMap.get(hashVal).listIterator(); iterator.hasNext();){
                    NumberNameMapper ob = iterator.next();
                    if(ob.phoneNumber == number){
                        //iterator.previous();
                        iterator.remove();
                    }
                }
            }
        }

        public Integer containsChain(int number){
            int hashVal = getHashValueOfInteger(number, 1000003, 1000);
            if(hMap.size()-1 < hashVal){
                return null;
            }else{
                return hashVal;
            }
        }



        public int getHashValueOfInteger(int number, int p, int m){
            if(a == null || b == null) {
                Random random = new Random();
                if(a == null){
                    a = 1 + random.nextInt(p);
                }
                if(b == null){
                    b = random.nextInt(p);
                }
            }
            //System.out.println("a--->"+a);
            //System.out.println("b--->"+b);
            long r = (long) a * (long) number + (long) b;
            //System.out.println("r--->"+r);
            //System.out.println("r % p--->"+r % p);
            return (int) (r % p) % m;
        }
    }

    public static class NumberNameMapper{
        private int phoneNumber;
        private String name;
        public NumberNameMapper(int number, String name){
            this.phoneNumber = number;
            this.name = name;
        }

    }
    public static void main(String args[]){
        //System.out.println(((964162*17239 + 134685)%1000003)%1000);
        Scanner sc = new Scanner(System.in);
        int queries = sc.nextInt();
        HTable hTable = new HTable();
        for(int i=0; i<queries; i++){
            String queryType = sc.next();
            int phoneNum = sc.nextInt();
            if(queryType.equals("find")){
                String n = hTable.find(phoneNum);
                if(n == null) {
                    System.out.println("not found");
                }else{
                    System.out.println(n);
                }
            }else if(queryType.equals("del")){
                hTable.delete(phoneNum);
            }else if(queryType.equals("add")){
                String name = sc.next();
                hTable.add(phoneNum,name);
            }
        }
    }
}
