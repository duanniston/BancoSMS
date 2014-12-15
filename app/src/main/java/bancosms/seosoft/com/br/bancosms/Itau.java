package bancosms.seosoft.com.br.bancosms;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author duanniston
 */
public class Itau implements IBancos {

    public static final String DEBITO = "ITAU DEBITO";
    public static final String SAQUE_APROVADO = "SAQUE APROVADO";
    public static final String COMPRA_APROVADA_DEBITO = "COMPRA APROVADA";
    public static final String COMPRA_APROVADA_CREDITO = "Compra aprovada no seu ";
    public static final String CREDITO_LIMIT = ". Utilizado ";
    public static final String REALIZADO_PAGAMENTO = "Realizado pagamento de";
    public static final String RECARGA_CELULAR = "Recarga de telefone celular";
    public static final String REALIZADA_TRANSFERENCIA = "Realizada TRANSFERENCIA entre contas";
    public static final String CIFRAO = "R$";
    public static final String CIFRAO_S = " RS ";
    public static final String LOCAL = "Local:";
    public static final String EM = " em ";
    public static final String AS = " as ";
    public static final String TRACO = " - ";
    public static final String CONSULTE = ". Consulte";
    private String smsMensagem;

    /**
     *
     *
     */

    public Itau(String mensagem) {
        // TODO Auto-generated constructor stub

        smsMensagem = mensagem;

    }

    private void replaceCifrao() {

        if (smsMensagem.contains(CIFRAO_S)) {

            smsMensagem = smsMensagem.replace(CIFRAO_S, " " + CIFRAO + " ");
            Log.v(BancosSMS.TAG_SMS, "CIFRAO S: " + smsMensagem);

        }

    }

