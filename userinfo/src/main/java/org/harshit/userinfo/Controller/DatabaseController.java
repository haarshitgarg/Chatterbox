package org.harshit.userinfo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.harshit.userinfo.Utils.Friend;

import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Result;
import org.neo4j.driver.Record;

/**
 * DatabaseController
 */
public class DatabaseController {
    private final Driver driver_;

    public DatabaseController() {
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

}
