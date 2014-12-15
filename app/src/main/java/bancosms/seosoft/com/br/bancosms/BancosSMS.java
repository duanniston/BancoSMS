package bancosms.seosoft.com.br.bancosms;

public class BancosSMS {

    public static final String TAG_SMS = "SMS BANCO";
    private static final int ITAU = 1;
    private static final int BRASIL = 2;
    private static final int BRADESCO = 3;
    private static final int DINERS = 4;
    private static final int CAIXA = 5;
    private int BANCO = 0;
    private String mensagem;

    public BancosSMS(String mensagemSMS, String numero) {
        // TODO Auto-generated constructor stub

        mensagem = mensagemSMS;

        if (mensagem.contains(Itau.REALIZADO_PAGAMENTO)
                || mensagem.contains(Itau.DEBITO)
                || mensagem.contains(Itau.REALIZADA_TRANSFERENCIA)
                || mensagem.contains(Itau.RECARGA_CELULAR)
                || mensagem.contains(Itau.COMPRA_APROVADA_CREDITO)
                || mensagem.contains(Itau.CREDITO_LIMIT)) {

            BANCO = ITAU;
        }

        // if (numero.equals("11108") || numero.equals("25010")) {
        //
        // BANCO = ITAU;
        //

        if (numero.equals("27183") || numero.equals("27326")
                || mensagem.contains(" BRADESCO ")) {

            BANCO = BRADESCO;

        }
        if (numero.equals("40040001") || mensagem.contains(Brasil.BB_INFORMA)) {

            BANCO = BRASIL;

        }
        if (mensagem.contains("Diners Exclusive:")) {

            BANCO = DINERS;

        }

        if (numero == null || numero.equals("")) {

            BANCO = 0;
        }

        if (mensagem.contains(Caixa.CAIXA_INFORMA)) {

            BANCO = CAIXA;

        }

    }

    public IBancos getBanco() {

        switch (BANCO) {

            case ITAU:

                return new Itau(mensagem);

            case BRASIL:

                return new Brasil(mensagem);

            case BRADESCO:

                return new Bradesco(mensagem);

            case DINERS:

                return new Diners(mensagem);

            case CAIXA:

                return new Caixa(mensagem);

            case 0:

                return null;

        }
        return null;

    }

}
