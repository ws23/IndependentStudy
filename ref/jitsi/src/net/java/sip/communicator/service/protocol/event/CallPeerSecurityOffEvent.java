/*
 * Jitsi, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Copyright @ 2015 Atlassian Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.java.sip.communicator.service.protocol.event;

import net.java.sip.communicator.service.protocol.*;

import org.jitsi.service.protocol.event.*;

/**
 * The <tt>CallPeerSecurityAuthenticationEvent</tt> is triggered whenever
 * a the security strings are received in a secure call.
 *
 * @author Yana Stamcheva
 */
public class CallPeerSecurityOffEvent
    extends CallPeerSecurityStatusEvent
{
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 0L;

    /**
     * The event constructor.
     *
     * @param callPeer the call peer associated with this event
     * @param sessionType the type of the session: audio or video
     */
    public CallPeerSecurityOffEvent( CallPeer callPeer,
                                            int sessionType)
    {
        super(callPeer, sessionType);
    }
}
