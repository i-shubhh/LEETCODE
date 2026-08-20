import java.util.AbstractList;

class Solution {
    private List<List<String>> list;
    private HashSet<Integer> col;
    private HashSet<Integer> posDiag;
    private HashSet<Integer> negDiag;

    public List<List<String>> solveNQueens(int n) {
        return new AbstractList<List<String>>() {
            @Override
            public int size() {
                init();
                return list.size();
            }

            @Override
            public List<String> get(int i) {
                init();
                return list.get(i);
            }

            private void init() {
                if (list != null)
                    return;
                list = new ArrayList<>();
                col = new HashSet<>();
                posDiag = new HashSet<>();
                negDiag = new HashSet<>();

                char[][] board = new char[n][n];
                for (char[] arr : board) {
                    Arrays.fill(arr, '.');
                }

                solver(board, 0, n, 0);
            }
        };
    }

    private void solver(char[][] board, int r, int n, int m) {
        if (n == m) {
            List<String> boardList = new ArrayList<>();
            for (char[] arr : board) {
                boardList.add(new String(arr));
            }
            list.add(boardList);
            return;
        }
        for (int c = 0; c < n; c++) {
            if (col.contains(c) || posDiag.contains(r + c) || negDiag.contains(r - c))
                continue;

            board[r][c] = 'Q';
            col.add(c);
            posDiag.add(r + c);
            negDiag.add(r - c);
            solver(board, r + 1, n, m + 1);

            board[r][c] = '.';
            col.remove(c);
            posDiag.remove(r + c);
            negDiag.remove(r - c);
        }
    }
}