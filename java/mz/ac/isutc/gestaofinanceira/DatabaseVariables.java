package mz.ac.isutc.gestaofinanceira;

public class DatabaseVariables {

    static final String DATABASE_NAME = "gestao_financeira.db";
    static final int DATABASE_VERSION = 1;

    static final String USUARIO_TABLE = "usuario",
                        USUARIO_KEY = "email",
                        USUARIO_NOME = "nome",
                        USUARIO_SENHA = "senha";

    static final String CONTA_TABLE = "conta",
                        CONTA_KEY = "id",
                        CONTA_NOME = "nome",
                        CONTA_SALDO = "saldo",
                        CONTA_BANCO = "banco",
                        CONTA_USUARIO = "usuario";

}
