import kotlin.math.pow

interface MyNumber<T> {
    var a: Double
    var b: Double
    fun add(x: T): T
    fun neg(x: T): T
    fun sub(x: T): T = add(neg(x))
    operator fun plus(x: T): T = add(x)
    operator fun minus(x: T): T = sub(x)
}

class Complex(override var a: Double, override var b: Double) : MyNumber<Complex> {

    override fun add(x: Complex): Complex {
        return Complex(this.a + x.a, this.b + x.b)
    }

    override fun neg(x: Complex): Complex {
        return Complex(-x.a, -x.b)
    }

    override fun toString(): String {
        return if (b == 0.0) {
            "$a"
        } else if (b > 0) {
            "$a+${b}i"
        } else {
            "$a${b}i"
        }
    }
}

class Exponent(override var a: Double, override var b: Double) : MyNumber<Exponent> {

    override fun add(x: Exponent): Exponent {
        val thisValue = this.a.pow(this.b)
        val xValue = x.a.pow(x.b)
        val result = thisValue + xValue
        return Exponent(result, 1.0)
    }

    override fun neg(x: Exponent): Exponent {
        val xValue = x.a.pow(x.b)
        val result = -xValue
        return Exponent(result, 1.0)
    }

    override fun toString(): String {
        return "$a^$b"
    }
}

operator fun Complex.plus(x: Exponent): Complex {
    val newA = this.a + x.a.pow(x.b)
    return Complex(newA, this.b)
}

operator fun Exponent.plus(x: Complex): Complex {
    val newA = this.a.pow(this.b) + x.a
    return Complex(newA, x.b)
}

operator fun Complex.minus(x: Exponent): Complex {
    val newA = this.a - x.a.pow(x.b)
    return Complex(newA, this.b)
}

operator fun Exponent.minus(x: Complex): Complex {
    val newA = this.a.pow(this.b) - x.a
    return Complex(newA, x.b)
}


fun main() {
    // task-1
    val c1 = Complex(4.0,2.0)
    val c2 = Complex(1.0,2.0)
    println(c1+c2)
    println(c1-c2)

    // task-2
    val e1 = Exponent(2.0,3.0)
    val e2 = Exponent(1.0,2.0)
    println(e1+e2)
    println(e1-e2)

    // task-3
    println(Complex(4.0,2.0) + Exponent(2.0,3.0))
    println(Exponent(4.0,2.0) + Complex(2.0,3.0))
}