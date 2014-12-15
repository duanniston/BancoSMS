package bancosms.seosoft.com.br.bancosms;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Caixa implements IBancos {

    public final static String SMS_BOLETO = "CAIXA informa: Pagamento de boleto, 284,92, conta ******95-1,  15/02/2013 as  10:52, INTERNET BANKING. Duvidas: 0800-726-0104";
    public final static String CAIXA_INFORMA = "CAIXA informa:";
    public final static String PAGAMENTO_BOLETO = " Pagamento de boleto, ";
    public final static String CONTA = ", conta ";
    public final static String AS = " as  ";
    public final static String VIRGULA = ",  ";
    public final static String INTERNET_BANKING = ", INTERNET BANKING. ";
    private String smsMensagem;

    public Caixa(String mensagem) {

        smsMensagem = mensagem;
    }

    @Override
    public Double getValor() {
        // TODO Auto-generated method stub
        int inicio, fim;
        String valorAux;
        Double valor;

        if (smsMensagem != null || !"".equals(smsMensagem)) {

            try {
                if (smsMensagem.contains(PAGAMENTO_BOLETO)) {

                    inicio = smsMensagem.indexOf(PAGAMENTO_BOLETO)
                            + PAGAMENTO_BOLETO.length();
                    fim = smsMensagem.indexOf(CONTA);

                    valorAux = smsMensagem.substring(inicio, fim);
                    valorAux = valorAux.trim();
                    valorAux = valorAux.replace(".", "");
                    valorAux = valorAux.replace(",", ".");

                    valor = Double.valueOf(valorAux);
                    Log.v(BancosSMS.TAG_SMS, "CAIXA: getValor: " + -valor);

                    return -valor;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS, "CAIXA: getValor: nulo" + e.getMessage());
                return null;
            }

        } else {
            Log.v(BancosSMS.TAG_SMS, "CAIXA: getValor: nulo");
        }
        return null;
    }

    @Override
    public Date getData() {
        // TODO Auto-generated method stub
        int inicio, fim;
        Date data;
        DateFormat formatar;
        String dataAux;

        if (smsMensagem != null || !"".equals(smsMensagem)) {

            try {
                if (smsMensagem.contains(PAGAMENTO_BOLETO)) {

                    inicio = smsMensagem.indexOf(VIRGULA) + VIRGULA.length();
                    fim = smsMensagem.indexOf(INTERNET_BANKING);

                    dataAux = smsMensagem.substring(inicio, fim);
                    dataAux = dataAux.trim();
                    dataAux = dataAux.replace(AS, " ");

                    formatar = new SimpleDateFormat("dd/MM/yyyy H:m");

                    Log.v(BancosSMS.TAG_SMS, "CAIXA: getData: " + dataAux);

                    try {

                        data = formatar.parse(dataAux);

                        return data;

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        Log.v(BancosSMS.TAG_SMS,
                                "CAIXA: getData: erro : " + e.getMessage());
                        return null;

                    }

                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS, "CAIXA: getData: nulo" + e.getMessage());
                return null;
            }
        } else {

            Log.v(BancosSMS.TAG_SMS, "CAIXA: getData: nulo");
        }
        return null;

    }

    @Override
    public String getDescricao() {
        // TODO Auto-generated method stub

        if (smsMensagem != null || !"".equals(smsMensagem)) {

            try {

                if (smsMensagem.contains(PAGAMENTO_BOLETO)) {

                    Log.v(BancosSMS.TAG_SMS, "CAIXA: getDescri����o: Pagamento de Boleto");

                    return "CAIXA: Pagamento de Boleto";

                }

            } catch (Exception e) {

                Log.v(BancosSMS.TAG_SMS, "CAIXA: getDescri����o: nulo" + e.getMessage());
                return null;
            }

            Log.v(BancosSMS.TAG_SMS, "CAIXA: getDescri����o: nulo");
        }
        return null;
    }

    @Override
    public String getNomebanco() {
        // TODO Auto-generated method stub
        return "CAIXA";
    }

}
