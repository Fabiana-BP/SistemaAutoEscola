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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author USUARIO
 */
public class TelaMatricularPratico extends javax.swing.JInternalFrame implements Comparable {

    private static String instrutor = "";
    private static String PlacaCarro = "";

    /**
     * @return the instrutor
     */
    public static String getInstrutor() {
        return instrutor;
    }

    /**
     * @return the PlacaCarro
     */
    public static String getPlacaCarro() {
        return PlacaCarro;
    }

    /**
     * Método retorna se já tem na tabela conjunto_aulas o instrutor e carro no
     * horario especificado
     *
     * @param instrutor
     * @param horaInicio
     * @param horaFim
     * @param placaCarro
     * @return
     */
    public boolean verificarDisponibilidadeOK(String instrutor, String horaInicio, String horaFim, String placaCarro) {
        /*preenche informações da tabela*/
        Conexao con = new Conexao();
        String disponibilidade = "select count (CA.id_conj_aulas) "
                + "from conjunto_aulas CA,veiculos V,funcionario F,usado U "
                + "where F.nome='" + instrutor + "' and F.cpf_func=CA.cpf_func and V.placa='" + placaCarro + "' and V.placa=U.placa and "
                + "u.id_conj_aulas=CA.id_conj_aulas and V.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and CA.hora_inicio='" + horaInicio + "' and CA.hora_fim!='" + horaFim + "' ";

        ResultSet rs = null;
        rs = con.executaBusca(disponibilidade);
        System.out.println("id_aut=" + PermissaoAcesso.getIdAutoEscola());
        try {
            while (rs.next()) {
                if (rs.getInt("count") > 0) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao manipular dados, verifique os dados");
            //Logger.getLogger(TelaDadosCFC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    
    /*Método retorna numero de dias úteis entre duas datas*/
    static long days(Date start, Date end) {
        //Ignore argument check

        Calendar c1 = Calendar.getInstance();
        c1.setTime(start);
        int w1 = c1.get(Calendar.DAY_OF_WEEK);
        c1.add(Calendar.DAY_OF_WEEK, -w1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        int w2 = c2.get(Calendar.DAY_OF_WEEK);
        c2.add(Calendar.DAY_OF_WEEK, -w2);

        //end Saturday to start Saturday 
        long days = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
        long daysWithoutSunday = days - (days * 2 / 7);

        return daysWithoutSunday - w1 + w2;
    }

    /**
     * Método para conferir se o aluno cumpriu a ch do curso de legislacao
     *
     * @param dataComecarAulaPratica
     * @param nomeCurso
     * @param cpfAluno
     * @return true caso possa concluir matricula em aulas práticas
     */
    public boolean VerificarData(String dataComecarAulaPratica, String nomeCurso, String cpfAluno) {
        Date dataDesejada = null;
        String formato = "dd/MM/yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        //transformar data informada para comecar aula em date
        try {
            dataDesejada = (Date) formatter.parse(dataComecarAulaPratica);
        } catch (ParseException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataDesejada);
        Date dataRequerida = calendar.getTime();

        //Pegar CH Curso
        String busca = "select distinct C.ch_curso_teorico from cursos C "
                + "where C.nome='" + nomeCurso + "'";
        ResultSet rs = null;
        Conexao con = new Conexao();
        int ch = 0;
        rs = con.executaBusca(busca);
        try {
            while (rs.next()) {
                ch = rs.getInt("ch_curso_teorico");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //transformar ch em minuto

        int diasCumprir = ch / 10;
        //int hora = (dias * 60);
        //Pegar data inicio curso
        String dataMatriculado = "";
        String buscaDataMatricula = "select M.data_da_matricula "
                + "from matriculado M,aluno A,cursos C, oferece O "
                + "where M.matricula=A.matricula and M.id_curso=C.id_curso and C.id_curso=O.id_curso and O.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and A.cpf='" + cpfAluno + "'";
        con = new Conexao();
        rs = con.executaBusca(buscaDataMatricula);
        try {
            while (rs.next()) {
                dataMatriculado = rs.getString("data_da_matricula");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Date dataMatricula = null;

        //transformar data da matricula em date
        try {
            dataMatricula = (Date) formatter.parse(dataMatriculado);
        } catch (ParseException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(dataMatricula);

        long diasUteis=days(dataMatricula,dataDesejada);
        
        if(diasUteis>diasCumprir){
            return true;
        }
       /* //somar a data de matricula com a ch transformada em minuto
        calendar1.add(Calendar.HOUR, hora);
        Date dataOk = calendar1.getTime();

        //verificar se data desejada é igual ou maior a data ok
        if (dataRequerida.after(dataOk)) {
            return true;
        }*/
        return false;
    }

    /**
     * Creates new form TelaMatricularAulasPraticas
     */
    public TelaMatricularPratico() {
        initComponents();
        jButtonCadastrar.setVisible(false);
        jLabelHoraInicio.setVisible(false);
        jLabeHoraFim.setVisible(false);
        jFHoraInicio.setVisible(false);
        jFHoraFim.setVisible(false);
        ArrayList<String> veiculos = new ArrayList<>();
        //preencher com os cursos existentes
        int aux = 0;
        ResultSet rs = null;
        Conexao con = new Conexao();
        String verificaMatriz;

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
                + "FROM veiculos WHERE id_aut=" + PermissaoAcesso.getIdAutoEscola();
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
        jLabel8 = new javax.swing.JLabel();
        jComboBoxCurso = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxProfessor = new javax.swing.JComboBox<>();
        jFDataInicio = new javax.swing.JFormattedTextField();
        jTNumeroAulas = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonCadastrar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButtonBuscar = new javax.swing.JButton();
        jFCPF = new javax.swing.JFormattedTextField();
        jTextNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxVeiculos = new javax.swing.JComboBox<>();
        jButtonVerificarDisponibilidade = new javax.swing.JButton();
        jLabelHoraInicio = new javax.swing.JLabel();
        jLabeHoraFim = new javax.swing.JLabel();
        jFHoraFim = new javax.swing.JFormattedTextField();
        jFHoraInicio = new javax.swing.JFormattedTextField();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Cadastro Aulas Práticas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Data início:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Número de Aulas:");

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

        jButtonVerificarDisponibilidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonVerificarDisponibilidade.setText("Verificar disponibilidade do instrutor e veículo ");
        jButtonVerificarDisponibilidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerificarDisponibilidadeActionPerformed(evt);
            }
        });

        jLabelHoraInicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelHoraInicio.setText("Hora início:");

        jLabeHoraFim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabeHoraFim.setText("Hora fim:");

        jFHoraFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));

        jFHoraInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jComboBoxVeiculos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNumeroAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addComponent(jButtonVerificarDisponibilidade)
                                .addGap(38, 38, 38))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextNome)
                            .addComponent(jComboBoxCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(50, 50, 50))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addGap(48, 48, 48)
                        .addComponent(jFCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButtonBuscar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelHoraInicio)
                        .addGap(18, 18, 18)
                        .addComponent(jFHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabeHoraFim)
                        .addGap(16, 16, 16)
                        .addComponent(jFHoraFim, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar))
                .addGap(25, 25, 25)
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
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jFDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTNumeroAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVerificarDisponibilidade))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHoraInicio)
                    .addComponent(jLabeHoraFim)
                    .addComponent(jFHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFHoraFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVerificarDisponibilidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerificarDisponibilidadeActionPerformed

        instrutor = (String) jComboBoxProfessor.getSelectedItem();
        String[] separarModelo = jComboBoxVeiculos.getSelectedItem().toString().split(":");
        String modelo = separarModelo[1];
        String[] separarModelo2 = modelo.split(" ");
        String modeloVeiculo = separarModelo2[1];
        System.out.println("modelo captado=" + modeloVeiculo);
        //procurar pela placa
        String verificaMatriz2 = "SELECT V.placa "
                + "FROM veiculos V "
                + "WHERE V.modelo= '" + modeloVeiculo + "' and V.id_aut=" + PermissaoAcesso.getIdAutoEscola();
        ResultSet rs = null;
        Conexao con = new Conexao();
        rs = con.executaBusca(verificaMatriz2);
        System.out.println("modelo=" + modeloVeiculo);

        try {
            if (rs.next()) {
                PlacaCarro = rs.getString("placa");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao manipular dados, verifique os dados");
            //Logger.getLogger(TelaCadastrarAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Placa=" + PlacaCarro);
        TelaVerificaDisponibilidadePratica disp = new TelaVerificaDisponibilidadePratica();
        disp.setVisible(true);

        jButtonCadastrar.setVisible(true);
        jLabelHoraInicio.setVisible(true);
        jLabeHoraFim.setVisible(true);
        jFHoraInicio.setVisible(true);
        jFHoraFim.setVisible(true);
    }//GEN-LAST:event_jButtonVerificarDisponibilidadeActionPerformed

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
        //buscar nome do curso
        verificaMatriz = "SELECT C.nome "
                + "FROM aluno A,cursos C,matriculado M WHERE M.id_curso=C.id_curso and A.cpf='" + cpfBusca + "' and A.matricula=M.matricula";
        con = new Conexao();
        rs = con.executaBusca(verificaMatriz);

        try {
            while (rs.next()) {
                jComboBoxCurso.insertItemAt(rs.getString("nome"), aux);
                aux++;

            }
        } catch (SQLException ex) {
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
        String nomeAux = jComboBoxVeiculos.getSelectedItem().toString();
        String horaInicio = jFHoraInicio.getText();
        String horaFim = jFHoraFim.getText();
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

        //procurar pela placa
        verificaMatriz2 = "SELECT V.placa "
                + "FROM veiculos V "
                + "WHERE V.modelo= '" + nomeCarro + "' and id_aut=" + PermissaoAcesso.getIdAutoEscola();

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
        //buscar cpf do instrutor
        String cpfFunc = "";
        verificaMatriz = "SELECT F.cpf_func "
                + "FROM funcionario F "
                + "WHERE F.nome='" + jComboBoxProfessor.getSelectedItem() + "' and F.id_aut=" + PermissaoAcesso.getIdAutoEscola();

        con = new Conexao();
        rs = con.executaBusca(verificaMatriz);

        try {
            while (rs.next()) {
                cpfFunc = rs.getString("cpf_func");
            }
        } catch (SQLException ex) {
        }

        int cont = 0;
        int resultado = 0;

        if (verificarDisponibilidadeOK(nomeProf, horaInicio, horaFim, placa)) {
            if (VerificarData(jFDataInicio.getText(), jComboBoxCurso.getSelectedItem().toString(), jFCPF.getText())) {
                //cadastro na tabela conj_aulas
                String tabela = "conjunto_aulas(id_conj_aulas, matricula, data_de_inicio,numero_de_aulas,hora_inicio,hora_fim,id_curso,cpf_func)";
                String valores = "(" + "default" + ",'" + matricula + "','" + dataInicio + "','" + numeroAulas + "','" + horaInicio + "','" + horaFim + "','" + idCurso + "','" + cpfFunc + "')";
                OperacoesBancoDeDados inserir = new OperacoesBancoDeDados();
                resultado = inserir.inserirLinhasBD(tabela, valores);
                if (resultado > 0) {
                    cont = 1;
                }
            } else {
                cont = 0;
                JOptionPane.showMessageDialog(null, "O aluno ainda não cumpriu a carga horária!");
                jFCPF.setText("");
                jFCPF.setText("");
                jComboBoxCurso.setSelectedItem("Selecione");
                jComboBoxProfessor.setSelectedItem("Selecione");
                jComboBoxVeiculos.setSelectedItem("Selecione");
                jFDataInicio.setText("");
                jTNumeroAulas.setText("");
                jFHoraInicio.setText("");
                jFHoraFim.setText("");
                jButtonCadastrar.setVisible(false);
                jLabelHoraInicio.setVisible(false);
                jLabeHoraFim.setVisible(false);
                jFHoraInicio.setVisible(false);
                jFHoraFim.setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Essa combinação escolhida não está disponível. Por favor, escolha outro instrutor, veículo ou um horário diferente");
        }

        //cadastro na tabela usado
        //procurar por placa e id_comj_aulas
        verificaMatriz2 = "SELECT CO.id_conj_aulas "
                + "FROM conjunto_aulas CO,oferece O "
                + "WHERE CO.matricula= '" + matricula + "' and CO.id_curso=O.id_Curso and O.id_aut=" + PermissaoAcesso.getIdAutoEscola();

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
        int resultado2 = 0;
        if (cont == 1) {
            String tabela2 = "usado";
            String valores2 = "(" + id_conj_aulas + ",'" + placa + "','" + dataInicio + "')";

            OperacoesBancoDeDados inserir2 = new OperacoesBancoDeDados();

            resultado2 = inserir2.inserirLinhasBD(tabela2, valores2);
        }
        if (resultado > 0 && resultado2 > 0) {
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");

        }

    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTNumeroAulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNumeroAulasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTNumeroAulasActionPerformed

    private void jComboBoxCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCursoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonVerificarDisponibilidade;
    private javax.swing.JComboBox<String> jComboBoxCurso;
    private javax.swing.JComboBox<String> jComboBoxProfessor;
    private javax.swing.JComboBox<String> jComboBoxVeiculos;
    private javax.swing.JFormattedTextField jFCPF;
    private javax.swing.JFormattedTextField jFDataInicio;
    private javax.swing.JFormattedTextField jFHoraFim;
    private javax.swing.JFormattedTextField jFHoraInicio;
    private javax.swing.JLabel jLabeHoraFim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelHoraInicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTNumeroAulas;
    private javax.swing.JTextField jTextNome;
    // End of variables declaration//GEN-END:variables

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
