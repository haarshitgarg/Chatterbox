package org.harshit.userinfo.Controller;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.harshit.userinfo.Utils.Friend;
import org.harshit.userinfo.Utils.Messages;

import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Result;
import org.neo4j.driver.Record;

/**
 * DatabaseController
 */
public class DatabaseController {
    private Driver driver_;

    public static DatabaseController instance = new DatabaseController();

    private DatabaseController() {
        String database_uri = "neo4j://localhost:7689";
        driver_ = GraphDatabase.driver(database_uri);
        System.out.println("[DATABASE] Neo4j Driver created");
    }

    public List<Friend> getFriendsList(String username) throws Exception {
        Session session = driver_.session();
        String query = "MATCH (p1:PERSON {username:\""+username+"\"})-[r:FRIEND]-(friends:PERSON) RETURN friends.username AS name, r.last_chat AS last_chat";
        Result result = session.run(query);
        List<Friend> friend_list = new ArrayList<>();
        if(!result.hasNext()) {
            System.out.println("[DATABASE] Result is empty");
        }
        else {
            while (result.hasNext()) {
                Record curr = result.next();

                String name = curr.get("name").toString();
                String last_chat = curr.get("last_chat").toString();

                friend_list.add(new Friend(name, last_chat));
            }
        }

        System.out.println("[DATABASE] Ran the db query");
        return friend_list;
    }

    public Boolean deleteAllMessages() {
        try {
            System.out.println("[NEO4J DB CONTROLLER] DELETING ALL MESSAGES");
            Session session = this.driver_.session();
            String query1 = "MATCH (m:MESSAGE) DETACH DELETE m;";
            String query2 = "MATCH ()-[r:SENDER]->() DELETE r;";
            String query3 = "MATCH ()-[r:RECEIVER]->() DELETE r;";
            session.run(query1);
            session.run(query2);
            session.run(query3);
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public Boolean addMessageToDB(Messages message) {
        try {
            System.out.println("[NEO4J DB CONTROLLER] Adding message to the db");
            Session session = this.driver_.session();
            String msg_from_query = String.format("MATCH (u1:PERSON{username:\"%s\"})\n", message.getMsg_from());
            String msg_to_query = String.format("MATCH (u2:PERSON{username:\"%s\"})\n", message.getMsg_to());
            String relationship_query = String.format("CREATE (u1)-[:SENDER]->(m:MESSAGE{id:\"%s\", msg: \"%s\", time:%d})-[:RECEIVER{status:%b}]->(u2);", 
                    message.getId(), 
                    message.getMsg(), 
                    message.getTime(), 
                    message.getBReceived());

            String query = msg_to_query + msg_from_query + relationship_query;
            session.run(query);
            return true;
        }
        catch (Exception e) {
            System.out.println("[NEO4J DB CONTROLLER] Encountered an error");
            e.printStackTrace();
            return false;

        }

    }

    public List<Messages> getMessages(String username, long last_sync) {
        List<Messages> messages = new ArrayList<>();
        try {
            Session session = this.driver_.session();
            String query = String.format("MATCH (p:PERSON)-->(m:MESSAGE)-[r:RECEIVER]->(p2:PERSON) WHERE p.username = \"%s\" AND m.time > %d RETURN m.id AS id, m.msg AS msg, m.time AS time, p2.username as username, r.status AS status", username, last_sync);

            System.out.println("[NEO4J DB CONTROLLER] The query: "+query);
            Result result = session.run(query);
            while(result.hasNext()) {
                Record rec = result.next();
                messages.add(new Messages(
                            rec.get("id").toString(), 
                            username, rec.get("username").toString(), 
                            rec.get("msg").toString(), 
                            rec.get("time", 0L), 
                            rec.get("status", false)
                            ));
            }
            String query2 = String.format("MATCH (p:PERSON)-->(m:MESSAGE)-[r:RECEIVER]->(p2:PERSON) WHERE p2.username = \"%s\" AND m.time > %d RETURN m.id AS id, m.msg AS msg, m.time AS time, p.username as username, r.status AS status", username, last_sync);
            Result result2 = session.run(query2);
            while(result2.hasNext()) {
                Record rec = result2.next();
                messages.add(new Messages(
                            rec.get("id").toString(), 
                            rec.get("username").toString(), username,  
                            rec.get("msg").toString(), 
                            rec.get("time", 0L), 
                            rec.get("status", false)
                            ));
            }
            return messages;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
