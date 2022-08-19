package org.example

println "ddd"

def abc = "abc"
println abc

abc = 123
println abc

class Person implements Comparable<Person> {

  int age

  @Override
  int compareTo(Person o) {
    return 0
  }
}

def person18 = new Person(age: 18)
def person18a = new Person(age: 18)

assert person18 == person18a
assert person18 !== person18a

def dir = new File("../../..")

dir.eachFileRecurse { file ->
  println file.name
}