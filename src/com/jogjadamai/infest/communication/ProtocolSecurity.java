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
package com.jogjadamai.infest.communication;

/**
 * <h1>class <code>ProtocolSecurity</code></h1>
 * <p><code>ProtocolSecurity</code> is final class with all static methods
 * in order to enhance the communication security between <code>ProtocolServer</code>
 * and <code>ProtocolClient</code>.</p>
 * <br>
 * <p><b><i>Coded, built, and packaged with passion by Danang Galuh Tegar P for Infest.</b></i></p>
 * 
 * @author Danang Galuh Tegar P
 * @version 2017.03.10.0001
 * @see ICPServer
 * @see com.jogjadamai.infest.communication.ProtocolServer
 * @see com.jogjadamai.infest.communication.ProtocolClient
 */
final class ProtocolSecurity {
    
    private static final Integer[] SECURITY_NUMBER = {
        -1874213221, -1062208929, 1159584749
    };
    /**
     * <h2>method <code>isVerfied()</code></h2>
     * <p>Method <code>isVerified</code> is used to verify an authenticity of a 
     * <code>ProtocolClient</code> object based on its type and security number. 
     * If <code>ProtocolClient</code> object's security number match of its type,
     * it will returning <code>true</code>.</p>
     * 
     * @param protocolClient <code>ProtocolClient</code> object to be verified.
     * @return Boolean indicating <code>true</code> when <code>ProtocolClient</code>
     *         is verified.
     */
    protected static Boolean isVerified(com.jogjadamai.infest.communication.IProtocolClient protocolClient) throws java.rmi.RemoteException {
        Boolean isVerified = false;
        switch(protocolClient.getType()) {
            case ADMINISTRATOR:
                isVerified = (java.util.Objects.equals(protocolClient.getSecurityNumber(), SECURITY_NUMBER[0]));
                break;
            case OPERATOR:
                isVerified = (java.util.Objects.equals(protocolClient.getSecurityNumber(), SECURITY_NUMBER[1]));
                break;
            case CUSTOMER:
                isVerified = (java.util.Objects.equals(protocolClient.getSecurityNumber(), SECURITY_NUMBER[2]));
                break;
            default:
                isVerified = false;
                break;
        }
        return isVerified;
    }
    
}
