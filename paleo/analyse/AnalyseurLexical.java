/*
 * PALEO: Petite Application Logicielle d'Etude Objet
 *
 * <p>PALEO est un pseudo-compilateur generant des schemas memoires en fonction d'instructions Java.</p>
 * 
 * Projet de Synthese (LCIN4U51)
 * Licence Informatique Semestre 4
 * Universite Henri Poincare (UHP Nancy)
 * 
 * @author: Jan KEROMNES
 * @version: 1.0
 * 
 */

package paleo.analyse;

import java.io.IOException;

import java_cup.runtime.Symbol;

// TODO: Auto-generated Javadoc
/**
 * L'Analyseur Lexical.
 */
public class AnalyseurLexical implements java_cup.runtime.Scanner {

	/** The Constant YYEOF. */
	public static final int YYEOF = -1;

	/** The Constant ZZ_BUFFERSIZE. */
	private static final int ZZ_BUFFERSIZE = 16384;

	/** The Constant YYINITIAL. */
	public static final int YYINITIAL = 0;
	
	/** The Constant Expression. */
	public static final int Expression = 2;
	
	/** The Constant AppelMethode. */
	public static final int AppelMethode = 4;

	/** The Constant ZZ_LEXSTATE. */
	private static final int ZZ_LEXSTATE[] = { 0, 0, 1, 1, 2, 2 };

	/** The Constant ZZ_CMAP_PACKED. */
	private static final String ZZ_CMAP_PACKED = "\11\0\1\32\1\31\1\0\1\32\1\30\22\0\1\32\11\0\1\27"
			+ "\3\0\1\34\1\26\12\23\1\0\1\25\1\0\1\33\3\0\32\22"
			+ "\4\0\1\24\1\0\1\10\1\13\1\21\1\11\1\14\1\7\1\6"
			+ "\1\16\1\1\2\24\1\4\1\24\1\2\1\5\2\24\1\17\1\15"
			+ "\1\3\1\12\3\24\1\20\1\24\uff85\0";

	/** The Constant ZZ_CMAP. */
	private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

	/** The Constant ZZ_ACTION. */
	private static final int[] ZZ_ACTION = zzUnpackAction();

	/** The Constant ZZ_ACTION_PACKED_0. */
	private static final String ZZ_ACTION_PACKED_0 = "\1\0\2\1\1\2\10\3\1\4\1\5\1\2\2\6"
			+ "\1\7\1\10\2\1\1\11\1\1\10\3\1\12\1\0"
			+ "\1\1\1\13\7\3\1\0\1\1\2\3\1\12\1\14" + "\2\3";

	/** The Constant ZZ_ROWMAP. */
	private static final int[] ZZ_ROWMAP = zzUnpackRowMap();

	/** The Constant ZZ_ROWMAP_PACKED_0. */
	private static final String ZZ_ROWMAP_PACKED_0 = "\0\0\0\35\0\72\0\127\0\164\0\221\0\256\0\313"
			+ "\0\350\0\u0105\0\u0122\0\u013f\0\u015c\0\127\0\u0179\0\u0196"
			+ "\0\127\0\127\0\127\0\u01b3\0\u01d0\0\127\0\u01ed\0\u020a"
			+ "\0\u0227\0\u0244\0\u0261\0\u027e\0\u029b\0\u02b8\0\u02d5\0\u02f2"
			+ "\0\u030f\0\u032c\0\221\0\u0349\0\u0366\0\u0383\0\u03a0\0\u03bd"
			+ "\0\u03da\0\u03f7\0\u0414\0\u0431\0\u044e\0\u046b\0\127\0\u0488"
			+ "\0\u04a5\0\u04c2";

	/** The Constant ZZ_TRANS. */
	private static final int[] ZZ_TRANS = zzUnpackTrans();

