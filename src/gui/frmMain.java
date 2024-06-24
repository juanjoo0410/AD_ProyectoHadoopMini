package gui;

import framework.Task;
import tasks.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanJoo
 */
public class frmMain extends javax.swing.JFrame {

    private Map<String, String[]> files;
    private final String SELECT_PROMPT = "Seleccione...";
    private Task tarea;

    /**
     * Creates new form frmMain
     */
    public frmMain() {
        initComponents();
        listTask();
        fillComboBoxes();
    }

    private void listTask() {
        files = new HashMap<>();
        files.put("weblog.txt", new String[]{
            "Codigo_404",
            "Extensión_GIF",
            "Accesos_Por_Horas"});
        files.put("JCMB_last31days.csv", new String[]{
            "Temperatura_Diferente_WindChill",
            "Precipitaciones_Mayor_A_Cero",
            "Temperatura_En_Superficie"});
        files.put("happiness.txt", new String[]{"Palabras_Extremadamente_Tristes"});
    }

    private void fillComboBoxes() {
        // Limpiar y llenar cmbInputFiles
        cmbInputFiles.removeAllItems();
        cmbInputFiles.addItem(SELECT_PROMPT);
        for (String fileName : files.keySet()) {
            cmbInputFiles.addItem(fileName);
        }

        // Añadir ActionListener a cmbInputFiles
        cmbInputFiles.addActionListener(e -> updateFiles());

        // Llenar cmbTasks inicialmente
        updateFiles();
    }

    private void updateFiles() {
        String file = (String) cmbInputFiles.getSelectedItem();
        cmbTasks.removeAllItems();
        cmbTasks.addItem(SELECT_PROMPT);

        if (file != null && !file.equals(SELECT_PROMPT) && files.containsKey(file)) {
            for (String task : files.get(file)) {
                cmbTasks.addItem(task);
            }
        }
    }

    private void findDirOutputFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Seleccionar Directorio");

        int result = fileChooser.showOpenDialog(frmMain.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                String taskName = Optional.ofNullable(cmbTasks.getSelectedItem())
                        .map(Object::toString)
                        .filter(s -> !s.equals("Seleccione..."))
                        .orElse("");

                String fileName = "Result" + (taskName.isEmpty() ? "" : "_" + taskName) + ".txt";
                String outputPath = selectedFile.getAbsolutePath() + File.separator + fileName;

                txtOutput.setText(outputPath);
            } else {
                txtOutput.setText("");
            }
        }
    }

    private boolean isEmpty() { //Valida si los campos estan vacios
        boolean estado = false;
        if (SELECT_PROMPT.equals(cmbInputFiles.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Seleccione un archivo de entrada.",
                    "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            estado = true;
        } else if (SELECT_PROMPT.equals(cmbTasks.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Seleccione la tarea a ejecutar.",
                    "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            estado = true;
        } else if (Integer.parseInt(numNodes.getValue().toString()) == 0) {
            JOptionPane.showMessageDialog(this, "Especifique el número de nodos.",
                    "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            estado = true;
        } else if ("".equals(txtOutput.getText())) {
            JOptionPane.showMessageDialog(this, "Seleccione un directorio para el archivo de salida.",
                    "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            estado = true;
        }
        return estado;
    }

    private void clear() {
        fillComboBoxes();
        numNodes.setValue(0);
        txtOutput.setText("");
    }

    private void ejecutarTarea(String task) throws IOException {
        String inputFileName = cmbInputFiles.getSelectedItem().toString();
        String resourcePath = "src/" + inputFileName;
        int nNodes = Integer.parseInt(numNodes.getValue().toString());

        tarea = new Task();
        tarea.setInputFile(resourcePath);
        tarea.setOutputFile(txtOutput.getText());
        tarea.setNodes(nNodes);

        switch (task) {
            case "Codigo_404":
                tarea.setMapFunction(WebLogCode404Analysis::mapFunction);
                tarea.setReduceFunction(WebLogGifAnalysis::reduceFunction);
                break;
            case "Extensión_GIF":
                tarea.setMapFunction(WebLogGifAnalysis::mapFunction);
                tarea.setReduceFunction(WebLogGifAnalysis::reduceFunction);
                break;
            case "Accesos_Por_Horas":
                tarea.setMapFunction(WebLogHourAnalysis::mapFunction);
                tarea.setReduceFunction(WebLogHourAnalysis::reduceFunction);
                break;
            /* case "Temperatura_Diferente_WindChill":
                tarea.setMapFunction(WeatherAnalysis::mapFunction);
                tarea.setReduceFunction(WeatherAnalysis::reduceFunction);
                break;
            case "Precipitaciones_Mayor_A_Cero":
                tarea.setMapFunction(RainfallAnalysis::mapFunction);
                tarea.setReduceFunction(RainfallAnalysis::reduceFunction);
                break;
            case "Temperatura_En_Superficie":
                tarea.setMapFunction(TemperatureAnalysis::mapFunction);
                tarea.setReduceFunction(TemperatureAnalysis::reduceFunction);
                break;
            case "Palabras_Extremadamente_Tristes":
                tarea.setMapFunction(SadWordsAnalysis::mapFunction);
                tarea.setReduceFunction(SadWordsAnalysis::reduceFunction);
                break; */
        }

        tarea.run();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cmbInputFiles = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbTasks = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        numNodes = new javax.swing.JSpinner();
        txtOutput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnFindDir = new javax.swing.JButton();
        btnRun = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("HADOOP MINI");

        jLabel2.setText("GRUPO # 6");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tareas"));

        jLabel3.setText("Archivo de Entrada:");

        jLabel4.setText("Tarea a Ejecutar:");

        jLabel5.setText("Numero de Nodos:");

        txtOutput.setBackground(new java.awt.Color(255, 255, 204));
        txtOutput.setEnabled(false);

        jLabel6.setText("Archivo de Salida:");

        btnFindDir.setText("...");
        btnFindDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindDirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtOutput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFindDir))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbTasks, javax.swing.GroupLayout.Alignment.LEADING, 0, 288, Short.MAX_VALUE)
                            .addComponent(numNodes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbInputFiles, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbInputFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTasks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(numNodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnFindDir))
                .addContainerGap())
        );

        btnRun.setText("Ejecutar");
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        btnExit.setText("Salir");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 210, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(205, 205, 205))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(238, 238, 238))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRun)
                    .addComponent(btnExit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        try {
            if (!isEmpty()){
                ejecutarTarea(cmbTasks.getSelectedItem().toString());
            JOptionPane.showMessageDialog(this, "Tarea ejecutada exitosamente.",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            clear();
            }
        } catch (IOException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRunActionPerformed

    private void btnFindDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindDirActionPerformed
        if (SELECT_PROMPT.equals(cmbTasks.getSelectedItem().toString()))
            JOptionPane.showMessageDialog(this, "Seleccione la tarea a ejecutar.",
                    "Campos incompletos", JOptionPane.WARNING_MESSAGE);
        else
            findDirOutputFile();
    }//GEN-LAST:event_btnFindDirActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose(); // Cierra este formulario
        System.exit(0); // Termina la aplicación
    }//GEN-LAST:event_btnExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFindDir;
    private javax.swing.JButton btnRun;
    private javax.swing.JComboBox<String> cmbInputFiles;
    private javax.swing.JComboBox<String> cmbTasks;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner numNodes;
    private javax.swing.JTextField txtOutput;
    // End of variables declaration//GEN-END:variables
}
