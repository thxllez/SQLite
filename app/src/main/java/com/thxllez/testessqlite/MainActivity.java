package com.thxllez.testessqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //SQLITE:

        try{
            //criar banco de dados
            SQLiteDatabase bancoDados;
            bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null); //abre um banco de dados existente ou cria um q nao existe.
            //1 param: nome da database ; 2 param: modo > private > apenas o meu app acessa esse banco ; 3 param: factory

            //criar tabela
            //bancoDados.execSQL("drop table pessoas");
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas ( cd_pessoa INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INTEGER(3)) "); //comandos de execução
            //cria a tabela apenas se ela já não existe.

            //inserir dados
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Thalles', 22) ");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Luciano', 31) ");
            //bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Maria', 99) ");
            //bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('João', 50) ");

            //recuperar dados
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM pessoas ", null); //método utilizado para RECUPERAR registros
            //cursor utilizado para percorrer todos os itens retornados
            //no sql pode ser usado todos os comandos (where, updates, inserts, drop table, etc)

            //recuperar indices da tabela
            int indiceCd_pessoa = cursor.getColumnIndex("cd_pessoa");
            int indiceNome = cursor.getColumnIndex("nome"); //recupera o numero inteiro correspondente a coluna passada no parametro
            int indiceIdade = cursor.getColumnIndex("idade"); //recupera o numero inteiro correspondente a coluna passada no parametro

            cursor.moveToFirst(); //no momento que o cursor lista todos os itens retornados, ele para sempre no último item
            //por isso devemos sempre utilizar este método para voltar o cursos para o primeiro item da lista

            while(cursor != null){ //enquanto cursor for diferente de nulo le os dados.
                Log.i("RESULTADO - nome: ", cursor.getString(indiceNome)); // no getString para o numero inteiro de identificação da coluna
                Log.i("RESULTADO - idade: ", cursor.getString(indiceIdade)); // no getString para o numero inteiro de identificação da coluna
                Log.i("RESULTADO - id: ", cursor.getString(indiceCd_pessoa)); // no getString para o numero inteiro de identificação da coluna
                cursor.moveToNext(); //move o cursor para o proximo item.
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
