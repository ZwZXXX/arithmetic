package leetcode.easy;

/**
 * @author - ZwZ
 * @date - 2021/3/22 - 20:57
 * @Description:191. 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * <p>
 * 提示：
 * <p>
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的示例 3中，输入表示有符号整数 -3。
 * <p>
 * 示例 1：
 * <p>
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011中，共有三位为 '1'。
 * 示例 2：
 * <p>
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000中，共有一位为 '1'。
 * 示例 3：
 * <p>
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-1-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HammingWeight {
    /**
     * 暴力
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        if (n == 0) return 0;
        int sum = 0;
        while (n != 0) {
            if (n == 0) {
                return sum;
            }
            if ((n & 1) != 0) {
                sum++;
            }
            n = n >> 1;
        }
        return sum;
    }

    /**
     * n & (n−1)
     * 其预算结果恰为把n的二进制位中的最低位的1变为0后的结果
     * 每次运算会使得 n的最低位的 1 被翻转，
     * 因此运算次数就等于 n 的二进制位中 1 的个数
     *
     * 时间复杂度：O(logn)。循环次数等于 n 的二进制位中 1 的个数，
     * 最坏情况下 n 的二进制位全部为 1。我们需要循环 logn 次
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/number-of-1-bits/solution/wei-1de-ge-shu-by-leetcode-solution-jnwf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int hammingWeight2(int n) {
        if (n == 0) return 0;
        int sum = 0;
        while (n != 0) {
            n = n & (n - 1);
            sum++;
        }
        return sum;
    }
}
