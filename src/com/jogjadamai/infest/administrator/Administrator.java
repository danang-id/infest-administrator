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
    
    private final com.jogjadamai.infest.service.ProgramPropertiesManager programPropertiesManager;
            
    
    private Administrator() {
        programPropertiesManager = com.jogjadamai.infest.service.ProgramPropertiesManager.getInstance();
        initialiseConnection();
    }
    
    protected static Administrator getIntance() {
        if(INSTANCE == null) INSTANCE = new Administrator();
        return INSTANCE;
    }
    
    private void initialiseConnection() {
        String serverAddress = null;
        try {
            serverAddress = programPropertiesManager.getProperty("serveraddress");
        } catch (java.lang.NullPointerException ex) {
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            javax.swing.JOptionPane.showMessageDialog(null, "Infest Configuration File is miss-configured!\n\n"
                    + "Please verify that the Infest Configuration File (infest.conf) is exist in the current\n"
                    + "working directory and is properly configured. Any wrong setting or modification of\n"
                    + "Infest Configuration File would cause this error.", "INFEST: Program Configuration Manager", javax.swing.JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
        }
        try {
            this.registry = java.rmi.registry.LocateRegistry.getRegistry(serverAddress, 42700);
            this.protocolClient = new com.jogjadamai.infest.communication.AdministratorClient();
            this.protocolServer = (com.jogjadamai.infest.communication.IProtocolServer) this.registry.lookup("InfestAPIServer");
            this.protocolServer.authenticate(this.protocolClient);
        } catch (java.rmi.NotBoundException | java.rmi.RemoteException ex) {
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            javax.swing.JOptionPane.showMessageDialog(null, "Failed to initialise Infest API Server (on " + serverAddress  +")!\n\n"
                + "Program error detected.", "INFEST: Remote Connection Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
        }
    }
    
    private void fatalExit(int code) {
        System.err.println("[INFEST] " +  getNowTime() + ": System exited with code " + code + ".");
        javax.swing.JOptionPane.showMessageDialog(null,
                "Fatal error occured! Please contact an Infest Adminisrator.\n\n"
                + "CODE [" + code + "]\n"
                + "Infest Program is now exiting.", "INFEST: System Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        System.exit(code);
    }

    private String getNowTime() {
        return java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(java.time.LocalDateTime.now());
    }
    
    protected void signIn(com.jogjadamai.infest.administrator.SignInGUI frame) {
        com.jogjadamai.infest.communication.Credentials savedCred = null;
        try {    
            savedCred = this.protocolServer.getCredentials(protocolClient);
        } catch (java.rmi.RemoteException ex) {
            savedCred = new com.jogjadamai.infest.communication.Credentials("", new char[0]);System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            javax.swing.JOptionPane.showMessageDialog(frame, "Infest API Server is unable to run!\n\n"
                + "Program error detected.", "INFEST: Remote Connection Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
        }
        com.jogjadamai.infest.communication.Credentials inputCred = new com.jogjadamai.infest.communication.Credentials(frame.usernameField.getText(), frame.passwordField.getPassword());
        try {
            String salt = null;
            salt = this.programPropertiesManager.getProperty("salt");
            try {
                inputCred.encrpyt(salt);
            } catch (java.security.NoSuchAlgorithmException 
                    | java.security.spec.InvalidKeySpecException 
                    | javax.crypto.NoSuchPaddingException 
                    | java.security.InvalidKeyException 
                    | java.security.spec.InvalidParameterSpecException 
                    | java.io.UnsupportedEncodingException 
                    | javax.crypto.IllegalBlockSizeException 
                    | javax.crypto.BadPaddingException ex) {
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            javax.swing.JOptionPane.showMessageDialog(frame, "Failed to encrypt credentials!\n\n"
                    + "Please contact an Infest Administrator for furhter help.", 
                    "INFEST: Encryption Service", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (NullPointerException ex) {
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            javax.swing.JOptionPane.showMessageDialog(frame, "Infest Configuration File is miss-configured!\n\n"
                    + "Please verify that the Infest Configuration File (infest.conf) is exist in the current\n"
                    + "working directory and is properly configured. Any wrong setting or modification of\n"
                    + "Infest Configuration File would cause this error.", 
                    "INFEST: Program Configuration Manager", javax.swing.JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
        }
        System.out.println(savedCred.hashCode() + " vs " + inputCred.hashCode());
        if(savedCred.equals(inputCred)) {
            frame.setVisible(false);
        } else {
            javax.swing.JOptionPane.showMessageDialog(frame, 
                    "Sign In Failed!\n\n"
                    + "Either username or password is wrong, or your\n"
                    + "Infest Configuration File is miss-configured.", 
                    "INFEST: Authentication System", javax.swing.JOptionPane.ERROR_MESSAGE);
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
