/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors
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

package org.aerogear.connectivity.cdi.event;

import java.util.LinkedHashMap;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.aerogear.connectivity.cdi.qualifier.Broadcast;
import org.aerogear.connectivity.cdi.qualifier.SelectedSend;
import org.aerogear.connectivity.model.PushApplication;
import org.aerogear.connectivity.rest.sender.messages.SelectiveSendMessage;

@Stateless
public final class PushMessageEventDispatcher {
    
    @Inject BeanManager manager;

    
    @SuppressWarnings("serial")
    @Asynchronous
    public void dispatchBroadcastMessage(PushApplication pushApplication, LinkedHashMap<String, ? extends Object> message) {
        BroadcastEvent broadcastEvent = new BroadcastEvent(pushApplication, message);
        manager.fireEvent(broadcastEvent, new AnnotationLiteral<Broadcast>(){});
    }
    
    @SuppressWarnings("serial")
    @Asynchronous
    public void dispatchSelectedSendMessage(PushApplication pushApplication, SelectiveSendMessage message) {
        SelectedSendEvent selectedSendEvent = new SelectedSendEvent(pushApplication, message);
        manager.fireEvent(selectedSendEvent, new AnnotationLiteral<SelectedSend>(){});
    }
}
