/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import ConexaoPostgres.Conexao;



/**
 *
 * @author USUARIO
 */
public class OperacoesBancoDeDados {

    /**
     *Método insere linhas em uma tabela do banco de dados
     * @param tabela tabela(atributo1,atributo2,...,atributoN)
     * @param valores (1,'Maria',...,'João Monlevade')          necessário indicar os valores com parênteses e strings com aspas simples
     * @return resultado indicando se houve erro na inserção (menor que zero) ou não
     */
    public int inserirLinhasBD(String tabela, String valores) {

        Conexao con = new Conexao();
        String sql="INSERT into "+tabela+" values "+valores;
        int resultado=con.executaSQL(sql);
        if(resultado>0){
            System.out.println("Cadastrado com sucesso!");
        }else{
            System.out.println("Erro de inserção durante conexão com banco de dados!");
        }
        return resultado;       
    }
    
    /**
     *Método atualiza linhas em uma tabela do banco de dados considerando algum critério
     * @param tabela pode ser apenas o nome da tabela sem indicar os atributos, exemplo: aluno 
     * @param coluna qual coluna quer modificar o valor, exemplo: coluna
     * @param novoValor string deve ser indicada com aspas simples, exemplo: '35.931-145'
     * @param condicao critérios, exemplo: id=3
     * @return resultado indicando se houve erro na atualização (menor que zero) ou não
     */
    public int atualizarLinhasBD(String tabela, String coluna,String novoValor, String condicao){
        
        Conexao con=new Conexao();
        String sql="UPDATE "+tabela+" SET "+coluna+" = "+novoValor+" WHERE "+condicao;
        int resultado=con.executaSQL(sql);
        if(resultado>0){
            System.out.println("Dado(s) atualizados com sucesso!");
        }else{
            System.out.println("Erro de gravação durante conexão com banco de dados!");
        }
        return resultado;
    }
    
    /**
     *Método atualiza todas as linhas de uma coluna específica de uma tabela
     * @param tabela   pode ser apenas o nome da tabela sem indicar os atributos, exemplo: prova_teorica
     * @param coluna   qual coluna quer modificar o valor, exemplo: local
     * @param novoValor string deve ser indicada com aspas simples, exemplo: 'UEMG'
     * @return resultado indicando se houve erro na atualização (menor que zero) ou não
     */
    public int atualizarLinhasBD(String tabela, String coluna,String novoValor){
        
        Conexao con=new Conexao();
        String sql="UPDATE "+tabela+" set "+coluna+" = "+novoValor;
        int resultado=con.executaSQL(sql);
        if(resultado>0){
            System.out.println("Dados(s) atualizados com sucesso!");
        }else{
            System.out.println("Erro de gravação durante conexão com banco de dados!");
        }
        return resultado;
    }
    
    /**
     * Método exclui linhas considerando algum critério
     * @param tabela pode ser apenas o nome da tabela sem indicar os atributos, exemplo: veiculos
     * @param condicao critérios, exemplo: placa='ABC-1234'
     * @return resultado indicando se houve erro na atualização (menor que zero) ou não
     */
    public int deletarLinhasBD(String tabela,String condicao){
        
        Conexao con=new Conexao();
        String sql="DELETE FROM "+tabela+" WHERE "+condicao;
        int resultado=con.executaSQL(sql);
        if(resultado>0){
            System.out.println("Linha excluida com sucesso!");
        }else{
            System.out.println("Erro de remoção no banco de dados!");
        }
        return resultado;
        
    }
    
    /**
     * Método exclui todas as linhas de uma tabela
     * @param tabela pode ser apenas o nome da tabela sem indicar os atributos, exemplo: veiculos
     * @return resultado indicando se houve erro na atualização (menor que zero) ou não
     */
    public int deletarLinhasBD(String tabela){
        
        Conexao con=new Conexao();
        String sql="DELETE FROM "+tabela;
        int resultado=con.executaSQL(sql);
        if(resultado>0){
            System.out.println("Linhas excluidas com sucesso!");
        }else{
            System.out.println("Erro de remoção no banco de dados!");
        }
        return resultado;
        
    }
}
