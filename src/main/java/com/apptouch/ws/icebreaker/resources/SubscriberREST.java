
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
import org.springframework.beans.factory.annotation.Autowired;



/**
 *
 * @author fernando
 */
@Path("/subscribers")
public class SubscriberREST {
    
    private static final Logger LOGGER = Logger.getLogger(SubscriberREST.class.getName());
    
    @Autowired    
    private SubscriberService subscriberService;  
    
    public SubscriberREST(){
        LOGGER.info("HelloSubscriberREST()");
    }
    
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

}
