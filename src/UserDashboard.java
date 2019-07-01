import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
// import jtable
import javax.swing.table.*;

public class UserDashboard extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement PStatement = null;
    ResultSet rs = null;
    // for delete
    Statement stmt = null;
    
    public UserDashboard() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btn_view = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employee CRUD using MySql");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address", "Contact", "Age", "Tenureship"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtable);
        if (jtable.getColumnModel().getColumnCount() > 0) {
            jtable.getColumnModel().getColumn(0).setMinWidth(30);
            jtable.getColumnModel().getColumn(0).setPreferredWidth(30);
            jtable.getColumnModel().getColumn(0).setMaxWidth(30);
            jtable.getColumnModel().getColumn(4).setMinWidth(60);
            jtable.getColumnModel().getColumn(4).setPreferredWidth(60);
            jtable.getColumnModel().getColumn(4).setMaxWidth(60);
        }

        btn_view.setText("View Employee Details");
        btn_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_view, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(373, 373, 373))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_view, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText(" Employee Page Dashboard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(50, 50, 50))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(235, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       conn = DBConnect.DBConnect();
          String sql = "SELECT * FROM emp";
       try {
           
           PStatement = conn.prepareStatement(sql);
           rs = PStatement.executeQuery();
           // Define jtable
           DefaultTableModel model = (DefaultTableModel) jtable.getModel();
           
           while(rs.next()){
               // set value to jtable
               String empid = rs.getString("empid");
               String fname = rs.getString("fname");
               String mname = rs.getString("mname");
               String lname = rs.getString("lname");
               Date bdate = rs.getDate("bdate");
               Date datehired = rs.getDate("datehired");
               String contactinfo = rs.getString("contactinfo");
               String address1 = rs.getString("address1");
               String address2 = rs.getString("address2");
               Period tenureship = getTenureship(datehired);
               Object[] row = { empid, fname + " " + mname + " " + lname, 
                   address1 + ", " + address2, 
                   contactinfo, 
                   getAge(bdate),
                   tenureship.getYears() + " year(s) and " + tenureship.getMonths() + " month(s)" };
               // Insert value to jtable row and column
               model.addRow(row);
           }
           
       } catch(Exception ex) {
           System.out.println("Error: "+ex);
       }
    }//GEN-LAST:event_formWindowOpened

    private int getAge(Date bdate) {
        Calendar c = Calendar.getInstance();
        c.setTime(bdate);
        
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        
        LocalDate dob = LocalDate.of(year, month, date);
        LocalDate today = LocalDate.now();
        Period diff = Period.between(dob, today);
        
        return diff.getYears();
    }
    
    private Period getTenureship(Date tenureship) {
        Calendar c = Calendar.getInstance();
        c.setTime(tenureship);
        
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        
        LocalDate dob = LocalDate.of(year, month, date);
        LocalDate today = LocalDate.now();
        Period diff = Period.between(dob, today);
        
        return diff;
    }
    
    private void btn_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewActionPerformed
        // Get ID from jtable
        int row = jtable.getSelectedRow();
        // .getValueAt(row, column) so we are going to get the row and the ID column w/c is 0
        String empid = jtable.getModel().getValueAt(row, 0).toString();
        
        view v = new view();
        // set view public id
        v.empid = empid;
        // open view form and close dashboard
        v.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_viewActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_view;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtable;
    // End of variables declaration//GEN-END:variables
}
