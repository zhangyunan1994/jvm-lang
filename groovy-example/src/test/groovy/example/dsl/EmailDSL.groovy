package example.dsl

class EmailSpec {
    void from(String from) { println "From: $from" }

    void to(String... to) { println "To: $to" }

    void subject(String subject) { println "Subject: $subject" }
}

def email(Closure cl) {
    def email = new EmailSpec()
    def code = cl.rehydrate(email, this, this)
    code.resolveStrategy = Closure.DELEGATE_ONLY
    code()
}

email {
    from 'dsl-guru@mycompany.com'
    to 'john.doe@waitaminute.com'
    subject 'The pope has resigned!'
}
