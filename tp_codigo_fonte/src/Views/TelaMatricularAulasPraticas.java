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
public class TelaMatricularAulasPraticas extends javax.swing.JInternalFrame implements Comparable {

    /**
     * Creates new form TelaMatricularAulasPraticas
     */
    public TelaMatricularAulasPraticas() {
        initComponents();
        ArrayList<String> veiculos = new ArrayList<>();
        //preencher com os cursos existentes
        int aux = 0;
        ResultSet rs = null;
        Conexao con = new Conexao();
        String verificaMatriz;

        verificaMatriz = "SELECT nome "
                + "FROM cursos";

        rs = con.executaBusca(verificaMatriz);

        try {
            while (rs.next()) {
                jComboBoxCurso.insertItemAt(rs.getString("nome"), aux);
                aux++;

            }
        } catch (SQLException ex) {
        }
        aux = 0;
        //preencher os professores de turmas práticas
        verificaMatriz = "SELECT F.nome "
                + "FROM funcionario F, instrutor I "
                + "WHERE I.tipo='" + "PRATICO" + "'" + " AND " + " F.cpf_func=I.cpf_func" + " AND " + "id_aut=" + PermissaoAcesso.getIdAutoEscola();

        con = new Conexao();
        rs = con.executaBusca(verificaMatriz);

        try {
            while (rs.next()) {
                jComboBoxProfessor.insertItemAt(rs.getString("nome"), aux);
                aux++;

            }
        } catch (SQLException ex) {
        }

        //preencher com os veículos
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxCurso = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxProfessor = new javax.swing.JComboBox<>();
        jFDataInicio = new javax.swing.JFormattedTextField();
        jTNumeroAulas = new javax.swing.JTextField();
        jFHorárioPreferencia = new javax.swing.JFormattedTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonCadastrar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButtonBuscar = new javax.swing.JButton();
        jFCPF = new javax.swing.JFormattedTextField();
        jTextNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxVeiculos = new javax.swing.JComboBox<>();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Cadastro Aulas Práticas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Data início:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Número de Aulas:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Horário de Preferência:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Curso:");

        jComboBoxCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCursoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Professor:");

        jComboBoxProfessor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        try {
            jFDataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jTNumeroAulas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNumeroAulasActionPerformed(evt);
            }
        });

        try {
            jFHorárioPreferencia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonCadastrar.setText("Cadastrar");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("CPF:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Nome:");

        jButtonBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        try {
            jFCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Veículo:");

        jComboBoxVeiculos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(241, 241, 241))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jFCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jButtonBuscar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextNome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jComboBoxCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(53, 53, 53))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jFDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTNumeroAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFHorárioPreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jComboBoxVeiculos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jFDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTNumeroAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jFHorárioPreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(72, Short.MAX_VALUE))
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

    private void jComboBoxCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCursoActionPerformed

    private void jTNumeroAulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNumeroAulasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTNumeroAulasActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        int aux = 0;
        ResultSet rs = null;
        Conexao con = new Conexao();
        String verificaMatriz;

        String cpfBusca = jFCPF.getText();

        if (!cpfBusca.equals("   .   .   -  ")) {
            //preencher com os cursos existentes

            verificaMatriz = "SELECT * "
                    + "FROM aluno "
                    + "WHERE cpf='" + cpfBusca + "'";
            rs = con.executaBusca(verificaMatriz);

            try {
                if (rs.next()) {
                    jTextNome.setText(rs.getString("nome"));

                } else {
                    JOptionPane.showMessageDialog(null, "Não existe cadastro para esse CPF!");
                }
            } catch (SQLException ex) {
            }

        } else {
            JOptionPane.showMessageDialog(null, "Informe a busca!");
        }


    }//GEN-LAST:event_jButtonBuscarActionPerformed

    /**
     * AÇÃO AO CLICAR NO BOTÃO SALVAR.
     *
     * @param evt
     */
    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        // TODO add your handling code here:
        String nomeCarro = "";
        String id_conj_aulas = "";
        String placa = "";

        String cpfAluno = jFCPF.getText();
        String nomeAluno = jTextNome.getText();
        String nomeCurso = jComboBoxCurso.getSelectedItem().toString();
        String nomeProf = jComboBoxProfessor.getSelectedItem().toString();
        String dataInicio = jFDataInicio.getText();
        String numeroAulas = jTNumeroAulas.getText();
        String horaPref = jFHorárioPreferencia.getText();
        String nomeAux = jComboBoxVeiculos.getSelectedItem().toString();
        char[] dividirCarro = nomeAux.toCharArray();

        //nome do veiculo começa no char 13
        for (int i = 13; i < dividirCarro.length; i++) {
            nomeCarro = nomeCarro + dividirCarro[i];
        }

        System.out.println(nomeCarro);
        String idCurso = null;
        String matricula = null;

        int aux = 0;
        ResultSet rs = null;
        ResultSet rs2 = null;
        Conexao con = new Conexao();
        Conexao con2 = new Conexao();
        String verificaMatriz, verificaMatriz2;

        //capturar id do curso
        verificaMatriz2 = "SELECT * "
                + "FROM  cursos "
                + "WHERE nome= '" + nomeCurso + "'";

        rs2 = con2.executaBusca(verificaMatriz2);

        try {
            if (rs2.next()) {
                idCurso = rs2.getString("id_curso");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao manipular dados, verifique os dados");
            //Logger.getLogger(TelaCadastrarAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }

        //capturar matricula do aluno
        verificaMatriz2 = "SELECT * "
                + "FROM aluno "
                + "WHERE cpf= '" + cpfAluno + "'";

        con2 = new Conexao();
        rs2 = con2.executaBusca(verificaMatriz2);

        try {
            if (rs2.next()) {
                matricula = rs2.getString("matricula");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao manipular dados, verifique os dados");
            //Logger.getLogger(TelaCadastrarAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }

        //cadastro na tabela conj_aulas
        String tabela = "conjunto_aulas(id_conj_aulas, matricula, data_de_inicio,numero_de_aulas,hora_inicio,hora_fim,id_curso)";
        String valores = "(" + "default" + ",'" + matricula + "','" + dataInicio + "','" + numeroAulas + "','" + horaPref + "','" + horaPref + "','" + idCurso + "')";

        OperacoesBancoDeDados inserir = new OperacoesBancoDeDados();
        int resultado = inserir.inserirLinhasBD(tabela, valores);

        //cadastro na tabela usado
        //procurar por placa e id_comj_aulas
        verificaMatriz2 = "SELECT * "
                + "FROM conjunto_aulas "
                + "WHERE matricula= '" + matricula + "'";

        con2 = new Conexao();
        rs2 = con2.executaBusca(verificaMatriz2);

        try {
            if (rs2.next()) {
                id_conj_aulas = rs2.getString("id_conj_aulas");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao manipular dados, verifique os dados");
            //Logger.getLogger(TelaCadastrarAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }

        //procurar pela placa        
        verificaMatriz2 = "SELECT * "
                + "FROM veiculos "
                + "WHERE modelo= '" + nomeCarro + "'";

        con2 = new Conexao();
        rs2 = con2.executaBusca(verificaMatriz2);

        try {
            if (rs2.next()) {
                placa = rs2.getString("placa");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao manipular dados, verifique os dados");
            //Logger.getLogger(TelaCadastrarAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }

        String tabela2 = "usado(id_conj_aulas,placa,data)";
        String valores2 = "('"+id_conj_aulas+"','"+placa+"','"+dataInicio+ "')";

        OperacoesBancoDeDados inserir2 = new OperacoesBancoDeDados();
        int resultado2 = inserir2.inserirLinhasBD(tabela2, valores2);

        
        
        
        if (resultado > 0 && resultado2>0) {
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");

        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JComboBox<String> jComboBoxCurso;
    private javax.swing.JComboBox<String> jComboBoxProfessor;
    private javax.swing.JComboBox<String> jComboBoxVeiculos;
    private javax.swing.JFormattedTextField jFCPF;
    private javax.swing.JFormattedTextField jFDataInicio;
    private javax.swing.JFormattedTextField jFHorárioPreferencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTNumeroAulas;
    private javax.swing.JTextField jTextNome;
    // End of variables declaration//GEN-END:variables

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
