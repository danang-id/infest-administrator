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

import java.rmi.RemoteException;

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
    
    private static Runnable MainGUI, LoginView;
    private static Thread MainGUIThread, LoginViewThread;
    private static com.jogjadamai.infest.communication.IProtocolServer Server;
    
    private static final Integer[] SECURITY_NUMBER = {
        -1358083639, 266374003
    };
    
    public static void main(String[] args) throws RemoteException {
        Program.MainGUI = new MainGUI();
        Program.LoginView = new LoginView();
        Program.MainGUIThread = new Thread(Program.MainGUI);
        Program.LoginViewThread = new Thread(Program.LoginView);
        Program.Server = com.jogjadamai.infest.communication.ProtocolServer.getInstance();
        Program.showLoginView();
    }
    
    protected static void showLoginView() {
        java.awt.EventQueue.invokeLater(Program.LoginViewThread);
    }
    
    protected static Boolean authenticate(Integer[] securityNumber){
        if (java.util.Arrays.equals(securityNumber, Program.SECURITY_NUMBER)) {
            java.awt.EventQueue.invokeLater(Program.MainGUIThread);
            return true;
        } else {
            return false;
        }
    }
    
}
