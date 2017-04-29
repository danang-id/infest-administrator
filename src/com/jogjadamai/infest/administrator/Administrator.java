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
public final class Administrator {
    
    private static Administrator INSTANCE;
    
    private com.jogjadamai.infest.administrator.SignInGUI signInFrame;
    private com.jogjadamai.infest.administrator.MainGUI mainFrame;
    private ViewFrame activeFrame;
    private java.util.List<javax.swing.JCheckBox> featuresCheckBox;
    private java.util.List<com.jogjadamai.infest.entity.Features> features;
    private java.rmi.registry.Registry registry;
    private com.jogjadamai.infest.communication.IProtocolClient protocolClient;
    private com.jogjadamai.infest.communication.IProtocolServer protocolServer;
    
    private final com.jogjadamai.infest.service.ProgramPropertiesManager programPropertiesManager;
    
    private enum ViewFrame {
        SIGN_IN, MAIN
    }
    
    private Administrator() {
        programPropertiesManager = com.jogjadamai.infest.service.ProgramPropertiesManager.getInstance();
        initialiseConnection();
        this.activeFrame = ViewFrame.SIGN_IN;
    }
    
    protected static Administrator getInstance() {
        if(INSTANCE == null) INSTANCE = new Administrator();
        return INSTANCE;
    }
    
    protected static Administrator getInstance(com.jogjadamai.infest.administrator.SignInGUI signInFrame, com.jogjadamai.infest.administrator.MainGUI mainFrame) {
        if(INSTANCE == null) INSTANCE = new Administrator();
        INSTANCE.setSignInFrame(signInFrame);
        INSTANCE.setMainFrame(mainFrame);
        return INSTANCE;
    }
    
    private void initialiseConnection() {
        String serverAddress = null;
        try {
            serverAddress = programPropertiesManager.getProperty("serveraddress");
        } catch (java.lang.NullPointerException ex) {
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, "Infest Configuration File is miss-configured!\n\n"
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
            javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, 
                    "There's is an error with Infest API Server! Please contact Infest Developer Team.\n\n"
                            + "Program error detected.", 
                    "INFEST: Remote Connection Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
        }
    }
    
    private void fatalExit(int code) {
        System.err.println("[INFEST] " +  getNowTime() + ": System exited with code " + code + ".");
        javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame,
                "Fatal error occured! Please contact an Infest Adminisrator.\n\n"
                + "CODE [" + code + "]\n"
                + "Infest Program is now exiting.", "INFEST: System Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        System.exit(code);
    }

    private String getNowTime() {
        return java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(java.time.LocalDateTime.now());
    }
    
    protected void setSignInFrame(com.jogjadamai.infest.administrator.SignInGUI signInFrame) {
        this.signInFrame = signInFrame;
    }
    
