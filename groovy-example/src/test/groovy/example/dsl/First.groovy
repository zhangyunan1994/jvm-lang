package example.dsl

import org.codehaus.groovy.runtime.InvokerHelper

println "hello"
println 1 /* one */ + 2 /* two */

def "abstract"() { true }
// when calling such methods, the name must be qualified using "this."
println this.abstract()

def map = [:]

map."an identifier with a space and double quotes" = "ALLOWED"
map.'with-dash-signs-and-single-quotes' = "ALLOWED"

assert map."an identifier with a space and double quotes" == "ALLOWED"
assert map.'with-dash-signs-and-single-quotes' == "ALLOWED"

def firstname = "Homer"
map."Simpson-${firstname}" = "Homer Simpson"
map.'Simpson-${firstname}' = "Homer Simpson Again"

assert map.'Simpson-Homer' == "Homer Simpson"
assert map.'Simpson-${firstname}' == "Homer Simpson Again"

def person = [name: 'Guillaume', age: 36]
assert "$person.name is $person.age years old" == 'Guillaume is 36 years old'

def number = 3.14
assert "$number is a ${number.class.name}" == '3.14 is a java.math.BigDecimal'

String thing = 'treasure'
assert 'The x-coordinate of the treasure is represented by treasure.x' ==
        "The x-coordinate of the $thing is represented by ${thing}.x"  // <= Curly braces required

def sParameterLessClosure = "1 + 2 == ${-> 3}"
assert sParameterLessClosure == '1 + 2 == 3'

def sOneParamClosure = "1 + 2 == ${w -> w << 3}"
assert sOneParamClosure == '1 + 2 == 3'

println "1 + 2 == ${w -> w.append('10')}"
println "1 + 2 == ${w -> w.append('10')}"

number = 1
def eagerGString = "value == ${number}"
def lazyGString = "value == ${-> number}"

assert eagerGString == "value == 1"
assert lazyGString == "value == 1"

number = 2
assert eagerGString == "value == 1"
assert lazyGString == "value == 2"

String takeString(String message) {
    assert message instanceof String
    return message
}

def message = "The message is ${'hello'}"
assert message instanceof GString

def result = takeString(message)
assert result instanceof String
assert result == 'The message is hello'

class Example {
    static void main(String[] args) {
        Student st = new Student();
        st.StudentID = 1;
        st.Marks1 = 10;
        println(st.DisplayMarks());
    }
}

trait Marks {
    void DisplayMarks() {
        println("Display Marks");
    }
}

class Student implements Marks {
    int StudentID
    int Marks1;
}

new Student().DisplayMarks()

def clos = { println "Hello Closure" }
clos.call()

def clos2 = { println "Hello ${it}" }
clos.call("World")

class MainScript extends Script {
    def run() {
        println 'Groovy world!'
    }
}

InvokerHelper.runScript(MainScript, args)


def counter  = {
    int count = 0;
    return {
        count++
        return count
    }
}.call()

println counter.call()
println counter.call()
println counter.call()
println counter.call()