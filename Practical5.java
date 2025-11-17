public class LCS_LRS {
    public static void lcs(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        int[][] dp = new int[m + 1][n + 1];
        char[][] dir = new char[m + 1][n + 1]; 

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    dir[i][j] = 'D';
                } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    dir[i][j] = 'U';
                } else {
                    dp[i][j] = dp[i][j - 1];
                    dir[i][j] = 'L';
                }
            }
        }
        System.out.println("Cost Matrix:");
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nDirection Matrix:");
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dir[i][j] + " ");
            }
            System.out.println();
        }

        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (dir[i][j] == 'D') {
                lcs.append(X.charAt(i - 1));
                i--; j--;
            } else if (dir[i][j] == 'U') {
                i--;
            } else {
                j--;
            }
        }

        lcs.reverse();
        System.out.println("\nFinal LCS length: " + dp[m][n]);
        System.out.println("LCS: " + lcs.toString());
    }
    public static void lrs(String S) {
        int n = S.length();
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (S.charAt(i - 1) == S.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder lrs = new StringBuilder();
        int i = n, j = n;
        while (i > 0 && j > 0) {
            if (S.charAt(i - 1) == S.charAt(j - 1) && i != j) {
                lrs.append(S.charAt(i - 1));
                i--; j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        lrs.reverse();
        System.out.println("Longest Repeating Subsequence: " + lrs.toString());
    }

    public static void main(String[] args) {
        String X = "AGCCCTAAGGGCTACCTAGCTT";
        String Y = "GACAGCCTACAAGCGTTAGCTTG";
        System.out.println("==== TASK 1: LCS ====");
        lcs(X, Y);
        System.out.println("\n==== TASK 2: LRS ====");
        lrs("AABCBDC");
    }
}
