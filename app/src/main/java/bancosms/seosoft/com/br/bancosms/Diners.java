package bancosms.seosoft.com.br.bancosms;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Diners implements IBancos {

    private String SMS = "Diners Exclusive: Compra aprovada em 23/01/12, as 16:43 de RS 24,00. Local: MALHAS FERJUIMBITUBA88.";
    private String COMPRA_APROVADA = ": Compra aprovada em ";
    private String PELO_ADICIONAL = " pelo seu adicional.";
    private String CIFRAO = " de RS ";
    private String LOCAL = ". Local:";
    private String AS = ", as ";

    private String smsMensagem;

    public Diners(String mensagem) {
        // TODO Auto-generated constructor stub

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
                if (smsMensagem.contains(COMPRA_APROVADA) && !smsMensagem.contains(PELO_ADICIONAL)) {

                    inicio = smsMensagem.indexOf(CIFRAO) + CIFRAO.length();
                    fim = smsMensagem.indexOf(LOCAL);

                    valorAux = smsMensagem.substring(inicio, fim);
                    valorAux = valorAux.trim();
                    valorAux = valorAux.replace(".", "");

                    valorAux = valorAux.replace(",", ".");

                    valor = Double.valueOf(valorAux);
                    Log.v(BancosSMS.TAG_SMS, "DINERS: getValor: " + -valor);

                    return -valor;
                }
                if (smsMensagem.contains(COMPRA_APROVADA) && smsMensagem.contains(PELO_ADICIONAL)) {

                    inicio = smsMensagem.indexOf(CIFRAO) + CIFRAO.length();
                    fim = smsMensagem.indexOf(PELO_ADICIONAL);

                    valorAux = smsMensagem.substring(inicio, fim);
                    valorAux = valorAux.trim();
                    valorAux = valorAux.replace(".", "");

                    valorAux = valorAux.replace(",", ".");

                    valor = Double.valueOf(valorAux);
                    Log.v(BancosSMS.TAG_SMS, "DINERS: getValor: " + -valor);

                    return -valor;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS, "DINERS: getValor: nulo" + e.getMessage());
                return null;
            }

        } else {
            Log.v(BancosSMS.TAG_SMS, "DINERS: getValor: nulo");
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
                if ((smsMensagem.contains(COMPRA_APROVADA))) {

                    inicio = smsMensagem.indexOf(COMPRA_APROVADA) + COMPRA_APROVADA.length();
                    fim = smsMensagem.indexOf(CIFRAO);

                    dataAux = smsMensagem.substring(inicio, fim);
                    dataAux = dataAux.replace(AS, " ");
                    dataAux = dataAux.replace(",", " ");
                    dataAux = dataAux.trim();
                    formatar = new SimpleDateFormat("dd/MM/yy H:m");

                    Log.v(BancosSMS.TAG_SMS, "DINERS: getData: " + dataAux);

                    try {

                        data = formatar.parse(dataAux);

                        return data;

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        Log.v(BancosSMS.TAG_SMS,
                                "DINERS: getData: erro : " + e.getMessage());
                        return null;

                    }
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS, "DINERS: getData: nulo" + e.getMessage());
                return null;
            }
        } else {

            Log.v(BancosSMS.TAG_SMS, "DINERS: getData: nulo");
        }
        return null;
    }

    @Override
    public String getDescricao() {
        // TODO Auto-generated method stub
        int inicio, fim;
        String descricao;

        if (smsMensagem != null || !"".equals(smsMensagem)) {

            try {
                if (smsMensagem.contains(COMPRA_APROVADA)) {

                    inicio = smsMensagem.indexOf(LOCAL) + LOCAL.length();
                    fim = smsMensagem.length() - 1;

                    descricao = smsMensagem.substring(inicio, fim);
                    descricao = descricao.trim();
                    descricao = descricao.replace("\\\\", " ");
                    descricao = descricao.replace("\\", " ");

                    Log.v(BancosSMS.TAG_SMS, "DINERS: getDescri����o: " + descricao);
                    return "DINERS: " + descricao;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS,
                        "DINERS: getDescri����o: nulo" + e.getMessage());
                return null;
            }

        } else {
            Log.v(BancosSMS.TAG_SMS, "DINERS: getDescri����o: nulo");
            return null;
        }
        return null;
    }

    @Override
    public String getNomebanco() {
        // TODO Auto-generated method stub
        return "DINERS";
    }

}
