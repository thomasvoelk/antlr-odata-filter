grammar Query;

/*
 * Parser Rules
 */

query                   : andExpression EOF;
//orExpression            : andExpression (OR andExpression)*;
andExpression           : expressionPart (AND expressionPart)*;
expressionPart          : ATTRIBUTE COMPARISON VALUE
                        |  ATTRIBUTE IN '(' VALUELIST ')';

/*
 * Lexer Rules
 */

// OR      : 'OR';
AND     : 'AND';
IN             : 'in';
VALUE: QUOTEDVALUE |
       INTEGERNUMBER;
COMPARISON     : EQUAL |
                 GREATEROREQUAL;
QUOTEDVALUE    : QUOTE ('\\\'' | ()? ~'\'')* QUOTE;
INTEGERNUMBER  : DIGIT+;
VALUELIST      : INTEGERNUMBER (COMMA INTEGERNUMBER)*;
ATTRIBUTE      : (LETTER | SPECIAL)+;
WHITESPACE     : (' ' | '\t' | '\n' | '\r')+ -> skip;

fragment DIGIT          : [0-9];
fragment LETTER         : [a-zA-Z];
fragment SPECIAL        : '_' | '-' | '.';
fragment EQUAL          : 'eq';
fragment GREATEROREQUAL : 'ge';
fragment QUOTE          : '\'';
fragment COMMA          : ',';