package dao;

import connection.ConnectionFactory;
import model.*;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Abstract dao.
 *
 * @param <T> the type parameter
 */
public class AbstractDAO<T> {
    /**
     * The constant LOGGER.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * Instantiates a new Abstract dao.
     *
     * @param type the type
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO(Class<T> type){
        this.type = type;
    }

    private String createSelectQuery(boolean all, String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        if(type.getSimpleName().equals("Order")){
            sb.append("shipping_business."+type.getSimpleName());
        }
        else{
            sb.append(type.getSimpleName());
        }
        if (!all) {
            sb.append(" WHERE " + field + " =?");
        }
        return sb.toString();
    }

    private String createDeleteQuery(boolean cond){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        if(type.getSimpleName().equals("Order")){
            sb.append("shipping_business."+type.getSimpleName());
        }
        else{
            sb.append(type.getSimpleName());
        }
        if ( cond ){
            sb.append(" WHERE (id = ?)");
        }

        return sb.toString();
    }

    private String createUpdateQuery(int id, String fieldToUpdate){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE " + "shipping_business." + type.getSimpleName());
        sb.append(" SET " + fieldToUpdate + " = ? ");
        sb.append("WHERE id = " + id );
        return sb.toString();
    }

    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        if(type.getSimpleName().equals("Order")){
            sb.append("shipping_business."+type.getSimpleName());
        }
        else{
            sb.append(type.getSimpleName());
        }
        sb.append(" (");
        int index = 0;
        for (Field f : type.getDeclaredFields()){
            if (index != 0){
                if ( index != type.getDeclaredFields().length - 1){
                    sb.append(f.getName() + ", ");
                }
                else{
                    sb.append(f.getName() + ") ");
                }
            }
            index++;
        }
        sb.append("VALUES (");
        for (int i = 1; i < index; i++){
            sb.append("?");
            if ( i != index-1 ){
                sb.append(", ");
            }
            else{
                sb.append(")");
            }
        }
        //System.out.println(sb.toString() + " " + type.getName());
        return sb.toString();
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        /*
        try {
            while (resultSet.next()) {
                Class<T> cls = (Class<T>) type.getClass();

                T instance = cls.getDeclaredConstructor().newInstance();
                //T instance = type.getDeclaredConstructor(type).newInstance();
                for(Field f : type.getDeclaredFields()){
                    Object val = resultSet.getObject(f.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, val);
                }
                list.add(instance);
            }
        } catch (SecurityException | IllegalArgumentException | SQLException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        */
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Delete by id.
     *
     * @param id the id
     */
    public void deleteByID(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        //ResultSet resultSet = null;
        String query = createDeleteQuery(true);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
        } finally {
            //ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Delete all.
     */
    public void deleteAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        //ResultSet resultSet = null;
        String query = createDeleteQuery(false);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(false, "id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();


            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<T> findAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(true, "");

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Insert int.
     *
     * @param t the t
     * @return the int
     */
    public int insert(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        int insertedID = -1;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int index = 0;
            for(Field f : t.getClass().getDeclaredFields()){
                if(index != 0){
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), t.getClass());
                    Method method = propertyDescriptor.getReadMethod();
                    Type type = propertyDescriptor.getPropertyType();
                    if(type.equals(int.class)){
                        int val = (Integer)method.invoke(t);
                        statement.setInt(index, val);
                    }
                    else if(type.equals(double.class)){
                        double val = (double)method.invoke(t);
                        statement.setDouble(index, val);
                    }
                    else if(type.equals(String.class)){
                        String s = (String)method.invoke(t);
                        statement.setString(index, s);
                    }
                }
                index++;
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                insertedID = resultSet.getInt(1);
            }
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("id", t.getClass());
            Method method = propertyDescriptor.getWriteMethod();
            method.invoke(t, insertedID);

        } catch (SQLException | IntrospectionException throwables) {
            throwables.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return insertedID;
    }

    /**
     * Update.
     *
     * @param t         the t
     * @param fieldName the field name
     * @param newVal    the new val
     */
    public void update(T t, String fieldName, Object newVal){
        //get the object id
        PropertyDescriptor propertyDescriptor = null;
        int objID = -1;
        try {
            propertyDescriptor = new PropertyDescriptor("id", t.getClass());
            Method method = propertyDescriptor.getReadMethod();
            objID = (Integer)method.invoke(t);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(objID, fieldName);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            PropertyDescriptor propertyDescriptor1 = new PropertyDescriptor(fieldName, t.getClass());
            Type type = propertyDescriptor1.getPropertyType();

            if(type.equals(int.class)){
                statement.setInt(1, (Integer)newVal);
            }
            if(type.equals(double.class)){
                statement.setDouble(1, (Double)newVal);
            }
            if(type.equals(String.class)){
                statement.setString(1, (String)newVal);
            }

            statement.executeUpdate();
        } catch (SQLException | IntrospectionException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
