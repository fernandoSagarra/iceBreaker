
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.persistence;

/**
 *
 * @author fernando
 */
public class SubscriberModelPersistence extends UserGenericPersistence{
    
    public SubscriberModelPersistence(){
        super();
    }   
 /*This method returns a SubscriberModel object corresponding to a given Id
 * a projection is performed so we only fetch favoritePlaces list
 * @param Object id: A given Id to search for
 * @return: A matching subscriber with a projection on favoritePlaces field
 */  
    /*public SubscriberModel getAllFavoritePlacesIdsForSubscriber(Object id){
        SubscriberModel result;
        ObjectId userId = null;
        if(id == null){
           throw new MongoUpdateException("Unknown subscriber to get favoritePlaces from");
        }
        if(id instanceof String){
            userId = new ObjectId((String)id);
        }else if(id instanceof ObjectId){
            userId = (ObjectId)id;
        }     
        Query<SubscriberModel> query = mongoDatastore.createQuery(SubscriberModel.class);
        result = query.field("_id")
                            .equal(userId)
                            .project("favoritePlaces", true)
                            .get();
        return result;
    }
/*This method returns a SubscriberModel object corresponding to a given Id
 * a projection is performed so we only fetch friends list
 * @param Object id: A given Id to search for
 * @return: A matching subscriber with a projection on friends field
 */    
    /*public SubscriberModel getAllFriendsIdsForSubscriber(Object id){
        SubscriberModel result;
        String userId = null;
        if(id == null){
           throw new MongoUpdateException("Unknown subscriber to get friends from");
        }
        if(id instanceof String){
            userId = (String)id;
        }else if(id instanceof ObjectId){
            userId = id.toString();
        }        
        Query<SubscriberModel> query = mongoDatastore.createQuery(SubscriberModel.class);
        result = query.field("_id")
                            .equal(userId)
                            .project("friends", true)
                            .get();
        return result;
    }
    public UserBaseEntity addNewFriend(Object id, SubscriberModel friend){
       if(friend == null){
           throw new MongoUpdateException("No friend found. Please add a friend");
       }
       if(id == null){
           throw new MongoUpdateException("Unknown subscriber to add a friend to");
       }
       String userId = "";
        if(id instanceof String){
            userId = (String)id;
        }else if(id instanceof ObjectId){
            userId = id.toString();
        }  
        if(userId.isEmpty()){
            throw new MongoUpdateException("Unknown subscriber to add a friend to");
        }
       Query<SubscriberModel> updateQuery = mongoDatastore.find(SubscriberModel.class);
       updateQuery.field("_id").equal(userId);
       UpdateOperations ops = mongoDatastore
               .createUpdateOperations(SubscriberModel.class)
               .addToSet("friends",friend);
       mongoDatastore.update(updateQuery, ops,false);  
       return friend;
    }
    public void addNewFavoritePlace(ObjectId id, PlaceModel place){
       if(place == null){
           throw new IllegalArgumentException("place can't be null");
       }
       if(id == null){
           throw new IllegalArgumentException("Id can't be null");
       }
       Query<SubscriberModel> updateQuery = mongoDatastore.find(SubscriberModel.class);
       updateQuery.field("id").equal(id);
       UpdateOperations ops = mongoDatastore
               .createUpdateOperations(SubscriberModel.class)
               .addToSet("favoritePlaces",place);
       mongoDatastore.update(updateQuery, ops, false);       
    }
    
    public boolean updateSubscriberProfileImageUrl(Object id, String url){
        String userId = "";
        if(id == null){
           throw new MongoUpdateException("Unknown subscriber to update profileUrl to");
        }
        if(id instanceof String){
            userId = (String)id;            
        }else if(id instanceof ObjectId){
            userId = id.toString();
        }  
        if(userId.isEmpty()){
            throw new MongoUpdateException("Unknown subscriber to update profileUrl to");
        }        
        return (updateFieldById(SubscriberModel.class, userId, "pictureUrl", url) instanceof BaseEntity);
    }*/
}
