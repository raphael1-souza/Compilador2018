/* Generated By:JavaCC: Do not edit this line. Compilador.java */
package parser;
import semantico.*;
import java.util.LinkedList;
import java.io.*;
import comando.*;
import apoio.*;
import geradorCodigo.*;
public class Compilador implements CompiladorConstants {
        static Tabela tabela = new Tabela();

        public static void main(String args[])  throws ParseException  {

           //Compilador analisador = null;

      try {

        Compilador analisador = new Compilador(new FileInputStream(Config.nomeArquivo+Config.extensaoFonte));
        //System.out.println("entrou");
        Compilador.inicio();
        System.out.println("An\u00e1lise l\u00e9xica, sint\u00e1tica e sem\u00e2ntico sem erros!");
      }
      catch(FileNotFoundException e) {
         System.out.println("Erro: arquivo n\u00e3o encontrado");
      }
      catch(TokenMgrError e) {
         System.out.println("Erro l\u00e9xico\u005cn" + e.getMessage());
      }
      catch(ParseException e) {
         System.out.println("Erro sint\u00e1tico\u005cn" + e.getMessage());
      }
   }

  static final public void inicio() throws ParseException {
    programa();
    jj_consume_token(0);
  }

  static final public ListaComandosAltoNivel programa() throws ParseException {
                                     ComandoAltoNivel comando = null;ListaComandosAltoNivel listaComandosAltoNivel = new ListaComandosAltoNivel();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EXIBE:
      case LEITURA:
      case SE:
      case ENQUANTO:
      case NUMERO:
      case PALAVRA:
      case VAR:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      comando = comando(listaComandosAltoNivel);
                        if(comando != null) {
                                listaComandosAltoNivel.addComando(comando);
                        }
    }
                {if (true) return listaComandosAltoNivel;}
    jj_consume_token(0);
    throw new Error("Missing return statement in function");
  }

  static final public ComandoAltoNivel comando(ListaComandosAltoNivel listaComandosAltoNivel) throws ParseException {
  ComandoAltoNivel comando = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VAR:
      comando = atribuicao(listaComandosAltoNivel);
      break;
    case NUMERO:
    case PALAVRA:
      declaracao();
      break;
    case SE:
      comando = se(listaComandosAltoNivel);
      break;
    case ENQUANTO:
      comando = enquanto(listaComandosAltoNivel);
      break;
    case LEITURA:
      comando = le(listaComandosAltoNivel);
      break;
    case EXIBE:
      comando = exibe(listaComandosAltoNivel);
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
            {if (true) return comando;}
    throw new Error("Missing return statement in function");
  }

