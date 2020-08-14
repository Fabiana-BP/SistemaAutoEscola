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
import java.text.ParseException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author USUARIO
 */
public class TelaRelatorioSituacaoAlunos extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaRelatorioSituacaoAlunos
     */
    public TelaRelatorioSituacaoAlunos() {
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

    public String buscarCurso() {
        //pesquisar nomes de cursos
        LinkedList<String> cursos = new LinkedList<>();

        String buscacurso = "select C.nome from cursos C,oferece O where C.id_curso=O.id_curso and O.id_aut=" + PermissaoAcesso.getIdAutoEscola();

        Conexao con = new Conexao();
        ResultSet rs = null;

        rs = con.executaBusca(buscacurso);
        try {
            while (rs.next()) {
                String curso = rs.getString("nome");
                if (curso != null) {
                    cursos.add(curso);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        int quantCursos = cursos.size();
        if (quantCursos > 0) {
            String[] escolha = new String[quantCursos];
            for (int i = 0; i < cursos.size(); i++) {
                escolha[i] = cursos.get(i);
            }

            String tipo = (String) JOptionPane.showInputDialog(null, "Escolha o curso: ",
                    "Situação por Curso", JOptionPane.QUESTION_MESSAGE, null, // Use
                    // default
                    // icon
                    escolha, // Array of choices
                    escolha[0]); // Initial choice
            String cursoEscolhido = "";
            System.out.println("tipo:" + tipo);
            cursoEscolhido = tipo;
            System.out.println("Curso escolhido: " + cursoEscolhido);

            return cursoEscolhido;

        } else {
            JOptionPane.showMessageDialog(null, "Nenhum curso cadastrado!");
            this.dispose();
            return null;
        }
    }

    public void gerarRelatorio() {
        //nome CFC
        jLabelNomeCFC.setText(PermissaoAcesso.getAutoEscola());
        int idAut = PermissaoAcesso.getIdAutoEscola();
        //pesquisa 1 - pelo curso escolhido buscar por subcursos teóricos 
        String cursoEscolhido = buscarCurso();

        jLabelnomeCurso.setText(cursoEscolhido);
        ResultSet rs = null;
        Conexao con = new Conexao();
        String buscaSituacaoAlunos = "select distinct AL.nome, AV.nota,AV.Resultado "
                + "from cursos C,oferece O,aluno AL,avaliado AV,matriculado MT,turmas_teoricas TT "
                + "where AV.matricula=MT.matricula and MT.matricula=AL.matricula and MT.id_curso=TT.id_curso "
                + "and C.nome='" + cursoEscolhido + "' and TT.id_curso=O.id_curso and O.id_curso=C.id_curso and O.id_aut=" + idAut + " "
                + "union "
                + "select distinct ALU.nome, 0,'C' "
                + "from cursos CUR, oferece OFE,matriculado M,turmas_teoricas T,avaliado AVAL, aluno ALU "
                + "where CUR.ch_curso_teorico>0 and CUR.id_curso=OFE.id_curso and OFE.id_aut=" + idAut + " and CUR.id_curso=M.id_curso  and M.matricula=ALU.matricula and CUR.nome='" + cursoEscolhido + "' "
                + "and M.matricula not in(select distinct AV.matricula "
                + "from cursos C,oferece O,aluno AL,avaliado AV,matriculado MT,turmas_teoricas TT "
                + "where AV.matricula=MT.matricula and MT.matricula=AL.matricula and MT.id_curso=TT.id_curso "
                + "and C.nome='" + cursoEscolhido + "' and TT.id_curso=O.id_curso and O.id_curso=C.id_curso and O.id_aut=" + idAut + " "
                + ") "
                + " order by nome";
        rs = con.executaBusca(buscaSituacaoAlunos);
        PreparedStatement ps = null;
        con = new Conexao();
        try {
            ps = con.getConexao().prepareStatement(buscaSituacaoAlunos);
            rs = ps.executeQuery();
            jTableSituacaoCursosTeoricos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            Logger.getLogger(TelaDadosCFC.class.getName()).log(Level.SEVERE, null, ex);
        }
        //pesquisa 2 - buscar pelos cursos práticos

        rs = null;
        con = new Conexao();
        String buscaSituacaoAlunosPratica = "select distinct AL.nome, S.resultado "
                + "from submetido S,conjunto_aulas CA,aluno AL, oferece O, cursos C "
                + "where S.matricula=CA.matricula and AL.matricula=S.matricula and CA.id_curso=O.id_curso and O.id_aut="+idAut
                + " and CA.id_curso=C.id_curso and C.nome= '" + cursoEscolhido + "' "
                + " union "
                + "select distinct ALU.nome,'C' "
                + "from submetido SU, conjunto_aulas COA, aluno ALU, cursos CS,oferece O "
                + "where COA.matricula=ALU.matricula and COA.id_curso=CS.id_curso and O.id_aut=" + idAut + " and CS.nome='" + cursoEscolhido + "' "
                + " and COA.matricula not in(select SUB.matricula "
                + "from submetido SUB, conjunto_aulas CONJ, oferece OFE, cursos CURS "
                + "where SUB.matricula=CONJ.matricula and CONJ.id_curso=OFE.id_curso and "
                + "CONJ.id_curso=CURS.id_curso and CURS.nome= '" + cursoEscolhido + "' and OFE.id_aut="+idAut+") "
                + " order by nome asc";

        rs = con.executaBusca(buscaSituacaoAlunos);
        ps = null;
        con = new Conexao();
        try {
            ps = con.getConexao().prepareStatement(buscaSituacaoAlunosPratica);
            rs = ps.executeQuery();
            jTableSituacaoCursosPraticos.setModel(DbUtils.resultSetToTableModel(rs));

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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSituacaoCursosTeoricos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabelendereco = new javax.swing.JLabel();
        jLabelTelefones = new javax.swing.JLabel();
        jLabelNomeCFC = new javax.swing.JLabel();
        jLabelnomeCurso = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableSituacaoCursosPraticos = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(1219, 436));

        jPanelImprimir.setBackground(new java.awt.Color(255, 255, 255));
        jPanelImprimir.setMaximumSize(new java.awt.Dimension(595, 824));
        jPanelImprimir.setMinimumSize(new java.awt.Dimension(595, 824));
        jPanelImprimir.setPreferredSize(new java.awt.Dimension(595, 824));

        jTableSituacaoCursosTeoricos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableSituacaoCursosTeoricos);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Situação dos alunos");

        jLabelendereco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelendereco.setText("Endereço");

        jLabelTelefones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTelefones.setText("Telefone");

        jLabelNomeCFC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNomeCFC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNomeCFC.setText("Nome CFC");

        jLabelnomeCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelnomeCurso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelnomeCurso.setText("Nome do Curso");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Curso Teórico");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Legenda: A- Aprovado             R- Reprovado                   C- Em Curso (não realizou o exame ainda)");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Curso Prático");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Legenda: A- Aprovado             R- Reprovado                   C- Em Curso (não realizou o exame ainda)");

        jTableSituacaoCursosPraticos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTableSituacaoCursosPraticos);

        javax.swing.GroupLayout jPanelImprimirLayout = new javax.swing.GroupLayout(jPanelImprimir);
        jPanelImprimir.setLayout(jPanelImprimirLayout);
        jPanelImprimirLayout.setHorizontalGroup(
            jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTelefones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelNomeCFC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelendereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelImprimirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelImprimirLayout.createSequentialGroup()
                        .addComponent(jLabelnomeCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanelImprimirLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelImprimirLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelImprimirLayout.createSequentialGroup()
                                    .addGroup(jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(516, 516, 516))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelImprimirLayout.createSequentialGroup()
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(76, 76, 76)))))))
        );
        jPanelImprimirLayout.setVerticalGroup(
            jPanelImprimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImprimirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNomeCFC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabelTelefones)
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addGap(63, 63, 63)
                .addComponent(jLabelnomeCurso)
                .addGap(43, 43, 43)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelendereco)
                .addGap(62, 62, 62))
        );

        jScrollPane1.setViewportView(jPanelImprimir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelNomeCFC;
    private javax.swing.JLabel jLabelTelefones;
    private javax.swing.JLabel jLabelendereco;
    private javax.swing.JLabel jLabelnomeCurso;
    private javax.swing.JPanel jPanelImprimir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableSituacaoCursosPraticos;
    private javax.swing.JTable jTableSituacaoCursosTeoricos;
    // End of variables declaration//GEN-END:variables
}
