package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

public class TheTest {
    @Test
    void test() {
        var filter = CharStreams.fromString("app_id eq '123' AND hurz eq 12");
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
            System.out.println("Operator: " + ctx.COMPARISON().getText());
            System.out.println("Attribute: " + ctx.VALUE().getText());
            return super.visitExpressionPart(ctx);
        }


    }
}
