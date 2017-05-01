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
 * <h1>class <code>Program</code></h1>
 * <p><code>Program</code> is class defining <code>main()</code> to run the
 * application.</p>
 * <br>
 * <p><b><i>Coded, built, and packaged with passion by Danang Galuh Tegar P for Infest.</i></b></p>
 * 
 * @author Danang Galuh Tegar P
 * @version 2017.03.10.0001
 */
public final class Program {
    
    private static SignInGUI SignInGUI;
    private static MainGUI MainGUI;
    private static Thread SignInThread, MainThread;
    private static Administrator Controller;
    private static com.jogjadamai.infest.communication.IProtocolServer Server;
    
    public static void main(String[] args) throws java.rmi.RemoteException {
        Program.SignInGUI = new SignInGUI();
        Program.MainGUI = new MainGUI();
        try {
            Program.Server = com.jogjadamai.infest.communication.ProtocolServer.getInstance();
        } catch(java.rmi.server.ExportException ex) {
            javax.swing.JOptionPane.showMessageDialog(SignInGUI, 
                    "Program already running! Only one instance allowed at a time.", 
                    "INFEST: Administrator", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        Program.Controller = Administrator.createInstance(Program.SignInGUI, Program.MainGUI);
        Program.Controller.onFirstRun();
        Program.SignInThread = new Thread(Program.SignInGUI);
        Program.MainThread = new Thread(Program.MainGUI);
        java.awt.EventQueue.invokeLater(Program.SignInThread);
        java.awt.EventQueue.invokeLater(Program.MainThread);
    }
    
}
