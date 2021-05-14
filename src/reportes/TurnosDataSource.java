/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import dominio.Turn;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author angel
 */
public class TurnosDataSource implements JRDataSource {
    
    private List<Turn> listaTurnos = new ArrayList<Turn>();
    private int indiceParticipanteActual = -1;

    @Override
    public boolean next() throws JRException {
        return ++indiceParticipanteActual < listaTurnos.size();
    }
    
    public void addTurno(Turn turno) {
        this.listaTurnos.add(turno);
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        
        if ("tblEmployee_noEmployee".equals(jrf.getName())) {
            valor = listaTurnos.get(indiceParticipanteActual).getIdEmployee() != null ? listaTurnos.get(indiceParticipanteActual).getIdEmployee().getNoEmployee() : "-";
        } else if ("tblTurn_dateTimeAssigned".equals(jrf.getName())) {
            valor = listaTurnos.get(indiceParticipanteActual).getDateTimeAssigned() != null ? new Timestamp(listaTurnos.get(indiceParticipanteActual).getDateTimeAssigned().getTime()) : null;
        } else if ("tblTurn_dateTimeCreated".equals(jrf.getName())) {
            valor = listaTurnos.get(indiceParticipanteActual).getDateTimeCreated() != null ? new Timestamp(listaTurnos.get(indiceParticipanteActual).getDateTimeCreated().getTime()) : null;
        } else if ("tblTurn_dateTimeFinished".equals(jrf.getName())) {
            valor = listaTurnos.get(indiceParticipanteActual).getDateTimeFinished() != null ? new Timestamp(listaTurnos.get(indiceParticipanteActual).getDateTimeFinished().getTime()) : null;
        } else if ("tblTurn_status".equals(jrf.getName())) {
            valor = listaTurnos.get(indiceParticipanteActual).getStatus() != null ? listaTurnos.get(indiceParticipanteActual).getStatus() : "-";
        } else if ("tblTurn_turnNumber".equals(jrf.getName())) {
            valor = listaTurnos.get(indiceParticipanteActual).getTurnNumber() != null ? listaTurnos.get(indiceParticipanteActual).getTurnNumber() : "-";
        } else if ("tblTurn_type".equals(jrf.getName())) {
            valor = listaTurnos.get(indiceParticipanteActual).getType() != null ? listaTurnos.get(indiceParticipanteActual).getType().name() : "-";
        } else if ("tblBranch_branchName".equals(jrf.getName())) {
            valor = listaTurnos.get(indiceParticipanteActual).getIdEmployee() != null ? listaTurnos.get(indiceParticipanteActual).getIdEmployee().getIdBranch().getBranchName() : "-";
        } else if ("tblAttentionPoint_point".equals(jrf.getName())) {
            valor = listaTurnos.get(indiceParticipanteActual).getIdEmployee() != null ? listaTurnos.get(indiceParticipanteActual).getIdEmployee().getIdAttentionPoint().getPoint() : "-";
        } 
        
        return valor;
    }
    
}