    protected void setMainFrame(com.jogjadamai.infest.administrator.MainGUI mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    private Boolean isCredentialsCurrent(java.awt.Component parent, com.jogjadamai.infest.communication.Credentials credentials) {
        com.jogjadamai.infest.communication.Credentials savedCred = null;
        try {    
            savedCred = this.protocolServer.getCredentials(protocolClient);
            if(savedCred == null) {
                savedCred = new com.jogjadamai.infest.communication.Credentials("", new char[0]);
                System.err.println("[INFEST] " +  getNowTime() + ": " + "java.lang.NullPointerException");
                javax.swing.JOptionPane.showMessageDialog(parent, 
                        "Infest Configuration File is miss-configured!\n\n"
                        + "Please verify that the Infest Configuration File (infest.conf) is exist in the current\n"
                        + "working directory and is properly configured. Any wrong setting or modification of\n"
                        + "Infest Configuration File would cause this error.", 
                        "INFEST: Program Configuration Manager", javax.swing.JOptionPane.ERROR_MESSAGE);
                fatalExit(-1);
            }
            try {
                String salt = getSalt();
                try {
                    credentials.encrpyt(salt);
                    return savedCred.equals(credentials);
                } catch (Exception ex) {
                    System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
                    javax.swing.JOptionPane.showMessageDialog(parent,
                            "Failed to encrypt credentials!\n\n"
                            + "Please contact an Infest Administrator for furhter help.", 
                            "INFEST: Encryption Service", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NullPointerException ex) {
                System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
                javax.swing.JOptionPane.showMessageDialog(parent, 
                        "Infest Configuration File is miss-configured!\n\n"
                        + "Please verify that the Infest Configuration File (infest.conf) is exist in the current\n"
                        + "working directory and is properly configured. Any wrong setting or modification of\n"
                        + "Infest Configuration File would cause this error.", 
                        "INFEST: Program Configuration Manager", javax.swing.JOptionPane.ERROR_MESSAGE);
                fatalExit(-1);
                return false;
            }
        } catch (java.rmi.RemoteException ex) {
            savedCred = new com.jogjadamai.infest.communication.Credentials("", new char[0]);
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            javax.swing.JOptionPane.showMessageDialog(parent, 
                    "There's is an error with Infest API Server! Please contact Infest Developer Team.\n\n"
                            + "Program error detected.", 
                    "INFEST: Remote Connection Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
            return false;
        }
    }
    
    protected void signIn() {
        if(isCredentialsCurrent((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, new com.jogjadamai.infest.communication.Credentials(signInFrame.usernameField.getText(), signInFrame.passwordField.getPassword()))) {
            signInFrame.setVisible(false);
            mainFrame.setVisible(true);
            activeFrame = ViewFrame.MAIN;
        } else {
            javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, 
                    "Authentication Failed!\n\n"
                        + "Either username or password is wrong, or your\n"
                        + "Infest Configuration File is miss-configured.",
                    "INFEST: Authentication System", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    protected void signOut() {
        mainFrame.setVisible(false);
        signInFrame.usernameField.setText("");
        signInFrame.usernameField.requestFocusInWindow();
        signInFrame.passwordField.setText("");
        signInFrame.setVisible(true);
        activeFrame = ViewFrame.SIGN_IN;
    }
    
    protected void shutdown(int code) {
        System.out.println("[INFEST] " +  getNowTime() + ": System exited with code " + code + ".");
        signInFrame.setVisible(false);
        mainFrame.setVisible(false);
        System.exit(code);
    }
    
    protected Boolean readAllFeatures() {
        Boolean isSuccess = true;
        try {
            this.features = this.protocolServer.readAllFeature(protocolClient);
        } catch (java.rmi.RemoteException ex) {
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, 
                    "There's is an error with Infest API Server! Please contact Infest Developer Team.\n\n"
                            + "Program error detected.", 
                    "INFEST: Remote Connection Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
        }
        this.featuresCheckBox = new java.util.ArrayList<>();
        this.featuresCheckBox.add(mainFrame.maintenanceModeCheckBox);
        this.featuresCheckBox.add(mainFrame.showCurrencyCheckBox);
        this.featuresCheckBox.add(mainFrame.operatorGenerateReportCheckBox);
        this.featuresCheckBox.add(mainFrame.customerPrintBillCheckBox);
        this.featuresCheckBox.add(mainFrame.customerShowMenuDurationCheckBox);
        this.featuresCheckBox.add(mainFrame.customerShowMenuImageCheckBox);
        features.forEach((feature) -> {
            this.featuresCheckBox.get(feature.getId()-1).setSelected((feature.getStatus() == 1));
            if(feature.getName().equals("CURRENCY")) mainFrame.currencyTextField.setText(feature.getDescription());
        });
        return isSuccess;
    }
    
    protected Boolean writeAllFeatures() {
        Boolean isSuccess = true;
        try {
            this.features = this.protocolServer.readAllFeature(protocolClient);
        } catch (java.rmi.RemoteException ex) {
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, 
                    "There's is an error with Infest API Server! Please contact Infest Developer Team.\n\n"
                            + "Program error detected.", 
                    "INFEST: Remote Connection Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
        }
        this.featuresCheckBox.add(mainFrame.maintenanceModeCheckBox);
        this.featuresCheckBox.add(mainFrame.showCurrencyCheckBox);
        this.featuresCheckBox.add(mainFrame.operatorGenerateReportCheckBox);
        this.featuresCheckBox.add(mainFrame.customerPrintBillCheckBox);
        this.featuresCheckBox.add(mainFrame.customerShowMenuDurationCheckBox);
        this.featuresCheckBox.add(mainFrame.customerShowMenuImageCheckBox);
        features.forEach((feature) -> {
            if(featuresCheckBox.get(feature.getId()-1).isSelected()) feature.setStatus(1);
            else feature.setStatus(0);
            if(feature.getName().equals("CURRENCY")) feature.setDescription(mainFrame.currencyTextField.getText());
            try {
                this.protocolServer.updateFeature(protocolClient, feature);
            } catch (java.rmi.RemoteException ex) {
                System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
                javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, 
                        "There's is an error with Infest API Server! Please contact Infest Developer Team.\n\n"
                                + "Program error detected.", 
                        "INFEST: Remote Connection Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                fatalExit(-1);
            }
        });
        isSuccess = true;
        return isSuccess;
    }
    
    protected void refreshServerStatus() {
        try {
            if(com.jogjadamai.infest.communication.ProtocolServer.getInstance().isServerActive()) {
                mainFrame.statusLabel.setText("Started & Listening");
                mainFrame.statusLabel.setForeground(java.awt.Color.BLUE);
                mainFrame.serverToggleButton.setText("STOP SERVER");
                mainFrame.serverToggleButton.setForeground(java.awt.Color.RED);
                this.repaintPane(true);
                if (!this.readAllFeatures()) javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, "Failed to read Features Configuration!", "Read Configuration", javax.swing.JOptionPane.ERROR_MESSAGE);
            } else {
                mainFrame.statusLabel.setText("Idle");
                mainFrame.statusLabel.setForeground(java.awt.Color.RED);
                mainFrame.serverToggleButton.setText("START SERVER");
                mainFrame.serverToggleButton.setForeground(java.awt.Color.BLUE);
                this.repaintPane(false);
            }
        } catch (java.rmi.RemoteException ex) {
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, 
                    "There's is an error with Infest API Server! Please contact Infest Developer Team.\n\n"
                            + "Program error detected.", 
                    "INFEST: Remote Connection Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
        }
    }
    
    protected void repaintPane(Boolean isEnabled) {
        if(!isEnabled) {
            mainFrame.maintenanceModeCheckBox.setSelected(isEnabled);
            mainFrame.showCurrencyCheckBox.setSelected(isEnabled);
            mainFrame.currencyTextField.setText("");
            mainFrame.operatorGenerateReportCheckBox.setSelected(isEnabled);
            mainFrame.customerPrintBillCheckBox.setSelected(isEnabled);
            mainFrame.customerShowMenuDurationCheckBox.setSelected(isEnabled);
            mainFrame.customerShowMenuImageCheckBox.setSelected(isEnabled);
        }
        mainFrame.maintenanceModeCheckBox.setEnabled(isEnabled);
        mainFrame.showCurrencyCheckBox.setEnabled(isEnabled);
        mainFrame.currencyTextField.setEnabled(isEnabled);
        mainFrame.operatorGenerateReportCheckBox.setEnabled(isEnabled);
        mainFrame.customerPrintBillCheckBox.setEnabled(isEnabled);
        mainFrame.customerShowMenuDurationCheckBox.setEnabled(isEnabled);
        mainFrame.customerShowMenuImageCheckBox.setEnabled(isEnabled);
        mainFrame.saveFeaturesConfiguration.setEnabled(isEnabled);
        mainFrame.featurePanel.setEnabled(isEnabled);
    }
        
    protected void toggleServer() {
        try {
            if(!com.jogjadamai.infest.communication.ProtocolServer.getInstance().isServerActive()) com.jogjadamai.infest.communication.ProtocolServer.getInstance().start();
            else com.jogjadamai.infest.communication.ProtocolServer.getInstance().stop();
        } catch (java.rmi.RemoteException ex) {
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, 
                    "There's is an error with Infest API Server! Please contact Infest Developer Team.\n\n"
                            + "Program error detected.", 
                    "INFEST: Remote Connection Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
        }
        this.refreshServerStatus();
    }
    
    protected void openDocumentation() {
        javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, 
            "Documentation of this program is not yet available. Please check on further release.\n\nThank you!",
            "INFEST: Documentation", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    protected void changePassword() {
        new ChangePasswordDialog().run();
    }
    
    protected void changePassword(com.jogjadamai.infest.administrator.ChangePasswordDialog changePasswordDialog) {
        if(isCredentialsCurrent(changePasswordDialog, new com.jogjadamai.infest.communication.Credentials("infestadmin", changePasswordDialog.currentPasswordField.getPassword()))) {
            try {
                String salt = getSalt();
                com.jogjadamai.infest.communication.Credentials newCredentials = new com.jogjadamai.infest.communication.Credentials("infestadmin", changePasswordDialog.newPasswordField.getPassword());
                try {
                    newCredentials.encrpyt(salt);
                    java.io.File credFile = new java.io.File("administrator.crd");
                    credFile.createNewFile();
                    java.io.FileOutputStream fos = new java.io.FileOutputStream(credFile, false);
                    java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(fos);
                    try {
                        oos.writeObject(newCredentials);
                        javax.swing.JOptionPane.showMessageDialog(changePasswordDialog,
                                "New Administrator Credentials has been saved!",
                                "INFEST: Credentials Manager", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        changePasswordDialog.setVisible(false);
                    } catch (java.io.IOException ex) {
                        System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
                        javax.swing.JOptionPane.showMessageDialog(changePasswordDialog,
                                "Failed to set new credentials!\n\n"
                                        + "The program is unable to create new credentials file.\n"
                                        + "The file probably is under used by another proccess.",
                                "INFEST: Credentials Manager", javax.swing.JOptionPane.ERROR_MESSAGE);
                    } finally {
                        try {
                            fos.close();
                            oos.close();
                        } catch (java.io.IOException ex) {
                            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
                        }
                    }
                } catch (Exception ex) {
                    System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
                    javax.swing.JOptionPane.showMessageDialog(changePasswordDialog,
                            "Failed to encrypt credentials!\n\n"
                                    + "Please contact Infest Developer Team for further help.",
                            "INFEST: Encryption System", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            } catch (NullPointerException ex) {
                System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
                javax.swing.JOptionPane.showMessageDialog(changePasswordDialog, 
                        "Infest Configuration File is miss-configured!\n\n"
                        + "Please verify that the Infest Configuration File (infest.conf) is exist in the current\n"
                        + "working directory and is properly configured. Any wrong setting or modification of\n"
                        + "Infest Configuration File would cause this error.", 
                        "INFEST: Program Configuration Manager", javax.swing.JOptionPane.ERROR_MESSAGE);
                fatalExit(-1);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(changePasswordDialog, 
                    "Authentication failed!\n\n"
                        + "Either current password is wrong, or your\n"
                        + "Infest Configuration File is miss-configured.",
                    "INFEST: Authentication System", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    protected void setOperatorCredentials() {
        new OperatorCredentialsDialog().run();
    }
    
    protected void setOperatorCredentials(com.jogjadamai.infest.administrator.OperatorCredentialsDialog operatorCredentialsDialog) {
        if(createOperatorCredential(operatorCredentialsDialog.usernameField.getText(), operatorCredentialsDialog.newPasswordField.getPassword())) {
            javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, 
                    "New Operator Credentials has been successfully set!",
                    "INFEST: Credentials Manager", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            operatorCredentialsDialog.setVisible(false);
        } else javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame,
                "Failed to set new Operator Credentials!",
                "INFEST: Credentials Manager", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    
    protected void resetOperatorCredentials() {
        if(javax.swing.JOptionPane.showConfirmDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame,
                "Are you sure to reset Operator credentials?", 
                "INFEST: Credentials Manager", 
                javax.swing.JOptionPane.YES_NO_OPTION, 
                javax.swing.JOptionPane.QUESTION_MESSAGE) == javax.swing.JOptionPane.YES_OPTION){
            if(createOperatorCredential()) javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, 
                    "Operator Credentials has been successfully reset!",
                    "INFEST: Credentials Manager", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            else javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame, 
                    "Failed to reset Operator Credentials!",
                    "INFEST: Credentials Manager", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String getSalt() throws NullPointerException {
        String salt;
        try {
            salt = programPropertiesManager.getProperty("salt");
            if(salt.isEmpty()) throw new NullPointerException();
        } catch(NullPointerException ex) {
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            salt = "";
            throw ex;
        }
        return salt;
    }
    
    private Boolean createOperatorCredential() {
        return createOperatorCredential(null, null);
    }
    
    private Boolean createOperatorCredential(String username, char[] password) {
        Boolean isSuccess = false;
        String defaultUsername = "infestoperator";
        char[] defaultPassword = {
            'o', 'p', 'e', 'r', 'a', 't', 'o', 'r', 'i', 'n', 'f', 'e', 's', 't'
        };
        if(username == null) username = defaultUsername;
        if(password == null) password = defaultPassword;
        try {
            String salt = getSalt();
            com.jogjadamai.infest.communication.Credentials credential = new com.jogjadamai.infest.communication.Credentials(username, password);
            try {
                credential.encrpyt(salt);
                java.io.File credFile = new java.io.File("operator.crd");
                credFile.createNewFile();
                java.io.FileOutputStream fos = new java.io.FileOutputStream(credFile, false);
                java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(fos);
                try {
                    oos.writeObject(credential);
                } catch (java.io.IOException ex) {
                    System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
                    javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame,
                            "Failed to create credentials file.",
                            "INFEST: Credentials Manager", javax.swing.JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        fos.close();
                        oos.close();
                        isSuccess = true;
                    } catch (java.io.IOException ex) {
                        System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
                    }
                }
            } catch (Exception ex) {
                System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
                javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame,
                        "Failed to encrypt credentials.",
                        "INFEST: Encryption System", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (NullPointerException ex) {
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            javax.swing.JOptionPane.showMessageDialog((activeFrame == ViewFrame.MAIN) ? mainFrame : signInFrame,
                    "Infest Configuration File is miss-configured!\n\n"
                            + "Please verify that the Infest Configuration File (infest.conf) is exist in the current\n"
                            + "working directory and is properly configured. Any wrong setting or modification of\n"
                            + "Infest Configuration File would cause this error.", 
                    "INFEST: Program Configuration Manager", javax.swing.JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
        }
        return isSuccess;
    }
    
}
