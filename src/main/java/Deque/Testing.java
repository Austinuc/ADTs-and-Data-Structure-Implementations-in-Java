package Deque;

public class Testing {

    public String isPossible(int a, int b, int c, int d) {
        if (a < 1 || b < 1 || c < 1 || d < 1) return "No";
        if (a > c || b > d) return "No";
        if (a == c && b == d) return "Yes";

        if (a+b <= d)
            return isPossible(a, a+b,c,d);
        else
            return isPossible(a+b, b, c, d);

    }
    public int gPali(String input) {
        double count = 0;
        for (int i = 0; i < input.length(); i++) {
            String l = ""+input.charAt(i);
            for (int j = i+1; j < input.length(); j++) {
                if (l.length() == 5) {
                    count += isPalindrome(l) ? 1 : 0;
                }
                if (l.length() > 5) break;
                l += input.charAt(j);
            }
        }
        return (int) (count % (Math.pow(10, 9) + 7));
    }
    double count = 0;
    public int getPalindromesCount(String input, int start, int stop) {
        if (stop >= input.length()) return 0;
        if (stop - start + 1 == 5) {
            if (isPalindrome(input.substring(start, stop))) {
                return 1;
            } else return 0;
        }

//        for (int i = 0; i < input.length(); i++) {
            count += getPalindromesCount(input, start, stop + 1);
//        }

        return (int) (count % (Math.pow(10, 9) + 7));
    }

    private boolean isPalindrome(String substring) {

        StringBuilder sb = new StringBuilder(substring);
        String rev = sb.reverse().toString();
        return substring.equals(rev);
    }

    public static int getPalindromesCount(String s) {
        int k = 5;
        if (k == 0) {
            return 1;
        }
        if (k > s.length()) {
            return 0;
        }
        int n0 = 0;
        int n1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                n0++;
            } else {
                n1++;
            }
        }
        int count = 0;
        if (k % 2 == 0) {
            count += (int)Math.pow(n0, k/2) * (int)Math.pow(n1, k/2);
        } else {
            count += (int)Math.pow(n0, (k-1)/2) * (int)Math.pow(n1, (k+1)/2);
            count += (int)Math.pow(n0, (k+1)/2) * (int)Math.pow(n1, (k-1)/2);
        }
        return count % (int)(Math.pow(10, 9) + 7);
    }

    public static void main(String[] args) {
        Testing testing = new Testing();
        //1,2,3,6

        System.out.println(testing.isPossible(1,2,3,6));

//        System.out.println(testing.gPali("010110"));
        System.out.println(getPalindromesCount("010110"));
    }
}
