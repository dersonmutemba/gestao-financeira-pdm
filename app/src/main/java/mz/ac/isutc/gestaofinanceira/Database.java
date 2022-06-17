package mz.ac.isutc.gestaofinanceira;

import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.CONTA_BANCO;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.CONTA_KEY;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.CONTA_NOME;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.CONTA_SALDO;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.CONTA_TABLE;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.CONTA_USUARIO;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.DATABASE_NAME;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.DATABASE_VERSION;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.USUARIO_KEY;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.USUARIO_NOME;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.USUARIO_SENHA;
import static mz.ac.isutc.gestaofinanceira.DatabaseVariables.USUARIO_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
                " REFERENCES " + USUARIO_TABLE + "(" + USUARIO_KEY +")" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USUARIO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CONTA_TABLE);
    }

    public long insertUsuario(String email, String username, String senha) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_KEY, email);
        contentValues.put(USUARIO_NOME, username);
        contentValues.put(USUARIO_SENHA, senha);
        return sqLiteDatabase.insert(USUARIO_TABLE, null, contentValues);
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

    public Cursor getContas (String[] usuarios) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + CONTA_TABLE + " WHERE " +
                CONTA_USUARIO + " = ?", usuarios);
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
}
