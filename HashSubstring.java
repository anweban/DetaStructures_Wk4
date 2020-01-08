package DataStructures_Wk4;
import java.util.*;

public class HashSubstring {

    public static void precomputeData(String text, List<Long> hashValsList, int patternLength){
        int x1 = -80 % 9;
        int x2 = 80 % 9;
        int x3 = 20 % 100;
        //System.out.println("Test");
        //System.out.println(x1+"   "+x2+"    "+x3);
        int x = 1;
        long y = 1;
        long p = 10000007l;
        //System.out.println("Substring --->"+ text.substring(text.length()-patternLength));
        Long[] hashVals = new Long[text.length()-patternLength +1];
        //hashVals.add(getHashVal(text.substring(text.length()-patternLength)));
        hashVals[0] = getHashVal(text.substring(text.length()-patternLength));
        //calculate x^patternLength
        for(int i = 1; i <= patternLength-1; i++){
            y = (y*x)%p;
        }
        //System.out.println("y--->"+y);
        for(int j=text.length()-patternLength-1; j>= 0; j--){
            int asciiVal1 = text.charAt(j);
            //System.out.println("asciVal1--->"+asciiVal1);
            int asciiVal2 = text.charAt(j+patternLength);
            //System.out.println("sub--->"+text.substring(j,j+patternLength));
            //System.out.println("asciVal2--->"+asciiVal2);
            long nextHash = hashVals[text.length()-patternLength - j -1];
            //System.out.println("nextHash--->"+nextHash);
            //System.out.println("(nextHash*x) --->"+(long)((nextHash*x)));
            //System.out.println("(asciiVal1)%p ---->"+(long)((asciiVal1)%p));
            //System.out.println("((y*asciiVal2)%p) ---->"+(long)((y*asciiVal2)%p));
            //System.out.println("((y*asciiVal2)%p)*x ---->"+(long)((y*asciiVal2)%p)*x);
            long hashVal = ((nextHash*x) + (asciiVal1)%p - ((y*asciiVal2)%p)*x)%p;
            //System.out.println("hashValLong--->"+hashVal);
            hashVals[text.length()-patternLength - j] = hashVal;
            //System.out.println("-------------FOR------------------------");
        }
        hashValsList.addAll(Arrays.asList(hashVals));
        Collections.reverse(hashValsList);
        /*System.out.println("-------------------------------------");
        for(long i:hashVals){
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println("-------------------------------------");*/
    }

    public static void searchOccurences(String text, String pattern){
        if(pattern.length() > text.length()){
            return;
        }
        List<Integer> positions = new ArrayList<Integer>();
        long p = 10000007l;
        long patternHash = getHashVal(pattern) % p;
        //System.out.println("patternHash --->"+patternHash);
        List<Long> hashVals = new ArrayList<>();
        precomputeData(text,hashVals,pattern.length());
        //System.out.println("here");
        for(int i = 0; i <= text.length() - pattern.length(); i++){
            if(patternHash == hashVals.get(i)){
               if(pattern.regionMatches(false,0,text, i, pattern.length())){
                   positions.add(i);
               }
            }
        }
        for(int i:positions){
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void searchOccurencesNaive(String text, String pattern){
        List<Integer> positions = new ArrayList<Integer>();
        for(int i = 0; i <= text.length() - pattern.length(); i++){
            if(pattern.regionMatches(false,0,text, i, pattern.length())){
                positions.add(i);
            }
        }
        System.out.println("---------------Naive----------------------");
        for(int i:positions){
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("-------------------------------------");
    }

    public static long getHashVal(String s){
        long p = 10000007l;
        int x = 1;
        long hash = 0;
        long y = 1;
        for(int i = 0; i < s.length(); i++){
            int asciiVal = s.charAt(i);
            //System.out.println("asciiVal----"+asciiVal);
            hash = hash + (asciiVal*y)%p;
            //System.out.println("terms--->"+(long)(asciiVal*y)%p);
            y = (y*x)%p;

        }
        return hash;
    }

    public static void main(String args[]){
        /*Scanner sc = new Scanner(System.in);
        String pattern = sc.next();
        String text = sc.next();
        searchOccurences(text, pattern);*/

        stressTest();
    }

    public static void stressTest(){
        for(int j=1; j<= 100; j++) {
            Random r = new Random();
            int patL = 15 + r.nextInt(3);
            int textL = 100000 + r.nextInt(5);


            String text = "";
            for (int i = 1; i <= textL; i++) {
                int flip = r.nextInt(2);
                char c;
                if (flip <= 2) {
                    c = (char) (97 + (int) flip);
                } else {
                    c = (char) (65 + (int) flip);
                }
                text += Character.toString(c);
            }
            String pattern = "";
            for (int i = 1; i <= patL; i++) {
                int flip = r.nextInt(2);
                char c;
                if (flip <= 2) {
                    c = (char) (97 + (int) flip);
                } else {
                    c = (char) (65 + (int) flip);
                }
                pattern += Character.toString(c);
            }
            System.out.println("textL--->" + textL);
            System.out.println("patL--->" + patL);
            System.out.println("text--->" + text);
            System.out.println("pattern--->" + pattern);
            searchOccurences(text, pattern);
            searchOccurencesNaive(text, pattern);
        }
    }
}
