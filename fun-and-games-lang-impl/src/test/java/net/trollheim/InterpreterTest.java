package net.trollheim;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;


class InterpreterTest {

    @Test
    void runTest(){
        var parser = new Interpreter();
        assert parser.run("+[1,2];").get(0).equals(new BigInteger("3"));
        assert parser.run("+ from 'data.txt';").get(0).equals(new BigInteger("15"));

    }
}