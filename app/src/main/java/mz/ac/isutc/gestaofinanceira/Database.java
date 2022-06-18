package mz.ac.isutc.gestaofinanceira;

import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.CONTA_BANCO;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.CONTA_KEY;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.CONTA_NOME;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.CONTA_SALDO;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.CONTA_TABLE;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.CONTA_USUARIO;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.DATABASE_NAME;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.DATABASE_VERSION;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.ENTIDADE_CATEGORIA;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.ENTIDADE_KEY;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.ENTIDADE_NOME;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.ENTIDADE_TABLE;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.ENTIDADE_USUARIO;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.MOVIMENTO_DATA;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.MOVIMENTO_ENTIDADE;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.MOVIMENTO_HORA;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.MOVIMENTO_KEY;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.MOVIMENTO_TABLE;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.MOVIMENTO_TIPO;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.MOVIMENTO_TITULO;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.MOVIMENTO_VALOR;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.USUARIO_KEY;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.USUARIO_NOME;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.USUARIO_SENHA;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.USUARIO_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USUARIO_TABLE + " (" +
                USUARIO_KEY + " TEXT PRIMARY KEY," +
                USUARIO_NOME + " TEXT NOT NULL," +
                USUARIO_SENHA + " TEXT NOT NULL)");
        db.execSQL("CREATE TABLE " + CONTA_TABLE + " (" +
                CONTA_KEY + " NUMBER PRIMARY KEY," +
                CONTA_NOME + " TEXT NOT NULL," +
                CONTA_SALDO + " REAL NOT NULL," +
                CONTA_BANCO + " TEXT," +
                CONTA_USUARIO + " TEXT NOT NULL," +
                " FOREIGN KEY (" + CONTA_USUARIO + ")" +
                " REFERENCES " + USUARIO_TABLE + "(" + USUARIO_KEY + ")" + ")");
        db.execSQL("CREATE TABLE " + ENTIDADE_TABLE + "(" +
                ENTIDADE_KEY + " NUMBER PRIMARY KEY," +
                ENTIDADE_NOME + " TEXT NOT NULL," +
                ENTIDADE_CATEGORIA + " TEXT," +
                ENTIDADE_USUARIO + " TEXT NOT NULL," +
                " FOREIGN KEY (" + ENTIDADE_USUARIO + ")" +
                " REFERENCES " + USUARIO_TABLE + "(" + USUARIO_KEY + ")" + ")");
        db.execSQL("CREATE TABLE " + MOVIMENTO_TABLE + "(" +
                MOVIMENTO_KEY + " NUMBER PRIMARY KEY," +
                MOVIMENTO_TIPO + " TEXT NOT NULL," +
                MOVIMENTO_VALOR + " REAL NOT NULL," +
                MOVIMENTO_DATA + " TEXT NOT NULL," +
                MOVIMENTO_TITULO + " TEXT NOT NULL," +
                MOVIMENTO_HORA + " TEXT NOT NULL," +
                MOVIMENTO_ENTIDADE + " NUMBER NOT NULL," +
                " FOREIGN KEY (" + MOVIMENTO_ENTIDADE + ")" +
                " REFERENCES " + ENTIDADE_TABLE + "(" + ENTIDADE_KEY + ")" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MOVIMENTO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ENTIDADE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CONTA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USUARIO_TABLE);
    }

    public long insertUsuario(String email, String username, String senha) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_KEY, email);
        contentValues.put(USUARIO_NOME, username);
        contentValues.put(USUARIO_SENHA, senha);
        return sqLiteDatabase.insert(USUARIO_TABLE, null, contentValues);
    }

    public long updateUsuario(String email, String username, String senha) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_NOME, username);
        contentValues.put(USUARIO_SENHA, senha);
        return sqLiteDatabase.update(USUARIO_TABLE, contentValues,
                "WHERE " + USUARIO_KEY + " =?", new String[]{email});
    }

    public long removeUsuario(String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(USUARIO_TABLE,
                "WHERE " + USUARIO_KEY + " =?", new String[]{email});
    }

    public Cursor getUsuario (String[] email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + USUARIO_TABLE + " WHERE " +
                USUARIO_KEY + " = ?", email);
    }

    public long insertConta(long id, String accountName, String associatedBank,
                            double accountAmount, String usuario) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTA_KEY, id);
        contentValues.put(CONTA_NOME, accountName);
        contentValues.put(CONTA_BANCO, associatedBank);
        contentValues.put(CONTA_SALDO, accountAmount);
        contentValues.put(CONTA_USUARIO, usuario);
        return sqLiteDatabase.insert(CONTA_TABLE, null, contentValues);
    }

    public long insertConta (long id, String accountName, double accountAmount, String usuario) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTA_KEY, id);
        contentValues.put(CONTA_NOME, accountName);
        contentValues.put(CONTA_SALDO, accountAmount);
        contentValues.put(CONTA_USUARIO, usuario);
        return sqLiteDatabase.insert(CONTA_TABLE, null, contentValues);
    }

    public long updateConta(long id, String accountName, String associatedBank,
                            double accountAmount) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTA_NOME, accountName);
        contentValues.put(CONTA_BANCO, associatedBank);
        contentValues.put(CONTA_SALDO, accountAmount);
        return sqLiteDatabase.update(CONTA_TABLE, contentValues,
                CONTA_KEY + " =?", new String[]{id + ""});
    }

    public long updateConta(long id, String accountName, double accountAmount) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTA_NOME, accountName);
        contentValues.put(CONTA_SALDO, accountAmount);
        return sqLiteDatabase.update(CONTA_TABLE, contentValues,
                CONTA_KEY + " =?", new String[]{id + ""});
    }

    public long removeConta(long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(CONTA_TABLE,
                CONTA_KEY + " =?", new String[]{id + ""});
    }

    public Cursor getContas (String[] usuarios) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + CONTA_TABLE + " WHERE " +
                CONTA_USUARIO + " = ?", usuarios);
    }

    public Cursor getConta (long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + CONTA_TABLE + " WHERE " +
                CONTA_KEY + " = ?", new String[]{id + ""});
    }

    public ArrayList<Conta> getContasArrayList (String[] usuarios) {
        Cursor cursor = getContas(usuarios);
        ArrayList<Conta> contas = new ArrayList<Conta>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            contas.add(new Conta(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(3),
                    cursor.getDouble(2),
                    cursor.getString(4)
            ));
        }
        return contas;
    }

    public Conta getObjectConta(long id) {
        Cursor cursor = getConta(id);
        if (cursor.getCount() <= 0)
            return null;
        cursor.moveToFirst();
        return new Conta(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(3),
                cursor.getDouble(2),
                cursor.getString(4)
        );
    }

    public long insertEntidade(long id, String nome, String categoria, String usuario) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ENTIDADE_KEY, id);
        contentValues.put(ENTIDADE_NOME, nome);
        contentValues.put(ENTIDADE_CATEGORIA, categoria);
        contentValues.put(ENTIDADE_USUARIO, usuario);
        return sqLiteDatabase.insert(ENTIDADE_TABLE, null, contentValues);
    }

    public long updateEntidade(long id, String nome, String categoria) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ENTIDADE_NOME, nome);
        contentValues.put(ENTIDADE_CATEGORIA, categoria);
        return sqLiteDatabase.update(ENTIDADE_TABLE, contentValues,
                ENTIDADE_KEY + " =?", new String[]{id + ""});
    }

    public long removeEntidade(long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(ENTIDADE_TABLE,
                ENTIDADE_KEY + " =?", new String[]{id + ""});
    }

    public Cursor getEntidadesByName (String[] name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + ENTIDADE_TABLE + " WHERE " +
                ENTIDADE_NOME + " = ?", name);
    }

    public Cursor getEntidadesByUsuario (String[] usuario) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + ENTIDADE_TABLE + " WHERE " +
                ENTIDADE_USUARIO + " = ?", usuario);
    }

    public Cursor getEntidadesByCategoria (String[] categoria) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + ENTIDADE_TABLE + " WHERE " +
                ENTIDADE_CATEGORIA + " = ?", categoria);
    }

    public ArrayList<Entidade> getEntidadesByNameArrayList(String[] name) {
        Cursor cursor = getEntidadesByName(name);
        ArrayList<Entidade> entidades = new ArrayList<>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            entidades.add(new Entidade(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));
        }
        return entidades;
    }

    public ArrayList<Entidade> getEntidadesByUsuarioArrayList(String[] usuario) {
        Cursor cursor = getEntidadesByUsuario(usuario);
        ArrayList<Entidade> entidades = new ArrayList<>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            entidades.add(new Entidade(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));
        }
        return entidades;
    }

    public ArrayList<Entidade> getEntidadesByCategoriaArrayList(String[] categoria) {
        Cursor cursor = getEntidadesByCategoria(categoria);
        ArrayList<Entidade> entidades = new ArrayList<>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            entidades.add(new Entidade(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));
        }
        return entidades;
    }

    public long insertMovimento(long id, String tipo, double valor, String data, String titulo, String hora, long entidade) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOVIMENTO_KEY, id);
        contentValues.put(MOVIMENTO_TIPO, tipo);
        contentValues.put(MOVIMENTO_VALOR, valor);
        contentValues.put(MOVIMENTO_DATA, data);
        contentValues.put(MOVIMENTO_TITULO, titulo);
        contentValues.put(MOVIMENTO_HORA, hora);
        contentValues.put(MOVIMENTO_ENTIDADE, entidade);
        return sqLiteDatabase.insert(MOVIMENTO_TABLE, null, contentValues);
    }

    public Cursor getMovimentosByTipo (String[] tipo) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + MOVIMENTO_TABLE + " WHERE " +
                MOVIMENTO_TIPO + " =?", tipo);
    }

    public Cursor getMovimentosByEntidade (String[] entidade) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + MOVIMENTO_TABLE + " WHERE " +
                MOVIMENTO_ENTIDADE + " =?", entidade);
    }

    public ArrayList<Movimento> getMovimentosByTipoArrayList (String[] tipo) {
        Cursor cursor = getMovimentosByTipo(tipo);
        ArrayList<Movimento> movimentos = new ArrayList<>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            movimentos.add(new Movimento(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getLong(6)
            ));
        }
        return movimentos;
    }

    public ArrayList<Movimento> getMovimentosByEntidadeArrayList (String[] entidade) {
        Cursor cursor = getMovimentosByEntidade(entidade);
        ArrayList<Movimento> movimentos = new ArrayList<>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            movimentos.add(new Movimento(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getLong(6)
            ));
        }
        return movimentos;
    }

}
