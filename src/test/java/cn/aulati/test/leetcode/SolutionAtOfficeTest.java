package cn.aulati.test.leetcode;

import org.junit.jupiter.api.*;

import cn.aulati.test.model.ListNode;
import cn.aulati.test.util.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SolutionAtOfficeTest {

    /** An instance of tested class */
    private SolutionAtOffice _solution;

    /**
     * Initialize the instance of tested class
     */
    @BeforeAll
    void init() {
        _solution = new SolutionAtOffice();
    }

    @Test
    void testDeleteDuplicates1() {
        // test null
        ListNode ret = _solution.deleteDuplicates(null);
        assertNull(ret);
        
        // test one node
        ListNode head = new ListNode(10);
        ret = _solution.deleteDuplicates(head);
        assertEquals("10", ret.toString());
    }

    @Test
    void testDeleteDuplicates2() {
        int[] arr = { 1, 1, 1, 2, 3 };
        ListNode head = ListNode.fromArray(arr);
        ListNode ret = _solution.deleteDuplicates(head);
        assertEquals("2->3", ret.toString());
    }

    @Test
    void testDeleteDuplicates3() {
        int[] arr = { 1, 2, 3, 3, 4, 4, 5 };
        ListNode head = ListNode.fromArray(arr);
        ListNode ret = _solution.deleteDuplicates(head);
        assertEquals("1->2->5", ret.toString());
    }

    @Test
    void testDeleteDuplicates4() {
        int[] arr = { 1, 1 };
        ListNode head = ListNode.fromArray(arr);
        ListNode ret = _solution.deleteDuplicates(head);
        assertNull(ret);
    }

    @Test
    void testDeleteDuplicates5() {
        int[] arr = { 1, 1, 2, 3, 4, 4, 5, 5 };
        ListNode head = ListNode.fromArray(arr);
        ListNode ret = _solution.deleteDuplicates(head);
        assertEquals("2->3", ret.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "4,1000,4",
            "1,2,1",
            "103966815,201619847391450,32"
    })
    void testSuperpalindromesInRange(String L, String R, int expected) {
        int ret = _solution.superpalindromesInRange(L, R);
        assertEquals(expected, ret);
    }

    @Test
    void testCombinationSum21() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        List<List<Integer>> ret = _solution.combinationSum2(candidates, target);

        String retStr = Utils.listOfListToString(ret);
        String expected = "[[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]";
        assertEquals(expected, retStr);
    }

    @Test
    void testExist1() {
        char[][] board = {{'A','B','C','E'}
                        , {'S','F','C','S'}
                        , {'A','D','E','E'}};
        String word = "ABCCED";

        boolean ret = _solution.exist(board, word);

        assertTrue(ret);
    }

    @Test
    void testExist2() {
        char[][] board = {{'A','B','C','E'}
                        , {'S','F','E','S'}
                        , {'A','D','E','E'}};
        String word = "ABCESEEEFS";

        boolean ret = _solution.exist(board, word);

        assertTrue(ret);
    }

    @Test
    void testGuessNumber1() {
        int n = 2126753390;
        int target = 1702766719;

        SolutionAtOffice.guessTarget = target;
        int ret = _solution.guessNumber(n);
        assertEquals(target, ret);
    }

    @Test
    void testGuessNumber4() {
        int n = 10;
        int target = 6;

        SolutionAtOffice.guessTarget = target;
        int ret = _solution.guessNumber(n);
        assertEquals(target, ret);
    }

    @Test
    void testGuessNumber2() {
        int n = 3;
        int target = 1;

        SolutionAtOffice.guessTarget = target;
        int ret = _solution.guessNumber(n);
        assertEquals(target, ret);
    }

    @Test
    void testGuessNumber3() {
        int n = 3;
        int target = 3;

        SolutionAtOffice.guessTarget = target;
        int ret = _solution.guessNumber(n);
        assertEquals(target, ret);
    }

    @Test
    void testThreeSum1() {
        int[] nums = { -1, 0, 1, 2, -1, -4 };
        List<List<Integer>> ret = _solution.threeSum(nums);

        StringBuilder sb = new StringBuilder("[-1, -1, 2][-1, 0, 1]");
        for (List<Integer> list : ret) {
            String val = Arrays.toString(list.toArray());
            int start = sb.indexOf(val);
            if (start < 0) {
                Assertions.fail("Result contains incorrect list: " + val);
            }

            sb.replace(start, val.length() + start, "");
        }

        assertEquals("", sb.toString(), "Result does not contains list(s): " + sb.toString());
    }

    @Test
    void testNextPermutation1() {
        int[] nums = {1, 2, 3};
        _solution.nextPermutation(nums);
        assertEquals("[1, 3, 2]", Arrays.toString(nums));
    }

    @Test
    void testNextPermutation2() {
        int[] nums = { 3, 2, 1 };
        _solution.nextPermutation(nums);
        assertEquals("[1, 2, 3]", Arrays.toString(nums));
    }

    @Test
    void testNextPermutation3() {
        int[] nums = {4,2,0,2,3,2,0};
        _solution.nextPermutation(nums);
        assertEquals("[4, 2, 0, 3, 0, 2, 2]", Arrays.toString(nums));
    }

    @Test
    void testDivide1() {
        int ret = _solution.divide(10, 3);
        assertEquals(3, ret);
    }

    @Test
    void testDivide2() {
        int ret = _solution.divide(7, -3);
        assertEquals(-2, ret);
    }

    @Test
    void testDivide3() {
        int ret = _solution.divide(Integer.MAX_VALUE, 1);
        assertEquals(Integer.MAX_VALUE, ret);
    }

    @Test
    void testDivide4() {
        int ret = _solution.divide(Integer.MIN_VALUE, 1);
        assertEquals(Integer.MIN_VALUE, ret);
    }

    /**
     * test findNumbers
     */
    @Test
    void testFindNumbers1() {
        int[] nums = {12,345,2,6,7896};
        int ret = _solution.findNumbers(nums);

        assertEquals(2, ret);
    }

    @Test
    void testFindNumbers2() {
        int[] nums = {555,901,482,1771};
        int ret = _solution.findNumbers(nums);

        assertEquals(1, ret);
    }

    @Test
    void testFindNumbers3() {
        int[] nums = {1,23,456,777,6666,777777};
        int ret = _solution.findNumbers(nums);

        assertEquals(3, ret);
    }
}
