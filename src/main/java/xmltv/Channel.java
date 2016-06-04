/*
 * Copyright 2016 The XMLTVImportPlugin for SageTV Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xmltv;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * POJO for channel data.
 * <br>This is an intermediary format between XMLTV and Sage.
 * 
 * @author mta
 */
public final class Channel {

    /**
     * The xmltv channel id.
     */
    final String xmltvId;

    /**
     * The names of the channel.
     */
    Set names = new LinkedHashSet();

    /**
     * The network.
     */
    String network = "";

    /**
     * The tuner channel numbers.   
     */
    Set numbers = new LinkedHashSet();

    /**
     * The station id.
     */
    Integer id;

    /**
     * The guids that have been used for movies on this channel.
     */
    HashMap movieIds = new HashMap();

    /** 
     * Creates a new instance of Channel
     *  
     * @param aXmltvId the XMLTV id for the channel.
     */
    public Channel(String aXmltvId) {
        this.xmltvId = aXmltvId;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public final String toString() {
        return this.xmltvId;
    }
}