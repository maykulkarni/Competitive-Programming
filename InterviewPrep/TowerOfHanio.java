package InterviewPrep;

/**
 * Created by mayur on 6/7/16.
 */
public class TowerOfHanio {

    public static void main(String[] at) {
        new Solver().solve(4, 'A', 'B', 'C');
    }
}

class Solver {
    public void solve(int numberOfDiscs, char from, char aux, char to) {
        if (numberOfDiscs == 1) {
            System.out.println("From " + from + " to " + to);
        } else {
            solve(numberOfDiscs - 1, from, to, aux);
            System.out.println("From " + from + " to " + to);
            solve(numberOfDiscs - 1, aux, from, to);
        }
    }
}