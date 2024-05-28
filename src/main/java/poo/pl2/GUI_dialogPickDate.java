/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package poo.pl2;

import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.toedter.calendar.IDateEvaluator;

/**
 *
 * @author tizia
 */
public class GUI_dialogPickDate extends javax.swing.JDialog {
    private LocalDate fecha;
    private boolean fechaSeleccionada=false;
    private ArrayList<LocalDate> fechasOcupadas;
    public boolean isFechaSeleccionada() {
        return fechaSeleccionada;
    }
    /**
     * Creates new form GUI_dialogPickDate
     */
    public GUI_dialogPickDate(java.awt.Frame parent, boolean modal, ArrayList<LocalDate> fechasOcupadas) {
        super(parent, modal);
        this.fechasOcupadas=fechasOcupadas;
        initComponents();
        initSpinnerListeners();
        pintarFechas();
    }
    public GUI_dialogPickDate(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initSpinnerListeners();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calendar = new com.toedter.calendar.JCalendar();

        aceptarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        calendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendarPropertyChange(evt);
            }
        });

        aceptarButton.setText("Aceptar");
        aceptarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(aceptarButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptarButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
        fechaSeleccionada=true;
        this.dispose();
    }//GEN-LAST:event_aceptarButtonActionPerformed

    private void calendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarPropertyChange
        if (this.calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now())){
            this.calendar.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            return;
        }
        fecha = this.calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }//GEN-LAST:event_calendarPropertyChange

    public LocalDate getFecha(){
        return fecha;
    }
        private static class HighlightEvaluator implements IDateEvaluator {

        private final List<Date> list = new ArrayList<>();

        public void add(Date date) {
            list.add(date);
        }

        @Override
        public boolean isSpecial(Date date) {
            return list.contains(date);
        }

        @Override
        public Color getSpecialForegroundColor() {
            return Color.black;
        }

        @Override
        public Color getSpecialBackroundColor() {
            return Color.red;
        }

        @Override
        public String getSpecialTooltip() {
            return "Highlighted event.";
        }

        @Override
        public boolean isInvalid(Date date) {
            return false;
        }

        @Override
        public Color getInvalidForegroundColor() {
            return null;
        }

        @Override
        public Color getInvalidBackroundColor() {
            return null;
        }

        @Override
        public String getInvalidTooltip() {
            return null;
        }
    }
    private Date createDate(int d) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, calendar.getYearChooser().getYear());
        c.set(Calendar.MONTH, calendar.getMonthChooser().getMonth());
        c.set(Calendar.DAY_OF_MONTH, d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return (c.getTime());
    }
    public void initSpinnerListeners(){
        calendar.getMonthChooser().addPropertyChangeListener(evt -> {
            pintarFechas();
        });
        calendar.getYearChooser().addPropertyChangeListener(evt -> {
            pintarFechas();
        });
    }
    public void pintarFechas(ArrayList<LocalDate> fechasOcupadas){
        
    }
    public void pintarFechas(){
        HighlightEvaluator resaltadorDias=new HighlightEvaluator();
        if (calendar.getYearChooser().getYear()<LocalDate.now().getYear() || (calendar.getYearChooser().getYear()==LocalDate.now().getYear() && calendar.getMonthChooser().getMonth()<LocalDate.now().getMonthValue()-1)){
            for (int i=1;i<=calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().lengthOfMonth();i++){
                resaltadorDias.add(createDate(i));
            }
            calendar.getDayChooser().addDateEvaluator(resaltadorDias);
            calendar.setCalendar(calendar.getCalendar());
            return;
        }
        if (calendar.getYearChooser().getYear()==LocalDate.now().getYear() && calendar.getMonthChooser().getMonth()==LocalDate.now().getMonthValue()-1){
            for (int i=1;i<=ChronoUnit.DAYS.between(LocalDate.now().withDayOfMonth(1),LocalDate.now());i++){
                resaltadorDias.add(createDate(i));
            }
        }
        int indice=0;
        while (indice<fechasOcupadas.size()){
            if (fechasOcupadas.get(indice).isBefore(LocalDate.now()) || fechasOcupadas.get(indice).getMonthValue()!=calendar.getMonthChooser().getMonth()+1 || fechasOcupadas.get(indice).getYear()!=calendar.getYearChooser().getYear()){
                indice+=2;
            }
            else{
                for (int i=fechasOcupadas.get(indice).getDayOfMonth();i<fechasOcupadas.get(indice+1).getDayOfMonth();i++){
                    resaltadorDias.add(createDate(i));
                }
                indice+=2;
            }
        }
        calendar.getDayChooser().addDateEvaluator(resaltadorDias);
        calendar.setCalendar(calendar.getCalendar());
    }

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
            java.util.logging.Logger.getLogger(GUI_dialogPickDate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_dialogPickDate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_dialogPickDate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_dialogPickDate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_dialogPickDate dialog = new GUI_dialogPickDate(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private com.toedter.calendar.JCalendar calendar;
    // End of variables declaration//GEN-END:variables
}
