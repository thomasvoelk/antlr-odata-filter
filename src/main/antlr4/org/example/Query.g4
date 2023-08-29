grammar Query;

/*
 * Parser Rules
 */

query                   : andExpression EOF;
//orExpression            : andExpression (OR andExpression)*;
andExpression           : expressionPart (AND expressionPart)*;
expressionPart          : ATTRIBUTE COMPARISON VALUE;

/*
 * Lexer Rules
 */

// OR      : 'OR';
AND     : 'AND';

VALUE: QUOTEDVALUE |
       INTEGERNUMBER;
COMPARISON     : GREATER |
                 LESSER |
                 EQUAL |
                 GREATER EQUAL |
                 LESSER EQUAL |
                 NOT EQUAL;
QUOTEDVALUE    : QUOTE ('\\\'' | ()? ~'\'')* QUOTE;
INTEGERNUMBER  : DIGIT+;
ATTRIBUTE      : (LETTER | SPECIAL)+;
WHITESPACE     : (' ' | '\t' | '\n' | '\r')+ -> skip;

fragment DIGIT          : [0-9];
fragment LETTER         : [a-zA-Z];
fragment SPECIAL        : '_' | '-' | '.';
fragment GREATER        : '>';
fragment LESSER         : '<';
fragment EQUAL          : 'eq';
fragment GREATEROREQUAL : 'ge';
fragment NOT            : '!';
fragment QUOTE          : '\'';