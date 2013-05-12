package paleo.analyse ;

import paleo.arbre.*;
import java_cup.runtime.*;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cupsym Codeslexicaux
%cup
   
%{
  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

%state Expression
%state AppelMethode

typePrimitif = "int"|"long"|"float"|"double"|"short"|"byte"|"char"|"boolean"
classe = [A-Z][A-Za-z_0-9]*
idf = [A-Za-z_][A-Za-z_0-9]*
expr = [^;]*
commentaire = (\/\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+\/)|(\/\/.*)

/* A line terminator is a \r (carriage return), \n (line feed), or \r\n. */
LineTerminator = \r|\n|\r\n
   
/* White space is a line terminator, space, tab, or line feed. */
WhiteSpace     = {LineTerminator} | [ \t\f]

null = {WhiteSpace}*null{WhiteSpace}*
   
%%

<YYINITIAL> {commentaire}      	      	{ }

<YYINITIAL> ";"          	      	{ return symbol(CodesLexicaux.POINTVIRGULE); }

<YYINITIAL> "="          	      	{ yybegin(Expression); return symbol(CodesLexicaux.EGAL); }

<YYINITIAL> "\."          	      	{ yybegin(AppelMethode); return symbol(CodesLexicaux.POINT); }

<YYINITIAL> {typePrimitif}		{ return symbol(CodesLexicaux.TYPEPRIMITIF, yytext()); }

<YYINITIAL> {classe}			{ return symbol(CodesLexicaux.CLASSE, yytext()); }

<YYINITIAL> {idf}			{ return symbol(CodesLexicaux.IDF, yytext()) ; }

<Expression> {null}			{ return symbol(CodesLexicaux.NULL, yytext()) ; }

<Expression> {expr}			{ return symbol(CodesLexicaux.EXPR, yytext()) ; }

<Expression> ";"          	      	{ yybegin(YYINITIAL); return symbol(CodesLexicaux.POINTVIRGULE); }

<AppelMethode> {expr}			{ return symbol(CodesLexicaux.EXPR, yytext()) ; }

<AppelMethode> ";"          	      	{ yybegin(YYINITIAL); return symbol(CodesLexicaux.POINTVIRGULE); }

{WhiteSpace}       			{ /* just skip what was found, do nothing */ }   

/* No token was found for the input so through an error.  Print out an
   Illegal character message with the illegal character that was found. */
[^]                    { throw new Error("Illegal character <"+yytext()+">"); }


