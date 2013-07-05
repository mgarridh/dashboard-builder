/**
 * Copyright (C) 2013 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.dashboard.ui.controller.requestChain;

import org.jboss.dashboard.ui.Dashboard;
import org.jboss.dashboard.ui.components.DashboardHandler;

/**
 * This is a request chain processor for dashbuilder.
 * It reads some request parameters and perform operations.
 */
public class DashbuilderRequestProcessor extends RequestChainProcessor {

    /** Class logger. */
    private static transient org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(DashbuilderRequestProcessor.class.getName());

    /** The refresh request parameter. */
    private static final String PARAM_REFRESH = "refresh";

    /**
     * Process the current request and extract custom dasbuilder paramerters.
     *
     * @return If request must be processed. Always <code>true</code>
     * @throws Exception An error while processing request.
     */
    @Override
    protected boolean processRequest() throws Exception {
        Dashboard dashboard = DashboardHandler.lookup().getCurrentDashboard();
        if (dashboard != null) {
            String refresh = getRequest().getParameter(PARAM_REFRESH);
            boolean isRefresh = toBoolean(refresh);
            if (isRefresh) dashboard.refresh();
        }
        return true;
    }

    /**
     * Converts a string value into a boolean one.
     *
     * @param str The string to convert.
     * @return The boolean value for <code>str</code> value.
     */
    protected boolean toBoolean(String str) {
        return (str != null && str.equals(Boolean.TRUE.toString()));
    }
}
