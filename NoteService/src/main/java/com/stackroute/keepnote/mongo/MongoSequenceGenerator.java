package com.stackroute.keepnote.mongo;

@FunctionalInterface
public interface MongoSequenceGenerator {

    long getNextSequenceId(int id);
}
