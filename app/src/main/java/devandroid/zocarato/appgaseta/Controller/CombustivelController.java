package devandroid.zocarato.appgaseta.Controller;

import android.content.SharedPreferences;

import devandroid.zocarato.appgaseta.model.Combustivel;
import devandroid.zocarato.appgaseta.view.GasEtaActivity;

public class CombustivelController {

    SharedPreferences preferences;
    SharedPreferences.Editor dadosPreferences;

    public static final String NOME_PREFERENCES = "pref_gaseta";

    // metodo construtor
    public CombustivelController (GasEtaActivity activity){

        preferences = activity.getSharedPreferences(NOME_PREFERENCES,0);
        dadosPreferences = preferences.edit();






    }

    public void salvar (Combustivel combustivel){

        dadosPreferences.putString("combustivel", combustivel.getNomeDoCombustivel());
        dadosPreferences.putFloat("precoDoCombustivel", (float) combustivel.getPrecoDoCombustivel());
        dadosPreferences.putString("recomendacao", combustivel.getRecomendacao());
        dadosPreferences.apply();

    }

    public void limpar (){

        dadosPreferences.clear();
        dadosPreferences.apply();
    }


}
