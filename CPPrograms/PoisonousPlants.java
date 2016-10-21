package CPPrograms;

import MyImplementations.InputReader;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by mayur on 5/7/16.
 * <p>
 * int main()
 * {
 * int n;
 * cin>>n;
 * <p>
 * int* p = new int[n];
 * for(int i=0;i<n;i++)
 * cin>>p[i];
 * <p>
 * int* days = new int[n]();
 * int min = p[0];
 * int max = 0;
 * stack<int> s;
 * <p>
 * s.push(0);
 * <p>
 * for(int i=1;i<n;i++)
 * {
 * <p>
 * if(p[i] > p[i-1])
 * days[i] = 1;
 * <p>
 * min = min < p[i]?min:p[i];
 * <p>
 * while(!s.empty() && p[s.top()] >= p[i])
 * {
 * if(p[i] > min)
 * days[i] = days[i] > days[s.top()] + 1?days[i]:days[s.top()] + 1;
 * <p>
 * s.pop();
 * }
 * <p>
 * max = max>days[i]?max:days[i];
 * s.push(i);
 * }
 * <p>
 * cout<<max<<endl;
 */

public class PoisonousPlants {

    public static void main(String[] a) {
        InputReader in = new InputReader(System.in);
        int len = in.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = in.nextInt();
        }
        new SolverPoisonousPlants().solve(arr);
    }
}

class SolverPoisonousPlants {
    public void solve(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(0);
        int[] days = new int[arr.length];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = min < arr[0] ? min : arr[i];
            if (arr[i] > arr[i - 1]) {
                days[i] = 1;
            }
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                if (arr[i] > min) {
                    days[i] = days[i] > days[stack.peek()] + 1 ? days[i] : days[stack.peek()] + 1;
                }
                stack.pop();
            }
            stack.push(i);
        }
        System.out.println(Arrays.toString(days));
        System.out.println(Arrays.stream(days).max().getAsInt());
    }
}





















