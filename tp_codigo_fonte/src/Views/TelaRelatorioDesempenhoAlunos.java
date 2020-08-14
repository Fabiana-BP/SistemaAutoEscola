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
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import net.proteanit.sql.DbUtils;

/**
 *
 * @author USUARIO
 */
public class TelaRelatorioDesempenhoAlunos extends javax.swing.JInternalFrame {

    private Date dataInicial;
    private Date dataFinal;

    /**
     * Creates new form TelaRelatorioDesempenhoAlunos
     */
    public TelaRelatorioDesempenhoAlunos() {
        initComponents();
        //jScrollPaneAnalisePeriodo.setVisible(false);
        gerarRelatorio();
    }

    /**
     * Método para fazer o relatório parecer folha A4
     */
    public void setTamanho() {
        //jPanelImprimirRelData.setSize(595, 900);
        jPanelImprimirRel1.setSize(595, 900);

        this.setMaximizable(true);
        try {
            this.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaRelatorioDesempenhoAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para usuário informar intervalo de data
     */
    public void BuscarData() {

        MaskFormatter formato = null;
        try {
            formato = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
           // Logger.getLogger(TelaRelatorioDesempenhoCFC.class.getName()).log(Level.SEVERE, null, ex);
        }
        JLabel label = new JLabel("Informe a data inicial desejada: ");
        JFormattedTextField texto = new JFormattedTextField(formato);

        JOptionPane.showMessageDialog(null, new Object[]{label, texto});

        Calendar calendar = Calendar.getInstance();
        String data = texto.getText();
        if (data == null || data.equals("")) {
            JOptionPane.showMessageDialog(null, "Nenhuma data inserida!");
            this.dispose();
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataInicial = (Date) formatter.parse(data);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Valor inválido de data!");
            this.dispose();
            return;
        }

        label = new JLabel("Informe a data final desejada: ");
        texto = new JFormattedTextField(formato);

        JOptionPane.showMessageDialog(null, new Object[]{label, texto});
        data = texto.getText();
        if (data == null || data.equals("")) {
            JOptionPane.showMessageDialog(null, "Nenhuma data inserida!");
            this.dispose();
            return;
        }

        try {
            dataFinal = (Date) formatter.parse(data);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Valor inválido de data!");
            this.dispose();
            return;
        }

        if (dataInicial.after(dataFinal)) {
            JOptionPane.showMessageDialog(null, "Data final maior que a inicial!");
            this.dispose();
        }

    }

    public void gerarRelatorio() {
        System.out.println("Entrou gerarRelatorio");
        /**
         * *************************************************Análises Prova
         * direção************************************************************************************************
         */
        //Pesquisa 1: total aprovados prova direcao por curso

        LinkedList<String> cursoApDir = new LinkedList<>();
        LinkedList<Integer> idcursoApDir = new LinkedList<>();
        LinkedList<Integer> countApDir = new LinkedList<>();

        String busca1 = "select distinct CUR.nome,CUR.id_curso, count (SUB.matricula) "
                + "from submetido SUB, veiculos V,conjunto_aulas CA,oferece O,cursos CUR "
                + "where SUB.placa=V.placa and V.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and SUB.resultado='A' and SUB.matricula=CA.matricula and CA.id_curso=O.id_curso and O.id_aut=V.id_aut "
                + "and CA.id_curso=CUR.id_curso and O.id_curso=CUR.id_curso "
                + "group by CUR.nome,CUR.id_curso "
                + "union "
                + "select CR.nome, CR.id_curso, 0 "
                + "from cursos CR,oferece O,auto_escola AE "
                + "where CR.id_curso=O.id_curso and O.id_aut=AE.id_aut and AE.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and CR.id_curso not in (select CURS.id_curso "
                + "from submetido SU, veiculos VE,conjunto_aulas COA, "
                + "oferece OFE,cursos CURS "
                + "where SU.placa=VE.placa and VE.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and SU.resultado='A' "
                + "and SU.matricula=COA.matricula and COA.id_curso=OFE.id_curso and "
                + "OFE.id_aut=VE.id_aut and COA.id_curso=CURS.id_curso and "
                + " OFE.id_curso=CURS.id_curso) "
                + "order by id_curso ASC";
        Conexao con = new Conexao();
        ResultSet rs = null;
        rs = con.executaBusca(busca1);
        try {
            while (rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("id_curso");
                int cont = rs.getInt("count");
                if (cont > 0 || cont == 0) {
                    cursoApDir.add(nome);
                    idcursoApDir.add(id);
                    countApDir.add(cont);
                }
            }
        } catch (SQLException ex) {
           // Logger.getLogger(TelaRelatorioDesempenhoCFC.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Pesquisa 3: total reprovados prova direcao por curso
        LinkedList<String> cursoRepDir = new LinkedList<>();
        LinkedList<Integer> idcursoRepDir = new LinkedList<>();
        LinkedList<Integer> countRepDir = new LinkedList<>();
        String busca3 = "select distinct CUR.nome,CUR.id_curso, count (SUB.matricula) "
                + "from submetido SUB, veiculos V,conjunto_aulas CA,oferece O,cursos CUR "
                + "where SUB.placa=V.placa and V.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and SUB.resultado='R' and SUB.matricula=CA.matricula and CA.id_curso=O.id_curso and O.id_aut=V.id_aut "
                + "and CA.id_curso=CUR.id_curso and O.id_curso=CUR.id_curso "
                + "group by CUR.nome,CUR.id_curso "
                + "union "
                + "select CR.nome, CR.id_curso, 0 "
                + "from cursos CR,oferece O,auto_escola AE "
                + "where CR.id_curso=O.id_curso and O.id_aut=AE.id_aut and AE.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and CR.id_curso not in (select CURS.id_curso "
                + "from submetido SU, veiculos VE,conjunto_aulas COA, "
                + "oferece OFE,cursos CURS "
                + "where SU.placa=VE.placa and VE.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and SU.resultado='R' "
                + "and SU.matricula=COA.matricula and COA.id_curso=OFE.id_curso and "
                + "OFE.id_aut=VE.id_aut and COA.id_curso=CURS.id_curso and "
                + " OFE.id_curso=CURS.id_curso) "
                + "order by id_curso ASC";
        con = new Conexao();
        rs = null;
        rs = con.executaBusca(busca3);
        try {
            while (rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("id_curso");
                int cont = rs.getInt("count");
                if (cont > 0 || cont == 0) {
                    cursoRepDir.add(nome);
                    idcursoRepDir.add(id);
                    countRepDir.add(cont);
                }
            }
        } catch (SQLException ex) {
           // Logger.getLogger(TelaRelatorioDesempenhoCFC.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Pesquisa 5: total de alunos que fizeram prova direcao
        LinkedList<String> cursoTotalDir = new LinkedList<>();
        LinkedList<Integer> idcursoTotalDir = new LinkedList<>();
        LinkedList<Integer> countTotalDir = new LinkedList<>();

        String busca5 = "select distinct CUR.nome,CUR.id_curso, count (SUB.matricula) "
                + "from submetido SUB, veiculos V,conjunto_aulas CA,oferece O,cursos CUR,prova_pratica PP "
                + "where SUB.placa=V.placa and V.id_aut=" + PermissaoAcesso.getIdAutoEscola() + "  and SUB.matricula=CA.matricula and CA.id_curso=O.id_curso and O.id_aut=V.id_aut "
                + "and CA.id_curso=CUR.id_curso and O.id_curso=CUR.id_curso and PP.id_pratica=SUB.id_pratica "
                + "group by CUR.nome,CUR.id_curso "
                + "union "
                + "select CR.nome, CR.id_curso, 0 "
                + "from cursos CR,oferece O,auto_escola AE "
                + "where CR.id_curso=O.id_curso and O.id_aut=AE.id_aut and AE.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and CR.id_curso not in (select CURS.id_curso "
                + "from submetido SU, veiculos VE,conjunto_aulas COA, "
                + "oferece OFE,cursos CURS, prova_pratica PRP "
                + "where SU.placa=VE.placa and VE.id_aut=" + PermissaoAcesso.getIdAutoEscola()
                + " and SU.matricula=COA.matricula and COA.id_curso=OFE.id_curso and "
                + "OFE.id_aut=VE.id_aut and COA.id_curso=CURS.id_curso and "
                + "OFE.id_curso=CURS.id_curso and PRP.id_pratica=SU.id_pratica "
                + ")"
                + "order by id_curso ASC";
        con = new Conexao();
        rs = null;
        rs = con.executaBusca(busca5);
        try {
            while (rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("id_curso");
                int cont = rs.getInt("count");
                if (cont > 0 || cont == 0) {
                    cursoTotalDir.add(nome);
                    idcursoTotalDir.add(id);
                    countTotalDir.add(cont);
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(TelaRelatorioDesempenhoCFC.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * *************************************************Análises Prova
         * Legislacao************************************************************************************************
         */
        // Pesquisa 1: total aprovados prova teórica por curso
        LinkedList<String> cursoApLeg = new LinkedList<>();
        LinkedList<Integer> idcursoApLeg = new LinkedList<>();
        LinkedList<Integer> countApLeg = new LinkedList<>();
        String busca7 = "select distinct CUR.nome,CUR.id_curso, count (AV.matricula) "
                + "from avaliado AV, prova_teorica PT, matriculado M, cursos CUR,oferece O "
                + "where AV.matricula=M.matricula and AV.id_teorica=PT.id_teorica and AV.resultado='A' and M.id_curso=CUR.id_curso and M.id_curso=O.id_curso "
                + "and O.id_aut=" + PermissaoAcesso.getIdAutoEscola()
                + " group by CUR.id_curso "
                + "union "
                + "select distinct C.nome,C.id_curso,0 "
                + "from cursos C,oferece OFE "
                + "where C.id_curso=OFE.id_curso and OFE.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and C.id_curso not in(select distinct CURS.id_curso "
                + "from avaliado AVA, prova_teorica PRT, matriculado MA, cursos CURS,oferece OFE "
                + "where AVA.matricula=MA.matricula and AVA.id_teorica=PRT.id_teorica and AVA.resultado='A' and "
                + "MA.id_curso=CURS.id_curso and MA.id_curso=OFE.id_curso) "
                + "order by id_curso";
        con = new Conexao();
        rs = null;
        rs = con.executaBusca(busca7);
        try {
            while (rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("id_curso");
                int cont = rs.getInt("count");
                if (cont > 0 || cont == 0) {
                    cursoApLeg.add(nome);
                    idcursoApLeg.add(id);
                    countApLeg.add(cont);
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(TelaRelatorioDesempenhoCFC.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Pesquisa 9: total reprovados prova teórica por curso
        LinkedList<String> cursoRepLeg = new LinkedList<>();
        LinkedList<Integer> idcursoRepLeg = new LinkedList<>();
        LinkedList<Integer> countRepLeg = new LinkedList<>();
        String busca9 = "select distinct CUR.nome,CUR.id_curso, count (AV.matricula) "
                + "from avaliado AV, prova_teorica PT, matriculado M, cursos CUR,oferece O "
                + "where AV.matricula=M.matricula and AV.id_teorica=PT.id_teorica and AV.resultado='R' and M.id_curso=CUR.id_curso and M.id_curso=O.id_curso "
                + "and O.id_aut=" + PermissaoAcesso.getIdAutoEscola()
                + " group by CUR.id_curso "
                + "union "
                + "select distinct C.nome,C.id_curso,0 "
                + "from cursos C,oferece OFE "
                + "where C.id_curso=OFE.id_curso and OFE.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and C.id_curso not in(select distinct CURS.id_curso "
                + "from avaliado AVA, prova_teorica PRT, matriculado MA, cursos CURS,oferece OFE "
                + "where AVA.matricula=MA.matricula and AVA.id_teorica=PRT.id_teorica and AVA.resultado='R' and "
                + "MA.id_curso=CURS.id_curso and MA.id_curso=OFE.id_curso) "
                + "order by id_curso";
        con = new Conexao();
        rs = null;
        rs = con.executaBusca(busca9);
        try {
            while (rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("id_curso");
                int cont = rs.getInt("count");
                if (cont > 0 || cont == 0) {
                    cursoRepLeg.add(nome);
                    idcursoRepLeg.add(id);
                    countRepLeg.add(cont);
                }
            }
        } catch (SQLException ex) {
         //   Logger.getLogger(TelaRelatorioDesempenhoCFC.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Total que fizeram prova teorica por curso
        LinkedList<String> cursoTotalLeg = new LinkedList<>();
        LinkedList<Integer> idcursoTotalLeg = new LinkedList<>();
        LinkedList<Integer> countTotalLeg = new LinkedList<>();
        String busca11 = "select distinct CUR.nome,CUR.id_curso, count (AV.matricula) "
                + "from avaliado AV, prova_teorica PT, matriculado M, cursos CUR,oferece O "
                + "where AV.matricula=M.matricula and AV.id_teorica=PT.id_teorica and M.id_curso=CUR.id_curso and M.id_curso=O.id_curso "
                + "and O.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " "
                + "group by CUR.id_curso "
                + "union "
                + "select distinct C.nome,C.id_curso,0 "
                + "from cursos C,oferece OFE "
                + "where C.id_curso=OFE.id_curso and OFE.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and C.id_curso not in(select distinct CURS.id_curso "
                + " from avaliado AVA, prova_teorica PRT, matriculado MA, cursos CURS,oferece OFE "
                + " where AVA.matricula=MA.matricula and AVA.id_teorica=PRT.id_teorica and "
                + " MA.id_curso=CURS.id_curso and MA.id_curso=OFE.id_curso) "
                + "order by id_curso";
        con = new Conexao();
        rs = null;
        rs = con.executaBusca(busca11);
        try {
            while (rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("id_curso");
                int cont = rs.getInt("count");
                if (cont > 0 || cont == 0) {
                    cursoTotalLeg.add(nome);
                    idcursoTotalLeg.add(id);
                    countTotalLeg.add(cont);
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(TelaRelatorioDesempenhoCFC.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Média notas por curso
        String busca13 = "select distinct id_curso,nome, avg (nota) as media "
                + "from (select AV.nota,C.nome,C.id_curso "
                + "	from avaliado AV, prova_teorica PT, matriculado MT, oferece OFE,cursos C "
                + "	where AV.matricula=MT.matricula and MT.id_curso=OFE.id_curso and OFE.id_curso=C.id_curso and OFE.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and "
                + "	PT.id_teorica=AV.id_teorica "
                + "	union "
                + "	select distinct 0,CR.nome,CR.id_curso "
                + "	from cursos CR,oferece OFE "
                + "	where CR.id_curso=OFE.id_curso and OFE.id_aut =" + PermissaoAcesso.getIdAutoEscola() + " and CR.id_curso not in (select distinct CUR.id_curso "
                + "	from avaliado AVA, prova_teorica PRT, matriculado MAT, oferece OFER,cursos CUR "
                + "	where AVA.matricula=MAT.matricula and MAT.id_curso=OFER.id_curso and CUR.id_curso=OFER.id_curso and OFER.id_aut=" + PermissaoAcesso.getIdAutoEscola() + " and "
                + "	PRT.id_teorica=AVA.id_teorica))AS resultado(nota,nome,id_curso) "
                + "	group by nome,id_curso "
                + "order by id_curso asc ";
        PreparedStatement ps = null;
        rs = null;
        con = new Conexao();
        try {
            ps = con.getConexao().prepareStatement(busca13);
            rs = ps.executeQuery();
            jTableDesempenhoDirecao.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(TelaDadosCFC.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * **************************************analise
         * relatorio*****************************************************************
         */
        DefaultTableModel dtmDesempenhoDirecao6 = (DefaultTableModel) jTableDesempenhoDirecao6.getModel();

        // legislacao sem periodo pre-estabelecido
        for (int i = 0; i < countApDir.size(); i++) {
            String nome1 = cursoApDir.get(i);
            //total direcao
            double total1 = 0;
            double totalAprov1 = 0;
            double totalReprov1 = 0;
            try {
                total1 = countTotalDir.get(i);
                System.out.println(total1);
                totalAprov1 = countApDir.get(i);
                System.out.println(totalAprov1);
                totalReprov1 = countRepDir.get(i);
                System.out.println(totalReprov1);
                //porcentagem total legislacao
                try {
                    if (totalAprov1 > 0 && total1 > 0) {
                        totalAprov1 = (totalAprov1 / total1) * 100;
                    }

                } catch (Exception ex) {
                    totalAprov1 = 0;
                }
                try {
                    if (totalReprov1 > 0 && total1 > 0) {
                        totalReprov1 = (totalReprov1 / total1) * 100;
                    }
                } catch (Exception ex) {
                    totalReprov1 = 0;
                }
                try {
                    if (total1 > 0) {
                        total1 = (total1 / total1) * 100;
                    }
                } catch (Exception ex) {
                    total1 = 0;
                }
                Object[] dados1 = {nome1, totalAprov1 + "%", totalReprov1 + "%", total1 + "%"};//linha
                dtmDesempenhoDirecao6.addRow(dados1);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao capturar os dados, por favor tente mais tarde!");
                dispose();
            }
        }

        DefaultTableModel dtmDesempenhoLegislacao6 = (DefaultTableModel) jTableLegislacao6.getModel();

        // legislacao sem periodo pre-estabelecido
        for (int i = 0; i < countApLeg.size(); i++) {
            String nome1 = cursoApLeg.get(i);
            //total direcao
            double total1 = 0;
            double totalAprov1 = 0;
            double totalReprov1 = 0;
            try {
                total1 = countTotalLeg.get(i);
                System.out.println(total1);
                totalAprov1 = countApLeg.get(i);
                System.out.println(totalAprov1);
                totalReprov1 = countRepLeg.get(i);
                System.out.println(totalReprov1);
                //porcentagem total legislacao
                try {
                    if (totalAprov1 > 0 && total1 > 0) {
                        totalAprov1 = (totalAprov1 / total1) * 100;
                    }

                } catch (Exception ex) {
                    totalAprov1 = 0;
                }
                try {
                    if (totalReprov1 > 0 && total1 > 0) {
                        totalReprov1 = (totalReprov1 / total1) * 100;
                    }
                } catch (Exception ex) {
                    totalReprov1 = 0;
                }
                try {
                    if (total1 > 0) {
                        total1 = (total1 / total1) * 100;
                    }
                } catch (Exception ex) {
                    total1 = 0;
                }
                Object[] dados1 = {nome1, totalAprov1 + "%", totalReprov1 + "%", total1 + "%"};//linha
                dtmDesempenhoLegislacao6.addRow(dados1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao capturar os dados, por favor tente mais tarde!");
                dispose();
            }
        }

        /**
         * *************************************************************************cabecalhos
         * e
         * rodapes************************************************************************************
         */
        //endereco
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

        //nome,endereco e telefones
        jLabelNomeCFCAnaliseGeral.setText(end);
        jLabelTelefones4.setText(tel);
        jLabelendereco4.setText(end);
        jLabelNomeCFCAnaliseGeral.setText(PermissaoAcesso.getAutoEscola());
        //jLabelNomeCFC2.setText(PermissaoAcesso.getAutoEscola());
        //jLabelTelefones2.setText(tel);
        //jLabelendereco2.setText(end);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanelImprimir1 = new javax.swing.JPanel();
        jScrollPaneAnalisePeriodo1 = new javax.swing.JScrollPane();
        jPanelImprimirRel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelTelefones4 = new javax.swing.JLabel();
        jLabelNomeCFCAnaliseGeral = new javax.swing.JLabel();
        jLabelLegislacao4 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTableLegislacao6 = new javax.swing.JTable();
        jLabelPratica6 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTableDesempenhoDirecao6 = new javax.swing.JTable();
        jLabelPratica7 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTableDesempenhoDirecao = new javax.swing.JTable();
        jLabelendereco4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(1219, 436));

        jScrollPaneAnalisePeriodo1.setPreferredSize(new java.awt.Dimension(597, 826));

        jPanelImprimirRel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelImprimirRel1.setMaximumSize(new java.awt.Dimension(595, 824));
        jPanelImprimirRel1.setMinimumSize(new java.awt.Dimension(595, 824));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Desempenho alunos por curso");

        jLabelTelefones4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTelefones4.setText("Telefone");

        jLabelNomeCFCAnaliseGeral.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNomeCFCAnaliseGeral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNomeCFCAnaliseGeral.setText("Nome CFC");

        jLabelLegislacao4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLegislacao4.setText("Porcentagem total de alunos aprovados e reprovados no Exame de Legislação");

        jTableLegislacao6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CFC", "Aprovados", "Reprovados", "Realizaram o teste"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane14.setViewportView(jTableLegislacao6);

        jLabelPratica6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPratica6.setText("Porcentagem total de alunos aprovados e reprovados no Exame de Direção");

        jTableDesempenhoDirecao6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CFC", "Aprovados", "Reprovados", "Realizaram o teste"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane15.setViewportView(jTableDesempenhoDirecao6);

        jLabelPratica7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPratica7.setText(" Notas Médias dos alunos");

        jTableDesempenhoDirecao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane16.setViewportView(jTableDesempenhoDirecao);

        jLabelendereco4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelendereco4.setText("Endereço");

        jLabel5.setText("Observação: Nota mínima para aprovação: 21,0 (70%)");

        javax.swing.GroupLayout jPanelImprimirRel1Layout = new javax.swing.GroupLayout(jPanelImprimirRel1);
        jPanelImprimirRel1.setLayout(jPanelImprimirRel1Layout);
        jPanelImprimirRel1Layout.setHorizontalGroup(
            jPanelImprimirRel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelTelefones4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelendereco4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelImprimirRel1Layout.createSequentialGroup()
                .addGroup(jPanelImprimirRel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelImprimirRel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelNomeCFCAnaliseGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelImprimirRel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanelImprimirRel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelLegislacao4, javax.swing.GroupLayout.PREFERRED_SIZE, 975, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelImprimirRel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 105, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelImprimirRel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPratica6, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(jPanelImprimirRel1Layout.createSequentialGroup()
                .addGroup(jPanelImprimirRel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelImprimirRel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabelPratica7, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelImprimirRel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelImprimirRel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelImprimirRel1Layout.setVerticalGroup(
            jPanelImprimirRel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImprimirRel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabelNomeCFCAnaliseGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTelefones4)
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addGap(44, 44, 44)
                .addComponent(jLabelLegislacao4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPratica6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabelPratica7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel5)
                .addGap(3, 3, 3)
                .addComponent(jLabelendereco4)
                .addContainerGap())
        );

        jScrollPaneAnalisePeriodo1.setViewportView(jPanelImprimirRel1);

        javax.swing.GroupLayout jPanelImprimir1Layout = new javax.swing.GroupLayout(jPanelImprimir1);
        jPanelImprimir1.setLayout(jPanelImprimir1Layout);
        jPanelImprimir1Layout.setHorizontalGroup(
            jPanelImprimir1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelImprimir1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPaneAnalisePeriodo1, javax.swing.GroupLayout.PREFERRED_SIZE, 1147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanelImprimir1Layout.setVerticalGroup(
            jPanelImprimir1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneAnalisePeriodo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImprimir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelImprimir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane2.addTab("Análise Geral", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelLegislacao4;
    private javax.swing.JLabel jLabelNomeCFCAnaliseGeral;
    private javax.swing.JLabel jLabelPratica6;
    private javax.swing.JLabel jLabelPratica7;
    private javax.swing.JLabel jLabelTelefones4;
    private javax.swing.JLabel jLabelendereco4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelImprimir1;
    private javax.swing.JPanel jPanelImprimirRel1;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPaneAnalisePeriodo1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableDesempenhoDirecao;
    private javax.swing.JTable jTableDesempenhoDirecao6;
    private javax.swing.JTable jTableLegislacao6;
    // End of variables declaration//GEN-END:variables
}
