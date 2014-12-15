package bancosms.seosoft.com.br.bancosms;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bradesco implements IBancos {

    private String SMS = "19/01/13 11:42 BRADESCO S2Lite: Compra cartao deb. final 8488 de  60,00 realizada no estab. ACAO GAMES.";
    private String COMPRA_CARTAO_DEB = "Compra cartao deb";
    private String DE = " de  ";
    private String REALIZADA_NO_ESTAB = " realizada no estab.";
    private String DOC_E = " DOC E ";
    private String BRADESCO = " BRADESCO ";
    private String smsMensagem;

    public Bradesco(String mensagem) {
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
                if (smsMensagem.contains(COMPRA_CARTAO_DEB)) {

                    inicio = smsMensagem.indexOf(DE) + DE.length();
                    fim = smsMensagem.indexOf(REALIZADA_NO_ESTAB);

                    valorAux = smsMensagem.substring(inicio, fim);
                    valorAux = valorAux.trim();
                    valorAux = valorAux.replace(".", "");
                    valorAux = valorAux.replace(",", ".");

                    valor = Double.valueOf(valorAux);
                    Log.v(BancosSMS.TAG_SMS, "BRADESCO: getValor: " + -valor);

                    return -valor;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS, "BRADESCO: getValor: nulo" + e.getMessage());
                return null;
            }

        } else {
            Log.v(BancosSMS.TAG_SMS, "BRADESCO: getValor: nulo");
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
                if (smsMensagem.contains(COMPRA_CARTAO_DEB)) {

                    inicio = 0;
                    fim = smsMensagem.indexOf(BRADESCO);

                    dataAux = smsMensagem.substring(inicio, fim);

                    formatar = new SimpleDateFormat("dd/MM/yy H:m");

                    Log.v(BancosSMS.TAG_SMS, "BRADESCO: getData: " + dataAux);

                    try {

                        data = formatar.parse(dataAux);

                        return data;

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        Log.v(BancosSMS.TAG_SMS,
                                "BRADESCO: getData: erro : " + e.getMessage());
                        return null;

                    }
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS, "BRADESCO: getData: nulo" + e.getMessage());
                return null;
            }
        } else {

            Log.v(BancosSMS.TAG_SMS, "BRADESCO: getData: nulo");
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
                if (smsMensagem.contains(COMPRA_CARTAO_DEB)) {

                    inicio = smsMensagem.indexOf(REALIZADA_NO_ESTAB)
                            + REALIZADA_NO_ESTAB.length();
                    fim = smsMensagem.indexOf(smsMensagem)
                            + smsMensagem.length() - 1;

                    descricao = smsMensagem.substring(inicio, fim);
                    descricao = descricao.trim();

                    Log.v(BancosSMS.TAG_SMS, "BRADESCO: getDescri����o: " + descricao);
                    return "BRADESCO DEBIT: " + descricao;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS,
                        "BRADESCO: getDescri����o: nulo" + e.getMessage());
                return null;
            }

        } else {
            Log.v(BancosSMS.TAG_SMS, "BRADESCO: getDescri����o: nulo");
            return null;
        }
        return null;

    }

    @Override
    public String getNomebanco() {
        // TODO Auto-generated method stub
        return "BRADESCO";
    }

}
