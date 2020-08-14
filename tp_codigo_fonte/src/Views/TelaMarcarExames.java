/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import ConexaoPostgres.Conexao;
import codigo.OperacoesBancoDeDados;
import codigo.PermissaoAcesso;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class TelaMarcarExames extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaMarcarExames
     */
    String tipoProva;
    String cpfAluno;
    String matricula;
    String placaCarro;

    public TelaMarcarExames() {
        initComponents();
        jPanel2.setVisible(false);
    }

    /**
     * DEFINE A POSIÇÃO DA TELA.
     */
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jFCPFAluno = new javax.swing.JFormattedTextField();
        jButtonBuscarAluno = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jFData = new javax.swing.JFormattedTextField();
        jFHorario = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextLocal = new javax.swing.JTextField();
        jButtonConfirmar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jComboBoxTipoProva = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextNome = new javax.swing.JTextField();
        jLabelNome = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxVeiculos = new javax.swing.JComboBox<>();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Cadastro de Exames");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("CPF:");

        try {
            jFCPFAluno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButtonBuscarAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonBuscarAluno.setText("Buscar");
        jButtonBuscarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarAlunoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel9)
                        .addGap(16, 16, 16)
                        .addComponent(jFCPFAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(jButtonBuscarAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jFCPFAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarAluno))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Local:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Data:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Horário:");

        try {
            jFData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFDataActionPerformed(evt);
            }
        });

        try {
            jFHorario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/prova-teste03.png"))); // NOI18N

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jComboBoxTipoProva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxTipoProva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PRATICA", "TEORICA" }));
        jComboBoxTipoProva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoProvaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tipo de prova:");

        jLabelNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNome.setText("Nome:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Veículo prova prática:");

        jComboBoxVeiculos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextNome))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxTipoProva, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addComponent(jLabel7))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFData, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxTipoProva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jFHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxTipoProvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoProvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoProvaActionPerformed

    private void jFDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFDataActionPerformed

    private void jButtonBuscarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarAlunoActionPerformed
        // TODO add your handling code here:
        this.cpfAluno = jFCPFAluno.getText();
        String nome = null;

        String verificaMatriz, verificaMatriz2;
        int aux = 0;
        ResultSet rs = null;
        ResultSet rs2 = null;
        Conexao con = new Conexao();
        Conexao con2 = new Conexao();

        verificaMatriz = "SELECT * "
                + "FROM aluno "
                + "WHERE cpf='" + this.cpfAluno + "'";

        rs = con.executaBusca(verificaMatriz);

        try {

            if (rs.next()) {
                jPanel2.setVisible(true);
                nome = rs.getString("nome");
                this.matricula = rs.getString("matricula"); // para salvar na tabel avaliado ou sibmetido
                aux = 1;
            } else {
                JOptionPane.showMessageDialog(null, "CPF não encontado");
                jFCPFAluno.setText("");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao manipular dados, verifique os dados");
            //Logger.getLogger(TelaCadastrarCurso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {

        }
        if (aux == 1) {

            jTextNome.setText(nome);
            //preencher veiculos
            ArrayList<String> veiculos = new ArrayList<>();

            verificaMatriz = "SELECT * "
                    + "FROM veiculos ";
            con = new Conexao();
            rs = con.executaBusca(verificaMatriz);

            try {
                while (rs.next()) {

                    if (rs.getString("tipo").equals("A")) {
                        veiculos.add("CATEGORIA A: " + rs.getString("modelo"));
                    }
                    if (rs.getString("tipo").equals("B")) {
                        veiculos.add("CATEGORIA B: " + rs.getString("modelo"));
                    }
                    if (rs.getString("tipo").equals("C")) {
                        veiculos.add("CATEGORIA C: " + rs.getString("modelo"));
                    }
                    if (rs.getString("tipo").equals("D")) {
                        veiculos.add("CATEGORIA D: " + rs.getString("modelo"));
                    }
                    if (rs.getString("tipo").equals("E")) {
                        veiculos.add("CATEGORIA E: " + rs.getString("modelo"));
                    }
                }
                //colocando array em ordem alfabetica
                Collections.sort(veiculos);
                //System.out.println(Arrays.toString(veiculos.toArray()));

                //lista os carros 
                for (int i = 0; i < veiculos.size(); i++) {
                    jComboBoxVeiculos.insertItemAt(veiculos.get(i), i);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao manipular dados, verifique os dados");
                //Logger.getLogger(TelaCadastrarCurso.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_jButtonBuscarAlunoActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        // TODO add your handling code here:

        String data, local, horario, idteorica = "", idpratica = "";

        this.tipoProva = jComboBoxTipoProva.getSelectedItem().toString();
        local = jTextLocal.getText();
        data = jFData.getText();
        horario = jFHorario.getText();

        String verificaMatriz, verificaMatriz2;
        int aux = 0, resultado, resultado2;
        ResultSet rs = null;
        ResultSet rs2 = null;
        Conexao con = new Conexao();
        Conexao con2 = new Conexao();
        String tabela, tabela2;
        String valores, valores2;
        OperacoesBancoDeDados inserir = new OperacoesBancoDeDados();
        OperacoesBancoDeDados inserir2 = new OperacoesBancoDeDados();

        //se for marcar prova teorica
        if (this.tipoProva.equals("TEORICA")) {
            tabela = "prova_teorica(local,id_teorica,data,horario)";
            valores = "('" + local + "', " + "default" + ",'" + data + "','" + horario + "')";

            resultado = inserir.inserirLinhasBD(tabela, valores);

            verificaMatriz = "SELECT id_teorica "
                    + "FROM prova_teorica "
                    + "ORDER BY id_teorica "
                    + "DESC LIMIT " + 1;

            con = new Conexao();
            rs = con.executaBusca(verificaMatriz);
            int ID = 0;
            try {
                while (rs.next()) {
                    System.out.println(rs.getInt("id_teorica"));
                    ID = rs.getInt("id_teorica");
                }
            } catch (SQLException ex) {
                // Logger.getLogger(TelaMarcarExames.class.getName()).log(Level.SEVERE, null, ex);
            }

            tabela2 = "avaliado(matricula,id_teorica,nota,resultado)";
            valores2 = "('" + this.matricula + "'," + ID + "," + 0 + ",'" + "X" + "')";

            resultado2 = inserir2.inserirLinhasBD(tabela2, valores2);

            if (resultado > 0 && resultado2>0) {
                JOptionPane.showMessageDialog(null, "Prova Marcada!");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao Marcar a prova!");
            }
            //matricular aula pratica
        } else {
            String nomeCarro = "";
            String nomeAux = jComboBoxVeiculos.getSelectedItem().toString();
            char[] dividirCarro = nomeAux.toCharArray();

            //nome do veiculo começa no char 13
            for (int i = 13; i < dividirCarro.length; i++) {
                nomeCarro = nomeCarro + dividirCarro[i];
            }
            System.out.println(nomeCarro);
            verificaMatriz = "SELECT * "
                    + "FROM veiculos "
                    + "WHERE modelo='" + nomeCarro + "'";
            con = new Conexao();
            rs = con.executaBusca(verificaMatriz);

            try {

                if (rs.next()) {
                    this.placaCarro = rs.getString("placa");
                    aux = 1;
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao manipular dados, verifique os dados");
                //Logger.getLogger(TelaCadastrarCurso.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (aux == 1) {
                tabela = "prova_pratica(local,id_pratica,data,horario)";
                valores = "('" + local + "', " + "default" + ",'" + data + "','" + horario + "')";

                resultado = inserir.inserirLinhasBD(tabela, valores);

                verificaMatriz = "SELECT id_pratica "
                        + "FROM prova_pratica "
                        + "ORDER BY id_pratica "
                        + "DESC LIMIT " + 1;

                con = new Conexao();
                rs = con.executaBusca(verificaMatriz);
                int ID = 0;
                try {
                    while (rs.next()) {
                        System.out.println(rs.getInt("id_pratica"));
                        ID = rs.getInt("id_pratica");
                    }
                } catch (SQLException ex) {
                    // Logger.getLogger(TelaMarcarExames.class.getName()).log(Level.SEVERE, null, ex);
                }

                tabela2 = "submetido(matricula,id_pratica,placa,resultado)";
                valores2 = "('" + this.matricula + "'," + ID  + ",'" +this.placaCarro+ "','"+ "X" + "')";

                resultado2 = inserir2.inserirLinhasBD(tabela2, valores2);

                if (resultado > 0 && resultado2 > 0) {
                    JOptionPane.showMessageDialog(null, "Prova Marcada!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao Marcar a prova!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao manipular dados, verifique os dados");
            }
        }
    }//GEN-LAST:event_jButtonConfirmarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarAluno;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JComboBox<String> jComboBoxTipoProva;
    private javax.swing.JComboBox<String> jComboBoxVeiculos;
    private javax.swing.JFormattedTextField jFCPFAluno;
    private javax.swing.JFormattedTextField jFData;
    private javax.swing.JFormattedTextField jFHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextLocal;
    private javax.swing.JTextField jTextNome;
    // End of variables declaration//GEN-END:variables
}