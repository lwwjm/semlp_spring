package com.smelp.utils;

import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
@Component
public class RedisTool {
	

    private static final String unlockScript =
              "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n"
            + "then\n"
            + "    return redis.call(\"del\",KEYS[1])\n"
            + "else\n"
            + "    return 0\n"
            + "end";
   @Autowired
    private StringRedisTemplate redisTemplate;

    public void RedisLock(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String lock(String name, long expire, long timeout) throws InterruptedException {
        Assert.isTrue(timeout>0 && timeout<60000, "timeout must greater than 0 and less than 1 min");

        long startTime = System.currentTimeMillis();
        String token;
        do{
            token = tryLock(name, expire);
            if(token == null) {
                if((System.currentTimeMillis()-startTime) > (timeout-50))
                    break;
                Thread.sleep(50); //try 50 per sec
            }
        }while(token==null);

        return token;
    }
  
    public String tryLock(String name, long expire) {
        Assert.notNull(name, "Lock name must not be null");
        Assert.isTrue(expire>0, "expire must greater than 0");

        String token = UUID.randomUUID().toString();
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection conn = factory.getConnection();
        try{
            Boolean result = conn.set(name.getBytes(Charset.forName("UTF-8")), token.getBytes(Charset.forName("UTF-8")), 
                    Expiration.from(expire, TimeUnit.MILLISECONDS), RedisStringCommands.SetOption.SET_IF_ABSENT);
            if(result!=null && result) {
            	 return token;
            }
               
        }finally {
            RedisConnectionUtils.releaseConnection(conn, factory);
        }
        return null;
    }

    public boolean unlock(String name, String token) {
        Assert.notNull(name, "Lock name must not be null");
        Assert.notNull(token, "Token must not be null");

        byte[][] keysAndArgs = new byte[2][];
        keysAndArgs[0] = name.getBytes(Charset.forName("UTF-8"));
        keysAndArgs[1] = token.getBytes(Charset.forName("UTF-8"));
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection conn = factory.getConnection();
        try {
            Long result = (Long)conn.scriptingCommands().eval(unlockScript.getBytes(Charset.forName("UTF-8")), ReturnType.INTEGER, 1, keysAndArgs);
            if(result!=null && result>0)
                return true;
        }finally {
            RedisConnectionUtils.releaseConnection(conn, factory);
        }
        
        return false;
    }
}
