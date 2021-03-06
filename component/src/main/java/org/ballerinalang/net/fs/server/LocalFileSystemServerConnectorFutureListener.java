/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinalang.net.fs.server;

import org.ballerinalang.connector.api.BallerinaConnectorException;
import org.ballerinalang.connector.api.ConnectorFutureListener;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.services.ErrorHandlerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@code LocalFileSystemServerConnectorFutureListener} is the responsible for acting on notifications
 * received from Ballerina side.
 */
public class LocalFileSystemServerConnectorFutureListener implements ConnectorFutureListener {

    private static final Logger log = LoggerFactory.getLogger(LocalFileSystemServerConnectorFutureListener.class);
    private String  serviceName;

    LocalFileSystemServerConnectorFutureListener(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void notifySuccess() {
        if (log.isDebugEnabled()) {
            log.debug("Received success notify for FileSystemConnector service: " + serviceName);
        }
    }

    @Override
    public void notifyReply(BValue... response) {
        if (log.isDebugEnabled() && response != null) {
            if (response.length > 0 && response[0] != null) {
                log.debug("Received reply for FileSystemConnector service: "
                        + serviceName + "; " + response[0].stringValue());
            }
        }
    }

    @Override
    public void notifyFailure(BallerinaConnectorException ex) {
        log.error("Error occurred for FileSystemConnector service: " + serviceName, ex);
        ErrorHandlerUtils.printError(ex);
    }
}
