/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poo.pl2;

import javax.swing.JOptionPane;

/**
 *
 * @author tizia
 */
public class GUI_inicioSesion extends javax.swing.JFrame {

    /**
     * Creates new form InicioSesion
     */
    public GUI_inicioSesion() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        correoField = new javax.swing.JTextField();
        claveField = new javax.swing.JPasswordField();
        inicioSesionButton = new javax.swing.JButton();
        registroButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaBnB");

        correoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoFieldActionPerformed(evt);
            }
        });

        claveField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveFieldActionPerformed(evt);
            }
        });

        inicioSesionButton.setText("Iniciar Sesion");
        inicioSesionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioSesionButtonActionPerformed(evt);
            }
        });

        registroButton.setText("Registrarme");
        registroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registroButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Correo");

        jLabel2.setText("Clave");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Iniciar sesion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(claveField)
                        .addComponent(inicioSesionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registroButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(correoField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(correoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(claveField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(inicioSesionButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registroButton)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicioSesionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesionButtonActionPerformed
        System.out.println("La clave "+new String(claveField.getPassword())+" tiene un hashcode de "+new String(claveField.getPassword()).hashCode());
        try{
            Usuario usu=Usuario.iniciarSesion(correoField.getText(), new String(claveField.getPassword()).hashCode());
            if (usu instanceof Cliente)
                JOptionPane.showMessageDialog(this, "Bienvenido, "+((Cliente) usu).getNombre(), "Inicio de sesion correcto", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(this, "Bienvenido, administrador "+usu.getCorreo(), "Inicio de sesion correcto", JOptionPane.INFORMATION_MESSAGE);
            
            switch(usu.getClass().getSimpleName()){
                case "Particular":
                    GUI_mainParticular particular = new GUI_mainParticular((Particular)usu);
                    particular.setVisible(true);
                    break;
                case "Anfitrion":
                    GUI_mainAnfitrion anfitrion = new GUI_mainAnfitrion((Anfitrion)usu);
                    anfitrion.setVisible(true);
                    break;
                case "Administrador":
                    GUI_mainAdmin admin = new GUI_mainAdmin((Administrador)usu);
                    admin.setVisible(true);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de usuario no reconocido");
            }
            this.dispose();
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_inicioSesionButtonActionPerformed

    private void registroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroButtonActionPerformed
        GUI_dialogRegistro registro = new GUI_dialogRegistro(this,true);
        registro.setVisible(true);
    }//GEN-LAST:event_registroButtonActionPerformed

    private void claveFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claveFieldActionPerformed
        inicioSesionButtonActionPerformed(evt);
    }//GEN-LAST:event_claveFieldActionPerformed

    private void correoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoFieldActionPerformed
        claveField.requestFocus();
    }//GEN-LAST:event_correoFieldActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_inicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField claveField;
    private javax.swing.JTextField correoField;
    private javax.swing.JButton inicioSesionButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton registroButton;
    // End of variables declaration//GEN-END:variables
}
