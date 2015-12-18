/***********************************************************************
 * Copyright (c) 2015:
 * Istituto Nazionale di Fisica Nucleare (INFN), Italy
 * Consorzio COMETA (COMETA), Italy
 *
 * See http://www.infn.it and and http://www.consorzio-cometa.it for details on
 * the copyright holders.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***********************************************************************
 */
package it.infn.ct.futuregateway.apiserver.v1;

import it.infn.ct.futuregateway.apiserver.utils.Constants;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import static junit.framework.Assert.assertEquals;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

/**
 *
 * @author Marco Fargetta <marco.fargetta@ct.infn.it>
 */
public class TaskCollectionServiceIT extends JerseyTest {
//
    @Override
    protected final Application configure() {
        return new ResourceConfig(TaskCollectionService.class);
    }
//
//
//
//    /**
//     * Test the status code for empty collection GET.
//     *
//     * The status code has to be 404.
//     */
//    @Test
//    public void emptyListTasks() {
//        Response rs = target("/v1.0/tasks").request(Constants.INDIGOMIMETYPE).
//                get();
//        assertEquals(200, rs.getStatus());
//    }
//
//    /**
//     * Test the POST of a new task in the collection.
//     */
//    @Test
//    public void createTask() {
//        Task ts = new Task();
//        ts.setApplicationId("test");
//        Entity<Task> taskEntity = Entity.entity(ts, Constants.INDIGOMIMETYPE);
//        Response rs = target("/v1.0/tasks").request(Constants.INDIGOMIMETYPE).
//                post(taskEntity);
//        assertEquals(201, rs.getStatus());
//        Task newTask = rs.readEntity(Task.class);
//        assertEquals(ts.getApplicationId(), newTask.getApplicationId());
//        assertNotNull(newTask.getId());
//    }
//
    /**
     * Ciccio.
     */
    @Test
    public final void emptyListTasks() {
        Response rs = target("/v1.0/tasks").request(Constants.INDIGOMIMETYPE).
                get();
        assertEquals(Response.Status.OK, rs.getStatus());
    }
}