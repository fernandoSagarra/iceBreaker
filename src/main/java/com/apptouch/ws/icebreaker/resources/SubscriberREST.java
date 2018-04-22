
package com.apptouch.ws.icebreaker.resources;

import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities.UserBaseEntity;
import com.apptouch.ws.icebreaker.businesslogic.model.SubscriberModel;
import com.apptouch.ws.icebreaker.businesslogic.services.SubscriberService;
import com.apptouch.ws.icebreaker.businesslogic.utils.CommonUtils;
import com.apptouch.ws.icebreaker.businesslogic.utils.MapperUtils;
import com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions.DataNotFoundMongoException;
import com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions.MongoUpdateException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.MongoException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;



/**
 *
 * @author fernando
 */
@Path("/subscribers")
public class SubscriberREST {
    
    SubscriberService subscriberService = new SubscriberService();  
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubscriberById(@PathParam("id") String id){
        String sSubscriber = "";
        try{
            SubscriberModel subscriber = (SubscriberModel)subscriberService.getSubscriberById(id);
            if(subscriber == null){
                throw new DataNotFoundMongoException("Subscriber is null");
            }else{
                sSubscriber = MapperUtils.getMapper().writeValueAsString(subscriber);
            }           
            
        }catch(MongoException mongoEx){
           throw new DataNotFoundMongoException(mongoEx.getMessage());
        }catch (JsonProcessingException ex) {
            Logger.getLogger(SubscriberService.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IllegalArgumentException inv){
            throw new DataNotFoundMongoException(inv.getMessage());
        }
        
        return Response.status(Response.Status.ACCEPTED).entity(sSubscriber).build();
    }
    
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubscriberByUserNameAndEmail(@HeaderParam("userName") String userName, @HeaderParam("email") String email){
        String sSubscriber = "";
        List<String> params = new ArrayList<>();
        params.add(email);
        params.add(userName);
        List<String> attr = new ArrayList<>();        
        attr.add("email");  
        attr.add("userName");
        
        try{
            UserBaseEntity subscriber = subscriberService.getSubscriberByUserNameAndEmail(attr,params);
            if(subscriber == null){
                throw new DataNotFoundMongoException("Subscriber is null");
            }else{
                sSubscriber = MapperUtils.getMapper().writeValueAsString(subscriber);
            }           
            
        }catch(MongoException mongoEx){
           throw new DataNotFoundMongoException(mongoEx.getMessage());
        }catch (JsonProcessingException ex) {
            Logger.getLogger(SubscriberService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.status(Response.Status.ACCEPTED).entity(sSubscriber).build();
    }
    @GET    
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubscriberByEmailAndPassword(@HeaderParam("email") String email, @HeaderParam("password") String password){
        String sSubscriber = "";
        List<String> params = new ArrayList<>();
        params.add(email);
        params.add(password);
        List<String> attr = new ArrayList<>();        
        attr.add("email");  
        attr.add("password");
        
        try{
            UserBaseEntity subscriber = subscriberService.getSubscriberByEmailAndPassword(attr,params);
            if(subscriber == null){
                throw new DataNotFoundMongoException("Subscriber is null");
            }else{
                sSubscriber = MapperUtils.getMapper().writeValueAsString(subscriber);
            }           
            
        }catch(MongoException mongoEx){
           throw new DataNotFoundMongoException(mongoEx.getMessage());
        }catch (JsonProcessingException ex) {
            Logger.getLogger(SubscriberService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.status(Response.Status.ACCEPTED).entity(sSubscriber).build();
    }
    
    @POST
    @Path("/subscriber")
    @Consumes(MediaType.APPLICATION_JSON)//ESTO LO DEBO QUITAR Y TRATAR LA DATA COMO QUERYPARAM
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewSubscriber(String data, @Context UriInfo uriInfo) {
        /*Should we use objectMapper or create en entity provider????*/
        String newUser = "";
        String id = "";
        try {
            UserBaseEntity newSubscriber = subscriberService.addNewSubscriber(data);
            newUser = MapperUtils.getMapper().writeValueAsString(newSubscriber);
            id = newSubscriber.getId();

        } catch (MongoException mongoEx) {
            throw new MongoUpdateException(mongoEx.getMessage());
        } catch (JsonProcessingException jsonEx) {
            jsonEx.printStackTrace();
        } 
        URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
        return Response.created(uri)
                .entity(newUser)
                .build();
    }
    @PUT
    @Path("/friends")    
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewfriendToSubscriber(@HeaderParam("id") String id,@HeaderParam("user") String friendId) {
        String user = "";
        try {
            UserBaseEntity subscriber = subscriberService.addNewfriendToSubscriber(id, friendId);
            user = MapperUtils.getMapper().writeValueAsString(subscriber);
        } catch (MongoException mongoEx) {
            throw new MongoUpdateException(mongoEx.getMessage());
        } catch (JsonProcessingException jsonEx) {
            jsonEx.printStackTrace();
        } 
        return Response.status(Response.Status.OK).entity(user).build();
    }
    
    @GET    
    @Path("/friends")  
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFriendsIdsForSubscriber(@HeaderParam("id") String id){  
        List<String> friendsIds = new ArrayList<>();
        String friendsEntity = "";        
        try{
          SubscriberModel subscriber = subscriberService.getAllFriendsIdsForSubscriber(id);
            if(subscriber == null){
                throw new DataNotFoundMongoException("Subscriber has no friends");
            }else{
                List<SubscriberModel> friends = (List<SubscriberModel>)subscriber.getFriends();
                if(friends == null || friends.isEmpty()){
                    throw new DataNotFoundMongoException("Subscriber has no friends");
                }else{
                    for(SubscriberModel s: friends){
                        friendsIds.add(s.getId().toString());
                    }
                }
                
               friendsEntity = MapperUtils.getMapper().writeValueAsString(friendsIds);
            }           
            
        }catch(MongoException mongoEx){
           throw new DataNotFoundMongoException(mongoEx.getMessage());
        }catch (JsonProcessingException ex) {
            Logger.getLogger(SubscriberService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.status(Response.Status.ACCEPTED)
                .entity(friendsEntity).build();
    }
   /* @POST
    @Path("/images")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("id") String id,
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {
        boolean result = false;
        // check if all form parameters are provided
        if (uploadedInputStream == null || fileDetail == null) {
            return Response.status(400).entity("Invalid form data").build();
        }
        // create our destination folder, if it not exists
        try {
            CommonUtils.createFolderIfNotExists(CommonUtils.getImageLocation());
        } catch (SecurityException se) {
            return Response.status(500)
                    .entity("Can not create destination folder on server")
                    .build();
        }
        String uploadedFileLocation = CommonUtils.getImageLocation() + fileDetail.getFileName();
        try {
            CommonUtils.saveFile(uploadedInputStream, uploadedFileLocation);
            result = subscriberService.updateSubscriberProfileImageUrl(id, uploadedFileLocation);
        } catch (IOException e) {
            return Response.status(500).entity("Can not save file").build();
        }catch (MongoUpdateException updateEx){
            throw new MongoUpdateException(updateEx.getMessage());
        }
        return (result)?Response.status(200)
                .entity("File saved to " + uploadedFileLocation).build():Response.status(500)
                .entity("File Not saved ").build(); //PLEASE CHANGE RESPONSE.STATUS
    }*/

}
