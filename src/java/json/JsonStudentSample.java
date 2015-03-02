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
package json;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
@Path("/student")
public class JsonStudentSample {
    /**
     * Produces a Student Object in JSON
     * @return - the Student JSON
     */
    @GET
    @Produces("application/json")
    public String getStudent() {
        JsonObject json = Json.createObjectBuilder()
                .add("name", "Bob Bobson")
                .add("id", 3241)
                .build();
        return json.toString();
    }
    
    @POST
    @Consumes("application/json")
    public void readStudent(String str) {
        JsonObject json = Json.createReader(new StringReader(str)).readObject();
        System.out.println(json.getInt("id") + ": " + json.getString("name"));
    }
}