//			EXPRESSOES
  static final public Expressao expressao() throws ParseException {
                        Expressao expressao_lista = new Expressao();
    expressao2(expressao_lista);
                                    {if (true) return expressao_lista;}
    throw new Error("Missing return statement in function");
  }

  static final public void expressao2(Expressao expressao_lista) throws ParseException {
                                               Token t;
    termo(expressao_lista);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OU:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      t = jj_consume_token(OU);
      termo(expressao_lista);
     expressao_lista.addItemPosfixo(new Operador(t, TipoOperador.OR));
    }
  }

  static final public void termo(Expressao expressao_lista) throws ParseException {
                                          Token t;
    termo1(expressao_lista);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IGUAL:
      t = jj_consume_token(IGUAL);
      termo1(expressao_lista);
                expressao_lista.addItemPosfixo(new Operador(t, TipoOperador.IGUAL));
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
  }

  static final public void termo1(Expressao expressao_lista) throws ParseException {
                                          Token t;
    termo2(expressao_lista);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CONCAT:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_3;
      }
      t = jj_consume_token(CONCAT);
      termo2(expressao_lista);
          expressao_lista.addItemPosfixo(new Operador(t, TipoOperador.CONCAT));
    }
  }

  static final public void termo2(Expressao expressao_lista) throws ParseException {
                                          Token t; TipoOperador tipo;
    termo3(expressao_lista);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SOMA:
      case SUB:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SOMA:
        t = jj_consume_token(SOMA);
                   tipo = TipoOperador.ADD;
        break;
      case SUB:
        t = jj_consume_token(SUB);
                  tipo = TipoOperador.SUB;
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      termo3(expressao_lista);
          expressao_lista.addItemPosfixo(new Operador(t, tipo));
    }
  }

  static final public void termo3(Expressao expressao_lista) throws ParseException {
                                          Token t; TipoOperador tipo;
    termo4(expressao_lista);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MUL:
      case DIV:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MUL:
        t = jj_consume_token(MUL);
                  tipo = TipoOperador.MUL;
        break;
      case DIV:
        t = jj_consume_token(DIV);
                  tipo = TipoOperador.DIV;
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      termo4(expressao_lista);
          expressao_lista.addItemPosfixo(new Operador(t, tipo));
    }
  }

  static final public void termo4(Expressao expressao_lista) throws ParseException {
                                           Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AP:
      jj_consume_token(AP);
      expressao2(expressao_lista);
      jj_consume_token(FP);
      break;
    case NUM:
      t = jj_consume_token(NUM);
                          //System.out.println("aqui veio Satanas");
                          Operando operador = new Operando(t, TipoDado.NUMERO, TipoElemento.CTE);
                          //System.out.println("matarei o golias");
                          expressao_lista.addItemPosfixo(operador);
                          //System.out.println("ate aqui JUDAS");
                          //expressao_lista.imprime();

      break;
    case SOMA:
      jj_consume_token(SOMA);
      t = jj_consume_token(NUM);
                          expressao_lista.addItemPosfixo(new Operando(t, TipoDado.NUMERO, TipoElemento.CTE));
      break;
    case SUB:
      jj_consume_token(SUB);
      t = jj_consume_token(NUM);
                          expressao_lista.addItemPosfixo(new Operando(t, TipoDado.NUMERO, TipoElemento.CTE));
      break;
    case VAR:
      t = jj_consume_token(VAR);
                                AcoesSemantica.variavelNaoDeclarada(t, tabela);
                                expressao_lista.addItemPosfixo(new Operando(t, tabela.consultaTipo(t.image), TipoElemento.VAR));
      break;
    case STRING:
      t = jj_consume_token(STRING);
                          expressao_lista.addItemPosfixo(new Operando(t, TipoDado.PALAVRA, TipoElemento.CTE));
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

//			COMANDOS
  static final public ComandoAtribuicao atribuicao(ListaComandosAltoNivel listaComandosAltoNivel) throws ParseException {
         Simbolo simb; Token t; Expressao expressao; ComandoAtribuicao comandoAtribuicao;
    t = jj_consume_token(VAR);
        AcoesSemantica.variavelNaoDeclarada(t,tabela);
    jj_consume_token(ATRIB);
    /*{System.out.println("chegou2"); }*/ expressao = expressao();
                simb = new Simbolo(t, TipoDado.PALAVRA);
                comandoAtribuicao = new ComandoAtribuicao(t,tabela.consultaSimbolo(t.image),expressao);
                //System.out.println(comandoAtribuicao+"\n"); //Cria��o do comando OK
                //System.out.println(listaComandosAltoNivel+"\n");
                //listaComandosAltoNivel.addComando(comandoAtribuicao);
                System.out.println(comandoAtribuicao.toString());
    jj_consume_token(PV);
           {if (true) return comandoAtribuicao;}
    throw new Error("Missing return statement in function");
  }

  static final public void declaracao() throws ParseException {
                     Token t; TipoDado tipo;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMERO:
      jj_consume_token(NUMERO);
                     tipo = TipoDado.NUMERO;
      break;
    case PALAVRA:
      jj_consume_token(PALAVRA);
                      tipo = TipoDado.PALAVRA;
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    t = jj_consume_token(VAR);
                   AcoesSemantica.trataDeclaracao(tabela,t,tipo);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ATRIB:
      jj_consume_token(ATRIB);
      expressao();
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VIRGULA:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_6;
      }
      jj_consume_token(VIRGULA);
      t = jj_consume_token(VAR);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ATRIB:
        jj_consume_token(ATRIB);
        expressao();
        break;
      default:
        jj_la1[13] = jj_gen;
        ;
      }
                                          AcoesSemantica.trataDeclaracao(tabela,t,tipo);
    }
    jj_consume_token(PV);
  }

  static final public ComandoCondicional se(ListaComandosAltoNivel listaComandosAltoNivel) throws ParseException {
 Token t; Expressao expressao; ComandoCondicionalSimples comandoCondicional;
    t = jj_consume_token(SE);
    jj_consume_token(AP);
    expressao = expressao();
    jj_consume_token(FP);
    listaComandosAltoNivel = programa();
           comandoCondicional = new ComandoCondicionalSimples(t, expressao,listaComandosAltoNivel);
           //listaComandosAltoNivel.addComando(comandoCondicional);
           System.out.println(comandoCondicional.toString());
    jj_consume_token(FIMSE);
                 {if (true) return comandoCondicional;}
    throw new Error("Missing return statement in function");
  }

  static final public ComandoEnquanto enquanto(ListaComandosAltoNivel listaComandosAltoNivel) throws ParseException {
  Token t; Expressao expressao; ComandoEnquanto comandoEnquanto;
    t = jj_consume_token(ENQUANTO);
    jj_consume_token(AP);
    expressao = expressao();
    jj_consume_token(FP);
    listaComandosAltoNivel = programa();
           comandoEnquanto = new ComandoEnquanto(t, expressao,listaComandosAltoNivel);
           //listaComandosAltoNivel.addComando(comandoEnquanto);
           System.out.println(comandoEnquanto.toString());
    jj_consume_token(FIMENQUANTO);
                       {if (true) return comandoEnquanto;}
    throw new Error("Missing return statement in function");
  }

  static final public ComandoEntrada le(ListaComandosAltoNivel listaComandosAltoNivel) throws ParseException {
                                                                     Token t; Token variavel; Simbolo simb; ComandoEntrada comandoEntrada;
    t = jj_consume_token(LEITURA);
    variavel = jj_consume_token(VAR);
                AcoesSemantica.variavelNaoDeclarada(variavel,tabela);
                simb = new Simbolo(t, TipoDado.PALAVRA);
                comandoEntrada = new ComandoEntrada(t,simb);
                //listaComandosAltoNivel.addComando(comandoEntrada);
                System.out.println(comandoEntrada.toString());
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VIRGULA:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_7;
      }
      jj_consume_token(VIRGULA);
      jj_consume_token(VAR);
                  AcoesSemantica.variavelNaoDeclarada(variavel,tabela);
                  simb = new Simbolo(t, TipoDado.PALAVRA);
                  comandoEntrada = new ComandoEntrada(t,simb);
                  //listaComandosAltoNivel.addComando(comandoEntrada);
                  System.out.println(comandoEntrada.toString());
    }
    jj_consume_token(PV);
                  {if (true) return comandoEntrada;}
    throw new Error("Missing return statement in function");
  }

  static final public ComandoSaida exibe(ListaComandosAltoNivel listaComandosAltoNivel) throws ParseException {
                                                                     Token t; Expressao expressao; ComandoSaida comandoSaida;
    t = jj_consume_token(EXIBE);
    expressao = expressao();
           comandoSaida = new ComandoSaida(t,expressao);
           //listaComandosAltoNivel.addComando(comandoSaida);
           System.out.println(comandoSaida.toString());
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VIRGULA:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_8;
      }
      jj_consume_token(VIRGULA);
      expressao = expressao();
                  comandoSaida = new ComandoSaida(t,expressao);
                  //listaComandosAltoNivel.addComando(comandoSaida);
              System.out.println(comandoSaida.toString());
    }
    jj_consume_token(PV);
                  {if (true) return comandoSaida;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public CompiladorTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[16];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x81ae0,0x81ae0,0x4000000,0x10000000,0x20000000,0xc00000,0xc00000,0x3000000,0x3000000,0xec4000,0x1800,0x8000000,0x20000,0x8000000,0x20000,0x20000,};
   }

  /** Constructor with InputStream. */
  public Compilador(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Compilador(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CompiladorTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Compilador(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CompiladorTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Compilador(CompiladorTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CompiladorTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[30];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 16; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 30; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
