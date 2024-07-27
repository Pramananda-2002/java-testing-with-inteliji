package server.dataStructure.constantTime;
public class Algorithms {
    private int matchedCost;
    private int replacedCost;
    private int removeCost;
    private int insertCost;

    public Algorithms() {
        matchedCost = 0;
        replacedCost = 1;
        removeCost = 1;
        insertCost = 1;

    }

    Algorithms(int matchedCost, int replacedCost, int removeCost, int insertCost) {
        this.matchedCost = matchedCost;
        this.replacedCost = replacedCost;
        this.removeCost = removeCost;
        this.insertCost = insertCost;

    }

    public int LevenshteinEditDistance(String word1, String word2) {
        final int n = word1.length();
        final int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + matchedCost;
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + replacedCost,
                            Math.min(dp[i - 1][j] + removeCost, dp[i][j - 1] + insertCost));
            }
        }
        return dp[n][m];
    }

}