	/** The Constant ZZ_TRANS_PACKED_0. */
	private static final String ZZ_TRANS_PACKED_0 = "\1\4\1\5\2\6\1\7\2\6\1\10\1\6\1\11"
			+ "\1\6\1\12\1\6\1\13\3\6\1\14\1\15\1\4"
			+ "\1\6\1\16\1\17\1\4\1\20\2\21\1\22\1\23"
			+ "\2\24\1\25\22\24\1\26\2\24\3\27\27\24\1\26"
			+ "\7\24\36\0\1\6\1\30\22\6\11\0\24\6\11\0"
			+ "\4\6\1\31\17\6\11\0\3\6\1\32\20\6\11\0"
			+ "\4\6\1\33\17\6\11\0\4\6\1\34\12\6\1\35"
			+ "\4\6\11\0\15\6\1\36\6\6\11\0\15\6\1\37"
			+ "\6\6\11\0\24\15\36\0\1\40\1\41\36\0\1\21"
			+ "\3\0\25\24\1\0\21\24\1\42\12\24\1\0\11\24"
			+ "\1\25\22\24\1\0\2\24\3\27\2\24\1\0\2\6"
			+ "\1\43\21\6\11\0\1\6\1\44\22\6\11\0\4\6"
			+ "\1\45\17\6\11\0\11\6\1\46\12\6\11\0\4\6"
			+ "\1\47\17\6\11\0\2\6\1\50\21\6\11\0\4\6"
			+ "\1\51\17\6\11\0\7\6\1\52\14\6\10\0\31\40"
			+ "\1\0\3\40\27\41\1\53\5\41\4\24\1\54\20\24"
			+ "\1\0\7\24\1\0\5\6\1\43\16\6\11\0\7\6"
			+ "\1\30\14\6\11\0\12\6\1\55\11\6\11\0\3\6"
			+ "\1\56\20\6\11\0\13\6\1\43\10\6\11\0\16\6"
			+ "\1\30\5\6\11\0\16\6\1\43\5\6\10\0\26\41"
			+ "\1\57\1\53\5\41\4\24\1\60\20\24\1\0\7\24"
			+ "\1\0\3\6\1\50\20\6\11\0\13\6\1\61\10\6"
			+ "\10\0\25\24\1\0\2\24\3\60\2\24\1\0\7\6"
			+ "\1\62\14\6\11\0\1\6\1\43\22\6\10\0";

	/* error codes */
	/** The Constant ZZ_UNKNOWN_ERROR. */
	private static final int ZZ_UNKNOWN_ERROR = 0;

	/** The Constant ZZ_NO_MATCH. */
	private static final int ZZ_NO_MATCH = 1;

	/** The Constant ZZ_PUSHBACK_2BIG. */
	private static final int ZZ_PUSHBACK_2BIG = 2;

	/* error messages for the codes above */
	/** The Constant ZZ_ERROR_MSG. */
	private static final String ZZ_ERROR_MSG[] = {
			"Unkown internal scanner error", "Error: could not match input",
			"Error: pushback value was too large" };

	/** The Constant ZZ_ATTRIBUTE. */
	private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();

	/** The Constant ZZ_ATTRIBUTE_PACKED_0. */
	private static final String ZZ_ATTRIBUTE_PACKED_0 = "\1\0\2\1\1\11\11\1\1\11\2\1\3\11\2\1"
			+ "\1\11\12\1\1\0\11\1\1\0\3\1\1\11\3\1";

	/**
	 * Zz unpack action.
	 * 
	 * @return the int[]
	 */
	private static int[] zzUnpackAction() {
		int[] result = new int[50];
		int offset = 0;
		offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
		return result;
	}

