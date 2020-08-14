/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import ConexaoPostgres.Conexao;
import codigo.PermissaoAcesso;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author USUARIO
 */
public class TelaRelatorioCategoriasAlunos extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaRelatorioCategoriasAlunos
     */
    public TelaRelatorioCategoriasAlunos() {
        initComponents();
        gerarRelatorio();
    }

    /**
     * Método para fazer o relatório parecer folha A4
     */
    public void setTamanho() {
        jPanelImprimir.setSize(595, 900);
        this.setMaximizable(true);
         try {
            this.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaRelatorioDesempenhoAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método preenche o relatorio de mapas de aulas
     */
    public void gerarRelatorio() {
        //nome CFC
        jLabelNomeCFC.setText(PermissaoAcesso.getAutoEscola());
        int idAut = PermissaoAcesso.getIdAutoEscola();
        //pesquisa 1 - total alunos

        ResultSet rs = null;
        Conexao con = new Conexao();
        String buscaTotalAlunos = "select distinct count(MT.matricula) "
                + "from matriculado MT,oferece O, auto_escola AE "
                + "where MT.id_curso=O.id_curso and O.id_aut=AE.id_aut and O.id_aut=" + idAut;
        rs = con.executaBusca(buscaTotalAlunos);
        int totalAlunos = 0;
        try {
            while (rs.next()) {
                totalAlunos = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaDadosCFC.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (totalAlunos > 1) {
            jLabelTotalAlunos.setText("O " + PermissaoAcesso.getAutoEscola() + "possui " + totalAlunos + " alunos no total.");
        } else {
            jLabelTotalAlunos.setText("O " + PermissaoAcesso.getAutoEscola() + "possui " + totalAlunos + " aluno no total.");
        }
        //pesquisa 2 - total alunos por categoria
        con = new Conexao();
        rs = null;
        PreparedStatement ps = null;
        String buscaTotalCategoria = "select distinct count(M.matricula), A.tipo_habilitacao "
                + "from matriculado M,oferece O, auto_escola AE, aluno A "
                + "where M.id_curso=O.id_curso and O.id_aut=AE.id_aut and AE.id_aut="+idAut+" and M.matricula=A.matricula "
                + "group by A.tipo_habilitacao "
                + "union "
                + "select distinct 0,V.tipo "
                + "from veiculos V,aluno AL, oferece O, auto_escola AE "
                + "where V.tipo not in(select distinct A.tipo_habilitacao "
                + "from matriculado M,oferece O, auto_escola AE, aluno A "
                + "where M.id_curso=O.id_curso and O.id_aut=AE.id_aut and AE.id_aut="+idAut+" and M.matricula=A.matricula) "
                + "order by tipo_habilitacao asc";
        try {
            ps = con.getConexao().prepareStatement(buscaTotalCategoria);
            rs = ps.executeQuery();
            jTableTotalPorCategoria.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            Logger.getLogger(TelaDadosCFC.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        //nome endereço e telefones da CFC
        rs = null;
        con = new Conexao();
        String buscaEndTel = "SELECT telefone1,telefone2,end_cep,end_cidade,end_rua,end_numero,end_bairro FROM auto_escola WHERE id_aut=" + PermissaoAcesso.getIdAutoEscola();

        rs = con.executaBusca(buscaEndTel);
        String telefone = null;
        String celular = null;
        String cep = null;
        String cidade = null;
        String rua = null;
        String numero = null;
        String bairro = null;
        try {
            while (rs.next()) {
                telefone = rs.getString("telefone1");
                celular = rs.getString("telefone2");
                cep = rs.getString("end_cep");
                cidade = rs.getString("end_cidade");
                rua = rs.getString("end_rua");
                numero = rs.getString("end_numero");
                bairro = rs.getString("end_bairro");

            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaDadosCFC.class.getName()).log(Level.SEVERE, null, ex);
        }
        String end = rua + ", n. " + numero + ", bairro: " + bairro + ", " + cep + ", " + cidade;
        String tel = telefone + " / " + celular;
        jLabelTelefones.setText(tel);
        jLabelendereco.setText(end);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelImprimir = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelendereco = new javax.swing.JLabel();
        jLabelTelefones = new javax.swing.JLabel();
        jLabelNomeCFC = new javax.swing.JLabel();
        jLabelTotalAlunos = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableTotalPorCategoria = new javax.swing.JTable();

        setClosable(true);

        jPanelImprimir.setBackground(new java.awt.Color(255, 255, 255));
        jPanelImprimir.setMaximumSize(new java.awt.Dimension(595, 824));
        jPanelImprimir.setMinimumSize(new java.awt.Dimension(595, 824));
        jPanelImprimir.setPreferredSize(new java.awt.Dimension(595, 824));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Relação Alunos versus Categorias");

        jLabelendereco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelendereco.setText("Endereço");

        jLabelTelefones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTelefones.setText("Telefone");

        jLabelNomeCFC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNomeCFC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNomeCFC.setText("Nome CFC");

        jLabelTotalAlunos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTotalAlunos.setText("Número total de alunos:                ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Quantidade de alunos por categoria              ");

        jTableTotalPorCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTableTotalPorCategoria);

        javax.swing.GroupLayout jPanelImprimirLayout = new javax.swing.GroupLayout(jPanelImprimir);
        jPanelImprimir.setLayout(jPanelImprimirLayout);
        jPanelImprimirLayout.setHorizontalGroup(
            jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImprimirLayout.createSequentialGroup()
                .addGroup(jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabelNomeCFC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
                        .addComponent(jLabelTelefones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelImprimirLayout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(jLabel4)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelImprimirLayout.createSequentialGroup()
                .addGroup(jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabelendereco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelImprimirLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelImprimirLayout.createSequentialGroup()
                            .addGap(154, 154, 154)
                            .addGroup(jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelTotalAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(3, 3, Short.MAX_VALUE))
        );
        jPanelImprimirLayout.setVerticalGroup(
            jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImprimirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNomeCFC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTelefones)
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jLabelTotalAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE)
                .addComponent(jLabelendereco)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanelImprimir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1004, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelNomeCFC;
    private javax.swing.JLabel jLabelTelefones;
    private javax.swing.JLabel jLabelTotalAlunos;
    private javax.swing.JLabel jLabelendereco;
    private javax.swing.JPanel jPanelImprimir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableTotalPorCategoria;
    // End of variables declaration//GEN-END:variables
}
