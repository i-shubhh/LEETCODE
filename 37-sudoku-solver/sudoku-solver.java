class Solution {
private boolean[][] rows = new boolean[9][10];
private boolean[][] cols = new boolean[9][10];
private boolean[][] boxes = new boolean[9][10];

public void solveSudoku(char[][] board) {
    // Initialize row, column, and box constraints
    for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
            if (board[r][c] != '.') {
                int num = board[r][c] - '0';
                int box = (r / 3) * 3 + (c / 3);
                rows[r][num] = true;
                cols[c][num] = true;
                boxes[box][num] = true;
            }
        }
    }
    solve(board);
}
private boolean solve(char[][] board) {
    int bestRow = -1;
    int bestCol = -1;
    int minOptions = 10;
    // Choose the empty cell with the fewest possible numbers (MRV)
    for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
            if (board[r][c] == '.') {
                int box = (r / 3) * 3 + (c / 3);
                int options = 0;
                for (int num = 1; num <= 9; num++) {
                    if (!rows[r][num] &&
                        !cols[c][num] &&
                        !boxes[box][num]) {
                        options++;
                    }
                }
                if (options < minOptions) {
                    minOptions = options;
                    bestRow = r;
                    bestCol = c;
                }
                // No valid number can fill this cell
                if (minOptions == 0) {
                    return false;
                }
            }
        }
    }
    // No empty cells remain: Sudoku solved
    if (bestRow == -1) {
        return true;
    }
    int box = (bestRow / 3) * 3 + (bestCol / 3);
    // Try valid digits
    for (int num = 1; num <= 9; num++) {
        if (!rows[bestRow][num] &&
            !cols[bestCol][num] &&
            !boxes[box][num]) {
            // Place the digit
            board[bestRow][bestCol] = (char) (num + '0');
            rows[bestRow][num] = true;
            cols[bestCol][num] = true;
            boxes[box][num] = true;
            if (solve(board)) {
                return true;
            }
            // Backtrack: undo the placement
            board[bestRow][bestCol] = '.';
            rows[bestRow][num] = false;
            cols[bestCol][num] = false;
            boxes[box][num] = false;
        }
    }
    return false;
}

}