package controller;
import model.*;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


public class controlerCRUD implements ActionListener {
    frmcliente viewCRUD = new frmcliente();
    clientedao modelCRUD = new clientedao();
    
    public controlerCRUD(frmcliente viewCRUD, clientedao modelCRUD){
        this.modelCRUD = modelCRUD;
        this.viewCRUD = viewCRUD;
        this.viewCRUD.btnBuscar.addActionListener(this);
        this.viewCRUD.btnListar.addActionListener(this);
        this.viewCRUD.btnModificar.addActionListener(this);
        this.viewCRUD.btnExcluir.addActionListener(this);
        this.viewCRUD.btnSalvar.addActionListener(this);
        this.viewCRUD.btnListar.addActionListener(this);
        this.viewCRUD.btnAtualizar.addActionListener(this);
    }
    
    public void inicializarCRUD(){
    }
    
    public void createTable(JTable tableD){
        DefaultTableModel modelT = new DefaultTableModel();
        tableD.setModel(modelT);
        
        modelT.addColumn("cpf");
        modelT.addColumn("nome");
        modelT.addColumn("rua");
        modelT.addColumn("nr_rua");
        modelT.addColumn("dt_nasc");
        modelT.addColumn("renda");
        
        Object[] coluna = new Object[6];
        
        int numRegistros = modelCRUD.listacliente().size();
        
        for (int i = 0; i < numRegistros; i++){
            coluna[0] = modelCRUD.listacliente().get(i).getCpf();
            coluna[1] = modelCRUD.listacliente().get(i).getNome();
            coluna[2] = modelCRUD.listacliente().get(i).getRua();
            coluna[3] = modelCRUD.listacliente().get(i).getNr_rua();
            coluna[4] = modelCRUD.listacliente().get(i).getDt_nasc();
            coluna[5] = modelCRUD.listacliente().get(i).getRenda();
            modelT.addRow(coluna);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == viewCRUD.btnSalvar){
            String cpf = viewCRUD.txtCpf.getText();
            String nome = viewCRUD.txtNome.getText();
            String rua = viewCRUD.txtRua.getText();
            Integer nr_rua = Integer.parseInt(viewCRUD.txtNr_rua.getText());
            String dt_nasc = viewCRUD.txtDt_nasc.getText();
            Double renda = Double.parseDouble(viewCRUD.txtRenda.getText());
            
            String rptaRegistro = modelCRUD.insertcliente(cpf, nome, rua, nr_rua, dt_nasc, renda);
            
            if (rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro Com problema");
            }
        }
        
        if (e.getSource() == viewCRUD.btnListar){
            createTable(viewCRUD.jtDados);
        }
        
        if (e.getSource() == viewCRUD.btnLimpar){
            viewCRUD.txtCpf.setText("");
            viewCRUD.txtNome.setText("");
            viewCRUD.txtRua.setText("");
            viewCRUD.txtNr_rua.setText("");
            viewCRUD.txtRenda.setText("");
            viewCRUD.txtDt_nasc.setText("");
        }
        
        
        if (e.getSource() == viewCRUD.btnExcluir){
            int regInicio = viewCRUD.jtDados.getSelectedRow();
            int numReg = viewCRUD.jtDados.getSelectedRowCount();
            
            ArrayList<String> listaCpf = new ArrayList();
            String cpf = "";
            if (regInicio>=0){
                for (int i = 0; i < numReg; i++){
                    cpf = String.valueOf(viewCRUD.jtDados.getValueAt(i+regInicio, 0));
                    listaCpf.add(cpf);
                }
                for (int i = 0; i < numReg; i++){
                    int rptaUser = JOptionPane.showConfirmDialog(null, "Eliminar o registro:" + listaCpf.get(i) + "?");
                    if (rptaUser == 0){
                        modelCRUD.excluircliente(listaCpf.get(i));
                    }
                }
                createTable(viewCRUD.jtDados);
            }
            else{
                JOptionPane.showMessageDialog(null, "Selecione um registro.");
            }
        }
        
        if (e.getSource() == viewCRUD.btnBuscar){
            String cpf = viewCRUD.txtCpf.getText();
            DefaultTableModel modelT = new DefaultTableModel();
            viewCRUD.jtDados.setModel(modelT);
            
            modelT.addColumn("Cpf");
            modelT.addColumn("Nome");
            modelT.addColumn("Rua");
            modelT.addColumn("nr_rua");
            modelT.addColumn("dt_nasc");
            modelT.addColumn("Renda");
            
            Object[] coluna = new Object[6];
            int numRegistros = modelCRUD.searchcliente(cpf).size();
            
            for (int i = 0; i < numRegistros; i++){
                coluna[0] = modelCRUD.searchcliente(cpf).get(i).getCpf();
                coluna[1] = modelCRUD.searchcliente(cpf).get(i).getNome();
                coluna[2] = modelCRUD.searchcliente(cpf).get(i).getRua();
                coluna[3] = modelCRUD.searchcliente(cpf).get(i).getNr_rua();
                coluna[4] = modelCRUD.searchcliente(cpf).get(i).getDt_nasc();
                coluna[5] = modelCRUD.searchcliente(cpf).get(i).getRenda();
                modelT.addRow(coluna);
            }
        }
        
        if (e.getSource() == viewCRUD.btnModificar){
            int regUpdate = viewCRUD.jtDados.getSelectedRow();
            int numReg = viewCRUD.jtDados.getSelectedRowCount();
            
            if (regUpdate >= 0 && numReg == 1){
                viewCRUD.txtCpf.setText(String.valueOf(viewCRUD.jtDados.getValueAt(regUpdate, 0)));
                viewCRUD.txtCpf.setEditable(false);
                viewCRUD.txtNome.setText(String.valueOf(viewCRUD.jtDados.getValueAt(regUpdate, 1)));
                viewCRUD.txtRua.setText(String.valueOf(viewCRUD.jtDados.getValueAt(regUpdate, 2)));
                viewCRUD.txtNr_rua.setText(String.valueOf(viewCRUD.jtDados.getValueAt(regUpdate, 3)));
                viewCRUD.txtDt_nasc.setText(String.valueOf(viewCRUD.jtDados.getValueAt(regUpdate, 4)));
                viewCRUD.txtRenda.setText(String.valueOf(viewCRUD.jtDados.getValueAt(regUpdate, 5)));
                viewCRUD.btnExcluir.setEnabled(false);
                viewCRUD.btnLimpar.setEnabled(false);
                viewCRUD.btnListar.setEnabled(false);
                viewCRUD.btnBuscar.setEnabled(false);
                viewCRUD.btnSalvar.setEnabled(false);
            }
            else{
                JOptionPane.showMessageDialog(null, "Selecione um registro.");
            }
        }
        
        if (e.getSource() == viewCRUD.btnAtualizar){
            String cpf = viewCRUD.txtCpf.getText();
            String nome = viewCRUD.txtNome.getText();
            String rua = viewCRUD.txtRua.getText();
            Integer nr_rua = Integer.valueOf(viewCRUD.txtNr_rua.getText());
            String dt_nasc = viewCRUD.txtDt_nasc.getText();
            Double renda = Double.valueOf(viewCRUD.txtRenda.getText());
            
            int rptaModificado = modelCRUD.updatecliente(cpf, nome, rua, nr_rua, dt_nasc, renda);
            if (rptaModificado>0){
                JOptionPane.showMessageDialog(null, "Registro modificado com sucesso.");
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro não pode ser modificado.");
            }
        }
    }
}
