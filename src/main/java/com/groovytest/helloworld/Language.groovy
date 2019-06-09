package com.groovytest.helloworld

//不需要语句结束符
println "1.不需要语句的结束符号";
def var = "hello world!"
println var
println var.class

//和 java 一样， 字符串 可以用 + 来拼接
println "2.使用+拼接字符串";
def str = "hello " +

        "world" +

        ",groovy!"
println str

//groovy也可以下面的方式， 三个引号之前不需要使用+来拼接
println "3.groovy 三引号拼接字符串写法,字符串的格式将会被保留";
def str2 = """hello

       world

       groovy!"""
println str2

//一切皆对象， groovy不关心对象的类型
println "4.一切皆对象， groovy不关心对象的类型";
def var1 = "hello " +
        "world" +

        ",groovy!"

println var1;

println var1.class;
var = 1001
println var.class

///////循环///////////
println "5.循环示例"
def hi= "hello world!"
//定义
def repeathi(hi){
    for (int i = 0; i <2 ; i++) {
        println hi;
    }
}
repeathi(hi)


println "上面的for语句还可以写成  for(i in 0..2)"
//定义
def repeat(hi){
    for (i in 0..2) {
        println hi;
    }
}
repeat(hi)

println "6.除了标准的java.lang.String以外（用’号括住），groovy还支持Gstring字符串类型（用“号括住）"
def repeat6(hi){
    for (i in 0..2) {
        println "This is  ${i} :${hi}"
    }
}
repeat6(hi);

println "示例2，运算"
def name="Tom"
println "Myname is ${"John"+name}"

def tom="Tom"
println "Myname is ${"Tom"==tom}"


//8、范围
//这个跟pascal中的“子界”是一样的。在前面的for循环介绍中我们已经使用过的for(i in 0..5)这样的用法，其中的0..5就是一个范围。
//范围 是一系列的值。
//  例如 “0..4” 表明包含 整数 0、1、2、3、4。Groovy 还支持排除范围，
//  “0..<4” 表示 0、1、2、3。还可以创建字符范围：“a..e” 相当于 a、b、c、d、e。“a..<e” 包括小于 e 的所有值。

//9 默认值
println "9. 默认值,当不传递参数时候，可以使用默认值"
def repeat9(hi,num=2){
    for (i in 0..<num) {
        println "This is  ${i} :${hi}"
    }
}
println "不传默认参数"
repeat9(hi);
println "传默认参数设置为5"
repeat9(hi,5);