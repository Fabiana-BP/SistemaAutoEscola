/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import ConexaoPostgres.Conexao;
import Views.TelaPrincipal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author USUARIO
 */
public class PermissaoAcesso {

    private static int idAutoEscola;
    private static String autoEscola = "";
    private static String user = "";
    private static String cpfUser = "";
    private static String senha = "";
    private static int tipoAcesso = 0;//nível de poder no sistema
    private static String acessoLogin = "";
    private int controleAcesso=0;//verificar se o usuario entrou no sistema,e o motivo caso negativo

    private static LinkedList<Integer> iDsAutoEscola = new LinkedList<>();
    private static LinkedList<String> nomesAutoEscola = new LinkedList<>();

    /**
     * Metódo para indicar qual autoescola
     *
     * @param nomeCFC
     */
    public void permissao(String nomeCFC) {

        for (int i = 0; i < nomesAutoEscola.size(); i++) {
            if (nomeCFC.equals(nomesAutoEscola.get(i))) {
                autoEscola = nomesAutoEscola.get(i);
                idAutoEscola = iDsAutoEscola.get(i);
                break;
            }
        }
    }

    /**
     * Método para verificar login
     *
     * @param userName
     * @param userSenha
     * @return 0 caso user ou senha inválidos, 1 caso usuario nao tem permissao
     * para essa autoescola específica, 3 usuario privilegiado
     */
    public int permissaoUser(String userName, String userSenha) {
        ResultSet rs = null;
        Conexao con = new Conexao();
        int id_aut = 0;
        String buscaUsuario = "select cpf_func,id_aut,nome_user, senha_user,tipo_acesso,acesso_login"
                + " from permissao where nome_user='" + userName + "'";
        rs = con.executaBusca(buscaUsuario);
        try {
            while (rs.next()) {
                cpfUser = rs.getString("cpf_func");
                id_aut = rs.getInt("id_aut");
                user = rs.getString("nome_user");
                senha = rs.getString("senha_user");
                tipoAcesso = rs.getInt("tipo_acesso");
                setAcessoLogin(rs.getString("acesso_login"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermissaoAcesso.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user.equals("")||user==null) {//não encontrou pesquisa no bd
            System.out.println("User não encontrado!");
            cpfUser = "";
            user = "";
            senha = "";
            tipoAcesso = 0;
            setAcessoLogin(null);
            controleAcesso=0;
            return 0;
        } else if (user.equals(userName) && !senha.equals(userSenha)) {//senha inválida
            System.out.println("Senha inválida!");
            controleAcesso=1;
            if(id_aut==0){
              controleAcesso=4;//não tem contagem de tempo se digitar senha errada  
            }
            return 1;
        } else if (id_aut > 0) {//o usuario possui permissao para auto_escola específica
            if (idAutoEscola != id_aut) {
                System.out.println("Usuário não tem permissão para acessar esse CFC!");
                controleAcesso=2;
                return 2;
            } else {
                System.out.println("acesso liberado!");
                controleAcesso=3;
                return 3;
            }
        } else if (id_aut == 0) {//acesso geral para qualquer auto_escola - equipe TI
            System.out.println("Usuario com acesso privilegiado!");
            cpfUser = "";
            controleAcesso=4;
            return 4;
        }
        return 0;
    }

    /**
     * Método atualiza no bd numero de tentativas e hora de tipoAcesso
     */
    public void atualizaAcessoLogin() {
        switch (controleAcesso) {
            case 0:
                //nao encontrou user no sistema, não atualiza
                System.out.println("Usuário não existe");
                break;
                //se consegue entrar limpa acesso_login no bd
            case 3:
              System.out.println("deveria limpar case 3. Acesso = "+tipoAcesso);
                    OperacoesBancoDeDados limpaAcessoLogin = new OperacoesBancoDeDados();
                    String condicao = "nome_user='" + user + "'";
                    limpaAcessoLogin.atualizarLinhasBD("permissao", "acesso_login", "null", condicao);
                    break;
            case 4:
                System.out.println("Usuário Privilegiado - TI");
                    break;

            default:
                    //senão atualiza
                     System.out.println("entrou no defaul. Acesso = "+tipoAcesso);
                    int numAcesso = 0;
                    Date date = dataAtual();
                    String formato = "dd/MM/yyyy HH:mm";
                    SimpleDateFormat formatter = new SimpleDateFormat(formato);
                    String dataAcesso = formatter.format(date); //data atual
                    if (acessoLogin != null) {
                        String[] verificaLogin = acessoLogin.split("-");
                        numAcesso = Integer.parseInt(verificaLogin[0]);//quantas vezes o usuário inseriu senha incorreta ou tentou acessar outra autoescola
                    }       numAcesso++;
                    acessoLogin = numAcesso + "-" + dataAcesso;
                    System.out.println("acesso login atualizado:" + user);
                    System.out.println("user login atualizado:" + user);
                    OperacoesBancoDeDados atualizaAcessoLogin = new OperacoesBancoDeDados();
                    condicao = "nome_user='" + user + "'";
                    String novoValor = "'" + acessoLogin + "'";
                    atualizaAcessoLogin.atualizarLinhasBD("permissao", "acesso_login", novoValor, condicao);
                    break;
        }
    }

    /**
     * Método retorna se usuario pode tentar novamente
     *
     * @return true caso positivo
     */
    public boolean gerenciaAcesso() {
        //verifica dados do usuário no banco
        System.out.println("acessoLogin=" + acessoLogin);
        if (acessoLogin != null) {
            String[] verificaLogin = acessoLogin.split("-");
            int numAcesso = Integer.parseInt(verificaLogin[0]);//quantas vezes o usuário inseriu senha incorreta ou tentou acessar outra autoescola
            String dataUltimoAcesso = verificaLogin[1];
            System.out.println("numAcesso: " + numAcesso);
            System.out.println("ultima data: " + dataUltimoAcesso);
            //caso numero de tipoAcesso menor que três, se tiver mais que 2 horas limpa acesso_login no bd
            if (numAcesso < 3) {
                if (confereHoraUltimoAcesso(dataUltimoAcesso)) {
                    OperacoesBancoDeDados limpaAcessoLogin = new OperacoesBancoDeDados();
                    String condicao = "nome_user='" + user + "'";
                    limpaAcessoLogin.atualizarLinhasBD("permissao", "acesso_login", "null", condicao);
                    return true;
                }

            }
            //caso numero de tipoAcesso igual a três, se tiver mais que 24 horas limpa acesso_login no bd, caso contrario bloqueia tipoAcesso
           else if (numAcesso == 3) {
                if (confereDataUltimoAcesso(dataUltimoAcesso)) {
                    OperacoesBancoDeDados limpaAcessoLogin = new OperacoesBancoDeDados();
                    String condicao = "nome_user='" + user + "'";
                    limpaAcessoLogin.atualizarLinhasBD("permissao", "acesso_login", "null", condicao);
                    return true;
                } else {
                    System.out.println("Usuário tentou acessar 3 vezes com senha inválida!");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Método retorna data atual
     *
     * @return dataAtual
     */
    public Date dataAtual() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        Date data = calendar.getTime();

        return data;

    }

    /**
     * Método para verificar se já passou 2 horas após o último tipoAcesso
     * @param String data
     * @return true caso afirmativo
     */
    public boolean confereHoraUltimoAcesso(String data) {
        Date date = null;
        String formato = "dd/MM/yyyy HH:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        //transformar String em date
        try {
            date = (Date) formatter.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //confere data atual
        Date now = dataAtual();

        //duas horas após a data recebida
        calendar.add(Calendar.HOUR, 2);
        Date dataOk = calendar.getTime();

        //se já passou 2 horas após ultimo tipoAcesso
        if (now.after(dataOk)) {
            return true;
        }
        return false;
    }

    /**
     * Método para verificar se já passou 1 dia após último tipoAcesso
     *
     * @param data
     * @return true caso afirmativo
     */
    public boolean confereDataUltimoAcesso(String data) {
        Date date = null;
        String formato = "dd/MM/yyyy HH:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        //transformar String em date
        try {
            date = (Date) formatter.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //confere data atual
        Date now = dataAtual();

        //duas horas após a data recebida
        calendar.add(Calendar.HOUR, 24);
        Date dataOk = calendar.getTime();

        //se já passou 24 horas após ultimo tipoAcesso
        if (now.after(dataOk)) {
            return true;
        }
        return false;
    }

    /**
     * @return the idAutoEscola
     */
    public static int getIdAutoEscola() {
        return idAutoEscola;
    }

    /**
     * @return the iDsAutoEscola
     */
    public static LinkedList<Integer> getiDsAutoEscola() {
        return iDsAutoEscola;
    }

    /**
     * @param aiDsAutoEscola the iDsAutoEscola to set
     */
    public static void setiDsAutoEscola(int id) {
        iDsAutoEscola.add(id);
    }

    /**
     * @return the nomesAutoEscola
     */
    public static LinkedList<String> getNomesAutoEscola() {
        return nomesAutoEscola;
    }

    /**
     * @param aNomesAutoEscola the nomesAutoEscola to set
     */
    public static void setNomesAutoEscola(String nome) {
        nomesAutoEscola.add(nome);
    }

    /**
     * @return the user
     */
    public static String getUser() {
        return user;
    }

    /**
     * @return the cpfUser
     */
    public static String getCpfUser() {
        return cpfUser;
    }

    /**
     * @return the autoEscola
     */
    public static String getAutoEscola() {
        return autoEscola;
    }

    /**
     * @return the tipoAcesso
     */
    public static int getTipoAcesso() {
        return tipoAcesso;
    }

    /**
     * @return the acessoLogin
     */
    public static String getAcessoLogin() {
        return acessoLogin;
    }

    /**
     * @param aAcessoLogin the acessoLogin to set
     */
    public static void setAcessoLogin(String aAcessoLogin) {
        acessoLogin = aAcessoLogin;
    }

}
