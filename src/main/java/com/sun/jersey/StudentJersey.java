package com.sun.jersey;

import com.sun.dao.StudentMapper;
import com.sun.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by pengjikun on 2017/6/10.
 */
@Path("student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentJersey {

    @Autowired
    private StudentMapper studentDao;

    @POST
    public Response create(Student student){
        studentDao.insert(student);
        return Response.ok("成功").build();
    }

    @GET
    public Response get(@QueryParam("id") Integer id) {
        Student student = studentDao.selectByPrimaryKey(id);
        if(student == null) throw new RuntimeException("null");
        return Response.ok(student).build();
    }

    @PUT
    public Response update(@QueryParam("id") Integer id, @QueryParam("name") String name) {
        Student student = Student.builder().id(id).name(name).build();
        studentDao.updateByPrimaryKey(student);
        return Response.ok("成功").build();
    }

    @DELETE
    public Response delete(@QueryParam("id") Integer id) {
        studentDao.deleteByPrimaryKey(id);
        return Response.ok("成功").build();
    }


}
