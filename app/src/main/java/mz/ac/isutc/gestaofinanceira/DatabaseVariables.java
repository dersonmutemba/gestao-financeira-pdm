package mz.ac.isutc.gestaofinanceira;

public class DatabaseVariables {

    static final String DATABASE_NAME = "gestao_financeira.db";
    static final int DATABASE_VERSION = 1;

    public static final String USUARIO_TABLE = "usuario",
                        USUARIO_KEY = "email",
                        USUARIO_NOME = "nome",
                        USUARIO_SENHA = "senha";

    public static final String CONTA_TABLE = "conta",
                        CONTA_KEY = "id",
                        CONTA_NOME = "nome",
                        CONTA_SALDO = "saldo",
                        CONTA_BANCO = "banco",
                        CONTA_USUARIO = "usuario";

    public static final String ENTIDADE_TABLE = "entidade",
                        ENTIDADE_KEY = "id",
                        ENTIDADE_NOME = "nome",
                        ENTIDADE_CATEGORIA = "categoria",
                        ENTIDADE_USUARIO = "usuario";

    public static final String MOVIMENTO_TABLE = "movimento",
                        MOVIMENTO_KEY = "id",
                        MOVIMENTO_TIPO = "tipo",
                        MOVIMENTO_VALOR = "valor",
                        MOVIMENTO_DATA = "data",
                        MOVIMENTO_TITULO = "titulo",
                        MOVIMENTO_HORA = "hora",
                        MOVIMENTO_ENTIDADE = "entidade";

}