	/**
	 * Zz unpack action.
	 * 
	 * @param packed
	 *            the packed
	 * @param offset
	 *            the offset
	 * @param result
	 *            the result
	 * @return the int
	 */
	private static int zzUnpackAction(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do {
				result[j++] = value;
			} while (--count > 0);
		}
		return j;
	}

	/**
	 * Zz unpack attribute.
	 * 
	 * @return the int[]
	 */
	private static int[] zzUnpackAttribute() {
		int[] result = new int[50];
		int offset = 0;
		offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
		return result;
	}

	/**
	 * Zz unpack attribute.
	 * 
	 * @param packed
	 *            the packed
	 * @param offset
	 *            the offset
	 * @param result
	 *            the result
	 * @return the int
	 */
	private static int zzUnpackAttribute(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do {
				result[j++] = value;
			} while (--count > 0);
		}
		return j;
	}

	/**
	 * Zz unpack c map.
	 * 
	 * @param packed
	 *            the packed
	 * @return the char[]
	 */
	private static char[] zzUnpackCMap(String packed) {
		char[] map = new char[0x10000];
		int i = 0; /* index in packed string */
		int j = 0; /* index in unpacked array */
		while (i < 92) {
			int count = packed.charAt(i++);
			char value = packed.charAt(i++);
			do {
				map[j++] = value;
			} while (--count > 0);
		}
		return map;
	}

	/**
	 * Zz unpack row map.
	 * 
	 * @return the int[]
	 */
	private static int[] zzUnpackRowMap() {
		int[] result = new int[50];
		int offset = 0;
		offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
		return result;
	}

	/**
	 * Zz unpack row map.
	 * 
	 * @param packed
	 *            the packed
	 * @param offset
	 *            the offset
	 * @param result
	 *            the result
	 * @return the int
	 */
	private static int zzUnpackRowMap(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int high = packed.charAt(i++) << 16;
			result[j++] = high | packed.charAt(i++);
		}
		return j;
	}

	/**
	 * Zz unpack trans.
	 * 
	 * @return the int[]
	 */
	private static int[] zzUnpackTrans() {
		int[] result = new int[1247];
		int offset = 0;
		offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
		return result;
	}

	/**
	 * Zz unpack trans.
	 * 
	 * @param packed
	 *            the packed
	 * @param offset
	 *            the offset
	 * @param result
	 *            the result
	 * @return the int
	 */
	private static int zzUnpackTrans(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			value--;
			do {
				result[j++] = value;
			} while (--count > 0);
		}
		return j;
	}

	/** The zz reader. */
	private java.io.Reader zzReader;

	/** The zz state. */
	private int zzState;

	/** The zz lexical state. */
	private int zzLexicalState = YYINITIAL;

	/** The zz buffer. */
	private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

	/** The zz marked pos. */
	private int zzMarkedPos;

	/** The zz current pos. */
	private int zzCurrentPos;

	/** The zz start read. */
	private int zzStartRead;

	/** The zz end read. */
	private int zzEndRead;

	/** The yyline. */
	private int yyline;

	/** The yychar. */
	private int yychar;

	/** The yycolumn. */
	private int yycolumn;

	/** The zz at eof. */
	private boolean zzAtEOF;

	/** The zz eof done. */
	private boolean zzEOFDone;

	/**
	 * Instantiates a new analyseur lexical.
	 * 
	 * @param in
	 *            the in
	 */
	public AnalyseurLexical(java.io.InputStream in) {
		this(new java.io.InputStreamReader(in));
	}

	/**
	 * Instantiates a new analyseur lexical.
	 * 
	 * @param in
	 *            the in
	 */
	public AnalyseurLexical(java.io.Reader in) {
		zzReader = in;
	}

	/* (non-Javadoc)
	 * @see java_cup.runtime.Scanner#next_token()
	 */
	public Symbol next_token() throws java.io.IOException {
		int zzInput;
		int zzAction;

		// cached fields:
		int zzCurrentPosL;
		int zzMarkedPosL;
		int zzEndReadL = zzEndRead;
		char[] zzBufferL = zzBuffer;
		char[] zzCMapL = ZZ_CMAP;

		int[] zzTransL = ZZ_TRANS;
		int[] zzRowMapL = ZZ_ROWMAP;
		int[] zzAttrL = ZZ_ATTRIBUTE;

		while (true) {
			zzMarkedPosL = zzMarkedPos;

			boolean zzR = false;
			for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL; zzCurrentPosL++) {
				switch (zzBufferL[zzCurrentPosL]) {
				case '\u000B':
				case '\u000C':
				case '\u0085':
				case '\u2028':
				case '\u2029':
					yyline++;
					yycolumn = 0;
					zzR = false;
					break;
				case '\r':
					yyline++;
					yycolumn = 0;
					zzR = true;
					break;
				case '\n':
					if (zzR) {
						zzR = false;
					} else {
						yyline++;
						yycolumn = 0;
					}
					break;
				default:
					zzR = false;
					yycolumn++;
				}
			}

			if (zzR) {
				// peek one character ahead if it is \n (if we have counted one
				// line too much)
				boolean zzPeek;
				if (zzMarkedPosL < zzEndReadL) {
					zzPeek = zzBufferL[zzMarkedPosL] == '\n';
				} else if (zzAtEOF) {
					zzPeek = false;
				} else {
					boolean eof = zzRefill();
					zzEndReadL = zzEndRead;
					zzMarkedPosL = zzMarkedPos;
					zzBufferL = zzBuffer;
					if (eof) {
						zzPeek = false;
					} else {
						zzPeek = zzBufferL[zzMarkedPosL] == '\n';
					}
				}
				if (zzPeek) {
					yyline--;
				}
			}
			zzAction = -1;

			zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

			zzState = ZZ_LEXSTATE[zzLexicalState];

			zzForAction: {
				while (true) {

					if (zzCurrentPosL < zzEndReadL) {
						zzInput = zzBufferL[zzCurrentPosL++];
					} else if (zzAtEOF) {
						zzInput = YYEOF;
						break zzForAction;
					} else {
						// store back cached positions
						zzCurrentPos = zzCurrentPosL;
						zzMarkedPos = zzMarkedPosL;
						boolean eof = zzRefill();
						// get translated positions and possibly new buffer
						zzCurrentPosL = zzCurrentPos;
						zzMarkedPosL = zzMarkedPos;
						zzBufferL = zzBuffer;
						zzEndReadL = zzEndRead;
						if (eof) {
							zzInput = YYEOF;
							break zzForAction;
						} else {
							zzInput = zzBufferL[zzCurrentPosL++];
						}
					}
					int zzNext = zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput]];
					if (zzNext == -1) {
						break zzForAction;
					}
					zzState = zzNext;

					int zzAttributes = zzAttrL[zzState];
					if ((zzAttributes & 1) == 1) {
						zzAction = zzState;
						zzMarkedPosL = zzCurrentPosL;
						if ((zzAttributes & 8) == 8) {
							break zzForAction;
						}
					}

				}
			}

			// store back cached position
			zzMarkedPos = zzMarkedPosL;

			switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
			case 9: {
				yybegin(YYINITIAL);
				return symbol(CodesLexicaux.POINTVIRGULE);
			}
			case 13:
				break;
			case 4: {
				return symbol(CodesLexicaux.CLASSE, yytext());
			}
			case 14:
				break;
			case 1: {
				return symbol(CodesLexicaux.EXPR, yytext());
			}
			case 15:
				break;
			case 12: {
				return symbol(CodesLexicaux.NULL, yytext());
			}
			case 16:
				break;
			case 11: {
				return symbol(CodesLexicaux.TYPEPRIMITIF, yytext());
			}
			case 17:
				break;
			case 3: {
				return symbol(CodesLexicaux.IDF, yytext());
			}
			case 18:
				break;
			case 6: { /* just skip what was found, do nothing */
			}
			case 19:
				break;
			case 8: {
				yybegin(AppelMethode);
				return symbol(CodesLexicaux.POINT);
			}
			case 20:
				break;
			case 7: {
				yybegin(Expression);
				return symbol(CodesLexicaux.EGAL);
			}
			case 21:
				break;
			case 2: {
				throw new Error("Illegal character <" + yytext() + ">");
			}
			case 22:
				break;
			case 5: {
				return symbol(CodesLexicaux.POINTVIRGULE);
			}
			case 23:
				break;
			case 10: {
			}
			case 24:
				break;
			default:
				if ((zzInput == YYEOF) && (zzStartRead == zzCurrentPos)) {
					zzAtEOF = true;
					zzDoEOF();
					{
						return symbol(CodesLexicaux.EOF);
					}
				} else {
					zzScanError(ZZ_NO_MATCH);
				}
			}
		}
	}

	/* user code: */
	/**
	 * Symbol.
	 * 
	 * @param type
	 *            the type
	 * @return the symbol
	 */
	private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}

	/**
	 * Symbol.
	 * 
	 * @param type
	 *            the type
	 * @param value
	 *            the value
	 * @return the symbol
	 */
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}

	/**
	 * Yybegin.
	 * 
	 * @param newState
	 *            the new state
	 */
	public final void yybegin(int newState) {
		zzLexicalState = newState;
	}

	/**
	 * Yycharat.
	 * 
	 * @param pos
	 *            the pos
	 * @return the char
	 */
	public final char yycharat(int pos) {
		return zzBuffer[zzStartRead + pos];
	}

	/**
	 * Yyclose.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public final void yyclose() throws java.io.IOException {
		zzAtEOF = true; /* indicate end of file */
		zzEndRead = zzStartRead; /* invalidate buffer */

		if (zzReader != null) {
			zzReader.close();
		}
	}

	/**
	 * Yylength.
	 * 
	 * @return the int
	 */
	public final int yylength() {
		return zzMarkedPos - zzStartRead;
	}

	/**
	 * Yypushback.
	 * 
	 * @param number
	 *            the number
	 */
	public void yypushback(int number) {
		if (number > yylength()) {
			zzScanError(ZZ_PUSHBACK_2BIG);
		}

		zzMarkedPos -= number;
	}

	/**
	 * Yyreset.
	 * 
	 * @param reader
	 *            the reader
	 */
	public final void yyreset(java.io.Reader reader) {
		zzReader = reader;
		zzAtEOF = false;
		zzEOFDone = false;
		zzEndRead = zzStartRead = 0;
		zzCurrentPos = zzMarkedPos = 0;
		yyline = yychar = yycolumn = 0;
		zzLexicalState = YYINITIAL;
	}

	/**
	 * Yystate.
	 * 
	 * @return the int
	 */
	public final int yystate() {
		return zzLexicalState;
	}

	/**
	 * Yytext.
	 * 
	 * @return the string
	 */
	public final String yytext() {
		return new String(zzBuffer, zzStartRead, zzMarkedPos - zzStartRead);
	}

	/**
	 * Zz do eof.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void zzDoEOF() throws java.io.IOException {
		if (!zzEOFDone) {
			zzEOFDone = true;
			yyclose();
		}
	}

	/**
	 * Zz refill.
	 * 
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private boolean zzRefill() throws java.io.IOException {

		/* first: make room (if you can) */
		if (zzStartRead > 0) {
			System.arraycopy(zzBuffer, zzStartRead, zzBuffer, 0, zzEndRead
					- zzStartRead);

			/* translate stored positions */
			zzEndRead -= zzStartRead;
			zzCurrentPos -= zzStartRead;
			zzMarkedPos -= zzStartRead;
			zzStartRead = 0;
		}

		/* is the buffer big enough? */
		if (zzCurrentPos >= zzBuffer.length) {
			/* if not: blow it up */
			char newBuffer[] = new char[zzCurrentPos * 2];
			System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
			zzBuffer = newBuffer;
		}

		/* finally: fill the buffer with new input */
		int numRead = zzReader.read(zzBuffer, zzEndRead, zzBuffer.length
				- zzEndRead);

		if (numRead > 0) {
			zzEndRead += numRead;
			return false;
		}
		// unlikely but not impossible: read 0 characters, but not at end of
		// stream
		if (numRead == 0) {
			int c = zzReader.read();
			if (c == -1) {
				return true;
			} else {
				zzBuffer[zzEndRead++] = (char) c;
				return false;
			}
		}

		// numRead < 0
		return true;
	}

	/**
	 * Zz scan error.
	 * 
	 * @param errorCode
	 *            the error code
	 */
	private void zzScanError(int errorCode) {
		String message;
		try {
			message = ZZ_ERROR_MSG[errorCode];
		} catch (ArrayIndexOutOfBoundsException e) {
			message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
		}

		throw new Error(message);
	}

}
