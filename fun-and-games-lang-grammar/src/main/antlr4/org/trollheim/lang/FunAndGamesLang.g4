grammar FunAndGamesLang;

program : line+;
line : statement EOL;

statement : command argument;

command :
        SUM | PRODUCT;

argument: data |  file;

data : array;
array : '[' (number (COMMA number)* )?']';

number : NUMBER;

file : FROM '\''  filePath '\'';

filePath: DRIVELETTER? (DIRECTORY? SEPARATOR)* FILENAME;

NUMBER     : MINUS? [1-9][0-9]*;
FROM : 'from';

FILENAME    : [a-zA-Z_0-9]+ ('.' [a-zA-Z_0-9]+)?;
DIRECTORY   : [a-zA-Z_0-9]+ | '.' | '..';
DRIVELETTER : [a-z] ':' SEPARATOR;
SEPARATOR   : '/';


MINUS : '-';
COMMA : ',';
PRODUCT : '*';
SUM : '+';
EOL : ';';


WS : [ \t\r\n]+ -> skip ;