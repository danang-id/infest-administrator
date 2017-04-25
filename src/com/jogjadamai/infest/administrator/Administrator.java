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
 * <h1>class <code>Administrator</code></h1>
 * <p><code>Administrator</code> is a controller class containing all the business
 * login of Infest Administrator application.</p>
 * <br>
 * <p><b><i>Coded, built, and packaged with passion by Danang Galuh Tegar P for Infest.</i></b></p>
 * 
 * @author Danang Galuh Tegar P
 * @version 2017.03.10.0001
 */
public class Administrator {
    
    private static Administrator INSTANCE;
    
    private java.util.List<javax.swing.JCheckBox> featuresCheckBox;
    private java.util.List<com.jogjadamai.infest.entity.Features> features;
    private java.rmi.registry.Registry registry;
    private com.jogjadamai.infest.communication.IProtocolClient protocolClient;
    private com.jogjadamai.infest.communication.IProtocolServer protocolServer;
    
    private Administrator() {
        initialiseConnection();
    }
    
    protected static Administrator getIntance() {
        if(INSTANCE == null) INSTANCE = new Administrator();
        return INSTANCE;
    }
    
    private void initialiseConnection() {
        try {
            com.jogjadamai.infest.service.ProgramPropertiesManager programPropertiesManager = com.jogjadamai.infest.service.ProgramPropertiesManager.getInstance();
            String serverAddress = programPropertiesManager.getProperty("serveraddress");
            if(serverAddress == null) System.exit(-1);
            this.registry = java.rmi.registry.LocateRegistry.getRegistry(42700);
            this.protocolClient = new com.jogjadamai.infest.communication.AdministratorClient();
            this.protocolServer = (com.jogjadamai.infest.communication.IProtocolServer) this.registry.lookup("InfestAPIServer");
            this.protocolServer.authenticate(this.protocolClient);
        } catch (java.rmi.NotBoundException | java.rmi.RemoteException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    protected void signIn(com.jogjadamai.infest.administrator.SignInGUI frame) {
        String password = "";
        for (char character : frame.passwordField.getPassword()) {
            password = password + character;
        }
        Integer[] securityNumber = {
            java.util.Arrays.hashCode(frame.usernameField.getText().getBytes()), 
            java.util.Arrays.hashCode(password.getBytes())
        };
        if(Program.authenticate(securityNumber)) {
            frame.setVisible(false);
        } else {
            javax.swing.JOptionPane.showMessageDialog(frame, "Sign In Failed! Either username or password is wrong.", "Infest Authentication System", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    protected Boolean readAllFeatures(com.jogjadamai.infest.administrator.MainGUI frame) {
        Boolean isSuccess = true;
        try {
            this.features = this.protocolServer.readAllFeature(protocolClient);
        } catch (java.rmi.RemoteException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.featuresCheckBox = new java.util.ArrayList<>();
        this.featuresCheckBox.add(frame.maintenanceModeCheckBox);
        this.featuresCheckBox.add(frame.showCurrencyCheckBox);
        this.featuresCheckBox.add(frame.operatorGenerateReportCheckBox);
        this.featuresCheckBox.add(frame.customerPrintBillCheckBox);
        this.featuresCheckBox.add(frame.customerShowMenuDurationCheckBox);
        this.featuresCheckBox.add(frame.customerShowMenuImageCheckBox);
        features.forEach((feature) -> {
            this.featuresCheckBox.get(feature.getId()-1).setSelected((feature.getStatus() == 1));
            if(feature.getName().equals("CURRENCY")) frame.currencyTextField.setText(feature.getDescription());
        });
        return isSuccess;
    }
    
    protected Boolean writeAllFeatures(com.jogjadamai.infest.administrator.MainGUI frame) {
        Boolean isSuccess = true;
        try {
            this.features = this.protocolServer.readAllFeature(protocolClient);
        } catch (java.rmi.RemoteException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.featuresCheckBox.add(frame.maintenanceModeCheckBox);
        this.featuresCheckBox.add(frame.showCurrencyCheckBox);
        this.featuresCheckBox.add(frame.operatorGenerateReportCheckBox);
        this.featuresCheckBox.add(frame.customerPrintBillCheckBox);
        this.featuresCheckBox.add(frame.customerShowMenuDurationCheckBox);
        this.featuresCheckBox.add(frame.customerShowMenuImageCheckBox);
        features.forEach((feature) -> {
            if(featuresCheckBox.get(feature.getId()-1).isSelected()) feature.setStatus(1);
            else feature.setStatus(0);
            if(feature.getName().equals("CURRENCY")) feature.setDescription(frame.currencyTextField.getText());
            try {
                this.protocolServer.updateFeature(protocolClient, feature);
            } catch (java.rmi.RemoteException ex) {
                java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        isSuccess = true;
        return isSuccess;
    }
    
    protected void refreshServerStatus(com.jogjadamai.infest.administrator.MainGUI frame) {
        try {
            if(com.jogjadamai.infest.communication.ProtocolServer.getInstance().isServerActive()) {
                frame.statusLabel.setText("Started & Listening");
                frame.statusLabel.setForeground(java.awt.Color.BLUE);
                frame.serverToggleButton.setText("STOP SERVER");
                frame.serverToggleButton.setForeground(java.awt.Color.RED);
                this.repaintPane(frame, true);
                if (!this.readAllFeatures(frame)) javax.swing.JOptionPane.showMessageDialog(frame, "Failed to read Features Configuration!", "Read Configuration", javax.swing.JOptionPane.ERROR_MESSAGE);
            } else {
                frame.statusLabel.setText("Idle");
                frame.statusLabel.setForeground(java.awt.Color.RED);
                frame.serverToggleButton.setText("START SERVER");
                frame.serverToggleButton.setForeground(java.awt.Color.BLUE);
                this.repaintPane(frame, false);
            }
        } catch (java.rmi.RemoteException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    protected void repaintPane(com.jogjadamai.infest.administrator.MainGUI frame, Boolean isEnabled) {
        if(!isEnabled) {
            frame.maintenanceModeCheckBox.setSelected(isEnabled);
            frame.showCurrencyCheckBox.setSelected(isEnabled);
            frame.currencyTextField.setText("");
            frame.operatorGenerateReportCheckBox.setSelected(isEnabled);
            frame.customerPrintBillCheckBox.setSelected(isEnabled);
            frame.customerShowMenuDurationCheckBox.setSelected(isEnabled);
            frame.customerShowMenuImageCheckBox.setSelected(isEnabled);
        }
        frame.maintenanceModeCheckBox.setEnabled(isEnabled);
        frame.showCurrencyCheckBox.setEnabled(isEnabled);
        frame.currencyTextField.setEnabled(isEnabled);
        frame.operatorGenerateReportCheckBox.setEnabled(isEnabled);
        frame.customerPrintBillCheckBox.setEnabled(isEnabled);
        frame.customerShowMenuDurationCheckBox.setEnabled(isEnabled);
        frame.customerShowMenuImageCheckBox.setEnabled(isEnabled);
        frame.saveFeaturesConfiguration.setEnabled(isEnabled);
        frame.featurePanel.setEnabled(isEnabled);
    }
        
    protected void toggleServer(com.jogjadamai.infest.administrator.MainGUI frame) {
        try {
            if(!com.jogjadamai.infest.communication.ProtocolServer.getInstance().isServerActive()) com.jogjadamai.infest.communication.ProtocolServer.getInstance().start();
            else com.jogjadamai.infest.communication.ProtocolServer.getInstance().stop();
        } catch (java.rmi.RemoteException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.refreshServerStatus(frame);
    }
    
}
