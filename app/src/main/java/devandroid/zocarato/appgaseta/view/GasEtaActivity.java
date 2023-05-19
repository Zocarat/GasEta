package devandroid.zocarato.appgaseta.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import devandroid.zocarato.appgaseta.Controller.CombustivelController;
import devandroid.zocarato.appgaseta.R;
import devandroid.zocarato.appgaseta.apoio.UtilGasEta;
import devandroid.zocarato.appgaseta.model.Combustivel;

public class GasEtaActivity extends AppCompatActivity {
    //Declaração de variaveis

    CombustivelController controller;
    Combustivel combustivelGasolina;
    Combustivel combustivelEtanol;

    EditText edit_gasolina;
    EditText edit_etanol;

    TextView txt_resultado;

    Button btn_calcular;
    Button btn_limpar;
    Button btn_salvar;
    Button btn_finalizar;

    double precoGasolina;
    double precoEtanol;
    String recomendação;
    List<Combustivel> dados;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gaseta);

        controller = new CombustivelController(GasEtaActivity.this);

        dados = controller.getListaDeDados();

        Combustivel objAlteracao = dados.get(1);

        objAlteracao.setRecomendacao("lalalalal");
        objAlteracao.setNomeDoCombustivel("vodka ");

       // controller.alterar(objAlteracao);

        //associa variaveis com id
        edit_gasolina = findViewById(R.id.edit_gasolina);
        edit_etanol = findViewById(R.id.edit_etanol);

        txt_resultado = findViewById(R.id.txt_resultado);

        btn_calcular = findViewById(R.id.btn_calcular);
        btn_limpar = findViewById(R.id.btn_limpar);
        btn_salvar = findViewById(R.id.btn_salvar);
        btn_finalizar = findViewById(R.id.btn_finalizar);

        //pegar o click dos botões
        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // variavel confirma se dados são validos
                boolean isDadosOK = true;

            //Se a variavel estiver vazia
                if(TextUtils.isEmpty(edit_gasolina.getText())){
                    edit_gasolina.setError("* Obrigatório");
                    edit_gasolina.requestFocus();  // faz com que o cursor volte para o campo
                    isDadosOK = false;
                }
                if(TextUtils.isEmpty(edit_etanol.getText())){
                    edit_etanol.setError("* Obrigatório");
                    edit_etanol.requestFocus();  // faz com que o cursor volte para o campo
                    isDadosOK = false;
                }
                if (isDadosOK){

                    precoGasolina = Double.parseDouble(edit_gasolina.getText().toString()); // pega os dados do campo e converte de string para double
                    precoEtanol = Double.parseDouble(edit_etanol.getText().toString());

                    recomendação = UtilGasEta.calcularMelhorOpcao(precoGasolina,precoEtanol);

                    txt_resultado.setText(recomendação);

                    btn_salvar.setEnabled(true);

                }
                else {
                    Toast.makeText(GasEtaActivity.this ,
                            "Por favor, digite os dados corretamente",
                            Toast.LENGTH_LONG).show();
                    btn_salvar.setEnabled(false);
                }
            }
        });

        btn_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit_etanol.setText("");
                edit_gasolina.setText("");

                btn_salvar.setEnabled(false);

                controller.limpar();
            }
        });

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //TODO: EditText inputType


                combustivelGasolina = new Combustivel(); // estanciando objetos
                combustivelEtanol= new Combustivel();

                combustivelGasolina.setNomeDoCombustivel("Gasolina");
                combustivelGasolina.setPrecoDoCombustivel(precoGasolina);

                combustivelEtanol.setNomeDoCombustivel("Etanol");
                combustivelEtanol.setPrecoDoCombustivel(precoEtanol);

                combustivelGasolina.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina,precoEtanol));
                combustivelEtanol.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina,precoEtanol));

                controller.salvar(combustivelGasolina);
                controller.salvar(combustivelEtanol);

                int parada = 0;
            }
        });
        btn_finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GasEtaActivity.this ,
                        "Volte Sempre", Toast.LENGTH_LONG).show();
                finish();
            }
        });


        Toast.makeText(GasEtaActivity.this , UtilGasEta.mensagem(),
                Toast.LENGTH_LONG).show();

    }
}
