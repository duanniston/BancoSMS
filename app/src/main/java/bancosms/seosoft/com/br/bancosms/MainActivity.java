package bancosms.seosoft.com.br.bancosms;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private IBancos banco;
    private Button btnTestar;
    private TextView tvLocal;
    private TextView tvData;
    private TextView tvValor;
    private TextView tvNomeBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTestar = (Button) findViewById(R.id.btn_testar);
        tvData = (TextView) findViewById(R.id.tv_data);
        tvLocal = (TextView) findViewById(R.id.tv_local);
        tvValor = (TextView) findViewById(R.id.tv_valor);
        tvNomeBanco = (TextView) findViewById(R.id.tv_nome_banco);


        btnTestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banco = new BancosSMS(Brasil.SMS,"000").getBanco();
                tvData.setText(banco.getData().toString());
                tvValor.setText(banco.getValor().toString());
                tvLocal.setText(banco.getDescricao());
                tvNomeBanco.setText(banco.getNomebanco());
            }
        });
    }


}
