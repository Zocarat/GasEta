package devandroid.zocarato.appgaseta.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import devandroid.zocarato.appgaseta.Controller.CursoController;
import devandroid.zocarato.appgaseta.Controller.PessoaController;
import devandroid.zocarato.appgaseta.R;
import devandroid.zocarato.appgaseta.model.Pessoa;

public class MainActivity extends AppCompatActivity {
    PessoaController controller;
    CursoController cursoController;

    Pessoa pessoa;
    List<String> nomeDosCursos;

    EditText edit_primeiro_nome;
    EditText edit_sobrenome;
    EditText edit_nome_do_curso;
    EditText edit_telefone_contato;

    Button btn_limpar;
    Button btn_salvar;
    Button btn_finalizar;

    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        controller = new PessoaController(MainActivity.this);
        controller.toString();

        pessoa = new Pessoa();
        controller.buscar(pessoa);

        edit_primeiro_nome = findViewById(R.id.edit_primeiro_nome);
        edit_sobrenome = findViewById(R.id.edit_sobrenome);
        edit_nome_do_curso = findViewById(R.id.edit_nome_do_curso);
        edit_telefone_contato = findViewById(R.id.edit_telefone_contato);


        edit_primeiro_nome.setText(pessoa.getPrimeiroNome());
        edit_sobrenome.setText(pessoa.getSobrenome());
        edit_nome_do_curso.setText(pessoa.getCursoDesejado());
        edit_telefone_contato.setText(pessoa.getTelefoneContato());

        btn_limpar = findViewById(R.id.btn_limpar);
        btn_salvar = findViewById(R.id.btn_salvar);
        btn_finalizar = findViewById(R.id.btn_finalizar);

        //Adapter
        //layout
        //injetar o adpter ao spinner - a lista será gerada

        cursoController = new CursoController();
        nomeDosCursos = cursoController.dadosParaSpinner();
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                cursoController.dadosParaSpinner());
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinner.setAdapter(adapter);


        btn_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_primeiro_nome.setText("");
                edit_sobrenome.setText("");
                edit_nome_do_curso.setText("");
                edit_telefone_contato.setText("");

                controller.limpar();
            }
        });
        btn_finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Volte Sempre", Toast.LENGTH_LONG).show();

                finish();
            }
        });
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pessoa.setPrimeiroNome(edit_primeiro_nome.getText().toString());
                pessoa.setSobrenome(edit_sobrenome.getText().toString());
                pessoa.setCursoDesejado(edit_nome_do_curso.getText().toString());
                pessoa.setTelefoneContato(edit_telefone_contato.getText().toString());

                Toast.makeText(MainActivity.this, "SALVO" + pessoa.toString(), Toast.LENGTH_LONG).show();

                controller.salvar(pessoa);
            }
        });

        //Log.i("POOAndroid", " Objeto pessoa:  " + outraPessoa.toString());

    }
}