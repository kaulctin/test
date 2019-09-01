package cn.aulati.test.leetcode.solution;

import cn.aulati.test.model.ListNode;
import cn.aulati.test.model.TreeNode;

import java.util.*;

public class Solution2 {
    /**
     * 1171. 从链表中删去总和值为零的连续节点
     * 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
     *
     * 删除完毕后，请你返回最终结果链表的头节点。
     *
     *  
     * 你可以返回任何满足题目要求的答案。
     *
     * （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）
     *
     * 示例 1：
     * 输入：head = [1,2,-3,3,1]
     * 输出：[3,1]
     * 提示：答案 [1,2,1] 也是正确的。
     * 
     * 示例 2：
     * 输入：head = [1,2,3,-3,4]
     * 输出：[1,2,4]
     * 
     * 示例 3：
     * 输入：head = [1,2,3,-3,-2]
     * 输出：[1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 
     * @param head 链表头
     * @return 删除和为0的连续节点后的链表的头节点
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) {
            return null;
        }
        while (head.val == 0) {
            head = head.next;
        }

        // 删除值为 0 的节点，顺便统计剩余节点的数量 len
        ListNode pre, cur = head;
        LinkedList<ListNode> list = new LinkedList<>();
        int len = 0;
        while (cur != null) {
            if (cur.val != 0) {
                len++;
                list.add(cur);
                pre = cur;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        
        return head;
    }

    private int deleteZeroSumHelper(LinkedList<ListNode> list) {
        int delCnt;
        int len = list.size();
        int[][] s = new int[len][len];

        for (int i = 0; i < len; i++) {
            s[i][i] = list.get(i).val;
        }

        for (int l = 2; l <= len; l++) {
            for (int i = 0; i < len + 1 - l; i++) {
                int j = i + l - 1;
                s[i][j] = s[i][j - 1] + list.get(j).val;
                if (s[i][j] == 0) {
                    delCnt = j - i + 1;
                    if (i > 0) {
                        list.get(i - 1).next = list.get(j).next;
                    } else {
                        
                    }
                }
            }
        }

        return len;
    }
    
    /**
     * 1170. 比较字符串最小字母出现频次
     * 我们来定义一个函数 f(s)，其中传入参数 s 是一个非空字符串；该函数的功能是统计 s  中（按字典序比较）最小字母的出现频次。
     *
     * 例如，若 s = "dcce"，那么 f(s) = 2，因为最小的字母是 "c"，它出现了 2 次。
     *
     * 现在，给你两个字符串数组待查表 queries 和词汇表 words，请你返回一个整数数组 answer 作为答案，其中每个 answer[i] 是满足 f(queries[i]) < f(W) 的词的数目，W 是词汇表 words 中的词。
     *
     *
     * 示例 1：
     * 输入：queries = ["cbd"], words = ["zaaaz"]
     * 输出：[1]
     * 解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
     * 
     * 示例 2：
     * 输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
     * 输出：[1,2]
     * 解释：第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
     *  
     *
     * 提示：
     * 1 <= queries.length <= 2000
     * 1 <= words.length <= 2000
     * 1 <= queries[i].length, words[i].length <= 10
     * queries[i][j], words[i][j] 都是小写英文字母
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/compare-strings-by-frequency-of-the-smallest-character
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 
     * @param queries
     * @param words
     * @return
     */
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] answer = new int[queries.length];
        if (words.length == 0) {
            return answer;
        }

        int[] iws = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            iws[i] = f(words[i]);
        }

        Arrays.sort(iws);

        for (int i = 0; i < queries.length; i++) {
            answer[i] = countGreaterElements(iws, iws.length, f(queries[i]));
        }

        return answer;
    }

    private int f(String s) {
        char pre = 'z', cur, cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            cur = s.charAt(i);
            if (cur < pre) {
                pre = cur;
                cnt = 1;
            } else if (cur == pre) {
                cnt++;
            }
        }
        return cnt;
    }

    private int countGreaterElements(int[] a, int len, int key) {
        // a 是递增数组，找出 a 中大于 key 的元素数量
        if (key < a[0]) {
            return len;
        } else if (key >= a[len - 1]) {
            return 0;
        }

        int left = 0, right = len - 1, mid = 0;
        while (left < right) {
            mid = (left + right) >>> 1;
            if (key < a[mid]) {
                right = mid - 1;
            } else if (key > a[mid]) {
                left = mid + 1;
            } else {
                break;
            }
        }

        if (left == right) {
            while (a[left] <= key) {
                left++;
            }
            return len - left;
        } else {
            while (a[mid] == key) {
                mid++;
            }
            return len - mid;
        }
    }
    
    /**
     * 357. 计算各个位数不同的数字个数
     * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
     *
     * 示例:
     * 输入: 2
     * 输出: 91 
     * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 
     * @param n 数字长度
     * @return 各位数字都不同的数字 x 的个数
     */
    public int countNumbersWithUniqueDigits(int n) {
        int cnt = 10, curCnt = 9;
        int digitToSelectFrom = 9;
        int maxLen = n <= 10 ? n : 10;

        for (int i = 1; i < maxLen; i++) {
            curCnt = curCnt * digitToSelectFrom;
            digitToSelectFrom--;
            cnt += curCnt;
        }

        return cnt;
    }
    
    /**
     * 264. 丑数 II
     * 编写一个程序，找出第 n 个丑数。
     *
     * 丑数就是只包含质因数 2, 3, 5 的正整数。
     *
     * 示例:
     *
     * 输入: n = 10
     * 输出: 12
     * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
     * 说明:  
     *
     * 1 是丑数。
     * n 不超过1690。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ugly-number-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 
     * @param n 需要返回的丑数序号（从1开始）
     * @return 第 n 个丑数
     */
    public int nthUglyNumber(int n) {
        int[] ret = new int[n];
        int ia = 0, ib = 0, ic = 0;
        int mula = 2, mulb = 3, mulc = 5, cur;
        ret[0] = 1;

        for (int i = 1; i < n; i++) {
            cur = Math.min(mula, mulb);
            cur = Math.min(cur, mulc);
            ret[i] = cur;
            if (cur == mula) {
                mula = ret[++ia] * 2;
            }
            if (cur == mulb) {
                mulb = ret[++ib] * 3;
            }
            if (cur == mulc) {
                mulc = ret[++ic] * 5;
            }
        }

        return ret[n - 1];
    }

    /**
     * 313. 超级丑数
     * 编写一段程序来查找第 n 个超级丑数。
     *
     * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
     *
     * 示例:
     *
     * 输入: n = 12, primes = [2,7,13,19]
     * 输出: 32 
     * 解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
     * 
     * 说明:
     * - 1 是任何给定 primes 的超级丑数。
     * - 给定 primes 中的数字以升序排列。
     * - 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
     * - 第 n 个超级丑数确保在 32 位有符整数范围内。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/super-ugly-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 
     * @param n 第 N 个超级丑数
     * @param primes 质数列表
     * @return 第 N 个超级丑数
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ret = new int[n];
        ret[0] = 1;
        int k = primes.length;
        int[] indexes = new int[k];
        int[] mul = new int[k];
        int minMul;
        for (int i = 0; i < k; i++) {
            indexes[i] = 0;
            mul[i] = primes[i];
        }

        for (int i = 1; i < n; i++) {
            minMul = mul[0];
            for (int j = 1; j < k; j++) {
                minMul = minMul <= mul[j] ? minMul : mul[j];
            }
            
            ret[i] = minMul;

            for (int j = 0; j < k; j++) {
                if (minMul == mul[j]) {
                    indexes[j]++;
                    mul[j] = ret[indexes[j]] * primes[j];
                }
            }
        }

        return ret[n-1];
    }
    
    /**
     * 493. 翻转对
     * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
     * <p>
     * 你需要返回给定数组中的重要翻转对的数量。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,3,2,3,1]
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: [2,4,3,5,1]
     * 输出: 3
     * 注意:
     * <p>
     * 给定数组的长度不会超过50000。
     * 输入数组中的所有数字都在32位整数的表示范围内。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-pairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums 整数数组
     * @return 给定数组中的翻转对数量
     */
    public int reversePairsII(int[] nums) {
        
        return 0;
    }
    
    public int reversePairs(int[] nums) {
        int ret = 0;

        for (int j = nums.length - 1; j > 0; j--) {
            if (nums[j] > Integer.MAX_VALUE >>> 1) {
                // 2 * nums[j] 超过int的最大值，不会有超过此值的数组元素
                continue;
            }
            if (nums[j] < Integer.MIN_VALUE >> 1) {
                // 2 * nums[j] 比int最小值还小，不会有比此更小的数组元素了
                ret += j;
                continue;
            }

            int threshold = nums[j] << 1;

            for (int i = j - 1; i >= 0; i--) {
                if (nums[i] > threshold) {
                    ret++;
                }
            }
        }

        return ret;
    }
    
    /**
     * 541. 反转字符串 II
     * 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。
     *
     * 示例:
     *
     * 输入: s = "abcdefg", k = 2
     * 输出: "bacdfeg"
     * 要求:
     *
     * 该字符串只包含小写的英文字母。
     * 给定字符串的长度和 k 在[1, 10000]范围内。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-string-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 
     * @param s 给定字符串
     * @param k 反转长度
     * @return 反转后的字符串
     */
    public String reverseStr(String s, int k) {
        int len = s.length();
        StringBuilder sb = new StringBuilder(len);
        
        // 当前段要反转的子串开始下标，无须反转的k个字符的开始下标，当前段的结束下标（不包含）
        int l = 0, m = k, r = 2 * k;
        do {
            if (m >= len) {
                // 反转 [l, len) 的字符
                m = len;
                r = len;
            } else if (r > len) {
                r = len;
            }

            // 反转的部分
            for (int i = m - 1; i >= l; i--) {
                sb.append(s.charAt(i));
            }
            
            // 不反转的部分
            sb.append(s, m, r);
            
            l = r;
            m = l + k;
            r = l + 2 * k;
            
        } while (l < len);
        
        return sb.toString();
    }
    
    /**
     * 32. 最长有效括号
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     *
     * 示例 1:
     *
     * 输入: "(()"
     * 输出: 2
     * 解释: 最长有效括号子串为 "()"
     * 示例 2:
     *
     * 输入: ")()())"
     * 输出: 4
     * 解释: 最长有效括号子串为 "()()"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s 只包含 '(' 和 ')' 的字符串
     * @return 最长有效括号的子串长度
     */
    public int longestValidParenthesesII(String s) {
        int len = s.length();
        int[] c = new int[len];
        int maxLen = 0;
        
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    c[i] = (i >= 2 ? c[i - 2] : 0) + 2;
                } else if (i - c[i - 1] > 0 && s.charAt(i - c[i - 1] -1) == '(') {
                    c[i] = (i - c[i - 1] >= 2 ? c[i - c[i - 1] - 2] : 0) + c[i - 1] + 2;
                }

                if (c[i] > maxLen) {
                    maxLen = c[i];
                }
            }
        }
        
        return maxLen;
    }

    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len <= 1) {
            return 0;
        }

        // c[i][j] 表示 [i, j)的子串是否是有效括号子串
        boolean[][] c = new boolean[len + 1][len + 1];

        int maxLen = 0;

        // 长度为0的子串
        for (int i = 0; i <= len; i++) {
            c[i][i] = true;
        }

        // 对于长度 >= 2 的子串，分两种情况检查
        for (int l = 2; l <= len; l += 2) {
            for (int i = 0; i <= len - l; i++) {

                int j = i + l;

                // 情形1：(substring)
                if ('(' == s.charAt(i)
                        && ')' == s.charAt(j - 1)
                        && c[i + 1][j - 1]) {
                    c[i][j] = true;
                    maxLen = l > maxLen ? l : maxLen;
                    continue;
                }

                // 情形2：substring + substring
                for (int k = i + 2; k <= j - 2; k += 2) {
                    if (c[i][k] && c[k][j]) {
                        c[i][j] = true;
                        maxLen = l > maxLen ? l : maxLen;
                        break;
                    }
                }
            }
        }

        return maxLen;
    }

    /**
     * 23. 合并K个排序链表
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     *
     * 示例:
     *
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param lists 有序链表数组
     * @return 合并后的单一有序链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        int len = lists.length;
        
        for (int i = 0; i < len;) {
            // 神经病吧，居然会有lists中元素是null的情形。。。
            if (lists[i] == null) {
                lists[i] = lists[--len];
            } else {
                i++;
            }
        }
        
        // 1 最小堆化
        int minIdx;
        for (int i = (len >>> 1) - 1; i >= 0; i--) {
            for (int j = i; j < len >>> 1;) {
                int left = (j << 1) + 1;
                int right = (j << 1) + 2;
                
                if (right >= len) {
                    // 这种情况只会出现在第一次
                    right = left;
                }

                if (lists[left].val <= lists[right].val) {
                    minIdx = left;
                } else {
                    minIdx = right;
                }

                if (lists[minIdx].val < lists[j].val) {
                    // 交换节点i和较小的子节点的位置
                    ListNode node = lists[j];
                    lists[j] = lists[minIdx];
                    lists[minIdx] = node;

                    j = minIdx;
                } else {
                    break;
                }
            }
        }
        
        // 2 合并链表
        ListNode root, cur;
        root = cur = lists[0];

        while (len > 1) {
            lists[0] = lists[0].next;
            if (lists[0] == null) {
                // 与最后一个数组元素交换
                lists[0] = lists[--len];
                lists[len] = null;
            }

            // 把 lists[0] 下沉到合适位置
            for (int i = 0; i < (len >>> 1); ) {
                int left = (i << 1) + 1;
                int right = (i << 1) + 2;

                if (right >= len) {
                    // 只可能在i指向数组最后一个元素的父节点时出现
                    right = left;
                }

                if (lists[left].val <= lists[right].val) {
                    minIdx = left;
                } else {
                    minIdx = right;
                }

                if (lists[minIdx].val < lists[i].val) {
                    // 交换节点i和较小的子节点的位置
                    ListNode node = lists[i];
                    lists[i] = lists[minIdx];
                    lists[minIdx] = node;
                    i = minIdx;
                } else {
                    // lists[0] 已下沉到合适位置，跳出内层循环
                    break;
                }
            }

            cur.next = lists[0];
            cur = cur.next;
        }
        
        return root;
    }
    
    

    /**
     * 21. 合并两个有序链表
     *
     * @param l1 有序链表1
     * @param l2 有序链表2
     * @return 合并后的有序链表首节点
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode root, cur;

        if (l1.val < l2.val) {
            cur = root = l1;
            l1 = l1.next;
        } else {
            cur = root = l2;
            l2 = l2.next;
        }

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }

        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }

        return root;
    }

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s 只包括括号字符的字符串
     * @return 字符串是否有效
     */
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        char pre;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                case '[':
                case '{':
                    st.push(c);
                    break;
                case ')':
                    if (st.isEmpty() || '(' != st.pop()) {
                        return false;
                    }
                    break;
                case ']':
                    if (st.isEmpty() || '[' != st.pop()) {
                        return false;
                    }
                    break;
                case '}':
                    if (st.isEmpty() || '{' != st.pop()) {
                        return false;
                    }
                    break;
            }
        }

        return true;
    }

    /**
     * 16. 最接近的三数之和
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     *
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum-closest
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        return 0;
    }

    /**
     * 15. 三数之和
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums 给定整数数组
     * @return 和为0的三元组集合
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 先排序
        Arrays.sort(nums);

        List<List<Integer>> ret = new LinkedList<>();
        List<Integer> list;
        int len = nums.length;

        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 如果某个数与前一个数相同，那么跳过这个数。这样可以避免找出一些重复的三元组
                continue;
            }

            // 另外两数之和
            int sum = - nums[i];
            int l = i + 1, r = len - 1;
            int curSum;

            while (l < r) {
                curSum = nums[l] + nums[r];
                if (curSum < sum) {
                    do {
                        l++;
                    } while (l < r && nums[l] == nums[l - 1]);
                } else if (curSum > sum) {
                    do {
                        r--;
                    } while (l < r && nums[r] == nums[r + 1]);
                } else {
                    // 找到一个三数之和为 0 的组合 (i, l, r)
                    list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    ret.add(list);

                    do {
                        l++;
                    } while (l < r && nums[l] == nums[l - 1]);

                    do {
                        r--;
                    } while (l < r && nums[r] == nums[r + 1]);
                }
            }
        }

        return ret;
    }

    /**
     * 494.目标和
     * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     *
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     *
     * 示例 1:
     * 输入: nums: [1, 1, 1, 1, 1], S: 3
     * 输出: 5
     * 解释:
     *
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     *
     * 一共有5种方法让最终目标和为3。
     * 注意:
     *
     * 数组的长度不会超过20，并且数组中的值全为正数。
     * 初始的数组的和不会超过1000。
     * 保证返回的最终结果为32位整数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/target-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums 非负整数组
     * @param S 目标和
     * @return 可计算出目标和的组合数量
     */
    public int findTargetSumWays(int[] nums, int S) {
        return findTargetSumWaysHelper(nums, nums.length, 0, S);
    }

    private int findTargetSumWaysHelper(int[] nums, int len, int i, int s) {
        // 这里用 “==” 会比 “>=” 有提升，leetcode 上用时分别是670ms, 905ms
        if (i == len) {
            return s == 0 ? 1 : 0;
        }

        int ways = findTargetSumWaysHelper(nums, len, i + 1, s - nums[i])
                + findTargetSumWaysHelper(nums, len, i + 1, s + nums[i]);

        return ways;
    }

    /**
     * 494.目标和
     * 动态规划解法。
     *
     * @param nums 非负整数组
     * @param S 目标和
     * @return 可计算出目标和的组合数量
     */
    public int findTargetSumWaysII(int[] nums, int S) {

        return 0;
    }

    /**
     * 1161. 最大层内元素和
     * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
     *
     * 请你找出层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-level-sum-of-a-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root 树根
     * @return 和最大那几层的最小层号
     */
    public int maxLevelSum(TreeNode root) {
        int maxSum = Integer.MIN_VALUE, maxLvl = -1, curLvl = 0;
        TreeNode curNode;

        // 两个栈，一个存储当前层的节点，一个存储当前层的子节点
        Stack<TreeNode> st = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        Stack<TreeNode> tmp;

        st.push(root);
        int curSum;

        while (!st.isEmpty()) {
            curSum = 0;
            curLvl++;

            // st 为存储当前层的所有节点的栈，依次将当前层节点出栈，求和，并将子节点添加到 st2
            while (!st.isEmpty()) {
                curNode = st.pop();
                curSum += curNode.val;

                if (curNode.left != null) {
                    st2.push(curNode.left);
                }
                if (curNode.right != null) {
                    st2.push(curNode.right);
                }
            }

            // 若当前层之和更大，则更新最大层和，和层数
            if (curSum > maxSum) {
                maxSum = curSum;
                maxLvl = curLvl;
            }

            // 交换 st 和 st2
            tmp = st;
            st = st2;
            st2 = tmp;
        }

        return maxLvl;
    }
}