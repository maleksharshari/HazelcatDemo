package org.example;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HazelcastExample {
    public static void main(String[] args) {

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("localhost:5701"); // assuming Hazelcast is running locally
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        IMap<Integer, person> map = client.getMap("My map");

        for (int i = 0; i < 10000; i++) {
            map.put(i, new person("Person",i));
        }
        System.out.println("Inserted 10,000 Person objects.");

        person person = map.get(5000); // Retrieve Person with key 5000
        System.out.println("Retrieved: " + person);

        client.shutdown();
    }
}




