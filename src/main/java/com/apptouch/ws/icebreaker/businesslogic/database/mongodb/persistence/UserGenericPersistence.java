
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.persistence;

/**
 *
 * @author fernando
 */
public class UserGenericPersistence extends MongoDBGenericPersistence{
    
    public UserGenericPersistence() {
        super();
    }
   /* public UserBaseEntity saveUserEntity(UserBaseEntity user) {
        
        return (UserBaseEntity)persist(user);
    }   
    public <E extends UserBaseEntity> E getUserById(Object id, Class<E> clazz){
        return getEntityById(clazz, id);
    }
    public UserBaseEntity getUserByPassword(String password){
        return getEntityByStringAttribute(UserBaseEntity.class, password, "password");
    } 
    public <E extends UserBaseEntity> E getUserByUserNameAndEmail(Class<E> clazz, List<String> attributes, List<String> values){
        if ((clazz == null) || (attributes == null) || (attributes.isEmpty())) {
            return null;
        } 
        Query<E> query = mongoDatastore.createQuery(clazz);
        query.and(
                query.criteria(attributes.get(0)).equal(values.get(0)),
                query.criteria(attributes.get(1)).equal(values.get(1))
        );
        return query.get();
    }
    public <E extends UserBaseEntity> E getUserByTwoAttributes(Class<E> clazz, List<String> attributes, List<String> values){
        if ((clazz == null) || (attributes == null) || (attributes.isEmpty())) {
            return null;
        } 
        Query<E> query = mongoDatastore.createQuery(clazz);
        query.and(
                query.criteria(attributes.get(0)).equal(values.get(0)),
                query.criteria(attributes.get(1)).equal(values.get(1))
        );
        return query.get();
    }*/
    
}
