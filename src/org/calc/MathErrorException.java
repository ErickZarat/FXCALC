package org.calc;

public class MathErrorException extends Exception{
    public MathErrorException(String msg){
        //log exception on ide console or terminal
        System.out.println(msg);
    }
}
