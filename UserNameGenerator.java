package com.wissen.practice;


@FunctionalInterface
interface UserNameGenerator {
    String generate(String firstName, String lastName, String yearOfBirth, String id);
}



