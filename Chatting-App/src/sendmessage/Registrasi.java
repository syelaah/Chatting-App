package sendmessage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author A S U S
 */
public class Registrasi extends javax.swing.JFrame {
    String error;
    String Username;
    /**
     * Creates new form registrasi
     */
    public Registrasi() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        email_label = new javax.swing.JLabel();
        nama_label = new javax.swing.JLabel();
        user_label = new javax.swing.JLabel();
        pass_label = new javax.swing.JLabel();
        jk_label = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        cmbJkl = new javax.swing.JComboBox<>();
        btnRegistrasi = new javax.swing.JButton();
        umur_label = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtUmur = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        jpfPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrasi");
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel1.setText("REGISTRASI PENGGUNA");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 20, 260, 27);

        email_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        email_label.setText("Email");
        getContentPane().add(email_label);
        email_label.setBounds(30, 90, 31, 17);

        nama_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nama_label.setText("Nama");
        getContentPane().add(nama_label);
        nama_label.setBounds(30, 130, 35, 17);

        user_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user_label.setText("Username");
        getContentPane().add(user_label);
        user_label.setBounds(20, 250, 61, 17);

        pass_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pass_label.setText("Password");
        getContentPane().add(pass_label);
        pass_label.setBounds(20, 290, 70, 17);

        jk_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jk_label.setText("Jenis Kelamin");
        getContentPane().add(jk_label);
        jk_label.setBounds(10, 210, 79, 17);
        getContentPane().add(txtEmail);
        txtEmail.setBounds(100, 90, 220, 30);

        cmbJkl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbJkl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perempuan", "Laki-Laki" }));
        getContentPane().add(cmbJkl);
        cmbJkl.setBounds(100, 210, 220, 30);

        btnRegistrasi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRegistrasi.setText("Registrasi");
        btnRegistrasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrasiActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrasi);
        btnRegistrasi.setBounds(210, 370, 99, 32);

        umur_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        umur_label.setText("Umur");
        getContentPane().add(umur_label);
        umur_label.setBounds(30, 170, 70, 17);
        getContentPane().add(txtNama);
        txtNama.setBounds(100, 130, 220, 30);
        getContentPane().add(txtUmur);
        txtUmur.setBounds(100, 170, 220, 30);
        getContentPane().add(txtUsername);
        txtUsername.setBounds(100, 250, 220, 30);
        getContentPane().add(jpfPassword);
        jpfPassword.setBounds(100, 290, 220, 30);

        setSize(new java.awt.Dimension(373, 476));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrasiActionPerformed
        // TODO add your handling code here:
        String Email = txtEmail.getText();
        String Nama = txtNama.getText();
        Object Jkl = cmbJkl.getSelectedItem();
        String Username = txtUsername.getText();
        String Password = jpfPassword.getText();
        int umur = Integer.parseInt(txtUmur.getText());
        this.Username = Username;
        String Cekuser = null;
        
        try {
            Connection connect = Koneksi.getConnection();
            try (Statement stat = connect.createStatement()) {
                String sql = "SELECT * FROM user WHERE username = '"+txtUsername+"'";
                try (ResultSet rs = stat.executeQuery (sql)) {
                    while (rs.next()){
                        Cekuser = rs.getString("username");
                    }
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Terjadi error "+e.getMessage());
            this.error = e.getMessage();
            exception();
        }
        if (Cekuser != null){
            String usn = "username yang anda masukkan sudah terdaftar";
            JOptionPane.showMessageDialog(null,usn,"ERROR",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            try {
                Connection connect = Koneksi.getConnection();
                String query = "INSERT INTO user (username, password, email, nama, umur, jkl) VALUES (?,md5(?),?,?,?,?)";
                try (PreparedStatement pstat = connect.prepareStatement(query)) {
                    pstat.setString(1,Username);
                    pstat.setString(2, Password);
                    pstat.setString(3, Email);
                    pstat.setString(4, Nama);
                    pstat.setInt(5, umur);
                    pstat.setString(6, (String) Jkl);
                    pstat.executeUpdate();
                    
                    ArrayListUser alu = new ArrayListUser();
                    alu.array();
    }
                String msg = "ANDA BERHASIL MENDAFTAR";
                JOptionPane.showMessageDialog(null,msg,"BERHASIL",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
                Login login = new Login();
                login.setVisible(true);
            } catch (SQLException e){
                JOptionPane.showMessageDialog(this, "Tidak berhasil mendaftar"+e.getMessage());
                this.error = e.getMessage();
                exception();
            }
        }
    }//GEN-LAST:event_btnRegistrasiActionPerformed

    public void exception(){
        try {
            final String PATH = "src/sendmessage/";
            
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(PATH + "/error.log", true)
            );
        
            String result = error + "\n";
            
            out.write(result);
            
            out.close();
        } catch (Exception e){
            System.out.println("Error : " + e.getMessage());
            this.error = e.getMessage();
            exception();
        }
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
            java.util.logging.Logger.getLogger(Registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrasi;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbJkl;
    private javax.swing.JLabel email_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jk_label;
    private javax.swing.JPasswordField jpfPassword;
    private javax.swing.JLabel nama_label;
    private javax.swing.JLabel pass_label;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtUmur;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JLabel umur_label;
    private javax.swing.JLabel user_label;
    // End of variables declaration//GEN-END:variables

}
