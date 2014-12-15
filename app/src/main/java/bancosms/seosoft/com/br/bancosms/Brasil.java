package bancosms.seosoft.com.br.bancosms;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Brasil implements IBancos {

    public static final String SMS = "BB informa: compra no(a) PAYPAL *DEALEXTRE, cartao de credito final 2017, valor RS 139,56, em 30/06/13, as 19:00.";
    public static final String SMS2 = "BB informa: compra no(a) SUPER ZE, cartao de debito final 8278, valor RS  35,77, em 17/07/13. Saldo c/c: RS  682,80C";
    public static final String BB_INFORMA = "BB informa: compra no(a)";
    private String CARTAO_CREDITO_FINAL = ", cartao de credito final";
    private String CARTAO_DEBITO = ", cartao de debito final ";
    private String CIFRAO = " RS ";
    private String EM = ", em ";
    private String AS = ", as ";
    private String PONTO = ". ";
    private String smsMensagem;

    public Brasil(String mensagem) {
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

                if (smsMensagem.contains(BB_INFORMA) && smsMensagem.contains(CARTAO_DEBITO)) {

                    inicio = smsMensagem.indexOf(CIFRAO) + CIFRAO.length();
                    fim = smsMensagem.indexOf(EM);

                    valorAux = smsMensagem.substring(inicio, fim);
                    valorAux = valorAux.trim();
                    valorAux = valorAux.replace(".", "");

                    valorAux = valorAux.replace(",", ".");

                    valor = Double.valueOf(valorAux);
                    Log.v(BancosSMS.TAG_SMS, "BRASIL: getValor: " + -valor);

                    return -valor;

                }
                if (smsMensagem.contains(BB_INFORMA) && smsMensagem.contains(CARTAO_CREDITO_FINAL)) {

                    inicio = smsMensagem.indexOf(CIFRAO) + CIFRAO.length();
                    fim = smsMensagem.indexOf(EM);

                    valorAux = smsMensagem.substring(inicio, fim);
                    valorAux = valorAux.trim();
                    valorAux = valorAux.replace(".", "");

                    valorAux = valorAux.replace(",", ".");

                    valor = Double.valueOf(valorAux);
                    Log.v(BancosSMS.TAG_SMS, "BRASIL: getValor: " + -valor);

                    return -valor;
                }


            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS, "BRASIL: getValor: nulo" + e.getMessage());
                return null;
            }

        } else {
            Log.v(BancosSMS.TAG_SMS, "BRASIl: getValor: nulo");
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


                if (smsMensagem.contains(BB_INFORMA) && smsMensagem.contains(CARTAO_DEBITO)) {

                    inicio = smsMensagem.indexOf(EM) + EM.length();
                    fim = smsMensagem.indexOf(PONTO);

                    dataAux = smsMensagem.substring(inicio, fim);

                    dataAux = dataAux.trim();
                    formatar = new SimpleDateFormat("dd/MM/yy");

                    Log.v(BancosSMS.TAG_SMS, "BRASIL: getData: " + dataAux);

                    try {

                        data = formatar.parse(dataAux);

                        return data;

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        Log.v(BancosSMS.TAG_SMS,
                                "BRASIL: getData: erro : " + e.getMessage());
                        return null;

                    }
                }

                if (smsMensagem.contains(BB_INFORMA) && smsMensagem.contains(CARTAO_CREDITO_FINAL)) {

                    inicio = smsMensagem.indexOf(EM) + EM.length();
                    fim = smsMensagem.indexOf(AS);

                    dataAux = smsMensagem.substring(inicio, fim);
//					dataAux = dataAux.replace(AS, " ");

                    dataAux = dataAux.trim();
//					dataAux = dataAux.replace(" ", "/"
//							+ Calendar.getInstance().get(Calendar.YEAR) + " ");
                    formatar = new SimpleDateFormat("dd/MM/yy");

                    Log.v(BancosSMS.TAG_SMS, "BRASIL: getData: " + dataAux);

                    try {

                        data = formatar.parse(dataAux);

                        return data;

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        Log.v(BancosSMS.TAG_SMS,
                                "BRASIL: getData: erro : " + e.getMessage());
                        return null;

                    }
                }


            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS, "BRASIL: getData: nulo" + e.getMessage());
                return null;
            }
        } else {

            Log.v(BancosSMS.TAG_SMS, "BRASIL: getData: nulo");
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

                if (smsMensagem.contains(BB_INFORMA) && smsMensagem.contains(CARTAO_DEBITO)) {

                    inicio = smsMensagem.indexOf(BB_INFORMA)
                            + BB_INFORMA.length();
                    fim = smsMensagem.indexOf(CARTAO_DEBITO);

                    descricao = smsMensagem.substring(inicio, fim);
                    descricao = descricao.trim();

                    Log.v(BancosSMS.TAG_SMS, "BRASIL: getDescricao: " + descricao);
                    return "BRASIL: " + descricao;
                }

                if (smsMensagem.contains(BB_INFORMA) && smsMensagem.contains(CARTAO_CREDITO_FINAL)) {

                    inicio = smsMensagem.indexOf(BB_INFORMA)
                            + BB_INFORMA.length();
                    fim = smsMensagem.indexOf(CARTAO_CREDITO_FINAL);

                    descricao = smsMensagem.substring(inicio, fim);
                    descricao = descricao.trim();

                    Log.v(BancosSMS.TAG_SMS, "BRASIL: getDescricao: " + descricao);
                    return "BRASIL: " + descricao;
                }


            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS,
                        "BRASIL: getDescricao: nulo" + e.getMessage());
                return null;
            }

        } else {
            Log.v(BancosSMS.TAG_SMS, "BRASIL: getDescricaoo: nulo");
            return null;
        }
        return null;

    }

    @Override
    public String getNomebanco() {
        // TODO Auto-generated method stub
        return "BRASIL";
    }
}
