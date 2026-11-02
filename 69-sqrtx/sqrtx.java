class Solution {
    public int mySqrt(int x) {
        if(x<2) return x;
        int i = x/2;
        for(i = x/2; i > x/i; i = (i+x/i)/2);
        return i;

    }
}