/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

/**
 *
 * @author DELL
 */
public class test {
    public static void main(String[] args) {
        int i = 50;
        int[] a = new int[10];
        a = new int[] {10,20,30};
        System.out.println(a.length);
        int[]b = null;
        b=a;
        
         a = new int[100];
        System.out.println(b.length);
        
         a = new int[i];
        System.out.println(a.length);
//        String s1 = "abc";
//        String s2 = "abc";
//        String s3 = new String("abc") ;
//        System.out.println(s1==s2);
//        System.out.println(s1==s3);
//        System.out.println(s1.concat(s2));
//        byte[] data = {12,34,9,0,-62,88};
//        System.out.println(data.length);
    }
    
}
