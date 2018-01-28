package cn.wolfcode.shop.cache;

import redis.clients.jedis.JedisPoolConfig;

/**
 * created by king on 2018/1/20
 */
public class RedisConfig extends JedisPoolConfig {
    private String host = "localhost";
    private int port = 6379;
    private int connectionTimeout = 2000;
    private int soTimeout = 2000;
    private String password;
    private int database = 0;
    private String clientName;

    public RedisConfig() {
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        if (host == null || "".equals(host)) {
            host = "localhost";
        }

        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if ("".equals(password)) {
            password = null;
        }

        this.password = password;
    }

    public int getDatabase() {
        return this.database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String clientName) {
        if ("".equals(clientName)) {
            clientName = null;
        }

        this.clientName = clientName;
    }

    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSoTimeout() {
        return this.soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }
}