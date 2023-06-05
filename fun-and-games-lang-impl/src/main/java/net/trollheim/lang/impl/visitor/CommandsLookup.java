package net.trollheim.lang.impl.visitor;

import java.math.BigInteger;

import java.util.function.BinaryOperator;


public enum CommandsLookup {
    ;
    public static BinaryOperator<BigInteger> get(String text) {
        //TODO implement different operations
        return (  a,   b) -> a.add(b);
    }




}
