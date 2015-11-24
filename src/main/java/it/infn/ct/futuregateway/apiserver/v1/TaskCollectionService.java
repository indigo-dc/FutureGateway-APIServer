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
 ***********************************************************************/

package it.infn.ct.futuregateway.apiserver.v1;

import it.infn.ct.futuregateway.apiserver.utils.Constants;
import it.infn.ct.futuregateway.apiserver.utils.annotations.Status;
import it.infn.ct.futuregateway.apiserver.v1.resources.Task;
import it.infn.ct.futuregateway.apiserver.v1.resources.TaskList;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * The TaskCollectionService provide the REST APIs for the task collection
 * as defined in the documentation.
 *
 * @see http://docs.csgfapis.apiary.io/#reference/v1.0/task
 * @author Marco Fargetta <marco.fargetta@ct.infn.it>
 */
@Path("/tasks")
public class TaskCollectionService extends BaseService {
    /**
     * Logger object.
     * Based on apache commons logging.
     */
    private final Log log = LogFactory.getLog(TaskCollectionService.class);


    /**
     * Retrieve the list of tasks.
     *
     * The list includes only the tasks associated to the user.
     *
     * @return The task collection
     */
    @GET
    @Produces(Constants.MIMETYPE)
    public final TaskList listTasks() {
        TaskList tasks;
        try {
            tasks = new TaskList(getEntityManager(), getUser());
        } catch (RuntimeException re) {
            getResponse().setStatus(
                    Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            throw re;
        }
        return tasks;
    }

    /**
     * Register a new task.
     *
     * @param task The task to register
     * @return The task registered
     */
    @POST
    @Status(Response.Status.CREATED)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(Constants.MIMETYPE)
    public final Task createTask(final Task task) {
        Date now = new Date();
        task.setDate(now);
        task.setLastChange(now);
        task.setStatus(Task.STATUS.WAITING);
        task.setUser(getUser());
        EntityManager em = getEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(task);
            et.commit();
        } catch (RuntimeException re) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            log.error("Impossible to create the task: " + task);
            log.error(re);
        } finally {
            em.close();
        }
        return task;
    }

    /**
     * Create the dir to store the input.
     * Create a directory inside the temporary store with path
     * "inputs/<taskId>". This is used to store the input file before the
     * submission.
     *
     * @param taskId The ID of the task to associate the files
     */
    private void createInputDir(final String taskId) {
    }
}