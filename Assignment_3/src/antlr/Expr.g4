grammar Expr;
/* The grammar name and file name must be the same*/

@header {
        package antlr;
}

// start variable
prog: (decl | expr | verf)+ EOF # Program
    ;

decl: VARIABLE ID                   # Declaration
    | VARIABLE ID '=' BOOL_VALUE    # Initialization
    ;

expr: '(' expr ')'                  # Parentheses
    | 'not' expr                    # Negation
    | expr 'and' expr               # Conjunction
    | expr 'or' expr                # Disjunction
    | expr '=>' expr                # Implication
    | expr '<=>' expr               # Equivalence
    | ID                            # Variable
    | BOOL_VALUE                    # BooleanValue
    ;

verf: 'verify' expr             # Verification
    ;

/* Tokens */
VARIABLE: 'var' ;
BOOL_VALUE: 'true' | 'false';
ID: [a-z][a-zA-Z0-9_]*;
COMMENT: '//' ~[\r\n]* -> skip;
WS : [ \t\n\r]+ -> skip ;