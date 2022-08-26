package example.base

import org.codehaus.groovy.control.CompilerConfiguration

def binding = new Binding()
def groovyShell = new GroovyShell(binding)
binding.setVariable("x", 1)
binding.setVariable("y", 2)
groovyShell.evaluate "z = x + y"
assert binding.getVariable('z') == 3

abstract class MyBaseClass extends Script {
  String name
  def greet() { println "Hello, $name!" }
}

def config = new CompilerConfiguration()
config.scriptBaseClass = 'io.github.bw.tutorial.example.MyBaseClass'
def shell = new GroovyShell(this.class.classLoader, config)
shell.evaluate """
    setName '张瑀楠'
    greet()
"""

abstract class MyGreetScript extends Script {
  int count
  abstract void scriptBody()
  def run() {
    count++
    scriptBody()
    count
  }
}

def greetConfig = new CompilerConfiguration()
greetConfig.scriptBaseClass = 'io.github.bw.tutorial.example.MyGreetScript'
def greetShell = new GroovyShell(this.class.classLoader, greetConfig)
def script = greetShell.parse("println 'Ok'")
assert script.run() == 1
assert script.run() == 2