/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
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
package websockets;

import java.io.IOException;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
@ClientEndpoint
public class SampleClient {
    Session session;
    @OnOpen
    public void open(Session s) {
        session = s;
    }
    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
    }
    public void send(String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }
}
