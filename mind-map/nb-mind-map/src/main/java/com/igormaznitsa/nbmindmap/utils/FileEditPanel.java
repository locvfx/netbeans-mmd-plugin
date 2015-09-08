/*
 * Copyright 2015 Igor Maznitsa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.igormaznitsa.nbmindmap.utils;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FileEditPanel extends javax.swing.JPanel {

  private static final long serialVersionUID = -6683682013891751388L;

  private static final Logger logger = LoggerFactory.getLogger(FileEditPanel.class);
  
  private final File projectFolder;
  
  public FileEditPanel(final File projectFolder, final String path) {
    initComponents();
    this.projectFolder = projectFolder;
    this.textFieldFilePath.setText(path == null ? "" : path);
  }

  public String getPath() {
    return this.textFieldFilePath.getText().trim();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    labelBrowseCurrentLink = new javax.swing.JLabel();
    textFieldFilePath = new javax.swing.JTextField();
    buttonChooseFile = new javax.swing.JButton();
    buttonReset = new javax.swing.JButton();

    setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
    setLayout(new java.awt.GridBagLayout());

    labelBrowseCurrentLink.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/igormaznitsa/nbmindmap/icons/file_link.png"))); // NOI18N
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/igormaznitsa/nbmindmap/i18n/Bundle"); // NOI18N
    labelBrowseCurrentLink.setToolTipText(bundle.getString("FileEditPanel.labelBrowseCurrentLink.toolTipText")); // NOI18N
    labelBrowseCurrentLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    labelBrowseCurrentLink.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    labelBrowseCurrentLink.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        labelBrowseCurrentLinkMouseClicked(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 10;
    add(labelBrowseCurrentLink, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1000.0;
    add(textFieldFilePath, gridBagConstraints);

    buttonChooseFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/igormaznitsa/nbmindmap/icons/file_manager.png"))); // NOI18N
    buttonChooseFile.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChooseFileActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    add(buttonChooseFile, gridBagConstraints);

    buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/igormaznitsa/nbmindmap/icons/cross16.png"))); // NOI18N
    buttonReset.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonResetActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    add(buttonReset, gridBagConstraints);
  }// </editor-fold>//GEN-END:initComponents
 
  private void labelBrowseCurrentLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBrowseCurrentLinkMouseClicked
    if (evt.getClickCount()>1){
      try {
        Desktop.getDesktop().open(new File(this.textFieldFilePath.getText().trim()));
      }
      catch (IOException ex) {
        logger.error("Can't open file "+this.textFieldFilePath.getText(), ex);
        Toolkit.getDefaultToolkit().beep();
      }
    }
  }//GEN-LAST:event_labelBrowseCurrentLinkMouseClicked

  private void buttonChooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChooseFileActionPerformed
    final File theFile = new File(this.labelBrowseCurrentLink.getText().trim());
    final File parent = theFile.getParentFile();
    
    final JFileChooser chooser = new JFileChooser(parent == null ? this.projectFolder : parent);
    if (theFile.isFile()) chooser.setSelectedFile(theFile);
    chooser.setApproveButtonText("Select");
    chooser.setDialogTitle("Select file");
    chooser.setMultiSelectionEnabled(false);
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
      final File selected = chooser.getSelectedFile();
      this.textFieldFilePath.setText(selected.getAbsolutePath());
    }
  }//GEN-LAST:event_buttonChooseFileActionPerformed

  private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
    this.textFieldFilePath.setText("");
  }//GEN-LAST:event_buttonResetActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonChooseFile;
  private javax.swing.JButton buttonReset;
  private javax.swing.JLabel labelBrowseCurrentLink;
  private javax.swing.JTextField textFieldFilePath;
  // End of variables declaration//GEN-END:variables
}
