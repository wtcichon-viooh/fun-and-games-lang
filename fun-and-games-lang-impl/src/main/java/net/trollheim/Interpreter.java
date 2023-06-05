package net.trollheim;

import net.trollheim.lang.impl.visitor.FunAndGamesLangVisitorImpl;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.trollheim.lang.FunAndGamesLangLexer;
import org.trollheim.lang.FunAndGamesLangParser;

import java.math.BigInteger;
import java.util.List;


public class Interpreter {


    List<BigInteger> run(String expression) {
        var lexer = new FunAndGamesLangLexer(CharStreams.fromString(expression));
        var parser = new FunAndGamesLangParser(new CommonTokenStream(lexer));
        var visitor = new FunAndGamesLangVisitorImpl();

        return (List<BigInteger>) visitor.visitProgram(parser.program());
    }

}