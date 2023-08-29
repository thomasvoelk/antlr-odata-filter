package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

public class TheTest {
    @Test
    void test() {
        var filter = CharStreams.fromString("app_id eq '123' AND hurz ge 12 AND knoedel in (1,2,3)");
        QueryLexer lexer = new QueryLexer(filter);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QueryParser parser = new QueryParser(tokens);
        ParseTree tree = parser.query();

        QueryVisitor visitor = new TestVisitor();
        visitor.visit(tree);

    }


    class TestVisitor extends QueryBaseVisitor {
        @Override
        public Object visitQuery(QueryParser.QueryContext ctx) {
            System.out.println("Query: " + ctx.getText());
            return super.visitQuery(ctx);
        }

        @Override
        public Object visitAndExpression(QueryParser.AndExpressionContext ctx) {
            System.out.println("AndExpression: " + ctx.getText());
            return super.visitAndExpression(ctx);
        }

        @Override
        public Object visitExpressionPart(QueryParser.ExpressionPartContext ctx) {
            System.out.println("Attribute: " + ctx.ATTRIBUTE().getText());
            if(ctx.COMPARISON() != null) {
                System.out.println("Operator: " + ctx.COMPARISON().getText());
            }
            if(ctx.VALUE() != null) {
                System.out.println("Value: " + ctx.VALUE().getText());
            }
            if(ctx.IN() != null) {
                System.out.println("In: " + ctx.IN().getText());
            }
            if(ctx.VALUELIST() != null) {
            System.out.println("Valuelist: " + ctx.VALUELIST().getText());
            }
            return super.visitExpressionPart(ctx);
        }


    }
}
