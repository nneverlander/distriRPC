import java.util.ArrayList;
import java.util.List;

/**
 * Created by adi on 9/21/15.
 */
public class Main {

    private static double SUMDIFF = 0;
    private static double ITERS = 1;

    public static void main(String[] args) throws InterruptedException {
        //log(-2>>>5);
        //log(Integer.toBinaryString(-2>>>5));
        //String a = "";
        //getPrecision();
        //System.out.println(SUMDIFF / ITERS);
        String s = "IYMECGOBDOJBSNTVAQLNBIEAOYIOHVXZYZYLEEVIPWOBB" +
                "OEIVZHWUDEAQALLKROCUWSWRYSIUYBMAEIRDEFYYLKODK" +
                "OGIKPHPRDEJIPWLLWPHRKYMBMAKNGMRELYDPHRNPZHBYJ" +
                "DPMMWBXEYOZJMYXNYJDQWYMEOGPYBCXSXXYHLBELLEPRD" +
                "EGWXLEPMNOCMRTGQQOUPPEDPSLZOJAEYWNMKRFBLPGIMQ" +
                "AYTSHMRCKTUMVSTVDBOEUEEVRGJGGPIATDRARABLPGIMQ" +
                "DBCFWXDFAWUWPPMRGJGNOETGDMCIIMEXTBEENBNICKYPW" +
                "NQBLPGIMQOELICMRCLACMV";
        List<String> l = getEveryNthLetter(9, s);
        //String s1 = "OUOASCTHVFCBSGOGCBUCTWQSOBRTWFSHVSACFBWBUVORROKBS RQZSOFOBRQCZRKWHVOQFWGDBSGGHVOHVWBHSROHHVSSBRCTGI AASFHVSMGSHTCFHVOHROMPFSOYHCGSSOAOBPSVSORSRHKSBHM WBOZZOBRPFOBFCRSOACBUHVSABSFJCIGKWHVSLQWHSASBHHVWG KOGHVSTWFGHHWASVSVORPSSBRSSASRCZRSBCIUVHCUCKWHVVWG ZCFRTOHVSFOBRVWGPFCHVSFGHCGSSHVSYWBUGXIGHWQSRCBSW HKOGHVSBWBHVMSOFCTGIAASFOBRHVSGSJSBHVCTPFOBGZWTSHV SAOBVORPSSBHOYSBCIHGWRSOGAOZZVCZRTOGHWBHVSVWZZGFCP PHVCIUVHVSKOGOKWZRZWBUVWGGKCFRGKCFBHCAOBQSFOMRSFH VSYWBUPSMCBRHVSKOZZWHAORSPFOBGGYWBDFWQYZSHCHVWBY CTWHVSFSASAPSFSRHVSVSOFHVHOZSGCZRBOBHCZRHVSAHVSKWZR ZWBUGKSFSQFISZASBGVSGOWRGZOJSFGOBRGZOMSFGOBRHVWSJS GHVSMQCBGCFHSRKWHVUWOBHGOBRUVCIZGGHCZSUWFZQVWZRF SBWBHVSRSORCTBWUVHOBRRFOBYPZCCRTFCADCZWGVSRVCFBGO BRHVSWFKCASBZOMKWHVHVSCHVSFGWBHVSZCBUBWUVHHCGWFS HSFFWPZSVOZTVIAOBQVWZRFSB";
        for (String str : l) {
            log(str);
            getMaxCorrelation(str);
        }
        //log(isSubString("abc", "abdabeaabc"));
        //log(isSubString2("abc", "abdabeaabc"));
    }

    private static List<String> getEveryNthLetter(int n, String s) {
        List<String> l = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < s.length();j+=9) {
                char c = s.charAt(j);
                sb.append(c);
            }
            l.add(sb.toString());
        }
        return l;
    }

    private static boolean isSubString2(String small, String big) {
        big.contains(small);
        int max = big.length() - small.length();
        outer: for (int i = 0; i <= max; i++) {
            int j=i;
            for(int k = 0; k < small.length(); k++) {
                if (big.charAt(j++) != small.charAt(k)) {
                    continue outer;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isSubString(String small, String big) {
        if (small == null || big == null) {
            return false;
        }
        if (small.equals("")) {
            return true;
        }
        List<Character> bigList = toList(big.toCharArray());
        List<Character> smallList = toList(small.toCharArray());
        List<Integer> indexList  = new ArrayList<>();
        for (char c : smallList) {
            int i = bigList.indexOf(c);
            indexList.add(i);
        }
        for (int i = 0; i < indexList.size() - 1; i++) {
            if ((indexList.get(i+1) - indexList.get(i)) != 1) {
                return false;
            }
        }
        return true;
    }

    private static List<Character> toList(char[] chars) {
        List<Character> list = new ArrayList<>();
        for (char c : chars) {
            list.add(c);
        }
        return list;
    }

    private static void getMaxCorrelation(String s) {

        float[] ratios = new float[26];
        double[] known = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2, 6.1, 7, 0.2, 0.8, 4, 2.4,6.7,7.5,1.9,0.1,6,6.3,9.1,2.8,1,2.4,0.2,2,0.1};
        s = s.toLowerCase();
        char[] ch = s.toCharArray();
        for (char c : ch) {
            if(c == ' ' || c == '\n') continue;
            ratios[c-'a']++;
        }
        for (int i=0; i<ratios.length;i++) {
            //log("index:" + i);
            //log(ratios[i]);
            ratios[i] /= s.length();
            ratios[i] = ratios[i]*100;
            //log(ratios[i]);
        }
        float maxCorr = 0, key = 0;
        for(int k = 0; k<26; k++) {
            int d = 0;
            for (int i = 0; i<26; i++) {
                d += ratios[(i+k)%26]*known[i];
            }
            if(d > maxCorr) {
                maxCorr = d;
                key = k;
            }
        }
        log("Max corr: " + maxCorr + " Key: " + key);
        StringBuilder sb = new StringBuilder();
        for (char c : ch) {
            char diff = (char) (c-'a'-key);
            if(diff <` 0) {
                diff += 26;
            }
            char c1 = (char) (diff + 'a');
            sb.append(c1);
        }
        log("Decrypted: " + sb.toString());

    }

    // This method returns values ranging fromm 380 to 8000 depending on the number of iterations used. Higher the number of ietrations, greater the precision.
    // Taking 7000 ns as the precision when iterations are 1.
    private static void getPrecision() {
        for (int i = 0; i < ITERS; i++) {
            long begin = CPUUtils.getCpuTime();
            long end = CPUUtils.getCpuTime();
            SUMDIFF += (end - begin);
        }
    }

    private static void log(Object... obj) {
        for (Object o : obj) {
            System.out.println(o);
        }
    }

}
