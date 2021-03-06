/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import ConexaoPostgres.Conexao;
import codigo.HashSenha;
import codigo.PermissaoAcesso;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class TelaLogin extends javax.swing.JFrame {

    PermissaoAcesso permissaoAcesso;

    /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {

        initComponents();
        permissaoAcesso = new PermissaoAcesso();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxNomeCFCLogin = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNomeUserLogin = new javax.swing.JTextField();
        jPasswordFieldSenhaLogin = new javax.swing.JPasswordField();
        jButtonCancelarLogin = new javax.swing.JButton();
        jButtonEntrarLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jComboBoxNomeCFCLogin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("LOGIN");

        jLabel2.setText("CFC:");

        jLabel3.setText("USER:");

        jLabel4.setText("SENHA:");

        jPasswordFieldSenhaLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldSenhaLoginActionPerformed(evt);
            }
        });

        jButtonCancelarLogin.setText("CANCELAR");
        jButtonCancelarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarLoginActionPerformed(evt);
            }
        });

        jButtonEntrarLogin.setText("ENTRAR");
        jButtonEntrarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEntrarLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordFieldSenhaLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addComponent(jTextFieldNomeUserLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxNomeCFCLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(jLabel1)))
                .addGap(91, 91, 91))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonCancelarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButtonEntrarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxNomeCFCLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNomeUserLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordFieldSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEntrarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordFieldSenhaLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldSenhaLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldSenhaLoginActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        /*Buscar nomes e IDs de autoEscola cadastrados*/

        ResultSet rs = null;
        String nomeCFC = "";
        int idAut = 0;

        Conexao con = new Conexao();

        String verificaCFC = "SELECT id_Aut,nome "
                + "FROM auto_escola";

        rs = con.executaBusca(verificaCFC);
        try {
            while (rs.next()) {
                idAut = rs.getInt("id_aut");
                nomeCFC = rs.getString("nome");
                PermissaoAcesso.setiDsAutoEscola(idAut);
                PermissaoAcesso.setNomesAutoEscola(nomeCFC);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastrarCFC.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rs == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma CFC cadastrada! Por favor contate os técnicos");
            System.exit(0);
        }
        PermissaoAcesso.getNomesAutoEscola().forEach((nome) -> {
            jComboBoxNomeCFCLogin.addItem(nome);
        });

    }//GEN-LAST:event_formComponentShown

    private void jButtonEntrarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEntrarLoginActionPerformed

        int acesso;
        String senha = "", user = "";
        /*validar autoEscola*/
        String autoEscolaSelecionada = jComboBoxNomeCFCLogin.getSelectedItem().toString();
        if (autoEscolaSelecionada == "Selecione") {
            JOptionPane.showMessageDialog(null, "Nenhuma CFC selecionada!");
        } else {
            /*validar usuário*/
            user = jTextFieldNomeUserLogin.getText();
            //criptografar senha
            HashSenha hash = new HashSenha();
            senha = hash.hashSenha(jPasswordFieldSenhaLogin.getText());

            permissaoAcesso.permissao(autoEscolaSelecionada);
            if (user.equals("") || senha.equals("")) {
                JOptionPane.showMessageDialog(null, "Ainda há dados para serem preenchidos!");
            } else {
                acesso = permissaoAcesso.permissaoUser(user, senha);
                System.out.println("acesso=" + acesso);
                boolean controleAcesso = permissaoAcesso.gerenciaAcesso();
                if (controleAcesso) {
                    permissaoAcesso.atualizaAcessoLogin();
                } else if (controleAcesso == false && acesso != 0) {
                    JOptionPane.showMessageDialog(null, "Para sua segurança só poderar acessar o sistema após 24 horas");
                    System.exit(0);
                }

                switch (acesso) {
                    case 0:
                        //user ou senha inválidos
                        JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                        jTextFieldNomeUserLogin.setText("");
                        jPasswordFieldSenhaLogin.setText("");
                        jComboBoxNomeCFCLogin.setSelectedIndex(0);
                        break;
                    case 1:
                        //senha inválida
                        JOptionPane.showMessageDialog(null, "Senha inválida!");
                        jTextFieldNomeUserLogin.setText("");
                        jPasswordFieldSenhaLogin.setText("");
                        jComboBoxNomeCFCLogin.setSelectedIndex(0);
                        break;
                    case 2:
                        //user e senha corretos, mas CFC incorreta
                        JOptionPane.showMessageDialog(null, "Usuário não tem permissão para acessar esse CFC!");
                        jTextFieldNomeUserLogin.setText("");
                        jPasswordFieldSenhaLogin.setText("");
                        jComboBoxNomeCFCLogin.setSelectedIndex(0);
                        break;
                    default:
                        TelaPrincipal telaPrincipal = new TelaPrincipal();
                        telaPrincipal.setVisible(true);
                        this.dispose();
                        break;
                }
            }
        }
    }//GEN-LAST:event_jButtonEntrarLoginActionPerformed

    private void jButtonCancelarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarLoginActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonCancelarLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelarLogin;
    private javax.swing.JButton jButtonEntrarLogin;
    private javax.swing.JComboBox<String> jComboBoxNomeCFCLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordFieldSenhaLogin;
    private javax.swing.JTextField jTextFieldNomeUserLogin;
    // End of variables declaration//GEN-END:variables
}
