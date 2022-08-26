package example.base

import groovy.transform.ToString
import groovy.transform.TypeChecked

class BodySpec {
  void p(String p) {
    println "Body p: $p"
  }
}

class EmailSpec {
  void from(String from) { println "From: $from" }

  void to(String... to) { println "To: $to" }

  void subject(String subject) { println "Subject: $subject" }

  void body(@DelegatesTo(BodySpec) Closure body) {
    def bodySpec = new BodySpec()
    def code = body.rehydrate(bodySpec, this, this)
    code.resolveStrategy = Closure.DELEGATE_ONLY
    code()
  }
}

def email(@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = EmailSpec) Closure cl) {
  def email = new EmailSpec()
  def code = cl.rehydrate(email, this, this)
  code.resolveStrategy = Closure.DELEGATE_ONLY
  code()
}

email {
  from 'dsl-guru@mycompany.com'
  to 'john.doe@waitaminute.com'
  subject 'The pope has resigned!'
  body {
    p 'Really, the pope has resigned!'
  }
}

@TypeChecked
void sendEmail() {
  email {
    from 'dsl-guru@mycompany.com'
    to 'john.doe@waitaminute.com'
    subject 'The pope has resigned!'
    body {
      p 'Really, the pope has resigned!'
    }
  }
}

public <T> void configure(@DelegatesTo.Target List<T> elements,
                          @DelegatesTo(strategy = Closure.DELEGATE_FIRST, genericTypeIndex = 0) Closure configuration) {
  elements.each { e ->
    def clone = configuration.rehydrate(e, this, this)
    clone.resolveStrategy = Closure.DELEGATE_FIRST
    clone.call()
  }
}

@ToString
class Realm {
  String name

  void name(String name) { this.name = name }
}

List<Realm> list = []
3.times { list << new Realm() }
configure(list) {
  name 'My Realm'
}
assert list.every { it.name == 'My Realm' }
list.each { println it }

class Mapper<T,U> {
  final T value
  Mapper(T value) { this.value = value }
  U map(@DelegatesTo(type = "T") Closure<U> producer) {
    producer.delegate = value
    producer()
  }
}

def mapper = new Mapper<String,Integer>('Hello')
println mapper.map { length() }
