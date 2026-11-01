class Solution {
public int myAtoi(String s) {
int i = 0;
int n = s.length();

    // Step 1: Skip leading spaces
    while (i < n && s.charAt(i) == ' ') {
        i++;
    }
    // Step 2: Check the sign
    int sign = 1;
    if (i < n && s.charAt(i) == '-') {
        sign = -1;
        i++;
    } else if (i < n && s.charAt(i) == '+') {
        i++;
    }
    // Step 3: Convert digits to integer
    long result = 0;
    while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
        int digit = s.charAt(i) - '0';
        result = result * 10 + digit;
        // Step 4: Check integer overflow
        if (sign * result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (sign * result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        i++;
    }
    return (int) (sign * result);
}

}