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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class TelaCadastrarVeiculos extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCadastrarVeiculos
     */
    String procura;

    public TelaCadastrarVeiculos() {
        initComponents();
    }

    /**
     * DEFINIR POSIÇÃO DA TELA
     */
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    /**
     * DEFINIR BOTÕES VISÍVEIS
     *
     * @param b
     */
    public void botoes(boolean b) {

        jButtonExcluir.setVisible(!b);
        jButtonSalvar.setVisible(b);
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
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextModel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextAno = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jCategoria = new javax.swing.JComboBox<>();
        jButtonExcluir = new javax.swing.JButton();
        jTextplaca = new javax.swing.JFormattedTextField();

        setClosable(true);
        setTitle("Cadastro de Veículos");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/carros-venda.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Modelo:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Placa:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Categoria:");

        jTextModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextModelActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Ano:");

        jButtonCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE", "A", "B", "C", "D", "E" }));

        jButtonExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        try {
            jTextplaca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("AAA####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextAno)
                            .addComponent(jTextplaca))
                        .addGap(358, 358, 358))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextModel, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(136, 136, 136))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextplaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextModelActionPerformed

    /**
     * AÇÃO AO PRESSIONAR O BOTÃO SALVAR.
     *
     * @param evt
     */
    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        // TODO add your handling code here:
        int aux = 0;
        int resultado;
        ResultSet rs = null;
        String procuraPlaca = "";
        Conexao con = new Conexao();

        String modelo = jTextModel.getText();
        String ano = jTextAno.getText();
        String placa = jTextplaca.getText();
        String categoria = jCategoria.getSelectedItem().toString();

        if (placa.length() == 7) {
            if (modelo.isEmpty() || ano.isEmpty() || placa.isEmpty() || categoria.equals("SELECIONE")) {
                JOptionPane.showMessageDialog(null, "Ainda existem campos para serem prenchidos!");
                aux = 1;
            } else {
                String verificaMatriz = "SELECT placa "
                        + "FROM veiculos";

                rs = con.executaBusca(verificaMatriz);
                try {
                    while (rs.next()) {
                        if (rs.getString("placa").equals(placa)) {
                            JOptionPane.showMessageDialog(null, "Já existe um veículo com essa placa!!");
                            jTextplaca.setText("");
                            aux = 1;
                        }

                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao manipular dados, verifique os dados");
                    //Logger.getLogger(TelaCadastrarCFC.class.getName()).log(Level.SEVERE, null, ex);
                }   catch (NullPointerException ex) {

                    }
            }
            if (aux == 0) {
                OperacoesBancoDeDados inserir = new OperacoesBancoDeDados();
                String tabela = "veiculos(modelo,ano,placa,tipo,id_aut)";
                String valores = "('" + modelo + "','" + ano + "','" + placa
                        + "','" + categoria + "','" + PermissaoAcesso.getIdAutoEscola() + "')";

                resultado = inserir.inserirLinhasBD(tabela, valores);
                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
                    jTextModel.setText("");
                    jTextAno.setText("");
                    jTextplaca.setText("");
                    jCategoria.setSelectedIndex(0);
                } else {

                    JOptionPane.showMessageDialog(null, "Erro durante Cadastrado. Tente novamente!");
                    jTextModel.setText("");
                    jTextAno.setText("");
                    jTextplaca.setText("");
                    jCategoria.setSelectedIndex(0);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Informe apenas as letras e numeros, sem espaço ou hífen");
        }

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    /**
     * EXCLUIR VEICULO
     *
     * @param evt
     */
    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        // TODO add your handling code here:

        int aux = 0;
        int resultado1;
        ResultSet rs = null;

        Conexao con = new Conexao();


        OperacoesBancoDeDados excluir = new OperacoesBancoDeDados();
        String tabela1 = "veiculos";
        //String tabela2 = "oferece";
        String condicao = "placa='" + this.procura + "'";

        resultado1 = excluir.deletarLinhasBD(tabela1, condicao);

        if (resultado1 > 0) {

            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
            jTextModel.setText("");
            jTextAno.setText("");
            jTextplaca.setText("");
            jCategoria.setSelectedIndex(0);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(null, "Exclusão não efetuada com sucesso. Favor contatar o técnico para verificar a situação!");
            this.dispose();

        }


    }//GEN-LAST:event_jButtonExcluirActionPerformed

    public void ExcluirVeiculo(String procuraPlaca) {
   
        this.procura = procuraPlaca;
        int aux = 0;
        int resultado;
        ResultSet rs = null;

        Conexao con = new Conexao();

        String verificaMatriz = "SELECT *"
                + "FROM veiculos "
                + "WHERE id_aut=" + PermissaoAcesso.getIdAutoEscola()+" AND "+"placa='"+procuraPlaca+"'";

        rs = con.executaBusca(verificaMatriz);
        try {
           if (rs.next()) {
               
                    jTextModel.setText(rs.getString("modelo"));
                    jTextAno.setText(rs.getString("ano"));
                    jTextplaca.setText(rs.getString("placa"));
                    jCategoria.setSelectedItem(rs.getString("tipo"));
                    aux = 1;
                

            }
            if (aux == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum veículo encontrado para esta placa!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao manipular dados, verifique os dados");
            //Logger.getLogger(TelaCadastrarCFC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextAno;
    private javax.swing.JTextField jTextModel;
    private javax.swing.JFormattedTextField jTextplaca;
    // End of variables declaration//GEN-END:variables
}
