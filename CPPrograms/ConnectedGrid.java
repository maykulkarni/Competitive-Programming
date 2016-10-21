package CPPrograms;

/**
 * Created by mayur on 6/7/16.
 */

public class ConnectedGrid {
    public static int row, col;

    public static void main(String[] at) {
        String[] grid = {"...", ".G.", "..."};
        char[][] twoDGrid = convertTo2D(grid);
        row = grid.length;
        col = grid[0].length();
        System.out.println(countStrokes(twoDGrid));
    }

    private static int countStrokes(char[][] twoDGrid) {
        int i, j = 0;
        int chari = -1;
        int charj = -1;
        char currChar = 'N';
        boolean found = false;
        for (i = 0; i < row; i++) {
            if (found) break;
            for (j = 0; j < col; j++) {
                if (twoDGrid[i][j] != '.') {
                    found = true;
                    chari = i;
                    charj = j;
                    currChar = twoDGrid[i][j];
                    break;
                }
            }
        }
        if (currChar == 'N') return 0;
        switch (currChar) {
            case 'G':
                twoDGrid[chari][charj] = 'B';
                if (twoDGrid[chari][charj + 1] == 'R') {
                    charj++;
                    while (twoDGrid[chari][charj++] == 'R') twoDGrid[chari][charj] = '.';
                } else if (twoDGrid[chari + 1][charj] == 'R') {
                    chari++;
                    while (twoDGrid[chari][charj++] == 'R') twoDGrid[chari][charj] = '.';
                }
                break;
            case 'B':
                twoDGrid[chari][charj] = '.';
                if (twoDGrid[chari][charj + 1] == 'B') {
                    charj++;
                    while (twoDGrid[chari][charj++] == 'B') twoDGrid[chari][charj] = '.';
                } else if (twoDGrid[chari + 1][charj] == 'B') {
                    chari++;
                    while (twoDGrid[i][charj++] == 'B') twoDGrid[chari][charj] = '.';
                }
                break;
            case 'R':
                twoDGrid[chari][charj] = '.';
                if (twoDGrid[chari][charj + 1] == 'R') {
                    charj++;
                    while (twoDGrid[chari][charj++] == 'R') twoDGrid[chari][charj] = '.';
                } else if (twoDGrid[chari + 1][charj] == 'R') {
                    chari++;
                    while (twoDGrid[chari][charj++] == 'R') twoDGrid[chari][charj] = '.';
                }
                break;
        }
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                System.out.print(twoDGrid[x][y]);
            }
            System.out.println();
        }
        System.out.println("***************");
        return 1 + countStrokes(twoDGrid);
    }

    private static char[][] convertTo2D(String[] grid) {
        char[][] twod = new char[grid.length][grid[0].length()];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                twod[i][j] = grid[i].charAt(j);
            }
        }
        return twod;
    }
}