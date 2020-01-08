package DataStructures_Wk4;
import java.util.*;

public class substring_equality {


    public static void getHashesForString(String st, long arr[]){
        arr[0] = 0;
        int x = 5;
        long p = 1000000000007l;
        for(int i = 0; i < st.length(); i++){
            int cur_letter = st.charAt(i);
            //System.out.println("char_"+i+"----->"+cur_letter);
            arr[i+1] = (arr[i]*x + cur_letter)%p;
        }

        /*for(int i = 0; i<arr.length; i++){
            //System.out.println(i+"----->"+arr[i]);
        }*/
    }

    public static void results(String st, int[][] arr, long[] hashes){
        System.out.println("st--->"+st);
        int x = 5;
        long p = 1000000000007l;
        long m1 = 1000000007;
        long m2 = 1000000013;
        for(int i = 0; i< arr.length; i++){
            System.out.println("a--->"+arr[i][0]);
            System.out.println("b--->"+arr[i][1]);
            System.out.println("l--->"+arr[i][2]);
            long y = 1;
            for(int j=1; j<= arr[i][2]; j++){
                y = (y*x)%p;
            }
            //System.out.println("hashes[arr[i][0]+arr[i][2]--->"+hashes[arr[i][0]+arr[i][2]] );
            //System.out.println("hashes[arr[i][0]---->"+hashes[arr[i][0]] );
            long hash_a = (hashes[arr[i][0]+arr[i][2]] - y*hashes[arr[i][0]])%p;
            //System.out.println("hash_a---->"+hash_a );
            hash_a = (hash_a + p)%p;
            //System.out.println("hash_a---->"+hash_a );

            //System.out.println("hashes[arr[i][1]+arr[i][2]--->"+hashes[arr[i][1]+arr[i][2]] );
            //System.out.println("hashes[arr[i][1]---->"+hashes[arr[i][1]] );
            long hash_b = (hashes[arr[i][1]+arr[i][2]] - y*hashes[arr[i][1]])%p;
            //System.out.println("hash_b---->"+hash_b );
            hash_b = (hash_b + p)%p;
            //System.out.println("hash_b---->"+hash_b );

            Boolean nyes = false;
            if(st.substring(arr[i][0],arr[i][0]+arr[i][2]).equals(st.substring(arr[i][1],arr[i][1]+arr[i][2]))){
                nyes = true;
            }

            if(((hash_a % m1)+ m1)%m1 == ((hash_b % m1)+ m1)%m1){
                if(((hash_a % m2)+ m2)%m2 == ((hash_b % m2)+ m2)%m2){
                    System.out.println("Yes");
                    if(!nyes){
                        System.out.println("---------------------------------------");
                        System.out.println("Problem!!!!!");
                        System.out.println("---------------------------------------");
                    }
                    continue;
                }
            }
            System.out.println("No");
            if(nyes){
                System.out.println("---------------------------------------");
                System.out.println("Problem!!!!!");
                System.out.println("---------------------------------------");
            }
        }
    }

    public static void main(String args[]){
        /*Scanner sc = new Scanner(System.in);
        String str = sc.next();
        long arr[] = new long[str.length()+1];
        getHashesForString(str,arr);
        int numQueries = sc.nextInt();
        int[][] queries_arr = new int[numQueries][3];
        for(int i = 0; i< numQueries; i++){
            queries_arr[i][0] = sc.nextInt();
            queries_arr[i][1] = sc.nextInt();
            queries_arr[i][2] = sc.nextInt();
        }
        results(str,queries_arr,arr);*/

        stressTest();

    }

    public static void stressTest(){
        List<List<Integer>> testList = new ArrayList<List<Integer>>();
        for(int i = 0; i<15; i++){
            for(int j = 0; j<15; j++){
                List<Integer> intList = new ArrayList<>();
                intList.add(i);
                intList.add(j);
                intList.add(15 - Math.max(i,j));
                testList.add(intList);
            }
        }
        int[][] arr = new int[testList.size()][3];
        for(int i = 0; i<testList.size(); i++){
            arr[i][0] = testList.get(i).get(0);
            arr[i][1] = testList.get(i).get(1);
            arr[i][2] = testList.get(i).get(2);
        }

        String str = "abacabadabacaba";
        long arr1[] = new long[str.length()+1];
        getHashesForString(str,arr1);
        results(str,arr,arr1);
    }


}

