package net.trollheim.lang.impl.visitor;


import org.trollheim.lang.FunAndGamesLangBaseVisitor;
import org.trollheim.lang.FunAndGamesLangParser;

import java.math.BigInteger;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunAndGamesLangVisitorImpl extends FunAndGamesLangBaseVisitor<Object> {
    @Override
    public Object visitProgram(FunAndGamesLangParser.ProgramContext ctx) {
        return ctx.line().stream().map(this::visitLine).collect(Collectors.toList());
    }

    @Override
    public Object visitLine(FunAndGamesLangParser.LineContext ctx) {
        return visitStatement(ctx.statement());
    }

    @Override
    public Object visitStatement(FunAndGamesLangParser.StatementContext ctx) {
        var arg = visitArgument(ctx.argument());
        BinaryOperator<BigInteger> accumulator  = (BinaryOperator<BigInteger>) visitCommand(ctx.command());
        return ((Stream<BigInteger>) arg).reduce(BigInteger.ZERO, accumulator);

    }

    @Override
    public Object visitCommand(FunAndGamesLangParser.CommandContext ctx) {
        return CommandsLookup.get(ctx.getText());
    }

    @Override
    public Object visitArgument(FunAndGamesLangParser.ArgumentContext ctx) {
        if (ctx.data() != null){
            return visitData(ctx.data());
        }
        if (ctx.file() != null){
            var filename = visitFile(ctx.file());

            return FileUtils.openFile(filename.toString());
        }
        throw new RuntimeException("unknown token "+ctx.getText());
    }

    @Override
    public Object visitData(FunAndGamesLangParser.DataContext ctx) {
        return visitArray(ctx.array());
    }

    @Override
    public Object visitArray(FunAndGamesLangParser.ArrayContext ctx) {

        return ctx.number().stream().map(this::visitNumber);
    }

    @Override
    public Object visitNumber(FunAndGamesLangParser.NumberContext ctx) {

        return new BigInteger(ctx.getText());
    }

    @Override
    public Object visitFile(FunAndGamesLangParser.FileContext ctx) {
         return ctx.filePath().getText();
    }
}
