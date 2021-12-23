package fr.uge.jee.servlet.hellosession;

import java.util.*;

/**
 * thread safe
 */
class HelloSessionData {

    private final Object lock = new Object();
    private final HashMap<UUID, Integer> tokenMap = new HashMap<>();


    UUID createToken(){
        UUID newToken;
        while(true){
            synchronized (lock){
                newToken = UUID.randomUUID();
                System.out.println("new token "+newToken);
                if(tokenMap.get(newToken) == null ){
                    tokenMap.put(newToken, 1 );
                    return newToken;
                    //return Map.entry(newToken, 1);
                }
            }
        }
    }


    Map.Entry<UUID, Integer> incrementCounter(UUID token){
        Objects.requireNonNull(token);
        synchronized (lock){
            var valueForToken =  tokenMap.get(token);
            if( valueForToken == null){
                return null;
            }
            var newToken =createToken();
            tokenMap.put(newToken,++valueForToken);
            tokenMap.remove(token);
            return Map.entry(newToken, valueForToken);
        }
    }

}
