package mz.ac.isutc.gestaofinanceira;

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

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + USUARIO_TABLE + " (" +
            USUARIO_KEY + " TEXT PRIMARY KEY," +
            USUARIO_NOME + " TEXT," +
            USUARIO_SENHA + " TEXT)";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertUsuario(String email, String username, String senha) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USUARIO_KEY, email);
        contentValues.put(USUARIO_NOME, username);
        contentValues.put(USUARIO_SENHA, senha);
        return sqLiteDatabase.insert(USUARIO_TABLE, null, contentValues);
    }

    public Cursor getUsuario () {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + USUARIO_TABLE, null);
    }

    public Cursor getUsuario (String[] email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + USUARIO_TABLE + " WHERE " +
                USUARIO_KEY + " = ?", email);
    }
}
