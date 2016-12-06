/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import beans.Aluno;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author laercio
 */
@Path("/aluno")
public class AlunoResource {

    @Context
    private UriInfo context;
    ArrayList<Aluno> alunos = new ArrayList<>();

    /**
     * Creates a new instance of CotacaoResource
     */
    public AlunoResource() {
    }

    /**
     * Retrieves representation of an instance of webservices.AlunoResource
     *
     * @return an instance of java.lang.String
     */
   

    @GET
    @Path("/getAll")
    @Produces("application/json")
    public ArrayList<Aluno> getAlunos() {
        try {
            FileInputStream fis = new FileInputStream("alunos");
            ObjectInputStream ois = new ObjectInputStream(fis);
            alunos = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();

        }

        return alunos;
    }

    /**
     * PUT method for updating or creating an instance of AlunoResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Path("/saveAll")
    @Consumes("application/json")
    public void saveAll(ArrayList<Aluno> alunos) {
        
       

        try {
            FileOutputStream fos = new FileOutputStream("alunos");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(alunos);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
