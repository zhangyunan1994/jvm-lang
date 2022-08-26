package example.dsl

class SomeGroovyClass {

    def property1 = 'ha'
    def field2 = 'ho'
    def field4 = 'hu'

    def getField1() {
        return 'getHa'
    }

    def invokeMethod(String name, Object args) {
        return "called invokeMethod $name $args"
    }

    def getProperty(String name) {
        if (name != 'field3')
            return metaClass.getProperty(this, name)
        else
            return 'field3'
    }

    def haha() {
        return 'method exists'
    }
}

def someGroovyClass = new SomeGroovyClass()

println someGroovyClass.haha()
println someGroovyClass.someMethod()
assert someGroovyClass.field1 == 'getHa'
assert someGroovyClass.field2 == 'ho'
assert someGroovyClass.field3 == 'field3'
assert someGroovyClass.field4 == 'hu'



