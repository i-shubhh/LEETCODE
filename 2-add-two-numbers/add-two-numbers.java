/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // Dummy node to make creating the result easier
        ListNode dummy = new ListNode(0);

        ListNode current = dummy;

        int carry = 0;

        // Continue while there are nodes or a carry
        while (l1 != null || l2 != null || carry != 0) {

            int sum = carry;

            // Add value from first list
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            // Add value from second list
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            // Calculate carry
            carry = sum / 10;

            // Calculate current digit
            int digit = sum % 10;

            // Add digit to result
            current.next = new ListNode(digit);
            current = current.next;
        }

        return dummy.next;
    }
}