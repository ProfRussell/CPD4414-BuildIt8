/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 * Updated 2015 Mark Russell <mark.russell@lambtoncollege.ca>.
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
package json;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
@Path("/stream")
public class JsonStreamSample {

    /**
     * Produces a fairly generic JSON Object on-demand.
     * @return - the JSON Object
     */
    @GET
    @Produces("application/json")
    public String getAll() {
        StringWriter out = new StringWriter();
        JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
        JsonGenerator gen = factory.createGenerator(out);
        gen.writeStartObject()
                .write("apple", "red")
                .write("banana", "yellow")
              .writeEnd();
        gen.close();
        return out.toString();
    }

    /**
     * A POST Request to /r/stream with Content-Type application/json will be parsed 
     * and all string values will be output to the server log.
     * @param str - the JSON String received from the client
     */
    @POST
    @Consumes("application/json")
    public void post(String str) {
        JsonParser parser = Json.createParser(new StringReader(str));
        Map<String, String> map = new HashMap<>();
        String name = "", value;
        while (parser.hasNext()) {
            Event evt = parser.next();
            switch (evt) {
                case KEY_NAME:
                    name = parser.getString();
                    break;
                case VALUE_STRING:
                    value = parser.getString();
                    map.put(name, value);
                    break;
                case VALUE_FALSE: case VALUE_NULL:
                case VALUE_NUMBER: case VALUE_TRUE:
                    map.put(name, "Error: Not String Value");
                    break;
            }
        }
        for(String k : map.keySet())
            System.out.printf("%s : %s\n", k, map.get(k));
    }
}
