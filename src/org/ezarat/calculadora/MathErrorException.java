/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ezarat.calculadora;

/**
 *
 * @author Admin
 */
public class MathErrorException extends Exception{
    public MathErrorException(String msg){
        System.out.println(msg);
    }
}
