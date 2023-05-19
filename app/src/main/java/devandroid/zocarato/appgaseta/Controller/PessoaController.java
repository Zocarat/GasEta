package devandroid.zocarato.appgaseta.Controller;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import devandroid.zocarato.appgaseta.model.Pessoa;
import devandroid.zocarato.appgaseta.view.MainActivity;

public class PessoaController {

    SharedPreferences preferences;
    SharedPreferences.Editor listaVip;
    public static final String NOME_PREFERENCES = "pref_listavip";

    public PessoaController (MainActivity mainActivity){

        preferences = mainActivity.getSharedPreferences(NOME_PREFERENCES,0);
        listaVip = preferences.edit();
    }

    @NonNull
    @Override
    public String toString() {

        Log.d("MCV_Controller", "Controller iniciada ");

        return super.toString();
    }

    public void salvar(Pessoa pessoa) {
        Log.d("MCV_Controller", "Salvo "+pessoa.toString());

        listaVip.putString("primeiroNome", pessoa.getPrimeiroNome());
        listaVip.putString("sobrenome", pessoa.getSobrenome());
        listaVip.putString("cursoDesejado", pessoa.getCursoDesejado());
        listaVip.putString("telefone", pessoa.getTelefoneContato());
        listaVip.apply();
    }

    public Pessoa buscar( Pessoa pessoa){
        pessoa.setPrimeiroNome(preferences.getString("primeiroNome","NA"));
        pessoa.setSobrenome(preferences.getString("sobrenome","NA"));
        pessoa.setCursoDesejado(preferences.getString("cursoDesejado","NA"));
        pessoa.setTelefoneContato(preferences.getString("telefone","NA"));

        return pessoa;

    }

    public void limpar (){
        listaVip.clear();
        listaVip.apply();
    }
}
