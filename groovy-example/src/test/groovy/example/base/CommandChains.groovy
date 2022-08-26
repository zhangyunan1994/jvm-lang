package example.base


show = { println it }
square_root = { Math.sqrt(it) }

def please(action) {
    [the: { what ->
        [of: { n -> action(what(n)) }]
    }]
}

showSelf = { println "I am showSelf"; this }

println 100
println(100)

println(showSelf.call())

please show the square_root of 100

please(show).the(square_root).of(100)

import com.google.common.base.*

def result = Splitter.on(',').trimResults(CharMatcher.is('_' as char)).split("_a ,_b_ ,c__").iterator().toList()
println result

def split(string) {
    [on: { sep ->
        [trimming: { trimChar ->
            Splitter.on(sep).trimResults(CharMatcher.is(trimChar as char)).split(string).iterator().toList()
        }]
    }]
}

// split("_a ,_b_ ,c__").on(",").trimming('_\')
result = split "_a ,_b_ ,c__" on ',' trimming '_'
println result

def aspect(before, after) {
    println before()
    print("method")
    after()
}

// 正常的调用方式
aspect({ println "before doing..." }, { println "after doing..." })


// 将闭包迁移到调用尾部，aspect() 的小括号 () 可写可不写。
aspect {
    println("before doing...")
    true
} {
    println("after doing...")
}
