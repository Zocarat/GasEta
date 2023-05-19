package devandroid.zocarato.appgaseta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import devandroid.zocarato.appgaseta.model.Combustivel;

public class GasEtaDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "gaseta.db";
    private static  final int DB_VERSION = 1;

    Cursor cursor;

    SQLiteDatabase db;

    public GasEtaDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // QUERY SQL para criar uma tabela
        String sqlTabelaCombustivel =
                "CREATE TABLE Combustivel (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nomeDoCombustivel TEXT, " +
                        "precoDoCombustivel REAL, " +
                        "recomendacao TEXT)";

        db.execSQL(sqlTabelaCombustivel);

        int i = 0;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void salvarObjeto (String tabela, ContentValues dados){

        db.insert(tabela, null, dados);
    }

    public List<Combustivel> listarDados (){

        List<Combustivel> lista = new ArrayList<>();

        Combustivel registro;            //representa um registro que salvo na tabela Combustivel do banco de dados

        String querySQL = "SELECT * FROM Combustivel";

        cursor = db.rawQuery(querySQL, null);

        if (cursor.moveToFirst()){

            do {

                registro = new Combustivel();

                registro.setId(cursor.getInt(0));
                registro.setNomeDoCombustivel(cursor.getString(1));
                registro.setPrecoDoCombustivel(cursor.getDouble(2 ));
                registro.setRecomendacao(cursor.getString(3));

                lista.add(registro);



            }while (cursor.moveToNext());      //Move o cursor para o proximo registro


        }else {
            // se o cursor falhar e cai dentro deste bloco
        }

        return lista;


    }




}

//Método para implementar um GRUD
// C - Create criar o banco de dados e as tabelas
// Create data base nome_do_banco_de_dados.db
// 1 - Nome e do canco de dados
// 2 - Versão do banco de dados




// Create table (SQL)

// R - Retrieve recuperar os dados salvos nas tabelas
// Select * from  table (SQL)

// U - Update alterar os dados que existem em um registro de tabela
// Update from table

// D -  Delete deletar os dados/registro de uma tabela
// Delete from (SQL)
