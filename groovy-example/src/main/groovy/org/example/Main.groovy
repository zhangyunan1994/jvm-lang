package org.example

println "Hello world!"
println("hahah")

def ifThen(Closure<Boolean> a, Closure b) {
  if (a()) {
    b()
  }
}

def ifa = {
  return true
}

def thenB = {
  println "then 被执行了"
}

ifThen(ifa, thenB)

ifThen ifa, thenB

ifThen { return true }, { println "abc"}



def testSwitch(val) {
  switch (val) {
    case 52 -> 'Number value match'
    case "Groovy 4" -> 'String value match'
    case ~/^Switch.*Groovy$/ -> 'Pattern match'
    case BigInteger -> 'Class isInstance'
    case 60..90 -> 'Range contains'
    case [21, 'test', 9.12] -> 'List contains'
    case 42.056 -> 'Object equals'
    case { it instanceof Integer && it < 50 } -> 'Closure boolean'
    case [groovy: 'Rocks!', version: '1.7.6'] -> "Map contains key '$val'"
    default -> 'Default'
  }
}

assert testSwitch(52) == 'Number value match'
assert testSwitch("Groovy 4") == 'String value match'
assert testSwitch("Switch to Groovy") == 'Pattern match'
assert testSwitch(42G) == 'Class isInstance'
assert testSwitch(70) == 'Range contains'
assert testSwitch('test') == 'List contains'
assert testSwitch(42.056) == 'Object equals'
assert testSwitch(20) == 'Closure boolean'
assert testSwitch('groovy') == "Map contains key 'groovy'"
assert testSwitch('default') == 'Default'
