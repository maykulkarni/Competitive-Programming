package CTCI;

import java.util.Scanner;


/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class Chapter1 {


    public static String urlify(String url) {
//        return url.replace(" ", "%20");
        String my_str = new String(new char[]{44, 45, 46});
        System.out.println(my_str);
        return "";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String one = in.nextLine();
        System.out.println(urlify(one));
    }
}