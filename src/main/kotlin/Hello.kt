import com.example.build.Template

class Hello {
    fun print(){
        println("Build Competed")
    }
}

fun main(args: Array<String>) {
    val d = Hello()
    d.print()
    Template().build()
}