package org.star_pet_house_commons.utiltest;

/*
 *@description:
 *@author jiafeng
 *@date 2020/12/2 0002 13:31
 */
public class Example {
    String str = new String("good");
    char[] ch = {'a','b','c'};
    public static void main(String[] args) {
        Example ex = new Example();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str +"and");
        System.out.print(ex.ch);
    }

    public void change(String str, char ch[]){
        str= "test ok";
        ch[0]= 'g';
    }
}
