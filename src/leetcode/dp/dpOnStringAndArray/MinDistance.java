package leetcode.dp.dpOnStringAndArray;

/**
 * @author - ZwZ
 * @date - 2021/1/10 - 16:18
 * @Description:583. 两个字符串的删除操作
 * 给定两个单词word1和word2，找到使得word1和word2相同所需的最小步数，
 * 每步可以删除任意一个字符串中的一个字符。
 * <p>
 * 示例：
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 * <p>
 * 提示：
 * 给定单词的长度不超过500。
 * 给定单词中的字符只含有小写字母。
 * <p>
 *
 *  相似题目：
 *      1143. 最长公共子序列LongestCommonSubsequence
 *      712. 两个字符串的最小ASCII删除和MinimumDeleteSum
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings
 */
public class MinDistance {
    /**
     * 暴力递归
     * 思路：
     * 用两个指针i,j从头开始比较两个字符串，word1[i]和word2[j]一共有两种情况：
     * 1. word1[i] == word2[j],此时这个位置上得元素对于最终的结果没有任何贡献，跳过
     * 2. word1[i] != word2[j],此时有三种情况：
     * 1) word1[i]在,word2[j]不在两个单词的最终构成的相同字符串里
     * 此时就要让j+1之后继续比较  (变成了一个子问题)
     * 2) word1[i]不在,word2[j]在  此时让i+1然后继续比较(又变成了一个子问题)
     * 3) word1[i]和word2[j]都不在  此时i+1,j+1
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance1(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        return dp1(word1, word2, 0, 0);
    }

    /**
     * 对暴力递归进行改进
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int[][] memo = new int[word1.length()][word2.length()];
        return dp2(word1, word2, memo, 0, 0);
    }

    /**
     * 动态规划
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance3(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        //dp[i][j]:word1[0...i-1]和word2[0...i-2]相同所需的最小步数
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                //初值
                if (i == 0 || j == 0) {
                    dp[i][j] = i + j;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    /**
     * 思路：
     *   求出两个字符串的最长公共子序列长度为n
     *   然后word1.length + word2.length - 2 * n则为本题想要的答案
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance4(String word1,String word2){

        //......

        return 0;
    }
    /**
     * 暴力递归函数
     *
     *
     * @param word1
     * @param word2
     * @param i     word1的下标
     * @param j     word2的下标
     * @return word1[0...i]和word2[0...j]使得word1和word2相同所需的最小步数
     */
    public int dp1(String word1, String word2, int i, int j) {
        //base case
        if (i == word1.length() && j == word2.length()) {
            return 0;
        }
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }
        if (word1.charAt(i) == word2.charAt(j)) {
            return dp1(word1, word2, i + 1, j + 1);
        } else {
            int res1 = dp1(word1, word2, i + 1, j) + 1;
            int res2 = dp1(word1, word2, i, j + 1) + 1;
            return Math.min(res1, res2);
        }
    }

    /**
     * 带备忘录的递归
     * 暴力递归中存在重复计算
     * dp(i,j)会在dp(i,j+1)和dp(i+1,j)两个过程中都计算一遍
     * @param word1
     * @param word2
     * @param memo
     * @param i
     * @param j
     * @return
     */
    public int dp2(String word1, String word2, int[][] memo, int i, int j) {
        //base case
        if (i == word1.length() && j == word2.length()) {
            return 0;
        }
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = dp2(word1, word2, memo, i + 1, j + 1);
        } else {
            int res1 = dp2(word1, word2, memo, i + 1, j) + 1;
            int res2 = dp2(word1, word2, memo, i, j + 1) + 1;
            memo[i][j] = Math.min(res1, res2);
        }
        return memo[i][j];
    }

    public static void main(String[] args) {
        MinDistance distance = new MinDistance();
        System.out.println(distance.minDistance1("sea", "eat"));
    }
}