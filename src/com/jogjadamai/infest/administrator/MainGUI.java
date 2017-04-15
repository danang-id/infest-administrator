/*
 * Copyright 2017 Danang Galuh Tegar P.
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
package com.jogjadamai.infest.administrator;

/**
 * <h1>class <code>MainGUI</code></h1>
 * <p><code>MainGUI</code> is <code>javax.swing.JFrame</code> class defining
 * the Main Graphical User Interface of the application.</p>
 * <br>
 * <p><b><i>Coded, built, and packaged with passion by Danang Galuh Tegar P for Infest.</i></b></p>
 * 
 * @author Danang Galuh Tegar P
 * @version 2017.03.10.0001
 */
public final class MainGUI extends javax.swing.JFrame implements Runnable {
    
    public MainGUI() {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initialiseComponents();
    }

    private void initialiseComponents() {
        
        featurePanel = new javax.swing.JPanel();
        showCurrencyCheckBox = new javax.swing.JCheckBox();
        maintenanceModeCheckBox = new javax.swing.JCheckBox();
        operatorGenerateReportCheckBox = new javax.swing.JCheckBox();
        customerPrintBillCheckBox = new javax.swing.JCheckBox();
        customerShowMenuDurationCheckBox = new javax.swing.JCheckBox();
        customerShowMenuImageCheckBox = new javax.swing.JCheckBox();
        saveFeaturesConfiguration = new javax.swing.JButton();
        currencyTextField = new javax.swing.JTextField();
        serverToggleButton = new javax.swing.JToggleButton();
        logoPanel = new javax.swing.JPanel();
        infestLogoLabel = new javax.swing.JLabel();
        serverStatusLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INFEST: Administrator Panel");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(655, 500));
        setMinimumSize(new java.awt.Dimension(655, 500));
        setName("mainGUI"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(655, 500));
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/InfestIcon.png")).getImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        featurePanel.setBackground(new java.awt.Color(0xffffff));
        featurePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Features Configuration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12))); // NOI18N
        featurePanel.setToolTipText("Features configuration panel.");
        featurePanel.setMaximumSize(new java.awt.Dimension(625, 400));
        featurePanel.setMinimumSize(new java.awt.Dimension(625, 400));

        showCurrencyCheckBox.setBackground(new java.awt.Color(0xffffff));
        showCurrencyCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        showCurrencyCheckBox.setText("Show Currency");
        showCurrencyCheckBox.setToolTipText("Show/unshow currency in OPERATOR and CUSTOMER. The currency will be shown AFTER the nomimal.");
        showCurrencyCheckBox.setDoubleBuffered(true);
        showCurrencyCheckBox.addChangeListener(this::showCurrencyCheckBoxStateChanged);

        maintenanceModeCheckBox.setBackground(new java.awt.Color(0xffffff));
        maintenanceModeCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        maintenanceModeCheckBox.setText("Maintenance Mode");
        maintenanceModeCheckBox.setToolTipText("Turn on/off maintenenance mode (if active, CUSTOMER and OPERATOR will be at offline mode).");
        maintenanceModeCheckBox.setDoubleBuffered(true);

        operatorGenerateReportCheckBox.setBackground(new java.awt.Color(0xffffff));
        operatorGenerateReportCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        operatorGenerateReportCheckBox.setText("OPERATOR: Generate Report");
        operatorGenerateReportCheckBox.setToolTipText("Turn on/off report generator feature in OPERATOR.");
        operatorGenerateReportCheckBox.setDoubleBuffered(true);

        customerPrintBillCheckBox.setBackground(new java.awt.Color(0xffffff));
        customerPrintBillCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        customerPrintBillCheckBox.setText("CUSTOMER: Print Bill");
        customerPrintBillCheckBox.setToolTipText("Turn on/off print bill feature in CUSTOMER.");
        customerPrintBillCheckBox.setDoubleBuffered(true);

        customerShowMenuDurationCheckBox.setBackground(new java.awt.Color(0xffffff));
        customerShowMenuDurationCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        customerShowMenuDurationCheckBox.setText("CUSTOMER: Show Menu Duration");
        customerShowMenuDurationCheckBox.setToolTipText("Show/unshow menu duration in CUSTOMER.");
        customerShowMenuDurationCheckBox.setDoubleBuffered(true);

        customerShowMenuImageCheckBox.setBackground(new java.awt.Color(0xffffff));
        customerShowMenuImageCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        customerShowMenuImageCheckBox.setText("COSTUMER: Show Menu Image");
        customerShowMenuImageCheckBox.setToolTipText("Show/unshow menu image in CUSTOMER.");
        customerShowMenuImageCheckBox.setDoubleBuffered(true);

        saveFeaturesConfiguration.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saveFeaturesConfiguration.setText("SAVE");
        saveFeaturesConfiguration.setToolTipText("Save features configuration.");
        saveFeaturesConfiguration.setDoubleBuffered(true);
        saveFeaturesConfiguration.setPreferredSize(new java.awt.Dimension(100, 40));
        saveFeaturesConfiguration.addActionListener(this::saveFeaturesConfigurationActionPerformed);

        currencyTextField.setBackground(new java.awt.Color(0xffffff));
        currencyTextField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        currencyTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        currencyTextField.setText("CUR");
        currencyTextField.setToolTipText("Type currency to show. The currency will be shown AFTER the nomimal.");
        currencyTextField.setDoubleBuffered(true);

        javax.swing.GroupLayout featurePanelLayout = new javax.swing.GroupLayout(featurePanel);
        featurePanel.setLayout(featurePanelLayout);
        featurePanelLayout.setHorizontalGroup(
            featurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(featurePanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(featurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maintenanceModeCheckBox)
                    .addGroup(featurePanelLayout.createSequentialGroup()
                        .addComponent(showCurrencyCheckBox)
                        .addGap(18, 18, 18)
                        .addComponent(currencyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(operatorGenerateReportCheckBox)
                    .addComponent(customerPrintBillCheckBox)
                    .addComponent(customerShowMenuDurationCheckBox)
                    .addComponent(customerShowMenuImageCheckBox))
                .addGap(381, 381, 381))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, featurePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saveFeaturesConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        featurePanelLayout.setVerticalGroup(
            featurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(featurePanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(maintenanceModeCheckBox)
                .addGap(18, 18, 18)
                .addGroup(featurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showCurrencyCheckBox)
                    .addComponent(currencyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(operatorGenerateReportCheckBox)
                .addGap(18, 18, 18)
                .addComponent(customerPrintBillCheckBox)
                .addGap(18, 18, 18)
                .addComponent(customerShowMenuDurationCheckBox)
                .addGap(18, 18, 18)
                .addComponent(customerShowMenuImageCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(saveFeaturesConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        serverToggleButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        serverToggleButton.setToolTipText("Start/stop Infest API Server.");
        serverToggleButton.setDoubleBuffered(true);
        serverToggleButton.setPreferredSize(new java.awt.Dimension(150, 50));
        serverToggleButton.addActionListener(this::serverToggleButtonActionPerformed);

        logoPanel.setBackground(new java.awt.Color(0xffffff));
        logoPanel.setToolTipText("Infest: Sistem Informasi Restoran Hotel Jogja Damai");
        logoPanel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        logoPanel.setPreferredSize(new java.awt.Dimension(120, 60));
        logoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        infestLogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/MainGUILogo.png"))); // NOI18N
        infestLogoLabel.setDoubleBuffered(true);
        logoPanel.add(infestLogoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        serverStatusLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        serverStatusLabel.setText("Server Status:");
        serverStatusLabel.setToolTipText("");

        statusLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusLabel.setText("<STATUS>");
        statusLabel.setToolTipText("Current Infest API Server status.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(serverToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serverStatusLabel)
                            .addComponent(statusLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(featurePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serverStatusLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(serverToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(statusLabel))
                    .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(featurePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        pack();
        
    }
    
    private void initialiseConnection() {
        try {
            this.registry = java.rmi.registry.LocateRegistry.getRegistry(42700);
            this.protocolClient = new com.jogjadamai.infest.communication.AdministratorClient();
            this.protocolServer = (com.jogjadamai.infest.communication.IProtocolServer) this.registry.lookup("InfestAPIServer");
            this.protocolServer.authenticate(this.protocolClient);
        } catch (java.rmi.NotBoundException | java.rmi.RemoteException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    private void saveFeaturesConfigurationActionPerformed(java.awt.event.ActionEvent evt) { 
        if (this.writeAllFeatures()) javax.swing.JOptionPane.showMessageDialog(this, "Features Configuration saved successfully!", "Save Configuration", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        else javax.swing.JOptionPane.showMessageDialog(this, "Features Configuration failed to be save!", "Save Configuration", javax.swing.JOptionPane.ERROR_MESSAGE);
        if (!this.readAllFeatures()) javax.swing.JOptionPane.showMessageDialog(this, "Failed to read Features Configuration!", "Read Configuration", javax.swing.JOptionPane.ERROR_MESSAGE);
    }                                                         

    private void serverToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.toggleServer();
    }   
    
    private void showCurrencyCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {
        if(this.showCurrencyCheckBox.isSelected()) {
            this.currencyTextField.setEnabled(true);
        } else {
            this.currencyTextField.setText("");
            this.currencyTextField.setEnabled(false);
        }
    }
    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {                                  
        Program.showLoginView();
    } 
    
    // Variables declaration - do not modify                     
    private javax.swing.JTextField currencyTextField;
    private javax.swing.JCheckBox customerPrintBillCheckBox;
    private javax.swing.JCheckBox customerShowMenuDurationCheckBox;
    private javax.swing.JCheckBox customerShowMenuImageCheckBox;
    private javax.swing.JPanel featurePanel;
    private javax.swing.JLabel infestLogoLabel;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JCheckBox maintenanceModeCheckBox;
    private javax.swing.JCheckBox operatorGenerateReportCheckBox;
    private javax.swing.JButton saveFeaturesConfiguration;
    private javax.swing.JLabel serverStatusLabel;
    private javax.swing.JToggleButton serverToggleButton;
    private javax.swing.JCheckBox showCurrencyCheckBox;
    private javax.swing.JLabel statusLabel;
    private java.util.List<javax.swing.JCheckBox> featuresCheckBox;
    private java.util.List<com.jogjadamai.infest.entity.Features> features;
    private java.rmi.registry.Registry registry;
    private com.jogjadamai.infest.communication.IProtocolClient protocolClient;
    private com.jogjadamai.infest.communication.IProtocolServer protocolServer;
    // End of variables declaration  
    
    @Override
    public void run() {
        initialiseConnection();
        refreshServerStatus();
        
        getContentPane().setBackground(new java.awt.Color(0xffffff));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Boolean readAllFeatures() {
        Boolean isSuccess = true;
        try {
            this.features = this.protocolServer.readAllFeature(protocolClient);
        } catch (java.rmi.RemoteException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.featuresCheckBox = new java.util.ArrayList<>();
        this.featuresCheckBox.add(this.maintenanceModeCheckBox);
        this.featuresCheckBox.add(this.showCurrencyCheckBox);
        this.featuresCheckBox.add(this.operatorGenerateReportCheckBox);
        this.featuresCheckBox.add(this.customerPrintBillCheckBox);
        this.featuresCheckBox.add(this.customerShowMenuDurationCheckBox);
        this.featuresCheckBox.add(this.customerShowMenuImageCheckBox);
        features.forEach((feature) -> {
            this.featuresCheckBox.get(feature.getId()-1).setSelected((feature.getStatus() == 1));
            if(feature.getName().equals("CURRENCY")) this.currencyTextField.setText(feature.getDescription());
        });
        return isSuccess;
    }
    
    private Boolean writeAllFeatures() {
        Boolean isSuccess = true;
        try {
            this.features = this.protocolServer.readAllFeature(protocolClient);
        } catch (java.rmi.RemoteException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.featuresCheckBox.add(this.maintenanceModeCheckBox);
        this.featuresCheckBox.add(this.showCurrencyCheckBox);
        this.featuresCheckBox.add(this.operatorGenerateReportCheckBox);
        this.featuresCheckBox.add(this.customerPrintBillCheckBox);
        this.featuresCheckBox.add(this.customerShowMenuDurationCheckBox);
        this.featuresCheckBox.add(this.customerShowMenuImageCheckBox);
        features.forEach((feature) -> {
            if(featuresCheckBox.get(feature.getId()-1).isSelected()) feature.setStatus(1);
            else feature.setStatus(0);
            if(feature.getName().equals("CURRENCY")) feature.setDescription(this.currencyTextField.getText());
            try {
                this.protocolServer.updateFeature(protocolClient, feature);
            } catch (java.rmi.RemoteException ex) {
                java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        isSuccess = true;
        return isSuccess;
    }
    
    private void refreshServerStatus() {
        try {
            if(com.jogjadamai.infest.communication.ProtocolServer.getInstance().isServerActive()) {
                this.statusLabel.setText("Started & Listening");
                this.statusLabel.setForeground(java.awt.Color.BLUE);
                this.serverToggleButton.setText("STOP SERVER");
                this.serverToggleButton.setForeground(java.awt.Color.RED);
                this.repaintPane(true);
                if (!this.readAllFeatures()) javax.swing.JOptionPane.showMessageDialog(this, "Failed to read Features Configuration!", "Read Configuration", javax.swing.JOptionPane.ERROR_MESSAGE);
            } else {
                this.statusLabel.setText("Idle");
                this.statusLabel.setForeground(java.awt.Color.RED);
                this.serverToggleButton.setText("START SERVER");
                this.serverToggleButton.setForeground(java.awt.Color.BLUE);
                this.repaintPane(false);
            }
        } catch (java.rmi.RemoteException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    private void repaintPane(Boolean b) {
        if(!b) {
            this.maintenanceModeCheckBox.setSelected(b);
            this.showCurrencyCheckBox.setSelected(b);
            this.currencyTextField.setText("");
            this.operatorGenerateReportCheckBox.setSelected(b);
            this.customerPrintBillCheckBox.setSelected(b);
            this.customerShowMenuDurationCheckBox.setSelected(b);
            this.customerShowMenuImageCheckBox.setSelected(b);
        }
        this.maintenanceModeCheckBox.setEnabled(b);
        this.showCurrencyCheckBox.setEnabled(b);
        this.currencyTextField.setEnabled(b);
        this.operatorGenerateReportCheckBox.setEnabled(b);
        this.customerPrintBillCheckBox.setEnabled(b);
        this.customerShowMenuDurationCheckBox.setEnabled(b);
        this.customerShowMenuImageCheckBox.setEnabled(b);
        this.saveFeaturesConfiguration.setEnabled(b);
        this.featurePanel.setEnabled(b);
    }
        
    private void toggleServer() {
        try {
            if(!com.jogjadamai.infest.communication.ProtocolServer.getInstance().isServerActive()) com.jogjadamai.infest.communication.ProtocolServer.getInstance().start();
            else com.jogjadamai.infest.communication.ProtocolServer.getInstance().stop();
        } catch (java.rmi.RemoteException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.refreshServerStatus();
    }
    
}