    @Override
    public Double getValor() {
        // TODO Auto-generated method stub
        int inicio, fim;
        String valorAux;
        Double valor;
        if (smsMensagem != null || !"".equals(smsMensagem)) {

            try {

                if (smsMensagem.contains(COMPRA_APROVADA_DEBITO)) {

                    replaceCifrao();

                    inicio = smsMensagem.indexOf(CIFRAO) + CIFRAO.length();
                    fim = smsMensagem.indexOf(LOCAL);

                    valorAux = smsMensagem.substring(inicio, fim);
                    valorAux = valorAux.trim();
                    valorAux = valorAux.replace(".", "");
                    valorAux = valorAux.replace(",", ".");

                    valor = Double.valueOf(valorAux);
                    Log.v(BancosSMS.TAG_SMS, "ITAU: getValor: " + -valor);

                    return -valor;
                }

                if (smsMensagem.contains(REALIZADO_PAGAMENTO)) {

                    replaceCifrao();

                    inicio = smsMensagem.indexOf(CIFRAO) + CIFRAO.length();
                    fim = smsMensagem.indexOf("na sua");

                    valorAux = smsMensagem.substring(inicio, fim);
                    valorAux = valorAux.trim();
                    valorAux = valorAux.replace(".", "");
                    valorAux = valorAux.replace(",", ".");
                    valor = Double.valueOf(valorAux);
                    Log.v(BancosSMS.TAG_SMS, "ITAU: getValor: " + -valor);
                    return -valor;
                }
                if (smsMensagem.contains(RECARGA_CELULAR)) {

                    replaceCifrao();

                    inicio = smsMensagem.indexOf(CIFRAO) + CIFRAO.length();
                    fim = smsMensagem.indexOf("na sua");

                    valorAux = smsMensagem.substring(inicio, fim);
                    valorAux = valorAux.trim();
                    valorAux = valorAux.replace(".", "");
                    valorAux = valorAux.replace(",", ".");
                    valor = Double.valueOf(valorAux);
                    Log.v(BancosSMS.TAG_SMS, "ITAU: getValor: " + -valor);
                    return -valor;
                }
                if (smsMensagem.contains(REALIZADA_TRANSFERENCIA)) {

                    if (smsMensagem.contains(" RS")) {

                        smsMensagem = smsMensagem.replace(" RS", " "
                                + CIFRAO + " ");
                        Log.v(BancosSMS.TAG_SMS, "CIFRAO S: " + smsMensagem);

                    }

                    inicio = smsMensagem.indexOf(CIFRAO) + CIFRAO.length();
                    fim = smsMensagem.indexOf(" em ");

                    valorAux = smsMensagem.substring(inicio, fim);
                    valorAux = valorAux.trim();
                    valorAux = valorAux.replace(".", "");
                    valorAux = valorAux.replace(",", ".");
                    valor = Double.valueOf(valorAux);
                    Log.v(BancosSMS.TAG_SMS, "ITAU: getValor: " + -valor);
                    return -valor;
                }
                if (smsMensagem.contains(COMPRA_APROVADA_CREDITO)) {

                    inicio = smsMensagem.lastIndexOf("RS") + 2;
                    fim = smsMensagem.indexOf(" em ");

                    valorAux = smsMensagem.substring(inicio, fim);
                    valorAux = valorAux.trim();
                    valorAux = valorAux.replace(".", "");
                    valorAux = valorAux.replace(",", ".");
                    valor = Double.valueOf(valorAux);
                    Log.v(BancosSMS.TAG_SMS, "ITAU: getValor: " + -valor);
                    return -valor;
                }
                if (smsMensagem.contains(CREDITO_LIMIT)) {

                    inicio = smsMensagem.indexOf(" - RS ") + 6;
                    fim = smsMensagem.lastIndexOf(" em ");

                    valorAux = smsMensagem.substring(inicio, fim);
                    valorAux = valorAux.trim();
                    valorAux = valorAux.replace(".", "");
                    valorAux = valorAux.replace(",", ".");

                    valor = Double.valueOf(valorAux);
                    Log.v(BancosSMS.TAG_SMS, "ITAU: getValor: " + -valor);

                    return -valor;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS, "ITAU: getValor: nulo: " + e.getMessage());
                return null;
            }

        } else {
            Log.v(BancosSMS.TAG_SMS, "ITAU: getValor: nulo");
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
                if (smsMensagem.contains(COMPRA_APROVADA_DEBITO)) {

                    replaceCifrao();

                    inicio = smsMensagem.indexOf(COMPRA_APROVADA_DEBITO)
                            + COMPRA_APROVADA_DEBITO.length();
                    fim = smsMensagem.indexOf(CIFRAO);

                    dataAux = smsMensagem.substring(inicio, fim);
                    dataAux = dataAux.trim();
                    dataAux = dataAux.replace(" ", "/"
                            + Calendar.getInstance().get(Calendar.YEAR) + " ");
                    formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                    Log.v(BancosSMS.TAG_SMS, "ITAU: getData: " + dataAux);

                    try {

                        data = formatar.parse(dataAux);

                        return data;

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        Log.v(BancosSMS.TAG_SMS,
                                "ITAU: getData: erro : " + e.getMessage());
                        return null;

                    }

                }
                if (smsMensagem.contains(REALIZADO_PAGAMENTO)) {

                    inicio = smsMensagem.lastIndexOf(" em ") + 3;
                    fim = smsMensagem.lastIndexOf(".");

                    dataAux = smsMensagem.substring(inicio, fim);
                    dataAux = dataAux.trim();
                    dataAux = dataAux.replace("as", "");
                    dataAux = dataAux.replace("  ", "/"
                            + Calendar.getInstance().get(Calendar.YEAR) + " ");

                    formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                    Log.v(BancosSMS.TAG_SMS, "ITAU: getData: " + dataAux);

                    try {

                        data = formatar.parse(dataAux);

                        return data;

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        Log.v(BancosSMS.TAG_SMS,
                                "ITAU: getData: erro : " + e.getMessage());
                        return null;

                    }

                }
                if (smsMensagem.contains(RECARGA_CELULAR)) {

                    inicio = smsMensagem.lastIndexOf(" em ") + 3;
                    fim = smsMensagem.lastIndexOf(".");

                    dataAux = smsMensagem.substring(inicio, fim);
                    dataAux = dataAux.trim();
                    dataAux = dataAux.replace("as", "");
                    dataAux = dataAux.replace("  ", "/"
                            + Calendar.getInstance().get(Calendar.YEAR) + " ");

                    formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                    Log.v(BancosSMS.TAG_SMS, "ITAU: getData: " + dataAux);

                    try {

                        data = formatar.parse(dataAux);

                        return data;

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        Log.v(BancosSMS.TAG_SMS,
                                "ITAU: getData: erro : " + e.getMessage());
                        return null;

                    }

                }
                if (smsMensagem.contains(REALIZADA_TRANSFERENCIA)) {

                    inicio = smsMensagem.lastIndexOf(" em ") + 3;
                    fim = smsMensagem.lastIndexOf(".");

                    dataAux = smsMensagem.substring(inicio, fim);
                    dataAux = dataAux.trim();
                    dataAux = dataAux.replace("as", "");
                    dataAux = dataAux.replace("  ", "/"
                            + Calendar.getInstance().get(Calendar.YEAR) + " ");

                    formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                    Log.v(BancosSMS.TAG_SMS, "ITAU: getData: " + dataAux);

                    try {

                        data = formatar.parse(dataAux);

                        return data;

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        Log.v(BancosSMS.TAG_SMS,
                                "ITAU: getData: erro : " + e.getMessage());
                        return null;

                    }

                }
                if (smsMensagem.contains(COMPRA_APROVADA_CREDITO)) {

                    inicio = smsMensagem.lastIndexOf(" em ") + 3;
                    fim = smsMensagem.lastIndexOf(".");

                    dataAux = smsMensagem.substring(inicio, fim);
                    dataAux = dataAux.trim();
                    Calendar cal = Calendar.getInstance();
                    dataAux = dataAux.replace(", as", "/" + cal.get(Calendar.YEAR));
                    dataAux = dataAux.replace("h", ":");

                    formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                    Log.v(BancosSMS.TAG_SMS, "ITAU: getData: " + dataAux);

                    try {

                        data = formatar.parse(dataAux);

                        return data;

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        Log.v(BancosSMS.TAG_SMS,
                                "ITAU: getData: erro : " + e.getMessage());
                        return null;

                    }

                }
                if (smsMensagem.contains(CREDITO_LIMIT)) {

                    inicio = smsMensagem.lastIndexOf(EM) + EM.length();
                    fim = smsMensagem.lastIndexOf(". ");

                    dataAux = smsMensagem.substring(inicio, fim);
                    dataAux = dataAux.trim();

                    dataAux = dataAux.replace(AS, " ");
                    dataAux = dataAux.replace("h", ":");

                    formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                    Log.v(BancosSMS.TAG_SMS, "ITAU: getData: " + dataAux);

                    try {

                        data = formatar.parse(dataAux);

                        return data;

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        Log.v(BancosSMS.TAG_SMS,
                                "ITAU: getData: erro : " + e.getMessage());
                        return null;

                    }

                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS, "ITAU: getData: nulo" + e.getMessage());
                return null;
            }
        } else {

            Log.v(BancosSMS.TAG_SMS, "ITAU: getData: nulo");
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
                if (smsMensagem.contains(COMPRA_APROVADA_DEBITO)) {

                    inicio = smsMensagem.indexOf(LOCAL) + LOCAL.length();
                    fim = smsMensagem.indexOf(CONSULTE);

                    descricao = smsMensagem.substring(inicio, fim);
                    descricao = descricao.trim();

                    Log.v(BancosSMS.TAG_SMS, "ITAU: getDescricao: " + descricao);
                    return "ITAU DEBIT: " + descricao;
                }
                if (smsMensagem.contains(REALIZADO_PAGAMENTO)) {

                    inicio = smsMensagem.indexOf(REALIZADO_PAGAMENTO)
                            + REALIZADO_PAGAMENTO.length();
                    fim = smsMensagem.indexOf("no valor");

                    descricao = smsMensagem.substring(inicio, fim);
                    descricao = descricao.trim();

                    Log.v(BancosSMS.TAG_SMS, "ITAU: getDescricao: " + descricao);
                    return "ITAU: " + descricao;
                }
                if (smsMensagem.contains(RECARGA_CELULAR)) {

                    inicio = smsMensagem.indexOf(RECARGA_CELULAR)
                            + RECARGA_CELULAR.length();
                    fim = smsMensagem.indexOf("no valor");

                    descricao = smsMensagem.substring(inicio, fim);
                    descricao = descricao.trim();

                    Log.v(BancosSMS.TAG_SMS, "ITAU: getDescricao: " + descricao);
                    return "ITAU Recarga celular: " + descricao;
                }
                if (smsMensagem.contains(REALIZADA_TRANSFERENCIA)) {

                    // inicio = smsMensagem.indexOf(RECARGA_CELULAR)
                    // + RECARGA_CELULAR.length();
                    // fim = smsMensagem.indexOf("no valor");
                    //
                    // descricao = smsMensagem.substring(inicio, fim);
                    // descricao = descricao.trim();
                    descricao = "ITAU Tranferencia entre contas";

                    Log.v(BancosSMS.TAG_SMS, "ITAU: getDescricao: " + descricao);
                    return descricao;
                }
                if (smsMensagem.contains(COMPRA_APROVADA_CREDITO)) {

                    inicio = smsMensagem.indexOf(" - ") + 3;
                    fim = smsMensagem.indexOf("valor RS");

                    descricao = smsMensagem.substring(inicio, fim);
                    descricao = descricao.trim();

                    Log.v(BancosSMS.TAG_SMS, "ITAU: getDescricao: " + descricao);
                    return "ITAU CREDIT: " + descricao;
                }

                if (smsMensagem.contains(CREDITO_LIMIT)) {

                    inicio = smsMensagem.indexOf(TRACO) + 3;
                    fim = smsMensagem.indexOf(" - RS");

                    descricao = smsMensagem.substring(inicio, fim);
                    descricao = descricao.trim();

                    Log.v(BancosSMS.TAG_SMS, "ITAU: getDescricao: " + descricao);
                    return "ITAU CREDIT: " + descricao;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v(BancosSMS.TAG_SMS, "ITAU: getDescricao: nulo" + e.getMessage());
                return null;
            }

        } else {
            Log.v(BancosSMS.TAG_SMS, "ITAU: getDescricao: nulo");
            return null;
        }
        return null;

    }

    @Override
    public String getNomebanco() {
        // TODO Auto-generated method stub
        return "ITAU";
    }

}
